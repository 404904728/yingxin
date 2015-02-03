package common.cq.hmq.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.cq.hmq.pojo.Org;
import common.cq.hmq.pojo.User;

import core.cq.hmq.service.BaseService;

@Service(value = "orgService")
public class OrgService extends BaseService {

	@Resource
	private UserService userService;

	/**
	 * 新增
	 * 
	 * @param org
	 */
	@Transactional
	public void insert(Org org) {
		dao.insert(org);
	}

	/**
	 * id：节点id，对载入远程数据很重要。 text：显示在节点的文本。 state：节点状态，'open' or
	 * 'closed'，默认为'open'。当设置为'closed'时，拥有子节点的节点将会从远程站点载入它们。 checked：表明节点是否被选择。
	 * attributes：可以为节点添加的自定义属性。 children：子节点，必须用数组定义。
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findOrgs() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String hql = "from " + Org.class.getName()
				+ " o where o.status=1 and o.parent is null order by o.order";
		List<Org> lOrg = dao.find(hql);
		for (Org org : lOrg) {
			Map map = new HashMap();
			map.put("id", "org:" + org.getId());
			map.put("text", org.getName());
			map.put("state", "closed");
			map.put("iconCls", "icon-bmp-17");
			// map.put("children", "[]");spring mvc 有这行返回会出错
			list.add(map);
		}
		return list;
	}

	/**
	 * 查找儿子部门
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Map<String, String>> findChildOrg(String id) {
		String[] ids = id.split(":");
		Long orgId = Long.parseLong(ids[1]);
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String hql = "from " + Org.class.getName()
				+ " o where o.status=1 and o.parent.id=? order by o.order";
		List<Org> lOrg = dao.find(hql, orgId);
		for (Org org : lOrg) {
			Map map = new HashMap();
			map.put("id", "org:" + org.getId());
			map.put("text", org.getName());
			map.put("state", "closed");
			map.put("iconCls", "icon-org");
			list.add(map);
		}
		String hqlUser = "from " + User.class.getName() + " u where u.org.id=?";
		List<User> lUser = dao.find(hqlUser, orgId);
		for (User user : lUser) {
			Map map = new HashMap();
			map.put("id", "user:" + user.getId());
			map.put("text", user.getName());
			map.put("state", "open");
			list.add(map);
		}
		return list;
	}

}
