package com.sandsbeach.surfreport.adapter.surflies.dto.tide;

public enum TideType {
    LOW("low"),
    NORMAL("mid"),
    HIGH("high");

    private final String name;

    TideType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    // TODO have a json serializer and deserializer if needed
}
