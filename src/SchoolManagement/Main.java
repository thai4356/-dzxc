package SchoolManagement;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
//		Menu m = new Menu();
//		m.hienthiMenu();
//		ArrayList<Subject> list = new ArrayList<>();
//		Subject s = new Subject();
//		s.input();
//		list.add(s);
//		SubjectDB sdb = new SubjectDB();
//		SubjectDB.insertSubject(s);
		 StudentDB studentDB = new StudentDB();
//	     studentDB.getData();
//	     studentDB.displayData();
	     Student s = new Student();
//	     studentDB.input();
	     studentDB.insertData();
	}
}
