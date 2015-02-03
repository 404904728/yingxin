package app.cq.hmq.pojo.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence(initialValue=1000)
public class ScoreContentMapping {
	@Id
	private Long id;
	
	@Column(length=80)
	private String title;
	
	@Column(length=10)
	private String colName;
	
	@Column(length=200)
	private String contentData;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}

	public String getContentData() {
		return contentData;
	}

	public void setContentData(String contentData) {
		this.contentData = contentData;
	}
	
}
