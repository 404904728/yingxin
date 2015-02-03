package app.cq.hmq.service.onlineregistration;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.cq.hmq.pojo.onlineregistration.Regist;
import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.QuerySQLUtil;
import core.cq.hmq.util.tools.DateUtil;
import core.cq.hmq.util.tools.StringUtil;

/**
 * 网上报名业务层
 * 
 * @author 何建
 * 
 */
@Service(value = "onlineregistService")
public class OnlineregistService extends BaseService {

	/**
	 * 数据查询
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EasyData<Regist> finddata(PageModel model, Regist regist) {
		String hql = QuerySQLUtil.hql(regist);
		if (StringUtil.isEmpty(hql)) {
			hql = "from Regist";
		} else {
			hql = "from Regist where " + hql;
		}
		if (!StringUtil.isEmpty(model.getSort())) {
			hql += " order by " + model.getSort() + " " + model.getOrder();
		} else {
			hql += " order by date desc";
		}
		PageList<Regist> pagelis = page(model, hql);
		return new EasyData<Regist>(pagelis);
	}

	@Transactional
	public String save(Regist regist) {
		if (regist.getDate() == null) {
			regist.setDate(new Date());
		}
		String d = DateUtil.format(regist.getDate(),
				DateUtil.COMPACT_DATE_PATTERN).substring(0, 4);
		int total = Integer
				.parseInt(dao
						.getHelperDao()
						.find("select count(*) from Regist_t r where to_char(r.date_f,'yyyy')=?",
								d).get(0).toString()) + 1;
		int length = (total + "").length();
		String no = "";
		switch (length) {
		case 1:
			no = "000" + total;
			break;
		case 2:
			no = "00" + total;
			break;
		case 3:
			no = "0" + total;
			break;
		default:
			no = total + "";
			break;
		}
		regist.setExamcardno(regist.getGrade() + d + no);
		dao.insert(regist);
		return regist.getExamcardno();
	}

	@Transactional
	public void update(Regist regist) {
		Regist db = dao.load(Regist.class, regist.getId());
		BeanUtils.copyProperties(regist, db, "id", "date");
		dao.update(db);
	}

	public Regist findById(Long id) {
		return dao.findOne(Regist.class, "id", id);
	}

	/**
	 * 验证身份证是否重复
	 * 
	 * @param card
	 * @return
	 */
	public boolean validcard(String card) {
		return dao.findOne(Regist.class, "idcardno", card) == null ? true
				: false;
	}
}
