package cn.hjgx.service;

import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;

/**
 * Created by alvin on 2018/1/30.
 */
public interface IWholeDecorationService {

    int insertSelective(WholeDecoration record);

    Pager<WholeDecorationResultDto> getWholeDecorationPaged(WholeDecorationResultDto wholeDecorationResultDto);


}
