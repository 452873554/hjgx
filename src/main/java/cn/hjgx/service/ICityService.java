package cn.hjgx.service;

import cn.hjgx.entity.City;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
public interface ICityService {

    int insertSelective(City record);

    List<City> selectByProvenceId(int id);

}
