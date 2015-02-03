package app.cq.hmq.pojo.onlineregistration;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.format.annotation.DateTimeFormat;

import core.cq.hmq.annotation.Sequence;
import core.cq.hmq.pojo.Enumeration;
import core.cq.hmq.service.tools.JsonDateSerializer;

@Entity
@Sequence
public class Regist {

	@Id
	private Long id;

	/** 学生姓名 */
	private String name;

	/** 学生性别 1：男 0 ：女 */
	private Integer sex;

	/** 出生年月 */
	private String birthday;

	/** 身份证号 */
	private String idcardno;

	/** 准考证号 */
	private String examcardno;

	/** 民族 */
	@ManyToOne
	private Enumeration nation = new Enumeration();

	/** 户口所在地 */
	private String place;

	/** 现住址（精确到门牌号） */
	private String address;

	/** 现就读学校 */
	private String school;

	/** 拟读年级 1：小学2 初中 3高中 */
	private Integer grade;

	/** 父亲名字 */
	private String father;

	/** 父亲工作单位 */
	private String fatherwork;

	/** 父亲联系电话 */
	private String fathertel;

	/** 母亲名字 */
	private String mother;

	/** 母亲工作单位 */
	private String motherwork;

	/** 母亲联系电话 */
	private String mothertel;

	/** 兴趣爱号 */
	@Column(length = 500)
	private String interest;

	/** 获奖情况 */
	@Column(length = 500)
	private String award;

	/** 报读我校原因 */
	private String reason;

	/** 信息来源 0：亲友介绍 1：老师推荐 2：慕名报读 */
	private Integer origin;

	/** 填报时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(String idcardno) {
		this.idcardno = idcardno;
	}

	public String getExamcardno() {
		return examcardno;
	}

	public void setExamcardno(String examcardno) {
		this.examcardno = examcardno;
	}

	public Enumeration getNation() {
		return nation;
	}

	public void setNation(Enumeration nation) {
		this.nation = nation;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getFatherwork() {
		return fatherwork;
	}

	public void setFatherwork(String fatherwork) {
		this.fatherwork = fatherwork;
	}

	public String getFathertel() {
		return fathertel;
	}

	public void setFathertel(String fathertel) {
		this.fathertel = fathertel;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getMotherwork() {
		return motherwork;
	}

	public void setMotherwork(String motherwork) {
		this.motherwork = motherwork;
	}

	public String getMothertel() {
		return mothertel;
	}

	public void setMothertel(String mothertel) {
		this.mothertel = mothertel;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}

	@JsonSerialize(using = JsonDateSerializer.class)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
