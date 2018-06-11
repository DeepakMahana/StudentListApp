package com.mvc.studentApp;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentController")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StudentDbUtil DbUtil;
	
	@Resource(name="jdbc/studentdb")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		
		super.init();
		
		// Create our Student DB Util and Pass Conn Pool / DataSource
		try{
			DbUtil = new StudentDbUtil(dataSource);
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			
			// read the "command" parameter
			String theCommand = request.getParameter("command");
			
			// If the Command is missing, then default to listing students
			if(theCommand == null){
				theCommand = "LIST";
			}
			
			//route to the appropriate method
			switch(theCommand){
			
				case "LIST"		: listStudents(request,response); 	break;
				case "LOAD" 	: loadStudent(request,response); 	break;
				case "UPDATE" 	: updateStudent(request,response); 	break;
				case "DELETE" 	: deleteStudent(request,response); 	break;
				case "SEARCH"	: searchStudents(request, response);break;
				default: listStudents(request, response);
			}
			
		} catch (Exception e) {
			throw new ServletException(e);
		}
		
	}
	
	private void searchStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 // Read Search Name from form data
        String theSearchName = request.getParameter("theSearchName");
        
        // Search Students from db util
        List<Student> students = DbUtil.searchStudents(theSearchName);
        
        // Add Students to the request
        request.setAttribute("STUDENT_LIST", students);
                
        // Send to JSP page (view)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
        dispatcher.forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
        	
            // Read the "command" parameter
            String theCommand = request.getParameter("command");
                    
            // Route to the appropriate method
            switch (theCommand) {
                            
            case "ADD":
                addStudent(request, response);
                break;
                                
            default:
                listStudents(request, response);
            }
                
        }
        catch (Exception exc) {
            throw new ServletException(exc);
        }
    }
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read Student ID from Form Data
		String theStudentId = request.getParameter("studentId");
		
		// Delete Student From Database
		DbUtil.deleteStudent(theStudentId);
		
		// Send Them Back to JSP Page
		listStudents(request, response);
		
	}


	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {

			// Read student info from form data
			int id = Integer.parseInt(request.getParameter("studentId"));
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			// create a new student object
			Student theStudent = new Student(id, firstName, lastName, email);
			
			// perform update on database
			DbUtil.updateStudent(theStudent);
			
			// send them back to the "list students" page
			listStudents(request, response);
			
		}


	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read Student ID From Form Data
		String sId = request.getParameter("studentId");
		
		// Get Student from Database (DB Util)
		Student student = DbUtil.getStudent(sId);
		
		// Place Student in the Request Attribute
		request.setAttribute("THE_STUDENT", student);
		
		// Send to JSP Page: update-student-form.jsp
		RequestDispatcher dispatcher = request.getRequestDispatcher("/update-student-form.jsp");
		dispatcher.forward(request, response);
		
	}


	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Read Student Info From Form Data
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		// Create a new student Object
		Student newStudent = new Student(firstName, lastName, email);
		
		// Add the Student to the Database
		DbUtil.addStudent(newStudent);
		
		// Send Back to Main Page (The Student List)
		response.sendRedirect(request.getContextPath() + "/StudentController?command=LIST");
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// Get Students from DB Util
		List<Student> students = DbUtil.getStudents();
		
		// Add Students to the Request
		request.setAttribute("STUDENT_LIST", students);
		
		// Send to JSP Page (View)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/list-students.jsp");
		dispatcher.forward(request, response);
	}

	
}
