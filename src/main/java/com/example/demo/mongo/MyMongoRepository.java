package com.example.demo.mongo;

import com.example.demo.mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MyMongoRepository extends MongoRepository<User, String> {

}
