package cn.hjgx.service;

import cn.hjgx.entity.WholeDecorationOrderDetail;

import java.util.List;

/**
 * Created by alvin on 2018/2/6.
 */
public interface IWholeDecorationOrderDetailService {

    int batchInsert(List<WholeDecorationOrderDetail> records);

}
