/**
 * Limit
 *
 */
package app.cq.hmq.controller.bedroomallot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.service.bedroomallot.BedroomAllotService;
import core.cq.hmq.controller.core.BaseController;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;

/**
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/bedroomallot")
public class BedroomAllotController extends BaseController {
	
	@Autowired
	private BedroomAllotService baService;
	
	@RequestMapping(value = "/main")
	public ModelAndView entranceHandle() {
		ModelAndView mav = new ModelAndView("/app/bedroomallot/bedroomallotmain");
		// 民族查询
		//mav.addObject("nations", enumerationService.findnation());
		return mav;
	}
	
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-20 下午4:56:23
	 *@version 1.0
	 *@Description 查询男生寝室信息
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/treevaluemen")
	@ResponseBody
	public List<Map<String,Object>> queryTreeValueToMen(Long id) {
		return baService.queryTreeValue(id);
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-20 下午4:56:23
	 *@version 1.0
	 *@Description 查询女生寝室信息
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/treevaluewomen")
	@ResponseBody
	public List<Map<String,Object>> queryTreeValueToWomen(Long id) {
		return baService.queryTreeValueToWomen(id);
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-20 下午4:56:23
	 *@version 1.0
	 *@Description 查询单一寝室信息寝室信息
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/queryroominfo")
	@ResponseBody
	public Map<String,Object> queryRoomInfo(Long id) {
		return baService.queryRoomInfo(id);
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 下午3:40:00
	 *@version 1.0
	 *@Description 查询班级下的学生信息，进行分配寝室操作
	 *
	 *@param model
	 *@param regist
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/finddata/data")
	@ResponseBody
	public EasyData<EntranceHandle> finddata(PageModel model,Long classid,int sex) {
		return baService.finddata(model,classid,sex);
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
		ModelAndView mav = new ModelAndView("/app/bedroomallot/querybedroom");
		return mav;
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-25 下午2:15:10
	 *@version 1.0
	 *@Description 查询阶段数据
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/initjieduan")
	@ResponseBody
	public  List<Map<String,Object>> initJieDuan(){
		return baService.initJieDuan();
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-25 下午2:15:10
	 *@version 1.0
	 *@Description 查询阶段下的年级
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/findgrand")
	@ResponseBody
	public  List<Map<String,Object>> findGrand(Long id){
		return baService.findGrand(id);
	}


	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-25 下午4:48:15
	 *@version 1.0
	 *@Description 手动保存分配的寝室信息
	 *
	 *@param roomId
	 *@param roomAlreadytLive
	 *@param stuIds
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/savebedroominfo")
	@ResponseBody
	public AjaxMsg saveBedRoomInfo(Long roomId,int roomAlreadytLive,String stuIds){
		return baService.saveBedRoomInfo(roomId,roomAlreadytLive,stuIds);
	}
	
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-1 上午11:44:09
	 *@version 1.0
	 *@Description 自动保存分配的寝室信息
	 *
	 *@param roomId
	 *@param roomtype
	 *@param classId
	 *@param gender
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/autosavebedroominfo")
	@ResponseBody
	public AjaxMsg autoSaveBedRoomInfo(Long roomId,int roomtype,Long classId,int gender){
		return baService.autoSaveBedRoomInfo(roomId,roomtype,classId,gender);
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-2 下午5:12:28
	 *@version 1.0
	 *@Description 查询已经分完寝室的学生信息
	 *
	 *@param model
	 *@param regist
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/querystudentroom")
	@ResponseBody
	public EasyData<Map<String,Object>> queryStudentRoom(PageModel model, EntranceHandle regist) {
		return baService.queryStudentbed(model, regist);
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-3 下午2:41:08
	 *@version 1.0
	 *@Description 修改学生寝室
	 *
	 *@param sex
	 *@param stuId
	 *@param roomId
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/updatebedroom")
	public ModelAndView updateBedRoom(Integer sex,Long stuId,Long roomId) {
		return baService.updateBedRoom(sex,stuId,roomId);
	}
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-3 下午3:16:50
	 *@version 1.0
	 *@Description 修改寝室页面初始化寝室楼信息
	 *
	 *@param sex
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/initbedroom")
	@ResponseBody
	public  List<Map<String,Object>> initbedRoom(Integer sex,int roomType,Long roomId){
		return baService.initbedRoom(sex,roomType,roomId);
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-4 下午3:39:41
	 *@version 1.0
	 *@Description 保存修改寝室页面修改的寝室信息
	 *
	 *@param stuId
	 *@param bedNo
	 *@param oldRoom
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/updatebedroomto")
	@ResponseBody
	public  AjaxMsg updateBedRoomTo(Long stuId,Long bedNo,Long oldRoom){
		return baService.updateBedRoomTo(stuId,bedNo,oldRoom);
	}
	
}
