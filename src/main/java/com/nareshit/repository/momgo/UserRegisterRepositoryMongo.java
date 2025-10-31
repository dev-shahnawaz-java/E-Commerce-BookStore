package com.nareshit.repository.momgo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nareshit.entity.mongo.UserRegisterMongo;

public interface UserRegisterRepositoryMongo extends MongoRepository<UserRegisterMongo, String> {

}
