package com.deepak.MVCDemo;

import java.util.ArrayList;
import java.util.List;

public class StudentDataUtil {
	
	public static List<StudentPOJO> getStudents() {
		
		// Create an Empty List
		List<StudentPOJO> students = new ArrayList<>();
		
		// Add Sample Data
		students.add(new StudentPOJO("Marry", "Public", "ma@gmail.com"));
		students.add(new StudentPOJO("Deepak", "Mah", "md@gmail.com"));
		students.add(new StudentPOJO("Deep", "Mak", "dm@gmail.com"));
		students.add(new StudentPOJO("Avi", "Anda", "aa@gmail.com"));
		students.add(new StudentPOJO("Push", "Karaj", "pa@gmail.com"));
		
		// Return the list
		return students;
	}

}
