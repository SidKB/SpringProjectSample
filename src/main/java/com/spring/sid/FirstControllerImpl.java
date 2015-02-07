package com.spring.sid;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.sid.beans.User;

@RestController
public class FirstControllerImpl implements BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstControllerImpl.class);
	public List<User> users;
	
	public FirstControllerImpl(){
		users= new ArrayList<User>();
		User user= new User(101L, "First User", 12345671L, "Country1");
		User user1= new User(102L, "Second User", 12345672L, "Country2");
		User user2= new User(103L, "Third User", 12345673L, "Country4");
		User user3= new User(104L, "Fourth User", 12345674L, "Country4");
		User user4= new User(105L, "Fifth User", 12345657L, "Country5");
		users.add(user);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
	}
	
	
	@RequestMapping(value = "/string/{userId}", method = RequestMethod.GET)
	public  @ResponseBody String allUsers(@PathVariable("userId") Long userId) {
		for(User user: users){
			if(user.getUserId().equals(userId)){
			logger.info(user.toString());
			return user.toString();
			}
		}
		
		return  "-------------------------ERROR";
	}
	
	
	@RequestMapping(value = "/users", method = RequestMethod.GET, produces="application/json")
	@Override
	public @ResponseBody List<User> users() {
		return users;
	}
	
	@RequestMapping(value = "/userId/{userId}", method = RequestMethod.GET, produces="application/json")
	@Override
	public @ResponseBody User user(@PathVariable("userId") Long userId) {
		
		for(User user: users){
			if(user.getUserId().equals(userId)){
			return user;
			}
		}
		return null;
	}
	
	@RequestMapping(value = "/country/{country}", method = RequestMethod.GET, 
			produces="application/xml")
	@Override
	public @ResponseBody List<User> userByCountry(@PathVariable("country") String country) {
		List<User> localUsers = new ArrayList<User>(); 
		for(User user: users){
			if(user.getCountry().equals(country)){
			localUsers.add(user);
			}
		}
		return localUsers;
	}
	
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes="application/json")
	@Override
	public void user(@RequestParam User user) {
		users.add(user);
	}
	
	
}
