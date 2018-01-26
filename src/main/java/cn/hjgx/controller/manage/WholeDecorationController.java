package cn.hjgx.controller.manage;

import cn.hjgx.entity.ProductSpace;
import cn.hjgx.entity.ProductStyle;
import cn.hjgx.service.IProductSpaceService;
import cn.hjgx.service.IProductStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/backstage")
public class WholeDecorationController {

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IProductSpaceService iProductSpaceService;

    @GetMapping("/whole-decoration/save.html")
    public String to_bg_whole_decoration_save_or_update(Model m) {

        List<ProductStyle> styles = iProductStyleService.getAllStyle();
        List<ProductSpace> spaces = iProductSpaceService.getAllProductSpaces();

        m.addAttribute("styles", styles);
        m.addAttribute("spaces", spaces);

        m.addAttribute("page_title", "整装编辑");//标题
        m.addAttribute("current_menu", "whole_decoration_list");//当前菜单高亮
        return "manage/whole_decoration/whole_decoration_save_or_update";

    }

    @GetMapping("/whole-decoration/list.html")
    public String to_bg_whole_decoration_list(Model m) {

        m.addAttribute("page_title", "整装编辑");//标题
        m.addAttribute("current_menu", "whole_decoration_list");//当前菜单高亮
        return "manage/whole_decoration/whole_decoration_list";

    }
}
