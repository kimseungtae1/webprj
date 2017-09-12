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

public class JdbcMemberRoleDao implements MemberRoleDao {
	
	public String getDefaultRoleId(String memberId) {
		String r = null;
		String sql = "select roleId from MemberRole where defaultRole=1 and memberId=?;";
		
		String url = "jdbc:mysql://211.238.142.247/newlecture?autoReconnect=true&amp;useSSL=false&characterEncoding=UTF-8";
		
		// JDBC ����̹�? �ε�
		try {
				Class.forName("com.mysql.jdbc.Driver");
		
				// ���� / ����
			    Connection con = DriverManager.getConnection(url, "sist", "cclass");
		
			    // ����
			    PreparedStatement st = con.prepareStatement(sql);

			    st.setString(1, memberId);
			    
			    // ���? ��������
			    ResultSet rs = st.executeQuery();
			    
			    // Model => ��µ�? ������ 
			    
			    // ���? ����ϱ�?
			    if (rs.next()) {
			       r = rs.getString("roleId");
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
		
		return r;
	}	
}
