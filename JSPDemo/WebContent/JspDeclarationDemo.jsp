<html>

<body>

<h3> Hello World to JSP </h3>

<%--    JSP Declaration Tag <%!  %>    --%>

<%!
	
	String makeItLower(String data) {
		return data.toLowerCase();
	}
	
%>

Lower case of "HELLO WORLD" : <%= makeItLower("HELLO WORLD") %>


</body>
</html>