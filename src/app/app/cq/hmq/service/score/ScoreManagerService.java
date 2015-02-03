package app.cq.hmq.service.score;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import app.cq.hmq.pojo.score.NoticeTemplate;
import app.cq.hmq.pojo.score.Score;

import common.cq.hmq.util.SendSMS;

import core.cq.hmq.dao.Dao;
import core.cq.hmq.dao.PageList;
import core.cq.hmq.dao.util.BeanUtil;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.service.BaseService;
import core.cq.hmq.util.tools.StringUtil;

@Service
public class ScoreManagerService extends BaseService{

	/**
	 * 查询成绩
	 * @param model
	 * @param score
	 * @return
	 */
	public EasyData<Map> findScoreData(PageModel model, Score score) {
		EasyData<Map> easyData = new EasyData<Map>(); 
		List<Object[]> colNameList = dao.getHelperDao().find("select s.name_f,ssm.colname_f from scoresubjectmapping_t ssm, subjectmapping_t sm,subjectinfo_t s"
				+ "	 where s.id_f = ssm.subject_f and sm.subjectinfo_f(+) = s.id_f and sm.stage_f = ? order by s.id_f", score.getStage()); 
		if(null != colNameList && colNameList.size() > 0){
			String searchCondition = "";
			int colNameLength = colNameList.size();
			for(int i=0;i < colNameLength; i++){
				searchCondition += colNameList.get(i)[1]+",";
			}
			
			StringBuffer hql = new StringBuffer("select title,sno,sName,");
			hql.append(searchCondition.toLowerCase());
			hql.append(" totalScore,gradeOrder,lqStatus from Score where sno like '");
			hql.append(score.getStage());
			hql.append("%' ");
			if(!StringUtil.isEmpty(score.getSno())){
				hql.append(" and sno ");
				hql.append(escapeChar(score.getSno()));
			}
			
			if(!StringUtil.isEmpty(score.getsName())){
				hql.append(" and sName ");
				hql.append(escapeChar(score.getsName()));
			}
			
			if(null != score.getTotalScore()){
				hql.append(" and totalScore >=  ");
				hql.append(score.getTotalScore());
			}
			
			if(!StringUtil.isEmpty(score.getImportDate())){
				hql.append(" and importDate = '");
				hql.append(score.getImportDate());
				hql.append("'");
			}
			
			if(!StringUtil.isEmpty(model.getSort()) && !StringUtil.isEmpty(model.getOrder())){
				hql.append(" order by  ");
				hql.append(model.getSort());
				hql.append(" "+model.getOrder());
			}
			
			PageList<Object[]> pageList = dao.page(model.getPage(), model.getRows(), hql.toString());
			List<Map> resultList = new ArrayList<Map>();
			if(null != pageList  && null != pageList.getList()){
				for (Object[] objects : pageList.getList()) {
					Map<String, Object> dataMap = new HashMap<String, Object>();
					dataMap.put("title", objects[0]);
					dataMap.put("sno", objects[1]);
					dataMap.put("sName", objects[2]);
					for(int i=0;i < colNameLength; i++){
						dataMap.put(String.valueOf(colNameList.get(i)[1]) , objects[3+i]);
					}
					dataMap.put("totalScore", objects[3+colNameLength]);
					dataMap.put("gradeOrder", objects[4+colNameLength]);
					dataMap.put("lqStatus",  "true".equals(String.valueOf(objects[5+colNameLength])) ? '是' :'否');
					resultList.add(dataMap);
				}
			}
			
			easyData.setRows(resultList);
			easyData.setTotal(pageList.getTotalCount());
		}
		
		return easyData;
	}
	
	/**
	 * 查询成绩列头
	 * @param model
	 * @param score
	 * @return
	 */
	public List<Map> findScoreHeader(int stage) {
		List<Map> colHeader = new ArrayList<Map>();
		List<Object[]> colNameList = dao.getHelperDao().find("select s.name_f,ssm.colname_f from scoresubjectmapping_t ssm, subjectmapping_t sm,subjectinfo_t s"
				+ "	 where s.id_f = ssm.subject_f and sm.subjectinfo_f(+) = s.id_f and sm.stage_f = ? order by s.id_f", stage); 
		if(null != colNameList){
			int colNameLength = colNameList.size();
			Map<String, Object> headerMap1 = new HashMap<String, Object>();
			headerMap1.put("field", "title");
			headerMap1.put("title", "标题");
			headerMap1.put("width", 360);
			colHeader.add(headerMap1);
			Map<String, Object> headerMap2 = new HashMap<String, Object>();
			headerMap2.put("field", "sno");
			headerMap2.put("title", "考号");
			headerMap2.put("width", 220);
			colHeader.add(headerMap2);
			Map<String, Object> headerMap3 = new HashMap<String, Object>();
			headerMap3.put("field", "sName");
			headerMap3.put("title", "姓名");
			headerMap3.put("width", 120);
			colHeader.add(headerMap3);
			for(int i=0;i < colNameLength; i++){
					Map<String, Object> headerMap = new HashMap<String, Object>();
					headerMap.put("field", String.valueOf(colNameList.get(i)[1]));
					headerMap.put("title", colNameList.get(i)[0]);
					headerMap.put("width", 120);
					colHeader.add(headerMap);
			}
			Map<String, Object> headerMap5 = new HashMap<String, Object>();
			headerMap5.put("field", "totalScore");
			headerMap5.put("title", "总成绩");
			headerMap5.put("sortable", true);
			headerMap5.put("width", 100);
			colHeader.add(headerMap5);
			Map<String, Object> headerMap4 = new HashMap<String, Object>();
			headerMap4.put("field", "gradeOrder");
			headerMap4.put("title", "排名"); 
			headerMap4.put("sortable", true);
			headerMap4.put("width", 100);
			colHeader.add(headerMap4);
			Map<String, Object> headerMap6 = new HashMap<String, Object>();
			headerMap6.put("field", "lqStatus");
			headerMap6.put("title", "录取状态"); 
			headerMap6.put("sortable", true);
			headerMap6.put("width", 80);
			colHeader.add(headerMap6);
		}
		
		return colHeader;
	}
	
	
	public String escapeChar(String str) {
		return "like '%"
				+ str.replace("%", "/%").replace("'", "''").replace("_", "/_")
				+ "%' escape '/' ";
	}

	@Transactional
	public String saveTemplate(String conent1, String conent2) {
		try {
			if(!StringUtil.isEmpty(conent1)){
				dao.excute("update NoticeTemplate set content = ?,createUserId=?,createDate = ? where type = 1", conent1,currentUserId(),new Date());
			}
			if(!StringUtil.isEmpty(conent2)){
				dao.excute("update NoticeTemplate set content = ?,createUserId=?,createDate = ? where type = 2", conent2,currentUserId(),new Date());
			}
			return "1";
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}
	
	public Map<Integer, String> findNoticeTemplate() {
		List<NoticeTemplate> ntList = dao.find(NoticeTemplate.class);
		Map<Integer, String> map = new HashMap<Integer, String>();
		if(null != ntList){
			for (NoticeTemplate noticeTemplate : ntList) {
				map.put(noticeTemplate.getType(),noticeTemplate.getContent());
			}
		}
		return map;
	}
	
	public String sendMsgByCondtion(int type, Score score) {
		StringBuffer luQuSql = new StringBuffer();
		luQuSql.append("select stu.name_f,stu.tel_f,r.totalscore_f,r.id_f from (select sc.id_f,sc.sno_f,sc.totalscore_f from score_t sc where  sc.sno_f like '");
		luQuSql.append(score.getStage());
		luQuSql.append("%' ");
		if(!StringUtil.isEmpty(score.getSno())){
			luQuSql.append(" and sc.sno_f ");
			luQuSql.append(escapeChar(score.getSno()));
		}
		
		if(!StringUtil.isEmpty(score.getsName())){
			luQuSql.append(" and sc.sName_f ");
			luQuSql.append(escapeChar(score.getsName()));
		}
		
		if(null != score.getTotalScore()){
			luQuSql.append(" and sc.totalScore_f >=  ");
			luQuSql.append(score.getTotalScore());
		}
		
		if(!StringUtil.isEmpty(score.getImportDate())){
			luQuSql.append(" and sc.importDate_f = '");
			luQuSql.append(score.getImportDate());
			luQuSql.append("'");
		}
		luQuSql.append(") r left join EntranceHandle_t stu on stu.examcardno_f = r.sno_f");
		List<Object[]> stuList = dao.getHelperDao().find(luQuSql.toString());
		if(null != stuList && stuList.size() > 0){
			String template = (String) dao.getHelperDao().find("select content_f from NoticeTemplate_t where type_f = ?",
					type).get(0);
			System.out.println("main=================="+Thread.currentThread().getId());
			int length = stuList.size();
			int times = 2;
			int count = length/times;
			int mod = length%times;			
			ExecutorService exec = Executors.newFixedThreadPool(times); 
			try {			
				if(count == 0){
					SendInner process = new SendInner(stuList,template,type,dao);
					exec.submit(process);
				}else{
					for(int i=0;i<times;i++){
						if(i == times -1){
							SendInner process = new SendInner(stuList.subList(i*count,(((i+1)*count + mod))),template,type,dao);
							exec.submit(process);
						}else{
							SendInner process = new SendInner(stuList.subList(i*count, (i+1)*count),template,type,dao);
							exec.submit(process);
						}
					}
				}
			   exec.shutdown();
			} catch (Exception e) {
				e.printStackTrace();
				exec.shutdown();
			}
			
			/*String content= null;
			for (Object[] objects : stuList) {
				if(!StringUtil.isEmpty(objects[1])){
					content = template;
					if(type == 2){
						content = content.replace("[姓名]", String.valueOf(objects[0]));
					}else if(type == 1){
						content = content.replace("[姓名]", String.valueOf(objects[0])
								.replace("[成绩]",String.valueOf(objects[2])));	
					}
					int status = SendSMS.sendMsg(content, String.valueOf(objects[1]));
					if(status > 0){
						dao.excute("update Score set lqStatus = true where id = ?", Long.parseLong(String.valueOf(objects[3])));
					}
				}
			}*/
		}
		
		return "1";
	}
	
	
	class SendInner implements Callable<String> {
		private List<Object[]> resultList;
		private String template;
		private int type;
		private Dao dao;
		
		public SendInner(List<Object[]> resultList,String template,int type,Dao dao) {
			super();
			this.resultList = resultList;
			this.template = template;
			this.type = type;
			this.dao = dao;
		}

		@Override
		public String call() throws Exception {
			System.out.println("sub========="+resultList.size()+"========="+Thread.currentThread().getId());
			HibernateTransactionManager transactionManager = (HibernateTransactionManager) BeanUtil.getBean("transactionManager");
			DefaultTransactionDefinition transactionDefinition = new DefaultTransactionDefinition(
					DefaultTransactionDefinition.PROPAGATION_REQUIRES_NEW);
			transactionDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_DEFAULT);
			transactionDefinition.setTimeout(400);
			TransactionStatus status = transactionManager.getTransaction(transactionDefinition);
			for (Object[] objects : resultList) {
				if(!StringUtil.isEmpty(objects[1])){
					String content = template;
					if(type == 2){
						content = content.replace("[姓名]", String.valueOf(objects[0]));
					}else if(type == 1){
						content = content.replace("[姓名]", String.valueOf(objects[0])
								.replace("[成绩]",String.valueOf(objects[2])));	
					}
					int sta = SendSMS.sendMsg(content, String.valueOf(objects[1]));
					if(sta > 0 && type == 2){
						dao.excute("update Score set lqStatus = true where id = ?", Long.parseLong(String.valueOf(objects[3])));
					}
				}
			}
			transactionManager.commit(status);
			return null;
		}

		public List<Object[]> getResultList() {
			return resultList;
		}
		


		public void setResultList(List<Object[]> resultList) {
			this.resultList = resultList;
		}

		public String getTemplate() {
			return template;
		}

		public void setTemplate(String template) {
			this.template = template;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Dao getDao() {
			return dao;
		}

		public void setDao(Dao dao) {
			this.dao = dao;
		}
		
	}
	
}
