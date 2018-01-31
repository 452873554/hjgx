package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationSpace;
import cn.hjgx.mapper.WholeDecorationSpaceMapper;
import cn.hjgx.service.IWholeDecorationSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alvin on 2018/1/30.
 */
@Service
public class WholeDecorationSpaceService implements IWholeDecorationSpaceService {

    @Autowired
    private WholeDecorationSpaceMapper wholeDecorationSpaceMapper;

    @Override
    public int insertSelective(WholeDecorationSpace record) {
        return wholeDecorationSpaceMapper.insertSelective(record);
    }
}
