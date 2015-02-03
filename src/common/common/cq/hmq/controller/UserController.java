package common.cq.hmq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.cq.hmq.pojo.Org;
import common.cq.hmq.pojo.User;
import common.cq.hmq.service.OrgService;
import common.cq.hmq.service.UserService;

import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;

/**
 * 用户
 * 
 * @author monster
 * 
 */
@Controller
@RequestMapping("/user")
public class UserController {

	@RequestMapping(value = "/showPage")
	public String indexView() {
		return "/core/system/user";
	}

	@Resource
	private OrgService orgService;

	/**
	 * 获取部门，用户数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/findorguser", method = RequestMethod.POST)
	public List<Map<String, String>> findOrgUser(String id) {
		if (id == null) {
			return orgService.findOrgs();
		} else {
			return orgService.findChildOrg(id);
		}
	}

	/**
	 * 获取部门，用户数据
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "finduser", method = RequestMethod.POST)
	public EasyData<User> finduser(PageModel model, User user) {
		return userService.findUser(model,user);
	}

	@Resource
	private UserService userService;

	/**
	 * 验证账号是否存在
	 * 
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "validNo", method = RequestMethod.POST)
	@ControllerAnn(toLogon = false)
	public boolean validNo(String no) {
		return userService.validNo(no) == 0 ? false : true;
	}

	@ResponseBody
	@RequestMapping(params = "register", method = RequestMethod.POST)
	@ControllerAnn(toLogon = false)
	public AjaxMsg register(User user, HttpSession session) {
		String pwd = user.getPwd();
		Org org = new Org();
		org.setId(Long.parseLong("2"));// 大众用户部门
		AjaxMsg am = userService.insert(user);
		if (am.getType() == 0) {
			user.setPwd(pwd);
			userService.logon(user, session);
			return am;
		} else {
			return am;
		}
	}

	@ResponseBody
	@RequestMapping(params = "update", method = RequestMethod.POST)
	public AjaxMsg update(User user) {
		return userService.update(user);
	}

	@ResponseBody
	@RequestMapping(params = "pwdchange", method = RequestMethod.POST)
	public AjaxMsg pwdchange(String oldPwd, String pwd, Long id) {
		return userService.pwdChange(id, oldPwd, pwd);
	}

}
