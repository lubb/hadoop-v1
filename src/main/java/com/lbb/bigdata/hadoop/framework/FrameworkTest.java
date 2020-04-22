package com.lbb.bigdata.hadoop.framework;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Properties;

/**
 * 使用HDFS API完成wordCount统计
 * 需求：统计HDFS上的文件的wc，然后将统计结构输出到hdfs
 * 功能拆分
 * 1、读取hdfs上的文件
 * 2、业务处理（词频统计）：对文件中的每一行数据都要进行业务处理
 * 3、将处理结果缓存起来
 * 4、将结果输出到hdfs
 */
public class FrameworkTest {

    public static void main(String[] args) throws Exception {

        Properties properties = ParamsUtil.getProperties();

        Configuration configuration = new Configuration();

        FileSystem fileSystem = FileSystem.get(new URI(properties.getProperty(Constants.HADOOP_CONNECT_URL)), configuration, properties.getProperty(Constants.HADOOP_CONNECT_NAME));

        FSDataInputStream fsDataInputStream = fileSystem.open(new Path(properties.getProperty(Constants.HADOOP_READ_FILE)));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream));

        String tempStr;
        MyContext context = new MyContext();
        Map<Object, Object> cacheMap = context.getCacheMap();

        Class<?> clazz = Class.forName(properties.getProperty(Constants.HADOOP_REFLECT_CLASS));

        MyMapper wordCountMapper = (MyMapper) clazz.newInstance();

        while ((tempStr = bufferedReader.readLine())!=null){
            wordCountMapper.map(tempStr, context);
        }
        //读取到hdfs
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path(properties.getProperty(Constants.HADOOP_OUTPUT_FILE)));

        for(Map.Entry<Object, Object> entry:cacheMap.entrySet()){
            fsDataOutputStream.write((entry.getKey().toString()+":"+entry.getValue()+"\n").getBytes());
        }
        fsDataOutputStream.close();
        fileSystem.close();
    }
}
