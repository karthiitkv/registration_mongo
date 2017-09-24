package com.kvkit.registration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

@Configuration
@ComponentScan(basePackages = "com.kvkit.registration")
@EnableMongoRepositories({ "com.kvkit.registration.repositories" })
public class MongoConfig {
 
    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
 
    	MongoClientURI uri = new MongoClientURI(
				   "mongodb://admin:admin@cluster0-shard-00-00-a0e9z.mongodb.net:27017,"
				   + "cluster0-shard-00-01-a0e9z.mongodb.net:27017,cluster0-shard-00-02-a0e9z.mongodb.net:27017/test?"
				   + "ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
		MongoClient  mongoClient = new MongoClient(uri);
        return new SimpleMongoDbFactory(mongoClient, "test");
 
    }
 
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
 
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
 
    }
 
}
