package com.newlecture.webprj.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.newlecture.webprj.dao.MemberDao;
import com.newlecture.webprj.dao.MemberRoleDao;
import com.newlecture.webprj.dao.NoticeDao;
import com.newlecture.webprj.entity.Member;
import com.newlecture.webprj.entity.Notice;
import com.newlecture.webprj.entity.NoticeView;

public class JdbcMemberDao implements MemberDao{

	public Member get(String id) {
		Member m = null;
		
		String sql = "SELECT * FROM Member where id=?";
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		
		// JDBC ????????????譴�??�? ???��?��??
		try {
				Class.forName("com.mysql.jdbc.Driver");
		
				// ???????????? / ????????????
			    Connection con = DriverManager.getConnection(url, "sist", "cclass");
		
			    // ????????????
			    PreparedStatement st = con.prepareStatement(sql);

			    st.setString(1, id);
			    
			    // ????????�? ????????????????????????
			    ResultSet rs = st.executeQuery();
			    
			    // Model => ??????�???�? ?????????????????? 
			    
			    // ????????�? ????????????�???�?
			    if (rs.next()) {
			       m = new Member();
	    		   m.setId(rs.getString("id")); 
	    		   m.setPwd(rs.getString("pwd")); 
	    		   m.setName(rs.getString("name")); 
	    		   m.setGender(rs.getString("gender")); 
	    		   m.setBirthday(rs.getString("birthday")); 
	    		   m.setPhone(rs.getString("phone")); 
	    		   m.setEmail(rs.getString("email"));
			    }
			    
			    rs.close();
			    st.close();
			    con.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return m;
	}
	
	public int insert(String id, String pwd, String name, String phone, String email) {

		return insert(new Member(id, pwd ,name, null, null, phone, email));
	}

	public int insert(Member member) {
		int result = 0;
		String sql = "INSERT INTO Member(id, pwd, name, gender, birthday, phone, email) VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		
		// JDBC ????????????譴�??�? ???��?��??
		try {
				Class.forName("com.mysql.jdbc.Driver");
		
				// ???????????? / ????????????
			    Connection con = DriverManager.getConnection(url, "sist", "cclass");
		
			    // ????????????
			    PreparedStatement st = con.prepareStatement(sql);
			    st.setString(1, member.getId());
			    st.setString(2, member.getPwd());
			    st.setString(3, member.getName());
			    st.setString(4, member.getGender());
			    st.setString(5, member.getBirthday());
			    st.setString(6, member.getPhone());
			    st.setString(7, member.getEmail());
			    
			    // ????????�? ???????????????????????? 0???????????? ?��?????? ???????????????????��?????? ???��?��? ????????????????????
			    result = st.executeUpdate();
			    			    
			    st.close();
			    con.close();
			      
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
