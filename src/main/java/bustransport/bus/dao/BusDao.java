package bustransport.bus.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bustransport.bus.model.Bus;

public interface BusDao extends JpaRepository<Bus, Integer> {
	public List<Bus> findByBusrouteRouteName(String routeName);

	public List<Bus> findByBusClass(String busClass);

	public List<Bus> findByPriceLessThan(double price);

	public List<Bus> findByBusrouteRouteFromAndBusrouteRouteTo(String from, String to);

	//extra
	public List<Bus> findByBusrouteDistanceLessThan(double d);

}
