package app.cq.hmq.pojo.score;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import core.cq.hmq.annotation.Sequence;

/**
 * 
 * @author Administrator
 *
 */
@Entity
@Sequence(initialValue=1000)
public class SubjectMapping{
	
	@Id
	private Long id;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 对应科目
	 */
	@ManyToOne
	private SubjectInfo subjectInfo;

	/**
	 * 对应阶段 1小学 2 初中 3高中
	 */
	private Integer stage;
	
	public SubjectInfo getSubjectInfo() {
		return subjectInfo;
	}

	public void setSubjectInfo(SubjectInfo subjectInfo) {
		this.subjectInfo = subjectInfo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public int getStage() {
		return stage;
	}

}
