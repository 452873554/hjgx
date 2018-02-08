package cn.hjgx.service;

import cn.hjgx.entity.WholeDecorationSpace;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
public interface IWholeDecorationSpaceService {

    int insertSelective(WholeDecorationSpace record);

    List<WholeDecorationSpaceDto> selectByWholeDecorationId(int id);

}
