<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inventory</title>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/Index.css">

<%@page import="java.util.ArrayList,javaModels.Items" %>

</head>
<body>

<h1>See our stock!</h1>

<table>
<tr>
    <th>Name</th>
    <th>Price</th>
</tr>


<%ArrayList<Items> allInventory = (ArrayList<Items>)request.getAttribute("allInventory");

for (Items s : allInventory) {%>
<!--HTML inside the loop-->
<tr>
	<td><%= s.getItemDescription() %></td>
	<td><%= s.getItemPrice() %></td>
	
</tr>	

<%} 


%>
</table>




</body>
</html>