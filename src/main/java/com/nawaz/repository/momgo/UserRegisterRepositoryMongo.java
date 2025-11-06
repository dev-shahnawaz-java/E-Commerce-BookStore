package com.nawaz.repository.momgo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nawaz.entity.mongo.UserRegisterMongo;

public interface UserRegisterRepositoryMongo extends MongoRepository<UserRegisterMongo, String> {

}
