package com.lbb.bigdata.hadoop.mr.access;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AccessReducer extends Reducer<Text, Access, NullWritable, Access> {

    /**
     *
     * @param key 手机号
     * @param values <Access,Access/>
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<Access> values, Context context) throws IOException, InterruptedException {

        Long up = 0L;
        Long down = 0L;
        for (Access access : values){
            up+=access.getUp();
            down+=access.getDown();
        }
        context.write(NullWritable.get(), new Access(key.toString(), up, down));
    }
}
