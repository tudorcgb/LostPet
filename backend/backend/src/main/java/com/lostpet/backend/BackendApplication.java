package com.lostpet.backend;

import com.lostpet.backend.mail.connection.ServerConnection;
//import com.lostdog.backend.chat.WebsocketDemoApplication;
import com.lostpet.backend.rest.StorageApi;
import com.lostpet.backend.storage.StorageProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


@SpringBootApplication
@ComponentScan("com.lostpet")
@EnableConfigurationProperties(StorageProperties.class)
public class BackendApplication {

	public static void main(String[] args) throws IOException, TimeoutException {
		SpringApplication.run(BackendApplication.class, args);
        //SpringApplication.run(WebsocketDemoApplication.class, args);
		ServerConnection serverConnection = ServerConnection.getInstance();


	}
	@Bean
	CommandLineRunner init(StorageApi storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
