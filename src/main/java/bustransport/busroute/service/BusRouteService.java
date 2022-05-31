package bustransport.busroute.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bustransport.admin.model.Admin;
import bustransport.busroute.dao.BusRouteDao;
import bustransport.busroute.model.BusRoute;
import bustransport.exception.AccountLogoutException;
import bustransport.exception.AdminNotLoggedInException;
import bustransport.exception.BusRouteAvailabilityException;
import bustransport.exception.InvalidRouteStatusException;
import bustransport.user.model.User;

@Service
public class BusRouteService implements IBusRouteService {

	Logger logger=LoggerFactory.getLogger(BusRouteService.class);
	@Autowired
	private BusRouteDao dao;

	@Override
	public BusRoute saveBusRoute(BusRoute br){
		BusRoute br1= dao.save(br);
		logger.info("Bus Route Data Inserted.");
		return br1;
	}

	@Override
	public BusRoute getBusRoute(String routeNo) {
		BusRoute br=dao.findById(routeNo).get();
		logger.info("Retrieved Bus Route Data.");
		return br;
	}

	@Override
	public List<BusRoute> getBusRoutes() {
		List<BusRoute> br=dao.findAll();
		logger.info("Retrieved All Bus Route Data.");
		return br;
	}

	@Override
	public BusRoute updateBusRoute(BusRoute br) {
		dao.findById(br.getRouteNo()).get();
		logger.info("Bus Route Data Updated.");
		return dao.save(br);
	}

	@Override
	public BusRoute deleteBusRoute(String routeNo) {
		BusRoute br=dao.findById(routeNo).get();
		dao.delete(br);
		return br;
	}

	@Override
	public List<BusRoute> getByBusRouteStatus(String status) {
		logger.info("Retrieved Bus Route by routeStatus.");
		return dao.findByRouteStatus(status);
	}

	@Override
	public List<BusRoute> getByBusRouteLessThanDistance(double d) {
		logger.info("Retrieved Bus Route by Distance Less than Given distance.");
		return dao.findByDistanceLessThan(d);
	}

	//boolean methods
	public  boolean checkClass(String routeStatus) {
		if(routeStatus.equalsIgnoreCase("UnderConstruction") || routeStatus.equalsIgnoreCase("Open") || routeStatus.equalsIgnoreCase("Traffic")) {
			return true;
		}
		throw new InvalidRouteStatusException("608",routeStatus+" is not Valid.Route Status Should be UnderConstruction/Open/Traffic.");
	}

	public boolean checkAdmin(Admin a) {
		if(a==null){
			throw new AdminNotLoggedInException("615","Admin Login is Required to do Operation on Bus Route Data.");
		}
		return true;
	}

	public boolean checkUser(User u) {
		if(u!=null) {
			throw new AccountLogoutException("615", "User need to Logout and Admin need to login to perform BusRoute Operation.");
		}
		return true;
	}
	
	public boolean checkRouteAvailability(String routeNo) {
		BusRoute br=dao.findById(routeNo).get();
		if(br.getRouteStatus().equalsIgnoreCase("UnderConstruction")) {
			throw new BusRouteAvailabilityException("609","Route Status with UnderConstruction cannot be added to Bus.");
		}
		return true;
	}

}
