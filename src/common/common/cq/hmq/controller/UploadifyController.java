package common.cq.hmq.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import core.cq.hmq.annotation.ControllerAnn;
import core.cq.hmq.controller.core.BaseController;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.service.AttachService;

@Controller
@RequestMapping("/uploadify")
public class UploadifyController extends BaseController {

	@Resource
	private AttachService attachService;

	/**
	 * 附件上传
	 * 
	 * @param request
	 *            uploadifly插件所调方法
	 * @return
	 */
	@RequestMapping(value = "/upload")
	@ControllerAnn(toLogon = false)
	@ResponseBody
	public AjaxMsg upload(HttpServletRequest request) {
		return attachService.upload(request);// uploadify上传返回
	}
}
