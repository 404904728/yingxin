package app.cq.hmq.service.xsccheck;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.StringUtil;

@Service
public class XscCheckService extends BaseService{

	@Transactional
	public EasyData<Map<String,Object>> findData(PageModel model, Integer year,
			Integer stage) {
		PageList<Object[]> pagelis = null;
		StringBuffer sb = new StringBuffer("select t.stage_f,t.year_f,wm_concat(t.type_f),min(t.submitstatus_f) from TeacherCheck_t t where 1 = 1 ");
			if(null != stage){
				sb.append(" and t.stage_f = ");
				sb.append(stage);
			}
			if(null != year){
				sb.append(" and t.year_f = ");
				sb.append(year);
			}
			sb.append(" group by t.stage_f, t.year_f");
		pagelis = dao.getHelperDao().page(model.getPage(), model.getRows(), sb.toString());
		if(null == pagelis || (null != pagelis && pagelis.getList().size() < 1)){
			return new EasyData<Map<String,Object>>();
		}
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Object[]> rList = pagelis.getList();
		
		Map<String,Object> map = null;
		String temp = null;
		String[] arrays = null;
		for (Object[] objs : rList) {
				int weidao = 0;
				int duodao = 0;
				map = new HashMap<String, Object>();
				map.put("stage", objs[0]);
			    temp = String.valueOf(objs[2]);
				if(!StringUtil.isEmpty(temp)){
					arrays =temp.split(",");
					for(String str : arrays){
						if(!StringUtil.isEmpty(str) && "1".equals(str)){
							map.put("weidao", ++weidao);
						}else if(!StringUtil.isEmpty(str) && "2".equals(str)){
							map.put("duodao", ++duodao);
						}
					}
				}else{
					map.put("duodao", 0);
					map.put("weidao", 0);
				}
				map.put("year", objs[1]);
				map.put("status", objs[3]);
				list.add(map);
		}
		return  new EasyData<Map<String,Object>>(list, pagelis.getTotalCount());
	}

	@Transactional
	public int updateStatus(Integer stage,Integer year) {
		if(null != stage && null != year){
			dao.excute("update TeacherCheck set submitStatus = 1 where stage = ? and year = ? and submitStatus = 0", stage,year);
		}
		return 1;
	}

	public EasyData<Map<String,Object>> findData_d(PageModel model,
			Integer year, Integer stage,String name,String examCardNo) {
		PageList<Object[]> pagelis = null;
		StringBuffer sb = new StringBuffer("select t.id_f, nvl(e.examcardno_f,t.examcardno_f),nvl(e.name_f,t.addname_f),"
				+ "e.sex_f,e.birthday_f,e.idcardno_f,e.nativeplace_f,e.guardian_f,e.tel_f,t.type_f,t.submitstatus_f from TeacherCheck_t t,entrancehandle_t"
				+ " e where e.id_f(+) = t.stuid_f ");
		
		if(!StringUtil.isEmpty(name)){
			sb.append(" and e.name_f ");
			sb.append(escapeChar(name));
		}
		
		if(!StringUtil.isEmpty(examCardNo)){
			sb.append(" and e.examcardno_f ");
			sb.append(escapeChar(examCardNo));
		}
		if(null != stage){//and t.stage_f = 1 and t.year_f = 2014
			sb.append(" and t.stage_f = ");
			sb.append(stage);
		}
		if(null != year){
			sb.append(" and t.year_f = ");
			sb.append(year);
		}
			if(!StringUtil.isEmpty(model.getSort()) && !StringUtil.isEmpty(model.getOrder())){
				sb.append(" order by t.");
				sb.append(model.getSort());
				sb.append("_f " +model.getOrder());
			}
		pagelis = dao.getHelperDao().page(model.getPage(), model.getRows(), sb.toString());
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		List<Object[]> rList = pagelis.getList();
		Map<String,Object> map = null;
		for (Object[] objs : rList) {
			map = new HashMap<String, Object>();
			map.put("id", objs[0]);
			map.put("stage", stage);
			map.put("examCardNo", objs[1]);
			map.put("name", objs[2]);
			map.put("sex", objs[3]);
			map.put("birthday", objs[4]);
			map.put("idCardNo", objs[5]);
			map.put("nativePlace", objs[6]);
			map.put("guarDian", objs[7]);
			map.put("tel", objs[8]);
			map.put("status", objs[9]);
			map.put("checkstatus", objs[10]);
			map.put("year", year);
			list.add(map);
		}
		return new EasyData(list,pagelis.getTotalCount());
	}
	
	public String escapeChar(String str) {
		return "like '%"
				+ str.replace("%", "/%").replace("'", "''").replace("_", "/_")
				+ "%' escape '/' ";
	}

}
