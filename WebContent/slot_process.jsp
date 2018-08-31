<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.io.*,javax.servlet.*,ttd.forecastPilgrims"%>

<%@page import="java.util.Map"%>

<%@page import="com.mongodb.DBCollection"%>
<%@page import="com.mongodb.DB"%>
<%@page import="com.mongodb.Mongo"%>
<%@page import="com.mongodb.MongoClient"%>
<%@page import="com.mongodb.*"%>
<%@page import="java.util.regex.Pattern"%>
<%@page import=" com.mongodb.client.MongoCollection"%>
<%@page import="com.mongodb.client.MongoCursor"%>
<%@page import="com.mongodb.client.MongoDatabase"%>
<%@page import="com.mongodb.client.model.Filters"%>
<%@page import="org.bson.Document"%>
<%@page import="org.bson.types.ObjectId"%>
<%@page import="java.util.Iterator"%>
<%@page import=" com.mongodb.client.FindIterable"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import=" java.text.ParseException"%>
<%@page import=" java.text.SimpleDateFormat"%>
<%@page import=" java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Arrays"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/xml; charset=ISO-8859-1">

</head>
<body>
	<%!
	// Declaring the static variables 
	
static String result,Presentvqc1,Presentvqc2;
static int vqc1,vqc2;

%>

	<%
try{
	
	response.setContentType("text/plain");
	response.setCharacterEncoding("UTF-8");
	
	//getting the values from DarshanTimeEstimation.jsp
	String ip=(String)request.getParameter("dbIP");
	int port=Integer.parseInt(""+request.getParameter("port"));
	String db=(String)request.getParameter("db");
	String coll=(String)request.getParameter("coll");
	String time=(String)request.getParameter("time");
	String queue=(String)request.getParameter("queue");
	String date=(String)request.getParameter("date");



      //connect to the Database
	MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));

	
	//Now connect to your database
	   MongoDatabase db1 = mongoClient.getDatabase( "TTD" );
	   System.out.println("Connect to DEPRICATE successfully");
	//Now connect to the Collection
	   MongoCollection<Document> coll1 = db1.getCollection("Homepage");
		System.out.println("Collection created  successfully...");	
		
		//Querying the collection based on the particular values
		BasicDBObject coun = new BasicDBObject();
	    coun.put("Date", date);
		coun.put("Time", time);  
		FindIterable<Document> cursors = coll1.find(coun);
		
		//Iterating the document
	    Iterator<Document> itr = cursors.iterator();
		 if(itr.hasNext())
		 { 
			 
			Document d = itr.next();
			System.out.print(date+time);
			 
			 vqc1 = (Integer)  d.get("VQC1_No_of_Pilgrims");
			 vqc2 = (Integer) d.get("VQC2_No_of_Pilgrims");
				 
			 Presentvqc1 = String.valueOf(vqc1);
		 Presentvqc2 = String.valueOf(vqc2);
			 

		}
		 
/* 
    If queue is equals to VQC1 then it takes the VQC1 present day value to the forecastPilgrims class for 
    estimating the number of pilgrims and then sending the response to the DarshanTimeEstimation.jsp
*/
		 if(queue.equals("VQC1")){ 
	
		 result=forecastPilgrims.getForecastResult(ip, port, db, coll, time, queue,Presentvqc1,date);
		 response.getWriter().write(""+result);
			
   	}
		 
		 /* If line 98 is false then it takes the VQC2 present day value to the forecastPilgrims class for
	        estimating the number of pilgrims and then sending the response to the DarshanTimeEstimation.jsp	 
		 */
		 else 
		Presentvqc1 = "";
	Presentvqc1 =Presentvqc2;
	System.out.println("Presentvqc2::: "+Presentvqc1);
	
	 result=forecastPilgrims.getForecastResult(ip, port, db, coll, time, queue,Presentvqc1,date);  
	 
	 

	response.getWriter().write(""+result);
	
		 }
		catch(Exception e)
    
{
System.out.println(e.getMessage());
e.getStackTrace();
}
    
%>
</body>

</html>