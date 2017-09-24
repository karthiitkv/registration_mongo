package com.kvkit.registration.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.kvkit.registration.bean.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

	@Query("{ 'firstName' : ?0 }")
	public User findByName(String name);
	
	@Query("{ 'email' : ?0 }")
	public User findByEmail(String email);
	
}
