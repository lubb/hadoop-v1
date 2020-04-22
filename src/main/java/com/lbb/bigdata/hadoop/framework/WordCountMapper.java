package com.lbb.bigdata.hadoop.framework;

public class WordCountMapper implements MyMapper {

    /**
     * 单词的统计mapper
     * @param line
     * @param context
     */
    public void map(String line, MyContext context) {

        String[] words = line.split("\t");
        for (String word : words){
            Object value = context.get(word);
            if(value == null){
                //表示没有出现该单词 需要首次记录为1
                context.write(word, 1);
            }else{
                //出现过的数据+1
                int v = Integer.parseInt(value.toString());
                context.write(word, v+1);
            }
        }
    }
}
