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
			Model model) { 				//�������� request�� �ƴ϶� �׳� �Ű������� �Ѱ��ټ��ִ�.!!
										//�ڷ����� Integer�̸� null�϶��� ������ �����ʴ´�. int�� �ٸ���
										//������̼��� �̿��ؼ� �Ű������� �⺻���� �������ټ� �ִ�.
										//���� request�� ��Ƽ� �ѱ�� ���� �ƴ϶� spring���� �����ϴ� model��ü�� �̿��ؼ� ����ֱ⸸ �ϸ� dispatcher�� �˾Ƽ� ã���ش�.
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
			@PathVariable("id") String id,	//�� �޾ƿ���
			Model model) {
			
			//NoticeDao noticeDao = new JdbcNoticeDao();
			NoticeView n = noticeDao.get(id);	//��������
			model.addAttribute("notice", n);	//�ȾƳֱ�
			
			return "/WEB-INF/views/customer/notice/detail.jsp";
	}
}
