package com.nekonex.services.test;

import com.nekonex.services.test.repository.TestMongoRepository;

import io.micronaut.runtime.Micronaut;
import io.micronaut.context.BeanContext;
import com.nekonex.services.test.repository.TestMongoRepository;

public class TestApplication {
	public static void main(String[] args) {
		//BeanContext context = BeanContext.run();
        //TestMongoRepository repository = context.getBean(TestMongoRepository.class);
        //System.out.println("MongoDB Database: " + repository.getMongodbDatabase());
		Micronaut.run(TestApplication.class);
	}
	
}
