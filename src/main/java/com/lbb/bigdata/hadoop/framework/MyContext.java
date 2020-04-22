package com.lbb.bigdata.hadoop.framework;

import java.util.HashMap;
import java.util.Map;

public class MyContext {

    private Map<Object, Object> cacheMap = new HashMap<Object, Object>();

    /**
     * 获取cacheMap
     * @return
     */
    public Map<Object, Object> getCacheMap(){
        return cacheMap;
    }

    /**
     * 往上下文写数据
     * @param key
     * @param value
     */
    public void write(Object key, Object value){
        cacheMap.put(key, value);
    }

    /**
     * 获取某个key的值
     * @param key
     * @return
     */
    public Object get(Object key){
        return cacheMap.get(key);
    }
}
