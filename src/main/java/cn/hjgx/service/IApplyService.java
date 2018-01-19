package cn.hjgx.service;

import cn.hjgx.entity.Apply;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ApplyPageParam;

import java.util.List;

/**
 * Created by alvin on 2017/7/22.
 */
public interface IApplyService {

    /**
     * 保存一则需求
     * @param apply
     * @return
     */
    public int save(Apply apply);

    /**
     * 标记申请为已读
     * @param id
     * @return
     */
    public int markReaded(int id);

    /**
     * 列表查询场地数据
     * @param app
     * @return
     */
    public Pager<Apply> getApplyPaged(ApplyPageParam app);

    /**
     * 获取已读信息个数
     * @return
     */
    public int getReadedCount();

    /**
     * 获取未读信息个数
     * @return
     */
    public int getUnReadedCount();

}
