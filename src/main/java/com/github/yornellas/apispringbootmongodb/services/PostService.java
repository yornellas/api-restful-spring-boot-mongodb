package com.github.yornellas.apispringbootmongodb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.yornellas.apispringbootmongodb.domain.Post;
import com.github.yornellas.apispringbootmongodb.repositories.PostRepository;
import com.github.yornellas.apispringbootmongodb.services.exceptions.ObjectNotFoundException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repository;
	
	public Optional<Post> findById(String id) {
		Optional<Post> obj = repository.findById(id);
		if(obj == null) {
			throw new ObjectNotFoundException("Post não encontrado");
		}
		return obj;
	}
}