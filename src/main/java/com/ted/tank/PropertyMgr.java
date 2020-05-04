package com.ted.tank;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {

    static Properties props = new Properties();

    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (props == null) {
            System.out.println("null");
            return null;
        }
        return props.get(key);
    }

}
