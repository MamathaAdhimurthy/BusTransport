package bustransport.admin.controller;

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
import bustransport.admin.service.AdminService;
import bustransport.user.model.User;


@RestController
public class AdminController {
	Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService ser;


	@PostMapping("/addadmin")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin a,HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		if(ser.checkEmpty(a) && ser.checkUser(u) && ser.checkAdmin1(ad)) {
		Admin ad1 = ser.addAdmin(a);
		logger.info("Admin Account data Is Inserted with adminId-"+ad1.getAdminId());
		return new ResponseEntity<Admin>(ad1, HttpStatus.CREATED);
		}
		logger.error("Admin Account Creation Failed.");
		return new ResponseEntity<Admin>(new Admin(), HttpStatus.NOT_IMPLEMENTED);
	}



	@GetMapping("/loginadmin")
	public ResponseEntity<Admin> getLogin(@Valid @RequestBody Admin a,HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		Admin admin = null;
		if(ser.checkUser(u) && ser.checkAdmin1(ad)) {
		admin = ser.loginAdmin(a.getAdminId(), a.getPassword());
		if(!admin.getAdminId().equals(null)) {
			session.setAttribute("admin", admin);
			logger.info("Admin Is Logged in with AdminId-"+admin.getAdminId());
			return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
		}
		}
		logger.info("Admin Is Logged in Failed");
		return new ResponseEntity<Admin>(admin, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@GetMapping("/logoutadmin")
	public ResponseEntity<Admin> getLogout(@Valid @RequestBody Admin a,HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		Admin admin = null;
		if(ser.checkUser(u) && ser.checkAdmin2(ad)) {
		admin = ser.loginAdmin(a.getAdminId(), a.getPassword());
		if(!admin.getAdminId().equals(null)) {
			session.setAttribute("admin", null);
			logger.info("Admin Is Logged out with AdminId-"+admin.getAdminId());
			return new ResponseEntity<Admin>(admin, HttpStatus.ACCEPTED);
		}
		}
		logger.info("Admin Is Logged out Failed.");
		return new ResponseEntity<Admin>(new Admin(), HttpStatus.NOT_ACCEPTABLE);
	}

	@GetMapping("/getadmin")
	public ResponseEntity<Admin> getAdmin(HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		if(ser.checkUser(u) && ser.checkAdmin2(ad)) {
		Admin ad1 = ser.getAdmin(ad.getAdminId());
		if (ad1.getAdminId().equals(ad.getAdminId())) {
			return new ResponseEntity<Admin>(ad1, HttpStatus.FOUND);
		}
		}
		return new ResponseEntity<Admin>(new Admin(), HttpStatus.NOT_FOUND);
	}

	@PutMapping("/updateadmin")
	public ResponseEntity<Admin> updateAdmin(@Valid @RequestBody Admin a,HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		if(ser.checkEmpty(a) && ser.checkUser(u) && ser.checkAdmin2(ad) && ser.checkAdminId(a,ad.getAdminId())) {
		Admin ad1 = ser.updateAdmin(a);
		logger.info("Admin Account data Is Updated with adminId-"+ad1.getAdminId());
		return new ResponseEntity<Admin>(ad1, HttpStatus.CREATED);
		}
		logger.error("Admin Account Updation Failed.");
		return new ResponseEntity<Admin>(new Admin(), HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping("/deleteadmin/{adminId}")
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("adminId") String adminId,HttpSession session) {
		User u=(User)session.getAttribute("user");
		Admin ad=(Admin)session.getAttribute("admin");
		if(ser.checkUser(u) && ser.checkAdmin2(ad) && ser.checkAdminId(ad, adminId)) {
		Admin ad1 = ser.deleteAdmin(adminId);
		logger.info("Admin Account is Deleted with AdminId-"+ad1.getAdminId());
		return new ResponseEntity<Admin>(ad1, HttpStatus.ACCEPTED);
		}
		logger.info("Admin Account Deletion Failed");
		return new ResponseEntity<Admin>(new Admin(), HttpStatus.NOT_FOUND);
	}
}
