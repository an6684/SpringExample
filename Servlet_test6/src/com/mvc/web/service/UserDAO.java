package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.jdbcUtil;
import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.RegisterVO;
import com.mvc.web.entity.user.User;

public class UserDAO {
	private static UserDAO instance = new UserDAO(); //처음에 생성한 하나의 dao
	
	public static UserDAO getInstance() {
		return instance;
	}

	//LOGIN 
	public User loginCheck(Login lg) {
		Connection con =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		User ur = new User();
		
		String sql = "select  userId, userPass, userName, userEmail, userRank "
			   	   + "  from tb_user_01_nt "
				   + " where userFlag = 'Y' "
				   + "   and userId = ? "
				   + "   and userPass = SHA2(?, 256); "; 
		try {
		
			System.out.println("id : " + lg.getId());
			System.out.println("pass : " + lg.getPass());
			con = ConnectionProvider.getConnection();
		    psmt = con.prepareStatement(sql);
			psmt.setString(1, lg.getId()); //login 객체 내 id 값을 입력
			psmt.setString(2, lg.getPass()); //login 객체 내 pass 값을 입력
			rs = psmt.executeQuery();	
			
			if(rs.next()) { // 조회된 값이 있다면  id pass 일치
					ur.setId(lg.getId());
					ur.setName(rs.getString("userName"));
					ur.setRank(rs.getString("userRank"));
					ur.setEmail(rs.getString("userEmail"));
					ur.setNumber(1);	
			}else {
				ur.setNumber(0); //조회된 값이 없을 때  id 혹은 pass 불일치 혹은 없음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return ur;
	}
	
	public int signUp(RegisterVO rv) {
		Connection con =null;
		PreparedStatement psmt = null;
		
		int result = 0;
		
		String sql = " insert into tb_user_01_nt (userId, userPass, userName, userEmail, userFlag)"
				   + " values (? , SHA2(? ,256), ? , ? ,'Y')"; 
		try {
			con = ConnectionProvider.getConnection();
		    psmt = con.prepareStatement(sql);
			psmt.setString(1, rv.getId()); 
			psmt.setString(2, rv.getPass()); 
			psmt.setString(3, rv.getName()); 
			psmt.setString(4, rv.getEmail()); 
	
			result  = psmt.executeUpdate();	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
		}
		return result;
	}
	
	
	//id 체크
	public int idCheck(String pid) {
		Connection con =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int result = 0;
		
		String sql = "select userId from tb_user_01_nt where userId = ?";
		
		try {
			con = ConnectionProvider.getConnection();
		    psmt = con.prepareStatement(sql);
			psmt.setString(1, pid);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = 1;   //조회된 값이 있을 경우
				
			}else {
				result = 0;   //조회된 값이 없을 경우
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return result;
	}
}
