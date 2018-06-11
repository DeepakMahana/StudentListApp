package com.mvc.studentApp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource){
		dataSource = theDataSource;
	}
	
	// ** Get All Students **
	public List<Student> getStudents() throws Exception {
		
		List<Student> students = new ArrayList<>();
		
		Connection myCon = null;
		Statement smt = null;
		ResultSet rs = null;
		
		try{
			//Step 1: Get Connection
			myCon = dataSource.getConnection();
			
			//Step 2: Create SQL Query
			String sql = "select * from student order by last_name";
			smt = myCon.createStatement();
			
			//Step 3: Execute Query
			rs = smt.executeQuery(sql);
			
			//Step 4: Process ResultSet
			while(rs.next()) {
				
				// Retrieve data from ResultSet Row
				int id = rs.getInt("id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				// Crate New Student Object
				Student tempStudent = new Student(id, firstName, lastName, email);
				
				// Add it to the list of Students
				students.add(tempStudent);
			}
			return students;
			
		}finally{
			
			//Step 5: Close JDBC Objects
			close(myCon, smt, rs);
			
		}
	}
	
	
	// ** Close all Open Connections **
	private void close(Connection myCon, Statement smt, ResultSet rs) {
		
		try{
			if(rs != null){
				rs.close();
			}
			if(smt != null){
				smt.close();
			}
			if(myCon != null){
				myCon.close();           // Doesnt Really Close it.. Just Puts Back in Connection Pool.
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	
	// ** Add New Student **
	public void addStudent(Student newStudent) throws SQLException {
		
		
		Connection myCon = null;
		PreparedStatement smt = null;
		
		try{
			// Get DB Connection
			myCon = dataSource.getConnection();
			
			// Create SQL Query
			String sql = "insert into student "
					   + "(first_name, last_name, email)"
					   + "values (?, ?, ?)";
			
			smt = myCon.prepareStatement(sql);
			
			// Set Param Values for the Student
			smt.setString(1, newStudent.getFirstName());
			smt.setString(2, newStudent.getLastName());
			smt.setString(3, newStudent.getEmail());
			
			// Execute SQL Insert
			smt.execute();
			
		}
		finally{
			// Clean Up JDBC objects
			close(myCon, smt, null);
		}
		
	}
	
	// ** Get Student Using ID **
	public Student getStudent(String studentId) throws Exception {
		
		Student student = null;
		Connection myCon = null;
		PreparedStatement smt = null;
		ResultSet rs = null;
		int sId;
		
		try{
			// Convert Student id to Int
			sId = Integer.parseInt(studentId);
			
			// Get Connection to Database
			myCon = dataSource.getConnection();
			
			// Create SQL to get Selected Student
			String query = "select * from student where id=?";
			
			// Create prepared Statement
			smt = myCon.prepareStatement(query);	
			
			// Set Params
			smt.setInt(1, sId);
			
			// Execute Statement
			rs = smt.executeQuery();
			
			// Retrieve Daa from ResultSet
			if(rs.next()){
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				
				// Use the StudentId during construction
				student = new Student(sId, firstName, lastName, email);
				
			}else{
				throw new Exception("Could Not Find Student Id: " + sId);
			}
			
			return student;
		}
		finally{
			close(myCon, smt, rs);
		}
		
	}
	
	
	// ** Update Student Using ID **
	public void updateStudent(Student theStudent) throws SQLException {
		
		Connection myCon = null;
		PreparedStatement smt = null;

		try {
			
			// Get DB Connection
			myCon = dataSource.getConnection();
			
			// Create SQL Update Statement
			String sql = "update student "
						+ "set first_name=?, last_name=?, email=? "
						+ "where id=?";
			
			// Prepare Statement
			smt = myCon.prepareStatement(sql);
			
			// Set Params
			smt.setString(1, theStudent.getFirstName());
			smt.setString(2, theStudent.getLastName());
			smt.setString(3, theStudent.getEmail());
			smt.setInt(4, theStudent.getId());
			
			// Execute SQL Statement
			smt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myCon, smt, null);
		}
	}
	
	
	// ** Delete Student Using ID **
	public void deleteStudent(String theStudentId) throws SQLException {
		
		Connection myCon = null;
		PreparedStatement smt = null;
		
		try {
			
			// Convert Student Id to Int
			int studentId = Integer.parseInt(theStudentId);
			
			// Get Connection to DB
			myCon = dataSource.getConnection();
			
			// Create SQL To Delete Student
			String sql = "delete from student where id=?";
			
			// Prepare Statement
			smt = myCon.prepareStatement(sql);
			
			// Set Params
			smt.setInt(1, studentId);
			
			// Execute SQL Statement
			smt.execute();
		}
		finally {
			// clean up JDBC objects
			close(myCon, smt, null);
		}
	}
	
	// ** Search Student **
	public List<Student> searchStudents(String theSearchName)  throws Exception {
		
        List<Student> students = new ArrayList<>();
        Connection myCon = null;
        PreparedStatement smt = null;
        ResultSet rs = null;
        int studentId;
        
        try {
            
            // Get connection to database
            myCon = dataSource.getConnection();
            
            //
            // Only search by name if theSearchName is not empty
            //
            if (theSearchName != null && theSearchName.trim().length() > 0) {
            	
                // Create sql to search for students by name
                String sql = "select * from student where lower(first_name) like ? or lower(last_name) like ?";
                
                // Create prepared statement
                smt = myCon.prepareStatement(sql);
                
                // Set params
                String theSearchNameLike = "%" + theSearchName.toLowerCase() + "%";
                smt.setString(1, theSearchNameLike);
                smt.setString(2, theSearchNameLike);
                
            } else {
            	
                // Create sql to get all students
                String sql = "select * from student order by last_name";
                
                // Create Prepared Statement
                smt = myCon.prepareStatement(sql);
            }
            
            // Execute statement
            rs = smt.executeQuery();
            
            // Retrieve data from result set row
            while (rs.next()) {
                
                // Retrieve Data from result set row
                int id = rs.getInt("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                
                // create new student object
                Student tempStudent = new Student(id, firstName, lastName, email);
                
                // add it to the list of students
                students.add(tempStudent);            
            }
            
            return students;
        }
        finally {
            // clean up JDBC objects
            close(myCon, smt, rs);
        }
    }
		

}
