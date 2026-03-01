package com.shop.vympel.enums;

import lombok.Getter;

@Getter
public enum ObjectStoragePath {
    PRODUCT("product");

    private final String value;
    ObjectStoragePath(String value) {
        this.value = value;
    }
}
