package app.cq.hmq.controller.placement;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.cq.hmq.util.Json;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.service.placement.PlacementService;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;

/**
 * 分班管理控制器
 * 
 * @author cqmonster
 * 
 */
@Controller
@RequestMapping("/placement")
public class PlacementController {

	@Autowired
	private PlacementService placementService;

	@RequestMapping(value = "/page/index")
	public String pageindex() {
		return "/app/placement/index";
	}

	/**
	 * 跳转分班规则页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/page/rule")
	public String pagerule() {
		return "/app/placement/rule";
	}

	/**
	 * 后台查找数据
	 * 
	 * @param model
	 * @param handle
	 * @return
	 */
	@RequestMapping(value = "/data/find")
	@ResponseBody
	public EasyData<Map<String, Object>> finddata(PageModel model,
			EntranceHandle handle) {
		return placementService.find(model, handle);
	}

	/**
	 * 分班
	 * 
	 * @return
	 */
	@RequestMapping(value = "/divideintoclasses")
	@ResponseBody
	public String divideintoclasses() {

		return Json.write(Json.SUCCESS, "分班成功");
	}

}
