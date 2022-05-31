package bustransport.bus.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bustransport.busbook.model.BusBook;
import bustransport.busroute.model.BusRoute;


@Entity
@Table(name = "bus")
public class Bus {
	@Id
	@JoinColumn(name = "bus_no")
	private int busNo;

	private String busModel;

	private String busClass;

	private int busCapacity;

	private double duration;

	private double price;

	private int seatAvailability;

	public Bus() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Bus(int busNo,String busModel,String busClass, BusRoute rou,int busCapacity,  double duration, double price) {
		super();
		this.busNo = busNo;
		this.busModel = busModel;
		this.busClass = busClass;
		this.busroute = rou;
		this.busCapacity = busCapacity;
		this.duration = duration;
		this.price = price;
	}
	public int getBusNo() {
		return busNo;
	}
	public void setBusNo(int busNo) {
		this.busNo = busNo;
	}
	public String getBusModel() {
		return busModel;
	}
	public void setBusModel(String busModel) {
		this.busModel = busModel;
	}
	public String getBusClass() {
		return busClass;
	}
	public void setBusClass(String busClass) {
		this.busClass = busClass;
	}

	public int getBusCapacity() {
		return busCapacity;
	}
	public void setBusCapacity(int busCapacity) {
		this.busCapacity = busCapacity;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSeatAvailability() {
		return seatAvailability;
	}
	public void setSeatAvailability(int seatAvailability) {
		this.seatAvailability = seatAvailability;
	}


	@ManyToOne()
	private BusRoute busroute;
	public BusRoute getBusroute() {
		return busroute;
	}
	public void setBusroute(BusRoute busroute) {
		this.busroute = busroute;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "bus",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<BusBook> booking;

	public List<BusBook> getBooking() {
		return booking;
	}

	public void setBooking(List<BusBook> booking) {
		this.booking = booking;
	}
	


}