package common.cq.hmq.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence
public class Org {
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
	private User leader;

	/** 排序 */
	private Integer order;

	@ManyToOne
	private Org parent;

	/** 是否可用 */
	private Boolean status = true;
	
	/** 联系方式 */
	private String tel;
	
	/**
	 * 1 阶段
	 * 2年级
	 * 3班级 
	 */
	private Integer type;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getLeader() {
		return leader;
	}

	public void setLeader(User leader) {
		this.leader = leader;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Org getParent() {
		return parent;
	}

	public void setParent(Org parent) {
		this.parent = parent;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
