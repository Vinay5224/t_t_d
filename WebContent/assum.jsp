<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assumptions</title>
<style>
body {
  background-color:WhiteSmoke;
}
</style>
</head>
<body>
<img src="<%=request.getContextPath()%>/images/company-logo.png"  />
<h4 ><u>ASSUMPTIONS:-</u></h4>
<p> 1. All the Business Rules are to be entered manually and it should consists of two days Business Rules from present time(48 hours ahead).</p>
<p> 2. For Friday, Saturday and Sunday the Divya Darshan(VQC-1) will not open the Darshan Slots.However, this can be modified on need basis.</p>
<p> 3. On Thursday the Divya Darshan (VQC-1) if slots are completed it will shows the alert (today darshan slots are over).  </p>
<img src="<%=request.getContextPath()%>/images/Untitled4.png" align="middle" style="border:1px solid black;margin-top:-0.5%"/>         
<p> 4. While entering the people in VQC1 & VQC2 at 05.00 PM it shows a alert that "Please Enter the Day after tomorrow's Business Rule" (For Everyday), <br></br>  then enter the Business Rules for that particular day and then proceed.</p>
<img src="<%=request.getContextPath()%>/images/Untitled3.png" align="middle" style="border:1px solid black;margin-top:-0.5%" /> 
<p> 5. If  the 'User' want to change the Business Rule then they have to remove the rules upto the particular time and then enter the new Business Rule.</p>
<p> 6. There is a possibility of changing or updating the values of VQC-I & VQC-II for particular Business Rule (for that no need to remove the Business rules).</p>
<p> 7. For every Business rules the 'From' Time will be automatically detects from previous 'To' Time shown in below diagram.</p>
<img src="<%=request.getContextPath()%>/images/Untitled6.png" align="middle"  /> 
<p> 8. If  'To' Time is in between 23:00 to 23:59 then it shows below information.</p>
<img src="<%=request.getContextPath()%>/images/Untitled2.png" align="middle" style="border:1px solid black;margin-top:-0.5%"/>

</body>
</html>