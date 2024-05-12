package SchoolManagement;

import java.time.LocalDate;
import java.util.Scanner;

public abstract class Human {
	String Fname,Lname;
	int gender,role;
	LocalDate dob;
	
	public Human(String fname, String lname, LocalDate dOB, int gender, int role) {
		super();
		Fname = fname;
		Lname = lname;
		dob = dOB;
		this.gender = gender;
		this.role = role;
	}
	
	public Human() {
		super();
	}

	

	
	
	
}
