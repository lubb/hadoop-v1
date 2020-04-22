package com.lbb.bigdata.hadoop.mr.access;

import com.lbb.bigdata.hadoop.mr.wordCount.WordCountMapper;
import com.lbb.bigdata.hadoop.mr.wordCount.WordCountReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 词频统计 本地文件
 */
public class AccessApp {

    public static void main(String[] args) throws  Exception {


        Configuration configuration = new Configuration();

        //创建一个job
        Job job = Job.getInstance(configuration);
        //设置job对应的参数
        job.setJarByClass(AccessApp.class);

        //job设置的maper和reducer
        job.setMapperClass(AccessMapper.class);
        job.setReducerClass(AccessReducer.class);

        //设置分区
        job.setPartitionerClass(AccessPartitioner.class);
        job.setNumReduceTasks(3);

        //设置job对应的参数：mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Access.class);

        //设置job对应的参数：reduce输出的key和value类型
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);

        //设置job设置输入输出的路径
        FileInputFormat.setInputPaths(job, new Path("file/access/input"));
        FileOutputFormat.setOutputPath(job, new Path("file/access/output"));

        boolean result = job.waitForCompletion(true);

        System.exit(result ? 0 : -1);


    }
}
