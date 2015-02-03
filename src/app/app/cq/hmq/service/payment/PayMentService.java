package app.cq.hmq.service.payment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.StringUtil;

@Service
public class PayMentService extends BaseService{

	public EasyData<EntranceHandle> findData(PageModel model,
			EntranceHandle condition) {
		PageList<EntranceHandle> pagelis = null;
		StringBuffer sb = new StringBuffer(" from EntranceHandle where 1=1 ");
		if(null != condition){
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
	
}
