package top.zerotop.blog.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import top.zerotop.blog.entity.Admin;
import top.zerotop.blog.util.ReMap;
import top.zerotop.blog.util.ReqJson;

/**
 * @author 作者: zerotop
 * @createDate 创建时间: 2018年5月31日下午11:29:14
 */
@Controller
public class UserService {
	
	/**
	 * 管理员登录
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/admin/login", produces = "application/json;charset=utf-8")
	public @ResponseBody String adminLogin(HttpServletRequest req) {

		String json = ReqJson.Json(req);

		Admin admin = JSONObject.parseObject(json, Admin.class);

		Subject subject = SecurityUtils.getSubject();
		
		if(subject.isAuthenticated()==true&&subject.getPrincipal().equals(admin.getUsername())){
			return ReMap.ResultMap(0, "当前用户已登录", null);
		}
		
		UsernamePasswordToken token = new UsernamePasswordToken(admin.getUsername(), admin.getPassword());
		try {
			token.setRememberMe(true);
			subject.login(token);
		} catch (IncorrectCredentialsException ice) {
			return ReMap.ResultMap(1, "用户名或密码错误", null);
		} catch (UnknownAccountException uae) {
			return ReMap.ResultMap(1, "用户名或密码错误", null);
		} catch (ExcessiveAttemptsException eae) {
			return ReMap.ResultMap(1, "请稍后尝试", null);
		}
		req.getSession().setAttribute("user", admin);
		

		return ReMap.ResultMap(0, "登录成功", null);
	}
	
	/**
	 * 管理员登出
	 * @return
	 */
	@RequestMapping(value="/admin/logout", produces="application/json;charset=utf-8")
	public @ResponseBody String adminLoginOut(){
		
		Subject subject = SecurityUtils.getSubject();
		
		if(subject.isAuthenticated()==true){
			subject.logout();
		}
		
		return ReMap.ResultMap(0, "退出登录成功", null);
	}

}
