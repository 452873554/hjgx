package cn.hjgx.controller.manage;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/backstage")
public class SupplierController {

    @Autowired
    private ISupplierService iSupplierService;

    /**
     * 新增/保存
     * @param m
     * @param supplier
     * @return
     */
    @PostMapping("/supplier/save-or-update")
    public String bg_supplier_save_or_update(Model m,Supplier supplier) {

        try {
            iSupplierService.insertSelective(supplier);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 记录日志，500页面
        }

        return "redirect:/backstage/supplier/list.html";
    }

    /**
     * 列表
     * @param m
     * @param supplier
     * @return
     */
    @GetMapping("/supplier/list.html*")
    public String to_bg_supplier_list(Model m, Supplier supplier) {

        try {

            //模糊查找
            supplier.setLinkman(supplier.getName());
            supplier.setContactWay(supplier.getName());
            supplier.setCompanyName(supplier.getName());
            supplier.setCompanyAddress(supplier.getName());

            Pager<Supplier> pager = iSupplierService.getSupplierPaged(supplier);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(supplier);
            m.addAttribute("page_title", "供应商管理");//标题
            m.addAttribute("current_menu", "supplier_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/supplier/list.html");//分页片段url
            m.addAttribute("supplier", supplier);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "manage/product/supplier_list";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/supplier/delete")
    @ResponseBody
    public String bg_supplier_delete(Model m,int id) {

        int count = iSupplierService.deleteByPrimaryKey(id);

        return String.valueOf(count);
    }

}
