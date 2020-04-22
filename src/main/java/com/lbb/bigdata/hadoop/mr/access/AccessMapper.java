package com.lbb.bigdata.hadoop.mr.access;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AccessMapper extends Mapper<LongWritable, Text, Text, Access> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] words = value.toString().split("\t");
        String telephone = words[0];
        Long up = Long.parseLong(words[words.length-2]);
        Long down = Long.parseLong(words[words.length-1]);
        context.write(new Text(telephone), new Access(telephone, up, down));
    }
}
