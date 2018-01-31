package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationSpu;
import cn.hjgx.mapper.WholeDecorationSpuMapper;
import cn.hjgx.service.IWholeDecorationSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alvin on 2018/1/30.
 */
@Service
public class WholeDecorationSpuService implements IWholeDecorationSpuService {

    @Autowired
    private WholeDecorationSpuMapper wholeDecorationSpuMapper;

    @Override
    public int insertSelective(WholeDecorationSpu record) {
        return wholeDecorationSpuMapper.insertSelective(record);
    }
}
