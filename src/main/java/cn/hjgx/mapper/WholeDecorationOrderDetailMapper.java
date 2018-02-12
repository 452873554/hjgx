package cn.hjgx.mapper;

import cn.hjgx.entity.WholeDecorationOrderDetail;
import cn.hjgx.entity.pagedto.WholeDecorationOrderDetailDto;

import java.util.List;

public interface WholeDecorationOrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WholeDecorationOrderDetail record);

    int insertSelective(WholeDecorationOrderDetail record);

    WholeDecorationOrderDetail selectByPrimaryKey(Integer id);

    List<WholeDecorationOrderDetailDto> selectByOrderId(int id);

    int updateByPrimaryKeySelective(WholeDecorationOrderDetail record);

    int updateByPrimaryKey(WholeDecorationOrderDetail record);

    int batchInsert(List<WholeDecorationOrderDetail> records);
}