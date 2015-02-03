package common.cq.hmq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/org")
public class OrgController {

	@RequestMapping(value = "/showPage")
	public String showPage() {
		return "/core/system/orgs";
	}

}
