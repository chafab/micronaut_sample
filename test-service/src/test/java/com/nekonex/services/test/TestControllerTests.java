package com.nekonex.services.test;

import com.nekonex.services.test.model.Test;
import io.micronaut.context.annotation.Property;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Property(name = "in-memory-store.enabled", value = "true")
@Requires(env = Environment.TEST)
@Slf4j
public class TestControllerTests {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @org.junit.jupiter.api.Test
    void findPage() {
        Test[] tests = client.toBlocking()
                .retrieve(HttpRequest.GET("/api/test/page?pageNumber=0&pageSize=10"), Test[].class);
        assertTrue(tests.length == 1);
        tests = client.toBlocking()
                .retrieve(HttpRequest.GET("/api/test/page?pageNumber=2&pageSize=10"), Test[].class);
        assertTrue(tests.length == 0);
    }

    @org.junit.jupiter.api.Test
    void findByElement() {
        String encodedParam = URLEncoder.encode("kvg:04e3b", StandardCharsets.UTF_8);
        log.info(encodedParam);
        Test test = client.toBlocking()
                .retrieve(HttpRequest.GET("/api/test/kvg/" + encodedParam), Test.class);
        assertNotNull(test);
        assertNotNull(test.kvg_id());
    }
}   
