<html>

<head> <title> Confirmation </title></head>

<%

	// read form data
	String favLang = request.getParameter("favLang");

	// create the cookie
	Cookie theCookie = new Cookie("myJsp.favLang", favLang);
	
	// set the life span of cookie
	theCookie.setMaxAge(60*60*24*365);	// -- For One Year
	
	// send cookie to browser
	response.addCookie(theCookie);
	

%>

<body>
	
	Thanks ! We set your favourite language to : ${param.favLang}
	
	<br/><br/>
	
	<a href="cookiesHomepage.jsp">Return to Homepage. </a>

</body>


</html>