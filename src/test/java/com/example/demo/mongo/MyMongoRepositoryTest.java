package com.example.demo.mongo;

import com.example.demo.mongo.model.User;
import com.example.demo.repository.model.UserRole;
import com.mongodb.client.MongoClients;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Import(MongoConfiguration.class)
@ActiveProfiles("test")
class MyMongoRepositoryTest {
    @Autowired
    MyMongoRepository myMongoRepository;
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void shouldSaveAUser() {
        myMongoRepository.save(
                User.builder()
                        .firstName("Taras")
                        .lastName("Shevchenko")
                        .role(UserRole.ADMIN)
                        .build()
        );
    }

    @Test
    void shouldSaveUserThroughMongoTemplate() {
        mongoTemplate.upsert(
            new Query().addCriteria(
                    Criteria.where("lastName").is("Shevchenko")
            ),
                new Update().set("firstName", "Andriy"),
                User.class
        );
    }

    @Test
    void shouldSaveAUserWithoutSpring() {
        var mongoClient = MongoClients.create();
        var mongoDb = mongoClient.getDatabase("db");
        mongoDb.getCollection("user").insertOne(
                new Document().append("firstName", "Taras").append("eyeColor", "blue")
        );
    }
}