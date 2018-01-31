package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationSpace;

public interface WholeDecorationSpaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationSpace record);

    int insertSelective(WholeDecorationSpace record);

    WholeDecorationSpace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecorationSpace record);

    int updateByPrimaryKey(WholeDecorationSpace record);
}