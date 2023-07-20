package com.nekonex.services.test.controller;

import com.nekonex.services.test.repository.ITestRepository;
import com.nekonex.services.test.model.Test;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Secured(SecurityRule.IS_ANONYMOUS)
@Controller("/api/test")
@Slf4j
@OpenAPIDefinition(
        info = @Info(
                title = "Test Controller",
                version = "0.0",
                description = "Nekonex API"
        )
)
public class TestController {
    private ITestRepository repository;

    TestController(ITestRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Find a test")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The test that was found ")
    })
    @Get("/kvg/{kvg_id}")
    public Test findById(String kvg_id) throws UnsupportedEncodingException {
        String decodedId = URLDecoder.decode(kvg_id, StandardCharsets.UTF_8.toString());
        log.info("test find: id={}, decoded={}", kvg_id, decodedId);
        Test test = repository.findByKVGId(kvg_id);
        return test;
    }
    @Get("/page")
    public List<Test> getAllTests(int pageNumber, int pageSize) {
        log.info("Test Page find: pageNumber={}, pageSize={}", pageNumber, pageSize);
        return repository.pagination(pageNumber, pageSize);
    }
}
