package bustransport.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bustransport.exception.EmptyInputException;
import bustransport.exception.InvalidUserAgeException;
import bustransport.user.dao.UserDao;
import bustransport.user.model.User;

@Service
public class UserService implements IUserService{

	Logger logger =LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserDao dao;


	public User addUser(User u) {
		logger.info("User Data is Inserted.");
		return dao.save(u);
	}


	@Override
	public User updateUser(User u) {
		dao.findById(u.getUserId()).get();
		logger.info("User Data is Updated.");
		return dao.save(u);
	}

	@Override
	public User deleteUser(String userID) {
		User u= dao.findById(userID).get();
		dao.delete(u);
		logger.info("User Data is Deleted.");
		return u;
	}



	@Override
	public User loginUser(String userID, String password) {
		User user=dao.findById(userID).get();
		if(user.getPassword().equals(password)) {
			logger.info("Login data with UserId is Avaialble ");
			return user;
		}else {
			logger.error("Login data with UserId is not Avaialble ");
			return new User();
		}
	}

	@Override
	public User getUser(String userID) {
		logger.info("User Data with Userid is Retrieved.");
		return dao.findById(userID).get();

	}

	@Override
	public List<User> getUsers() {
		logger.info("All User Data is Retrieved.");
		return dao.findAll();
	}

	//Boolean methods
	public boolean checkAgeValidity(User u) {
		if(u.getAge()>=80 && u.getAge()<=10) {
			throw new InvalidUserAgeException("603","User Age Should be Between 10-80.");
		}
		return true;
	}

	public boolean checkEmpty(User u) {
		if(u.getUserId().isBlank() && u.getPassword().isBlank() && u.getName().isBlank() && u.getEmail().isBlank() && !this.checkAgeValidity(u) && u.getPhoneNumber().isBlank() && u.getAddress().isBlank()) {
			throw new EmptyInputException("601", "User Data cannot be Empty.");
		}
		return true;
	}


}

