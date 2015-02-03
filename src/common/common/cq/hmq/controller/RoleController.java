package common.cq.hmq.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.pojo.User;
import common.cq.hmq.service.UserService;

import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.controller.core.BaseController;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.pojo.Role;
import core.cq.hmq.service.system.RoleService;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	@Resource
	private UserService userService;

	@RequestMapping(value = "/page")
	public String page() {
		return "/core/roleAndpermission/roles";
	}

	@Resource
	private RoleService roleService;

	@RequestMapping(value = "/findData", method = RequestMethod.POST)
	@ResponseBody
	public EasyData<Role> findData(PageModel model) {
		return roleService.find(model);
	}

	@RequestMapping(value = "/pageform")
	public ModelAndView pageform(String id) {
		ModelAndView mav = new ModelAndView("core/roleAndpermission/roleform");
		if (id != null) {
			mav.addObject("role", roleService.findById(Long.parseLong(id)));
		}
		return mav;
	}

	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMsg saveOrUpdate(Role role) {
		if (role.getId() == null) {
			return roleService.save(role);
		} else {
			return roleService.update(role);
		}
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMsg del(String id) {
		return roleService.del(Long.parseLong(id));
	}

	/**
	 * 根据角色ID，查找该角色下的用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/finduser", method = RequestMethod.POST)
	@ResponseBody
	public List<User> findUserByRoleId(Long id) {
		return userService.findUserByRoleId(id);
	}

	/**
	 * 根据角色ID与用户ID进行添加
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/adduser", method = RequestMethod.POST)
	@ResponseBody
	@ControllerAnn(refPermission = true)
	public AjaxMsg addUserByRoleId(Long roleId, Long userId) {
		return roleService.addUser(userId, roleId);
	}

	/**
	 * 根据角色ID与用户ID进行删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/user/deluser", method = RequestMethod.POST)
	@ResponseBody
	@ControllerAnn(refPermission = true)
	public AjaxMsg delUserByRoleId(Long roleId, Long userId) {
		return roleService.delUser(userId, roleId);
	}

	/**
	 * 权限分配到角色
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/grantperpage")
	public ModelAndView grantPerPage(String id) {
		ModelAndView mav = new ModelAndView(
				"/core/roleAndpermission/rolegrantPer");
		if (id != null) {
			mav.addObject("role", roleService.findById(Long.parseLong(id)));
		}
		return mav;
	}

	/**
	 * 角色分配给权限
	 * 
	 * @return
	 */
	@RequestMapping(value = "/grantPermission", method = RequestMethod.POST)
	@ResponseBody
	public String grantPermission() {
		return null;
	}

	/**
	 * 页面跳转，角色分配给用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/grantuserpage")
	public ModelAndView grantUserPage(Long id) {
		ModelAndView mav = new ModelAndView(
				"/core/roleAndpermission/roleGrantUser");
		mav.addObject("roleId", id);
		return mav;
	}

}
