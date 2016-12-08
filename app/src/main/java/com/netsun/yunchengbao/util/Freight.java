package com.netsun.yunchengbao.util;

/**
 * Created by Administrator on 2016/12/7.
 */

public class Freight {
    public static final String KEY_ID ="id";
    public static final String KEY_ORIGIN="origin";
    public static final String KEY_DEST="dest";
    public static final String KEY_GOODS="goods";
    public static final String KEY_WEIGHT="weight";
    public static final String KEY_TIME="time";

    private int id;//派车单号
    private String origin;//出发地
    private String destination; //目的地
    private String goods; //货物
    private float weight; //重量
    private String time;//时间

    public int getId() {
        return id;
    }

    public String getDestination() {
        return destination;
    }

    public float getWeight() {
        return weight;
    }

    public String getGoods() {
        return goods;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
