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
import com.github.yornellas.apispringbootmongodb.dto.CommentDTO;
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
		User yolanda = new User(null, "Yolanda Ornellas", "yolanda@gmail.com");
		
		userRepository.saveAll(Arrays.asList(marina, nayara, veronica));
		
		Post post1 = new Post(null, sdf.parse("21/03/2019"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(nayara));
		Post post2 = new Post(null, sdf.parse("21/03/2019"), "Bom dia!", "Acordei feliz hoje!", new AuthorDTO(nayara));
		
		CommentDTO c1 = new CommentDTO("Boa viagem, Ge!", sdf.parse("21/03/2019"), new AuthorDTO(yolanda));
		CommentDTO c2 = new CommentDTO("Aproveita!", sdf.parse("21/03/2019"), new AuthorDTO(marina));
		CommentDTO c3 = new CommentDTO("Eba!", sdf.parse("21/03/2019"), new AuthorDTO(veronica));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		nayara.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(nayara);
	}

}
