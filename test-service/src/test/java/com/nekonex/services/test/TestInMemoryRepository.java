package com.nekonex.services.test;

import com.nekonex.services.test.repository.ITestRepository;
import com.nekonex.services.repository.TestsInitialList;
import com.nekonex.services.test.model.Test;
import io.micronaut.context.annotation.Requires;
import io.micronaut.runtime.context.scope.Refreshable;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//The following class is only used for tests
@Refreshable
@Requires(property = "in-memory-store.enabled", value = "true", defaultValue = "false")
@Slf4j
public class TestInMemoryRepository implements ITestRepository {

    @Inject
    private TestsInitialList testInitialList;

    private List<Test> tests = new ArrayList<>();

    @PostConstruct
    public void init() {
        testInitialList.getTest().forEach(x -> tests.add(x));
        log.info("Tests: {}", tests.size());
        log.info("Test: {}", tests.get(0).kvg_id());
    }
    @Override
    public Test findByKVGId(String kvg_id) {
        return tests.stream()
                .filter(test ->
                        test.kvg_id().equals(kvg_id))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Test> pagination(int pageNumber, int pageSize) {
        List<Test> result = new ArrayList<>();
        for (int i = 0; i < tests.size(); ++i)
        {
            if (i / pageSize == pageNumber) {
                result.add(tests.get(i));
            }
        }
        return result;
    }
}