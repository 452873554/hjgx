package cn.hjgx.service.impl;

import cn.hjgx.entity.District;
import cn.hjgx.mapper.DistrictMapper;
import cn.hjgx.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/2/12.
 */
@Service
public class DistrictService implements IDistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public int insertSelective(District record) {
        return districtMapper.insertSelective(record);
    }

    @Override
    public List<District> selectByCityId(int id) {
        return districtMapper.selectByCityId(id);
    }
}
