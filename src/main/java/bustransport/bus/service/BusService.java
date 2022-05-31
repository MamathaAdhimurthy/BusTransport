package bustransport.bus.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bustransport.bus.dao.BusDao;
import bustransport.bus.model.Bus;
import bustransport.busroute.model.BusRoute;
import bustransport.exception.BusRouteAvailabilityException;
import bustransport.exception.EmptyInputException;
import bustransport.exception.InsufficientSeatsAvailableException;
import bustransport.exception.InvalidBusClassException;

@Service
public class BusService implements IBusService {

	Logger logger=LoggerFactory.getLogger(BusService.class);

	@Autowired
	private BusDao busdao;

	@Override
	public Bus saveBus(Bus b) {
		logger.info("Bus Data Inserted.");
		return busdao.save(b);
	}

	@Override
	public Bus getBus(int busNo) {
		logger.info("Bus data Retrieved");
		return busdao.findById(busNo).get();
	}

	@Override
	public List<Bus> getBuses() {
		logger.info("All Bus data Retrieved");
		return busdao.findAll();
	}

	@Override
	public List<Bus> getBusByRouteName(String routeName) {
		logger.info("All Bus data Retrieved by routeName.");
		return busdao.findByBusrouteRouteName(routeName);
	}

	@Override
	public Bus updateBus(Bus b) {
		Bus b1=busdao.findById(b.getBusNo()).get();
		b.setSeatAvailability(b1.getSeatAvailability());
		logger.info("Bus data Updated.");
		return busdao.save(b);
	}

	@Override
	public Bus deleteBus(int busNo) {
		Bus b=busdao.findById(busNo).get();
		busdao.delete(b);
		logger.info("Bus data Deleted.");
		return b;
	}

	@Override
	public List<Bus> getBusByClass(String busClass) {
		logger.info("All Bus data Retrieved by Bus class");
		return busdao.findByBusClass(busClass);
	}

	@Override
	public List<Bus> getBusByLessThanPrice(double price) {
		logger.info("All Bus data Retrieved By price Less than.");
		return busdao.findByPriceLessThan(price);
	}

	@Override
	public List<Bus> getBusByRouteFromAndTo(String from, String to) {
		logger.info("All Bus data Retrieved by Route From and To.");
		return busdao.findByBusrouteRouteFromAndBusrouteRouteTo(from, to);
	}

	@Override
	public boolean getBusAvailability(Bus b,int noOfseats) {
		Bus bb=busdao.findById(b.getBusNo()).get();
		if((bb.getSeatAvailability()-noOfseats) >=0) {
			this.updateAvailability(bb, noOfseats);
			return true;
		}
		throw new InsufficientSeatsAvailableException("609","The No. of Seat Available Seats of Bus No-"+bb.getBusNo()+" is "+bb.getSeatAvailability());
	}
	@Override
	public void updateAvailability(Bus b,int noOfSeats) {
		Bus bb=busdao.findById(b.getBusNo()).get();
		bb.setSeatAvailability(b.getSeatAvailability()-noOfSeats);
		logger.info("Bus Seat Availability Updated.");
		busdao.save(bb);
	}

	//boolean methods
	public boolean checkNotClass(String busClass) {
		if(busClass.equalsIgnoreCase("Ac") ||busClass.equalsIgnoreCase("NonAc") ||busClass.equalsIgnoreCase("Seater")|| busClass.equalsIgnoreCase("Sleeper")) {
			return true;
		}
		throw new InvalidBusClassException("602","Bus Class Should be Ac/NonAc/Sleeper/Seater");
	}

	public boolean checkEmpty(Bus b) {
		if(b.getBusModel().isBlank() || b.getBusClass().isBlank() || b.getBusroute().getRouteNo().isBlank() || b.getBusCapacity()==0 || b.getDuration()==0 ||b.getPrice()==0) {
			throw new EmptyInputException("601", "Enter the bus data properly and don't enter empty data.");
		}
		return true;
	}

}
