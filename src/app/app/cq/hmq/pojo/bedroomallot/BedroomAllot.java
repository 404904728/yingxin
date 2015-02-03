/**
 * Limit
 *
 */
package app.cq.hmq.pojo.bedroomallot;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import core.cq.hmq.annotation.Sequence;

/**
 * @author Administrator
 *
 */
@Entity
@Sequence
public class BedroomAllot {
	
	@Id
	private Long id;
	
	//房间号、楼号、楼层
	private String name;
	
	//使用状态 1：停用 0 ：使用
	private int userStatus;
	
	
	@ManyToOne
	private BedroomAllot parent;
	
	//使用类型 1: 寝室楼 2：楼层 3 房间
	private int type;
	
	//男女生寝室 1：男 0 ：女
	private int gender;
	
	//生活老师
	private Long lifeTeacher;
	
	//其实居住总人数
	private int liveSum;
	
	//已住人数
	private int alreadyLive=0;
	
	//已住学生信息
	private String stuId;

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

	public int getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(int userStatus) {
		this.userStatus = userStatus;
	}

	public BedroomAllot getParent() {
		return parent;
	}

	public void setParent(BedroomAllot parent) {
		this.parent = parent;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Long getLifeTeacher() {
		return lifeTeacher;
	}

	public void setLifeTeacher(Long lifeTeacher) {
		this.lifeTeacher = lifeTeacher;
	}

	public int getLiveSum() {
		return liveSum;
	}

	public void setLiveSum(int liveSum) {
		this.liveSum = liveSum;
	}

	public int getAlreadyLive() {
		return alreadyLive;
	}

	public void setAlreadyLive(int alreadyLive) {
		this.alreadyLive = alreadyLive;
	}

	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	
	
}
