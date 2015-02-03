package app.cq.hmq.pojo.teacheck;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import common.cq.hmq.pojo.Org;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence(initialValue=1000)
public class TeacherCheck {
	@Id
	private Long id;
	
	/** 1未到 2多到 */
	private int type = 1;
	
	/** 未到的学生id */
	private Long stuId;
	
	/**多到的学生姓名 */
	private String addName;
	/**多到的学生考号 */
	private String examcardNo;
	
	/** 确认班级 */
	@ManyToOne
	private Org org;
	
	/** 阶段 1小 2初 3高  */
	private Integer stage;

	/** 录入年份 */
	private int year;
	
	private Integer submitStatus = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getAddName() {
		return addName;
	}

	public void setAddName(String addName) {
		this.addName = addName;
	}

	public String getExamcardNo() {
		return examcardNo;
	}

	public void setExamcardNo(String examcardNo) {
		this.examcardNo = examcardNo;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public Integer getSubmitStatus() {
		return submitStatus;
	}

	public void setSubmitStatus(Integer submitStatus) {
		this.submitStatus = submitStatus;
	}
	
}
