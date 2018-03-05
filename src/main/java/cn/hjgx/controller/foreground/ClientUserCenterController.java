package cn.hjgx.controller.foreground;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.component.LoginInterceptor;
import cn.hjgx.entity.UserBusiness;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/usercenter")
public class ClientUserCenterController {

    @Autowired
    private IWholeDecorationOrderService iWholeDecorationOrderService;

    /**
     * 商家后台欢迎页
     * @param m
     * @return
     */
    @GetMapping("/dashboard.html")
    public String to_usercenter_dashboard(Model m) {

        try {

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "foreground/usercenter/dashboard";
    }

    /**
     * 商家后台订单列表
     * @param m
     * @param wholeDecorationOrder
     * @param request
     * @return
     */
    @GetMapping("/decoration-order/list.html*")
    @Login
    public String to_bg_whole_decoration_order_list(Model m,
                                                    WholeDecorationOrder wholeDecorationOrder,
                                                    HttpServletRequest request) {

        try {
            //只能查询自己的订单
            wholeDecorationOrder.setUsername(((UserBusiness)request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername());
            Pager<WholeDecorationOrder> pager = iWholeDecorationOrderService.getWholeDecorationOrderPaged(wholeDecorationOrder);
            m.addAttribute("pager", pager);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "foreground/usercenter/whole_decoration_order";
    }

}
