package bustransport.busroute.service;

import java.util.List;

import bustransport.busroute.model.BusRoute;

public interface IBusRouteService {
	
	public BusRoute saveBusRoute(BusRoute br);
	public BusRoute getBusRoute(String routeNo);
	public List<BusRoute> getBusRoutes();
	public BusRoute updateBusRoute(BusRoute br);
	public BusRoute deleteBusRoute(String routeNo);
	public List<BusRoute> getByBusRouteStatus(String status);
	public List<BusRoute> getByBusRouteLessThanDistance(double d);

}
