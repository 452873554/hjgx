package cn.hjgx.controller.manage;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductSpu;
import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.IProductStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by alvin on 2018/1/22.
 */
@Controller
@RequestMapping("/backstage")
public class ProductStyleController {

    @Autowired
    private IProductStyleService iProductStyleService;

    @GetMapping("/style/list.html*")
    public String to_bg_product_style_list(Model m, ProductStyle productStyle) {

        try {

            Pager<ProductStyle> pager = iProductStyleService.getProductStylePaged(productStyle);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(productStyle);
            m.addAttribute("page_title", "风格管理");//标题
            m.addAttribute("current_menu", "style_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/style/list.html");//分页片段url
            m.addAttribute("productStyle", productStyle);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/whole_decoration/style_list";
    }

    @PostMapping("/style/save-or-update")
    public String bg_style_save_or_update(Model m, ProductStyle productStyle) {

        try {
            //管理员不指定排序时，自动选择一个最大的排序值
            if(ObjectUtils.isEmpty(productStyle.getIdentifyOrder())){
                productStyle.setIdentifyOrder(iProductStyleService.getMaxIdentifyOrder());
            }
            iProductStyleService.insertSelective(productStyle);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 记录日志，500页面
        }

        return "redirect:/backstage/style/list.html";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/style/delete")
    @ResponseBody
    public String bg_style_delete(Model m,int id) {

        int count = iProductStyleService.deleteByPrimaryKey(id);

        return String.valueOf(count);
    }


}
