package app.cq.hmq.service.teacheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.pojo.teacheck.TeacherCheck;

import common.cq.hmq.pojo.Org;

import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.DateUtil;
import core.cq.hmq.util.tools.StringUtil;

@Service
public class TeaCheckService extends BaseService{

	public EasyData<EntranceHandle> findData(PageModel model,
			EntranceHandle condition) {
		PageList<EntranceHandle> pagelis = null;
		StringBuffer sb = new StringBuffer(" from EntranceHandle where 1=1 ");
		if(null != condition){
			if(null != condition.getOrg()){
				sb.append(" and org = ");
				sb.append(condition.getOrg());
			}else{
				return null;
			}
			
			if(!StringUtil.isEmpty(condition.getName())){
				sb.append(" and name ");
				sb.append(escapeChar(condition.getName()));
			}
			
			if(!StringUtil.isEmpty(condition.getExamCardNo())){
				sb.append(" and examCardNo ");
				sb.append(escapeChar(condition.getExamCardNo()));
			}
			
			if(null != condition.getStage()){
				sb.append(" and stage = ");
				sb.append(condition.getStage());
			}
			
			if(null != condition.getYear()){
				sb.append(" and year = ");
				sb.append(condition.getYear());
			}
			
			if(!StringUtil.isEmpty(model.getSort()) && !StringUtil.isEmpty(model.getOrder())){
				sb.append(" order by ");
				sb.append(model.getSort());
				sb.append(" " +model.getOrder());
			}
		}
		
		pagelis = dao.page(model.getPage(), model.getRows(), sb.toString());
		if(null != pagelis && pagelis.getList().size() < 1){
			return new EasyData<EntranceHandle>();
		}
		return new EasyData<EntranceHandle>(pagelis);
	}
	
	public String escapeChar(String str) {
		return "like '%"
				+ str.replace("%", "/%").replace("'", "''").replace("_", "/_")
				+ "%' escape '/' ";
	}

	@Transactional
	public int updatePaySum(Long id, float sum) {
		if(null != id){
			try {
				dao.excute("update EntranceHandle set paySum = ?,isPay = 1 where id = ? ",sum,id);
			} catch (Exception e) {
				e.printStackTrace();
				return -1;
			}
		}
		return 1;
	}
	
	public Map<Integer, Object> getCurrentClassId(){
		/** 查询当前用户是否是某个班级的班主任 */
		Map<Integer, Object>  map = new HashMap<Integer, Object>();
		List<Object[]> list = dao.getHelperDao().find("select o.id_f,o.name_f from org_t o where o.leader_f = "+currentUserId()+" and o.status_f = 1");
		if(null != list && list.size() > 0){
			map.put(1, list.get(0)[0]);
			map.put(2, list.get(0)[1]);
			Org o = dao.load(Org.class, Long.parseLong(String.valueOf(map.get(1))));
			/** 阶段 */
			map.put(3, o.getParent().getParent().getId()-1);
			return map;
		}
		return map;
	}
	

	public Map<String, Object> findUnStudentData(Long orgid,String orgname) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TeacherCheck> list=  dao.find("from TeacherCheck where org.id = ?", orgid);
		if(null != list && list.size() > 0){
			List<Long> wdList = new ArrayList<Long>();
			List<Map<String, Object>> ddList = new ArrayList<Map<String, Object>>();
			for (TeacherCheck teacherCheck : list) {
				if(1 == teacherCheck.getType()){
					wdList.add(teacherCheck.getStuId());
				}else{
					Map<String, Object> dmap = new HashMap<String, Object>();
					dmap.put("id", teacherCheck.getId());
					dmap.put("name", teacherCheck.getAddName());
					dmap.put("examcardno", teacherCheck.getExamcardNo());
					dmap.put("orgname", orgname);
					ddList.add(dmap);
				}
			}
			map.put("title", "学生信息("+orgname+":未到"+wdList.size()+"人,多到"+ddList.size()+"人)");
			map.put("weidao",wdList);
			map.put("duodao",ddList);
		}
		return map;
	}

	/**
	 * 
	 * @param tCheck
	 * @param flag 默认type为1 即未到
	 * @return
	 */
	@Transactional
	public int updateUnStatus(TeacherCheck tCheck,
			Integer flag) {
		if(null != flag){
			if(1 == flag){
				if(null != tCheck.getOrg() && null != tCheck.getStuId()){
					tCheck.setYear(DateUtil.getYear(new Date()));
					tCheck.setStage(tCheck.getOrg().getType());
					tCheck.setStage(tCheck.getOrg().getType());
					dao.insert(tCheck);
				}
			}else if(2 == flag){
				dao.excute("delete TeacherCheck where org.id = ? and stuId = ?", tCheck.getOrg().getId(),tCheck.getStuId());
			}
			return 1;
		}
		return -1;
	}

	@Transactional
	public int saveduodao(TeacherCheck tCheck) {
		if(null != tCheck){
			if(null != tCheck.getId()){
				if(!StringUtil.isEmpty(tCheck.getAddName()) || !StringUtil.isEmpty(tCheck.getExamcardNo())){
					tCheck.setType(2);
					tCheck.setYear(DateUtil.getYear(new Date()));
					tCheck.setStage(tCheck.getOrg().getType());
					dao.update(tCheck);
					return 1;
				}
			}else{
				if(!StringUtil.isEmpty(tCheck.getAddName()) || !StringUtil.isEmpty(tCheck.getExamcardNo())){
					tCheck.setType(2);
					tCheck.setYear(DateUtil.getYear(new Date()));
					tCheck.setStage(tCheck.getOrg().getType());
					dao.insert(tCheck);
					return 1;
				}
			}
		}
		return -1;
	}

	@Transactional
	public int deleteduodao(Long tcId) {
		if(null != tcId){
			try {
				dao.delete(TeacherCheck.class, tcId);
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return -1;
	}
	
}
