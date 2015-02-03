package common.cq.hmq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.pojo.User;
import common.cq.hmq.service.UserService;

import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.listener.SessionListener;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.SessionModel;
import core.cq.hmq.service.system.MenuService;
import core.cq.hmq.util.tools.ResourceUtil;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private MenuService menuService;

	/**
	 * 后台主页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("/home/home");
		// mav.addObject("menus", menuService.findMenu(null));
		mav.addObject("headmenus", menuService.headmenu());
		return mav;
	}

	/**
	 * 登陆
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logon")
	@ControllerAnn(toLogon = false)
	public String logon_() {
		return "/home/logon";
	}

	@Autowired
	private UserService userService;

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ControllerAnn(toLogon = false)
	@ResponseBody
	public AjaxMsg logon(User user, HttpSession session) {
		boolean isLogon = userService.isLogon(session);
		if (isLogon) {
			return new AjaxMsg(0);
		}
		return userService.logon(user, session);
	}

	/**
	 * 获取当前用户
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/currentUser", method = RequestMethod.POST)
	@ResponseBody
	@ControllerAnn(toLogon = false)
	public SessionModel currentUser(HttpSession session) {
		SessionModel sModal = (SessionModel) session.getAttribute(ResourceUtil
				.getSessionInfoName());
		return sModal;
	}

	/**
	 * 用户登录
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logonImg", method = RequestMethod.POST)
	@ControllerAnn(toLogon = false)
	@ResponseBody
	public AjaxMsg logonImg(User user, HttpSession session, String authImg) {
		boolean isLogon = userService.isLogon(session);
		if (isLogon) {
			return new AjaxMsg(0, "登录成功");
		}
		return userService.logonImgAuth(user, session, authImg);
	}

	/**
	 * 用户退出
	 * 
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	@ControllerAnn(toLogon = true)
	public String logout(HttpSession session) {
		session.invalidate();
		// return "home/logon";
		return "/index";
	}

	@RequestMapping(value = "/online")
	@ResponseBody
	public int online() {
		List sessions = SessionListener.getSessions();
		return sessions.size();
	}

	@RequestMapping(value = "/changeskin")
	@ResponseBody
	public int changeskin(HttpSession session, String skin) {
		SessionModel sessionModal = (SessionModel) session
				.getAttribute("sessionModal");
		sessionModal.setSkin(skin);
		session.setAttribute("sessionModal", sessionModal);
		return 0;
	}
}
