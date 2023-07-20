package com.nekonex.services.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micronaut.core.annotation.Introspected;

import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Introspected
public record Test(String kvg_id,List<Child> children) {
    @Override
    public String toString() {
        return "Test{" +
                "id=" + kvg_id +              
                ", children=" +  (children != null ? children.stream().map(x->x.toString()).collect(Collectors.joining("|")) : "") +                
                "}";
    }
}
