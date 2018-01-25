package cn.hjgx.controller;

import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.ICommonService;
import cn.hjgx.entity.Advertisment;
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


    /**
     * 首页
     * @param m
     * @return
     */
    @GetMapping("/")
    public String index(Model m) {
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


}
