package bustransport.busroute.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bustransport.bus.model.Bus;

@Entity
@Table(name = "busroute")
public class BusRoute{
	@Id
	@Column(name = "route_No")
	private String routeNo;

	private String routeName;

	private String routeFrom;

	private String routeTo;

	private String routeStatus;

	private double distance;
	
	public BusRoute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BusRoute(String routeNo, String routeName, String routeFrom,  String routeTo,  String routeStatus, double distance) {
		super();
		this.routeNo = routeNo;
		this.routeName = routeName;
		this.routeFrom = routeFrom;
		this.routeTo = routeTo;
		this.routeStatus = routeStatus;
		this.distance = distance;
		
	}

	public String getRouteNo() {
		return routeNo;
	}

	public void setRouteNo(String routeNo) {
		this.routeNo = routeNo;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getRouteFrom() {
		return routeFrom;
	}

	public void setRouteFrom(String routeFrom) {
		this.routeFrom = routeFrom;
	}

	public String getRouteTo() {
		return routeTo;
	}

	public void setRouteTo(String routeTo) {
		this.routeTo = routeTo;
	}

	public String getRouteStatus() {
		return routeStatus;
	}

	public void setRouteStatus(String routeStatus) {
		this.routeStatus = routeStatus;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	@JsonIgnore
	@OneToMany(mappedBy = "busroute",orphanRemoval = true)
	private List<Bus> bus;

	public List<Bus> getBus() {
		return bus;
	}

	public void setBus(List<Bus> bus) {
		this.bus = bus;
	}
	
	
	
}
