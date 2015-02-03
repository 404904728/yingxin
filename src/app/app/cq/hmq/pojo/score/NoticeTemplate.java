package app.cq.hmq.pojo.score;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence(initialValue=1000)
public class NoticeTemplate {
	
	@Id
	private Long id;
	
	/**
	 * 1 通知模版 2 录取模版
	 */
	private int type = 1;
	
	@Column(length=500)
	private String content;
	
	private Long createUserId;
	
	private Date createDate;

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

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
