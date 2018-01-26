package cn.hjgx.controller.manage;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.IProductSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/backstage")
public class ProductSpaceController {

    @Autowired
    private IProductSpaceService iProductSpaceService;

    /**
     *
     * @param m
     * @param productSpace
     * @return
     */
    @PostMapping("/space/save-or-update")
    public String bg_productspace_save_or_update(Model m,ProductSpace productSpace) {

        try {
            //管理员不指定排序时，自动选择一个最大的排序值
            if(ObjectUtils.isEmpty(productSpace.getIdentifyOrder())){
                productSpace.setIdentifyOrder(iProductSpaceService.getMaxIdentifyOrder());
            }
            iProductSpaceService.insertSelective(productSpace);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 记录日志，500页面
        }

        return "redirect:/backstage/space/list.html";
    }

    @GetMapping("/space/list.html*")
    public String to_bg_productspace_list(Model m, ProductSpace productSpace) {

        try {

            Pager<ProductSpace> pager = iProductSpaceService.getProductSpacePaged(productSpace);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(productSpace);
            m.addAttribute("page_title", "空间管理");//标题
            m.addAttribute("current_menu", "space_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/space/list.html");//分页片段url
            m.addAttribute("productSpace", productSpace);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }

        return "manage/whole_decoration/space_list";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/space/delete")
    @ResponseBody
    public String bg_space_delete(Model m,int id) {

        int count = iProductSpaceService.deleteByPrimaryKey(id);

        return String.valueOf(count);
    }
}
