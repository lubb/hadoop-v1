package com.lbb.bigdata.hadoop.framework;

public interface MyMapper {

    /**
     * 数据处理
     * @param line
     * @param context
     */
    void map(String line, MyContext context);

}
