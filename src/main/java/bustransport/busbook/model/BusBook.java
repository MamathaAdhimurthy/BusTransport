package bustransport.busbook.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import bustransport.bus.model.Bus;
import bustransport.user.model.User;

@Entity
@Table(name = "busbook")
public class BusBook {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int bookId;

	private String paymentType;

	private int noOfSeats;


	public BusBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusBook(int bookId,User user, Bus bus,String paymentType,int noOfSeats) {
		super();
		this.bookId = bookId;
		this.user = user;
		this.bus = bus;
		this.paymentType = paymentType;
		this.noOfSeats = noOfSeats;
	}



	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public int getNoOfSeats() {
		return noOfSeats;
	}

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}



	@ManyToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
	private Bus bus;

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}



}