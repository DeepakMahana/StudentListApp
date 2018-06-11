<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<title>Student Tracker App</title>	
	
	<link type="text/css" rel="stylesheet" href="css/style.css">
	
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<h2>Mumbai University</h2>
		</div>
	</div>

	<div id="container">
	
		<div id="content">
		
			<!--  Search Box -->
            <form action="StudentController" method="GET">
        
                <input type="hidden" name="command" value="SEARCH" />
            
                Search student: <input type="text" name="theSearchName" />
                
                <input type="submit" value="Search" class="add-student-button" />
            
            </form>
		
			<!-- Add Student Button -->
			
			<input type="button" value="Add Student"
				   onClick="window.location.href='add-student-form.jsp'; return false;"
				   class="add-student-button" />
		
			<table>
			
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempStudent" items="${STUDENT_LIST}">
				
				<!-- Set Up a Link for Each Student -->
				<c:url var="tempLink" value="StudentController"> 
					<c:param name="command" value="LOAD" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				
				<!-- Set Up a Link to Delete a Student -->
				<c:url var="deleteLink" value="StudentController"> 
					<c:param name="command" value="DELETE" />
					<c:param name="studentId" value="${tempStudent.id}" />
				</c:url>
				
					<tr>
						<td> ${tempStudent.firstName} 	</td>
						<td> ${tempStudent.lastName} 	</td>
						<td> ${tempStudent.email}  		</td>
						
						<td> 
							<a href="${tempLink}"> Update </a>
							|
							<a href="${deleteLink}"
							onclick="if (!(confirm('Are You Sure ?'))) return false"> Delete </a>
						</td>
						
					</tr>
				
				</c:forEach>
				
			</table>
		
		</div>
	
	</div>
</body>


</html>








