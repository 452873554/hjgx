package cn.hjgx.controller;

import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.ProductStyle;
import cn.hjgx.entity.WholeDecoration;
import cn.hjgx.entity.WholeDecorationSpace;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.WholeDecorationResultDto;
import cn.hjgx.entity.paramDto.WholeDecorationSpaceDto;
import cn.hjgx.service.IProductStyleService;
import cn.hjgx.service.IWholeDecorationService;
import cn.hjgx.service.IWholeDecorationSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 前台导航控制器
 */
@Controller
@RequestMapping("/")
public class NavigatorController {

    @Autowired
    private IWholeDecorationService iWholeDecorationService;

    @Autowired
    private IProductStyleService iProductStyleService;

    @Autowired
    private IWholeDecorationSpaceService iWholeDecorationSpaceService;

    /**
     * 首页
     * @param m
     * @return
     */
    @GetMapping("/")
    public String index(Model m,WholeDecorationResultDto wholeDecoration) {

        try {
            //首页固定查询6个
            wholeDecoration.setPageSize(6);
            Pager<WholeDecorationResultDto> pager = iWholeDecorationService.getWholeDecorationPaged(wholeDecoration);
            m.addAttribute("pager", pager);

        } catch (Exception e) {
            e.printStackTrace();
            //TODO 跳转至错误页面
        }
        return "index";
    }


    /**
     * 系统管理员登录页面
     * @param m
     * @return
     */
    @GetMapping("/manage/login.html")
    public String to_manage_login(Model m) {
        return "manage/login";
    }

    /**
     * 企业用户
     * @param m
     * @return
     */
    @GetMapping("/user/login.html")
    public String to_usercenter_login(Model m) {
        return "foreground/login";
    }

    /**
     * 联系我们
     * @param m
     * @return
     */
    @GetMapping("/contract.html")
    public String to_contract(Model m) {
        return "foreground/contact";
    }


}
