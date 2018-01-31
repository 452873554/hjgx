package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;

import java.util.List;

public interface WholeDecorationMapper {

    List<WholeDecorationResultDto> selectByPageParam(WholeDecorationResultDto wholeDecoration);

    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecoration record);

    int insertSelective(WholeDecoration record);

    WholeDecoration selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WholeDecoration record);

    int updateByPrimaryKey(WholeDecoration record);

}