package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.entity.WholeDecorationOrderDetail;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.mapper.WholeDecorationOrderDetailMapper;
import cn.hjgx.mapper.WholeDecorationOrderMapper;
import cn.hjgx.service.IWholeDecorationOrderService;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
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

    @Autowired
    private WholeDecorationOrderDetailMapper wholeDecorationOrderDetailMapper;


    @Override
    public int insertSelective(WholeDecorationOrder record) {
        return wholeDecorationOrderMapper.insertSelective(record);
    }

    @Override
    public Pager<WholeDecorationOrder> getWholeDecorationOrderPaged(WholeDecorationOrder wholeDecorationOrder) {

        PageHelper.startPage(wholeDecorationOrder.getPageOffSet(), wholeDecorationOrder.getPageSize());
        PageHelper.orderBy("create_time desc");
        List<WholeDecorationOrder> datas = wholeDecorationOrderMapper.selectByPageParam(wholeDecorationOrder);


        for (WholeDecorationOrder order : datas) {
            order.setWholeDecorationOrderDetailDtos(wholeDecorationOrderDetailMapper.selectByOrderId(order.getId()));
        }

        Pager<WholeDecorationOrder> pager = new Pager<WholeDecorationOrder>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public WholeDecorationOrder getWholeDecorationOrder(int orderId) {
        WholeDecorationOrder order = wholeDecorationOrderMapper.selectByPrimaryKey(orderId);
        order.setWholeDecorationOrderDetailDtos(wholeDecorationOrderDetailMapper.selectByOrderId(order.getId()));
        return order;
    }
}
