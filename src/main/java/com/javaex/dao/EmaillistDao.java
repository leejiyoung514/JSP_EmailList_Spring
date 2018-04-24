package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.EmailVo;

@Repository  //new 시켜주는 애들이 잇음 
public class EmaillistDao {

	public void insert(EmailVo vo) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		int count;

		try {
			//
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			
			String sql = "INSERT INTO EMAILLIST values( SEQ_EMAILLIST_NO.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getLastName());
			pstmt.setString(2, vo.getFirstName());
			pstmt.setString(3, vo.getEmail());

			count = pstmt.executeUpdate();
			System.out.println(count + "�� ���");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패- " + e);
			// �ڼ��ϰ� �޽����� ó���ϰ� ������
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public List<EmailVo> getList() {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		List<EmailVo> list = new ArrayList<EmailVo>();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "1234");
			String sql = "SELECT NO, LASTNAME, FIRSTNAME, EMAIL" + " FROM EMAILLIST" + " ORDER BY NO DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt("NO");
				String lastName = rs.getString("LASTNAME");
				String firstName = rs.getString("FIRSTNAME");
				String Email = rs.getString("EMAIL");
				EmailVo vo = new EmailVo(no, Email, firstName, Email);
				list.add(vo);
			}

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버로딩실패 - " + e);
		
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;

	}

}
