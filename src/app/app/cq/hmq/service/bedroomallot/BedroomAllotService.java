/**
 * Limit
 *
 */
package app.cq.hmq.service.bedroomallot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.ListUI;

import org.apache.commons.collections.ListUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.pojo.Org;

import app.cq.hmq.pojo.bedroomallot.BedroomAllot;
import app.cq.hmq.pojo.entrancehandle.EntranceHandle;

import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class BedroomAllotService extends BaseService {

	
	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-20 下午4:56:23
	 *@version 1.0
	 *@Description 查询男生寝室信息
	 *
	 *@param id
	 * @param gender 
	 *@return
	 *
	 *
	 */
	public List<Map<String, Object>> queryTreeValue(Long id) {
		List<Map<String,Object>> resultList = null;
		Map<String,Object> resultMap = null;
		System.out.println(id);
		String querySql = " from BedroomAllot where ";
		resultList = new ArrayList<Map<String,Object>>();
		if(id == 1){
			querySql += " type = ? and gender = 1 and userStatus = 0";
			List<BedroomAllot> find = dao.find(querySql, Integer.parseInt(String.valueOf(id)));
			if(find.isEmpty()){
				return null;
			}else{
				for (int i = 0; i < find.size(); i++) {
					BedroomAllot allot = find.get(i);
					resultMap = new HashMap<String,Object>();
					resultMap.put("id", allot.getId() + ",1,1");
					resultMap.put("text", allot.getName());
					resultMap.put("state", "closed");
					resultMap.put("iconCls", "icon-lou-small");
					resultList.add(resultMap);
				}
				return resultList;
			}
		}else{
			querySql += " gender = 1 and parent.id = ? and userStatus = 0";
			List<BedroomAllot> find = dao.find(querySql, id);
			if(find.isEmpty()){
				return null;
			}else{
				for (int i = 0; i < find.size(); i++) {
					BedroomAllot allot = find.get(i);
					resultMap = new HashMap<String,Object>();
					if(allot.getType() == 2){
						resultMap.put("id", allot.getId() + ",2,1");
						resultMap.put("text", allot.getName());
						resultMap.put("state", "closed");
						resultMap.put("iconCls", "icon-lou-small");
						resultList.add(resultMap);
					}else if(allot.getType() == 3){
						resultMap.put("id", allot.getId() + ",3,1");
						if(allot.getAlreadyLive() != 0){
							resultMap.put("text", allot.getName()+"(可住"+allot.getLiveSum()+"人,已住"+ allot.getAlreadyLive() +"人)");
						}else{
							resultMap.put("text", allot.getName()+"(可住"+allot.getLiveSum()+"人)");
						}
						resultMap.put("state", "open");
						resultMap.put("iconCls", "icon-lou-small");
						resultList.add(resultMap);
					}
				}
				return resultList;
			}
		}
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
	public List<Map<String, Object>> queryTreeValueToWomen(Long id) {
		List<Map<String,Object>> resultList = null;
		Map<String,Object> resultMap = null;
		System.out.println(id);
		String querySql = " from BedroomAllot where ";
		resultList = new ArrayList<Map<String,Object>>();
		if(id == 0){
			querySql += " type = ? and gender = 0  and userStatus = 0";
			List<BedroomAllot> find = dao.find(querySql, 1);
			if(find.isEmpty()){
				return null;
			}else{
				for (int i = 0; i < find.size(); i++) {
					BedroomAllot allot = find.get(i);
					resultMap = new HashMap<String,Object>();
					resultMap.put("id", allot.getId() + ",1,0");
					resultMap.put("text", allot.getName());
					resultMap.put("state", "closed");
					resultMap.put("iconCls", "icon-lou-small");
					resultList.add(resultMap);
				}
				return resultList;
			}
		}else{
			querySql += " gender = 0 and parent.id = ? and userStatus = 0";
			List<BedroomAllot> find = dao.find(querySql, id);
			if(find.isEmpty()){
				return null;
			}else{
				for (int i = 0; i < find.size(); i++) {
					BedroomAllot allot = find.get(i);
					resultMap = new HashMap<String,Object>();
					if(allot.getType() == 2){
						resultMap.put("id", allot.getId() + ",2,0");
						resultMap.put("text", allot.getName());
						resultMap.put("state", "closed");
						resultMap.put("iconCls", "icon-lou-small");
						resultList.add(resultMap);
					}else if(allot.getType() == 3){
						resultMap.put("id", allot.getId() + ",3,0");
						if(allot.getAlreadyLive() != 0){
							resultMap.put("text", allot.getName()+"(可住"+allot.getLiveSum()+"人,已住"+ allot.getAlreadyLive() +"人)");
						}else{
							resultMap.put("text", allot.getName()+"(可住"+allot.getLiveSum()+"人)");
						}
						resultMap.put("state", "open");
						resultMap.put("iconCls", "icon-lou-small");
						resultList.add(resultMap);
					}
				}
				return resultList;
			}
		}
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
	public Map<String, Object> queryRoomInfo(Long id) {
		Map<String, Object> map = null;
		if(null == id || StringUtil.isEmpty(id)){
			map = new HashMap<String, Object>();
			map.put("flg", 2);
			return map;
		}
		BedroomAllot allot = dao.findOne(BedroomAllot.class, "id", id);
		if(null != allot){
			map = new HashMap<String, Object>();
			map.put("flg", 1);
			map.put("data", allot);
			return map;
		}
		map = new HashMap<String, Object>();
		map.put("flg", 2);
		return map;
	}

	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-23 下午3:28:01
	 *@version 1.0
	 *@Description
	 *
	 *@param model
	 * @param classid 
	 * @param sex 
	 *@param regist
	 *@param startTime
	 *@param endTime
	 *@return
	 *
	 *
	 */
	public EasyData<EntranceHandle> finddata(PageModel model, Long classid, int sex) {
		System.out.println(classid);
		EasyData<EntranceHandle> data = new EasyData<EntranceHandle>();
		PageList<EntranceHandle> pagelis = null;
		StringBuffer sb = new StringBuffer(" where 1=1 and isPay = 1 and isFenBan = 1");
		sb.append("and org = " + classid);
		sb.append("and sex = " + sex);
		sb.append("and isFenQS = 0");
		sb.append(" order by createTime,stage desc");
		pagelis = page(model, "from " + EntranceHandle.class.getName()
				+ sb.toString());
		if(pagelis.size() == 0){
			return data;
		}
		return new EasyData<EntranceHandle>(pagelis);
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
	public List<Map<String, Object>> findGrand(Long id) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if(null == id || StringUtil.isEmpty(id)){
			return list;
		}
		List<Org> list2 = dao.find(Org.class, "parent.id", id);
		if(list2.isEmpty()){
			return list;
		}
		map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "--请选择--");
		list.add(map);
		for (Org org : list2) {
			map = new HashMap<String, Object>();
			map.put("id", org.getId());
			map.put("text", org.getName());
			list.add(map);
		}
		return list;
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
	public List<Map<String, Object>> initJieDuan() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		List<Org> list2 = dao.find(Org.class, "parent.id", 14L);
		if(list2.isEmpty()){
			return list;
		}
		map = new HashMap<String, Object>();
		map.put("id", "");
		map.put("text", "--请选择--");
		list.add(map);
		for (Org org : list2) {
			map = new HashMap<String, Object>();
			map.put("id", org.getId());
			map.put("text", org.getName());
			list.add(map);
		}
		return list;
	}


	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-25 下午4:48:15
	 *@version 1.0
	 *@Description 保存分配的寝室信息
	 *
	 *@param roomId
	 *@param roomAlreadytLive
	 *@param stuIds
	 *@return
	 *
	 *
	 */
	@Transactional
	public AjaxMsg saveBedRoomInfo(Long roomId, int roomAlreadytLive,
			String stuIds) {
		AjaxMsg msg = new AjaxMsg();
		if(StringUtil.isEmpty(roomId) || StringUtil.isEmpty(stuIds)){
			msg.setType(2);
			return msg;
		}
		BedroomAllot bedroomAllot = dao.findOne(BedroomAllot.class, "id", roomId);
		if(null == bedroomAllot){
			msg.setType(2);
			return msg;
		}
		if(bedroomAllot.getLiveSum() < (roomAlreadytLive + stuIds.split(",").length)){
			msg.setType(2);
			return msg;
		}
		String stuId = " where t.id in ( ";
		String[] split = stuIds.split(",");
		for (int i = 0; i < split.length; i++) {
			if(i == (split.length - 1)){
				stuId += Long.parseLong(split[i]);
			}else{
				stuId += Long.parseLong(split[i]) + ",";
			}
		}
		stuId += " )";
		String updateSql = "update EntranceHandle t set t.isFenQS = 1,t.dormitory = " + roomId + stuId;
		dao.excute(updateSql);
		bedroomAllot.setAlreadyLive(bedroomAllot.getAlreadyLive() + split.length);
		dao.update(bedroomAllot);
		msg.setType(1);
		msg.setMsg(bedroomAllot.getLiveSum() + "," + bedroomAllot.getAlreadyLive() + "," + bedroomAllot.getGender() + "," + bedroomAllot.getName());
		return msg;
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
	public AjaxMsg autoSaveBedRoomInfo(Long roomId, int roomtype, Long classId,
			int gender) {
		AjaxMsg msg = new AjaxMsg();
		msg.setType(2);
		msg.setMsg("数据异常,请联系管理员");
		if(StringUtil.isEmpty(classId)){
			msg.setMsg("没有选择班级信息");
			return msg;
		}
		String stuSql = "select en.id_f from entrancehandle_t en where en.org_f = "+ classId +" and en.isfenqs_f = 0 and en.sex_f = " + gender;
		List list = dao.getHelperDao().find(stuSql);
		if(list == null || list.size() == 0){
			msg.setMsg("班级下没有学生信息");
			return msg;
		}
		StringBuffer sb = new StringBuffer("select bed.id_f,bed.alreadylive_f,bed.livesum_f from bedroomallot_t bed where bed.stuid_f is not null ");
		sb.append(" and bed.type_f = 3 and bed.livesum_f - bed.alreadylive_f > 0 and  bed.gender_f = " + gender);
		if(roomtype == 1){
			sb.append(" and bed.parent_f in (select bed.id_f from bedroomallot_t bed where bed.type_f = 2 and bed.parent_f = "+ roomId +") ");
		}
		if(roomtype == 2){
			sb.append(" and bed.parent_f = " + roomId);
		}
		if(roomtype == 3){
			sb.append("and bed.id_f = " + roomId);
		}
		sb.append(" order by bed.stuid_f asc ");
		String saveAutoBedRoom = saveAutoBedRoom(sb.toString(),list);
		if("succ".equals(saveAutoBedRoom)){
			msg.setType(1);
			msg.setMsg("分配完成");
			return msg;
		}else if("2".equals(saveAutoBedRoom)){
			msg.setMsg("学生数据异常,请联系管理员");
			return msg;
		}else if("3".equals(saveAutoBedRoom)){
			msg.setMsg("您选择分配寝室的楼,楼层,或者房间床位已经全部分配完成,无法在继续分配,请您重新选择其他寝室进行分配");
			return msg;
		}
		return msg;
	}
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-7-1 下午3:21:26
	 *@version 1.0
	 *@Description
	 *
	 *@param sql
	 *@param list
	 *@return
	 *
	 *
	 */
	@Transactional
	public String saveAutoBedRoom(String sql,List list){
		List roomList = dao.getHelperDao().find(sql);
		if(list.size() == 0){
			return "1";
		}else if(roomList.size() == 0){
			return "3";
		}
		else{
			Object[] object = (Object[]) roomList.get(0);
			int alreadyLive = Integer.parseInt(object[1].toString());
			//当前一间寝室能住的人数
			int num = Integer.parseInt(object[2].toString()) - alreadyLive;
			int cycleNum = 0;
			if(num > list.size()){
				cycleNum = list.size();
			}else if(num <= list.size()){
				cycleNum = num;
			}
			String updateStuIdSql = " where t.id in ( ";
			for (int i = 0; i < cycleNum; i++) {
				Object object2 = list.get(i);
				if(i == (cycleNum - 1)){
					updateStuIdSql += Long.parseLong(object2.toString()) + ")";
				}else{
					updateStuIdSql += Long.parseLong(object2.toString()) + ",";
				}
				alreadyLive = alreadyLive + 1;
			}
			String updateStuSql = "update EntranceHandle t set t.isFenQS = 1,t.dormitory = " + Long.parseLong(object[0].toString()) + updateStuIdSql;
			String updateBedRoomSql = "update BedroomAllot b set b.alreadyLive = " + alreadyLive + " where b.id = " + Long.parseLong(object[0].toString());
			dao.excute(updateStuSql);
			dao.excute(updateBedRoomSql);
			for (int i = 0; i < cycleNum; i++) {
				list.remove(0);
			}
			if(list.size() == 0){
				return "succ";
			}
			return saveAutoBedRoom(sql,list);
		}
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
	@SuppressWarnings("unchecked")
	public EasyData<Map<String,Object>> queryStudentbed(PageModel model, EntranceHandle regist) {
		EasyData<Map<String,Object>> data = new EasyData<Map<String,Object>>();
		//PageList<Map<String,Object>> pagelis = null;
		StringBuffer sb = new StringBuffer("select stu.name_f,stu.guardian_f,stu.examcardno_f," +
				"stu.tel_f,bed.name_f as bname,bed.stuid_f,stu.id_f, stu.sex_f," +
				"bed.id_f as bid from entrancehandle_t stu,bedroomallot_t bed " +
				"where stu.dormitory_f = bed.id_f and stu.isfenqs_f = 1");
		if(null != regist){
			if(!StringUtil.isEmpty(regist.getExamCardNo()) || !StringUtil.isEmpty(regist.getName())){
				//准考证号
				if(!StringUtil.isEmpty(regist.getExamCardNo())){
					sb.append(" and stu.examcardno_f like '%" + regist.getExamCardNo() + "%' ");
				}
				//姓名
				if(!StringUtil.isEmpty(regist.getName())){
					sb.append(" and stu.name_f like '%" + regist.getName() + "%' ");
				}
			}else{
				if(!StringUtil.isEmpty(regist.getSex()) || !StringUtil.isEmpty(regist.getDormitory())){
					//性别
					if(!StringUtil.isEmpty(regist.getSex())){
						sb.append(" and bed.gender_f =  " + regist.getSex());
					}
					//寝室号
					if(!StringUtil.isEmpty(regist.getDormitory())){
						sb.append(" and bed.stuid_f ='" + String.valueOf(regist.getDormitory())+"' ");
					}
				}
			}
			//拟读年级
			if(!StringUtil.isEmpty(regist.getOrg())){
				sb.append(" and stu.org_f =  " + regist.getOrg());
			}
			sb.append(" order by stu.sex_f ");
		}
//		pagelis = page(model, "from " + EntranceHandle.class.getName()
//				+ sb.toString());
		PageList page = dao.getHelperDao().page(model.getPage(), model.getRows(), sb.toString());
		List list = page.getList();
		if(list.size() == 0){
			return data;
		}
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = null;
		for (int i = 0; i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			map = new HashMap<String,Object>();
			if(!StringUtil.isEmpty(object[0].toString())){
				map.put("stuName", object[0].toString());
			}
			if(!StringUtil.isEmpty(object[1].toString())){
				map.put("guardian", object[1].toString());
			}
			if(!StringUtil.isEmpty(object[2].toString())){
				map.put("examcardno", object[2].toString());
			}
			if(!StringUtil.isEmpty(object[3].toString())){
				map.put("tel", object[3].toString());
			}
			if(!StringUtil.isEmpty(object[4].toString())){
				map.put("bedName", object[4].toString());
			}
			if(!StringUtil.isEmpty(object[5].toString())){
				map.put("bedCode", object[5].toString());
			}
			if(!StringUtil.isEmpty(object[7].toString())){
				map.put("sex", object[7].toString());
			}
			if(!StringUtil.isEmpty(object[6].toString())){
				map.put("id", object[6].toString());
			}
			if(!StringUtil.isEmpty(object[8].toString())){
				map.put("bedid", object[8].toString());
			}
			resultList.add(map);
		}
		page.setResult(resultList);
		return new EasyData<Map<String,Object>>(page);
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
	public ModelAndView updateBedRoom(Integer sex, Long stuId, Long roomId) {
		if(StringUtil.isEmpty(sex) || StringUtil.isEmpty(stuId) || StringUtil.isEmpty(roomId)){
			return null;
		}
		
		ModelAndView mav = new ModelAndView("/app/bedroomallot/bedroomupdate");
		// 民族查询
		//mav.addObject("nations", enumerationService.findnation());
		EntranceHandle handle = dao.findOne(EntranceHandle.class, "id", stuId);
		BedroomAllot bedroomAllot = dao.findOne(BedroomAllot.class, "id", roomId);
		handle.setIdCardNo(bedroomAllot.getParent().getParent().getName() + "-" + bedroomAllot.getParent().getName() + "-" + bedroomAllot.getName());
		mav.addObject("eh", handle);
		mav.addObject("oldroom", roomId);
		return mav;
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
	 * @param roomType,Long roomId 
	 *@return
	 *
	 *
	 */
	public List<Map<String, Object>> initbedRoom(Integer sex, int roomType,Long roomId) {
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		if(1 == roomType){
			String querySql = "from BedroomAllot t where t.type = 1 and t.gender = ?";
			List bedList = dao.find(querySql, sex);
			if(bedList.size() == 0){
				return null;
			}
			map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "--请选择--");
			resultList.add(map);
			for (int i = 0; i < bedList.size(); i++) {
				BedroomAllot object = (BedroomAllot) bedList.get(i);
				map = new HashMap<String, Object>();
				map.put("id", object.getId());
				map.put("text", object.getName());
				resultList.add(map);
			}
			return resultList;
		}else if(2 == roomType){
			map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "--请选择--");
			resultList.add(map);
			if(StringUtil.isEmpty(roomId)){
				return resultList;
			}
			String querySql = "from BedroomAllot t where t.type = 2 and t.parent.id = ?";
			List bedList = dao.find(querySql, roomId);
			if(bedList.size() == 0){
				return resultList;
			}
			for (int i = 0; i < bedList.size(); i++) {
				BedroomAllot object = (BedroomAllot) bedList.get(i);
				map = new HashMap<String, Object>();
				map.put("id", object.getId());
				map.put("text", object.getName());
				resultList.add(map);
			}
			return resultList;
			
		}else{
			map = new HashMap<String, Object>();
			map.put("id", "");
			map.put("text", "--请选择--");
			resultList.add(map);
			if(StringUtil.isEmpty(roomId)){
				return resultList;
			}
			String querySql = "from BedroomAllot t where t.type = 3 and t.parent.id = ?";
			List bedList = dao.find(querySql, roomId);
			if(bedList.size() == 0){
				return resultList;
			}
			for (int i = 0; i < bedList.size(); i++) {
				BedroomAllot object = (BedroomAllot) bedList.get(i);
				map = new HashMap<String, Object>();
				map.put("id", object.getId());
				map.put("text", object.getName() + "(可以住"+ object.getLiveSum() +"人,已住"+ object.getAlreadyLive() +"人)");
				resultList.add(map);
			}
			return resultList;
		}
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
	@Transactional
	public AjaxMsg updateBedRoomTo(Long stuId, Long bedNo, Long oldRoom) {
		AjaxMsg msg = new AjaxMsg();
		if(StringUtil.isEmpty(bedNo)){
			msg.setMsg("请选择对应的寝室房间");
			return msg;
		}
		EntranceHandle handle = dao.findOne(EntranceHandle.class, "id", stuId);
		BedroomAllot newallot = dao.findOne(BedroomAllot.class, "id", bedNo);
		BedroomAllot oldallot = dao.findOne(BedroomAllot.class, "id", oldRoom);
		if(null == handle || null == newallot){
			msg.setMsg("数据异常,请联系管理员");
			return msg;
		}
		if(newallot.getLiveSum() - newallot.getAlreadyLive() <= 0){
			msg.setMsg("该间寝室已经注满,请选择其他寝室入住");
			return msg;
		}
		handle.setDormitory(newallot.getId());
		newallot.setAlreadyLive(newallot.getAlreadyLive() + 1);
		oldallot.setAlreadyLive(oldallot.getAlreadyLive() - 1);
		dao.update(handle);
		dao.update(newallot);
		dao.update(oldallot);
		msg.setMsg("修改成功");
		return msg;
	}
}
