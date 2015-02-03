package app.cq.hmq.controller.teacheck;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.pojo.Org;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.pojo.teacheck.TeacherCheck;
import app.cq.hmq.service.teacheck.TeaCheckService;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;

@Controller
@RequestMapping(value = "/teacheck")
public class TeaCheckController {
	@Resource 
	private TeaCheckService teacheckService;
	
	
	@RequestMapping(value="/ppage")
	public ModelAndView pageMentPage() {
		ModelAndView mv = new ModelAndView("/app/teacheck/checks");
		Map<Integer,Object> map = teacheckService.getCurrentClassId();
		mv.addObject("orgid", map.get(1));
		mv.addObject("orgname", map.get(2));
		TeacherCheck tCheck = new TeacherCheck();
		Org o = new Org();
		o.setId(Long.parseLong(String.valueOf(map.get(1))));
		o.setType(Integer.parseInt(String.valueOf(map.get(3))));
		tCheck.setOrg(o);
		mv.addObject("tCheck", tCheck);
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
	public EasyData<EntranceHandle> findData(PageModel model, EntranceHandle condition) {
		return teacheckService.findData(model, condition);
	}
	
	/**
	 * 
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/findUnStudent")
	@ResponseBody
	public Map<String, Object> findUnStudentData(Long orgid,String orgname){
		return teacheckService.findUnStudentData(orgid, orgname);
	}
	
	/**
	 * 
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/updateUnStatus")
	@ResponseBody
	public int updateUnStatus(TeacherCheck tCheck,Integer flag){
		return teacheckService.updateUnStatus(tCheck,flag);
	}
	
	@RequestMapping(value = "/saveduodao")
	@ResponseBody
	public int saveduodao(TeacherCheck tCheck){
		return  teacheckService.saveduodao(tCheck);
	}
	
	@RequestMapping(value = "/deleteduodao")
	@ResponseBody
	public int deleteduodao(Long tcId){
		return  teacheckService.deleteduodao(tcId);
	}
}
