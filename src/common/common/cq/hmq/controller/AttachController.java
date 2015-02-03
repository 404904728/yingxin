package common.cq.hmq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.pojo.Attach;

import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.AttachService;

@Controller
@RequestMapping("/attach")
public class AttachController {

	/**
	 * 跳转附件页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page")
	public String showPage() {
		return "/core/attach/attach";
	}

	/**
	 * 跳转附件上传页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/uploadpage")
	public String attachUpLoad() {
		return "/core/attach/attachUpload";
	}

	@Resource
	private AttachService attachService;

	/**
	 * 获取附件信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/finddata")
	@ResponseBody
	public EasyData<Attach> getData(PageModel pageModel) {
		EasyData<Attach> ed = attachService.findAttach(pageModel);
		return ed;
	}

	@RequestMapping(value = "/officeView_")
	public ModelAndView officeView(Long id, String docType) {
		ModelAndView modelAndView = new ModelAndView("/core/attach/officeView");
		modelAndView.addObject("id", id);
		modelAndView.addObject("docType", docType);
		return modelAndView;
	}

	/**
	 * 删除附件
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	@ControllerAnn(toLogon = false)
	public AjaxMsg delete(Long id) {
		return attachService.delete(id);
	}

}
