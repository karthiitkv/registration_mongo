package com.kvkit.registration.services;

import java.util.List;

import com.kvkit.registration.bean.User;

public interface UserService {

	 public void create(User User);
	 
    public void update(User User);
 
    public void delete(User User);
 
    public void deleteAll();
 
    public User find(User User);
 
    public User findByName(String brand);
 
    public User findByEmail(String model);
 
    public List <User> findAll();
}
