package com.ziyoujiayuan.browser.controller.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ziyoujiayuan.api.demo.DemoService;
import com.ziyoujiayuan.browser.beans.Person;
import com.ziyoujiayuan.data.sql.mybaties.mapper.auto.usermanage.UserInfoBeanMapper;
import com.ziyoujiayuan.web.param.ResponseJsonResult;

/**
 * DemoController
 * @Author wanghjbuf
 * @Date 2017年9月11日
 */
@RestController
@RequestMapping("/demo")
public class DemoController {
 
	@Qualifier("com.ziyoujiayuan.service.demo.DemoServiceImpl")
	@Autowired
	DemoService demoService;
	
	@Autowired
	UserInfoBeanMapper userInfoBeanMapper;
	
	/**
	 * 页面+bean测试
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		
		Person personEng = new Person("wanghjbuf", 18);
		model.addAttribute("singlePersonEng", personEng);
		Person personCn = new Person("王焕杰", 18);
		model.addAttribute("singlePersonCn", personCn);
		
		return "views/demo/index";
	}
	
	/**
	 * 列表测试demo
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView listPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/views/demo/list");
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("wanghj",10));
		persons.add(new Person("wanghj",11));
		
		modelAndView.addObject("personList",persons);
		
		return modelAndView;
	}
	
	/**
	 * mybaties取数据测试
	 * @return
	 */
	@RequestMapping("/mybaties-list")
	@ResponseBody
	public Map<String, Object> mapJsonDemo() {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person",userInfoBeanMapper.selectByPrimaryKey(1L));
	    
	    return map;
	}
	
	@RequestMapping("/listcache")
	@ResponseBody
	@Cacheable(value="user-key")
	public Map<String, Object> getUserInfoToTestCache () {
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("person",userInfoBeanMapper.selectByPrimaryKey(1L));
	    
	    return map;
	}

	/**
	 * Dubbo-测试demo
	 * @param model
	 * @return
	 */
//	@ResponseBody
	@RequestMapping("/dubbotest")
	public ResponseJsonResult dubboTest(Model model) {
		ResponseJsonResult responseJsonResult = new ResponseJsonResult();
		demoService.doTest();
		
	    responseJsonResult.setMsg("操作成功");
		return responseJsonResult;
	}
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	/**
	 * rabbitmq发送测试
	 * @return
	 */
	@RequestMapping("/rabbitmq-do")
	@ResponseBody
	public Map<String, Object> rabbitSendTest() {
	    Map<String, Object> map = new HashMap<String, Object>();

	    rabbitTemplate.convertAndSend("test-queue","do-message");
	    
	    map.put("success", true);
	    map.put("msg", "6666");
	    return map;
	}
}
