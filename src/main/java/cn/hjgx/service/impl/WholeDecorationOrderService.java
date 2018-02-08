package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.mapper.WholeDecorationOrderMapper;
import cn.hjgx.service.IWholeDecorationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by alvin on 2018/2/6.
 */
@Service
public class WholeDecorationOrderService implements IWholeDecorationOrderService {

    @Autowired
    private WholeDecorationOrderMapper wholeDecorationOrderMapper;

    @Override
    public int insertSelective(WholeDecorationOrder record) {
        return wholeDecorationOrderMapper.insertSelective(record);
    }
}
