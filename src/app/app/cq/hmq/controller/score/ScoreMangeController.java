package app.cq.hmq.controller.score;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
/**
 * 成绩管理
 * @author Administrator
 *
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.cq.hmq.pojo.score.Score;
import app.cq.hmq.service.score.ScoreManagerService;
import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
@Controller
@RequestMapping(value = "/scoremange")
public class ScoreMangeController {
	
	
	@Resource
	private ScoreManagerService  scoreManagerService; 
	
	
	@RequestMapping(value="/scorepage")
	public ModelAndView scorePage() {
		/** 查询是否有通知模版信息 */
		ModelAndView mv = new ModelAndView("/app/score/scorepage");
		Map<Integer, String> map = scoreManagerService.findNoticeTemplate();	
		if(null != map && map.size() > 0){
			mv.addObject("type1", map.get(1));
			mv.addObject("type2", map.get(2));
		}
		return mv;
	}
	
	/**
	 * 后台查找数据
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/findScoreData")
	@ResponseBody
	public  EasyData<Map> findScoreData(PageModel model, Score score) {
		return scoreManagerService.findScoreData(model, score);
	}
	
	/**
	 * 后台查找列表显示
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/findScoreHeader")
	@ResponseBody
	public List<Map> findScoreHeader(int stage) {
		return scoreManagerService.findScoreHeader(stage);
	}
	
	/**
	 * 更新通知模板
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/saveTemplate")
	@ResponseBody
	public String saveTemplate(String conent1,String conent2) {
		return scoreManagerService.saveTemplate(conent1,conent2);
	}
	
	/**
	 * 推送短信
	 * 
	 * @param model
	 * @param regist
	 * @return
	 */
	@RequestMapping(value = "/sendMsgByCondtion")
	@ResponseBody
	public String sendMsgByCondtion(int type, Score score) {
		return scoreManagerService.sendMsgByCondtion(type,score);
	}
}
