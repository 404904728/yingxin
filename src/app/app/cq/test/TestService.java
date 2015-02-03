package app.cq.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import common.cq.hmq.pojo.Menu;
import common.cq.hmq.service.OrgService;

import core.cq.hmq.dao.util.BeanUtil;
import core.cq.hmq.service.BaseTestService;
import core.cq.hmq.service.SqlQueryService;
import core.cq.hmq.util.tools.JSONDeBug;

public class TestService extends BaseTestService {

	@Autowired
	private SqlQueryService sqlQueryService;

	public void test() throws SQLException {
		List<Map<String, Object>> list = sqlQueryService
				.searchColumnName("select * from Menu_t");
		JSONDeBug.vaildJson(list);

	}

	@Test
	public void test1() {
		List<Object> l= dao.getHelperDao().find("select count(*) from Regist_t r where r.date>'2014-01-01'");
		vaildJson(l);
	}
}
