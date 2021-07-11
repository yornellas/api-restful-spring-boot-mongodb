package com.github.yornellas.apispringbootmongodb.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.github.yornellas.apispringbootmongodb.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
