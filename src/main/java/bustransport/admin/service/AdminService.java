package bustransport.admin.service;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bustransport.admin.dao.AdminDao;
import bustransport.admin.model.Admin;
import bustransport.exception.AccountLogoutException;
import bustransport.exception.EmptyInputException;
import bustransport.exception.InvalidUserIdException;
import bustransport.user.model.User;



@Service
public class AdminService implements IAdminService {
	Logger logger=LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private AdminDao dao;

	@Override
	public Admin addAdmin(Admin a) {
		logger.info("Admin data is Inserted.");
		return dao.save(a);
	}

	@Override
	public Admin loginAdmin(String adminId, String password) {
		Admin admin = dao.findById(adminId).get();
		if (admin.getPassword().equals(password)) {
			logger.info("Admin data with AdminId is Available.");
			return admin;
		} else {
			logger.info("Admin data with AdminId is not Available.");
			return new Admin();

		}
	}

	@Override
	public Admin getAdmin(String adminId) {
		logger.info("Admin data is Retrieved.");
		return dao.findById(adminId).get();
	}
	
	@Override
	public Admin updateAdmin(Admin a) {
		dao.findById(a.getAdminId()).get();
		logger.info("Admin Data is Updated.");
		return dao.save(a);
	}

	@Override
	public Admin deleteAdmin(String adminId) {
		Admin a = dao.findById(adminId).get();
		dao.delete(a);
		logger.info("Admin data is Deleted.");
		return a;
	}

	//boolean methods
	public boolean checkEmpty(@Valid Admin a) {
		if(a.getAdminId().isBlank() && a.getPassword().isBlank() && a.getName().isBlank() && a.getEmail().isBlank() && a.getPhoneNumber().isBlank()) {
			throw new EmptyInputException("601", "Admin Input Data is Empty.");
		}
		return true;
	}

	public boolean checkUser(User u) {
		if(u==null) {
			return true;
		}
		throw new AccountLogoutException("611", "User Need to Logout Out.");
	}

	public boolean checkAdmin1(Admin a) {
		if(a==null) {
			return true;
		}
		throw new AccountLogoutException("611", "Admin need to Logout for new Admin Account Creation.");
	}

	public boolean checkAdmin2(Admin a) {
		if(a==null) {
			throw new AccountLogoutException("611", "Admin need be Logged in for Admin Account Operation.");
		}
		return true;
	}

	public boolean checkAdminId(Admin a, String adminId) {
		if(a.getAdminId().equals(adminId)) {
			return true;
		}
		throw new InvalidUserIdException("607", "Admin Id is InCorrect ,Enter Correct AdminId.");
	}

}

