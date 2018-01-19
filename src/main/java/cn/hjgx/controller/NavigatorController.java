package cn.hjgx.controller;

import cn.hjgx.entity.page.Pager;
import cn.hjgx.entity.pagedto.AdPageParam;
import cn.hjgx.service.ICommonService;
import cn.hjgx.entity.Advertisment;
import cn.hjgx.service.IAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台导航控制器
 */
@Controller
@RequestMapping("/")
public class NavigatorController {

    @Autowired
    private ICommonService iCommonService;

    @Autowired
    private IAdService iAdService;

    /**
     * 首页
     * @param m
     * @return
     */
    @GetMapping("/")
    public String index(Model m) {

//        AdPageParam adPageParam = new AdPageParam();
//        Pager<Advertisment> pager = iAdService.getAdPaged(adPageParam);
//
//        m.addAttribute("pager", pager);

        return "index";
    }

    /**
     * 系统管理员登录页面
     * @param m
     * @return
     */
    @GetMapping("/manage/login")
    public String manageLogin(Model m) {
        return "manage/login";
    }

    /**
     * 导航到一站式服务界面
     * @return
     */
    @GetMapping("/onestation")
    public String to_one_station(Model m) {
        return "one_station";
    }

    /**
     * 导航到关于我们界面
     * @return
     */
    @GetMapping("/about")
    public String to_about(Model m) {
        return "about";
    }

}
