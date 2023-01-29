package com.douzone.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.douzone.mysite.vo.UserVo;

public class UserDao {

	public void insert(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into user values (null, ?, ?, password(?), ?, now())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	public UserVo findByEmailAndPassword(UserVo vo) {
		UserVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name from user where email = ? and password = password(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(2, vo.getPassword());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = new UserVo();
				
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				
				result.setNo(no);
				result.setName(name);
			}
		} catch (SQLException e) {
			System.out.println("Error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		
		return result;
	}	

	public UserVo findByNo(Long no) {
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			
			String sql = "select no, name, email, gender from user where no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVo();
				
				vo.setNo(rs.getLong(1));
				vo.setName(rs.getString(2));
				vo.setEmail(rs.getString(3));
				vo.setGender(rs.getString(4));
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}

	public void update(UserVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();
			
			if("".equals(vo.getPassword())) {
				String sql = "update user set name=?, gender=? where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setLong(3, vo.getNo());
			} else {
				String sql = "update user set name=?, gender=?, password=password(?) where no=?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getName());
				pstmt.setString(2, vo.getGender());
				pstmt.setString(3, vo.getPassword());
				pstmt.setLong(4, vo.getNo());
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			
			String url = "jdbc:mariadb://172.30.1.33:3307/webdb?charset=utf8";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} 
		
		return conn;
	}
}