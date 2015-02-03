package app.cq.services;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

import app.cq.hmq.pojo.score.Score;
import app.cq.hmq.pojo.score.ScoreContentMapping;
import core.cq.hmq.dao.Dao;

public class ScoreInnerProcess implements Callable<Integer>{

	private Dao dao;
	private String date;
	private String title;
	private List<Map<String, Object>> dataList;
	
	public ScoreInnerProcess(Dao dao, String date, String title,
			List<Map<String, Object>> dataList) {
		super();
		this.setDao(dao);
		this.setDate(date);
		this.setTitle(title);
		this.setDataList(dataList);
	}

	@Override
	public Integer call() throws Exception {
		Map<String,Object> map = null;
		/** 拼接内容映射表中的关联字符 */
		String tempTitle = null;
		/** 该数据中在数据库中已经存在的数据班级id */
		Set<String> classExistSet = new HashSet<String>();
		/** 该数据中在数据库中不存在的数据班级id */
		Set<String> classNOExistSet = new HashSet<String>();
		/** 存放对应科目的 映射列名 */
		Map<String, String> subjectMap = new HashMap<String, String>();
		for (int i = 0; i < dataList.size(); i++) {
			tempTitle = "";
			map = dataList.get(i);
			Score sc = new Score();
			sc.setTitle(title);
			sc.setsClass((String)map.get("class"));
			sc.setSno((String)map.get("no"));
			sc.setsName((String)map.get("name"));
			tempTitle = title + sc.getsClass()+ sc.getsName();
			List<Map<String, Object>> subjectList = (List<Map<String, Object>>) map.get("subject");
			if(null != subjectList && subjectList.size() > 0){
				List<String> colNameList = null;
				Method setMethod = null;
				for (Map subMap : subjectList) {
					/** 取出每一科的科目名称 */
					String subjectName = (String) subMap.get("name");
					if(i == 0){
						if(null == subjectMap.get(subjectName)){
							colNameList  =  dao.getHelperDao().find("select ssm.colname_f from scoresubjectmapping_t ssm," +
									"subjectinfo_t si where si.id_f = ssm.subject_f and si.name_f = ?", subjectName);
						if (null == colNameList || colNameList.size() < 1) {
							/** 此处有可能是在成绩科目映射表中未配置关系 */
							continue;
						}
							subjectMap.put(subjectName, colNameList.get(0));
						}
					}
					
					String score = String.valueOf(subMap.get("score"));
				    setMethod = sc.getClass().getDeclaredMethod("set" + subjectMap.get(subjectName),Float.class);
					if (score.matches("([1-9]+[0-9]*|0)(\\.[\\d]{1})?")) {
						setMethod.invoke(sc, Float.parseFloat(score));
					} else {
						setMethod.invoke(sc, -1f);
					}
					ScoreContentMapping scm = new ScoreContentMapping();
					scm.setTitle(tempTitle);
					scm.setColName(subjectMap.get(subjectName));
					scm.setContentData((String)subMap.get("content"));
					dao.insert(scm);
				}
			}
			
			
			String totalScore =  String.valueOf(map.get("totalscore"));
			if (null == totalScore || totalScore.matches("\\s*")) {
				sc.setTotalScore(0f);
			} else {
				sc.setTotalScore(Float.parseFloat(totalScore));
			}
			sc.setImportDate(date);
			sc.setImportUser(1L);
			dao.insert(sc);
		}
		return 121121;
	}
	
	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Dao getDao() {
		return dao;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}
	
}
