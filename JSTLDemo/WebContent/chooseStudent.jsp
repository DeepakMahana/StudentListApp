<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="java.util.*, com.jstl.demo.Student" %>

<%

	// Just Create Some Sample Student Data
	List<Student> data = new ArrayList<>();

	data.add(new Student("Deepak","Mahana",true));
	data.add(new Student("Deep","Makwana",false));
	data.add(new Student("Avi","Anda",false));
	
	pageContext.setAttribute("myStudents", data);


%>

<html>
<body>

	<table border="1">
		
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gold Customer</th>
		</tr>
		
		<c:forEach var="tempStudent" items="${myStudents}">
		<tr>
			<td> ${tempStudent.firstName} </td>
			<td> ${tempStudent.lastName} </td>
			
			<!--  Choose Works like Switch Statements -->
			
			<td> 
			
				<c:choose>
				
					<c:when test="${tempStudent.goldCustomer}">
						<h5 align="center"> Special Discount </h5>
					</c:when> 
					
					<c:otherwise>
						<h5 align="center"> No Discount </h5>
					</c:otherwise> 
				
				</c:choose>
			</td>
				
		</tr>
		</c:forEach>
		
	</table>


</body>
</html>