package com.ziyoujiayuan.browser.controller.session;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * SessinTime测试类
 * @Author wanghjbuf
 * @Date 2017年9月16日
 */
@Controller
@RestController
@RequestMapping("/session")
public class SessionTimeController {

	@RequestMapping("/do")
	@ResponseBody
	public Map<String, Object> mapJsonDemo(HttpSession httpSession) {
	  Map<String, Object> map = new HashMap<String, Object>();
	  UUID uuid = (UUID) httpSession.getAttribute("uuid");
	  if (uuid == null) {
		uuid = UUID.randomUUID();
	  }
	  httpSession.setAttribute("uuid", uuid);
	  map.put("sessionId", "list");
	  return map;
	}
}
