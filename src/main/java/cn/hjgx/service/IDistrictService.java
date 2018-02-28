package cn.hjgx.service;

import cn.hjgx.entity.District;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
public interface IDistrictService {

    int insertSelective(District record);

    List<District> selectByCityId(int id);

    void truncateData();

}
