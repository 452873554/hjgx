package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationOrderDetail;
import cn.hjgx.mapper.WholeDecorationOrderDetailMapper;
import cn.hjgx.service.IWholeDecorationOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/2/6.
 */
@Service
public class WholeDecorationOrderDetailService implements IWholeDecorationOrderDetailService {

    @Autowired
    private WholeDecorationOrderDetailMapper wholeDecorationOrderDetailMapper;

    @Override
    public int batchInsert(List<WholeDecorationOrderDetail> records) {
        return wholeDecorationOrderDetailMapper.batchInsert(records);
    }
}
