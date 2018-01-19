package cn.hjgx.service;

import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.AdPageParam;
import cn.hjgx.entity.Advertisment;


public interface IAdService {

    /**
     * 列表查询广告图数据
     * @param app
     * @return
     */
    public Pager<Advertisment> getAdPaged(AdPageParam app);

    /**
     * 保存或更新广告图信息
     * @param ad
     * @return
     */
    public int  saveOrUpdateAd(Advertisment ad);

}
