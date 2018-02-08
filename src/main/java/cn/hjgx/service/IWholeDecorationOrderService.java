package cn.hjgx.service;

import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.entity.page.Pager;

/**
 * Created by alvin on 2018/2/6.
 */
public interface IWholeDecorationOrderService {

    int insertSelective(WholeDecorationOrder record);

    public Pager<WholeDecorationOrder> getWholeDecorationOrderPaged(WholeDecorationOrder brand);

}
