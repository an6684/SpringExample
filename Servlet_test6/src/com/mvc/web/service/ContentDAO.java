package com.mvc.web.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.VO.content.CommentVO;
import com.mvc.web.VO.content.contentVO;
import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.jdbcUtil;



public class ContentDAO {

	public int getCount(String boardid,String cds,String sw) {
		Connection con = null;
		PreparedStatement psmt = null ;
		ResultSet rs = null;
		
		String sql = "	select count(*) as count "
				+ "     from tb_board_01_nt n  "
				+ "		where useFlag = 'Y'"
				+ "		  and "+cds+" like ? "
				+ "		  and boardid like ? ";
		int result = 0;
		
//		System.out.println("sql :  "+ sql);
//		System.out.println(cds);
//		System.out.println(sw);
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1,"%"+sw+"%");
			psmt.setString(2,boardid);
//			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				result = rs.getInt("count");
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return result;
	}
	
	public List<contentVO> getList(String boardid, int page, String cds, String sw) {
		Connection con = null;
		PreparedStatement psmt = null ;
		ResultSet rs = null;
		
		int startP = 1+ (page-1)*10;
		int endP = page*10; 
		
		String sql = "select * "
				+ "     from (select @rownum:=@rownum+1 as num ,n.* "
				+ "             from( select * "
				+ "		                from tb_board_01_nt"
				+ "					   where "+cds+" like ? "
				+ "						 and useFlag ='Y' "
				+ "						 and boardid = ? "
				+ "			        order by regdate desc)n "
				+ "             where (@rownum:=0)=0)num "
				+ "    where num.num between ? and ? "; // 조회 sql
		
		List<contentVO> list = new ArrayList<contentVO>();
		
		System.out.println("sql :  "+ sql);
//		System.out.println(cds);
//		System.out.println(sw);
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, "%"+sw+"%");
			psmt.setString(2, boardid);
			psmt.setInt(3, startP);
			psmt.setInt(4, endP);
//			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int id1 = rs.getInt("content_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				String writedid = rs.getString("writedid");
				String useflag = rs.getString("useFlag");
				int hit = rs.getInt("hit");
				String board_id = rs.getString("boardid");
				String filePath = rs.getString("filepath");
				
				contentVO cv = new contentVO(id1, title, content, regdate, useflag, writedid, hit, board_id, filePath);
				
				list.add(cv);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return list;
	}
	
	
	public contentVO getDetail(int pid) {
		Connection con = null;
		PreparedStatement psmt = null ;
		ResultSet rs = null;
		
		
		contentVO cv = null;
		
		String sql = "	select content_id, title, content , regdate , useFlag ,writedid ,hit, filepath "
				+ "     from tb_board_01_nt n  "
				+ "		where useFlag = 'Y'"
				+ "		  and content_id = ? ";
		
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
//			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				cv = new contentVO();
				cv.setId(rs.getInt("content_id"));
				cv.setTitle(rs.getString("title"));
				cv.setWriteid(rs.getString("writedid"));
				cv.setContent(rs.getString("content"));
				cv.setRegdate(rs.getTimestamp("regdate"));
				cv.setHit(rs.getInt("hit"));
				cv.setFilepath(rs.getString("filepath"));
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return cv;
	}
	
	public int insertContent(String ptitle, String pcontent, String pwritedid, String filePath ) {
		Connection con = null;
		PreparedStatement psmt = null ;
		int result = 0;
		
		String sql = "	insert into tb_board_01_nt (title, content, writedid, filepath, boardid)"
				   + "values (?, ? ,?, ?, ?)";
		
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, ptitle);
			psmt.setString(2, pcontent);
			psmt.setString(3, pwritedid);
			psmt.setString(4, filePath);
			psmt.setString(5, "1");
//			System.out.println(psmt);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
		}
		return result;
	}

	public int updateContent(contentVO cv) {
		Connection con = null;
		PreparedStatement psmt = null ;
		int result = 0;
		
		String sql = "UPDATE tb_board_01_nt set title = ? , content= ?"
				   + " where content_id = ? ";
		
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, cv.getTitle());
			psmt.setString(2, cv.getContent());
			psmt.setInt(3, cv.getId());
//			System.out.println(psmt);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
		}
		return result;
	}

	public int insertContent(CommentVO cm) {
		Connection con = null;
		PreparedStatement psmt = null ;
		int result = 0;
		
		String sql = "	insert into tb_board_02_nt (content_id, content, writeid)"
				   + "values (?, ? ,?)";
		
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, cm.getContentId());
			psmt.setString(2, cm.getContent());
			psmt.setString(3, cm.getWriteId());
//			System.out.println(psmt);
			result = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
		}
		return result;
	}

	public List<CommentVO> getCommentList(int pid) {
		Connection con = null;
		PreparedStatement psmt = null ;
		ResultSet rs = null;
		
		String sql = "select comment_id, content_id, content, regdate, writeid "
				+ "from tb_board_02_nt where userflag='Y'"
				+ "and content_id = ?"; // 조회 sql
		
		List<CommentVO> list = new ArrayList<CommentVO>();

		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, pid);
			System.out.println(psmt);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				int contentid = rs.getInt("content_id");
				int commentid = rs.getInt("comment_id");
				String content = rs.getString("content");
				Date regdate = rs.getTimestamp("regdate");
				String writeid = rs.getString("writeid");
				
				CommentVO cm = new CommentVO(contentid, commentid, writeid, regdate, content);
				
				list.add(cm);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(con);
			jdbcUtil.close(psmt);
			jdbcUtil.close(rs);
		}
		return list;
	}
}
