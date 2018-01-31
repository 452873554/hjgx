package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationItem;

public interface WholeDecorationItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationItem record);

    int insertSelective(WholeDecorationItem record);

    WholeDecorationItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecorationItem record);

    int updateByPrimaryKey(WholeDecorationItem record);
}