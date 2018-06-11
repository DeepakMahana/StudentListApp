<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<html>
<body>


<!-- Basic String Functions  -->

<h3> String Operations</h3>

<c:set var="data" value="DeepakMahana" />

Length of the string <b>${data}</b> : ${fn:length(data)}

<br/><br/>

UpperCase of the string <b>${data}</b> : ${fn:toUpperCase(data)}

<br/><br/>

Does the string <b>${data}</b> start with ? &nbsp; ${fn:startsWith(data, "Dee")}

<br/><br/>


<!-- Split and Join Functions  -->


<c:set var="data" value="Singapore, Tokyo, Mumbai, London" />
<h3> Split Demo</h3>


<c:set var="citiesArray" value="${fn:split(data, ',')}" />
<c:forEach var="tempCity" items="${citiesArray}" >
	${tempCity}
</c:forEach>


<br/><br/>

<c:set var="fun" value="${fn:join(citiesArray, '*')}" />
Result of Joining: ${fun}


</body>

</html>