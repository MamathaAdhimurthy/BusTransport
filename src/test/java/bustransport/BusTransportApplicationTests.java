package bustransport;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import bustransport.admin.dao.AdminDao;
import bustransport.admin.model.Admin;
import bustransport.bus.dao.BusDao;
import bustransport.bus.model.Bus;
import bustransport.busbook.dao.BusBookDao;
import bustransport.busbook.model.BusBook;
import bustransport.busroute.dao.BusRouteDao;
import bustransport.busroute.model.BusRoute;
import bustransport.user.dao.UserDao;
import bustransport.user.model.User;

@SpringBootTest
class BusTransportApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private AdminDao adao;
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private BusBookDao bbdao;
	
	@Autowired
	private BusRouteDao brdao;
	
	@Autowired
	private BusDao bdao;
	
	@Test
	void testInsertionBusRoute() {
		BusRoute br=new BusRoute("R-101", "Thane-Udupi-1", "Thane", "Udupi", "UnderConstruction",1200);
		BusRoute br1=new BusRoute("R-102", "Thane-Cst", "Thane", "Cst", "Open",600);
		BusRoute br2=new BusRoute("R-103", "Thane-Udupi-2", "Thane", "Udupi", "Open",1600);
		brdao.save(br);
		brdao.save(br1);
		brdao.save(br2);
	}
	
	@Test
	void testInsertionBus() {
		BusRoute br=new BusRoute();
		br.setRouteNo("R-101");
		BusRoute br1=new BusRoute();
		br.setRouteNo("R-102");
		BusRoute br2=new BusRoute();
		br.setRouteNo("R-103");
//		Bus b=new Bus(101,"Tata Motors","Ac",br,30,25,1500);
//		Bus b1=new Bus(102,"Ashok Leyland","NonAc",br1,30,2.5,1500);
//		Bus b2=new Bus(103,"Tata Motors","Seater",br2,50,28,1200);
		Bus b3=new Bus(104,"Ashok Leyland","Sleeper",br1,20,2,700);
//		assertNotNull(bdao.save(b));
//		assertNotNull(bdao.save(b1));
//		assertNotNull(bdao.save(b2));
		assertNotNull(bdao.save(b3));
	}
	
	@Test
	void testDeletionBus() {
		bdao.deleteById(101);
		assertThat(bdao.existsById(101)).isFalse();
	}
	@Test
	public void addAdmin() {
	Admin a = new Admin();
	a.setAdminId("h123");
	a.setName("hari");
	a.setPassword("Likitha12");
	a.setEmail("likitha@gmail.com");
	a.setPhoneNumber("9381327958");
	adao.save(a);
	}




	@Test
	public void testLoginAdmin() {
	Admin a = adao.findByAdminIdAndPassword("a123", "Akshay@123");
	adao.save(a);




	}




	@Test
	public void testGetAdmin() {
	List<Admin> lst = adao.findAll();
	assertThat(lst.size()).isGreaterThan(0);
	}




	@Test
	public void testUpdateAdmin() {
	Admin a = adao.findById("14").get();
	a.setPassword("Manasa12");
	a.setName("manasa");
	a.setEmail("manasa@gmail.com");
	a.setPhoneNumber("7022422623");
	a.setName("Manasa");
	adao.save(a);




	}




	@Test
	public void testDeleteAdmin() {
	Admin a = adao.findById("14").get();
	adao.delete(a);
	// assertThat(adao.existsById(9)).isFalse();
	}




	@Test
	public void addUser() {
	User u = new User();
	u.setUserId("user101");
	u.setPassword("Jeep12345");
	u.setName("abhi");
	u.setAge(20);
	u.setPhoneNumber("7561327958");
	u.setEmail("arya12@gmail.com");
	u.setAddress("kerala");
	udao.save(u);
	}




	@Test
	public void testLoginUser() {
	User user = udao.findByUserIdAndPassword("user101", "Jeep12345");
	udao.save(user);
	}




	@Test
	public void UpdateUser() {
	User u = udao.findById("21").get();
	u.setPassword("Mamatha12567");
	u.setName("Mamatha");
	u.setAge(22);
	u.setPhoneNumber("7337679709");
	u.setEmail("mamatha@gmail.com");
	u.setAddress("Garachatlapalli");
	udao.save(u);
	}




	@Test
	public void testDeleteUser() {
	User u = udao.findById("24").get();
	udao.delete(u);
	}




	@Test
	public void testGetUser() {
	List<User> lst = udao.findAll();
	assertThat(lst.size()).isGreaterThan(0);
	}






	@Test
	public void testSaveBus() {
	Bus b3 = new Bus();
	b3.setBusNo(559);
	b3.setBusModel("Ashok Leyland");
	b3.setBusClass("Seater");



	b3.setBusCapacity(55);
	b3.setDuration(55.0);
	b3.setPrice(2500.0);
	b3.setSeatAvailability(55);
	BusRoute br = brdao.findById("R-102").get();
	b3.setBusroute(br);
	bdao.save(b3);
	}




	@Test
	void testDeletionBus1() {
	bdao.deleteById(259);
	assertThat(bdao.existsById(259)).isFalse();
	}






	@Test
	public void testGetBuses() {
	List<Bus> lst = bdao.findAll();
	assertThat(lst.size()).isGreaterThan(0);
	}




	@Test
	public void testgetBus() {
	Bus lst = bdao.findById(357).get();
	assertNotNull(lst);
	}




	@Test
	public void testgetByBusrouteRouteName() {
	List<Bus> lst = bdao.findByBusrouteRouteName("Thane-Udu2");
	assertThat(lst).isNotEmpty();
	}




	@Test
	public void testUpdateBus() {
	Bus b3 = bdao.findById(459).get();
	b3.setBusModel("Mahendra groups");
	b3.setBusClass("Seater");
	// b3.setBusroute("R-101");
	b3.setBusCapacity(45);
	b3.setDuration(34.0);
	b3.setPrice(1300.0);
	// BusRoute br = brdao.findById("R-103").get();
	bdao.save(b3);
	}




	@Test
	public void testDeleteBus() {
	Bus b = bdao.findById(257).get();
	bdao.delete(b);
	}




	@Test
	public void testgetBusByClass() {
	List<Bus> lst = bdao.findByBusClass("Sleeper");
	assertThat(lst).isNotEmpty();
	// System.out.println(lst);
	}




	@Test
	public void testgetBusByLessThanPrice() {
	List<Bus> lst = bdao.findByPriceLessThan(14522);
	assertThat(lst).isNotEmpty();
	}




	@Test
	public void testgetBusByRouteFromAndTo() {
	List<Bus> lst = bdao.findByBusrouteRouteFromAndBusrouteRouteTo("Thane", "Udupi");
	assertThat(lst).isNotEmpty();
	}



	@Test
	void testInsertionBusRoute1() {
	BusRoute br = new BusRoute("R-101", "Thane-Udupi-1", "Thane", "Udupi", "UnderConstruction", 750);
	BusRoute br1 = new BusRoute("R-102", "Thane-Cst", "Thane", "Cst", "Open", 350);
	BusRoute br2 = new BusRoute("R-103", "Thane-Udupi-2", "Thane", "Udupi", "Open", 250);
	brdao.save(br);
	brdao.save(br1);
	brdao.save(br2);
	}
	//@Test
	//void testSvaeBusRoute() {
	//BusRoute br = new BusRoute("R-104", "Kasaragod-TVM", "Kasaragod", "TVM", "UnderConstruction", 750);
	//BusRoute br1 = new BusRoute("R-105", "Wayanad-Palakkad", "Wayanad", "Palakkad", "Open", 350);
	//BusRoute br2 = new BusRoute("R-106", "Alappuzha-Malappuram", "Alappuzha", "Malappuram", "Open", 250);
	//brdao.save(br);
	//brdao.save(br1);
	//brdao.save(br2);
	//}



	//For Booking Bus Ticket
	@Test
	public void addBooking() {
	BusBook bb = new BusBook();
	bb.setBookId(6);
	bb.setPaymentType("PhonePe");
	bb.setNoOfSeats(7);
	Bus b = bdao.findById(559).get();
	bb.setBus(b);
	User u = udao.findById("arya123").get();
	bb.setUser(u);
	bbdao.save(bb);
	}




	@Test
	public void testGetBooking() {
	BusBook lst = bbdao.findById(4).get();
	assertNotNull(lst);
	}




	@Test
	public void testUpdateBooking() {
	BusBook bb = bbdao.findById(6).get();
	bb.setPaymentType("UPI");
	bb.setNoOfSeats(4);
	Bus b = bdao.findById(459).get();
	bb.setBus(b);
	User u = udao.findById("Vishnu123").get();
	bb.setUser(u);
	bbdao.save(bb);
	}



	@Test
	public void testDeleteBooking() {
	BusBook b = bbdao.findById(3).get();
	bbdao.delete(b);
	}
	
	@Test
	public void testgetBookByBookingType()
	{
	List<BusBook> lst=bbdao.findByPaymentType("UI");
	assertThat(lst).isNotEmpty();
	}
	
}
