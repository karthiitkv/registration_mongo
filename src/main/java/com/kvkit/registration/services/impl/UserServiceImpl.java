package com.kvkit.registration.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kvkit.registration.bean.User;
import com.kvkit.registration.repositories.UserRepository;
import com.kvkit.registration.services.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    UserRepository userRepo;
	
	public void create(User user) {
		userRepo.insert(user);

	}

	public void update(User user) {
		userRepo.save(user);

	}

	public void delete(User user) {
		userRepo.delete(user);

	}

	public void deleteAll() {
		userRepo.deleteAll();
	}

	public User find(User user) {
		return userRepo.findOne(user.getId());
	}

	public User findByName(String name) {
		return userRepo.findByName(name);
	}

	public User findByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	public List<User> findAll() {
		return userRepo.findAll();
	}

	public UserRepository getUserRepo() {
		return userRepo;
	}

	public void setUserRepo(UserRepository userRepo) {
		this.userRepo = userRepo;
	}

}
