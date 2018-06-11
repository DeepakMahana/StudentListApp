<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>

<body>

<h2>Student Table Demo</h2>
<hr>
<br/>
	
	<table border="1">
	
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email Id</th>
	</tr>
	
	<c:forEach var="tempStudent" items="${student_list}">
	<tr>
		<td>${tempStudent.firstname}</td>
		<td>${tempStudent.lastname}</td>
		<td>${tempStudent.email}</td>
	</tr>
	</c:forEach>
	
	
	</table>


</body>

</html>