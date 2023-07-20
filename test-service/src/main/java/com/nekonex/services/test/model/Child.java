package com.nekonex.services.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.micronaut.core.annotation.Introspected;

import java.util.List;
import java.util.stream.Collectors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Introspected
public record Child(
        String id,
        String element,
        List<Child> children) {

    @Override
    public String toString() {
        return "Child{" +
                "id='" + id +
                ", element=" + element +
                ", children=" +  (children != null? children.stream().map(x->x.toString()).collect(Collectors.joining("|")) : "")  +
                "}";
    }
}