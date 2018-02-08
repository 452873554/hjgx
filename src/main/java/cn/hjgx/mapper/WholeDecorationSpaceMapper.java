package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationSpace;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;

import java.util.List;

public interface WholeDecorationSpaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationSpace record);

    int insertSelective(WholeDecorationSpace record);

    WholeDecorationSpace selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecorationSpace record);

    int updateByPrimaryKey(WholeDecorationSpace record);

    List<WholeDecorationSpaceDto> selectByWholeDecorationId(int id);

}