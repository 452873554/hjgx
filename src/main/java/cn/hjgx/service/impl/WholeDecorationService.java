package cn.hjgx.service.impl;

import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.mapper.WholeDecorationMapper;
import cn.hjgx.service.IWholeDecorationService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alvin on 2018/1/30.
 */
@Service
public class WholeDecorationService implements IWholeDecorationService
{
    @Autowired
    private WholeDecorationMapper wholeDecorationMapper;

    @Override
    public int insertSelective(WholeDecoration record) {
        return wholeDecorationMapper.insertSelective(record);
    }

    @Override
    public WholeDecoration selectByPrimaryKey(Integer id) {
        return wholeDecorationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pager<WholeDecorationResultDto> getWholeDecorationPaged(WholeDecorationResultDto wholeDecoration) {

        PageHelper.startPage(wholeDecoration.getPageOffSet(), wholeDecoration.getPageSize());
        PageHelper.orderBy("wd.create_time desc");

        List<WholeDecorationResultDto> datas = wholeDecorationMapper.selectByPageParam(wholeDecoration);

        Pager<WholeDecorationResultDto> pager = new Pager<>();
        pager.setDatas(datas);

        return pager;
    }
}
