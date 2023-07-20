package com.nekonex.services.test.repository;

import com.nekonex.services.test.model.Test;

import java.util.List;

public interface ITestRepository {

    Test findByKVGId(String element);
    List<Test> pagination(int pageNumber, int pageSize);
}
