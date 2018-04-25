package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.EmaillistDao;
import com.javaex.vo.EmailVo;

@Controller
public class EmaillistController {
	
	@Autowired //자동으로 연결해줌 (applicationContext에 조건을 줘야함)
	private EmaillistDao dao; //연결되어 주소를 다 가지고 있음
	  // 컨트 롤러가 다오를 자동 연결함

	@RequestMapping(value="/form", method=RequestMethod.GET)
	public String form() {
		System.out.println("form");
		return"/WEB-INF/views/form.jsp";
	}
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String add(@ModelAttribute EmailVo emailVo) {
		System.out.println("add");
		System.out.println(emailVo.toString());
		dao.insert(emailVo);
		
		return"redirect:/list";
	}
	@RequestMapping(value="/list", method=RequestMethod.GET )
	public String list(Model model) {
		List<EmailVo> list=dao.getlist();
		model.addAttribute("list", list);
		//디스패처서블릿한테 모델만 담으면 
		//디스패쳐서블릿에서 알아서 request에서 꺼내서 return 주소에 전달 
		return"/WEB-INF/views/list.jsp";
		
	}
	
	
}
