package common.cq.hmq.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import common.cq.hmq.util.ExportUtil;

import core.cq.hmq.util.tools.OfficeUtil;

@Controller
@RequestMapping("/export")
public class ExportController {

	@RequestMapping(value = "/export")
	public ModelAndView export() {
		Method method = null;
		try {
			method = ExportUtil.class.getMethod("export", Map.class,
					HSSFWorkbook.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("problem", "数据");
		OfficeUtil office = new OfficeUtil("名字", method);
		return new ModelAndView(office, map);
	}
}
