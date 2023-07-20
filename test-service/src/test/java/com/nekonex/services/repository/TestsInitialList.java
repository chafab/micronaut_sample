package com.nekonex.services.repository;

import io.micronaut.context.annotation.ConfigurationProperties;
import com.nekonex.services.test.model.Test;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties("test")
public class TestsInitialList {

    private List<Test> tests = new ArrayList<>();

    public List<Test> getTest () {
        return tests;
    }

    public void setTest(List<Test> tests) {
        this.tests = tests;
    }

}
