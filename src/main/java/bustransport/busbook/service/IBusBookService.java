package bustransport.busbook.service;

import java.util.List;

import bustransport.busbook.model.BusBook;

public interface IBusBookService {
	public BusBook addBooking(BusBook b);
	public BusBook getBooking(int bookId);
	public BusBook updateBooking(BusBook b);
	public BusBook deleteBooking(int bookId);
	public List<BusBook> getBookings(String userId);
	//admin
	public List<BusBook> getBookByPaymentType(String ptype);
	public List<BusBook> getBookingsAll();

}