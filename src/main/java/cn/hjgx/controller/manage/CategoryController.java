package cn.hjgx.controller.manage;

import cn.hjgx.entity.Category;
import cn.hjgx.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/backstage")
public class CategoryController {

    @Autowired
    private ICategoryService iCategoryService;

    /**
     * 级联移除分类
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/category/delete")
    public String bg_category_delete(Model m,int id) {

        try {
            iCategoryService.deleteCategoryCascadeById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/backstage/category/list.html";
    }

    /**
     * 新增商品分类
     * @param m
     * @param category
     * @return
     */
    @PostMapping("/category/save-or-update")
    public String bg_category_save_or_update(Model m,Category category) {

        try {
            //管理员不指定排序时，自动选择一个同级别中最大的排序值
            if(ObjectUtils.isEmpty(category.getIdentifyOrder())){
                category.setIdentifyOrder(iCategoryService.getMaxIdentifyOrder(category.getParentCategoryId()));
            }
            iCategoryService.insertSelective(category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/backstage/category/list.html";
    }

    /**
     * 商品分类列表
     * @param m
     * @return
     */
    @GetMapping("/category/list.html*")
    public String to_bg_category_list(Model m) {

        try {

            List<Category> nodes = iCategoryService.getAllCategory();

            m.addAttribute("page_title", "商品分类管理");//标题
            m.addAttribute("current_menu", "category_list");//当前菜单高亮
            m.addAttribute("nodes", nodes);//分页片段url

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/product/category_list";

    }
}
