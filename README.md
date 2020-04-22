hadoop生态圈
spark生态圈

大数据概述                               初识hadoop
分布式文件系统HDFS              分布式资源调度YARN
分布式计算框架MapReduce    Hadoop项目实战
数据仓库Hive                            Hive项目实战
Hadoop分布式集群搭建

大数据概述
商品推荐 预言家

大数据4V特征
1、数据量Volume
2、多样性，复杂性Variety
3、基于高度分析的新价值Value
4、速度Velocity

大数据带来的技术变革
1、技术驱动：数据量大
     存储：文件存储 ==> 分布式存储
     计算：单机        ==> 分布式计算
     网络：万兆
     DB  ：RDBMS     ==> NoSQL（HBase/Redis。。。）
2、商业驱动

大数据现存的模式
手握大数据，没有大数据思维 
没有大数据、有大数据思维
既有大数据、又有大数据思维

大数据技术概述
单机：cpu memory disk
分布式并行计算/处理

数据采集 flume(日志采集局和传输系统) sqoop（关系型数据库<->hdfs）
数据存储 hadoop（hdfs）
数据处理/分析/挖掘 hadoop、spark、flink、hive。。。
可视化 

船的选择
廉价
中高价值

运输过程拆开
货物搬到船上：数据采集 数据存储
处理：小于多少的石头扔了 精细化的筛选

大数据在技术架构上带来的挑战
对现有数据库管理技术的挑战
经典数据库技术并没有考虑数据的多类别
实时性的技术挑战
网络架构、数据中心、运维的挑战

数据隐私
数据源复杂多样

GFS 谷歌分布式存储系统
MapReduce 分布式计算框架
BigTable 大数据存储表

模仿Google大数据技术的开源实现Hadoop

初识Hadoop
1、hadoop概述
2、hadoop核心组件
3、hadoop优势
4、hadoop发展史
5、hadoop生态系统
6、hadoop发行版的选择

hadoop的由来
nutch、hadoop：Doug Cutting

hadoop.apache.org
hive.apache.org
hbase.apache.org
spark.apache.org
flink.apache.org
storm.apache.org

reliable, scalable, distributed computing
分布式存储+分布式计算

Hadoop：提供分布式的存储（一个文件被拆分成很多个块，并且以副本的方式存储在各个节点中）和计算
是一个分布式的系统基础架构：用户可以在不了解分布式底层细节的情况下进行使用

分布式文件系统：HDFS实现将文件分布式存储在很多的服务器上
分布式计算框架：MapReduce实现在很多及其上分布式并行计算
分布式资源调度框架：YARN实现集群资源管理以及作业的调度

分布式文件系统HDFS
将文件切分成指定大小的数据块并以多副本的存储在多个机器上
数据切分、多副本、容错等操作对用户是透明的

文件、块、副本
文件：test.log 200M
块（block）：默认的blocksize是128M，2个块 = 1个128M（blk1）+1个72M（blk2）
副本：HDFS默认3副本

5节点
node1:blk1 blk2
node2:blk2
node3:blk1 blk2
node4:
node5:blk1

分布式计算框架MapReduce
可扩展&容错性&海量数据离线处理

资源调度系统YARN
Yet Another Resourcr Negotiator
负责整个集群资源的管理和调度
扩展性&容错性&多框架资源统一调度

hapoop优势之高可靠性
数据存储：数据块多副本
数据计算：重新调度作业计算

存储/计算资源不够时，可以横向的线性扩展机器
一个集群可以包含数以千计的节点

存储在廉价机器上，降低成本 去IOE
成熟的生态圈

狭义的hadoop vs 广义的hadoop
狭义的Hadoop：是一个适合大数据分布式存储（HDFS）、分布式计算（MapReduce）和资源调度（YARN）的平台；
广义的Hadoop：是Hadoop生态系统，Hadoop生态系统是一个很庞大的概念，Hadoop是其中最重要最基础的一个部分；生态系统的每一子系统只解决某一个特定的问题域（甚至可能很窄），不搞统一型的一个全能系统，而是小而精的多个小系统；

常用的Hadoop发行版
Apache
       优点：纯开源
       缺点：不同版本/不痛框架之间的整合 jar冲突。。。吐血
CDH:https://www.cloudera.com/
优点：cm（cloudera manager）通过页面一键安装各种框架、升级
缺点：cm不开源，与社区版本有些许出入
Hortonworks：HDP
优点：原装Hadoop、纯开源、支持tez
缺点：企业级安全不开源
MapR

HDFS概述（Hadoop Distributed File System）
分布式
commodity hardware
fault-tolerant 高容错
high throughput
large data sets

HDFS是一个分布式的文件系统

普通文件系统
目录结构 C /
存放的是文件或者文件夹
对外提供服务：创建、修改、删除、查看、移动

普通文件系统 vs 分布式文件系统
单机
分布式文件系统能够横跨N个机器

HDFS前提和设计目标
Hardware Failure 硬件错误
Streaming Data Access 流式数据访问
Large Data Sets 大数据集
Moving Computation is Cheaper than Moving Data 移动计算比移动数据更廉价

HDFS架构
1）NameNode（master） and DataNodes（slave）
2）master/slave主从架构
3）NN：the file system namespace
regulates access to files by clients
4）DN：storage
5）HDFS exposes a file system namespace and allows user data to be stored in files
6）a file is split into one or more blocks 
blocksize：128M 150M拆分成两个block
7）blocks are stored in a set of DataNodes 容错
8）NameNode executes file system namespace operations：CRUD
9）determines the mapping of blocks to DataNodes
a.txt 150M blocksize=128M
a.txt 拆分成2个block 一个是block1:128M 另一个是block2:22M
10）通常情况下，一个node部署一个组件

Hadoop使用版本：http://archive.cloudera.com/cdh5/cdh/5/

上传文件：scp XXX.jar root@192.168.31.100:/home/software/
解压taz：tar -zxvf xxx.tar -C /home/hadoop/app

ls -a 查看隐藏文件
安装ssh无密码登录
ssh-keygen -t rsa 一路回车
cat id_rsa.pub >> authorized_keys
chmod 600 authorized_keys

HDFS安装
注意：路径在hadoop安装etc/hadoop下面
配置jdk /etc/hadoop/hadoop-env.sh
配置core-site.xml
etc/hadoop/core-site.xml:
<configuration>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://s001:8020</value>
    </property>
</configuration>
配置hdfs-site.xml
etc/hadoop/hdfs-site.xml:
<configuration>

<property>
        	<name>dfs.replication</name>
        	<value>1</value>
    	</property>
    	<property>
        	<name>hadoop.tmp.dir</name>
        	<value>/home/hadoop/app/tmp</value>
    	</property>
	</configuration>

修改slaves
s001

 虚拟机账号 root bing921jiayou

 启动HDFS：
1、第一次执行的时候一定要格式化文件系统，不要重复执行
bin/hdfs目录下
hdfs namenode -format
Storage directory /home/hadoop/app/tmp/dfs/name has been successfully formatted.
2、启动集群
/sbin/start-dfs.sh
验证：jps
[root@s001 sbin]# jps
1824 DataNode
1993 SecondaryNameNode
1741 NameNode
http://192.168.1.100:50070
查看防火墙 firewall-cmd --state
关闭防火墙 systemctl stop firewalld.service


3、关闭dfs：./stop-dfs.sh
4、start/stop-dfs.sh 与hadoop-daemons.sh的关系
start-dfs.sh = 
hadoop-daemons.sh start namenode
hadoop-daemons.sh start datanode
hadoop-daemons.sh start secondarynamenode
stop-dfs.sh = 
hadoop-daemons.sh stop namenode
hadoop-daemons.sh stop datanode
hadoop-daemons.sh stop secondarynamenode

HDFS命令行操作(bin目录下)
查看 hadoop fs -ls /
递归查询 hadoop fs -ls -R /
拷贝文件 hadoop fs -put /sd/sdfs.txt /
 hadoop fs -put README.txt /hdfs-test/
 hadoop fs -copyFromLocal
 hadoop fs -moveFromLocal
查看文件 hadoop fs -text /aaa.txt
 hadoop fs -cat /aa.txt
下载文件 hadoop fs -get /aa.txt
创建文件夹 hadoop fs -mkdir /hdfs-test
移动        hadoop fs -mv /aa.txt
合并 hadoop fs -getmerge
删除文件 hadoop fs -rm /xxx.txt
删除文件夹 hadoop fs -rm -r /hdfs-test

http://192.168.1.100:50070/explorer.html#/查看文件

put:1file ==> 1....n block ==>存放到不同的节点上
cat xxx >> a.gz ...... 将文件合并
get:去nn上查找这个file对应的元数据

HDFS API词频统计
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

util.properties
HADOOP.CONNECT.URL=hdfs://192.168.1.100:8020
HADOOP.CONNECT.NAME=root
HADOOP.READ.FILE=/hdfsapi/test/bb.txt
HADOOP.OUTPUT.FILE=/hdfsapi/output/wc.out8
HADOOP.REFLECT.CLASS=com.lbb.bigdata.hadoop.framework.CaseIgnoreWordCountMapper

MyMapper.java
package com.lbb.bigdata.hadoop.framework;
public interface MyMapper {

    /**
     * 数据处理
     * @param line
     * @param context
     */
    void map(String line, MyContext context);

}


WordCountMapper.java
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
MyContext.java上下文
package com.lbb.bigdata.hadoop.framework;
import java.util.HashMap;
import java.util.Map;
public class MyContext {
    private Map<Object, Object> cacheMap = new HashMap<Object, Object>();
    /**
     * 获取cacheMap
     * @return
     */
    public Map<Object, Object> getCacheMap(){
        return cacheMap;
    }

    /**
     * 往上下文写数据
     * @param key
     * @param value
     */
    public void write(Object key, Object value){
        cacheMap.put(key, value);
    }

    /**
     * 获取某个key的值
     * @param key
     * @return
     */
    public Object get(Object key){
        return cacheMap.get(key);
    }
}

ParamsUtil.java 读取配置文件
package com.lbb.bigdata.hadoop.framework;
import java.io.IOException;
import java.util.Properties;
/**
 * 读取配置文件
 */
public class ParamsUtil {
    private static Properties properties = new Properties();
    static {
        try {
            properties.load(ParamsUtil.class.getClassLoader().getResourceAsStream("util.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getProperties(){
        return properties;
    }
}

Constants.java常量类
package com.lbb.bigdata.hadoop.framework;
/**
 * 常量类
 */
public class Constants {
    public final static String HADOOP_CONNECT_URL ="HADOOP.CONNECT.URL";
    public final static String HADOOP_CONNECT_NAME ="HADOOP.CONNECT.NAME";
    public final static String HADOOP_READ_FILE ="HADOOP.READ.FILE";
    public final static String HADOOP_OUTPUT_FILE ="HADOOP.OUTPUT.FILE";
    public final static String HADOOP_REFLECT_CLASS ="HADOOP.REFLECT.CLASS";
}

hdfs的元数据管理
元数据：HDFS的目录结构以及每个文件的BLOCK信息(id，副本系数，BLOCK存放在哪个datanode上)
存放在什么地方：对应配置${hadoop.tmp.dir}/name/.......


HADOOP的元数据 CHECKPOINT原理 
1、namenode内存记录文件的文件结构信息，并每30/60分钟将元数据信息序列化到fsimage中【此时刻记录之前所有的元数据信息】
2、之后30分钟内的操作文件记录信息（删除、创建、修改）将记录到edits日志中；【此时元数据=fsimage+edits的日志】
3、当宕机或者服务器挂了，secondary namenode请求namenode停止edits文件，创建一个新的edits.new文件来记录新的操作信息。
4、secondary namenode将当前的fsimage+edits拿到，先fsimage反序列化到内存，再将edits作用到内存
4、生成一个新的fsimage文件，再替换在namenode的fsimage
5、产生的edits.new替换成旧的edits文件，同时更新fstime文件来记录检查点执行的时间

分布式计算处理框架MapReduce

MapReduce概述
源自于Google的MapReduce论文，论文发表于2004年12月
Hadoop MapReduce是Google MapReduce的克隆版
MapReduce优点：海量数据离线处理&易开发&易运行
MapReduce缺点：实时流式计算



实战MapReduce

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
            context.write(new Text(word), new IntWritable(1));
        }
    }
}

package com.lbb.bigdata.hadoop.mr.wordCount;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
import java.io.IOException;
import java.util.Iterator;
public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    /**
     * （hello,1） (word,1)
     *  (hello,1)  (welcome,1)
     * map的输出到reduce端，是按照相同的key分发到一个reduce上去执行
     *
     *  reduce1:(hello,1) (hello,1)   =>(hello,<1,1>)
     *  reduce2:(word,1)              =>(word,<1>)
     *  reduce3:(welcome,1)           =>(welcome,<1>)
     *
     *
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        Iterator<IntWritable> iterator = values.iterator();
        while (iterator.hasNext()){
            IntWritable value = iterator.next();
            count+=value.get();
        }
        context.write(key, new IntWritable(count));
    }
}

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
//设置combiner
        job.setCombinerClass(WordCountReducer.class);
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

MAPREDUCE的combiner


combiner优点：
能减少IO，提升作业的执行性能。
combiner的局限性：求平均数 除法要慎重

复杂数据结构
1、自定义实体类并实现Writable接口（write、readFields）
package com.lbb.bigdata.hadoop.mr.access;
import org.apache.hadoop.io.Writable;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
/**
 * 流量入口实体类
 */
public class Access implements Writable {
    private String telephone;
    private Long up;
    private Long down;
    private Long total;
    public void write(DataOutput out) throws IOException {
        out.writeUTF(telephone);
        out.writeLong(up);
        out.writeLong(down);
        out.writeLong(total);
    }
    public void readFields(DataInput in) throws IOException {
        this.telephone = in.readUTF();
        this.up = in.readLong();
        this.down = in.readLong();
        this.total = in.readLong();
    }
    @Override
    public String toString() {
        return telephone+" "+up+" "+down+" "+total;
    }
    public Access(){}
    public Access(String telephone, Long up, Long down) {
        this.telephone = telephone;
        this.up = up;
        this.down = down;
        this.total = up+down;
    }
    public String getTelephone() {
        return telephone;
    
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public Long getUp() {
        return up;
    }
    public void setUp(Long up) {
        this.up = up;
    }
    public Long getDown() {
        return down;
    }
    public void setDown(Long down) {
        this.down = down;
    }
    public Long getTotal() {
        return total;
    }
    public void setTotal(Long total) {
        this.total = total;
    }
}
2、自定义Map、reducer和partitioner
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

package com.lbb.bigdata.hadoop.mr.access;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;
public class AccessPartitioner extends Partitioner<Text, Access> {
    public int getPartition(Text phone, Access access, int numPartitions) {
        if(phone.toString().startsWith("13")){
            return 0;
        }else if(phone.toString().startsWith("15")){
            return 1;
        }else{
            return 2;
        }
    }
}

4、测试类
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
        //设置分区 设置不同的key按照定义的需求输出到不同文件中
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
hadoop的partitioner分区


YARN管理
yarn架构以及流程

配置文件修改
etc/hadoop/mapred-site.xml:
<configuration>
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
</configuration>
etc/hadoop/yarn-site.xml:
<configuration>
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
</configuration>
启动关闭yarn
$ sbin/start-yarn.sh sbin/stop-yarn.sh
启动成功后jps如下：
[root@s001 sbin]# jps
9296 NodeManager
8869 DataNode
9206 ResourceManager
8777 NameNode
9593 Jps
9053 SecondaryNameNode
浏览器访问yarn：http://192.168.1.100:8088/

如何运行在yarn上面的example
进入到hadoop-2.6.0-cdh5.15.1/share/hadoop/mapreduce下
执行pi：hadoop jar hadoop-mapreduce-examples-2.6.0-cdh5.15.1.jar pi 1 2
执行wordcount：hadoop jar hadoop-mapreduce-examples-2.6.0-cdh5.15.1.jar wordcount /wordcount/input/aaa.txt /wordcount/output

如何在yarn上运行本地的mr作业
步骤：
1、项目跟目录下运行 mvn clean package -DskipTests
2、创建存放文件的目录和文件
将本地的文件传送到服务器：scp access.log root@192.168.1.100:/home/hadoop/data/
hadoop fs -mkdir -p /access/input
hadoop fs -put access.log /access/input
3、将打包好的jar报传送到服务器 在根项目的target下面
scp hadoop-v1-1.0-SNAPSHOT.jar root@192.168.1.100:/home/hadoop/lib/
4、然后执行运行mr
hadoop jar hadoop-v1-1.0-SNAPSHOT.jar com.lbb.bigdata.hadoop.mr.access.AccessYARNApp /access/input/access.log /access/output/
