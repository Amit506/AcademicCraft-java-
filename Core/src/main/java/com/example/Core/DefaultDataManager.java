package com.example.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultDataManager {

    public  static Map<String,DefaultProperty> defaultPropertyList= new HashMap<>();

    public  static  void addDefaultProperty(DefaultProperty defaultProperty){
        defaultPropertyList.put(defaultProperty.getName(),defaultProperty);


    }
}
