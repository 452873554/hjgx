package cn.hjgx.service.impl;

import cn.hjgx.entity.City;
import cn.hjgx.mapper.CityMapper;
import cn.hjgx.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
@Service
public class CityService implements ICityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public int insertSelective(City record) {
        return cityMapper.insertSelective(record);
    }

    @Override
    public List<City> selectByProvenceId(int id) {
        return cityMapper.selectByProvenceId(id);
    }
}
