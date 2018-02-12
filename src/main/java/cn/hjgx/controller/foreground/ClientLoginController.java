package cn.hjgx.controller.foreground;

import cn.hjgx.Utils.JsonUtil;
import cn.hjgx.component.AuthorityFilter;
import cn.hjgx.component.LoginInterceptor;
import cn.hjgx.entity.UserAdministrator;
import cn.hjgx.entity.UserBusiness;
import cn.hjgx.entity.paramDto.ResultDto;
import cn.hjgx.service.IUserAdministratorService;
import cn.hjgx.service.IUserBusinessService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.collect.Maps;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

/**
 * 用户登录管理
 */
@Controller
@RequestMapping("/userlogin")
public class ClientLoginController {

	@Autowired
	private IUserBusinessService iUserBusinessService;

	@PostMapping("/dologin")
	@ResponseBody
	public JsonNode dologin(MultipartHttpServletRequest request) throws IOException {

		ResultDto resultDto = new ResultDto();
		try {
			UserBusiness user =
					JsonUtil.toPOJO(request.getParameter("user"), new TypeReference<UserBusiness>() {});

			//TODO 验证码通过
			if(true){

				UserBusiness dbuser = iUserBusinessService.validateUser(user);
				//用户名密码校验通过,保存会话信息，登录到管理首页
				if(dbuser != null){
					//更新最后登录时间
					dbuser.setLastLoginTime(new Date());
					iUserBusinessService.updateByPrimaryKeySelective(dbuser);
					request.getSession().setAttribute(LoginInterceptor.LOGIN_USER,dbuser);

					Map<String,Object> mapDatas = Maps.newHashMap();
					mapDatas.put("loginUser",dbuser);
					resultDto.setMapData(mapDatas);
					resultDto.setMessage("登录成功");

					JsonUtil.toJson(resultDto);
					return JsonUtil.toJson(resultDto);
				}else{
					//用户名或者密码不通过
					resultDto.setFlag(0);
					resultDto.setMessage("用户名或密码不正确");
					return JsonUtil.toJson(resultDto);
				}

			}else{
				resultDto.setFlag(0);
				resultDto.setMessage("验证码不正确");
				return JsonUtil.toJson(resultDto);
			}
		}catch (Exception e){
			resultDto.setFlag(0);
			resultDto.setMessage("登录异常，请重试或者联系系统管理员");
			return JsonUtil.toJson(resultDto);
		}

	}

	@GetMapping("/dologout")
	public String dologout(HttpServletRequest request, UserAdministrator user, Model m) {

		request.getSession().invalidate();
		return "redirect:/";

	}
	
}
