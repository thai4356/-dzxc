package SchoolManagement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//import java.sql.Statement;
//import java.sql.Types;
//import java.sql.Statement;
//import java.util.Scanner;
import SchoolManagement.Connect;


public class SubjectDB {
	static Connection conn = Connect.getConnection();
	
	public static boolean insertSubject(Subject s) {
		// 1. Tạo kết nối
		if (conn == null) {
			System.out.println("Connect fail!");
			return false;
		}
		PreparedStatement ps = null;
		CallableStatement cs = null;
		try {
			ps = conn.prepareStatement("insert into subject (id,name,status,Teacher_id) values (?,?,?,?)");
			ps.setInt(1, s.getId());
			ps.setString(2, s.getName());
			ps.setInt(3, s.getStatus());
			ps.setInt(4, s.getTeacherId());
			Statement stm = conn.createStatement();
			int result = ps.executeUpdate();
			System.out.format("succesfully add " + result + " record");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4. Đóng kết nối
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connect.CloseConnection(conn);
		}
		return false;
	}
	
	public static boolean updateSubject(Subject s) {
		// 1. Tạo kết nối
		if (conn == null) {
			System.out.println("Connect fail!");
			return false;
		}
		PreparedStatement ps = null;
		CallableStatement cs = null;
		try {
			ps = conn.prepareStatement("update subject where id = ?");
			ps.setInt(1, s.getId());
			Statement stm = conn.createStatement();
			int result = ps.executeUpdate();
			System.out.format("succesfully update " + result + " record with id = "+s.getId());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4. Đóng kết nối
			try {
				cs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Connect.CloseConnection(conn);
		}
		return false;
	}
	
}
