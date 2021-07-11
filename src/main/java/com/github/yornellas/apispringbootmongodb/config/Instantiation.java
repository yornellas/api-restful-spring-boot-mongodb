package com.github.yornellas.apispringbootmongodb.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.yornellas.apispringbootmongodb.domain.User;
import com.github.yornellas.apispringbootmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User marina = new User(null, "Marina Araujo", "marina@gmail.com");
		User nayara = new User(null, "Nayara Salomão", "nayara@gmail.com");
		User veronica = new User(null, "Verônica Faria", "veronica@gmail.com");
		
		userRepository.deleteAll(); //limpa a base de dados do Mongo
		userRepository.saveAll(Arrays.asList(marina, nayara, veronica));
		
	}

}
