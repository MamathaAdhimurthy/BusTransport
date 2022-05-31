package bustransport.busbook.controller;

import java.util.ArrayList;
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
import bustransport.bus.service.BusService;
import bustransport.busbook.model.BusBook;
import bustransport.busbook.service.BusBookService;
import bustransport.exception.AccountLogoutException;
import bustransport.user.model.User;

@RestController
public class BusBookController {
	@Autowired
	private BusBookService ser;
	
	@Autowired
	private BusService ser1;

	Logger logger = LoggerFactory.getLogger(BusBookController.class);

	@PostMapping("/addbooking")
	public ResponseEntity<BusBook> addBooking(@Valid @RequestBody BusBook b,HttpSession session) {
		logger.info("Controller Method call of Insertion of Booking Data.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(ser.checkAdmin(a) && ser.checkEmpty(b) && ser.checkLogin(u) && ser.checkUser(b,u) && ser.checkPayType(b.getPaymentType())) {
			if(ser1.getBusAvailability(b.getBus(), b.getNoOfSeats())) {
				BusBook bb = ser.addBooking(b);
				logger.info("Booking Data got added by User.");
				return new ResponseEntity<BusBook>(bb, HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<BusBook>(new BusBook(),HttpStatus.NOT_ACCEPTABLE);
	}



	@GetMapping("/getbooking/{bookId}")
	public ResponseEntity<BusBook> getBooking(@PathVariable("bookId") int bookId,HttpSession session) {
		logger.info("Controller Method call of searching of Booking Data.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(ser.checkAdmin(a) && ser.checkLogin(u)) {
			BusBook b = ser.getBooking(bookId);
			if(ser.checkUser(b,u)) {
					logger.info("Booking Data got searched by User.");
					return new ResponseEntity<BusBook>(b, HttpStatus.FOUND);
			} 
		}
		return new ResponseEntity<BusBook>(new BusBook(), HttpStatus.NOT_FOUND);
	}


	@PutMapping("/updatebooking")
	public ResponseEntity<BusBook> updateBooking(@Valid @RequestBody BusBook b,HttpSession session) {
		logger.info("Controller Method call of Updation of Booking Data.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(ser.checkAdmin(a) && ser.checkEmpty(b) && ser.checkLogin(u) && ser.checkUser(b,u) && ser.checkPayType(b.getPaymentType())) {
			BusBook bk=ser.getBooking(b.getBookId());
			if(ser1.getBusAvailability(b.getBus(), (b.getNoOfSeats())-bk.getBus().getSeatAvailability())) {
				BusBook bb = ser.updateBooking(b);
				logger.info("Booking Data got updated by User.");
				return new ResponseEntity<BusBook>(bb, HttpStatus.OK);
			}
		}
		return new ResponseEntity<BusBook>(new BusBook(),HttpStatus.NOT_ACCEPTABLE);
	}

	
	@GetMapping("/getbookings")
	public ResponseEntity<List<BusBook>> getBookings(HttpSession session){
		logger.info("Controller Method call of searching all of Booking Data of a User.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(ser.checkAdmin(a) && ser.checkLogin(u)) {
			List<BusBook> b = ser.getBookings(u.getUserId());
			logger.info("All Booking Data of a User got searched.");
			return new ResponseEntity<List<BusBook>>(b, HttpStatus.FOUND);
			} 
		return new ResponseEntity<List<BusBook>>(new ArrayList<BusBook>(), HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getbookingsall")
	public ResponseEntity<List<BusBook>> getBookingsAll(HttpSession session){
		logger.info("Controller Method call of searching all of Booking Data of a User.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(a!=null && u==null) {
			List<BusBook> b = ser.getBookingsAll();
			logger.info("All Booking Data of a User got searched by Admin.");
			return new ResponseEntity<List<BusBook>>(b, HttpStatus.FOUND);
			} 
		throw new AccountLogoutException("611", "Admin is not logged in or User is logged in.");
	}


	@DeleteMapping("/deletebooking/{bookId}")
	public ResponseEntity<BusBook> deleteBooking(@PathVariable("bookId") int bookId,HttpSession session) {
		logger.info("Controller Method call of deletion of Booking Data.");
		User u=(User) session.getAttribute("user");
		Admin a=(Admin) session.getAttribute("admin");
		if(ser.checkAdmin(a) && ser.checkLogin(u)) {
			BusBook b = ser.deleteBooking(bookId);
			ser1.getBusAvailability(b.getBus(), -b.getNoOfSeats());
			if(ser.checkUser(b,u)) {
					logger.info("Booking Data got deleted by User.");
					return new ResponseEntity<BusBook>(b, HttpStatus.ACCEPTED);
			} 
		}
		return new ResponseEntity<BusBook>(new BusBook(), HttpStatus.NOT_FOUND);
	}

}	