package com.newlecture.webprj.dao;

import com.newlecture.webprj.entity.Member;

public interface MemberDao {

	int insert(Member member);

	int insert(String id, String pwd, String name, String phone, String email);

	Member get(String id);
	
}
