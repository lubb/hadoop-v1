package com.lbb.bigdata.hadoop.framework;

import java.io.IOException;
import java.util.Properties;

/**
 * 读取配置文件
 */
public class ParamsUtil {

    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ParamsUtil.class.getClassLoader().getResourceAsStream("util.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties(){
        return properties;
    }
}
