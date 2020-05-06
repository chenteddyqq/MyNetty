package com.ted.tank;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private PropertyMgr(){}
    static Properties props = new Properties();
    public static Object get(String key){
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (props == null) {
            return null;
        }
        return props.get(key);
    }
}
