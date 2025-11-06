package com.nawaz.repository.momgo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nawaz.entity.mongo.BookModuleMongo;

public interface BookMongoRepo extends MongoRepository<BookModuleMongo, String> {

}
