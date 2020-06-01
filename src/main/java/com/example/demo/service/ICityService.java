package com.example.demo.service;

import com.example.demo.pojo.City;

import java.util.List;

/**
 * @author fuqiangxin
 * @Classname: ICityService
 * @Description: 业务接口层
 * @date 2020/5/29 14:02
 */
public interface ICityService {

    City saveCity(City city);

    void deleteCity(Long id);

    void updateCity(City city);

    City findCityById(Long id);

    List<City> findAllCity();
}
