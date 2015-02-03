package app.cq.hmq.controller.onlineregistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.cq.hmq.pojo.onlineregistration.Regist;
import app.cq.hmq.service.onlineregistration.OnlineregistService;
import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.EnumerationService;

/**
 * 网上报名
 * 
 * @author 何建
 * 
 */
@Controller
@RequestMapping(value = "/onlineregist")
public class OnlineRegistController {

	@Autowired
	private OnlineregistService onlineregistService;

	@Autowired
	private EnumerationService enumerationService;

	/**
	 * 后台页面跳转
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/page/ht")
	public ModelAndView pageht() {
		ModelAndView mav = new ModelAndView("/app/onlineregistration/registers");
		// 民族查询
		mav.addObject("nations", enumerationService.findnation());
		return mav;
	}

	/**
	 * 前台页面跳转
	 * http://localhost/yx/onlineregist/page/regist
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/page/regist")
	@ControllerAnn(toLogon = false)
	public ModelAndView regist() {
		ModelAndView mav = new ModelAndView(
				"/app/onlineregistration/qtregisters");
		// 民族查询
		mav.addObject("nations", enumerationService.findnation());
		return mav;
	}

	/**
	 * 后台查找数据，列表显示
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/data/ht")
	@ResponseBody
	public EasyData<Regist> finddata(PageModel model, Regist regist) {
		return onlineregistService.finddata(model, regist);
	}

	/**
	 * 新增面跳转
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/page/save")
	public ModelAndView savepage() {
		ModelAndView mav = new ModelAndView(
				"/app/onlineregistration/registerform");
		mav.addObject("nations", enumerationService.findnation());
		return mav;
	}

	/**
	 * 修改页面跳转
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/page/updatepage/{id}")
	public ModelAndView updatepage(@PathVariable Long id) {
		ModelAndView mav = new ModelAndView(
				"/app/onlineregistration/registerform");
		mav.addObject("nations", enumerationService.findnation());
		mav.addObject("regist", onlineregistService.findById(id));
		return mav;
	}

	/**
	 * 新增删除数据
	 * 
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/data/saveorupdate")
	@ResponseBody
	public AjaxMsg saveorupdatedata(Regist regist) {
		AjaxMsg am = new AjaxMsg();
		if (regist.getNation().getId() == null) {
			regist.setNation(null);
		}
		try {
			if (regist.getId() == null) {
				am.setMsg("报名成功,您的准考证号为:" + onlineregistService.save(regist));
			} else {
				onlineregistService.update(regist);
				am.setMsg("修改成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			am.setMsg("操作失败");
		}
		return am;
	}

	/**
	 * 严重身份证是否重复
	 * 
	 * @return
	 */
	@RequestMapping(value = "/validcard/{card}")
	@ResponseBody
	public boolean validCard(@PathVariable String card) {
		boolean b = false;
		b = onlineregistService.validcard(card);
		return b;
	}

}
