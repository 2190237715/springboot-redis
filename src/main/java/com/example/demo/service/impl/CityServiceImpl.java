package com.example.demo.service.impl;

import com.example.demo.dao.CityDao;
import com.example.demo.pojo.City;
import com.example.demo.service.ICityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author fuqiangxin
 * @Classname: CityService
 * @Description:
 * @date 2020/5/29 14:03
 */
@Service
public class CityServiceImpl implements ICityService {

    private static final Logger logger = LoggerFactory.getLogger(CityServiceImpl.class);

    @Autowired
    private CityDao cityDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public City saveCity(City city) {
        String key = "city_" + city.getId();
        cityDao.saveCity(city);
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        //插入缓存
        operations.set(key, city, 100, TimeUnit.SECONDS);
        logger.info("CityServiceImpl.saveCiry:从数据库获取了城市 >> " + city.toString());
        return city;
    }

    @Override
    public void deleteCity(Long id) {
        cityDao.deleteCity(id);
        String key = "city_" + id;
        //存在就删除
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.deleteCity:从缓存中删除了城市ID >>" + id);
        }
    }

    @Override
    public void updateCity(City city) {
        cityDao.updateCity(city);
        String key = "city_" + city.getId();
        //存在就删除
        boolean haskey = redisTemplate.hasKey(key);
        if (haskey) {
            redisTemplate.delete(key);
            logger.info("CityServiceImpl.updateCity:从缓存中删除了城市 >>" + city.toString());
        }
    }

    @Override
    public City findCityById(Long id) {
        City city;
        String key = "city_" + id;
        ValueOperations<String, City> operations = redisTemplate.opsForValue();
        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            city = operations.get(key);
            logger.info("CityServiceImpl.findCityById:从缓存中获取了城市 >> " + city.toString());
            return city;
        }
        //从数据库获取数据
        city = cityDao.findCityById(id);
        //插入缓存
        operations.set(key, city, 100, TimeUnit.SECONDS);
        logger.info("CityServiceImpl.findCityById:从数据库获取了城市 >> " + city.toString());
        return city;
    }

    @Override
    public List<City> findAllCity() {
        List<City> cityList = null;
        String key = "citylist";
        ValueOperations<String, List<City>> operations = redisTemplate.opsForValue();
        //缓存存在
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            cityList = operations.get(key);
            logger.info("CityServiceImpl.findAllCity:从缓存中获取了城市列表 >> " + cityList.get(0));
            return cityList;
        }
        //从数据库获取数据
        cityList = cityDao.findAllCity();
        Map valueMap = new HashMap();
        valueMap.put(key, cityList);
        //插入缓存
        operations.multiSet(valueMap);
        operations.set(key, cityList, 100, TimeUnit.SECONDS);
        logger.info("CityServiceImpl.findAllCity:从数据库获取了城市列表 >> " +  cityList.get(0));
        return cityList;
    }

}
