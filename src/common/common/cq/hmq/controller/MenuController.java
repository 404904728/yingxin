package common.cq.hmq.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.cq.hmq.service.system.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@RequestMapping(value = "/index")
	public ModelAndView menu() {
		ModelAndView mav = new ModelAndView("/home/menu/menu");
		mav.addObject("menus", menuService.findMenu(null));
		return mav;
	}

	@RequestMapping(value = "/headmenu")
	public ModelAndView headmenu() {
		ModelAndView mav = new ModelAndView("/home/menu/menuhead");
		mav.addObject("headmenus", menuService.headmenu());
		return mav;
	}

	@Autowired
	private MenuService menuService;

	// 用户获取到的菜单
	@RequestMapping(value = "/findData", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> findData(Long id, Long pId) {
		if (id != null) {
			return menuService.findMenu(id);
		}
		return menuService.findMenu(pId);
	}

	// 流程菜单管理
	@RequestMapping(params = "findProcessMenu", method = RequestMethod.POST)
	@ResponseBody
	public List<Map<String, Object>> findProcessMenu(Long id) {
		return menuService.findProcessMenu(id);
	}

	@RequestMapping(params = "page")
	public String page() {
		return "core/system/menu";
	}
}
