package com.lbb.bigdata.hadoop.mr.wordCount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN:Map任务读数据key类型，offset，是每行数据起始位置的偏移量，Long
 * VALUEIN:Map任务度数据的value类型，其实就是一行行的字符串，String
 * KEYOUT:map方法自定义实现输出的key的类型
 * VALUEOUT:map方法自定义实现输出的value的类型
 *
 * 词频统计：相同单词的次数
 *
 * 模版模式
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        //把value对应的行数据按照制定的分隔符拆开
        String[] words = value.toString().split("\t");

        for(String word :words){
            //(hello,1) (word:1)
            context.write(new Text(word.toLowerCase()), new IntWritable(1));
        }
    }
}
