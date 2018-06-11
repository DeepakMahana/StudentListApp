<%@page import="java.util.*" %>

<html>

<body>

<!--  Step 1: Create HTML Form -->

<form action="ToDo.jsp">

	Add new Item: <input type="text" name="item" />
	
	<input type="submit" value="Submit" />
	
</form>

<!--  Step 2: Add new item to ToDo List -->

<%
	
// Get the ToDo Items from the session
	List<String> items = (List<String>) session.getAttribute("myToDoList");
	
	if(items == null){
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}

// See if there is form data to add
	String item = request.getParameter("item");
	if(item != null){
		items.add(item);
	}

%>

<!--  Step 3: Display all "ToDo" item from session -->

<hr>
<b> To Do List Items :</b> <br/>

<ol>
<%
	for(String temp: items){
		out.println("<li>" + temp + "</li>");
	}

%>
</ol>


</body>

</html>