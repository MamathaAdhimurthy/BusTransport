package bustransport.busroute.controller;

import java.util.ArrayList;
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
import bustransport.busroute.model.BusRoute;
import bustransport.busroute.service.BusRouteService;
import bustransport.user.model.User;

@RestController
public class BusRouteController {
	
	Logger logger=LoggerFactory.getLogger(BusRouteController.class);
	
	@Autowired
	private BusRouteService ser;
	
	@PostMapping("/saveroute")
	public ResponseEntity<BusRoute> saveBusRoute(@Valid @RequestBody BusRoute br,HttpSession session) {
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin")) && ser.checkClass(br.getRouteStatus())) {
		BusRoute br1=ser.saveBusRoute(br);
		logger.info("Bus Route Data is added by Admin with routeNo-"+br.getRouteTo());
		return new ResponseEntity<BusRoute>(br1,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<BusRoute>(new BusRoute(),HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping("/getroute/{id}")
	public ResponseEntity<BusRoute> getBusRoute(@PathVariable("id") String routeNo,HttpSession session){
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin"))) {
			BusRoute br=ser.getBusRoute(routeNo);
			logger.info("Searched Bus Route Data By Admin with routeNo-"+br.getRouteTo());
			return new ResponseEntity<BusRoute>(br,HttpStatus.FOUND);
		}
		return new ResponseEntity<BusRoute>(new BusRoute(),HttpStatus.NOT_FOUND);
	}

	@GetMapping("/getroutes")
	public ResponseEntity<List<BusRoute>> getBusRoutes(HttpSession session){
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin"))) {
			List<BusRoute> br=ser.getBusRoutes();
			logger.info("Searched All Bus Route By Admin");
			return new ResponseEntity<List<BusRoute>>(br,HttpStatus.FOUND);
		}
		return new ResponseEntity<List<BusRoute>>(new ArrayList<BusRoute>(),HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("updateroute")
	public ResponseEntity<BusRoute> updateBusRoute(@Valid @RequestBody BusRoute br,HttpSession session){
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin")) && ser.checkClass(br.getRouteStatus())) {
			BusRoute br1=ser.updateBusRoute(br);
			logger.info("BusRoute Data is Updated By Admin with routeNo-"+br1.getRouteNo());
			return new ResponseEntity<BusRoute>(br1,HttpStatus.OK);
		}
		return new ResponseEntity<BusRoute>(new BusRoute(),HttpStatus.NOT_MODIFIED);
	}
	
	@DeleteMapping("/deleteroute/{id}")
	public ResponseEntity<BusRoute> deleteBusRoute(@PathVariable("id") String routeNo,HttpSession session){
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin"))) {
			BusRoute br=ser.deleteBusRoute(routeNo);
			logger.info("BusRoute Data is Deleted by Admin with routeNo-"+br.getRouteNo());
			return new ResponseEntity<BusRoute>(br,HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<BusRoute>(new BusRoute(),HttpStatus.NOT_IMPLEMENTED);
	}
	
	@GetMapping("/getroutebystatus/{s}")
	public ResponseEntity<List<BusRoute>> getByBusRouteStatus(@PathVariable("s") String status,HttpSession session){
		List<BusRoute> br=null;
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin")) && ser.checkClass(status)) {
			br=ser.getByBusRouteStatus(status);
		}
		if(br!=null) {
			logger.info("BusRoute Data with RouteStatus-"+status+" is Searched By Admin.");
			return new ResponseEntity<List<BusRoute>>(br,HttpStatus.FOUND);
		}
		logger.info("BusRoute Data with RouteStatus-"+status+" is not Searched By Admin.");
		return new ResponseEntity<List<BusRoute>>(br,HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/getroutelessthandistance/{d}")
	public ResponseEntity<List<BusRoute>> getByBusRouteLessThanDistance(@PathVariable("d") double d,HttpSession session){
		List<BusRoute> br=null;
		if(ser.checkUser((User)session.getAttribute("user")) && ser.checkAdmin((Admin)session.getAttribute("admin"))) {
			br=ser.getByBusRouteLessThanDistance(d);
		}
		if(br!=null) {
			logger.info("BusRoute Data with distance Less than-"+d+" is Searched By Admin.");
			return new ResponseEntity<List<BusRoute>>(br,HttpStatus.FOUND);
		}
		logger.info("BusRoute Data with distance Less than-"+d+" is not Searched By Admin.");
		return new ResponseEntity<List<BusRoute>>(br,HttpStatus.NOT_FOUND);
	}
	
}
