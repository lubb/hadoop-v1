package com.lbb.bigdata.hadoop.mr.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.net.URI;

/**
 * 词频统计 hdfs
 */
public class WordCountApp {

    public static void main(String[] args) throws  Exception {

        System.setProperty("HADOOP_USER_NAME","root");

        Configuration configuration = new Configuration();

        configuration.set("fs.defaultFS","hdfs://192.168.1.100:8020");
        //创建一个job
        Job job = Job.getInstance(configuration);
        //设置job对应的参数
        job.setJarByClass(WordCountApp.class);

        //job设置的maper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置job对应的参数：mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置job对应的参数：reduce输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置job设置输入输出的路径
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.1.100:8020"), configuration, "root");
        Path outputPath = new Path("/wordcount/output");
        if(fileSystem.exists(outputPath)){
            fileSystem.delete(outputPath, true);
        }
        FileInputFormat.setInputPaths(job, new Path("/wordcount/input"));
        FileOutputFormat.setOutputPath(job, outputPath);

        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);


    }
}
