package cn.hjgx.service.impl;

import cn.hjgx.entity.Province;
import cn.hjgx.mapper.ProvinceMapper;
import cn.hjgx.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
@Service
public class ProvinceService implements IProvinceService {

    @Autowired
    private ProvinceMapper provinceMapper;

    @Override
    public int insertSelective(Province record) {
        return provinceMapper.insertSelective(record);
    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceMapper.getAllProvinces();
    }
}
