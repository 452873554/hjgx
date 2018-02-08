package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.WholeDecorationOrderMapper;
import cn.hjgx.service.IWholeDecorationOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Pager<WholeDecorationOrder> getWholeDecorationOrderPaged(WholeDecorationOrder wholeDecorationOrder) {

        PageHelper.startPage(wholeDecorationOrder.getPageOffSet(), wholeDecorationOrder.getPageSize());
        PageHelper.orderBy("create_time desc");
        List<WholeDecorationOrder> datas = wholeDecorationOrderMapper.selectByPageParam(wholeDecorationOrder);

        Pager<WholeDecorationOrder> pager = new Pager<WholeDecorationOrder>();
        pager.setDatas(datas);

        return pager;
    }
}
