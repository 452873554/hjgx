package cn.hjgx.mapper;

import cn.hjgx.entity.Category;

import java.util.List;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int deleteCategoryCascadeById(int id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category getRootCategory();

    int getMaxIdentifyOrder(int parentId);

    List<Category> getSubCategorys(int id);

    List<Category> getAllCategory();
}