package com.spring.sid;

import java.util.List;

import com.spring.sid.beans.User;

public interface BaseController {

public List<User> users();
public User user(Long userId);
public List<User> userByCountry(String country);
public void user(User user);


}
