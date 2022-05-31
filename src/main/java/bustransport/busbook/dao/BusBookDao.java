package bustransport.busbook.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import bustransport.busbook.model.BusBook;


public interface BusBookDao extends JpaRepository<BusBook, Integer> {
	public List<BusBook> findByUserUserId(String userId);
	public List<BusBook> findByPaymentType(String ptype);

	//	@Query("SELECT SUM(b.bus.seatAvialability) from BusBook b WHERE b.bus.busNo=?1")
	//	public int getSeatAvailabiltyByBusBusNo(int busNo);
}