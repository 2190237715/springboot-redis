package com.example.demo.dao;

import com.example.demo.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author fuqiangxin
 * @Classname: CityDao
 * @Description: 数据访问层
 * @date 2020/5/29 11:25
 */
@Mapper
@Repository
public interface CityDao {

    void saveCity(City city);

    void deleteCity(Long id);

    void updateCity(City city);

    City findCityById(Long id);

    List<City> findAllCity();
}
