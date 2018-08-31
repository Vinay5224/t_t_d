    <%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="com.mongodb.DBCursor"%>
<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBObject"%>
<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.Mongo"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.*"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>

<%@page import="org.bson.types.ObjectId" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
  <title>TTD</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> 
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script> -->
  <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.5.1/css/bootstrap-datepicker.min.css" />   
</head>
<style>
body{background:url("data:image/jpeg;base64,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")center center;
/* http://www.bluitgroup.com/wp-content/uploads/2015/11/plain-light-color-for-guest-background.jpg */
background-repeat: no-repeat;
background-attachment: fixed;
background-position: center;
background-size: 1350px 650px;

}
#date{
    width: 280px;
}
.checkbox1{


}
</style>
<body>




    <%
 
System.out.println("Inside Retrieve jsp page");
 response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  
 
 //Now connect to database
MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

 //Now connect to your database
DB db = mongoClient.getDB("TTD");

%>
 
<div class="container">


<!-- Top banner starts -->
  <div class="img-figure" style="margin-top:2%">
  <div class="panel panel-default" style="box-shadow: 8px 8px 3px gray;">
  <div class="panel-heading custom_class" style="height:150px;background-color:white; " >
  
<div align="center" id="image" style="float:center; margin-RIGHT:12%;">
<img src="https://i.imgur.com/WVQwLFA.png" class="media-object" style="width:870px;height:120px"/>
</div>
<div id="image" style="float:right; margin-RIGHT:0%;margin-top:-10%">
<a href="http://www.exafluence.com" target="new">
<img src="https://i.imgur.com/Av2h6H8.png" alt="exafluence" class="media-object" style="width:210px;height:90px"align="right"/></a>
<p style="margin-left:30%"><b>Beta Ver.1.0</b></p>
</div>
  </div>
  </div>
  </div>
  <!-- Top banner ends -->
  
   <a type="button" href="Timeslot.jsp"> <img src="http://arjinamedi.com/uploads/images/r9cyqu20160120204053.png" style="height:70px ;float:right;"/></a>
  
 
  
<div class="container">

 <form method="post" action="Slots" >
 
 <%
 // getting the date and day from the Timeslot.jsp
 
 String str = request.getParameter("dte");
 String str1 = request.getParameter("day");
	
 
 %>
 
 <!-- Printing those date and day in this page  -->
 <div class="row" style="margin-top:2%">
 <div class="col-sm-2">
 <input type="text" name="date" value="<%out.print(str);%>" class="form-control" readonly/>
  </div>
 <div class="col-sm-2">
 <input type="text" name="day" value="<%out.print(str1);%>" class="form-control" readonly/>
 
 </div>
   </div>
<div class="well">

     <table class="table table-bordered">
    <thead>
      <tr>
<th>Time Slot</th>
<th>Real Time People (VQC-I)</th>
<th>Real Time People (VQC-II)</th>
<th>Calculated Time(VQC-I)</th>
<th>Calculated Time(VQC-II)</th>
<th>Real Time Darshan(VQC-I)</th>
<th>Real Time Darshan(VQC-II)</th>
  </tr>
    <tbody>
   
      <% 
      // checking the date and time in the Homepage collection and displaying on this page
      
      for(int i=0;i<24;i++) {
      
        
        BasicDBObject whereQuery = new BasicDBObject();

DBCollection coll = db.getCollection("Homepage");

BasicDBObject g = new BasicDBObject();
g.append("Time", i+":00");
g.append("Date", str);

DBCursor cursors = coll.find(g);
 while (cursors.hasNext()) { 

	 
	 DBObject results=cursors.next();
	  Map resultmaps=results.toMap();  
	  
	  
	  %>
      
       <tr>
              <td   class="col-xs-2"><%  out.print(i+":00"); %>  </td>
         <input type="hidden" name="Time" value="<%  out.print(i+":00"); %>" />
         <input type="hidden" name="count" value="<% out.print(i); %>" />
	         <td  ><input id="Real(VQC-I)" name="rvqc1" type="text"  value="<% out.print(resultmaps.get("VQC1_No_of_Pilgrims")); %>" class="form-control" placeholder="No.of.Pilgrims" readonly ></td>  
        <td><input id="Real(VQC-II)" name="rvqc2" type="text" value="<% out.print(resultmaps.get("VQC2_No_of_Pilgrims")); %>" class="form-control" placeholder="No.of.Pilgrims" readonly></td>
        <td><input id="Calculated(VQC-I)" name="cvqc1" type="text" value="<% out.print(resultmaps.get("VQC1_Est_DarshanTime")); %>" class="form-control" placeholder="Est.Darshan Time"  readonly></td>  
         <td><input id="Calculated(VQC-II)" name="cvqc2" type="text" value="<% out.print(resultmaps.get("VQC2_Est_DarshanTime")); %>" class="form-control" placeholder="Est.Darshan Time" readonly></td> 
        <td><input id="CalculatedTime(VQC-I)" name="ctvqc1" type="text"  class="form-control" placeholder="Real Darshan Time" ></td> 
          <td><input id="CalculatedTime(VQC-II)" name="ctvqc2" type="text"  class="form-control" placeholder="Real Darshan Time"></td>       
      </tr>
     
        
     
      <% } } %>
       </tbody>
      </table>
      
 
   <button type="submit" class="btn btn-primary " style="float:right;margin-top:-1.5%">Save</button>
   
   </form>
        </div>
        </div>
        
       
</body>
</html>
