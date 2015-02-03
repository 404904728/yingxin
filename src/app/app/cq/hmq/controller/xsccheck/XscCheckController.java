package app.cq.hmq.controller.xsccheck;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.cq.hmq.service.xsccheck.XscCheckService;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.util.tools.DateUtil;

@Controller
@RequestMapping(value = "/xsccheck")
public class XscCheckController {
	@Resource 
	private XscCheckService xscCheckService;
	
	
	@RequestMapping(value="/ppage")
	public ModelAndView xscCheckPage() {
		ModelAndView mv = new ModelAndView("/app/xsccheck/xsccheck");
		return mv;
	}
	
	@RequestMapping(value="/ppage_jwc")
	public ModelAndView jwcCheckPage() {
		//ModelAndView mv = new ModelAndView("/app/jwccheck/jwccheck");
		ModelAndView mv = new ModelAndView("/app/jwccheck/aa");
		return mv;
	}
	
	@RequestMapping(value="/detail")
	public ModelAndView xsccheckDetail(Integer stage,Integer year,String flag){
		ModelAndView mv = new ModelAndView("/app/xsccheck/xsccheckDetail");
		mv.addObject("stage", stage);
		mv.addObject("year", year);
		mv.addObject("flag", flag);
		return mv;
	}
	
	/**
	 * 
	 *@param model
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/findata")
	@ResponseBody
	public EasyData<Map<String,Object>> findData(PageModel model,Integer year,Integer stage) {
		if(null == year){
			year = DateUtil.getYear(new Date());
		}
		return xscCheckService.findData(model, year,stage);
	}
	
	/**
	 * 
	 *@param model
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/findata_d")
	@ResponseBody
	public EasyData<Map<String,Object>> findData_d(PageModel model,Integer year,Integer stage,String name,String examCardNo) {
		if(null == year){
			year = DateUtil.getYear(new Date());
		}
		return xscCheckService.findData_d(model, year,stage,name,examCardNo);
	}
	
	@RequestMapping(value = "/updateStatus")
	@ResponseBody
	public int updateStatus(Integer stage,Integer year){
		return  xscCheckService.updateStatus( stage, year);
	}
}
