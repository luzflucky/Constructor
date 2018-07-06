package com.maomi.coder.template;

import com.mongodb.DBCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component("mgTemplate")
public class MgTemplate {

    @Autowired(required = false)
    private MongoTemplate mongoTemplate;

    public DBCollection getCollection(String collectionName) {
        return mongoTemplate.getCollection(collectionName);
    }

}