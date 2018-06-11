<html>

<head><title>Student Confirmation </title></head>

<body>

	The Student is confirmed : ${param.firstname} ${param.lastname} 

	<br/><br/>
	
	The Student's country : ${param.country}
	
	<br/><br/>
	
	Student's Gender : ${param.gender}
	
	
	<br/><br/>
	
	<!--  Display List of Favourite Language -->
	
		<ul>
		
			<%
				String[] langs = request.getParameterValues("language");
				
				if(langs != null){
					for (String tempLang: langs){
						out.println("<li>" + tempLang + "</li>");
					}
				}
				
			%>
		
		
		</ul>

</body>
</html>