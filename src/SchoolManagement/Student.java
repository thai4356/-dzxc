package SchoolManagement;

import java.sql.Date;
import java.time.LocalDate;

public class Student extends Human{
    int warning;
    int grade,classID,statusID;
	

	public Student(String fname, String lname, LocalDate dOB, int gender, int role, int warning, int grade,int classID,int statusID) {
		super(fname, lname, dOB,gender, role);
		this.warning = warning;
		this.grade = grade;
		this.classID = classID;
		this.statusID  = statusID;
	}
	
	
	public Student(String fname, String lname, LocalDate dOB, int gender, int role,int classID,int statusID) {
		super(fname, lname, dOB, gender, role);
	}
	
	public Student() {
		super();
		
	}


	public int getClassID() {
		return classID;
	}


	public void setClassID(int classID) {
		this.classID = classID;
	}

	public int getWarning() {
		return warning;
	}
	public void setWarning(int warning) {
		this.warning = warning;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getStatus() {
		return statusID;
	}


	public void setStatus(int status) {
		this.statusID = status;
	}


	public static void printTitle(){
        System.out.println("FirstName" + " " + "LastName" + " " + "DOB" + " " + "Gender" + " " + "ClassID"
                + " " + "Warning" + " " + "StatusID" + " " + "RoleID" + " " + " Class id" + " " + "Status id");
    }
	 public void print() {
	        System.out.println(this.Fname + " " +this.Lname + " " + this.dob + " " +  this.gender
	                + " " + this.role + " " + this.warning + " " + this.grade + " " +this.classID + " " + this.statusID);
	    }
}