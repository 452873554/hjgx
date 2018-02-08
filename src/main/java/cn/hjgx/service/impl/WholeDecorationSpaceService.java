package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationSpace;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.mapper.WholeDecorationItemMapper;
import cn.hjgx.mapper.WholeDecorationSpaceMapper;
import cn.hjgx.mapper.WholeDecorationSpuMapper;
import cn.hjgx.service.IWholeDecorationSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
@Service
public class WholeDecorationSpaceService implements IWholeDecorationSpaceService {

    @Autowired
    private WholeDecorationSpaceMapper wholeDecorationSpaceMapper;

    @Autowired
    private WholeDecorationItemMapper wholeDecorationItemMapper;

    @Autowired
    private WholeDecorationSpuMapper wholeDecorationSpuMapper;

    @Override
    public int insertSelective(WholeDecorationSpace record) {
        return wholeDecorationSpaceMapper.insertSelective(record);
    }

    @Override
    public List<WholeDecorationSpaceDto> selectByWholeDecorationId(int id) {

        List<WholeDecorationSpaceDto> wholeDecorationSpaceDtos = wholeDecorationSpaceMapper.selectByWholeDecorationId(id);

        wholeDecorationSpaceDtos.forEach(space -> {
            //设置每个空间所包含的项目
            space.setWholeDecorationItemDtos(wholeDecorationItemMapper.selectBySpaceId(space.getId()));
            //设置每个项目所关联的SKU
            space.getWholeDecorationItemDtos().forEach(item -> {
                item.setWholeDecorationSpus(wholeDecorationSpuMapper.selectByItemId(item.getId()));
            });
        });

        return wholeDecorationSpaceDtos;

    }
}
