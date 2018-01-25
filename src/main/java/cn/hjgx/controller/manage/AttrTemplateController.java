package cn.hjgx.controller.manage;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductSpuAttrsTemplate;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.IProductSpuAttrsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/backstage")
public class AttrTemplateController {

    @Autowired
    private IProductSpuAttrsTemplateService iProductSpuAttrsTemplateService;

    /**
     * 新增/保存
     * @param m
     * @param productSpuAttrsTemplate
     * @return
     */
    @PostMapping("/attr-template/save-or-update")
    public String bg_brand_save_or_update(Model m,ProductSpuAttrsTemplate productSpuAttrsTemplate) {

        try {
            iProductSpuAttrsTemplateService.insertSelective(productSpuAttrsTemplate);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/backstage/attr-template/list.html";
    }

    @GetMapping("/attr-template/list.html*")
    public String to_bg_supplier_list(Model m,ProductSpuAttrsTemplate productSpuAttrsTemplate) {

        try {

            Pager<ProductSpuAttrsTemplate> pager = iProductSpuAttrsTemplateService.getTemplatePaged(productSpuAttrsTemplate);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(productSpuAttrsTemplate);
            m.addAttribute("page_title", "属性模板管理");//标题
            m.addAttribute("current_menu", "attr_template_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/attr-template/list.html");//分页片段url
            m.addAttribute("productSpuAttrsTemplate", productSpuAttrsTemplate);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/product/attr_template_list";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/attr-template/delete")
    @ResponseBody
    public String bg_brand_delete(Model m,int id) {
        int count = iProductSpuAttrsTemplateService.deleteByPrimaryKey(id);
        return String.valueOf(count);
    }

}
