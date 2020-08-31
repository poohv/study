package com.spp.p08001;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

public class BDao  {
	
	/*private final String DB_URL = "jdbc:mysql://localhost:3306/bookstory?serverTimezone=UTC"; //접속할 DB 서버	
	private final String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
	private final String PASSWORD = "1234"; //사용자의 비밀번호를 상수로 정의
	*/

	DataSource dataSource;
	ApplicationContext context = new ClassPathXmlApplicationContext("com/spp/p08001/context.xml");
	
	public void BDao() {}
	/*
	@Autowired
	DataSource dataSource;*/
	
	
	public void insert(String name, String author, int isbn, int price) {
		System.out.println("db insert 시작");
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "insert into book (name,author,isbn,price) values (?,?,?,?)";
		try {
			/*Class.forName("com.mysql.jdbc.Driver");*/
			/*conn = DriverManager.getConnection(url,USER_NAME,PASSWORD);*/
			
			dataSource = context.getBean(DataSource.class);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, author);
			pstmt.setInt(3, isbn);
			pstmt.setInt(4, price);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			
		}finally {
 
            try {
 
                if(pstmt != null) {pstmt.close();}
                if(conn != null) {conn.close();}
 
            }catch(Exception e) {e.printStackTrace();}
 
        }
		
	}
	
	public ArrayList<Bdto> select() {
		
		System.out.println("select 시작");
		ArrayList<Bdto> list = new ArrayList<Bdto>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from book ";
		try {
			/*Class.forName("com.mysql.jdbc.Driver");*/
			/*conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);*/
			dataSource = context.getBean(DataSource.class);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("name");
				String author = rs.getString("author");
				int isbn = rs.getInt("isbn");
				int price = rs.getInt("price");
				Bdto bdto = new Bdto(name, author, isbn, price);
				list.add(bdto);
			}
			
		} catch (Exception e) {
			
		}finally {
 
            try {
 
                if(pstmt != null) {pstmt.close();}
                if(conn != null) {conn.close();}
 
            }catch(Exception e) {e.printStackTrace();}
 
        }
		
	
		return list;
	}
	
	public void delete(int isbn) {
		System.out.println("삭제 dao실행");
	
		Connection conn =null; ;
		PreparedStatement pstmt =null;
		String sql = "delete from book where isbn = ?";
		try {	
			
			dataSource = context.getBean(DataSource.class);
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, isbn);
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}if (pstmt != null) {
					pstmt.close();
				}
				
			} catch (Exception e2) {}
		}
		
	}
	
}
