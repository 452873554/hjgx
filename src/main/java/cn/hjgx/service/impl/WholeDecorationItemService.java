package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationItem;
import cn.hjgx.mapper.WholeDecorationItemMapper;
import cn.hjgx.service.IWholeDecorationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alvin on 2018/1/30.
 */
@Service
public class WholeDecorationItemService implements IWholeDecorationItemService {

    @Autowired
    private WholeDecorationItemMapper wholeDecorationItemMapper;

    @Override
    public int insertSelective(WholeDecorationItem record) {
        return wholeDecorationItemMapper.insertSelective(record);
    }
}
