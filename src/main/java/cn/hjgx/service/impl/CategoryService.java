package cn.hjgx.service.impl;

import cn.hjgx.entity.Category;
import cn.hjgx.mapper.CategoryMapper;
import cn.hjgx.service.ICategoryService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

/**
 * Created by alvin on 2018/1/16.
 */
@Service
public class CategoryService implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public int insertSelective(Category record) {
        return categoryMapper.insertSelective(record);
    }

    @Override
    public int deleteCategoryCascadeById(int id) {
        return categoryMapper.deleteCategoryCascadeById(id);
    }

    @Override
    public Category selectByPrimaryKey(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Category getRootCategory() {
        return categoryMapper.getRootCategory();
    }

    @Override
    public int getMaxIdentifyOrder(int parentId) {
        return categoryMapper.getMaxIdentifyOrder(parentId);
    }

    @Override
    public List<Category> getSubCategorys(int id) {
        return categoryMapper.getSubCategorys(id);
    }

    @Override
    public List<Category> getAllCategory() {

        List<Category> categoryFromDb = categoryMapper.getAllCategory();
        List<Category> categoryForView = Lists.newArrayList();

        //找出根节点
        Optional<Category> rootNode = categoryFromDb.stream().filter(c -> ObjectUtils.isEmpty(c.getParentCategoryId())).findFirst();
        Category root = null;
        if (rootNode.isPresent()) {
            categoryForView.add(rootNode.get());
            getSubCategorysecursive(rootNode.get().getId(),categoryForView);
        }

        return categoryForView;
    }

    /**
     * 递归查询所有子目录
     * @param id 父类目ID
     * @param container
     * @return
     */
    private List<Category> getSubCategorysecursive(int id,List<Category> container) {
        List<Category> categoryFromDb = categoryMapper.getSubCategorys(id);

        categoryFromDb.forEach(category -> {
            container.add(category);
            getSubCategorysecursive(category.getId(),container);
        });

        return container;
    }

}
