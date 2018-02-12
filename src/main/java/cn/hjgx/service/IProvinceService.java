package cn.hjgx.service;

import cn.hjgx.entity.Province;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
public interface IProvinceService {

    int insertSelective(Province record);

    List<Province> getAllProvinces();

}
