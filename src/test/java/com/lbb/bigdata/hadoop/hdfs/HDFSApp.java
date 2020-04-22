package com.lbb.bigdata.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;

public class HDFSApp {

    public static final String HDFS_PATH = "hdfs://192.168.1.100:8020";

    FileSystem fileSystem = null;

    Configuration configuration = null;

    @Before
    public void setUp() throws Exception{
        configuration = new Configuration();
        configuration.set("dfs.replication","1");
        fileSystem = FileSystem.get(new URI(HDFS_PATH),configuration, "root");
    }

    /**
     * 创建文件夹
     * @throws Exception
     */
    @Test
    public void mkdir() throws Exception{
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * 查看文件
     * @throws Exception
     */
    @Test
    public void text() throws Exception{
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/test/hello.txt"));
        IOUtils.copyBytes(in, System.out,1024);
    }

    /**
     * 打印文件数据
     * @throws Exception
     */
    @Test
    public void print() throws Exception{
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/output/wc.out"));
        IOUtils.copyBytes(in, System.out,1024);
    }

    /**
     * 创建文件
     * @throws Exception
     */
    @Test
    public void create() throws Exception{
        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/test/b.txt"));
        out.writeUTF("hello world b");
        out.flush();
        out.close();
    }

    /**
     * 改名
     * @throws Exception
     */
    @Test
    public void rename() throws Exception{
        Path oldPath = new Path("/hdfsapi/test/b.txt");
        Path newPath = new Path("/hdfsapi/test/c.txt");
        boolean result = fileSystem.rename(oldPath, newPath);
        System.out.println(result);
    }

    /**
     * 拷贝本地文件
     * @throws Exception
     */
    @Test
    public void copyFromLocalFile() throws Exception{
        Path src = new Path("/Users/xinwis/Downloads/hello.txt");
        Path dst = new Path("/hdfsapi/test/");
        fileSystem.copyFromLocalFile(src, dst);
    }

    /**
     * 拷贝hdfs文件到本地
     * @throws Exception
     */
    @Test
    public void copyToLocalFile() throws Exception{
        Path src = new Path("/hdfsapi/test/hello.txt");
        Path dst = new Path("/Users/xinwis/Downloads");
        fileSystem.copyToLocalFile(src, dst);
    }

    /**
     * 列出文件夹下的所有文件
     * @throws Exception
     */
    @Test
    public void listFiles() throws Exception{
        //递归查询某个文件夹下的文件 fileSystem.listFiles(new Path("/hdfsapi/test"), true);
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/hdfsapi/test"));
        for (FileStatus fileStatus : fileStatuses){
            String isDir = fileStatus.isDirectory() ? "文件夹":"文件";
            String permission = fileStatus.getPermission().toString();
            short replication = fileStatus.getReplication();
            long length = fileStatus.getLen();
            String path = fileStatus.getPath().toString();
            System.out.println("isDir:"+isDir+" permission:"+permission+" replication:"+replication+" length:"+length+" path:"+path);
        }
    }

    /**
     * 查看文件块信息
     * @throws Exception
     */
    @Test
    public void getFileBlockLocations() throws  Exception{
        FileStatus fileStatus = fileSystem.getFileLinkStatus(new Path("/hdfs-test/jdk-8u144-linux-x64.tar.gz"));
        BlockLocation[] blockLocations = fileSystem.getFileBlockLocations(fileStatus, 0 ,fileStatus.getLen());
        for (BlockLocation blockLocation : blockLocations){
            for(String name: blockLocation.getNames()){
                System.out.println(name+":"+blockLocation.getOffset()+":"+blockLocation.getLength());
            }
        }
    }

    /**
     * 删除文件信息
     * @throws Exception
     */
    @Test
    public void delete() throws Exception{
        boolean result = fileSystem.delete(new Path("/hdfs-test/jdk-8u144-linux-x64.tar.gz"),true);
        System.out.println(result);
    }

    /**
     * 获取副本数量
     * @throws Exception
     */
    @Test
    public void textReplication() throws Exception{
        System.out.println(configuration.get("dfs.replication"));
    }

    @After
    public void tearDown(){
        configuration = null;
        fileSystem = null;
    }
}
