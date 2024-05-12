package SchoolManagement;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentDB {
    private ArrayList <Student> studentCollection = new ArrayList<Student>();
    private static Scanner scan = new Scanner(System.in);
    private static Connection conn=Connect.getConnection();

    public void getData() {
        studentCollection.clear();
        try{
            conn = Connect.getConnection();
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM student";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 Student s = new Student(rs.getString("FirstName"),rs.getString("LastName"),rs.getDate("date_of_birth").toLocalDate(),rs.getInt("gender"),rs.getInt("roleID"),rs.getInt("warning"),rs.getInt("grade"));
                 rs.getInt("Id");
                 studentCollection.add(s);
            }
            
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void displayData() {
        Student.printTitle();
        for (Student student : studentCollection) {
            student.print();
        }
    }

    public Student input() {
    	Student student = new Student();
//        System.out.println("Enter student's ID: ");
//        while (true) {
//            String id = scan.nextLine();
//            try {
//                student.id = Integer.parseInt(id);
//                if (student.id <= 0) {
//                    System.out.println("ID must be greater than 0, please re-enter: ");
//                    continue;
//                }
//                PreparedStatement stmt =
//                        conn.prepareStatement("SELECT * FROM student WHERE id = ?");
//                stmt.setInt(1, student.id);
//                ResultSet rs = stmt.executeQuery();
//                if (rs.next()) {
//                    System.out.println("ID already exists, please re-enter: ");
//                    continue;
//                }
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("ID must be an integer, please re-enter: ");
//            } catch (SQLException e) {
//                System.out.println("ID does not exist, please re-enter: ");
//            }
//        }
        System.out.println("Enter First Name: ");
        student.Fname = scan.nextLine();
        while (student.Fname.isEmpty()) {
            System.out.println("Cannot be empty, please re-enter: ");
            student.Fname = scan.nextLine();
        }
        System.out.println("Enter Last Name: ");
        student.Lname = scan.nextLine();
        while (student.Lname.isEmpty()) {
            System.out.println("Cannot be empty, please re-enter: ");
            student.Lname = scan.nextLine();
        }
       
        while (true) {
            try{
            	System.out.println("Enter class id");
            	student.classID  =  scan.nextInt();
            	break;
            }catch(Exception ioe){
            	System.out.println("must be number: " );
            	scan.nextLine();
            }
        }
        System.out.println("Enter Warning: ");
        while (true) {
            String warning = scan.nextLine();
            try {
                student.warning = Integer.parseInt(warning);
                if (student.warning < 0) {
                    System.out.println("Warning must be greater than 0, please re-enter: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Warning must be an integer, please re-enter: ");
            }
        }
        System.out.println("Enter Status ID: ");
        while (true) {
            
            try {
                student.statusID = scan.nextInt();
                if (student.statusID <= 0) {
                    System.out.println("Status ID must be greater than 0, please re-enter: ");
                    continue;
                }
                break;
//                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM status WHERE id = ?");
//                stmt.setInt(1, student.statusID);
//                ResultSet rs = stmt.executeQuery();
//                if(!rs.next()){
//                    System.out.println("Status ID does not exist, please re-enter: ");
//                    continue;
//                }
            } catch (NumberFormatException e) {
                System.out.println("Status ID must be an integer, please re-enter: ");
                scan.nextLine();
            } 
//            catch (SQLException e) {
//                System.out.println("Status ID does not exist, please re-enter: ");
//            }
        }
        System.out.println("Enter Role ID: ");
        while (true) {
            try {
                student.role = scan.nextInt();
                if (student.role <= 0) {
                    System.out.println("Role ID must be greater than 0, please re-enter: ");
                    continue;
                }
//                PreparedStatement stmt =
//                        conn.prepareStatement("SELECT * FROM role WHERE id = ?");
//                stmt.setInt(1, student.role);
//                ResultSet rs = stmt.executeQuery();
//                if(!rs.next()){
//                    System.out.println("Role ID does not exist, please re-enter: ");
//                    continue;
//                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Role ID must be an integer, please re-enter: ");
                scan.nextLine();
            } 
//            catch (SQLException e) {
//                System.out.println("Role ID does not exist, please re-enter: ");
//            }
        }
        System.out.println("Enter Grade: ");
        while (true) {
            String grade = scan.nextLine();
            try {
                student.grade = Integer.parseInt(grade);
                if (student.grade < 0) {
                    System.out.println("Grade must be greater than 0, please re-enter: ");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Grade must be an integer, please re-enter: ");
            }
        }
        System.out.println("Enter Date of Birth(yyyy-MM-dd): ");
        while (true) {
            String date_of_birth = scan.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (date_of_birth.isEmpty()) {
                System.out.println("Date of Birth cannot be empty, please re-enter: ");
                continue;
            }
            Period period = Period.between(LocalDate., endDateExclusive)
            if(LocalDate.now().minusYears(15).isAfter(LocalDate.parse(date_of_birth, formatter))){
                System.out.println("Student must be at least 15 years old, please re-enter: ");
                continue;
            }
            try {
                student.dob = LocalDate.parse(date_of_birth, formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Date of Birth must be in the format yyyy-MM-dd, please re-enter: ");
            }
        }
        
            System.out.println("Enter Gender: ");
            student.gender = scan.nextInt();
//            try {
//                student.gender = Integer.parseInt(gender);
//                if (student.gender < 0) {
//                    System.out.println("Gender must be greater than 0, please re-enter: ");
//                    continue;
//                }
//                break;
//            }catch (NumberFormatException e){
//                System.out.println("Gender must be an integer, please re-enter: ");
//            }
            return student;
        
       
    }

    public void insertData() {
    	Student student = new Student();
        student = input();
        PreparedStatement stmt=null;
        try {
            stmt = conn.prepareStatement("INSERT INTO student(FirstName, LastName, classID, warning, statusID" +
                    ", roleID, grade, date_of_birth, gender) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?) ");
            stmt.setString(1, student.Fname);
            stmt.setString(2, student.Lname);
            stmt.setInt(3, student.classID);
            stmt.setInt(4, student.warning);
            stmt.setInt(5, student.statusID);
            stmt.setInt(6, student.role);
            stmt.setInt(7, student.grade);
            stmt.setDate(8, java.sql.Date.valueOf(student.dob));
            stmt.setInt(9, student.gender);
            stmt.executeUpdate();
//            System.out.println("inserted row: " + stmt.executeUpdate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
//
//    public void updateData (Student student) {
//        int id;
//        while (true) {
//            System.out.println("Enter ID you want to update: ");
//            String input = scan.nextLine();
//            try {
//                id = Integer.parseInt(input);
//                if (id < 0) {
//                    System.out.println("ID must be greater than 0, please enter again: ");
//                    continue;
//                }
//                PreparedStatement stmt =
//                        conn.prepareStatement("SELECT * FROM student WHERE id = ?");
//                stmt.setInt(1, id);
//                ResultSet rs = stmt.executeQuery();
//                if(!rs.next()){
//                    System.out.println("ID does not exist, please re-enter: ");
//                    continue;
//                }
//                break;
//            }catch (NumberFormatException e){
//                System.out.println("ID must be an integer, please re-enter: ");
//            }
//            catch (SQLException e) {
//                System.out.println("ID does not exist, please re-enter: ");
//            }
//        }
//        student=input(student);
//        try{
//            PreparedStatement stmt = conn.prepareStatement("UPDATE student " +
//                    "SET FirstName = ?, LastName = ?, classID = ?, warning = ?, statusID = ?, roleID = ?, grade = ?," +
//                    " date_of_birth = ?, gender = ? " +
//                    "WHERE id = ?");
//            stmt.setString(1, student.FirstName);
//            stmt.setString(2, student.LastName);
//            stmt.setInt(3, student.classID);
//            stmt.setInt(4, student.warning);
//            stmt.setInt(5, student.statusID);
//            stmt.setInt(6, student.roleID);
//            stmt.setInt(7, student.grade);
//            stmt.setDate(8, java.sql.Date.valueOf(student.DOB));
//            stmt.setInt(9, student.gender);
//            stmt.setInt(10, id);
//            System.out.println("Updated rows: " +stmt.executeUpdate());
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//
//    public void deleteData(Student student) {
//        int id;
//        System.out.println("Enter ID you want to delete: ");
//        while (true) {
//            String input = scan.nextLine();
//            try {
//                id = Integer.parseInt(input);
//                if (id < 0) {
//                    System.out.println("ID must be greater than 0, please enter again: ");
//                    continue;
//                }
//                PreparedStatement stmt =
//                        conn.prepareStatement("SELECT * FROM student WHERE id = ?");
//                stmt.setInt(1, id);
//                ResultSet rs = stmt.executeQuery();
//                if (!rs.next()) {
//                    System.out.println("ID does not exist, please re-enter: ");
//                    continue;
//                }
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("ID must be an integer, please re-enter: ");
//            } catch (SQLException e) {
//                System.out.println("ID does not exist, please re-enter: ");
//            }
//        }
//        try {
//            PreparedStatement stmt = conn.prepareStatement("DELETE FROM student WHERE id = ?");
//            stmt.setInt(1, id);
//            System.out.println("Updated rows: " + stmt.executeUpdate());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }





}