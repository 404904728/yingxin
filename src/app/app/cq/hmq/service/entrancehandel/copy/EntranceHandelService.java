/**
 * Limit
 *
 */
package app.cq.hmq.service.entrancehandel.copy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.apache.bcel.generic.GETSTATIC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.pojo.onlineregistration.Regist;
import core.cq.hmq.dao.PageList;
import core.cq.hmq.model.AjaxMsg;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.DateUtil;
import core.cq.hmq.util.tools.StringUtil;

/**
 * @author Administrator
 *
 */
@Service
public class EntranceHandelService extends BaseService {

	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 上午9:45:34s
	 *@version 1.0
	 *@Description 查询具体一个学生的基础信息
	 *
	 *@param ksNo
	 *
	 *
	 */
	public Map<String,Object> queryStuInfo(String ksNo) {
		Map<String,Object> resMap = new HashMap<String, Object>();
		if(null == ksNo || StringUtil.isEmpty(ksNo)){
			resMap.put("flg", 0);
			return resMap;
		}
		StringBuffer sb = new StringBuffer("select case re.grade_f when 1 then '小学' when 2 then '中学' when 3 then  '高中' end as chinese,re.examcardno_f, sc.totalscore_f, " +
				"sc.gradeorder_f, re.name_f, case re.sex_f when 0 then '女' when 1 then '男' end as sex,");
		sb.append(" (select en.name_f from enumeration_t en where en.id_f = re.nation_f) as nation, ");
		sb.append(" re.idcardno_f, re.birthday_f, re.place_f, re.address_f, re.school_f, re.reason_f, re.father_f, re.fatherwork_f, re.fathertel_f,re.mother_f, ");
		sb.append(" re.motherwork_f,re.mothertel_f,re.interest_f,re.award_f,re.sex_f,re.nation_f,re.grade_f from regist_t re left join score_t sc on re.examcardno_f = sc.sno_f ");
		sb.append(" where re.examcardno_f = ?");
		List result = dao.getHelperDao().find(sb.toString(), ksNo);
		if(result.size() == 0){
			resMap.put("flg", 0);
			return resMap;
		}
		
		String gradVal = "<option value=\"无\">无</option>";
		String telVal = "<option value=\"无\">无</option>";
		Object[] obj = (Object[]) result.get(0);
		if(null == obj || obj.length == 0){
			resMap.put("flg", 0);
			return resMap;
		}
		
		//年级
		if(null != obj[0] && !StringUtil.isEmpty(obj[0])){
			resMap.put("grand", obj[0].toString());
		}else{
			resMap.put("grand", "无");
		}
		//考号
		if(null != obj[1] && !StringUtil.isEmpty(obj[1])){
			resMap.put("examNo", obj[1].toString());
		}else{
			resMap.put("examNo", "无");
		}
		//总分  是否合格
		if(null != obj[2] && !StringUtil.isEmpty(obj[2])){
			resMap.put("totalScore", obj[2].toString()+"分");
			if(Integer.parseInt(obj[2].toString()) >= 600){
				resMap.put("hege", "合格");
			}else{
				resMap.put("hege", "不合格");
			}
			
		}else{
			resMap.put("totalScore", "无");
			resMap.put("hege", "无成绩不能判断");
		}
		//名次
		if(null != obj[3] && !StringUtil.isEmpty(obj[3])){
			resMap.put("order", "第"+obj[3].toString()+"名");
		}else{
			resMap.put("order", "无");
		}
		//姓名
		if(null != obj[4] && !StringUtil.isEmpty(obj[4])){
			resMap.put("name", obj[4].toString());
		}else{
			resMap.put("name", "无");
		}
		//性别
		if(null != obj[5] && !StringUtil.isEmpty(obj[5])){
			resMap.put("sex", obj[5].toString());
		}else{
			resMap.put("sex", "无");
		}
		//民族
		if(null != obj[6] && !StringUtil.isEmpty(obj[6])){
			resMap.put("nation", obj[6].toString());
		}else{
			resMap.put("nation", "无");
		}
		//身份证号
		if(null != obj[7] && !StringUtil.isEmpty(obj[7])){
			resMap.put("idCard", obj[7].toString());
		}else{
			resMap.put("idCard", "无");
		}
		//出生日期
		if(null != obj[8] && !StringUtil.isEmpty(obj[8])){
			resMap.put("bir", obj[8].toString());
		}else{
			resMap.put("bir", "无");
		}
		
		//户口
		if(null != obj[9] && !StringUtil.isEmpty(obj[9])){
			resMap.put("place", obj[9].toString());
		}else{
			resMap.put("place", "无");
		}
		//现住址
		if(null != obj[10] && !StringUtil.isEmpty(obj[10])){
			resMap.put("address", obj[10].toString());
		}else{
			resMap.put("address", "无");
		}
		//原学校
		if(null != obj[11] && !StringUtil.isEmpty(obj[11])){
			resMap.put("School", obj[11].toString());
		}else{
			resMap.put("School", "无");
		}
		//就读本校原因
		if(null != obj[12] && !StringUtil.isEmpty(obj[12])){
			resMap.put("rea", obj[12].toString());
		}else{
			resMap.put("rea", "无");
		}
		//父亲
		if(null != obj[13] && !StringUtil.isEmpty(obj[13])){
			resMap.put("fa", obj[13].toString());
			gradVal += "<option value=\""+ obj[13].toString() +"\">"+ obj[13].toString() +"</option>";
		}else{
			resMap.put("fa", "无");
		}
		//父亲工作
		if(null != obj[14] && !StringUtil.isEmpty(obj[14])){
			resMap.put("fawo", obj[14].toString());
		}else{
			resMap.put("fawo", "无");
		}
		//父亲电话
		if(null != obj[15] && !StringUtil.isEmpty(obj[15])){
			resMap.put("faTel", obj[15].toString());
			telVal += "<option value=\""+ obj[15].toString() +"\">"+ obj[15].toString() +"</option>";
		}else{
			resMap.put("faTel", "无");
		}
		//母亲
		if(null != obj[16] && !StringUtil.isEmpty(obj[16])){
			resMap.put("ma", obj[16].toString());
			gradVal += "<option value=\""+ obj[16].toString() +"\">"+ obj[16].toString() +"</option>";
		}else{
			resMap.put("ma", "无");
		}
		//母亲工作
		if(null != obj[17] && !StringUtil.isEmpty(obj[17])){
			resMap.put("mawo", obj[17].toString());
		}else{
			resMap.put("mawo", "无");
		}
		//母亲电话
		if(null != obj[18] && !StringUtil.isEmpty(obj[18])){
			resMap.put("maTel", obj[18].toString());
			telVal += "<option value=\""+ obj[18].toString() +"\">"+ obj[18].toString() +"</option>";
		}else{
			resMap.put("maTel", "无");
		}
		//爱好
		if(null != obj[19] && !StringUtil.isEmpty(obj[19])){
			resMap.put("inter", obj[19].toString());
		}else{
			resMap.put("inter", "无");
		}
		//获奖
		if(null != obj[20] && !StringUtil.isEmpty(obj[20])){
			resMap.put("aw", obj[20].toString());
		}else{
			resMap.put("aw", "无");
		}
		//性别(数据库)
		if(null != obj[21] && !StringUtil.isEmpty(obj[21])){
			resMap.put("datasex", obj[21].toString());
		}else{
			resMap.put("datasex", "0");
		}
		//民族(数据库)
//		if(null != obj[22] && !"".equals(obj[22])){
//			resMap.put("datanation", obj[22].toString());
//		}else{
//			resMap.put("datanation", "1");
//		}
		//拟读年级(数据库)
		if(null != obj[23] && !StringUtil.isEmpty(obj[23])){
			resMap.put("datagrand", obj[23].toString());
		}else{
			resMap.put("datagrand", "1");
		}
		resMap.put("gradVal", gradVal);
		resMap.put("telVal", telVal);
		resMap.put("flg", 1);
		return resMap;
	}

	
	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-16 下午3:40:00
	 *@version 1.0
	 *@Description 返回查询出的学生信息
	 *
	 *@param model
	 *@param regist
	 * @param endTime 
	 * @param startTime 
	 *@return
	 *
	 *
	 */
	@SuppressWarnings("unchecked")
	public EasyData<EntranceHandle> finddata(PageModel model, EntranceHandle regist, String startTime, String endTime) {
		EasyData<EntranceHandle> data = new EasyData<EntranceHandle>();
		PageList<EntranceHandle> pagelis = null;
		StringBuffer sb = new StringBuffer(" where 1=1 ");
		if(null != regist){
			//性别
			if(null != regist.getSex() && !StringUtil.isEmpty(regist.getSex())){
				sb.append(" and sex = " + regist.getSex());
			}
			//拟读年级
			if(null != regist.getStage() && !StringUtil.isEmpty(regist.getStage())){
				sb.append(" and stage = " + regist.getStage());
			}
			//姓名
			if(null != regist.getName() && !StringUtil.isEmpty(regist.getName())){
				sb.append(" and name like '%" + regist.getName() + "%' ");
			}
			//姓名
			if(null != regist.getExamCardNo() && !StringUtil.isEmpty(regist.getExamCardNo())){
				sb.append(" and examCardNo like '%" + regist.getExamCardNo() + "%' ");
			}
		}
		//开始时间
		if(null != startTime && !StringUtil.isEmpty(startTime)){
			sb.append(" and createTime > '" + startTime + "'");
		}
		//结束时间
		if(null != endTime && !StringUtil.isEmpty(endTime)){
			sb.append(" and createTime < '" + endTime + "'");
		}
		sb.append(" order by createTime,stage desc");
		pagelis = page(model, "from " + EntranceHandle.class.getName()
				+ sb.toString());
		if(pagelis.size() == 0){
			return data;
		}
		return new EasyData<EntranceHandle>(pagelis);
	}


	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-17 下午5:22:55
	 *@version 1.0
	 *@Description 验证准考证号是否存在
	 *
	 *@param examCardNo
	 *@return
	 *
	 *
	 */
	public String isExisStu(String examCardNo) {
		if(null == examCardNo || "".equals(examCardNo)){
			return "2";
		}
		List<EntranceHandle> list = dao.find(EntranceHandle.class,"examCardNo",examCardNo);
		if(list.size() == 0){
			return "1";
		}else{
			return "0";
		}
	}

	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-17 下午5:32:32
	 *@version 1.0
	 *@Description 将学生报到数据提交到数据库
	 *
	 *@param eh
	 *@return
	 *
	 *
	 */
	@Transactional
	public String startBaoDao(EntranceHandle eh) {
		System.out.println(eh);
		if(null == eh){
			return "0";
		}
		//判断准考证号是否是空
		if(null == eh.getExamCardNo() || StringUtil.isEmpty(eh.getExamCardNo())){
			return "0";
		}
		//身份证号
		if(null == eh.getIdCardNo() || StringUtil.isEmpty(eh.getIdCardNo())){
			return "0";
		}
		//名字
		if(null == eh.getName() || StringUtil.isEmpty(eh.getName())){
			return "0";
		}
		//拟读年级
		if(StringUtil.isEmpty(eh.getName())){
			return "0";
		}
		//拟读年级
		if(null == eh.getYear() || StringUtil.isEmpty(eh.getYear())){
			return "0";
		}
		dao.insert(eh);
		return "1";
	}


	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-18 下午12:15:02
	 *@version 1.0
	 *@Description 查询要修改的修生信息
	 *
	 *@param id
	 *@return
	 *
	 *
	 */
	public EntranceHandle queryToUpdate(Long id) {
		return dao.findOne(EntranceHandle.class, "id",id);
	}


	/**
	 * 
	 *@title
	 *@author Limit
	 *@date 2014-6-19 上午9:30:13
	 *@version 1.0
	 *@Description 将招生办修改的数据保存到数据库
	 *
	 *@param eh
	 *@return
	 *
	 *
	 */
	@Transactional
	public AjaxMsg updateStudent(EntranceHandle eh) {
		AjaxMsg msg = new AjaxMsg();
		EntranceHandle handle = dao.findOne(EntranceHandle.class, "id", eh.getId());
		if(1 == handle.getIsPay()){
			msg.setMsg("该生已经缴费完成,无法在进行修改操作,如有需要请联系管理员操作!");
			return msg;
		}
		handle.setName(eh.getName());
		handle.setSex(eh.getSex());
		handle.setBirthday(eh.getBirthday());
		handle.setIdCardNo(eh.getIdCardNo());
		handle.setNativePlace(eh.getNativePlace());
		handle.setAddress(eh.getAddress());
		handle.setGuarDian(eh.getGuarDian());
		handle.setTel(eh.getTel());
		handle.setCreateTime(eh.getCreateTime());
		handle.setIsTeacherChild(eh.getIsTeacherChild());
		handle.setScholarShip(eh.getScholarShip());
		handle.setYear(eh.getYear());
		dao.update(handle);
		msg.setMsg("修改成功");
		return msg;
	}
	
}
