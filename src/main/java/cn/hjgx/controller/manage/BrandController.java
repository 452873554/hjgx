package cn.hjgx.controller.manage;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.Brand;
import cn.hjgx.entity.Site;
import cn.hjgx.entity.Supplier;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.SitePageParam;
import cn.hjgx.service.IBrandService;
import cn.hjgx.service.ICommonService;
import cn.hjgx.service.IMeetingRoomService;
import cn.hjgx.service.ISiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/backstage")
public class BrandController {

    @Autowired
    private IBrandService brandService;

    /**
     * 新增/保存
     * @param m
     * @param brand
     * @return
     */
    @PostMapping("/brand/save-or-update")
    public String bg_brand_save_or_update(Model m,Brand brand) {

        try {

            //管理员不指定排序时，自动选择一个最大的排序值
            if(ObjectUtils.isEmpty(brand.getIdentifyOrder())){
                brand.setIdentifyOrder(brandService.getMaxIdentifyOrder());
            }

            brandService.insertSelective(brand);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 记录日志，500页面
        }

        return "redirect:/backstage/brand/list.html";
    }

    @GetMapping("/brand/list.html*")
    public String to_bg_supplier_list(Model m, Brand brand) {

        try {

            Pager<Brand> pager = brandService.getBrandPaged(brand);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(brand);
            m.addAttribute("page_title", "品牌管理");//标题
            m.addAttribute("current_menu", "brand_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/brand/list.html");//分页片段url
            m.addAttribute("brand", brand);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/product/brand_list";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/brand/delete")
    @ResponseBody
    public String bg_brand_delete(Model m,int id) {

        int count = brandService.deleteByPrimaryKey(id);

        return String.valueOf(count);
    }
}
