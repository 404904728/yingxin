package app.cq.hmq.pojo.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence(initialValue=1000)
public class ScoreSubjectMapping {
	
	@Id
	private Long id;
	
	/**
	 * 对应科目
	 */
	@ManyToOne
	private SubjectInfo subject;
	
	/**
	 * 对应分数表中的列名
	 */
	@Column(length=10)
	private String colName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubjectInfo getSubject() {
		return subject;
	}

	public void setSubject(SubjectInfo subject) {
		this.subject = subject;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
	
}
