package common.cq.hmq.pojo;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import core.cq.hmq.annotation.Sequence;
import core.cq.hmq.pojo.Permission;

@Entity
@Sequence(initialValue = 10000)
public class Menu {

	@Id
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private String name;
	@ManyToOne
	private Menu pid;
	private String url;
	@ManyToOne
	private Permission permission;

	/** 是否可用 */
	private Boolean use = true;
	/** 菜单图标 */
	private String icon;

	/** 流程ID,用于前台配置 */
	private String proId;

	/** 排序 */
	private String order;

	public Boolean getUse() {
		return use;
	}

	public void setUse(Boolean use) {
		this.use = use;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Menu getPid() {
		return pid;
	}

	public void setPid(Menu pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Permission getPermission() {
		return permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public static String cls() {
		return Menu.class.getName();
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	// 临时属性，用于存放儿子菜单
	@Transient
	private List<Menu> child;

	public List<Menu> getChild() {
		return child;
	}

	public void setChild(List<Menu> child) {
		this.child = child;
	}

}
