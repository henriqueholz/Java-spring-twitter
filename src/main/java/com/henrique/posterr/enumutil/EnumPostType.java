package com.henrique.posterr.enumutil;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum EnumPostType {
    TYPE1("Post"),
    TYPE2("Repost"),
    TYPE3("Quote");

    private String code;

    private EnumPostType(String code) {
        this.code=code;
    }

    @JsonCreator
    public static EnumPostType decode(final String code) {
        return Stream.of(EnumPostType.values()).filter(targetEnum -> targetEnum.code.equals(code)).findFirst().orElse(null);
    }

    @JsonValue
    public String getCode() {
        return code;
    }
}