package cn.hjgx.mapper;

import cn.hjgx.entity.Site;
import cn.hjgx.entity.pagedto.SitePageParam;

import java.util.List;

public interface SiteMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Site record);

    int insertSelective(Site record);

    Site selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Site record);

    int updateByPrimaryKey(Site record);

    List<Site> selectByPageParam(SitePageParam spp);

    Integer selectByPageParamCount(SitePageParam spp);

}