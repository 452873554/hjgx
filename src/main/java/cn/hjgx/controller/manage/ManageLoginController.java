package cn.hjgx.controller.manage;

import cn.hjgx.entity.UserAdministrator;
import cn.hjgx.component.AuthorityFilter;
import cn.hjgx.service.IUserAdministratorService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * 用户登陆管理
 */
@Controller
@RequestMapping("/manage")
public class ManageLoginController {

	@Autowired
	private IUserAdministratorService iUserAdministratorService;

	@PostMapping("/dologin")
	public String dologin(HttpServletRequest request, UserAdministrator user, Model m) {

		//TODO 验证码通过
		if (true) {

			UserAdministrator dbuser = iUserAdministratorService.validateUser(user);
			//用户名密码校验通过,保存会话信息，登录到管理首页
			if (dbuser != null) {
				//更新最后登录时间
				dbuser.setLastLoginTime(new Date());
				iUserAdministratorService.updateByPrimaryKeySelective(dbuser);
				request.getSession().setAttribute(AuthorityFilter.LOGIN_ADMIN, dbuser);
				return "redirect:/backstage/whole-decoration-order/list.html";
			} else {
				//用户名或者密码不通过
				m.addAttribute("msg", "用户名或密码不正确");
				return "manage/login";
			}

		} else {
			//验证码不通过
			m.addAttribute("msg", "验证码不正确");
			return "manage/login";
		}
	}

	@GetMapping("/dologout")
	public String dologout(HttpServletRequest request, UserAdministrator user, Model m) {

		request.getSession().invalidate();
		return "manage/login";

	}



}
