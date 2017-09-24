package com.kvkit.registration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.kvkit.registration.bean.User;
import com.kvkit.registration.config.MongoConfig;
import com.kvkit.registration.repositories.impl.UserRepositoryImpl;
import com.kvkit.registration.services.UserService;

/**
 * Hello world!
 *
 */
public class App 
{
	static AbstractApplicationContext context;
	static UserService userService;
	
	static UserRepositoryImpl userRepoImpl;
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        context = new AnnotationConfigApplicationContext(MongoConfig.class);
        userService = (UserService) context.getBean("userService");
        userRepoImpl = (UserRepositoryImpl) context.getBean(UserRepositoryImpl.class);
        //createUser();
        //deleteUser();
        //System.out.println(findByName("Vino"));
        //System.out.println(findByEmail("Vino@gmail.com"));
        updateLastActivity("2");
    }

	public static void createUser() {
    	User user1 = new User();
    	user1.setId("1");
    	user1.setFirstName("Karthi");
    	user1.setEmail("karthiitkv@gmail.com");
    	user1.setPassword("password");
    	
    	userService.create(user1);
    	
    	User user2 = new User();
    	user2.setId("2");
    	user2.setFirstName("Vino");
    	user2.setEmail("Vino@gmail.com");
    	user2.setPassword("password2");
    	userService.create(user2);
    }
    
    public static void deleteUser() {
    	User user = new User();
    	user.setId("1");
    	userService.delete(user);
    }
    
    private static User findByName(String name) {
		return userService.findByName(name);
		
	}
    
    private static User findByEmail(String email) {
		return userService.findByEmail(email);
		
	}
    
    private static void searchUsersByName(String string) {
		System.out.println(userRepoImpl.searchUsersByName("vino"));
	}
    
    private static void updateLastActivity(String id) {
		System.out.println(userRepoImpl.updateLastActivity(id));
	}
}
