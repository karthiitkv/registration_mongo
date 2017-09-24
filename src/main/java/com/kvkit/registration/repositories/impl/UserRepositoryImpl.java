package com.kvkit.registration.repositories.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.StringOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.kvkit.registration.bean.User;
import com.mongodb.WriteResult;

//@Component
public class UserRepositoryImpl {
	
	@Autowired
	MongoOperations mongoTemplate;
	
	public boolean updateLastActivity(String userId) {
		WriteResult result = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(userId)),
                Update.update("lastActivity", new Date()), User.class);
		if(result != null && result.getN()>0) {
			return true;
		}
		return false;
	}
	
	public boolean userUpdate(Map<String, Object> map, String userId) {
		
		Update update = new Update();
		map.forEach((key, val) -> {
			update.set(key, val);
		});
		
		WriteResult result = mongoTemplate.updateFirst(new Query(Criteria.where("id").is(userId)),
				update, User.class);
		if(result != null && result.getN()>0) {
			return true;
		}
		return false;
	}
	
	public List<User> searchUsers(String searchParam) {
		Aggregation aggregate = Aggregation.newAggregation(
									Aggregation.project("firstName", "lastName", "email")
									.and(StringOperators.valueOf("firstName").concat(" ").concatValueOf("lastName")).as("fullName"),
									Aggregation.match(new Criteria().orOperator(Criteria.where("firstName").regex(searchParam, "i"),
											Criteria.where("lastName").regex(searchParam, "i"),
											Criteria.where("fullName").regex(searchParam, "i"))));
		return mongoTemplate.aggregate(aggregate, User.class, User.class).getMappedResults();
	}
	
	public List<User> searchUsersByName(String name) {
		Query query = new Query(Criteria.where("name").regex(name, "i"));
		return mongoTemplate.find(query, User.class);
	}
	
	
}
