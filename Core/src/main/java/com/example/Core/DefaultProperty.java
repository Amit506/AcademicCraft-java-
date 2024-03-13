package com.example.Core;

public class DefaultProperty {


    private  final  String name;
    private  final  String value;

    public DefaultProperty(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
