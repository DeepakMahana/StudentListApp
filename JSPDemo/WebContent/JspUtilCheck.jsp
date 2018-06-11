
<%--    Import Util Class from Src using <%@   %>    --%>

<%@page import="com.demo.jsp.Utils"%>

<html>

<body>

<h3> Hello World to JSP </h3>

<%--    Calling Java Util Method From JSP    --%>

Let's have some fun : <%= Utils.makeItLower("FUNJSP") %>


</body>
</html>