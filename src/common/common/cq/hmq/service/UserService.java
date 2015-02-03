package common.cq.hmq.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import common.cq.hmq.pojo.User;

import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.model.SessionModel;
import core.cq.hmq.pojo.Role2Permission;
import core.cq.hmq.pojo.User2Role;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.Encrypt;
import core.cq.hmq.util.tools.ResourceUtil;
import core.cq.hmq.util.tools.SessionUtil;
import core.cq.hmq.util.tools.StringUtil;

@Service(value = "userService")
public class UserService extends BaseService {

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	@Transactional()
	public AjaxMsg insert(User user) {
		User dbUser = dao.findOne(User.class, "no", user.getNo());
		AjaxMsg am = new AjaxMsg();
		if (dbUser != null) {
			am.setMsg("账号已经存在");
			am.setType(am.ERROR);
			return am;
		} else {
			user.setTel(user.getNo());
			user.setPwd(Encrypt.md5(user.getPwd()));
			dao.insert(user);
			am.setMsg("注册成功");
			return am;
		}
	}

	/**
	 * 更新
	 * 
	 * @param user
	 */
	public AjaxMsg update(User user) {
		AjaxMsg am = new AjaxMsg();
		if (validNo(user.getNo()) == 0 || validNo(user.getNo()) != user.getId()) {
			User dbUser = dao.findOne(User.class, "id", user.getId());
			try {
				dbUser.setAddress(user.getAddress());
				dbUser.setAge(user.getAge());
				dbUser.setEmail(user.getEmail());
				dbUser.setHead(user.getHead());
				dbUser.setIdentity(user.getIdentity());
				dbUser.setName(user.getName());
				dbUser.setNickName(user.getNickName());
				dbUser.setNo(user.getNo());
				dbUser.setProfession(user.getProfession());
				dbUser.setSex(user.getSex());
				dao.update(dbUser);
				am.setMsg("更新成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				am.setMsg("更新失败");
				am.setType(am.ERROR);
			}
		} else {
			am.setMsg("您输入的电话号码已经存在，请重新输入");
			am.setType(am.ERROR);
		}
		return am;
	}

	/**
	 * 验证账号是否存在
	 * 
	 * @param no
	 * @return
	 */
	public Long validNo(String no) {
		User dbUser = dao.findOne(User.class, "no", no);
		AjaxMsg am = new AjaxMsg();
		if (dbUser != null) {
			return dbUser.getId();
		} else {
			return (long) 0;
		}
	}

	public AjaxMsg logon(User user, HttpSession session) {
		User dbUser = dao.findOne(User.class, "no", user.getNo());
		AjaxMsg am = new AjaxMsg();
		if (dbUser == null) {
			am.setType(AjaxMsg.ERROR);
			am.setMsg("帐号不存在！");
			return am;
		} else {
			if (!dbUser.getPwd().equals(Encrypt.md5(user.getPwd()))) {
				am.setType(AjaxMsg.ERROR);
				am.setMsg("密码错误！");
				return am;
			}
			SessionUtil.saveSession(dbUser, session);
			dbUser.setDate(new Date());
			savePerByUserId(session, dbUser.getId());
			return am;
		}
	}

	public AjaxMsg logonImgAuth(User user, HttpSession session, String authImg) {
		AjaxMsg am = new AjaxMsg();
		if (!session.getAttribute("AUTH_IMG_IN_SESSION").equals(authImg)) {
			am.setType(AjaxMsg.ERROR);
			am.setMsg("验证码错误！");
			return am;
		}
		User dbUser = dao.findOne(User.class, "no", user.getNo());
		if (dbUser == null) {
			am.setType(AjaxMsg.ERROR);
			am.setMsg("帐号不存在！");
			return am;
		} else {
			if (!dbUser.getPwd().equals(Encrypt.md5(user.getPwd()))) {
				am.setType(AjaxMsg.ERROR);
				am.setMsg("密码错误！");
				return am;
			}
			SessionUtil.saveSession(dbUser, session);
			dbUser.setDate(new Date());
			savePerByUserId(session, dbUser.getId());
			am.setMsg("登录成功");
			return am;
		}
	}

	/**
	 * 判断是否登录
	 * 
	 * @param session
	 * @return
	 */
	public boolean isLogon(HttpSession session) {
		SessionModel sModal = (SessionModel) session.getAttribute(ResourceUtil
				.getSessionInfoName());
		if (sModal != null) {
			if (sModal.getSessionId() != null) {
				return true;
			}
		}
		return false;
	}

	public boolean isNull() {
		return dao.findOne(User.class) == null ? true : false;
	}

	public User findUserById(Long id) {
		return dao.findOne(User.class, "id", id);
	}

	/**
	 * 获取用户的所有权限，并保存到session中
	 * 
	 * @param uid
	 * @return
	 */
	public void savePerByUserId(HttpSession session, Long uid) {
		Set<String> setPer = findPerByUserId(uid);
		session.setAttribute(ResourceUtil.getPermissionCookie(), setPer);
	}

	/**
	 * 获取用户的所有权限
	 * 
	 * @param uId
	 * @return
	 */
	public Set<String> findPerByUserId(Long uId) {
		Set<String> setPer = new HashSet<String>();
		List<User2Role> u2rs = dao.find(User2Role.class, "user.id", uId);
		for (User2Role user2Role : u2rs) {
			List<Role2Permission> r2ps = dao.find(Role2Permission.class,
					"role.id", user2Role.getRole().getId());
			for (Role2Permission role2Permission : r2ps) {
				setPer.add(role2Permission.getPermission().getId() + "");
			}
		}
		if (setPer.size() == 0) {
			setPer.add("-1");
		}
		return setPer;
	}

	/**
	 * 该角色是否有该权限 有时间再做缓存(已把权限做到session中)
	 * 
	 * @deprecated
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean canPermissionByUserId(Long uid, Long pid) {
		List<Role2Permission> r2ps = dao.find(Role2Permission.class,
				"permission.id", pid);
		List<User2Role> u2rs = dao.find(User2Role.class, "user.id", uid);
		for (User2Role u2r : u2rs) {
			for (Role2Permission r2p : r2ps) {
				if (r2p.getRole().getId() == u2r.getRole().getId()) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 密码修改
	 * 
	 * @param id
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	public AjaxMsg pwdChange(Long id, String oldPwd, String pwd) {
		User user = dao.findOne(User.class, "id", id);
		AjaxMsg am = new AjaxMsg();
		if (user.getPwd().equals(Encrypt.md5(oldPwd))) {
			user.setPwd(Encrypt.md5(pwd));
			try {
				dao.update(user);
				am.setMsg("密码更新成功");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				am.setMsg("密码更新失败");
				am.setType(am.ERROR);
			}
		} else {
			am.setMsg("历史密码输入不正确");
			am.setType(am.ERROR);
		}
		return am;
	}

	/**
	 * 根据角色id查找该角色下的用户
	 * 
	 * @param id
	 * @return
	 */
	public List<User> findUserByRoleId(Long id) {
		List<User> users = new ArrayList<User>();
		List<User2Role> u2r = dao.find(User2Role.class, "role.id", id);
		for (User2Role user2Role : u2r) {
			users.add(user2Role.getUser());
		}
		return users;
	}

	/** 根据当前用户ID，查询当前用户的部门负责人 */
	public Long findLeaderByCurrentUserId() {
		User dbUser = findUserById(currentUserId());
		if (dbUser == null) {
			return null;
		} else if (dbUser.getOrg() == null) {
			return null;
		} else if (dbUser.getOrg().getLeader() == null) {
			return null;
		} else {
			return dbUser.getOrg().getLeader().getId();
		}
	}

	/**
	 * 查找用户
	 * 
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public EasyData<User> findUser(PageModel model, User user) {
		PageList<User> users = null;
		if (user == null) {
			users = page(model, User.class);
		} else {
			StringBuffer sb = new StringBuffer();
			if (!StringUtil.isEmpty(user.getNo())) {
				sb.append(" no like '%" + user.getNo() + "%' and");
			}
			if (!StringUtil.isEmpty(user.getName())) {
				sb.append(" name like '%" + user.getName() + "%' and");
			}
			if (user.getSex() != null) {
				sb.append(" sex=" + user.getSex() + " and");
			}
			String sql = sb.substring(0, sb.lastIndexOf("and"));
			if (!StringUtil.isEmpty(model.getOrder())
					&& !StringUtil.isEmpty(model.getSort())) {
				sql += " order by " + model.getSort() + " " + model.getOrder();
			}
			users = page(model, "from " + User.class.getName() + " where "
					+ sql);
		}
		return new EasyData<User>(users);
	}
}
