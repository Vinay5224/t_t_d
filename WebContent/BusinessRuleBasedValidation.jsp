<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Error</title>
<style>
body {
  background-color:WhiteSmoke;
}
</style>
</head>
<body>
<img src="<%=request.getContextPath()%>/images/company-logo.png" />
<h4 ><u>Business Rule Based Validation:-</u></h4>
<p> 1. It will occur when the previous Business Rule 'To Time' is not same with the present Business Rule 'From Time' and automatically reloads the entire page.</p>
<img src="<%=request.getContextPath()%>/images/Untitled.png" align="middle" />  
<P> 2. It will occur when the Entered 'From' Time is less than or equal to 'To' Time.</P>
<P>   I. If 'From' Time is empty or 'To' Time is empty. </P>
<img src="<%=request.getContextPath()%>/images/Untitled1.png" align="middle" />  
</body>
</html>