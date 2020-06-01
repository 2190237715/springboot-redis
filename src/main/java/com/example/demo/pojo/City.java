package com.example.demo.pojo;

import java.io.Serializable;

/**
 * @author fuqiangxin
 * @Classname: City
 * @Description: 城市实体
 * @date 2020/5/29 11:21
 */
public class City implements Serializable {
    private int id;                 //城市编号
    private int provinceId;        //省份编号
    private String cityName;       //城市名称
    private String description;     //描述

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
