package cn.hjgx.controller.manage;

import cn.hjgx.Utils.MD5Util;
import cn.hjgx.Utils.NumberUtil;
import cn.hjgx.Utils.ParamUtil;
import cn.hjgx.entity.Brand;
import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.page.Pager;
import cn.hjgx.service.IBrandService;
import cn.hjgx.service.IUserBusinessService;
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
public class UserBusinessController {

    @Autowired
    private IUserBusinessService iUserBusinessService;

    /**
     * 生成密码
     * @return
     */
    @GetMapping("/userbusiness/generatePassword")
    @ResponseBody
    public String generate_password() {
        return NumberUtil.getStringRandom(8);
    }

    /**
     * 重置密码
     * @return
     */
    @GetMapping("/userbusiness/resetPassword")
    @ResponseBody
    public String reset_password(int id) {


        String newpassword = NumberUtil.getStringRandom(8);

        UserBusiness userBusiness = iUserBusinessService.selectByPrimaryKey(id);
        String newDBPassword = MD5Util.MD5Encode(userBusiness.getUsername() + newpassword,"UTF-8");

        iUserBusinessService.updatePasswordByPrimary(id,newDBPassword);
        return newpassword;
    }


    /**
     * 新增/保存
     * @param m
     * @param userBusiness
     * @return
     */
    @PostMapping("/userbusiness/save-or-update")
    public String bg_userbusiness_save_or_update(Model m,UserBusiness userBusiness) {

        try {
            userBusiness.setPassword(MD5Util.MD5Encode(userBusiness.getUsername() + userBusiness.getPassword(),"UTF-8"));
            iUserBusinessService.insertSelective(userBusiness);
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 记录日志，500页面
        }

        return "redirect:/backstage/userbusiness/list.html";
    }

    @GetMapping("/userbusiness/list.html")
    public String to_bg_userbusiness_list(Model m, UserBusiness userBusiness) {

        try {

            Pager<UserBusiness> pager = iUserBusinessService.getUserBusinessPaged(userBusiness);

            m.addAttribute("pager", pager);

            String pathParam = ParamUtil.parseBeanToPathParam(userBusiness);
            m.addAttribute("page_title", "企业用户管理");//标题
            m.addAttribute("current_menu", "user_business_list");//当前菜单高亮
            m.addAttribute("curUrl", "/backstage/userbusiness/list.html");//分页片段url
            m.addAttribute("userBusiness", userBusiness);//查询参数保存
            m.addAttribute("pathParam", pathParam);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "manage/system_manage/user_business_list";
    }

    /**
     * 列表
     * @param m
     * @param id
     * @return
     */
    @GetMapping("/userbusiness/delete")
    @ResponseBody
    public String bg_userbusiness_delete(Model m,int id) {

        int count = iUserBusinessService.deleteByPrimaryKey(id);
        return String.valueOf(count);
    }
}
