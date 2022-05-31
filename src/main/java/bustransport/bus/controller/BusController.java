package bustransport.bus.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bustransport.admin.model.Admin;
import bustransport.bus.model.Bus;
import bustransport.bus.service.BusService;
import bustransport.busroute.service.BusRouteService;
import bustransport.exception.AccountLogoutException;
import bustransport.user.model.User;

@RestController
public class BusController {
	private Logger logger=LoggerFactory.getLogger(BusController.class);

	@Autowired
	private BusService ser;
	
	@Autowired
	private BusRouteService ser1;

	@PostMapping("/savebus")
	public ResponseEntity<Bus> saveBus(@Valid @RequestBody Bus b,HttpSession session) {
		logger.info("Controller Method call of Insertion of Bus Data.");
		if(((User)session.getAttribute("user"))==null && (((Admin)session.getAttribute("admin"))!=null)) {
			if(ser.checkEmpty(b) && ser1.checkRouteAvailability(b.getBusroute().getRouteNo())) {
				if(ser.checkNotClass(b.getBusClass())) {
					b.setSeatAvailability(b.getBusCapacity());
					Bus bb=ser.saveBus(b);
					logger.info("Bus Data got added by Admin.");
					return new ResponseEntity<Bus>(bb,HttpStatus.CREATED);
				}
			}
		}
		throw new AccountLogoutException("611", "User is logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbus/{no}")
	public ResponseEntity<Bus> getBus(@PathVariable("no") int busNo,HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			Bus b=ser.getBus(busNo);
			logger.info("Bus Data is searched by User or Admin.");
			return new ResponseEntity<Bus>(b,HttpStatus.FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbuses")
	public ResponseEntity<List<Bus>> getBuses(HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			logger.info("All Bus Data is searched by User or Admin");
			return new ResponseEntity<List<Bus>>(ser.getBuses(),HttpStatus.FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbusbyroutename")
	public ResponseEntity<List<Bus>> getBusByRouteName(@RequestBody Bus b,HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			List<Bus> b1=ser.getBusByRouteName(b.getBusroute().getRouteName());
			if(b1!=null) {
				logger.info("All Bus Data is searched by User or Admin using route name.");
				return new ResponseEntity<List<Bus>>(b1,HttpStatus.FOUND);
			}
			return new ResponseEntity<List<Bus>>(b1,HttpStatus.NOT_FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");
	}

	@PutMapping("/updatebus")
	public ResponseEntity<Bus> updateBus(@Valid @RequestBody Bus b,HttpSession session){
		if(((User)session.getAttribute("user"))==null && (((Admin)session.getAttribute("admin"))!=null)) {
			logger.info("Controller Method call of Updation of Bus Data.");
			if(ser.checkEmpty(b) && ser1.checkRouteAvailability(b.getBusroute().getRouteNo())) {
				if(ser.checkNotClass(b.getBusClass())) {
					Bus bb=ser.updateBus(b);
					logger.info("Bus Data got updated by Admin.");
					return new ResponseEntity<Bus>(bb,HttpStatus.CREATED);
				}
			}
		}
		throw new AccountLogoutException("611", "User is logged in or Admin is not Logged in.");
	}

	@DeleteMapping("/deletebus/{no}")
	public ResponseEntity<Bus> deleteBus(@PathVariable("no") int busNo,HttpSession session){
		if(((User)session.getAttribute("user"))==null && (((Admin)session.getAttribute("admin"))!=null)) {
			Bus b=ser.deleteBus(busNo);
			logger.info("Bus Data is Deleted By Admin.");
			return new ResponseEntity<Bus>(b,HttpStatus.ACCEPTED);
		}
		throw new AccountLogoutException("611", "User is logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbusbyclass/{c}")
	public ResponseEntity<List<Bus>> getBusByClass(@PathVariable("c") String busclass,HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			List<Bus> b1=ser.getBusByClass(busclass);
			if(b1!=null) {
				logger.info("All Bus Data is searched by User or Admin using Bus Class.");
				return new ResponseEntity<List<Bus>>(b1,HttpStatus.FOUND);
			}
			return new ResponseEntity<List<Bus>>(b1,HttpStatus.NOT_FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbusbypricelessthan/{p}")
	public ResponseEntity<List<Bus>> getBusByLessThanPrice(@PathVariable("p") double price,HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			List<Bus> b=ser.getBusByLessThanPrice(price);
			if(b!=null) {
				logger.info("All Bus Data is searched by User or Admin using price(Less Than).");
				return new ResponseEntity<List<Bus>>(b,HttpStatus.FOUND);
			}
			return new ResponseEntity<List<Bus>>(b,HttpStatus.NOT_FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");
	}

	@GetMapping("/getbusbyroutetofrom")
	public ResponseEntity<List<Bus>> getBusByRouteFromAndTo(@RequestBody Bus b,HttpSession session){
		if(((User)session.getAttribute("user"))!=null || (((Admin)session.getAttribute("admin"))!=null)) {
			List<Bus> b1=ser.getBusByRouteFromAndTo(b.getBusroute().getRouteFrom(), b.getBusroute().getRouteTo());
			if(b1!=null) {
				logger.info("All Bus Data is searched by User or Admin using Bus Route From and To.");
				return new ResponseEntity<List<Bus>>(b1,HttpStatus.FOUND);
			}
			return new ResponseEntity<List<Bus>>(b1,HttpStatus.NOT_FOUND);
		}
		throw new AccountLogoutException("611", "User is not logged in or Admin is not Logged in.");	
	}
}
