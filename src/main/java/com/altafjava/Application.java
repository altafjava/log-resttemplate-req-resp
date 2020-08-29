package com.altafjava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import com.altafjava.config.AppConfig;
import com.altafjava.service.UserService;
import lombok.extern.log4j.Log4j2;

/**
 * Application is the starting class for Spring boot Application. It is used to automatically get the user details from jsonplaceholder.
 * 
 * @author altaf
 *
 */
@Log4j2
@SpringBootApplication
public class Application {

	@Autowired
	private AppConfig appConfig;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = new SpringApplicationBuilder(Application.class).web(WebApplicationType.NONE).run(args);
		log.info("-----------------------------------------------------");
		log.info("RestTemplate Log Request Response Application started");
		Application application = context.getBean(Application.class);
		application.run(context);
	}

	public void run(ConfigurableApplicationContext context) {
		UserService userService = context.getBean(UserService.class);
		userService.fetchUsers(appConfig.getUsersURL());
		userService.createPost(appConfig.getPostsURL());
	}

}
