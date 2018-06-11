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
			
			<!--  If Condition (If goldCustomer is true display Special Discount else Display - )-->
			
			<td> 
				<c:if test="${tempStudent.goldCustomer}">
					Special Discount
				</c:if> 
				
				<c:if test="${not tempStudent.goldCustomer}">
					<h5 align="center"> - </h5>
				</c:if> 
			</td>
				
		</tr>
		</c:forEach>
		
	</table>



</body>
</html>