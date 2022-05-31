package bustransport.busroute.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bustransport.busroute.model.BusRoute;

public interface BusRouteDao extends JpaRepository<BusRoute, String> {

	public List<BusRoute> findByRouteStatus(String status);

	public List<BusRoute> findByDistanceLessThan(double d);

}
