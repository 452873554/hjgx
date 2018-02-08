package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationSpu;

import java.util.List;

public interface WholeDecorationSpuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationSpu record);

    int insertSelective(WholeDecorationSpu record);

    WholeDecorationSpu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecorationSpu record);

    int updateByPrimaryKey(WholeDecorationSpu record);

    List<WholeDecorationSpu> selectByItemId(int id);

}