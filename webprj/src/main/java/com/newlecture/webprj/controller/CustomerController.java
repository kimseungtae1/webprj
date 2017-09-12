package com.newlecture.webprj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.dao.jdbc.JdbcMemberDao;
import com.newlecture.webprj.dao.jdbc.JdbcNoticeDao;
import com.newlecture.webprj.entity.NoticeView;

@Controller
public class CustomerController {
	
	@Autowired
	private NoticeDao noticeDao;
	
	@RequestMapping("/customer/notice")
	public String notice(/*String q*/
			@RequestParam(defaultValue="1") Integer page,
			@RequestParam(defaultValue="") String query,
			Model model) { 				//쿼리값을 request가 아니라 그냥 매개변수로 넘겨줄수있다.!!
										//자료형이 Integer이면 null일때도 오류가 나지않는다. int랑 다르게
										//어노테이션을 이용해서 매개변수의 기본값을 지정해줄수 있다.
										//모델은 request에 담아서 넘기는 것이 아니라 spring에서 제공하는 model객체를 이용해서 담아주기만 하면 dispatcher가 알아서 찾아준다.
		//System.out.println(q);
		//System.out.println(page);
		//System.out.println(query);
		
		//NoticeDao noticeDao = new JdbcNoticeDao();
		List<NoticeView> list = noticeDao.getList(page, query);
		
		model.addAttribute("list", list);
		
		return "/WEB-INF/views/customer/notice/list.jsp";
	}
	
	@RequestMapping("/customer/notice/{id}")
	public String noticeDetail(
			@PathVariable("id") String id,	//값 받아오기
			Model model) {
			
			//NoticeDao noticeDao = new JdbcNoticeDao();
			NoticeView n = noticeDao.get(id);	//가져오기
			model.addAttribute("notice", n);	//꽂아넣기
			
			return "/WEB-INF/views/customer/notice/detail.jsp";
	}
}
