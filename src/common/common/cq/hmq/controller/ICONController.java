package common.cq.hmq.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import core.cq.hmq.util.tools.FileUtil;
import core.cq.hmq.util.tools.ResourceUtil;

@Controller
@RequestMapping("/icon")
public class ICONController {

	private List<String> iconList;

	public List<String> getIconList() {
		return iconList;
	}

	public void setIconList(List<String> iconList) {
		this.iconList = iconList;
	}

	@RequestMapping(params = "icon")
	public ModelAndView pageIcon(HttpSession session) {
		String path = ResourceUtil.getProjectWebPath(session)
				+ "\\resources\\material\\icon";
		iconList = FileUtil.readfile(path);
		ModelAndView mav = new ModelAndView("core/system/icon");
		mav.addObject("iconList", iconList);
		return mav;
		// return view("system/icon");
	}

	@RequestMapping(params = "swf")
	public String pageSwf() {
		return "core/system/swf";
	}

}
