package bustransport.busbook.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bustransport.admin.model.Admin;
import bustransport.busbook.dao.BusBookDao;
import bustransport.busbook.model.BusBook;
import bustransport.exception.AccountLogoutException;
import bustransport.exception.EmptyInputException;
import bustransport.exception.InvalidPaymentTypeException;
import bustransport.exception.InvalidUserIdException;
import bustransport.exception.UserNotLoggedInException;
import bustransport.user.model.User;

@Service
public class BusBookService implements IBusBookService {

	Logger logger=LoggerFactory.getLogger(BusBookService.class);

	@Autowired
	private BusBookDao dao;

	@Override
	public BusBook addBooking(BusBook b) {
		logger.info("Bus Booking Data is Inserted.");
		return dao.save(b);
	}

	@Override
	public BusBook getBooking(int bookId) {
		logger.info("Bus Book Data is Retrieved.");
		return dao.findById(bookId).get();
	}


	@Override
	public BusBook updateBooking(BusBook b) {
		dao.findById(b.getBookId()).get();
		logger.info("Bus Book Data is Updated.");
		return dao.save(b);
	}


	@Override
	public BusBook deleteBooking(int bookId) {
		BusBook b=dao.findById(bookId).get();
		dao.delete(b);
		logger.info("Bus Book Data is Deleted");
		return b;
	}



	@Override
	public List<BusBook> getBookings(String userId) {
		logger.info("All Bus Book Data is Retrieved by its User.");
		return dao.findByUserUserId(userId);
	}
	//Admin
	@Override
	public List<BusBook> getBookingsAll() {
		logger.info("Bus Book Data is Retrieved by Admin.");
		return dao.findAll();
	}

	//admin
	@Override
	public List<BusBook> getBookByPaymentType(String classname) {
		return dao.findByPaymentType(classname);
	}


	//Boolean methods
	public boolean checkPayType(String paymentType) {
		if(paymentType.equalsIgnoreCase("Upi") ||paymentType.equalsIgnoreCase("Net banking") || paymentType.equalsIgnoreCase("Debit") ||paymentType.equalsIgnoreCase("Credit")) {
			return true;
		}
		throw new InvalidPaymentTypeException("608","Payment Type Should be Upi/Net Banking/Credit/Debit.");
	}

	public boolean checkEmpty(BusBook b) {
		if(b.getBus().getBusNo()==0 || b.getUser().getUserId().isBlank() || b.getNoOfSeats()==0 || b.getPaymentType().isBlank()) {
			throw new EmptyInputException("601","BusBook Data is Empty.");
		}
		return true;
	}

	public boolean checkUser(BusBook b, User u) {
		if(b.getUser().getUserId().equals(u.getUserId())) {
			return true;
		}
		throw new InvalidUserIdException("607","User Need to Enter Correct User Id.");
	}

	public boolean checkLogin(User u) {
		if(u==null) {
			throw new UserNotLoggedInException("606","User need to login before booking Bus Ticket.");
		}
		return true;
	}

	public boolean checkAdmin(Admin a) {
		if(a==null) {
			return true;
		}
		throw new AccountLogoutException("611", "Admin need to logout.");
	}




}