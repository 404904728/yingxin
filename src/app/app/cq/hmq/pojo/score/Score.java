package app.cq.hmq.pojo.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import core.cq.hmq.annotation.Sequence;

@Entity
@Sequence(initialValue=1000)
public class Score {
	
	@Id
	private Long id;
	
	/**
	 * 考试内容
	 */
	@Column(length=80)
	private String title;
	
	/**
	 * 学号
	 */
	@Column(length=50)
	private String sno;
	
	/**
	 * 姓名
	 */
	@Column(length=20)
	private String sName;
	
	/**
	 * 班级
	 */
    @Transient
	private String sClass;
	
	/**
	 * 分数1
	 */
	private Float score1;
	
	private Float score2;
	
	private Float score3;
	
	private Float score4;
	
	private Float score5;
	
	private Float score6;
	
	private Float score7;
	
	private Float score8;
	
	private Float score9;
	
	private Float score10;
	
	/**
	 * 总分
	 */
	private Float totalScore;
	
	/**
	 * 总年级排名
	 */
	private Short gradeOrder;
	
	/**
	 * 导入人
	 */
	private Long importUser;
	
	/**
	 * 导入时间
	 */
	@Column(length=50)
	private String importDate; 
	
	/**
	 * 默认为未录取，录取状态,true 录取
	 * @return
	 */
    private Boolean lqStatus = false;
	
    @Transient
    private int stage;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsClass() {
		return sClass;
	}

	public void setsClass(String sClass) {
		this.sClass = sClass;
	}

	public Float getScore1() {
		return score1;
	}

	public void setScore1(Float score1) {
		this.score1 = score1;
	}

	public Float getScore2() {
		return score2;
	}

	public void setScore2(Float score2) {
		this.score2 = score2;
	}

	public Float getScore3() {
		return score3;
	}

	public void setScore3(Float score3) {
		this.score3 = score3;
	}

	public Float getScore4() {
		return score4;
	}

	public void setScore4(Float score4) {
		this.score4 = score4;
	}

	public Float getScore5() {
		return score5;
	}

	public void setScore5(Float score5) {
		this.score5 = score5;
	}

	public Float getScore6() {
		return score6;
	}

	public void setScore6(Float score6) {
		this.score6 = score6;
	}

	public Float getScore7() {
		return score7;
	}

	public void setScore7(Float score7) {
		this.score7 = score7;
	}

	public Float getScore8() {
		return score8;
	}

	public void setScore8(Float score8) {
		this.score8 = score8;
	}

	public Float getScore9() {
		return score9;
	}

	public void setScore9(Float score9) {
		this.score9 = score9;
	}

	public Float getScore10() {
		return score10;
	}

	public void setScore10(Float score10) {
		this.score10 = score10;
	}

	public Float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Float totalScore) {
		this.totalScore = totalScore;
	}

	public Short getGradeOrder() {
		return gradeOrder;
	}

	public void setGradeOrder(Short gradeOrder) {
		this.gradeOrder = gradeOrder;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setImportDate(String importDate) {
		this.importDate = importDate;
	}

	public String getImportDate() {
		return importDate;
	}

	public void setImportUser(Long importUser) {
		this.importUser = importUser;
	}

	public Long getImportUser() {
		return importUser;
	}

	public Boolean isLqStatus() {
		return lqStatus;
	}

	public void setLqStatus(Boolean lqStatus) {
		this.lqStatus = lqStatus;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
}
