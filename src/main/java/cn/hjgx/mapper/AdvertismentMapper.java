package cn.hjgx.mapper;

import cn.hjgx.entity.pagedto.AdPageParam;
import cn.hjgx.entity.Advertisment;

import java.util.List;

public interface AdvertismentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Advertisment record);

    int insertSelective(Advertisment record);

    Advertisment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Advertisment record);

    int updateByPrimaryKey(Advertisment record);

    List<Advertisment> selectByPageParam(AdPageParam app);

}