package com.github.yornellas.apispringbootmongodb.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.yornellas.apispringbootmongodb.domain.User;
import com.github.yornellas.apispringbootmongodb.dto.UserDTO;
import com.github.yornellas.apispringbootmongodb.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	public UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);	
	}
}