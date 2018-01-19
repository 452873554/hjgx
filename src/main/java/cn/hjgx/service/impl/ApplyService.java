package cn.hjgx.service.impl;


import cn.hjgx.entity.Apply;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.ApplyPageParam;
import cn.hjgx.mapper.ApplyMapper;
import cn.hjgx.service.IApplyService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2017/7/22.
 */

@Service
public class ApplyService implements IApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    /**
     * 列表查询场地数据
     * @param app
     * @return
     */
    public Pager<Apply> getApplyPaged(ApplyPageParam app){
        PageHelper.startPage(app.getPageOffSet(),app.getPageSize());
        List<Apply> datas =  applyMapper.selectByPageParam(app);

        Pager<Apply> pager = new Pager<Apply>();
        pager.setDatas(datas);

        return pager;
    }

    @Override
    public int save(Apply apply) {
        return applyMapper.insertSelective(apply);
    }

    @Override
    public int markReaded(int id) {
        return applyMapper.markReaded(id);
    }

    @Override
    public int getReadedCount() {
        return applyMapper.getReadedCount();
    }

    @Override
    public int getUnReadedCount() {
        return applyMapper.getUnReadedCount();
    };
}
