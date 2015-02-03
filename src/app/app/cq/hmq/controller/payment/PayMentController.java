package app.cq.hmq.controller.payment;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import core.cq.hmq.model.EasyData;
import core.cq.hmq.model.PageModel;
import core.cq.hmq.util.tools.DateUtil;
import app.cq.hmq.pojo.entrancehandle.EntranceHandle;
import app.cq.hmq.service.payment.PayMentService;

@Controller
@RequestMapping(value = "/payment")
public class PayMentController {
	@Resource 
	private PayMentService payMentService;
	
	
	@RequestMapping(value="/ppage")
	public ModelAndView pageMentPage() {
		ModelAndView mv = new ModelAndView("/app/payment/payment");
		return mv;
	}
	
	/**
	 * 
	 *@param model
	 *@return
	 *
	 *
	 */
	@RequestMapping(value = "/findata")
	@ResponseBody
	public EasyData<EntranceHandle> findData(PageModel model, EntranceHandle condition) {
		if(null == condition){
			condition = new EntranceHandle();
			condition.setYear(DateUtil.getYear(new Date()));
		}
		return payMentService.findData(model, condition);
	}
	
	@RequestMapping(value = "/updatePaySum")
	@ResponseBody
	public int updatePaySum(Long id, float sum ){
		return  payMentService.updatePaySum(id,sum);
	}
}
