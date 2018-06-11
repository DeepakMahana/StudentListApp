<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%

	// Just create some sample Data
	
	String[] cities = {"Mumbai", "Bangalore", "Delhi"};

	pageContext.setAttribute("myCities", cities);

%>

<html>

<body>

<c:forEach var="tempCity" items="${myCities}">
	
	${tempCity} <br/>

</c:forEach>


</body>


</html>