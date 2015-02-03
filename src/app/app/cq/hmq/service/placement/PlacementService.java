package app.cq.hmq.service.placement;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.DateUtil;
import core.cq.hmq.util.tools.StringUtil;

@Service
public class PlacementService extends BaseService {

	@SuppressWarnings("unchecked")
	public EasyData<Map<String, Object>> find(PageModel model,
			EntranceHandle handle) {
		String sql = "";
		if (StringUtil.isEmpty(sql)) {
			sql = "select e.id_f,e.name_f,e.birthday_f,e.sex_f,e.idCardNo_f,"
					+ "e.examCardNo_f,e.createTime_f,e.stage_f,e.isFenBan_f,"
					+ "s.totalScore_f,s.gradeOrder_f "
					+ "from  EntranceHandle_t e,Score_t s where e.examCardNo_f=s.sno_f ";
		} else {
			sql = "select e.id_f,e.name_f,e.birthday_f,e.sex_f,e.idCardNo_f,"
					+ "e.examCardNo_f,e.stage_f,e.createTime_f,e.isFenBan_f,"
					+ "s.totalScore_f,s.gradeOrder_f "
					+ "from  EntranceHandle_t e,Score_t s where e.examCardNo_f=s.sno_f "
					+ sql;
		}
		sql += " and e.createTime_f like '" + DateUtil.getYear(new Date())
				+ "%' ";
		if (StringUtil.isEmpty(model.getSort())
				|| StringUtil.isEmpty(model.getOrder())) {
			sql += " order by e.createTime_f desc";
		} else {
			sql += " order by " + model.getSort() + " " + model.getOrder();
		}
		PageList<Object[]> handles = dao.getHelperDao().page(model.getPage(),
				model.getRows(), sql);
		List<Map<String, Object>> list = new ArrayList<>();
		for (Object[] object : handles.getList()) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("e.id_f", object[0]);
			map.put("e.name_f", object[1]);
			map.put("e.birthday_f", object[2]);
			map.put("e.sex_f", object[3]);
			map.put("e.idCarNo_f", object[4]);
			map.put("e.examCardNo_f", object[5]);
			map.put("e.createTime_f", object[6]);
			map.put("e.grade_f", object[7]);
			map.put("e.isFenBan_f", object[8]);
			map.put("s.totalScore_f", object[9]);
			map.put("s.gradeOrder_f", object[10]);
			list.add(map);
		}
		EasyData<Map<String, Object>> ed = new EasyData<>();
		ed.setRows(list);
		ed.setTotal(handles.getTotalCount());
		
		List<Map<String, Object>> listfooter = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("e.examCardNo_f", "总人数：");
		map.put("e.createTime_f", handles.getTotalCount()+"人");
		listfooter.add(map);
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("e.examCardNo_f", "男生：");
		map1.put("e.createTime_f", "200人");
		listfooter.add(map1);
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("e.examCardNo_f", "女生：");
		map2.put("e.createTime_f", "150人");
		listfooter.add(map2);
		ed.setFooter(listfooter);
		
		return ed;
	}

	public List<Object[]> divideintoclasses() {
		String sql = "select e.id_f,e.name_f,e.sex_f,e.examCardNo_f,e.stage_f,e.createTime_f,"
				+ "s.totalScore_f,s.gradeOrder_f "
				+ "from  EntranceHandle_t e,Score_t s where e.examCardNo_f=s.sno_f and e.isFenBan_f=0 and e.stage_f=?";
		List<Object[]> list = dao.getHelperDao().find(sql, 3);// 2初中 ，3高中

		return list;

	}

}
