package com.ziyoujiayuan.browser.controller.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ziyoujiayuan.browser.beans.Person;

/**
 * DemoController
 * @Author wanghjbuf
 * @Date 2017年9月11日
 */
@Controller
@RestController
@RequestMapping("/demo")
public class DemoController {
 
	/**
	 * dagger-demo for index
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		
		Person personEng = new Person("wanghjbuf", 18);
		model.addAttribute("singlePersonEng", personEng);
		Person personCn = new Person("王焕杰", 18);
		model.addAttribute("singlePersonCn", personCn);
		
		System.out.println("dagger-demo for index!");
		return "views/demo/index";
	}
	
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

}
