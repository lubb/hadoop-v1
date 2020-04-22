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
    }

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
