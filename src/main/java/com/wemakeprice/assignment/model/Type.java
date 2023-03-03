package com.wemakeprice.assignment.model;

import lombok.Getter;

public enum Type {
    REMOVE("HTML 태그 제외"),
    ALL("Text 전체");

    @Getter
    private final String value;

    Type(String value) {
        this.value = value;
    }
}
