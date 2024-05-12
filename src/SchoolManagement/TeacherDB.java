package SchoolManagement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

//import java.util.Scanner;
import SchoolManagement.Connect;


public class TeacherDB {
	static Connection conn = Connect.getConnection();
	    public static boolean insertTeacher(Teacher t) {
        // 1. Tạo kết nối

        if (conn == null) {
            System.out.println("Connect fail!");
            return false; 
        }
        PreparedStatement ps = null;
        Scanner scanner = new Scanner(System.in);
        int id;
        
        while (true) {
        	System.out.println("Enter the ID you check to insert: ");
            String nhap = scanner.nextLine();
            try {
                id = Integer.parseInt(nhap);
                if (id < 0) {
                    System.out.println("ID must be greater than 0, please enter again: ");
                    continue;
                }
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM teacher WHERE id = ?");
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    System.out.println("ID already exists, please re-enter: ");
                    continue;
                }else {
                	System.out.println("A valid ID can perform the insert");
                }
                
                break;
            }catch (NumberFormatException e){
                System.out.println("ID must be an integer, please re-enter: ");
            }
            catch (SQLException e) {
                System.out.println("ID already exists, please re-enter: ");
            }
        }
        t = nhap(t);
       
        try {
          
            String insert = "INSERT INTO teacher (Id, FirstName, LastName, date_of_birth, Salary, statusID, RoleId, gender) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(insert);
            ps.setInt(1, t.getId());
            ps.setString(2, t.getFirstName());
            ps.setString(3, t.getLastName());
            ps.setDate(4, java.sql.Date.valueOf(t.date_of_birth));
            ps.setInt(5, t.getSalary());
            ps.setInt(6, t.getStatusId());
            ps.setInt(7, t.getRoleId());
            ps.setInt(8, t.getGender());
            
            int result = ps.executeUpdate();
            System.out.println("Successfully added " + result + " record(s).");
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	// Đóng PreparedStatement và kết nối
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connect.CloseConnection(conn);
        }
		return false;
 }
	    public static boolean update(Teacher t) {
	    	  // 1. Tạo kết nối

	        if (conn == null) {
	            System.out.println("Connect fail!");
	            return false; 
	        }
	        PreparedStatement ps = null;        
	        Scanner scanner = new Scanner(System.in);

	        int id;
	        while (true) {
	            System.out.println("Enter the ID you check to update: ");
	            String nhap = scanner.nextLine();
	            try {
	                id = Integer.parseInt(nhap);
	                if (id < 0) {
	                    System.out.println("ID must be greater than 0, please enter again: ");
	                    continue;
	                }
	                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM teacher WHERE id = ?");
	                stmt.setInt(1, id);
	                ResultSet rs = stmt.executeQuery();
	                if(!rs.next()){
	                    System.out.println("ID does not exist, please re-enter: ");
	                    continue;
	                }else {
	                	System.out.println("A valid ID can perform the update");
	                }
	                
	                break;
	            }catch (NumberFormatException e){
	                System.out.println("ID must be an integer, please re-enter: ");
	            }
	            catch (SQLException e) {
	                System.out.println("ID does not exist, please re-enter: ");
	            }
	        }
	        
 
	        t = nhap(t);
	        try {
	            // Cập nhật giáo viên
	            String updateQuery = "UPDATE teacher SET FirstName = ?, LastName = ?, date_of_birth = ?, Salary = ?, RoleId = ?, StatusId = ?, Gender = ? WHERE Id = ?";
	            ps = conn.prepareStatement(updateQuery);

	            // Thiết lập các tham số từ đối tượng teacher
	            ps.setString(1, t.getFirstName());
	            ps.setString(2, t.getLastName());
	            ps.setDate(3, java.sql.Date.valueOf(t.date_of_birth));
	            ps.setInt(4, t.getSalary());
	            ps.setInt(5, t.getRoleId());
	            ps.setInt(6, t.getStatusId());
	            ps.setInt(7, t.getGender());
	            ps.setInt(8, t.getId());  // Cập nhật giáo viên dựa trên ID

	            // Thực thi truy vấn cập nhật
	            int rowsUpdated = ps.executeUpdate();

	            if (rowsUpdated > 0) {
	                System.out.println("Successfully updated teacher with ID: " + t.getId()) ;
	                return true;
	            } else {
	                System.out.println("No records were updated.");
	                return false;
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        } finally {
	            try {
	                if (ps != null) {
	                    ps.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            Connect.CloseConnection(conn);
	        }
	    
  }


	public static boolean delete(Teacher t) {
    	 // 1. Tạo kết nối

        if (conn == null) {
            System.out.println("Connect fail!");
            return false; 
        }
        PreparedStatement ps = null;    
        Scanner scanner = new Scanner(System.in);
        int id;
        while (true) {
        	System.out.println("Enter ID you want to delete: ");
            String nhap = scanner.nextLine();
            try {
                id = Integer.parseInt(nhap);
                if (id < 0) {
                    System.out.println("ID must be greater than 0, please enter again: ");
                    continue;
                }
                PreparedStatement stmt =
                        conn.prepareStatement("SELECT * FROM teacher WHERE id = ?");
                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();
                if(!rs.next()){
                    System.out.println("ID does not exist, please re-enter: ");
                    continue;
                }
                break;
            }catch (NumberFormatException e){
                System.out.println("ID must be an integer, please re-enter: ");
            }
            catch (SQLException e) {
                System.out.println("ID does not exist, please re-enter: ");
            }
        }
        
        // Thực hiện truy vấn xóa giáo viên theo ID
        
        try {
            String deleteQuery = "DELETE FROM teacher WHERE id = ?";
            ps = conn.prepareStatement(deleteQuery);
            ps.setInt(1, id);
            
            int rowsDeleted = ps.executeUpdate();
            
            if (rowsDeleted > 0) {
                System.out.println("Successfully deleted teacher with ID: " + id);
                return true;
            } else {
                System.out.println("No records were deleted.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // Đóng PreparedStatement và kết nối
            try {
                if (ps != null) ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connect.CloseConnection(conn);
        }
    
}
	public static boolean getdata(Teacher t) {
		 // 1. Tạo kết nối

        if (conn == null) {
            System.out.println("Connect fail!");
            return false; 
        }
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        
        try {
            // Truy vấn để lấy tất cả dữ liệu giáo viên từ bảng teacher
            String get = "SELECT * FROM teacher";
            ps = conn.prepareStatement(get);

            // Thực thi truy vấn và lấy kết quả
            rs = ps.executeQuery();

            // In ra tất cả dữ liệu đã nhập
            System.out.println("Danh sách giáo viên:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                LocalDate date_of_birth = rs.getDate("date_of_birth").toLocalDate();
                int salary = rs.getInt("Salary");
                int statusId = rs.getInt("statusID");
                int roleId = rs.getInt("RoleId");
                int gender = rs.getInt("gender");

                System.out.println("ID: " + id + ", First Name: " + firstName + ", Last Name: " + lastName + ", Date_of_birth: " + date_of_birth +
                                   ", Salary: " + salary + ", Status ID: " + statusId + ", Role ID: " + roleId +
                                   ", Gender: " + gender);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            // 2. Đóng kết nối
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Connect.CloseConnection(conn);
        }
		return false;
    }
	

public static Teacher nhap(Teacher t) {
    Scanner scanner = new Scanner(System.in);
    
    System.out.println("Enter student's ID: ");
    while (true) {
        String id = scanner.nextLine();
        try {
            t.id = Integer.parseInt(id);
            if (t.id <= 0) {
                System.out.println("ID must be greater than 0, please re-enter: ");
                continue;
            }
            PreparedStatement stmt =
                    conn.prepareStatement("SELECT * FROM student WHERE id = ?");
            stmt.setInt(1, t.id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("ID already exists, please re-enter: ");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("ID must be an integer, please re-enter: ");
        } catch (SQLException e) {
            System.out.println("ID does not exist, please re-enter: ");
        }
    }
    
    System.out.println("Enter first name:");
    t.setFirstName(scanner.nextLine());

    System.out.println("Enter last name:");
    t.setLastName(scanner.nextLine());
    
    System.out.println("Enter Date of Birth(yyyy-MM-dd): ");
    while (true) {
        String date_of_birth = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (date_of_birth.isEmpty()) {
            System.out.println("Date of Birth cannot be empty, please re-enter: ");
            continue;
        }
        try {
            t.date_of_birth = LocalDate.parse(date_of_birth, formatter);
            break;
        } catch (DateTimeParseException e) {
            System.out.println("Date of Birth must be in the format yyyy-MM-dd, please re-enter: ");
        }
    }
    
    while (true) {
        try {
            System.out.println("Enter salary:");
            t.setSalary(scanner.nextInt());
            if(t.Salary < 0) {
            System.out.println("Salary must be greater than 0, please enter again: ");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid salary. Please enter an integer value.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    while (true) {
        try {
            System.out.println("Enter RoleId:");
            t.setRoleId(scanner.nextInt());
            if(t.RoleId < 0) {
            System.out.println("RoleId must be greater than 0, please enter again: ");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid RoleId. Please enter an integer value.");
            scanner.nextLine(); 
        }
    }

    while (true) {
        try {
            System.out.println("Enter StatusId: ");
            t.setStatusId(scanner.nextInt());
            if(t.RoleId < 0) {
            System.out.println("StatusId must be greater than 0, please enter again: ");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid StatusId. Please enter an integer value.");
            scanner.nextLine(); 
        }
    }

    
    while (true) {
        try {
            System.out.println("Enter gender (1 for male, 2 for female):");
            int gender = scanner.nextInt();
            if (gender == 1 || gender == 2) {
                t.setGender(gender);
            } else {
                System.out.println("Invalid gender. Please enter 1 for male or 2 for female.");
            }
            break;
        } catch (InputMismatchException e) {
            System.out.println("Invalid gender. Please enter an integer value (1 for male, 2 for female).");
            scanner.nextLine(); 
        }
    }
	return t;
    }
}