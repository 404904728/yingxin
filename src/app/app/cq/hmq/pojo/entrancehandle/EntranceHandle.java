/**
 * Limit
 *
 */
package app.cq.hmq.pojo.entrancehandle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import core.cq.hmq.annotation.Sequence;

/**
 * @author Administrator
 * 入学办理
 */
@Entity
@Sequence
public class EntranceHandle {
	
	//id
	@Id
	protected Long id;
	
	//身份证号码
	@Column(length = 50)
	private String idCardNo;
	
	//准考证号码
	@Column(length = 15)
	private String examCardNo;
	
	//姓名
	@Column(length = 50)
	private String name;
	
	//入学阶段
	private Integer stage;
	
	//籍贯
	@Column(length = 200)
	private String nativePlace;
	
	//性别 1:男 0 ：女
	private Integer sex;
	
	//出生日期
	@Column(length = 12)
	private String birthday;
	
	//监护人电话
	@Column(length = 15)
	private String tel;
	
	//监护人
	private String guarDian;
	
	//现住址
	@Column(length = 30)
	private String address;
	
	//头像
	private Long headpic;
	
	//入学年
	private Integer year;
	
	//班级
	private Long org;
	
	//对应的寝室
	private Long dormitory;
	
	//报名时间
	@Column(length = 12)
	private String createTime;
	
	//教师子女 0:不是 1：是
	private int isTeacherChild;
	
	//奖学金  0: 无 1:1等 2:2等 3：3等 4:4等
	private int scholarShip;
	
	//特殊情况 0: 不是  1: 是
	private String specialApprove;
	
	//是否已经缴费 0 : 未缴费  1： 缴费
	private int isPay;
	
	//缴费金额
	private Float paySum;
	
	//是否已经分班 0: 未分班 1：已分班
	private int isFenBan;
	
	//是否分寝室 0: 未分 1：已分
	private int isFenQS;
	
	//是否退、转学 0: 未退 1：已退
	private int isOutSchool;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getExamCardNo() {
		return examCardNo;
	}

	public void setExamCardNo(String examCardNo) {
		this.examCardNo = examCardNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStage() {
		return stage;
	}

	public void setStage(Integer stage) {
		this.stage = stage;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
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

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getGuarDian() {
		return guarDian;
	}

	public void setGuarDian(String guarDian) {
		this.guarDian = guarDian;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getHeadpic() {
		return headpic;
	}

	public void setHeadpic(Long headpic) {
		this.headpic = headpic;
	}


	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getOrg() {
		return org;
	}

	public void setOrg(Long org) {
		this.org = org;
	}

	public Long getDormitory() {
		return dormitory;
	}

	public void setDormitory(Long dormitory) {
		this.dormitory = dormitory;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getIsTeacherChild() {
		return isTeacherChild;
	}

	public void setIsTeacherChild(int isTeacherChild) {
		this.isTeacherChild = isTeacherChild;
	}

	public int getScholarShip() {
		return scholarShip;
	}

	public void setScholarShip(int scholarShip) {
		this.scholarShip = scholarShip;
	}

	public String getSpecialApprove() {
		return specialApprove;
	}

	public void setSpecialApprove(String specialApprove) {
		this.specialApprove = specialApprove;
	}

	
	public int getIsPay() {
		return isPay;
	}

	public void setIsPay(int isPay) {
		this.isPay = isPay;
	}

	public Float getPaySum() {
		return paySum;
	}

	public void setPaySum(Float paySum) {
		this.paySum = paySum;
	}

	public int getIsFenBan() {
		return isFenBan;
	}

	public void setIsFenBan(int isFenBan) {
		this.isFenBan = isFenBan;
	}

	public int getIsFenQS() {
		return isFenQS;
	}

	public void setIsFenQS(int isFenQS) {
		this.isFenQS = isFenQS;
	}

	public int getIsOutSchool() {
		return isOutSchool;
	}

	public void setIsOutSchool(int isOutSchool) {
		this.isOutSchool = isOutSchool;
	}

	@Override
	public String toString() {
		return "EntranceHandle [id=" + id + ", idCardNo=" + idCardNo
				+ ", examCardNo=" + examCardNo + ", name=" + name + ", stage="
				+ stage + ", nativePlace=" + nativePlace + ", sex=" + sex
				+ ", birthday=" + birthday + ", tel=" + tel + ", guarDian="
				+ guarDian + ", address=" + address + ", headpic=" + headpic
				+ ", year=" + year + ", org=" + org + ", dormitory="
				+ dormitory + ", createTime=" + createTime
				+ ", isTeacherChild=" + isTeacherChild + ", scholarShip="
				+ scholarShip + ", specialApprove=" + specialApprove
				+ ", isPay=" + isPay + ", paySum=" + paySum + ", isFenBan="
				+ isFenBan + ", isFenQS=" + isFenQS + ", isOutSchool="
				+ isOutSchool + "]";
	}
	
	
}
