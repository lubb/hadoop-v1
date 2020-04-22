package com.lbb.bigdata.hadoop.mr.wordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 词频统计 本地文件
 */
public class WordCountLocalApp {

    public static void main(String[] args) throws  Exception {


        Configuration configuration = new Configuration();

        //创建一个job
        Job job = Job.getInstance(configuration);
        //设置job对应的参数
        job.setJarByClass(WordCountLocalApp.class);

        //job设置的maper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //设置job对应的参数：mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //设置combiner
        job.setCombinerClass(WordCountReducer.class);

        //设置job对应的参数：reduce输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置job设置输入输出的路径
        FileInputFormat.setInputPaths(job, new Path("file/wordCount/input"));
        FileOutputFormat.setOutputPath(job, new Path("file/wordCount/output"));

        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);


    }
}
