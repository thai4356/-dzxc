package SchoolManagement;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Subject {
	int id, Status, TeacherId;
	String name;
	int [] Id_Array,Status_id = new int [100];
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return Status;
	}

	public void setStatus(int status) {
		Status = status;
	}

	public int getTeacherId() {
		return TeacherId;
	}

	public void setTeacherId(int teacherId) {
		TeacherId = teacherId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Subject(int id, int status, int teacherId, String name) {
		super();
		this.id = id;
		Status = status;
		TeacherId = teacherId;
		this.name = name;
	}

	public Subject() {
		super();
	}

	public int input() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				System.out.println("Enter subject id : ");
				this.id = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Must be a number: ");
				sc.nextLine();
			}
		}
		sc.nextLine();
		System.out.println("Enter subject name : ");
		this.name = sc.nextLine();
		while (true) {
			try {
				System.out.println("Enter status id : ");
				this.Status = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Must be a number: ");
				sc.nextLine();
			}
		}
		sc.nextLine();
		while (true) {
			try {
				System.out.println("Enter teacher id : ");
				this.TeacherId = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("Must be a number: ");
				sc.nextLine();
			}
		}
		sc.close();
		return this.id;
	} 
}
