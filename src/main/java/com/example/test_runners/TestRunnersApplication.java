package com.example.test_runners;

import com.example.test_runners.user.User;
import com.example.test_runners.user.UserHttpClient;
import com.example.test_runners.user.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.util.List;

@SpringBootApplication
public class TestRunnersApplication {

	private static final Logger log = LoggerFactory.getLogger(TestRunnersApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestRunnersApplication.class, args);
	}

	@Bean
	UserHttpClient userHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	@Bean
	CommandLineRunner runner(UserHttpClient client){
		return  args -> {
			List<User> users = client.findAll();
			System.out.println(users);

			User user = client.findById(2);
			System.out.println(user);
		};
	}

}
