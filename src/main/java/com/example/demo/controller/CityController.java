package com.example.demo.controller;

import com.example.demo.pojo.City;
import com.example.demo.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author fuqiangxin
 * @Classname: CityController
 * @Description: 核心控制层
 * @date 2020/5/29 15:05
 */
@RestController
public class CityController {

    @Autowired
    private ICityService cityService;

    @PostMapping(value = "/api/test")
    public String test(@RequestBody City city) {
        System.out.println(city.toString());
        System.out.println("进来了 ");
        return "SUCCESS";
    }

    @PostMapping(value = "/api/saveCity")
    public City saveCity(@RequestBody City city) {
        City cityVo = cityService.saveCity(city);
        return cityVo;
    }

    @DeleteMapping(value = "/api/deleteCity/{id}")
    public String deleteCity(@PathVariable("id") long id) {
        cityService.deleteCity(id);
        return "SUCCESS";
    }

    @PutMapping(value = "/api/updateCity")
    public String updateCity(@RequestBody City city) {
        cityService.updateCity(city);
        return "SUCCESS";
    }

    @GetMapping(value = "/api/findCityById/{id}")
    public City findCityById(@PathVariable("id") long id) {
        City cityVo = cityService.findCityById(id);
        return cityVo;
    }

    @GetMapping(value = "/api/findAllCity")
    public List<City> findAllCity() {
        List<City> cityList = cityService.findAllCity();
        return cityList;
    }
}
