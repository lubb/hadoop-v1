package com.lbb.bigdata.hadoop.hdfs;


import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 使用HDFS API完成wordCount统计
 * 需求：统计HDFS上的文件的wc，然后将统计结构输出到hdfs
 * 功能拆分
 * 1、读取hdfs上的文件
 * 2、业务处理（词频统计）：对文件中的每一行数据都要进行业务处理
 * 3、将处理结果缓存起来
 * 4、将结果输出到hdfs
 */
public class HDFSWCApp01 {

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration();

        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.100:8020"), configuration, "root");

        FSDataInputStream fsDataInputStream = fileSystem.open(new Path("/hdfsapi/test/hello.txt"));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fsDataInputStream));

        String tempStr;

        Map<String,Integer> cacheMap = new HashMap<String, Integer>();
        while ((tempStr = bufferedReader.readLine())!=null){
           String[] words = tempStr.split("\t");
           for (String word : words){
               if(cacheMap.containsKey(word)){
                   cacheMap.put(word, Integer.parseInt(cacheMap.get(word).toString())+1);
               }else{
                   cacheMap.put(word, 1);
               }
           }
        }

        //存入到缓存
        //读取到hdfs
        FSDataOutputStream fsDataOutputStream = fileSystem.create(new Path("/hdfsapi/output/wc.out"));

        for(Map.Entry<String, Integer> entry:cacheMap.entrySet()){
            fsDataOutputStream.write((entry.getKey()+":"+entry.getValue()+"\n").getBytes());
        }
        fsDataOutputStream.close();
        fileSystem.close();
    }
}
