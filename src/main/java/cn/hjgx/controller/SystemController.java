package cn.hjgx.controller;

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
public class SystemController {

	@Autowired
	private IUserAdministratorService iUserAdministratorService;

	@PostMapping("/dologin")
	public String dologin(HttpServletRequest request, UserAdministrator user, Model m) {

		//TODO 验证码通过
		if(true){

			UserAdministrator dbuser = iUserAdministratorService.validateUser(user);
			//用户名密码校验通过,保存会话信息，登录到管理首页
			if(dbuser != null){
				//更新最后登录时间
				dbuser.setLastLoginTime(new Date());
				iUserAdministratorService.updateByPrimaryKeySelective(dbuser);
				request.getSession().setAttribute(AuthorityFilter.LOGIN_ADMIN,dbuser);
				return "redirect:/backstage/product/list.html";
			}else{
				//用户名或者密码不通过
				m.addAttribute("msg","用户名或密码不正确");
				return "manage/login";
			}

		}else{
			//验证码不通过
			m.addAttribute("msg","验证码不正确");
			return "manage/login";
		}
	}

	@GetMapping("/dologout")
	public String dologout(HttpServletRequest request, UserAdministrator user, Model m) {

		request.getSession().invalidate();
		return "manage/login";
		//验证码通过
//		if(true){
//
//			SystemAdmin temp = CacheService.SYSTEM_ADMIN.get(systemAdmin.getUsername());
//
//			//用户名密码校验通过,保存会话信息，登录到管理首页
//			if(temp != null && MD5Util.MD5Encode(systemAdmin.getUsername() + systemAdmin.getPassword(),"UTF-8").equals(temp.getPassword())){
//
//				request.getSession().setAttribute(AuthorityFilter.LOGIN_USER,temp);
//
//				return "redirect:/systemManage/apply/list?hasRead=false";
//			}else{
//				//用户名或者密码不通过
//				m.addAttribute("msg","用户名或密码不正确");
//				return "/manage/login";
//			}
//
//		}else{
//			//验证码不通过
//			m.addAttribute("msg","验证码不正确");
//			return "/manage/login";
//		}
	}
	
}
