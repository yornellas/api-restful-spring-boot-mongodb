package com.github.yornellas.apispringbootmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.github.yornellas.apispringbootmongodb.domain.Post;
import com.github.yornellas.apispringbootmongodb.domain.User;
import com.github.yornellas.apispringbootmongodb.dto.AuthorDTO;
import com.github.yornellas.apispringbootmongodb.repositories.PostRepository;
import com.github.yornellas.apispringbootmongodb.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll(); //limpa a base de dados do Mongo
		postRepository.deleteAll();
		
		User marina = new User(null, "Marina Araujo", "marina@gmail.com");
		User nayara = new User(null, "Nayara Salomão", "nayara@gmail.com");
		User veronica = new User(null, "Verônica Faria", "veronica@gmail.com");
		
		userRepository.saveAll(Arrays.asList(marina, nayara, veronica));
		
		Post post1 = new Post(null, sdf.parse("21/03/2019"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(nayara));
		Post post2 = new Post(null, sdf.parse("21/03/2019"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(nayara));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		nayara.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(nayara);
	}

}
