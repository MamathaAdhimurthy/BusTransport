package bustransport.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bustransport.admin.model.Admin;
import bustransport.exception.AccountLogoutException;
import bustransport.exception.InvalidUserIdException;
import bustransport.user.model.User;
import bustransport.user.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService ser;

	Logger logger=LoggerFactory.getLogger(UserController.class);

	@PostMapping("/adduser")
	public ResponseEntity<User> addUser(@Valid @RequestBody User user,HttpSession session) {
		if(((User)session.getAttribute("user"))==null && ((Admin)session.getAttribute("admin"))==null) {
			if(ser.checkEmpty(user)) {
				User u=ser.addUser(user);
				logger.info("User is Created with userId-"+u.getUserId());
				return new ResponseEntity<User>(u, HttpStatus.CREATED);
			}
		}
		throw new AccountLogoutException("611","Logout From Account Before Another Account Creation.");
	}


	@GetMapping("/loginuser")
	public ResponseEntity<User> getLogin(@Valid @RequestBody User u,HttpSession session) {
		if(((User)session.getAttribute("user"))==null && ((Admin)session.getAttribute("admin"))==null) {
			User user = ser.loginUser(u.getUserId(), u.getPassword());
			if(user.getUserId().equals(u.getUserId())) {
				session.setAttribute("user", u);
				logger.info("User got logged in with Userid-"+u.getUserId());
				return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
			}
			else {
				session.setAttribute("user", null);
				logger.error("User Login Failed.");
				return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
			}
		}
		throw new AccountLogoutException("611", "User is logged In or Admin is Logged In.");
	}

	@GetMapping("/logoutuser")
	public ResponseEntity<User> Logout(HttpSession session) {
		User u=(User)session.getAttribute("user");
		if((u)!=null && ((Admin)session.getAttribute("admin"))==null) {
			User user = ser.loginUser(u.getUserId(), u.getPassword());
			if(user.getUserId().equals(null)) {
				logger.error("User Login Out Failed.");
				return new ResponseEntity<User>(user, HttpStatus.NOT_ACCEPTABLE);
			}
			else {
				session.setAttribute("user",null);
				logger.info("User Got Logged Out.");
				return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
			}
		}
		throw new AccountLogoutException("611", "User is not logged In or Admin is Logged In.");
	}

	@GetMapping("/getuser")
	public ResponseEntity<User> getUser(HttpSession session) {
		User u=(User)session.getAttribute("user");
		if(u!=null && ((Admin)session.getAttribute("admin"))==null) {
			User us = ser.getUser(u.getUserId());
			logger.info("Searching User Data By User.");
			return new ResponseEntity<User>(us, HttpStatus.FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged In or Admin is Logged In.");
	}

	@GetMapping("/getusers")
	public ResponseEntity<List<User>> getUsers(HttpSession session) {
		if(((User)session.getAttribute("user"))==null && ((Admin)session.getAttribute("admin"))!=null) {
			List<User> us = ser.getUsers();
			logger.info("Searching All User Data By Admin.");
			return new ResponseEntity<List<User>>(us, HttpStatus.FOUND);
		}
		throw new AccountLogoutException("611", "User is logged In or Admin is not Logged In.");
	}

	@GetMapping("/getuser/{aid}")
	public ResponseEntity<User> getUser(@PathVariable("aid") String userId, HttpSession session) {
		if(((User)session.getAttribute("user"))==null && ((Admin)session.getAttribute("admin"))!=null) {
			User us = ser.getUser(userId);
			logger.info("Searching User Data By Admin.");
			return new ResponseEntity<User>(us, HttpStatus.FOUND);
		}
		throw new AccountLogoutException("611", "User is logged In or Admin is not Logged In.");
	}

	@PutMapping("/updateuser")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User a,HttpSession session) {
		User u=(User)session.getAttribute("user");
		if((u)!=null && ((Admin)session.getAttribute("admin"))==null) {
			if(u.getUserId().equals(a.getUserId()) && ser.checkEmpty(a)) {
				User ad = ser.updateUser(a);
				return new ResponseEntity<User>(ad, HttpStatus.OK);
			}
			throw new InvalidUserIdException("606", "Invalid UserId entered.Please enter the correct UserId");
		}
		throw new AccountLogoutException("611", "User is  not logged In or Admin is Logged In."); 
	}



	@DeleteMapping("/deleteuser/{userId}")
	public ResponseEntity<User> deleteUser(@PathVariable("userId") String userId,HttpSession session) {
		User u=(User)session.getAttribute("user");
		if((u)!=null && ((Admin)session.getAttribute("admin"))==null) {
			if(u.getUserId().equals(userId)) {
				User ad = ser.deleteUser(userId);
				return new ResponseEntity<User>(ad, HttpStatus.ACCEPTED);
			}
			throw new InvalidUserIdException("606","InValid UserId ,Enter the Correct Userid.");
		}
		throw new AccountLogoutException("611", "User is not Logged In or Admin is Logged In.");
	}



}
