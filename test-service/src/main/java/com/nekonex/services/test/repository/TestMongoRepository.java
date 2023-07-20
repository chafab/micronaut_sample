package com.nekonex.services.test.repository;


import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.nekonex.services.test.model.Test;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import jakarta.inject.Singleton;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.ArrayList;
import java.util.List;
@Singleton
@Requires(missingProperty = "in-memory-store.enabled")
public class TestMongoRepository implements ITestRepository {

    @Property(name = "mongodb.database")
    private String mongodbDatabase;
    private final String mongodbCollection = "test";

    private MongoClient mongoClient;

    TestMongoRepository(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }


    public Test findByKVGId(String kvg_id) {
        return repository().find(Filters.eq("kvg_id", kvg_id)).first();
    }

    public String getMongodbDatabase() {
        return mongodbDatabase != null ? mongodbDatabase :  "ZZZZZZZ";
    }

    public List<Test> pagination(int pageNumber, int pageSize) {
        int skip = (pageNumber - 1) * pageSize;

        final List<Test> tests = new ArrayList<>();
        repository()
                .find()
                .skip(skip)
                .limit(pageSize)
                .iterator()
                .forEachRemaining(tests::add);
        return tests;
    }

    private MongoCollection<Test> repository() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .register("com.nekonex.services.test.model")
//                .automatic(true)
                .build();

        CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(
                MongoClientSettings.getDefaultCodecRegistry(),
                CodecRegistries.fromProviders(pojoCodecProvider));
        return mongoClient.getDatabase(mongodbDatabase)
                .withCodecRegistry(pojoCodecRegistry)
                .getCollection(mongodbCollection, Test.class);
    }

}
