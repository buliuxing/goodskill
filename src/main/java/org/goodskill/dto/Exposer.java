package org.goodskill.dto;

/**
 * @author ：yangzengrui
 * @date ：Created in 17/3/2019 11:26 AM
 * @description：暴露秒杀地址
 */

public class Exposer {

    //是否开启秒杀
    private boolean exposed;

    //加密措施
    private String md5;

    //id
    private long goodId;

    //系统当前时间
    private long now;

    //开始时间
    private long start;

    //结束时间
    private long end;


    public Exposer(boolean exposed, String md5, long goodId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.goodId = goodId;
    }

    public Exposer(boolean exposed, long goodId, long now, long start, long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
        this.goodId = goodId;
    }

    public Exposer(boolean exposed, long goodId) {
        this.exposed = exposed;
        this.goodId = goodId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getGoodId() {
        return goodId;
    }

    public void setGoodId(long goodId) {
        this.goodId = goodId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", goodId=" + goodId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
