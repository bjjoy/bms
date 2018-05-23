package cn.bjjoy.bms.setting.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@RequestMapping(value = "/login" , method = RequestMethod.GET)
	public String login() {
		return "admin/login";
	}

	/**
	 * 用户登录验证
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password, ModelMap model) {
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			return redirect("/admin/index");
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
		}
		return "admin/login";
	}

	@RequestMapping(value = "/logout" , method = RequestMethod.GET)
	public String logout() {
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		return redirect("admin/login");
	}

	@RequestMapping(value ={"","/index"}, method = RequestMethod.GET)
	public String index(){
		return "admin/index";
	}

	/**
	 * 带参重定向
	 *
	 * @param path
	 * @return
	 */
	private String redirect(String path) {
		return "redirect:" + path;
	}
}
