package cn.hjgx.service.impl;


import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.AdPageParam;
import cn.hjgx.mapper.AdvertismentMapper;
import cn.hjgx.entity.Advertisment;
import cn.hjgx.service.IAdService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by alvin on 2017/7/22.
 */

@Service
public class AdService implements IAdService {

    @Autowired
    private AdvertismentMapper advertismentMapper;

    @Override
    public Pager<Advertisment> getAdPaged(AdPageParam app) {
        PageHelper.startPage(app.getPageOffSet(),app.getPageSize());
        List<Advertisment> datas =  advertismentMapper.selectByPageParam(app);

        Pager<Advertisment> pager = new Pager<Advertisment>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public int saveOrUpdateAd(Advertisment ad) {

        int modifiedCount = 0;
        //更新
        if(ad.getId() !=null){
            modifiedCount = advertismentMapper.updateByPrimaryKeySelective(ad);
        }else{
            //新增
            modifiedCount = advertismentMapper.insertSelective(ad);
        }

        return modifiedCount;
    }
}
