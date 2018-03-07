package cn.hjgx.controller.foreground;

import cn.hjgx.Annotation.Login;
import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.component.LoginInterceptor;
import cn.hjgx.entity.UserAddress;
import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.WholeDecorationOrder;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.IUserAddressService;
import cn.hjgx.service.IWholeDecorationOrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/usercenter")
public class ClientUserCenterController {

    @Autowired
    private IWholeDecorationOrderService iWholeDecorationOrderService;

    @Autowired
    private IUserAddressService iUserAddressService;

    @GetMapping("/address/default")
    @ResponseBody
    public JsonNode set_default_address(Model m, int id, HttpServletRequest request) throws IOException {

        ResultDto resultDto = new ResultDto();
        try {

            iUserAddressService.setDefault(((UserBusiness) request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername(),id);
            resultDto.setMessage("设置默认收货地址成功！");
        } catch (Exception e) {
            e.printStackTrace();
            resultDto.setFlag(0);
            resultDto.setMessage("设置默认收货地址失败！请重试或联系系统管理员");
        }
        return JsonUtil.toJson(resultDto);
    }

    /*****************/

    @PostMapping("/address/save")
    @ResponseBody
    public JsonNode save_user_address(Model m, UserAddress userAddress, HttpServletRequest request) throws IOException {

        ResultDto resultDto = new ResultDto();
        try {

            userAddress.setUserName(((UserBusiness) request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername());
            int count = iUserAddressService.insertSelective(userAddress);

            resultDto.setFlag(count);
            if (count == 1) {
                resultDto.setMessage("添加收货地址成功！");
            } else {
                resultDto.setMessage("添加收货地址失败！请重试或联系系统管理员");
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultDto.setFlag(0);
            resultDto.setMessage("添加收货地址失败！请重试或联系系统管理员");
        }
        return JsonUtil.toJson(resultDto);
    }

    /**
     * 商家后台欢迎页
     *
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
     *
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
            wholeDecorationOrder.setUsername(((UserBusiness) request.getSession().getAttribute(LoginInterceptor.LOGIN_USER)).getUsername());
            Pager<WholeDecorationOrder> pager = iWholeDecorationOrderService.getWholeDecorationOrderPaged(wholeDecorationOrder);
            m.addAttribute("pager", pager);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "foreground/usercenter/whole_decoration_order";
    }

}
