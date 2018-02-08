package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationOrder;

public interface WholeDecorationOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationOrder record);

    int insertSelective(WholeDecorationOrder record);

    WholeDecorationOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecorationOrder record);

    int updateByPrimaryKey(WholeDecorationOrder record);
}