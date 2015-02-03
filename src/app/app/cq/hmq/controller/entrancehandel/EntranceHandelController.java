/**
 * Limit
 *
 */
package app.cq.hmq.controller.entrancehandel;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.pojo.onlineregistration.Regist;
import app.cq.hmq.service.entrancehandel.copy.EntranceHandelService;
import core.cq.hmq.controller.core.BaseController;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.EnumerationService;

/**
 * @author Limit
 * 入学办理
 */
@Controller
@RequestMapping(value = "/entrancehandel")
public class EntranceHandelController extends BaseController {
	
	
	@Autowired
	private EntranceHandelService ehservicr;
	
	@Autowired
	private EnumerationService enumerationService;
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 上午9:45:34
	 *@version 1.0
	 *@Description 查询具体一个学生的基础信息
	 *
	 *@param ksNo
	 *
	 *
	 */
	@RequestMapping(value = "/querystuinfo/{ksNo}")
	@ResponseBody
	public Map<String,Object> queryStuInfo(@PathVariable String ksNo){
		return ehservicr.queryStuInfo(ksNo);
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 下午3:39:03
	 *@version 1.0
	 *@Description 加载信息查询页面
	 *
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/querynation")
	public  ModelAndView queryNation(){
		ModelAndView mav = new ModelAndView("/app/entrancehandle/queryinfo");
		// 民族查询
		mav.addObject("nations", enumerationService.findnation());
		return mav;
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 下午3:40:00
	 *@version 1.0
	 *@Description 返回查询出的学生信息
	 *
	 *@param model
	 *@param regist
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/finddata/data")
	@ResponseBody
	public EasyData<EntranceHandle> finddata(PageModel model, EntranceHandle regist,String startTime, String endTime) {
		return ehservicr.finddata(model, regist,startTime,endTime);
	}
	
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-13 下午4:36:46
	 *@version 1.0
	 *@Description 入学办理
	 *
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/page/entranceHandleto")
	public ModelAndView entranceHandle() {
		ModelAndView mav = new ModelAndView("/app/entrancehandle/entranceHandle");
		// 民族查询
		//mav.addObject("nations", enumerationService.findnation());
		return mav;
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-17 下午5:22:55
	 *@version 1.0
	 *@Description 验证准考证号是否存在
	 *
	 *@param examCardNo
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/isexisstu/{examCardNo}")
	@ResponseBody
	public String isExisStu(@PathVariable String examCardNo ) {
		return ehservicr.isExisStu(examCardNo);
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-17 下午5:32:32
	 *@version 1.0
	 *@Description 将学生报到数据提交到数据库
	 *
	 *@param eh
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/startbaodao")
	@ResponseBody
	public String startBaoDao(EntranceHandle eh) {
		String s="";
		try {
			s=ehservicr.startBaoDao(eh);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		return s;
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-18 下午12:15:02
	 *@version 1.0
	 *@Description 查询要修改的修生信息
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/updatestudentmessage/{id}")
	public ModelAndView queryToUpdate(@PathVariable Long id) {
		ModelAndView view = new ModelAndView("/app/entrancehandle/ehupdate");
		EntranceHandle eh = ehservicr.queryToUpdate(id);
		view.addObject("eh", eh);
		return view;
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-19 上午9:30:13
	 *@version 1.0
	 *@Description 将招生办修改的数据保存到数据库
	 *
	 *@param eh
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/updateStudent")
	@ResponseBody
	public AjaxMsg updateStudent(EntranceHandle eh) {
		return ehservicr.updateStudent(eh);
	}
}
