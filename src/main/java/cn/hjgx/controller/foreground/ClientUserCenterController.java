package cn.hjgx.controller.foreground;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.service.IWholeDecorationOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usercenter")
public class ClientUserCenterController {

    @Autowired
    private IWholeDecorationOrderService iWholeDecorationOrderService;

    @GetMapping("/dashboard.html")
    public String to_usercenter_dashboard(Model m) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "foreground/usercenter/dashboard";
    }

    @GetMapping("/decoration-order/list.html*")
    @Login
    public String to_bg_whole_decoration_order_list(Model m, WholeDecorationOrder wholeDecorationOrder) {

        try {

            Pager<WholeDecorationOrder> pager = iWholeDecorationOrderService.getWholeDecorationOrderPaged(wholeDecorationOrder);
            m.addAttribute("pager", pager);
            String pathParam = ParamUtil.parseBeanToPathParam(wholeDecorationOrder);
            m.addAttribute("page_title", "整装订单管理");//标题
            m.addAttribute("current_menu", "whole_decoration_order_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/whole-decoration-order/list.html");//分页片段url
            m.addAttribute("wholeDecorationOrder", wholeDecorationOrder);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "foreground/usercenter/whole_decoration_order";
    }

}
