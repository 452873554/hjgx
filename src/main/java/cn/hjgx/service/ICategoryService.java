package cn.hjgx.service;

import cn.hjgx.entity.Category;

import java.util.List;

/**
 * Created by alvin on 2018/1/16.
 */
public interface ICategoryService {

    int insertSelective(Category record);

    int deleteCategoryCascadeById(int id);

    Category selectByPrimaryKey(Integer id);

    Category getRootCategory();

    int getMaxIdentifyOrder(int parentId);

    List<Category> getSubCategorys(int id);

    List<Category> getAllCategory();

}
