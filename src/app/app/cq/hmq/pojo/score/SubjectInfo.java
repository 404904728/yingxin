package app.cq.hmq.pojo.score;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import core.cq.hmq.annotation.Sequence;

/**
 * 
 * @author Administrator
 * 
 */
@Entity
@Sequence(initialValue=1000)
public class SubjectInfo {

	@Id
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 科目名称
	 */
	@Column(unique = true)
	private String name;

	/**
	 * 科目描述
	 */
	private String desc;

	/**
	 * 创建时间
	 */
	private Date createDate;

	/**
	 * 创建人
	 */
	private Long createPerson;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}

}
