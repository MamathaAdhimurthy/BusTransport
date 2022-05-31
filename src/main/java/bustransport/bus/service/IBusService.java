package bustransport.bus.service;

import java.util.List;

import bustransport.bus.model.Bus;

public interface IBusService {
	//insertion of bus data
	public Bus saveBus(Bus b);
	//getting bus data using busno
	public Bus getBus(int busNo);
	//getting all available bus data
	public List<Bus> getBuses();
	//getting bus data using routename from route table
	public List<Bus> getBusByRouteName(String routeName);
	//updating bus data
	public Bus updateBus(Bus b);
	//delete bus data using busno
	public Bus deleteBus(int busNo);
	//geeting bus data using bus class
	public List<Bus> getBusByClass(String busClass);
	//getting bus data whose price is less than given price.
	public List<Bus> getBusByLessThanPrice(double price);
	//getting bus data using route from and route to.
	public List<Bus> getBusByRouteFromAndTo(String from,String to);
	//getting availability of a bus
	public boolean getBusAvailability(Bus b,int noOfSeats);
	//updating the availability of a bus
	public void updateAvailability(Bus b,int noOfSeats);
}
