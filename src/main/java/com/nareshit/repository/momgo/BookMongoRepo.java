package com.nareshit.repository.momgo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nareshit.entity.mongo.BookModuleMongo;

public interface BookMongoRepo extends MongoRepository<BookModuleMongo, String> {

}
