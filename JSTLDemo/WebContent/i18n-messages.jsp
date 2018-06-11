<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<c:set var="theLocale"
	   value="${not empty param.theLocale ? param.theLocale : pageContext.request.locale}"
	   scope="session" />
	   
<fmt:setLocale value="${theLocale}" />	   
<fmt:setBundle basename="com.jstl.demo.i18n.mylabels" />

<html>

<body>


<a href="i18n-messages.jsp?theLocale=en_US"> English (US)</a>
|
<a href="i18n-messages.jsp?theLocale=es_ES"> English (US)</a>
|
<a href="i18n-messages.jsp?theLocale=de_DE"> English (US)</a>

<hr>


<fmt:message key="label.greeting" /> <br/> <br/>

<fmt:message key="label.firstname" /> <i>John</i> <br/>

<fmt:message key="label.lastname" /> <i>Doe</i> <br/>

<fmt:message key="label.welcome" /> 

<hr>

Selected Locale : ${theLocale }

</body>


</html>

