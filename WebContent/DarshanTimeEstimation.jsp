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
<html>
<head>
<meta charset="utf-8">
 
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href='https://fonts.googleapis.com/css?family=Passion+One' rel='stylesheet' type='text/css'>
 <link href='https://fonts.googleapis.com/css?family=Oxygen' rel='stylesheet' type='text/css'>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
 
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />


<link rel="stylesheet" href="http://weareoutman.github.io/clockpicker/dist/jquery-clockpicker.css" />
<link rel="stylesheet" href="clockpicker-12-hour-option.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/jquery-clockpicker.min.js"></script>
 
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.14.1/moment.min.js"></script>

<!-- autocomplete.js scripts -->
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- autocomplete.js scripts end -->
 
<!-- chart.js Scripts start -->



         <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.js" type="text/javascript"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.bundle.min.js" type="text/javascript"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.js" type="text/javascript"></script>
         <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.3.0/Chart.min.js" type="text/javascript"></script>
         
       
         
 		 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/vis/4.16.1/vis.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/vis/4.16.1/vis.js"></script>

        <link href="https://cdnjs.cloudflare.com/ajax/libs/vis/4.16.1/vis.min.css" rel="stylesheet" type="text/css" />
        <link href="https://cdnjs.cloudflare.com/ajax/libs/vis/4.16.1/vis.css" rel="stylesheet" type="text/css" />
 
<!-- Chart.js Scripts end -->
 
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css" />
<link rel="stylesheet" href="http://weareoutman.github.io/clockpicker/dist/jquery-clockpicker.css" />
<link rel="stylesheet" href="clockpicker-12-hour-option.css" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/clockpicker/0.0.7/jquery-clockpicker.min.js"></script>
  
<title>TTD</title>
</head>
<style>

/* Box styling  */
 .boxx{
    background-color: white;
    width: 300px;
    border: 1px solid red;
    padding: 25px;
    margin: 25px;
}

/* Loader Styling */
.loader {
    border: 16px solid #f3f3f3; /* Light grey */
    border-top: 16px solid #3498db; /* Blue */
    border-radius: 50%;
    width: 120px;
    height: 120px;
    animation: spin 2s linear infinite;
}

@keyframes spin {
    0% { transform: rotate(0deg); }
    100% { transform: rotate(360deg); }
}

#playground-container {
    height: 500px;
    overflow: hidden !important;
    -webkit-overflow-scrolling: touch;
    }
    .main{
 	margin:50px 15px;
}
hr{
	width: 10%;
	color: #fff;
}

/* form group styling  */
.form-group{
	margin-bottom: 15px;
}

/* label styling */
label{
	margin-bottom: 15px;
}
 
 /*  main login styling */
.main-login{
 	background-color: #fff;
    /* shadows and rounded borders */
    -moz-border-radius: 2px;
    -webkit-border-radius: 2px;
    border-radius: 2px;
    -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
    box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);

}

/* form control styling */
.form-control {
    height: auto!important;
padding: 8px 12px !important;
}

/* input group styling */
.input-group {
    -webkit-box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
    -moz-box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
    box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.21)!important;
}

/* button styling */
#button {
    border: 1px solid #ccc;
    margin-top: 28px;
    padding: 6px 12px;
    color: #666;
    text-shadow: 0 1px #fff;
    cursor: pointer;
    -moz-border-radius: 3px 3px;
    -webkit-border-radius: 3px 3px;
    border-radius: 3px 3px;
    -moz-box-shadow: 0 1px #fff inset, 0 1px #ddd;
    -webkit-box-shadow: 0 1px #fff inset, 0 1px #ddd;
    box-shadow: 0 1px #fff inset, 0 1px #ddd;
    background: #f5f5f5;
    background: -moz-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
    background: -webkit-gradient(linear, left top, left bottom, color-stop(0%, #f5f5f5), color-stop(100%, #eeeeee));
    background: -webkit-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
    background: -o-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
    background: -ms-linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
    background: linear-gradient(top, #f5f5f5 0%, #eeeeee 100%);
    filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f5f5f5', endColorstr='#eeeeee', GradientType=0);
}
.main-center {
 	margin-top: 30px;
 	margin: 0 auto;
 	max-width: 400px;
    padding: 10px 40px;
	background:#009edf;
	    color: #FFF;
    text-shadow: none;
	-webkit-box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);
-moz-box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);
box-shadow: 0px 3px 5px 0px rgba(0,0,0,0.31);

}
span.input-group-addon i {
    color: #009edf;
    font-size: 17px;
}

.login-button{
	margin-top: 5px;
}

.login-register{
	font-size: 11px;
	text-align: center;
}

/* nav tabs styling */
.nav-tabs { border-bottom: 2px solid #DDD; }
    .nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover { border-width: 0; }
    .nav-tabs > li > a { border: none; color: #666; }
        .nav-tabs > li.active > a, .nav-tabs > li > a:hover { border: none; color: #4285F4 !important; background: transparent; }
        .nav-tabs > li > a::after { content: ""; background: #4285F4; height: 2px; position: absolute; width: 100%; left: 0px; bottom: -1px; transition: all 250ms ease 0s; transform: scale(0); }
    .nav-tabs > li.active > a::after, .nav-tabs > li:hover > a::after { transform: scale(1); }
.tab-nav > li > a::after { background: #21527d none repeat scroll 0% 0%; color: #fff; }
.tab-pane { padding: 15px 0; }
.tab-content{padding:20px}
.card {background: white none repeat scroll 0% 0%; background-opacity:0.9%;box-shadow: 0px 1px 3px rgba(0, 0, 0, 0.3); margin-bottom: 30px; width: 100%}
body{ background:url("  data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBw0NDQ0NDQ0NDQ0NDQ0NDQ0NDQ8NDQ0NFREWFhURFRUYHSggGBolGxUVITEhJSkrLi4uFx8zOD8tNygtLisBCgoKDQ0NDw0NDysZFRkrKzcrNzctKzctLS0rLSstLS0rKy03KystKystKystKystLSsrKysrKy0tKysrKysrK//AABEIALEBHAMBIgACEQEDEQH/xAAZAAADAQEBAAAAAAAAAAAAAAABAgMABAb/xAAYEAEBAQEBAAAAAAAAAAAAAAAAAQISEf/EABoBAAMBAQEBAAAAAAAAAAAAAAABAgMEBgX/xAAYEQEBAQEBAAAAAAAAAAAAAAAAARESAv/aAAwDAQACEQMRAD8A8thbESwth9l5eq5imYTKuYipNmKZgZiuYi1cjSGmRkPIztaSBMmmTSGmWd9NJ5JyPKkhuU30qeUuR5V5HlPSuUeB5V5Hkuj5R4bhbkeS6PlDgeFuR5HQ5Q4bhfluR0fKHDcrctyOhyhchcL8luT6GIXJLl0XJLFT0WOfWU9R0aieouVNjm1lHcdOojuNZU2ObcQ3HVuIbjWE5tRKx0aiVjSKi+Vsp5VyK46rlXKeFcopRXKmSZUyy9NIfKkhMqRl6aw2YeQsPGVaRpDSDBiLVxvB8GCnVYHjeG8HwtPC8j4bwfE6eE8bw/jeDTJ43hwGgvgeGY9IlhLFKWqhJ2E0ppPS4VT0lpXSWmkTUdxHa2ktNYiobQ3HRtHTaEhqJeL6idjSGrmKZJmKZOuSqZWyllXKKlXKmUsq5ZemkUikSypGXprFIeJxSMq0h4aEh4irhoIQUVUNBLDJUwgxGLB6wDMAenhaJa1C1UhaFLa1pbVSJ0Knqm1U9VpIm0uqlo+qnqtJE2p6S0pqpaayJ1LSWldJaaQJaJ4poi4qKRTJIfKnJ6UyplOHympVyrlHKmazqorKpmpSnlZWNJVZTxKU8rOxrKrKaVOU0rOxcqkNKSUfUWL0/o+k9H1OHp/W9L63owab0PS+t6MGm9LaFoWnhaNpbQtLauRN9DaS1rSWqkTfTWk1WtTtaTyi0NVPVNqp6rSQtLqpaPqp6aSFpNJ6PSVcOEpKekU0imTwkPDcvo8PE4eFUKynylKeVFEWlPKlKeVnY0lVlPKlKaVnYuVaU0qUppUWNJVZRlTmhlRitU9H1P1ui5PpT0fU+m6HJdH9D0nQXR8jo9oekug9OeU9HuiWltC6XPKb6G0tpbS2qnlN9DaS0LSWrnlHTWk1WtJa0kGhanTWk1VSGXRNUbS01wtKNLVNIpDxOU0psPUUlPKnDQmVVhpU5TSppKynlSlNKmxUqsp5UZTys7FyqymlSlHpNipVejSo9G9TyfSvTepej0XI6U6bpLpuj5HSnTdJ9BdHyXSnRbonQXR8pvo90W6JaF0qeU9GtLaF0W1UidG0loWltXINa0lo0lp4Na0lo2ktPFwKS0aW02nkLSWjaS0NYpKeVHNPmmy9LSnlSlNKGNVlPKlKaUkqyjKnKaVOBSU00lKPpYerTQ+oyjNFg1b0ekvR9LD6U6HpL1vS5HSnTdJ9N0fJdKdB0n63p4Wn6DonQdHhaf0Lol0Fp4WntLaX0tp4DWltD0tp4Y2ltC0toVBtJaNpLQ0ka1PVG1PVNrI1pfWtJaGsh5TzTnmlJoMvTomjSoZ0eaDGxeU00hKaaCF5RlSmhmiJaUZpL0ZRhK+j6l6PpYFOh6T9b0YNU6bonrejCP03SfrengU6DonrejCN63pPQ9AP6HpfQ9M8NaHpbQugeGtC0tpfQqQ1pbS2luguQbS2haS0NJGtJa10nrQayDdE9C0loaSDNKZ05po80rGfqOnOjzTmmjzQYWOiaPNOabPNEix0TQzSE0aaCcX6NNITRpohi3RppDo3QLFem9S6HoDFfW9S6boFivoep9N0DxT1uk/W9Aw/Qek6DoHinoek9LdA+VLQ6T6DoHPKl0W6J6W6C55PdFtJdlugueT3Sd0W6JdBpIa6JdFuiWm0kNdF9LaX01SBKaaS9H00+vK00eaQlNNBlfLomjTTmmjzZYzvl0TR5pzTRpoJ5dM0M05+jTRFy6JoekJoZoDlfoekOhmiLlbpukeh6B8q9N0l03QHKvTdJdB0D5V6a6S6C7B8qdB0n0W6B8q3QXSXQdBU8qXRbol0W6Cp5PdFuiXRbo1yHuiXRbS3QVIa0l0W0LTXINpfS2h6FYb1vQYxYb0ZSQYEXyeU00m0oRfK0ozSUoyguVpo02h0PRJ5Xmhm0JoegXLo7btGaGaLC5W6HpHpugOVum6R6HoHyr0HSfQdAcq9B0n6HoPlS6DpO0Ogrk/QXSfQXQw8PdB0T0PTVIe6LdFtC0KwfQtLaFoPBtLaFoBWNaDADxVgYwLMwTRFmCawswSMFmBURjMRMMZgQwzMCAYzACDMRsVmNQFFgALRYGFBmC4AVmBloCwMoVmBgzMDf//Z")center center;
/* http://www.bluitgroup.com/wp-content/uploads/2015/11/plain-light-color-for-guest-background.jpg */
background-repeat: no-repeat;
background-attachment: fixed;
background-position: center;
background-size: 1350px 650px;

}

.panel-default {
opacity: 0.9;
margin-top:30px;
}
.form-group.last { margin-bottom:0px; }
div.scroll {
    /* width: 100%; */ /* Optional */
    /* border-collapse: collapse; */
    border-spacing: 0;
    border: 2px solid black;
}

div.scroll input_qcControllfields_wrap,
table.scroll thead { display: block; }
 
table.scroll input_qcControllfields_wrap {
    height: 100px;
    overflow-y: auto;
    overflow-x: hidden;
}
* {
  box-sizing: border-box;
}

.row > .column {
  padding: 0 28px;
}

.row:after {
  content: "";
  display: table;
  clear: both;
}

.column {
  float: left;
  width: 25%;

}

/* The Modal (background) */
.modal {
  display: none;
  position: fixed;
  z-index: 1;
  padding-top: 100px;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto;
  background-color: rgba(0,0,0,0.7);
}

/* Modal Content */
.modal-content {
  position: relative;
  background-color: #fefefe;
  margin: auto;
  padding: 0;
  width: 90%;
  max-width: 1200px;
}

/* The Close Button */
.close {
  color: #fff;
  position: absolute;
  top: 10px;
  right: 25px;
  font-size: 45px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #fff;
  text-decoration: none;
  cursor: pointer;
}

/* dropdown submenu styling */
.dropdown-submenu {
    position: relative;
}

.dropdown-submenu>.dropdown-menu {
    top: 0;
    left: 100%;
    margin-top: -6px;
    margin-left: -1px;
    -webkit-border-radius: 0 6px 6px 6px;
    -moz-border-radius: 0 6px 6px;
    border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
    display: block;
}

.dropdown-submenu>a:after {
    display: block;
    content: " ";
    float: right;
    width: 0;
    height: 0;
    border-color: transparent;
    border-style: solid;
    border-width: 5px 0 5px 5px;
    border-left-color: #ccc;
    margin-top: 5px;
    margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
    border-left-color: #fff;
}

.dropdown-submenu.pull-left {
    float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
    left: -100%;
    margin-left: 10px;
    -webkit-border-radius: 6px 0 6px 6px;
    -moz-border-radius: 6px 0 6px 6px;
    border-radius: 6px 0 6px 6px;
}

.news:hover .img-figure img {
	-webkit-transform: scale(1.10);
	-moz-transform: scale(1.10);
	-ms-transform: scale(1.10);
	-o-transform: scale(1.10);
	transform: scale(1.10);
}

.btn span.glyphicon {    			
	opacity: 0;	
				
}
.btn.active span.glyphicon {				
	opacity: 1;	
   width:50px;		
}
.new{
margin-top:-34px;
margin-right:-54%;
margin-top:0px;
}
.rare{ 
margin-top:0%;
padding:0%;
right:-9%;
}
/* Time Dropdown start  */

.btn-select {
    position: relative;
    padding: 0;
    
    width: 63%;
    border-radius: 0;
    margin-bottom: 20px;
}

.btn-select .btn-select-value {
    padding: 6px 12px;

    position: absolute;
    left: 0;
    right: 34px;
    text-align: center;
    text-overflow: ellipsis;
    overflow: hidden;
    border-top: none !important;
    border-bottom: none !important;
    border-left: none !important;
}

.btn-select .btn-select-arrow {
    float: right;
    left:10px;
    line-height: 20px;
    padding: 6px 10px;
    top: 0;
}

.btn-select ul {
    display: none;
    background-color: white;
    color: black;
    
    clear: both;
    list-style: none;
    padding: 0;
    margin: 0;
    border-top: none !important;
    position: absolute;
    left: -1px;
    right: -1px;
    top: 33px;
    z-index: 999;
} 
/* Primary Start */
.btn-select.btn-primary:hover, .btn-select.btn-primary:active, .btn-select.btn-primary.active {
    border-color: #286090;
}

.btn-select.btn-primary ul li.selected {
    background-color: #2e6da4;
    color: white;
}

.btn-select.btn-primary ul {
    border: #2e6da4 1px solid;
}

.btn-select.btn-primary .btn-select-value {
    background-color: #428bca;
    border: #2e6da4 1px solid;
}

.btn-select.btn-primary:hover, .btn-select.btn-primary.active {
    background-color: #286090;
}
/* Primary End */

.btn-select.btn-select-light .btn-select-value {
    background-color: white;
    color: black;
}
.scrollable-menu {
    height: auto;
    max-height: 200px;
    overflow-x: hidden;
}

/* Time Dropdown End */

.sidenav {
    height: 125%;
    width: 0;
    position: absolute;
    z-index: 1;
    top: 23%;
    right: 0;
    background-color: rgba(134, 226, 247, 0.91);
    overflow-x: hidden;
    transition: 0.5s;
    padding-top: 60px;
}

.sidenav a {
    padding: 8px 8px 8px 32px;
    text-decoration: none;
    font-size: 18px;
    color: #000000;
    font-family:Timesnewroman;
    display: block;
    transition: 0.3s;
}

.sidenav a:hover, .offcanvas a:focus{
    color: #ffffff;
}

.sidenav .closebtn {
    position: absolute;
    top: 0;
    right: 25px;
    font-size: 36px;
    margin-left: 50px;
    color: #000000;
}

@media screen and (max-height: 450px) {
  .sidenav {padding-top: 15px;}
  .sidenav a {font-size: 18px;}
}
.log {
    position: relative;
    background-color: #ffffff;
    border: none;
    font-size: 20px;
    font-family: Bodoni;
    color: red;
    padding: 5px;
    width: 100px;
    text-align: center;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    text-decoration: none;
    overflow: hidden;
    cursor: pointer;
}

.log:after { 
    content: "";
    background: black;
    display: block;
    position: absolute;
    padding-top: 300%;
    padding-left: 350%;
    margin-left: -20px!important;
    margin-top: -120%;
    opacity: 0;
    transition: all 0.8s
}

.log:active:after {
    padding: 0;
    margin: 0;
    opacity: 1;
    transition: 0s
}
</style>
<body>

 <%
    String name = (String) session.getAttribute("Email");  
   if(name == null){
	   response.sendRedirect("index.jsp"); 
	   }
   else
   {
	   out.println(" ");
   }

   %> 
   
   <input type="hidden" class="form-control" id="reloadValue1">
    
<%
 response.setHeader("Cache-Control","no-cache"); //HTTP 1.1 
 response.setHeader("Pragma","no-cache"); //HTTP 1.0 
 response.setDateHeader ("Expires", 0); //prevents caching at the proxy server  

 //connecting to the Database
MongoCredential credential = MongoCredential.createCredential("ttd@", "TTD", "ttd".toCharArray());
		MongoClient mongoClient = new MongoClient(new ServerAddress("52.38.31.24",27795),Arrays.asList(credential));
 //connecting to the particular database
DB db = mongoClient.getDB("TTD");

%>


	<div class="container">
		<!-- Top banner starts  -->
		<div class="img-figure" style="margin-top: 3%">
			<div class="panel panel-default"
				style="box-shadow: 3px 3px 1px gray;">
				<div class="panel-heading custom_class"
					style="height: 160px; background-color: white;">
					<h1 align="center"
						style="margin-top: 0%; margin-left: -4%; color: #3876c2; text-shadow: 2px 2px 1px black; font-size: 45px; font-family: Timesnewroman">
						Forecasting of Piligrim Influx <br>&<br>Estimated
						Darshan Time
					</h1>
				</div>
				<div id="image"
					style="float: right; margin-RIGHT: 2%; margin-top: -11%">
					<a href="http://www.exafluence.com" target="new"> <img
						src="https://i.imgur.com/Av2h6H8.png" alt="exafluence"
						class="media-object" style="width: 210px; height: 90px"
						align="right" /></a>
					<p style="margin-left: 30%">
						<b>Beta Ver.1.0</b>
					</p>
				</div>
				<div id="image"
					style="float: left; margin-right: 4%; margin-left: 1%; margin-top: -13%;">
					<img src="https://i.imgur.com/4dWP0Ib.png" class="media-object"
						style="width: 150px; height: 130px" align="left" />
				</div>
			</div>
		</div>
		<!-- Top banner ends  -->


		<div class="row">
			<!-- Nav tabs starts -->
			<div class="card">
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#home"
						aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
					<li role="presentation" class="buss"><a href="#businessrules"
						aria-controls="businessrules" id="br1" type="submit" role="tab"
						data-toggle="tab">Business Rules</a></li>
					<li role="presentation"><a href="#admin" aria-controls="admin"
						role="tab" data-toggle="tab">Admin</a></li>
					<li role="presentation"><a href="#settings"
						aria-controls="settings" role="tab" data-toggle="tab">Analytics</a></li>
					<li role="presentation"><a href="#help" aria-controls="help"
						role="tab" data-toggle="tab">Help</a></li>

					<a href="HomeLogout">
						<li role="presentation" style="float: right; text-color: red"><button
								type="submit" class="button log">Logout</button></li>
					</a>

				</ul>

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="home"
						style="background-image: url(https://i.imgur.com/AP1XIPv.jpg); background-repeat: no-repeat; background-size: 1130px 450px; height: 330px">
						-->

						<!-- Datapicker and day starts  -->
							
						<div class="form-group">

							<div class="col-xs-2">

								<b><input class="form-control" id="date" name="date"
									placeholder="MM/DD/YYY"readonly/ ></b><br>

							</div>
							<div class="col-xs-2" style="margin-left: -1.5%">
								<p>
									<b type="text" class="form-control" id="demo"></b>
								</p>

							</div>
						</div>
						<!-- Datapicker and day ends  -->
						<br>
						<br>
						<br>
						<div class="row">
						
						<!-- Vaikuntam Queue Complex submenu starts here  -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li class="dropdown" style="left: 160%; bottom: 75px"><a
										href="#" class="dropdown-toggle form-control"
										data-toggle="dropdown" id="br" type="submit" role="button"
										aria-haspopup="true"><b>Vaikuntam Queue Complex</b> <span
											class="caret"></span></a>
										<ul class="dropdown-menu">

											<li class="dropdown-submenu"><a href="#">Divya
													Darshan(VQC-I)</a>
												<ul class="dropdown-menu">

													<li><a href="#" onclick="openModal();currentSlide(1)"><strong>One
																Hour </strong></a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="openModal();currentSlide(2)"><strong>Half
																an Hour</strong> </a></li>
												</ul></li>
											<li class="divider"></li>
											<li class="dropdown-submenu"><a href="#">Sarva
													Darshan(VQC-II)</a>
												<ul class="dropdown-menu">
													<li><a href="#" onclick="openModal();currentSlide(3)"><strong>One
																Hour</strong> </a></li>
													<li class="divider"></li>
													<li><a href="#" onclick="openModal();currentSlide(4)"><strong>Half
																an Hour </strong></a></li>
												</ul></li>
										</ul></li>
								</ul>
							</div>
								<!-- Vaikuntam Queue Complex submenu ends here  -->
							
							<!-- Q-Line dropdowns starts here -->
							<div class="collapse navbar-collapse"
								id="bs-example-navbar-collapse-1">
								<ul class="nav navbar-nav">
									<li class="dropdown" style="left: 700%; bottom: 113px"><a
										href="#" class="dropdown-toggle form-control"
										data-toggle="dropdown" role="button" aria-haspopup="true"><b>Q-Line</b>
											<span class="caret"></span></a>
										<ul class="dropdown-menu">

											<li class="dropdown-submenu" role="menu"
												aria-labelledby="dropdownMenu"><a tabindex="-1"
												href="#" onclick="openModal();currentSlide(5)">Divya
													Darshan(VQC-I)</a></li>
											<li class="divider"></li>
											<li class="dropdown-submenu"><a href="#"
												onclick="openModal();currentSlide(6)">Sarva
													Darshan(VQC-II)</a></li>
										</ul></li>
								</ul>
							</div>
							<!-- Q-Line dropdowns ends here -->
						</div>
						
						<!-- Vaikuntam Queue Complex and Q-Line modal starts here  -->
						<div id="myModal" class="modal">
							<span class="close cursor" onclick="closeModal()">&times;</span>
							<div class="modal-content">
								<div class="mySlides">
									<!-- Display time and date in modal -->
									<div class="form-group ">

										<div class="col-xs-2 pull-right">
										
											<b><input class="form-control" id="datevqc1"
												name="datevqc1" placeholder="MM/DD/YYY" readonly /></b><br>

										</div>
										<div class="col-xs-2 pull-right">
											<p>
												<b type="text" class="form-control" id="demovqc1"></b>
											</p>
										
										</div>
									</div>

									<!-- End -->
									
									<!-- Divya Darshan VQC-1 Q-Line starts here -->
									<div class="well">
										<h4>
											<b>Please enter the VQC-I Details (Hour)</b>
										</h4>
										<table class="table table-bordered" id="t1">
											<thead>
												<tr>
													<th style="width: 8%">Compartment in time</th>
													<th style="text-align: center">People waiting in
														compartments</th>
													<th style="text-align: center">People entered into
														compartment</th>
													<th style="text-align: center">Expected time of
														release(HH:MM)</th>
													<th style="text-align: center">Expected waiting
														duration in compartment</th>
													<th style="width: 16%">No.of people considered for
														Darshan</th>
												
												</tr>
											<tbody>

													<!-- for loop for displaying the time  -->
													
												<% for(int i=0;i<24;i++) { %>
												<tr>
													<td class="form-control" style="margin-top: 10%">
														<%  out.print(i); %>:00
													</td>

													<td><b><input id="wait<%  out.print(i); %>"
															type="text" class="form-control"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)" readonly></b></td>

													<td><b><input id="AddedPeople" name="value1"
															maxlength="5" type="text"
															data-id1="<%  out.print(i); %>:00"
															class="form-control vqc1 new"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)"></b></td>

													<td><div class="col-sm-12">
															<b> <input id="ESDI<%  out.print(i); %>" name="est1"
																type="text" class="form-control" placeholder="HH:MM"
																onkeypress="return isNumber(event)" readonly>
															</b>
														</div></td>
													<td>
														<div class="col-sm-12">
															<b><input id="ESDIMin<%  out.print(i); %>"
																name="est1min" type="text" class="form-control"
																placeholder="Minutes"
																onkeypress="return isNumber(event)" readonly>
														</div>
													</td>
													<td><b><input id="pe1<%  out.print(i); %>"
															type="text" class="form-control"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)" readonly></b></td>

												</tr>
												<% } %>
											</tbody>
										</table>
									</div>
								</div>
								<!-- Divya Darshan VQC-1 Q-Line ends here -->
								<div class="mySlides">

								<!-- Sarva Darshan VQC-2 Q-Line starts here -->
									
									<div class="well">
										<h4>
											<b>Please enter the VQC-I Details (Half an Hour)</b>
										</h4>
										<table class="table table-bordered" id="t1">
											<thead>
												<tr>
													<th style="width: 8%">Time Slot</th>
													<th style="text-align: center">Waiting People</th>
													<th style="text-align: center">Added People</th>
													<th style="width: 16%">People Had Darshan</th>
													<th style="width: 16%">Remaining People</th>
													<!-- <th style="width:16%">Remaining Slot</th> -->
													<th style="text-align: center">Estimated Darshan
														Time(HH:MM)</th>

												</tr>
											<tbody>
												<% double [] number = {3.00, 3.30, 4.00, 4.30, 5.00, 5.30, 6.00, 6.30,7.00,7.30,8.00, 8.30, 9.00, 9.30,10.00,11.00,11.30,12.00,12.30,13.00,13.30,14.00,14.30,15.00,15.30,16.00,16.30,17.00,17.30,18.00,18.30,19.00,19.30,20.00,21.30,22.00,22.30,23.00,23.30};  %>
												<% for ( double P: number){ %>
												<tr>
													<td class="form-control" style="margin-top: 10%">
														<%  out.print(P); %>0
													</td>
													<td><input id="PeopleWaiting" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="AddedPeople" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="Peoplehaddarshan" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="Remainingpeople" type="text"
														class="form-control" placeholder="Rem.No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<!--  <td><input id="Remainingslot" type="text" class="form-control" placeholder="Remaining slot" onkeypress="return isNumber(event)"></td> 
        -->
													<td><div class="col-sm-6">
															<input type="text" id="Esttime" class="form-control"
																placeholder="HH:MM">
														</div>
														<div class="col-sm-6">
															<input type="text" id="Estmin" class="form-control"
																placeholder="In Minutes">
														</div></td>
												</tr>
												<% } %>
											</tbody>
										</table>
									</div>
								</div>
								<!-- Sarva Darshan VQC-2 Q-Line ends here -->
								
								
								<!-- Divya Darshan VQC-1 Vaikuntam Queue Complex starts here -->
								<div class="mySlides">
									
									<!-- Display time and date in modal -->

									<div class="form-group">

										<div class="col-xs-2 pull-right">
												<b><input class="form-control" id="datevqc2"
												name="datevqc1" placeholder="MM/DD/YYY" readonly /></b><br>

										</div>

										<div class="col-xs-2 pull-right" style="margin-left: 1.5%">
											<p>
												<b type="text" class="form-control" id="demovqc2"></b>
											</p>
									</div>
									</div>
									<!-- End -->

									<div class="well">
						
										<h4>
											<b>Please enter the VQC-II Details (Hour)</b>
										</h4>
										<table class="table table-bordered">
											<thead>
												<tr>
													<th style="width: 8%">Compartment in time</th>
													<th style="text-align: center">People waiting in
														compartment</th>
													<th style="text-align: center">People entered in
														compartment</th>
													<th style="text-align: center">Expected time of
														release(HH:MM)</th>
													<th style="text-align: center">Expected waiting
														duration in compartment</th>
													<th style="width: 16%">No. of people considered for
														Darshan</th>
												</tr>
											<tbody>

												<% for(int i=0;i<24;i++) { %>
												<tr>
													<td class="form-control" style="margin-top: 10%">
														<%  out.print(i); %>:00
													</td>
													<td><b><input id="waiting2<%  out.print(i); %>"
															type="text" class="form-control"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)" readonly></b></td>
													<td><b><input id="AddedPeople" type="text"
															name="value2" maxlength="5"
															data-id2="<%  out.print(i); %>:00"
															"  class="form-control vqc2 new"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)"></b></td>
													<td><div class="col-sm-12">
															<b> <input id="ESDII<%  out.print(i); %>" name="est2"
																type="text" class="form-control" placeholder="HH:MM"
																onkeypress="return isNumber(event)" readonly>
															</b>
														</div></td>
													<td>
														<div class="col-sm-12">
															<b><input id="ESDIIMin<%  out.print(i); %>"
																name="est2min" type="text" class="form-control"
																placeholder="Minutes"
																onkeypress="return isNumber(event)" readonly>
														</div>
													</td>
													<td><b><input id="peo<%  out.print(i); %>"
															name="people" type="text" class="form-control"
															placeholder="No.of.Pilgrims"
															onkeypress="return isNumber(event)" readonly></b></td>

												</tr>
												<%} %>
											</tbody>
										</table>

									</div>
								</div>
								<!-- Divya Darshan VQC-1 Vaikuntam Queue Complex ends here -->
								
								<div class="mySlides">
								
								<!-- Sarva Darshan VQC-2 Vaikuntam Queue Complex starts here -->
									<div class="well">
										<h4>
											<b>Please enter the VQC-II Details (Half an Hour)</b>
										</h4>
										<table class="table table-bordered">
											<thead>
												<tr>
													<th style="width: 8%">Time Slot</th>
													<th style="text-align: center">Waiting People</th>
													<th style="text-align: center">Added People</th>
													<th style="width: 16%">People Had Darshan</th>
													<th style="width: 16%">Remaining People</th>
													<!--   <th style="width:16%">Remaining Slot</th> -->
													<th style="text-align: center">Estimated Darshan
														Time(HH:MM)</th>
												</tr>
											<tbody>
												<% double [] number1 = {3.00, 3.30, 4.00, 4.30, 5.00, 5.30, 6.00, 6.30,7.00,7.30,8.00, 8.30, 9.00, 9.30,10.00,11.00,11.30,12.00,12.30,13.00,13.30,14.00,14.30,15.00,15.30,16.00,16.30,17.00,17.30,18.00,18.30,19.00,19.30,20.00,21.30,22.00,22.30,23.00,23.30};  %>
												<% for ( double P: number1){ %>
												<tr>
													<td class="form-control" style="margin-top: 10%">
														<%  out.print(P); %>0
													</td>
													<td><input id="PeopleWaiting" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="AddedPeople" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="Peoplehaddarshan" type="text"
														class="form-control" placeholder="No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<td><input id="Remainingpeople" type="text"
														class="form-control" placeholder="Rem.No.of.Pilgrims"
														onkeypress="return isNumber(event)"></td>
													<!-- <td><input id="Remainingslot" type="text" class="form-control" placeholder="Remaining slot" onkeypress="return isNumber(event)"></td> 
         -->
													<td><div class="col-sm-6">
															<input type="text" id="Esttime" class="form-control"
																placeholder="HH:MM">
														</div>
														<div class="col-sm-6">
															<input type="text" id="Estmin" class="form-control"
																placeholder="In Minutes">
														</div></td>
												</tr>
												<% } %>
											</tbody>
										</table>
									</div>
								</div>
							</div>
							<div class="mySlides">
								<div class="well">
									<h4>
										<b>Sarva Darshanam Queue(VQC-I)</b>
									</h4>
									<div id="mySidenav" class="sidenav">
										<a href="javascript:void(0)" class="closebtn"
											onclick="closeNav()">&times;</a> <b> <a>1.VQC-2</a> <a>2.Arch</a>
											<a>3.North Bridge of ATC Parking</a> <a>4.North Bridge</a>
											<a>5.Luggage Centre</a> <a>6.Toilet Block-1</a> <a>7.Toilet
												Block-2</a> <a>8.Toilet Block-3</a> <a>9.Toilet Block-4</a> <a>10.Culvert</a>
											<a>11.MBC-26</a> <a>12.Gokulam Circle</a> <a>13.TBC
												Bridge</a> <a>14.Gokulam Circle (Temp. Qline)</a> <a>15.MBC-26
												(Temp. Qline)</a></b>

									</div>
									<span
										style="font-size: 25px; cursor: pointer; float: right; margin-top: -4%"
										onclick="openNav()">&#9776; Que Reference</span>
									<script>
function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}
</script>
									<br>
									<table class="table table-bordered">
										<thead>
											<tr>
												<th style="text-align: center">Queue in Time</th>
												<th style="text-align: center">Name of the Entering
													Point</th>
												<th style="text-align: center">No.of.People</th>
												<th style="text-align: center">Expected Time to reach
													Queue Complex</th>
											</tr>
										<tbody>
											<% for(int i=3;i<24;i++) { %>
											<tr>
												<td class="form-control"
													style="margin-top: 5%; text-align: center"><b>
														<%  out.print(i); %>:00
												</b></td>
												<td><input id="Enteringpoint" type="text"
													class="form-control" placeholder="Entering Point"></td>
												<td><input id="NoofPeople" type="text"
													class="form-control" placeholder="No.of.Pilgrims"
													onkeypress="return isNumber(event)"></td>
												<td style="width: 30%"><input id="Expectedtime"
													type="text" class="form-control"
													placeholder="Hours:Minutes"
													onkeypress="return isNumber(event)"></td>

											</tr>
											<% } %>
										</tbody>
									</table>

								</div>

							</div>

							<div class="mySlides">
								<div class="well">
									<h4>
										<b>Divya Darshanam Queue(VQC-II)</b>
									</h4>
									<div id="mySidenav1" class="sidenav">
										<a href="javascript:void(0)" class="closebtn"
											onclick="closeNav1()">&times;</a> <b> <a>1.VQC1
												Scanning</a> <a>2.S-W corner of Anantalwar Garden Compound
												wall</a> <a>3.North Bridge</a> <a>4.Food Point</a> <a>5.East
												corner of Deer Statue</a> <a>6.West Corner of Deer Statue</a> <a>7.Akkagarla
												Temple</a> <a>8.Padmavathi Parinayam Jn.</a> <a>9.N.G.Garden
												Maingate</a> <a>10.Dasavatarala Circle</a> <a>11.ATRH Gate</a>
											<a>12.SVRH Jn.</a> <a>13.Hotel Sarangi</a> <a>14.NGRH-1</a>
											<a>15.RB Centre Gate</a> <a>16.VQC-2 Jn.</a>
										</b>

									</div>

									<span
										style="font-size: 25px; cursor: pointer; float: right; margin-top: -4%"
										onclick="openNav1()">&#9776; Que Reference</span>

									<script>
function openNav1() {
    document.getElementById("mySidenav1").style.width = "250px";
}

function closeNav1() {
    document.getElementById("mySidenav1").style.width = "0";
}
</script>

									<br>
									<table class="table table-bordered">
										<thead>
											<tr>

												<th style="text-align: center">Queue in Time</th>
												<th style="text-align: center">Name of the Entering
													Point</th>
												<th style="text-align: center">No.of.People</th>
												<th style="text-align: center">Expected Time to reach
													Queue Complex</th>
											</tr>
										<tbody>
											<% for(int i=3;i<24;i++) { %>
											<tr>
												<td class="form-control"
													style="margin-top: 5%; text-align: center"><b>
														<%  out.print(i); %>:00
												</b></td>
												<td><input id="Enteringpoint" type="text"
													class="form-control" placeholder="Entering Point"></td>
												<td><input id="NoofPeople" type="text"
													class="form-control" placeholder="No.of.Pilgrims"
													onkeypress="return isNumber(event)"></td>
												<td style="width: 30%"><input id="Expectedtime"
													type="text" class="form-control"
													placeholder="Hours:Minutes"
													onkeypress="return isNumber(event)"></td>
											</tr>
											<% } %>
										</tbody>
									</table>
								</div>
							</div>
							<div class="caption-container">
								<p id="caption"></p>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="businessrules">
						<nav class="navbar navbar-inverse"
							style="margin-top:1%;width:98%;left:1%">
						<div class="container-fluid">

							<ul class="nav nav-pills nav-justified">
								<li class="active"><a data-toggle="pill"
									style="font-family: TimesnewRoman; font-size: 130%" href="#sun">Sunday</a></li>
								<li><a data-toggle="pill" href="#mon"
									style="font-family: TimesnewRoman; font-size: 130%">Monday</a></li>
								<li><a data-toggle="pill" href="#tue"
									style="font-family: TimesnewRoman; font-size: 130%">Tuesday</a></li>
								<li><a data-toggle="pill" href="#wed"
									style="font-family: TimesnewRoman; font-size: 130%">Wednesday</a></li>
								<li><a data-toggle="pill" href="#thurs"
									style="font-family: TimesnewRoman; font-size: 130%">Thursday</a></li>
								<li><a data-toggle="pill" href="#fri"
									style="font-family: TimesnewRoman; font-size: 130%">Friday</a></li>
								<li><a data-toggle="pill" href="#sat"
									style="font-family: TimesnewRoman; font-size: 130%">Saturday</a></li>
							</ul>
						</div>
						</nav>
						
						
						<!-- tab contents starts here -->
						<div class="tab-content">
						
						<!-- Sunday Tab starts here -->
							<div id="sun" class="tab-pane fade in active">
								<button class="add_qcControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>

								<br>
								<br>

								<div style="border: 1px solid black; margin-top: -0.5%">
									<div class="input_qcControllfields_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">


										<%
										//Retrieving the Business Rules from the SundayCollection
										
											BasicDBObject Object = new BasicDBObject();

											DBCollection SundayCollection = db.getCollection("Sunday");
											DBCursor cursors = SundayCollection.find();
											int i = 1;
											int a = cursors.size();
											while (cursors.hasNext()) {

												DBObject results = cursors.next();
												Map resultmaps = results.toMap();
										%>

										<div class="input_qcControllfields_wrap">
											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">


													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control "
															id="Business_Rule_Name0<%out.print(resultmaps.get("ID"));%>"
															name="Business_Rule_Name"
															value="<% out.println(resultmaps.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From0<%out.print(resultmaps.get("ID"));%>"
															name="From"
															value="<% out.println(resultmaps.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To0<%out.print(resultmaps.get("ID"));%>" name="To"
															value="<% out.println(resultmaps.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity0<%out.print(resultmaps.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmaps.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity0<%out.print(resultmaps.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmaps.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue0<%out.print(resultmaps.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmaps.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="qc_count" value="0" /> <a
															href="#" class="save_qcfield btn btn-primary"
															attr-id="<%out.println(resultmaps.get("ID"));%>"
															id="Save"
															value="<% out.println(resultmaps.get("ID")); %>"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>
													</div>


													<input type="hidden" name="qc_count" value="0" /><a
														href="#" attr-id="<%out.println(resultmaps.get("ID"));%>"
														id="remove" class="remove_qcfield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>
												</div>
											</div>
										</div>
										<%} %>


										<div class="input_qcControllfields_wrap1"></div>
									</div>
								</div>


								<script type="text/javascript">
   
								// Add Rule script
								
   var qcControllwrapper = $(".input_qcControllfields_wrap1"); //Fields wrapper
   var qcControlladd_button = $(".add_qcControllfield_button"); //Add button ID
   			var qcLabelling = 1; //initlal text box count
  /*  $(qcControlladd_button) */
   $(document).on("click", ".add_qcControllfield_button", function() {


   var parentdivsun =	$( ".input_qcControllfields_wrap" ).children( ".form-group");
   
   var parentdivsunlength=parentdivsun.length;
   var sundaydate = $("#datepicker_s").val();
   var sunday = $("#demos").html();
   var add = "add";
     $(qcControllwrapper)
   	.append('<div class="input_qcControllfields_wrap" ><div class="form-group" style="margin-left: 20px"> <div class="scroll"><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control" id="Business_Rule_Name0'+parentdivsunlength+'" name="Business_Rule_Name" value="" onkeypress="return isWord(event)" placeholder="Name of the B.R"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From0'+parentdivsunlength+'" name="From" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To0'+parentdivsunlength+'" name="To" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity0'+parentdivsunlength+'" name="VQC1_Max_Capacity" onkeypress="return isNumber(event)" placeholder="Nill"></div>'+
   				'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity0'+parentdivsunlength+'" name="VQC1_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue0'+parentdivsunlength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>'+ 
   				'<div class="col-sm-11 nopadding">	<a href="#" attr-id="'+parentdivsunlength+'" id="Save" class="save_qcfield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div><input type="hidden" name="qc_count" value="0"  /><a href="#"  attr-id="'+parentdivsunlength+'" id="remove" class="remove_qcfield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div></div> '); //add input box
   			
   				qcLabelling++; //text box increment
   				
   				//Autocomplete Scripts
   			   $( function() {
   				 var availableTags = [
   			      "Suprabatham",
   			      "Thomala Seva",
   			      "Angapradakshina + SD + DD",
   			      "Koluvu",
   			      "Archana",
   			      "1st Bell",
   			      "Astadalapaada Padmaradhana",
   			      "2nd Bell",
   			      "Nithyaharathi + Break Darshan",
   			      "Break Darshan",
   			      "SD + DD + SED + Supadam",
   			      "PH & SC",
   			      "SD + DD + SED + Supadam",
   			      "SD + DD + SED ",
   			      "3rd Bell",
   			      "SD + DD + SED",
   			      "SD + DD",
   			      "Kalyanostavam Tickets ",
   			      "Sathmora + Nithya Harathi",
   			      "3rd Bell + Naivedyam",
   			      "Saharsa Kalasabishekam",
   			      "Scheme",
   			      "SK Darshan + Nithya Harathi + Break Darshan",
   			      "Kalyanostavam",
   			      "Tiruppavada",
   			      "3rd Bell + Pedda Suddi",
   			      "Abhisekam",
   			      "Nijapada Darshanam",
   			      "Samarpana",
   			      "Thomala + Archana",
   			      "Sathmora + Nithya Harathi",
   			      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
   			     
   			    ];
   			    

   				
   			    $( "#Business_Rule_Name0"+parentdivsunlength ).autocomplete({
   			      source: availableTags
   			    });
   			  } );
   				
   				// Ajax calling getting the Previous rule 'To' Time from the Sunday Collection
   			   $.ajax({

   				
   				 url: 'Createsun',
   				   type:"POST",
   				   data:{add: add},

   				   success : function(responseText) {
   				   	   
   				   $( "#From0"+parentdivsunlength ).val(responseText);

   				   		} 
   				   });
   	 
   });
   
  </script>
								<script>
  
								// Remove the Business Rule script
   $(document).on("click", ".remove_qcfield",
				function(e) { //user click on remove text


var parentdivsun=	$( ".input_qcControllfields_wrap" ).children( ".form-group");
 
var parentdivsunlength=$(this).attr("attr-id");
//alert(parentdivsunlength);

//Ajax call to remove the business rule in th SundayCollection
$.ajax({

url : 'remove',
type:"POST",
data:{parentdivsunlength:parentdivsunlength}
	
	 
});



e.preventDefault();
$(this).parent('div').remove();
qcLabelling--;
				}) 
   							
   
   </script>

								<script>
								
								
      $(document).ready(function(){

	
	$(document).on("click", ".save_qcfield", function() {
 
	var parentdivsun=	$( ".input_qcControllfields_wrap" ).children( ".form-group");
	 
	var parentdivsunlength=$(this).attr("attr-id");
var SundayBusinessRuleName=$("#Business_Rule_Name0"+parentdivsunlength).val();
 
var SundayFromTime=$("#From0"+parentdivsunlength).val();
var SundayToTime=$("#To0"+parentdivsunlength).val();
var SundayVQC1=$("#VQC1_Max_Capacity0"+parentdivsunlength).val();
var SundayVQC2=$("#VQC2_Max_Capacity0"+parentdivsunlength).val();
var SundayPreferenceQueue=$("#Preference_Queue0"+parentdivsunlength).val();
var save = "save";
var SundayDate = $("#date").val();
var SundayDay = $("#demo").html();


if(SundayToTime >= "23:00" && SundayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Monday)");
	}

	//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time
if(SundayFromTime>=SundayToTime || SundayToTime=="" || SundayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
}else{


 
//Ajax call to save the Business rule in the Sunday Collection

    $.ajax({
 		 
 		 url : 'action',
 		 type:"POST",
 		 data:{parentdivsunlength:parentdivsunlength,SundayFromTime:SundayFromTime,SundayBusinessRuleName:SundayBusinessRuleName,SundayToTime:SundayToTime,SundayVQC1:SundayVQC1,SundayVQC2:SundayVQC2,SundayPreferenceQueue:SundayPreferenceQueue, save:save, SundayDate:SundayDate , SundayDay:SundayDay},
 		
 			 success : function(responseText) {
		 			 
		 			if(responseText=="yes"){
		 				alert("Entered From Timing "+SundayFromTime+" is not matching with the previous Business Rules 'To Time'" );
		 				 
		 				window.location.reload(true);
		 			}
		 											}  
 		}); 
       
}
 	});

 	
      });
      </script>



							</div>

<!-- Sunday Tab ends here -->


							<!-- /////////////////////  MONDAY /////////////////// -->


							<!-- Monday Tab starts here -->
							<div id="mon" class="tab-pane fade">
								<button class="add_monControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>

								<div style="border: 1px solid black; margin-top: -0.5%">

									<div class="input_Monday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">


										<%

										//Retrieving the Business Rules from the MondayCollection

	BasicDBObject Mondayobject = new BasicDBObject();

	DBCollection MondayCollection = db.getCollection("Monday");
	DBCursor cursorsmon = MondayCollection.find();
	int im = 1;
	int am= 	cursorsmon.size();
	while (cursorsmon.hasNext()) { 

		 
		 DBObject resultsmon=cursorsmon.next();
		  Map resultmapsmon=resultsmon.toMap();


%>
										<div class="input_monControllfields_wrap">
											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">




													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name1<%out.print(resultmapsmon.get("ID"));%>"
															name="Business_Rule_Name"
															value="<% out.println(resultmapsmon.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From1<%out.print(resultmapsmon.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapsmon.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To1<%out.print(resultmapsmon.get("ID"));%>" name="To"
															value="<% out.println(resultmapsmon.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity1<%out.print(resultmapsmon.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapsmon.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity1<%out.print(resultmapsmon.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapsmon.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue1<%out.print(resultmapsmon.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmapsmon.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="mon_count" value="0" /><a
															href="#"
															attr-idm="<%out.println(resultmapsmon.get("ID"));%>"
															id="Savem" class="save_monfield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>

													</div>

													<input type="hidden" name="mon_count" value="0" /><a
														href="#"
														attr-idm="<%out.println(resultmapsmon.get("ID"));%>"
														id="removem" class="remove_monfield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>
												</div>
											</div>
											<br>
										</div>

										<%} %>



										<div class="input_monControllfields_wrap1"></div>
									</div>

								</div>

								<script type="text/javascript">
								// Add Rule script
   var monControllwrapper = $(".input_monControllfields_wrap1"); //Fields wrapper
   var monControlladd_button = $(".add_monControllfield_button"); //Add button ID
   var monLabelling = 1; //initlal text box count
 
   $(document).on("click", ".add_monControllfield_button", function() {
   
	   var parentdivmon=	$( ".input_monControllfields_wrap" ).children( ".form-group");
	    
	   var parentdivmonlength=parentdivmon.length; 
	   var mondaydate = $("#datepicker_m").val();
	   var mondayday = $("#demom").html();
	   var addm = "addm";
	  
	   
   $(monControllwrapper)
   	.append(' <div class="input_monControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll"><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control" id="Business_Rule_Name1'+parentdivmonlength+'" name="BusinessRules" value="" onkeypress="return isWord(event)" placeholder="Name of the B.R"></div>' +
   		'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From1'+parentdivmonlength+'" name="From" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To1'+parentdivmonlength+'" name="To" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div>' +
   		'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity1'+parentdivmonlength+'" name="VQC1_Max_Capacity" value="" onkeypress="return isNumber(event)" placeholder="Nill"></div>' +
   		'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity1'+parentdivmonlength+'" name="VQC2_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill"></div>' +
   		'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control"  id="Preference_Queue1'+parentdivmonlength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>' +
   		'<div class="col-sm-11 nopadding"><input type="hidden" name="mon_count" value="0" /><a href="#" attr-idm="'+parentdivmonlength+'" id="Savem" class="save_monfield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div><input type="hidden" name="mon_count" value="0"  /><a href="#"  attr-idm="'+parentdivmonlength+'" id="removem" class="remove_monfield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box

   			monLabelling++; //text box increment
   			
   		//Autocomplete Scripts

			   $( function() {
				 var availableTags = [
			      "Suprabatham",
			      "Thomala Seva",
			      "Angapradakshina + SD + DD",
			      "Koluvu",
			      "Archana",
			      "1st Bell",
			      "Astadalapaada Padmaradhana",
			      "2nd Bell",
			      "Nithyaharathi + Break Darshan",
			      "Break Darshan",
			      "SD + DD + SED + Supadam",
			      "PH & SC",
			      "SD + DD + SED + Supadam",
			      "SD + DD + SED ",
			      "3rd Bell",
			      "SD + DD + SED",
			      "SD + DD",
			      "Kalyanostavam Tickets ",
			      "Sathmora + Nithya Harathi",
			      "3rd Bell + Naivedyam",
			      "Saharsa Kalasabishekam",
			      "Scheme",
			      "SK Darshan + Nithya Harathi + Break Darshan",
			      "Kalyanostavam",
			      "Tiruppavada",
			      "3rd Bell + Pedda Suddi",
			      "Abhisekam",
			      "Nijapada Darshanam",
			      "Samarpana",
			      "Thomala + Archana",
			      "Sathmora + Nithya Harathi",
			      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
			     
			    ];
			    

				
			    $( "#Business_Rule_Name1"+parentdivmonlength ).autocomplete({
			      source: availableTags
			    });
			  } );
				
			// Ajax calling getting the Previous rule 'To' Time from the Monday Collection

   		   $.ajax({

				 	   url:'Createmon',
				 	   type:"POST",
				       data:{addm:addm},
 
				   success : function(responseText) {
				   	  
				   	  
				   	  $("#From1"+parentdivmonlength).val(responseText);
				   		}
				   });
   			
   });
   </script>



								<script>
								// Remove the Business Rule script

								
   $(document).on("click", ".remove_monfield",
			function(e) { //user click on remove text


var parentdivmon=$( ".input_monControllfields_wrap" ).children( ".form-group");
 
var parentdivmonlength=$(this).attr("attr-idm");
 

//Ajax call to remove the business rule in th MondayCollection

$.ajax({

url : 'mondayre',
type:"POST",
data:{parentdivmonlength:parentdivmonlength}

});



e.preventDefault();
$(this).parent('div').remove();
monLabelling--;
			}) 
   
   </script>


								<script>
      $(document).ready(function(){
 	$(document).on("click", ".save_monfield", function() {
	 
	var parentdivmon=	$( ".input_monControllfields_wrap" ).children( ".form-group");
 	var parentdivmonlength=$(this).attr("attr-idm");
	 
var MondayBusinessRuleName=$("#Business_Rule_Name1"+parentdivmonlength).val();
 
var MondayFromTime=$("#From1"+parentdivmonlength).val();
var MondayToTime=$("#To1"+parentdivmonlength).val();
var MondayVQC1=$("#VQC1_Max_Capacity1"+parentdivmonlength).val();
var MondayVQC2=$("#VQC2_Max_Capacity1"+parentdivmonlength).val();
var MondayPreferenceQueue=$("#Preference_Queue1"+parentdivmonlength).val();
var savem = "savem";
var MondayDate = $("#date").val();
var MondayDay = $("#demo").html();


if(MondayToTime >= "23:00" && MondayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Tuesday) ");
	}
	

//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time
if(MondayFromTime>=MondayToTime || MondayToTime=="" || MondayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
}else{


	//Ajax call to save the Business rule in the Monday Collection

      $.ajax({
 		 
 		 url : 'actionm',
 		 type:"POST",
 		 data:{parentdivmonlength:parentdivmonlength,MondayFromTime:MondayFromTime,MondayBusinessRuleName:MondayBusinessRuleName,MondayToTime:MondayToTime,MondayVQC1:MondayVQC1,MondayVQC2:MondayVQC2,MondayPreferenceQueue:MondayPreferenceQueue, savem:savem, MondayDate:MondayDate, MondayDay:MondayDay},
 		
	 		 success : function(responseText) {
		 			 
		 			if(responseText=="yes"){
		 				alert("Entered From Timing "+MondayFromTime+" is not matching with the previous Business Rules 'To Time'" );
		 			 
		 				window.location.reload(true);
		 			}
		 											}  
 		});
       
}
 	});

 	
      });
      </script>

							</div>
<!-- Monday Tab ends here -->


							<!-- /////////////////////  TUESDAY /////////////////// -->


<!-- Tuesday Tab starts here -->
							<div id="tue" class="tab-pane fade">



								<button class="add_tueControllfield_button btn btn-primary"
									id="addt" style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>

								<div style="border: 1px solid black; margin-top: -0.5%">

									<div class="input_Tuesday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">



										<%
										//Retrieving the Business Rules from the TuesdayCollection

										
	BasicDBObject QueryObject = new BasicDBObject();

	DBCollection TuesdayCollection = db.getCollection("Tuesday");
	DBCursor cursorstue = TuesdayCollection.find();
	int it = 1;
	int at= 	cursorstue.size();
	while (cursorstue.hasNext()) { 

		 
		 DBObject resultstue=cursorstue.next();
		  Map resultmapstue=resultstue.toMap();


%>

										<div class="input_tueControllfields_wrap">

											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name2<%out.print(resultmapstue.get("ID"));%>"
															name="Business_Rule_Name"
															value="<% out.println(resultmapstue.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c "
															id="From2<%out.print(resultmapstue.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapstue.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control"
															id="To2<%out.print(resultmapstue.get("ID"));%>" name="To"
															value="<% out.println(resultmapstue.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity2<%out.print(resultmapstue.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapstue.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity2<%out.print(resultmapstue.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapstue.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue2<%out.print(resultmapstue.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmapstue.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="tue_count" value="0" /><a
															href="#"
															attr-idt="<%out.print(resultmapstue.get("ID"));%>"
															id="Savet" class="save_tuefield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>
													</div>


													<input type="hidden" name="tue_count" value="0" /><a
														href="#"
														attr-idt="<%out.print(resultmapstue.get("ID"));%>"
														id="removet" class="remove_tuefield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>
												</div>
											</div>
											<br>
										</div>

										<%} %>

										<div class="input_tueControllfields_wrap1"></div>
									</div>
								</div>

								<script type="text/javascript">
								// Add Rule script
   
   var tueControllwrapper = $(".input_tueControllfields_wrap1"); //Fields wrapper
   var tueControlladd_button = $(".add_tueControllfield_button"); //Add button ID
   var tueLabelling = 1; //initlal text box count
 
   $(document).on("click", ".add_tueControllfield_button", function() {
   
	   var parentdivtue=$( ".input_tueControllfields_wrap" ).children( ".form-group");
	   
	   var parentdivtuelength=parentdivtue.length;
	   var tuesdayDate = $("#datepicker_t").val();
	   var tuesdaydate = $("#date").val();
	   var tuesdayday = $("#demot").html();
	   var addt = "addt";
	 	   
   $(tueControllwrapper)
   	.append(' <div class="input_tueControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll"><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control" id="Business_Rule_Name2'+parentdivtuelength+'" name="BusinessRules" value="" onkeypress="return isWord(event)" placeholder="Name of the B.R"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c " id="From2'+parentdivtuelength+'" name="From" value="" onkeypress="return isTime(event)" placeholder="HH:MM" "></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To2'+parentdivtuelength+'" name="To" value="" onkeypress="return isTime(event)" placeholder="HH:MM" ></div>' +
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity2'+parentdivtuelength+'" name="VQC1_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill"></div>' +
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity2'+parentdivtuelength+'" name="VQC2_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue2'+parentdivtuelength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>' +
   	'<div class="col-sm-11 nopadding"><input type="hidden" name="tue_count" value="0" /><a href="#"  attr-idt="'+parentdivtuelength+'" id="Savet" class="save_tuefield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div>'+
   	'<input type="hidden" name="tue_count" value="0"  /><a href="#"  attr-idt="'+parentdivtuelength+'" id="removet" class="remove_tuefield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box


   		tueLabelling++; //text box increment
   		
   	//Autocomplete Scripts
   		
   	   $( function() {
			 var availableTags = [
		      "Suprabatham",
		      "Thomala Seva",
		      "Angapradakshina + SD + DD",
		      "Koluvu",
		      "Archana",
		      "1st Bell",
		      "Astadalapaada Padmaradhana",
		      "2nd Bell",
		      "Nithyaharathi + Break Darshan",
		      "Break Darshan",
		      "SD + DD + SED + Supadam",
		      "PH & SC",
		      "SD + DD + SED + Supadam",
		      "SD + DD + SED ",
		      "3rd Bell",
		      "SD + DD + SED",
		      "SD + DD",
		      "Kalyanostavam Tickets ",
		      "Sathmora + Nithya Harathi",
		      "3rd Bell + Naivedyam",
		      "Saharsa Kalasabishekam",
		      "Scheme",
		      "SK Darshan + Nithya Harathi + Break Darshan",
		      "Kalyanostavam",
		      "Tiruppavada",
		      "3rd Bell + Pedda Suddi",
		      "Abhisekam",
		      "Nijapada Darshanam",
		      "Samarpana",
		      "Thomala + Archana",
		      "Sathmora + Nithya Harathi",
		      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
		     
		    ];
		    $( "#Business_Rule_Name2"+parentdivtuelength ).autocomplete({
		      source: availableTags
		    });
		  } );
			
   	// Ajax calling getting the Previous rule 'To' Time from the Tuesday Collection

 	   $.ajax({

		
			url:'Createtue',
			type:"POST",
			   data:{ addt: addt},

			   success : function(responseText) {
			   	  
			   	 $( "#From2"+parentdivtuelength ).val(responseText);

			   		} 
			   });
   		
   		
   });
   </script>

								</script>
								-->




								<script>
								// Remove the Business Rule script
								
   $(document).on("click", ".remove_tuefield",
			function(e) { //user click on remove text


var parentdivtue=$( ".input_tueControllfields_wrap" ).children( ".form-group");
 
var parentdivtuelength=$(this).attr("attr-idt");
//alert(parentdivtuelength);

//Ajax call to remove the business rule in th TuesdayCollection
$.ajax({

url : 'tuesdayre',
type:"POST",
data:{parentdivtuelength:parentdivtuelength}

});



e.preventDefault();
$(this).parent('div').remove();
tueLabelling--;
			}) 
   
   </script>
								<script>
      $(document).ready(function(){
    		

	
	$(document).on("click", ".save_tuefield", function() {
	 
	var parentdivtue=	$( ".input_tueControllfields_wrap" ).children( ".form-group");
	 
	var parentdivtuelength=$(this).attr("attr-idt");
	
	 
	

var TuesdayBusinessRuleName=$("#Business_Rule_Name2"+parentdivtuelength).val();
 
var TuesdayFromTime=$("#From2"+parentdivtuelength).val();
var TuesdayToTime=$("#To2"+parentdivtuelength).val();
var TuesdayVQC1=$("#VQC1_Max_Capacity2"+parentdivtuelength).val();
var TuesdayVQC2=$("#VQC2_Max_Capacity2"+parentdivtuelength).val();
var TuesdayPreferenceQueue=$("#Preference_Queue2"+parentdivtuelength).val();
var savet = "savet";
var TuesdayDate = $("#date").val();
var TuesdayDay = $("#demo").html();
 

if(TuesdayToTime >= "23:00" && TuesdayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Wednesday)");
	}
//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time
if(TuesdayFromTime>=TuesdayToTime || TuesdayToTime=="" || TuesdayFromTime==""){
	
	alert("Enter Correct Time");
	
	//location.reload(true);

}else{
	
	

	//Ajax call to save the Business rule in the Tuesday Collection

     $.ajax({
 		 
 		 url : 'actiont',
 		 type:"POST",
 		 data:{parentdivtuelength:parentdivtuelength,TuesdayFromTime:TuesdayFromTime,TuesdayBusinessRuleName:TuesdayBusinessRuleName,TuesdayToTime:TuesdayToTime,TuesdayVQC1:TuesdayVQC1,TuesdayVQC2:TuesdayVQC2,TuesdayPreferenceQueue:TuesdayPreferenceQueue, savet:savet,TuesdayDate:TuesdayDate, TuesdayDay:TuesdayDay },
	 success : function(responseText) {
	 		 
	 			if(responseText=="yes"){
	 				alert("Entered From Timing "+TuesdayFromTime+" is not matching with the previous Business Rules 'To Time'" );
	 				 
	 				window.location.reload(true);
	 			}
	 	 			 	} 
 		});
}
       
 	});

 	
      });
      </script>
							</div>
					<!-- Tuesday Tab ends here -->		
							
							<!-- /////////////////////  WEDNESDAY /////////////////// -->

					<!-- Wednesday Tab starts here -->
							<div id="wed" class="tab-pane fade">



								<button class="add_wedControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>
								<div style="border: 1px solid black; margin-top: -0.5%">

									<div class="input_Wednesday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">



										<% 
										//Retrieving the Business Rules from the WednesdayCollection

										
	BasicDBObject QueryObjectWed = new BasicDBObject();

	DBCollection WednesdayCollection = db.getCollection("Wednesday");
	DBCursor cursorswed = WednesdayCollection.find();
	int iw = 1;
	int aw= 	cursorswed.size();
	while (cursorswed.hasNext()) { 

		 
		 DBObject resultswed=cursorswed.next();
		  Map resultmapswed=resultswed.toMap();


%>

										<div class="input_wedControllfields_wrap">

											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name3<%out.print(resultmapswed.get("ID"));%>"
															name="Business_Rule_Name"
															value="<%out.println(resultmapswed.get("Business_Rule_Name"));%>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From3<%out.print(resultmapswed.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapswed.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To3<%out.print(resultmapswed.get("ID"));%>" name="To"
															value="<% out.println(resultmapswed.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity3<%out.print(resultmapswed.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapswed.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity3<%out.print(resultmapswed.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapswed.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue3<%out.print(resultmapswed.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmapswed.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="wed_count" value="0" /><a
															href="#"
															attr-idw="<%out.print(resultmapswed.get("ID"));%>"
															id="Savew" class="save_wedfield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>
													</div>


													<input type="hidden" name="wed_count" value="0" /><a
														href="#"
														attr-idw="<%out.print(resultmapswed.get("ID"));%>"
														id="removew" class="remove_wedfield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>

												</div>
											</div>
											<br>
										</div>
										<%} %>


										<div class="input_wedControllfields_wrap1"></div>
									</div>
								</div>

								<script type="text/javascript">
								// Add Rule script
								
   var wedControllwrapper = $(".input_wedControllfields_wrap1"); //Fields wrapper
   var wedControlladd_button = $(".add_wedControllfield_button"); //Add button ID
   var wedLabelling = 1; //initlal text box count
 
   $(document).on("click", ".add_wedControllfield_button", function() {
   
	   var parentdivwed=	$( ".input_wedControllfields_wrap" ).children( ".form-group");
	   
	   var parentdivwedlength=parentdivwed.length;
	   var wednesdaydate = $("#datepicker_w").val();
	   var wednesdayday = $("#demow").html();
	   var addw = "addw";
	    
	   
   $(wedControllwrapper)
   	.append(' <div class="input_wedControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll">'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control" id="Business_Rule_Name3'+parentdivwedlength+'" name="BusinessRules" onkeypress="return isWord(event)" value="" placeholder="Name of the B.R"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From3'+parentdivwedlength+'" name="From" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To3'+parentdivwedlength+'" name="To" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity3'+parentdivwedlength+'" name="VQC1_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity3'+parentdivwedlength+'" name="VQC2_Max_Capacity" onkeypress="return isNumber(event)" "value="" placeholder="Nill"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue3'+parentdivwedlength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div><div class="col-sm-11 nopadding">	<input type="hidden" name="wed_count" value="0" /><a href="#"  attr-idw="'+parentdivwedlength+'" id="Savew" class="save_wedfield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div><input type="hidden" name="wed_count" value="0"  /><a href="#"  attr-idw="'+parentdivwedlength+'" id="removew" class="remove_wedfield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box


   	wedLabelling++; //text box increment
   	
  //Autocomplete Scripts
   	
    $( function() {
		 var availableTags = [
	      "Suprabatham",
	      "Thomala Seva",
	      "Angapradakshina + SD + DD",
	      "Koluvu",
	      "Archana",
	      "1st Bell",
	      "Astadalapaada Padmaradhana",
	      "2nd Bell",
	      "Nithyaharathi + Break Darshan",
	      "Break Darshan",
	      "SD + DD + SED + Supadam",
	      "PH & SC",
	      "SD + DD + SED + Supadam",
	      "SD + DD + SED ",
	      "3rd Bell",
	      "SD + DD + SED",
	      "SD + DD",
	      "Kalyanostavam Tickets ",
	      "Sathmora + Nithya Harathi",
	      "3rd Bell + Naivedyam",
	      "Saharsa Kalasabishekam",
	      "Scheme",
	      "SK Darshan + Nithya Harathi + Break Darshan",
	      "Kalyanostavam",
	      "Tiruppavada",
	      "3rd Bell + Pedda Suddi",
	      "Abhisekam",
	      "Nijapada Darshanam",
	      "Samarpana",
	      "Thomala + Archana",
	      "Sathmora + Nithya Harathi",
	      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
	     
	    ];
	    

		
	    $( "#Business_Rule_Name3"+parentdivwedlength ).autocomplete({
	      source: availableTags
	    });
	  } );
		
 // Ajax calling getting the Previous rule 'To' Time from the Sunday Collection

    $.ajax({

	     	  url:'Createwed',
		   type:"POST",
		   data:{addw: addw},

		    success : function(responseText) {
		   	  
		   	 $( "#From3"+parentdivwedlength ).val(responseText);
 
		   		}
		   });
   	
   	
   	
   });
   </script>
								<script>
								
								// Remove the Business Rule script
   $(document).on("click", ".remove_wedfield",
	 function(e) { //user click on remove text


var parentdivwed=	$( ".input_wedControllfields_wrap" ).children( ".form-group");
 
var parentdivwedlength=$(this).attr("attr-idw");
 

//Ajax call to remove the business rule in th SundayCollection

$.ajax({

url : 'wednesdayre',
type:"POST",
data:{parentdivwedlength:parentdivwedlength}

});



e.preventDefault();
$(this).parent('div').remove();
wedLabelling--;
			}) 
   
   </script>

								<script type="text/javascript">
   
   $.ajaxSetup({ cache: false });
      $(document).ready(function(){

	$(document).on("click", ".save_wedfield", function() {
 
	var parentdivwed=	$( ".input_wedControllfields_wrap" ).children( ".form-group");
	 
	var parentdivwedlength=$(this).attr("attr-idw");

var WednesdayBusinessRuleName=$("#Business_Rule_Name3"+parentdivwedlength).val();
 
var WenesdayFromTime=$("#From3"+parentdivwedlength).val();
var WenesdayToTime=$("#To3"+parentdivwedlength).val();
var WednesdayVQC1=$("#VQC1_Max_Capacity3"+parentdivwedlength).val();
var WednesdayVQC2=$("#VQC2_Max_Capacity3"+parentdivwedlength).val();
var WednesdayPreferenceQueue=$("#Preference_Queue3"+parentdivwedlength).val();
var savew = "savew";
var WednesdayDate = $("#date").val();
var WednesdayDay = $("#demo").html();


if(WenesdayToTime >= "23:00" && WenesdayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Thursday)");
	}
	
	

//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time
	

if(WenesdayFromTime>=WenesdayToTime || WenesdayToTime=="" || WenesdayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
	

}else{
	//Ajax call to save the Business rule in the Sunday Collection

   $.ajax({
 		 
 		 url : 'actionw',
 		 type:"POST",
 		 data:{parentdivwedlength:parentdivwedlength,WednesdayBusinessRuleName:WednesdayBusinessRuleName,WenesdayFromTime:WenesdayFromTime,WenesdayToTime:WenesdayToTime,WednesdayVQC1:WednesdayVQC1,WednesdayVQC2:WednesdayVQC2,WednesdayPreferenceQueue:WednesdayPreferenceQueue,savew:savew,WednesdayDate:WednesdayDate,WednesdayDay:WednesdayDay},
 		
 			 success : function(responseText) {
 			 
 			if(responseText=="yes"){
 				alert("Entered From Timing "+WenesdayFromTime+" is not matching with the previous Business Rules 'To Time'" );
 				location.reload(true);
 		
 			}
 											}  
 		});
}


      
 	});

 	
      });
      </script>

							</div>
<!-- Wednesday Tab ends here -->




							<!-- /////////////////////  THURSDAY /////////////////// -->
<!-- Thursday Tab starts here -->
							<div id="thurs" class="tab-pane fade">


								<button class="add_thursControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>


								<div style="border: 1px solid black; margin-top: -0.5%">
									<div class="input_Thursday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">

										<%
										//Retrieving the Business Rules from the ThursdayCollection

	BasicDBObject QueryObjectThur = new BasicDBObject();

	DBCollection ThursdayCollection = db.getCollection("Thursday");
	DBCursor cursorsthur = ThursdayCollection.find();
	int ith = 1;
	int ath= 	cursorsthur.size();
	while (cursorsthur.hasNext()) { 

		 
		 DBObject resultsthur=cursorsthur.next();
		  Map resultmapsthur=resultsthur.toMap();


%>
										<div class="input_thursControllfields_wrap">

											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name4<%out.print(resultmapsthur.get("ID"));%>"
															name="Business_Rule_Name"
															value=" <% out.println(resultmapsthur.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From4<%out.print(resultmapsthur.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapsthur.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To4<%out.print(resultmapsthur.get("ID"));%>"
															name="To"
															value="<% out.println(resultmapsthur.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity4<%out.print(resultmapsthur.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapsthur.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity4<%out.print(resultmapsthur.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapsthur.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue4<%out.print(resultmapsthur.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmapsthur.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="thurs_count" value="0" /><a
															href="#"
															attr-idw="<%out.print(resultmapsthur.get("ID"));%>"
															id="Saveth" class="save_thursfield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%">
															<img src="https://i.imgur.com/L9BIwKs.png" height="23" />Save
														</a>
													</div>


													<input type="hidden" name="thurs_count" value="0" /><a
														href="#"
														attr-idw="<%out.print(resultmapsthur.get("ID"));%>"
														id="removeth" class="remove_thursfield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%">
														<img src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove
													</a>
												</div>

											</div>
											<br>
										</div>
										<%} %>


										<div class="input_thursControllfields_wrap1"></div>
									</div>
								</div>


								<script type="text/javascript">

								// Add Rule script


var thursControllwrapper = $(".input_thursControllfields_wrap1"); //Fields wrapper
var thursControlladd_button = $(".add_thursControllfield_button"); //Add button ID
var thursLabelling = 1; //initlal text box count


  $(document).on("click", ".add_thursControllfield_button", function() {
   
	   var parentdivthur=	$( ".input_thursControllfields_wrap" ).children( ".form-group");
	   
	   var parentdivthurlength=parentdivthur.length; 
	 var thursdaydate = $("#datepicker_th").val();
   var thursdayday = $("#demoth").html();
   var addth = "addth";
    


  $(thursControllwrapper)

	.append(' <div class="input_thursControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll"><div class="col-sm-2 nopadding">'+
   	'<label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control"  id="Business_Rule_Name4'+parentdivthurlength+'" name="BusinessRules" value="" onkeypress="return isWord(event)" placeholder="Name of the B.R"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From4'+parentdivthurlength+'" name="From" onkeypress="return isTime(event)" value="" placeholder="HH:MM"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To4'+parentdivthurlength+'" name="To" onkeypress="return isTime(event)" value="" placeholder="HH:MM"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity4'+parentdivthurlength+'" name="VQC1_Max_Capacity"  value="" onkeypress="return isNumber(event)" placeholder="Nill">'+
   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity4'+parentdivthurlength+'" name="VQC2_Max_Capacity" onkeypress="return isNumber(event)" value="" placeholder="Nill">'+
   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue4'+parentdivthurlength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>'+
   	'<div class="col-sm-11 nopadding"><input type="hidden" name="thurs_count" value="0" /><a href="#"  attr-idth="'+parentdivthurlength+'" id="Saveth" class="save_thursfield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div>'+
   	'<input type="hidden" name="thurs_count" value="0"  /><a href="#" attr-idth="'+parentdivthurlength+'" id="removeth"  class="remove_thursfield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box
	

			thursLabelling++; //text box increment

			//Autocomplete Scripts
			 $( function() {
				 var availableTags = [
			      "Suprabatham",
			      "Thomala Seva",
			      "Angapradakshina + SD + DD",
			      "Koluvu",
			      "Archana",
			      "1st Bell",
			      "Astadalapaada Padmaradhana",
			      "2nd Bell",
			      "Nithyaharathi + Break Darshan",
			      "Break Darshan",
			      "SD + DD + SED + Supadam",
			      "PH & SC",
			      "SD + DD + SED + Supadam",
			      "SD + DD + SED ",
			      "3rd Bell",
			      "SD + DD + SED",
			      "SD + DD",
			      "Kalyanostavam Tickets ",
			      "Sathmora + Nithya Harathi",
			      "3rd Bell + Naivedyam",
			      "Saharsa Kalasabishekam",
			      "Scheme",
			      "SK Darshan + Nithya Harathi + Break Darshan",
			      "Kalyanostavam",
			      "Tiruppavada",
			      "3rd Bell + Pedda Suddi",
			      "Abhisekam",
			      "Nijapada Darshanam",
			      "Samarpana",
			      "Thomala + Archana",
			      "Sathmora + Nithya Harathi",
			      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
			     
			    ];
			    

				
			    $( "#Business_Rule_Name4"+parentdivthurlength ).autocomplete({
			      source: availableTags
			    });
			  } );
				
	
			
			// Ajax calling getting the Previous rule 'To' Time from the Thurday Collection
		
			
  $.ajax({

   				    url: 'Createthu',
   				   type:"POST",
   				   data:{addth: addth},

   				   success : function(responseText) {
   				   	  
   				   	  
   				   $( "#From4"+parentdivthurlength ).val(responseText);
   				   		} 
   				   });



});
      </script>
								<script>
								// Remove the Business Rule script
								
  $(document).on("click", ".remove_thursfield",
			function(e) { //user click on remove text


var parentdivthur=	$( ".input_thursControllfields_wrap" ).children( ".form-group");
 
var parentdivthurlength=$(this).attr("attr-idth");

//Ajax call to remove the business rule in th ThursdayCollection

$.ajax({

url : 'remove',
type:"POST",
data:{parentdivthurlength:parentdivthurlength}


});



e.preventDefault();
$(this).parent('div').remove();
thursLabelling--;
			}) 
  
  
  
  
  
  </script>
								<script>
  
  
  $(document).ready(function(){


$(document).on("click", ".save_thursfield", function() {

var parentdivthur=	$( ".input_thursControllfields_wrap" ).children( ".form-group");
 
var parentdivthurlength=$(this).attr("attr-idth");
var ThursdayBusinessRuleName=$("#Business_Rule_Name4"+parentdivthurlength).val();
var ThursdayFromTime=$("#From4"+parentdivthurlength).val();
var ThursdayToTime=$("#To4"+parentdivthurlength).val();
var ThursdayVQC1=$("#VQC1_Max_Capacity4"+parentdivthurlength).val();
var ThursdayVQC2=$("#VQC2_Max_Capacity4"+parentdivthurlength).val();
var ThursdayPreferenceQueue=$("#Preference_Queue4"+parentdivthurlength).val();
var saveth = "saveth";
var ThursdayDate = $("#date").val();
var ThursdayDay = $("#demo").html();


if(ThursdayToTime >= "23:00" && ThursdayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Friday)");
	}
//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time

if(ThursdayFromTime>=ThursdayToTime || ThursdayToTime=="" || ThursdayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
}else{



 
	//Ajax call to save the Business rule in the Thursday Collection


$.ajax({
	 
	 url : 'actionth',
	 type:"POST",
	 data:{parentdivthurlength:parentdivthurlength,ThursdayFromTime:ThursdayFromTime,ThursdayBusinessRuleName:ThursdayBusinessRuleName,ThursdayToTime:ThursdayToTime,ThursdayVQC1:ThursdayVQC1,ThursdayVQC2:ThursdayVQC2,ThursdayPreferenceQueue:ThursdayPreferenceQueue, saveth:saveth, ThursdayDate:ThursdayDate , ThursdayDay:ThursdayDay},
	
 success : function(responseText) {
		 
		if(responseText=="yes"){
			alert("Entered From Timing "+ThursdayFromTime+" is not matching with the previous Business Rules 'To Time'" );
			window.location.reload(true);
		}
										} 
	});

}
});


});
  
  
  
  
  </script>

							</div>


<!-- Thursday Tab ends here -->


							<!-- ///////////////////// FRIDAY /////////////////// -->


<!-- Friday Tab starts here -->
							<div id="fri" class="tab-pane fade">


								<button class="add_friControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>


								<div style="border: 1px solid black; margin-top: -0.5%">
									<div class="input_Friday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">


										<%

										//Retrieving the Business Rules from the FridayCollection

	BasicDBObject QueryObjectFriday = new BasicDBObject();

	DBCollection FridayCollection = db.getCollection("Friday");
	DBCursor cursorsfri = FridayCollection.find();
	int iff = 1;
	int af= 	cursorsfri.size();
	while (cursorsfri.hasNext()) { 

		 
		 DBObject resultsfri=cursorsfri.next();
		  Map resultmapsfri=resultsfri.toMap();


%>
										<div class="input_friControllfields_wrap">

											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name5<%out.print(resultmapsfri.get("ID"));%>"
															name="Business_Rule_Name"
															value="<% out.println(resultmapsfri.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From5<% out.print(resultmapsfri.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapsfri.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To5<%out.print(resultmapsfri.get("ID"));%>" name="To"
															value="<% out.println(resultmapsfri.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity5<%out.print(resultmapsfri.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapsfri.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity5<% out.print(resultmapsfri.get("ID")); %>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapsfri.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue5<% out.print(resultmapsfri.get("ID")); %>"
															name="Preference_Queue"
															value="<% out.println(resultmapsfri.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="fri_count" value="0" /><a
															href="#"
															attr-idf="<%out.print(resultmapsfri.get("ID")); %>"
															id="Savef" class="save_frifield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>
													</div>


													<input type="hidden" name="fri_count" value="0" /><a
														href="#"
														attr-idf="<% out.print(resultmapsfri.get("ID")); %>"
														id="removef" class="remove_frifield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>

												</div>
											</div>
											<br>
										</div>
										<%} %>


										<div class="input_friControllfields_wrap1"></div>
									</div>
								</div>

								<script type="text/javascript">

								// Add Rule script
  
   var friControllwrapper = $(".input_friControllfields_wrap1"); //Fields wrapper
   var friControlladd_button = $(".add_friControllfield_button"); //Add button ID
   var friLabelling = 1; //initlal text box count
 
   $(document).on("click", ".add_friControllfield_button", function() {
   
	   var parentdivfri=	$( ".input_friControllfields_wrap" ).children( ".form-group");
	   
	   var parentdivfrilength=parentdivfri.length; 
	   
	   var fridaydate = $("#datepicker_f").val();
	   var fridayday = $("#demof").html();
	   var addf = "addf";
	    
	   
   $(friControllwrapper)
   	.append(' <div class="input_friControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll"><div class="col-sm-2 nopadding">'+
   	'<label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control"  id="Business_Rule_Name5'+parentdivfrilength+'" name="BusinessRules" value="" onkeypress="return isWord(event)" placeholder="Name of the B.R"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From5'+parentdivfrilength+'" name="From" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To5'+parentdivfrilength+'" name="To" value="" onkeypress="return isTime(event)" placeholder="HH:MM"></div>'+
   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity5'+parentdivfrilength+'" name="VQC1_Max_Capacity"  value="" onkeypress="return isNumber(event)" placeholder="Nill">'+
   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity5'+parentdivfrilength+'" name="VQC2_Max_Capacity" value="" onkeypress="return isNumber(event)" placeholder="Nill">'+
   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue5'+parentdivfrilength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>'+
   	'<div class="col-sm-11 nopadding"><input type="hidden" name="fri_count" value="0" /><a href="#"  attr-idf="'+parentdivfrilength+'" id="Savef" class="save_frifield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div>'+
   	'<input type="hidden" name="fri_count" value="0"  /><a href="#" attr-idf="'+parentdivfrilength+'" id="removef"  class="remove_frifield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box
   	friLabelling++; //text box increment
   	
  //Autocomplete Scripts
    $( function() {
		 var availableTags = [
	      "Suprabatham",
	      "Thomala Seva",
	      "Angapradakshina + SD + DD",
	      "Koluvu",
	      "Archana",
	      "1st Bell",
	      "Astadalapaada Padmaradhana",
	      "2nd Bell",
	      "Nithyaharathi + Break Darshan",
	      "Break Darshan",
	      "SD + DD + SED + Supadam",
	      "PH & SC",
	      "SD + DD + SED + Supadam",
	      "SD + DD + SED ",
	      "3rd Bell",
	      "SD + DD + SED",
	      "SD + DD",
	      "Kalyanostavam Tickets ",
	      "Sathmora + Nithya Harathi",
	      "3rd Bell + Naivedyam",
	      "Saharsa Kalasabishekam",
	      "Scheme",
	      "SK Darshan + Nithya Harathi + Break Darshan",
	      "Kalyanostavam",
	      "Tiruppavada",
	      "3rd Bell + Pedda Suddi",
	      "Abhisekam",
	      "Nijapada Darshanam",
	      "Samarpana",
	      "Thomala + Archana",
	      "Sathmora + Nithya Harathi",
	      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
	     
	    ];
	    

		
	    $( "#Business_Rule_Name5"+parentdivfrilength ).autocomplete({
	      source: availableTags
	    });
	  } );

 // Ajax calling getting the Previous rule 'To' Time from the Thursday Collection

	   $.ajax({
			   url: 'Createfri',
			   type:"POST",
			   data:{addf: addf},

			   success : function(responseText) {
			   	   
			   	$( "#From5"+parentdivfrilength ).val(responseText);
			   		} 
			   });
   	
   	
   });
   </script>
								<script>

								// Remove the Business Rule script
								
   $(document).on("click", ".remove_frifield",
			function(e) { //user click on remove text


var parentdivfri=	$( ".input_friControllfields_wrap" ).children( ".form-group");
 
var parentdivfrilength=$(this).attr("attr-idf");

//Ajax call to remove the business rule in th ThursdayCollection

$.ajax({

url : 'fridayre',
type:"POST",
data:{parentdivfrilength:parentdivfrilength}

});



e.preventDefault();
$(this).parent('div').remove();
friLabelling--;
			}) 
   
   </script>
								<script>
      $(document).ready(function(){

	
	$(document).on("click", ".save_frifield", function() {
	 
	var parentdivfri=	$( ".input_friControllfields_wrap" ).children( ".form-group");
	 
	var parentdivfrilength=$(this).attr("attr-idf");
  
var FridayBusinessRuleName=$("#Business_Rule_Name5"+parentdivfrilength).val();
var FridayFromTime=$("#From5"+parentdivfrilength).val();
var FridayToTime=$("#To5"+parentdivfrilength).val();
var FridayVQC1=$("#VQC1_Max_Capacity5"+parentdivfrilength).val();
var FridayVQC2=$("#VQC2_Max_Capacity5"+parentdivfrilength).val();
var FridayPreferenceQueue=$("#Preference_Queue5"+parentdivfrilength).val();
var savef = "savef";
var FridayDate = $("#date").val();
var FridayDay = $("#demo").html();


if(FridayToTime >= "23:00" && FridayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Saturday)");
	}

//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time

if(FridayFromTime>=FridayToTime || FridayToTime=="" || FridayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
}else{
	//Ajax call to save the Business rule in the Thursday Collection

   $.ajax({
 		 
 		 url : 'actionf',
 		 type:"POST",
 		 data:{parentdivfrilength:parentdivfrilength,FridayFromTime:FridayFromTime,FridayBusinessRuleName:FridayBusinessRuleName,FridayToTime:FridayToTime,FridayVQC1:FridayVQC1,FridayVQC2:FridayVQC2,FridayPreferenceQueue:FridayPreferenceQueue, savef:savef, FridayDate:FridayDate, FridayDay:FridayDay},
 		
		 success : function(responseText) {
		 			 
		 			if(responseText=="yes"){
		 				alert("Entered From Timing "+FridayFromTime+" is not matching with the previous Business Rules 'To Time'" );
		 		     	window.location.reload(true);
		 			}
		 											}  
 		});
}
 	});

 	
      });
      </script>



							</div>
							
<!-- Friday Tab ends here -->
							<!-- /////////////////////  SATURDAY /////////////////// -->



<!-- Saturday Tab starts here -->
							<div id="sat" class="tab-pane fade">

								<button class="add_satControllfield_button btn btn-primary"
									style="margin-left: 0%; margin-top: -1.50%">
									Add New Rule <img src="https://i.imgur.com/e5x494x.png"
										height="23" />
								</button>
								<br> <br>

								<div style="border: 1px solid black; margin-top: -0.5%">
									<div class="input_Saturday_wrap"
										style="margin-top: 2%; overflow: scroll; height: 500px">



										<%
										//Retrieving the Business Rules from the SaturdayCollection

	BasicDBObject QueryObjectSat = new BasicDBObject();

	DBCollection SaturdayCollection = db.getCollection("Saturday");
	DBCursor cursorssat = SaturdayCollection.find();
	int is = 1;
	int as= 	cursorssat.size();
	while (cursorssat.hasNext()) { 

		 
		 DBObject resultssat=cursorssat.next();
		  Map resultmapssat=resultssat.toMap();


%>

										<div class="input_satControllfields_wrap">

											<div class="form-group" style="margin-left: 20px">
												<div class="scroll">

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 130%">
															Business Rule</label> <input type="text" class="form-control"
															id="Business_Rule_Name6<%out.print(resultmapssat.get("ID"));%>"
															name="Business_Rule_Name"
															value="<% out.println(resultmapssat.get("Business_Rule_Name")); %>"
															onkeypress="return isWord(event)"
															placeholder="Name of the B.R">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															From</label> <input type="text" class="form-control c"
															id="From6<%out.print(resultmapssat.get("ID"));%>"
															name="From"
															value="<% out.println(resultmapssat.get("From")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Entry Time">
													</div>

													<div class="col-sm-2">
														<label style="font-family: Cambria; font-size: 130%">
															To</label> <input type="text" class="form-control c"
															id="To6<% out.print(resultmapssat.get("ID"));%>"
															name="To"
															value="<% out.println(resultmapssat.get("To")); %>"
															onkeypress="return isTime(event)"
															placeholder="Enter the Exit Time">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 116%">
															Max.VQC-I Capacity</label> <input type="text"
															class="form-control"
															id="VQC1_Max_Capacity6<%out.print(resultmapssat.get("ID"));%>"
															name="VQC1_Max_Capacity"
															value="<% out.println(resultmapssat.get("VQC1_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 112%">
															Max.VQC-II Capacity</label> <input type="text"
															class="form-control"
															id="VQC2_Max_Capacity6<%out.print(resultmapssat.get("ID"));%>"
															name="VQC2_Max_Capacity"
															value="<% out.println(resultmapssat.get("VQC2_Max_Capacity")); %>"
															onkeypress="return isNumber(event)" placeholder="Nill">
													</div>

													<div class="col-sm-2 nopadding">
														<label style="font-family: Cambria; font-size: 125%">Preference
															Queue</label> <input type="text" class="form-control"
															id="Preference_Queue6<%out.print(resultmapssat.get("ID"));%>"
															name="Preference_Queue"
															value="<% out.println(resultmapssat.get("Preference_Queue")); %>"
															placeholder="Preference Queue"><br>
													</div>

													<div class="col-sm-11 nopadding">
														<input type="hidden" name="sat_count" value="0" /><a
															href="#"
															attr-ids="<%out.print(resultmapssat.get("ID"));%>"
															id="Saves" class="save_satfield btn btn-primary"
															style="margin-left: 88%; font-family: TimesnewRoman; font-size: 120%"><img
															src="https://i.imgur.com/L9BIwKs.png" height="23" />Save</a>
													</div>


													<input type="hidden" name="sat_count" value="0" /><a
														href="#"
														attr-ids="<%out.print(resultmapssat.get("ID"));%>"
														id="removes" class="remove_satfield btn btn-primary"
														style="margin-left: 88%; margin-top: -3.7%; font-family: TimesnewRoman; font-size: 120%"><img
														src="https://i.imgur.com/ZQUpQtS.png" height="23" />
														Remove </a>

												</div>
											</div>
											<br>
										</div>
										<%} %>


										<div class="input_satControllfields_wrap1"></div>
									</div>

								</div>
							</div>
						</div>
						<!-- tab contents ends here -->
						
						<script type="text/javascript">
						// Add Rule script
						
  var satControllwrapper = $(".input_satControllfields_wrap1"); //Fields wrapper
var satControlladd_button = $(".add_satControllfield_button"); //Add button ID
var satLabelling = 1; //initlal text box count

  $(document).on("click", ".add_satControllfield_button", function() {


  var parentdivsat=	$( ".input_satControllfields_wrap" ).children( ".form-group");
   
   var parentdivsatlength=parentdivsat.length;
   var saturdaydate = $("#datepicker_sa").val();
   var saturdayday = $("#demosa").html();
   var adds = "adds";
    





$(satControllwrapper)
	.append(' <div class="input_satControllfields_wrap" ><div class="form-group" style="margin-left: 20px"><div class="scroll"><div class="col-sm-2 nopadding">'+
		   	'<label style="font-family: Cambria;font-size: 130%"> Business Rule</label><input type="text" class="form-control"  id="Business_Rule_Name6'+parentdivsatlength+'" name="BusinessRules" value=""  onkeypress="return isWord(event)" placeholder="Name of the B.R"></div>'+
		   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> From</label><input  class="form-control c" id="From6'+parentdivsatlength+'" name="From" value=""  onkeypress="return isTime(event)"  placeholder="HH:MM"></div>'+
		   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 130%"> To</label><input  class="form-control c" id="To6'+parentdivsatlength+'" name="To" value="" onkeypress="return isTime(event)"  placeholder="HH:MM"></div>'+
		   	'<div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 116%"> Max.VQC-I Capacity</label><input type="text" class="form-control" id="VQC1_Max_Capacity6'+parentdivsatlength+'" name="VQC1_Max_Capacity"  value="" onkeypress="return isNumber(event)" placeholder="Nill">'+
		   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 112%"> Max.VQC-II Capacity</label><input type="text" class="form-control" id="VQC2_Max_Capacity6'+parentdivsatlength+'" name="VQC2_Max_Capacity" value="" onkeypress="return isNumber(event)" placeholder="Nill">'+
		   	'</div><div class="col-sm-2 nopadding"><label style="font-family: Cambria;font-size: 125%">Preference Queue</label><input type="text" class="form-control" id="Preference_Queue6'+parentdivsatlength+'"  name="Preference_Queue" value="" placeholder="Preference Queue"><br></div>'+
		   	'<div class="col-sm-11 nopadding"><input type="hidden" name="sat_count" value="0" /><a href="#"  attr-ids="'+parentdivsatlength+'" id="Saves" class="save_satfield btn btn-primary"style="margin-left: 88%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/L9BIwKs.png" height="23"/>Save</a></div>'+
		   	'<input type="hidden" name="sat_count" value="0"  /><a href="#" attr-ids="'+parentdivsatlength+'" id="removes"  class="remove_satfield btn btn-primary" style="margin-left: 88%;margin-top:-3.7%;font-family: TimesnewRoman;font-size: 120%"><img src="https://i.imgur.com/ZQUpQtS.png" height="23"/> Remove </a></div></div><br></div>'); //add input box
			satLabelling++; //text box increment

			//Autocomplete Scripts
			 $( function() {
				 var availableTags = [
			      "Suprabatham",
			      "Thomala Seva",
			      "Angapradakshina + SD + DD",
			      "Koluvu",
			      "Archana",
			      "1st Bell",
			      "Astadalapaada Padmaradhana",
			      "2nd Bell",
			      "Nithyaharathi + Break Darshan",
			      "Break Darshan",
			      "SD + DD + SED + Supadam",
			      "PH & SC",
			      "SD + DD + SED + Supadam",
			      "SD + DD + SED ",
			      "3rd Bell",
			      "SD + DD + SED",
			      "SD + DD",
			      "Kalyanostavam Tickets ",
			      "Sathmora + Nithya Harathi",
			      "3rd Bell + Naivedyam",
			      "Saharsa Kalasabishekam",
			      "Scheme",
			      "SK Darshan + Nithya Harathi + Break Darshan",
			      "Kalyanostavam",
			      "Tiruppavada",
			      "3rd Bell + Pedda Suddi",
			      "Abhisekam",
			      "Nijapada Darshanam",
			      "Samarpana",
			      "Thomala + Archana",
			      "Sathmora + Nithya Harathi",
			      "Kalyanostavaam + Unjal Seva + Arjitha Brahmostavam + SD Seva"
			     
			    ];
			    

				
			    $( "#Business_Rule_Name6"+parentdivsatlength ).autocomplete({
			      source: availableTags
			    });
			  } );
				
			// Ajax calling getting the Previous rule 'To' Time from the Saturday Collection

	   $.ajax({

   				
   				 url: 'Createsat',  
   				 type:"POST",
   				   data:{adds: adds},
   				   
   				   success: function(responseText){
   					   
   					 $( "#From6"+parentdivsatlength ).val(responseText);
   				   }

   			
   				   });

});
  
  </script>
						<script>

						// Remove the Business Rule script
  $(document).on("click", ".remove_satfield",
			function(e) { //user click on remove text


var parentdivsat=	$( ".input_satControllfields_wrap" ).children( ".form-group");
 
var parentdivsatlength=$(this).attr("attr-ids");
//alert(parentdivsatlength);
//Ajax call to remove the business rule in th SaturdayCollection

$.ajax({

url : 'remove',
type:"POST",
data:{parentdivsatlength:parentdivsatlength}

});



e.preventDefault();
$(this).parent('div').remove();
satLabelling--;
			}) 
  
  
  </script>
						<script>	
  
  $(document).ready(function(){
		
	  
	  $("#load").hide();
$(document).on("click", ".save_satfield", function() {
 
var parentdivsat=	$( ".input_satControllfields_wrap" ).children( ".form-group");
 
var parentdivsatlength=$(this).attr("attr-ids");
var SaturdayBusinessRuleName=$("#Business_Rule_Name6"+parentdivsatlength).val();
var SaturdayFromTime=$("#From6"+parentdivsatlength).val();
var SaturdayToTime=$("#To6"+parentdivsatlength).val();
var SaturdayVQC1=$("#VQC1_Max_Capacity6"+parentdivsatlength).val();
var SaturdayVQC2=$("#VQC2_Max_Capacity6"+parentdivsatlength).val();
var SaturdayPreferenceQueue=$("#Preference_Queue6"+parentdivsatlength).val();
var saves = "saves";
var SaturdayDate = $("#date").val();
var SaturdayDay = $("#demo").html();


if(SaturdayToTime >= "23:00" && SaturdayToTime <= "23:59"){
	
	alert("Today's Time has extended Please Proceed with Next Day (Sunday)");
	}
//Checking the 'From' Time and 'To' Time is null and also 'From' Time is greater than the 'To' Time

if(SaturdayFromTime>=SaturdayToTime || SaturdayToTime=="" || SaturdayFromTime==""){
	
	alert("Enter Correct Time");
	//location.reload(true);
	
}else{

	//Ajax call to save the Business rule in the Saturday Collection

$.ajax({
	 
	 url : 'actions',
	 type:"POST",
	 data:{parentdivsatlength:parentdivsatlength,SaturdayFromTime:SaturdayFromTime,SaturdayBusinessRuleName:SaturdayBusinessRuleName,SaturdayToTime:SaturdayToTime,SaturdayVQC1:SaturdayVQC1,SaturdayVQC2:SaturdayVQC2,SaturdayPreferenceQueue:SaturdayPreferenceQueue, saves:saves, SaturdayDate:SaturdayDate , SaturdayDay:SaturdayDay},
	
 		 success : function(responseText) {
	 		 
	 			if(responseText=="yes"){
	 				alert("Entered From Timing "+SaturdayFromTime+" is not matching with the previous Business Rules 'To Time'" );
	 				 
	 				window.location.reload(true);
	 			}
	 											}
	});
}
 
});


});
 
  </script>
					</div>

<!-- Saturday Tab ends here -->

					<!--        ADMIN PAGE               -->

					<div role="tabpanel" class="tab-pane" id="admin">

						<div class=" col-md-onset-5 ">
							<div class="panel ">
								<div class="panel-heading">
									<span class="glyphicon glyphicon-lock"></span> Login
									<!-- <img src=" https://i.imgur.com/4dcMthp.png"  style="height:40px ;float:left;"/> -->
								</div>
								<div class="panel-body">
									<form class="form-signin" name="loginform"
										onSubmit="return validateForm();" method="post" action="login">
										<div class="form-group">
											<div class="col-sm-3" style="left: 30%">
												<label for="inputEmail3" class=" control-label">
													Email</label> <input type="email" name="User" class="form-control"
													id="inputEmail3" placeholder="Email" required>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-3" style="left: 30%">
												<label for="inputPassword3" class=" control-label">
													Password</label> <input type="password" style="left: 30%"
													name="Password" class="form-control" id="inputPassword3"
													placeholder="Password" required>
											</div>
										</div>
										<div class="form-group">
											<div class="col-sm-offset-3 col-sm-9" style="left: 30%">
												<div class="checkbox">
													<label> <input type="checkbox" /> Remember me
													</label>
												</div>
											</div>
										</div>
										<div class="form-group last">
											<div class="col-sm-offset-3 col-sm-9" style="left: 45%">
												<input type="submit" id="login"
													class="btn btn-lg btn-primary" value="Login"/ >

											</div>
										</div>
									</form>
								</div>

							</div>
						</div>


					</div>




					<!-- ANALYTICS PAGE -->


					<div role="tabpanel" class="tab-pane" id="settings"
						style="height: 530px">

						<p style="margin-top: -2%">
							<b>Select the VQC</b>
						</p>

						<div class="well-sm col-md-3 ">

							<div class="radio-inline" id="vqc">
								<label><input type="radio" id="vqc" value="VQC1"
									name="optradio" checked>VQC-1</label>
							</div>
							<div class="radio-inline">
								<label><input type="radio" id="vqc" value="VQC2"
									name="optradio">VQC-2</label>
							</div>

						</div>



						<select class="selectpicker" data-style="btn-primary"
							style="color: white margin-left:4%; overflow: scroll" id="wed_ts"
							onchange="ntwkinit()">
							<option id="value1" value="--Select--">--Select Time--
							<option id="wed_ts" value="3:00">3:00
							<option id="wed_ts" value="4:00">4:00
							<option id="wed_ts" value="5:00">5:00
							<option id="wed_ts" value="6:00">6:00
							<option id="wed_ts" value="7:00">7:00
							<option id="wed_ts" value="8:00">8:00
							<option id="wed_ts" value="9:00">9:00
							<option id="wed_ts" value="10:00">10:00
							<option id="wed_ts" value="11:00">11:00
							<option id="wed_ts" value="12:00">12:00
							<option id="wed_ts" value="13:00">13:00
							<option id="wed_ts" value="14:00">14:00
							<option id="wed_ts" value="15:00">15:00
							<option id="wed_ts" value="16:00">16:00
							<option id="wed_ts" value="17:00">17:00
							<option id="wed_ts" value="18:00">18:00
							<option id="wed_ts" value="19:00">19:00
							<option id="wed_ts" value="20:00">20:00
							<option id="wed_ts" value="21:00">21:00
							<option id="wed_ts" value="22:00">22:00
							<option id="wed_ts" value="23:00">23:00
						</select>


						<!--  <input type="button" class="btn btn-primary btn-sm outline " value="Show Graph" onclick="chartjs_graph()">  -->



						<span><div id="graph_values" style="visibility: hidden;"></div></span>
						<span><div id="graph_dates" style="visibility: hidden;"></div></span>

						<br>
						<br>
						<div id="load" class="progress"
							style="width: 500px; margin-left: 325px;">
							<div class="progress-bar progress-bar-striped active"
								role="progressbar" aria-valuenow="100" aria-valuemin="0"
								aria-valuemax="100" style="width: 100%"></div>
						</div>
						<div id="graph_div"
							style="margin-left: 13%; position: absolute; width: 750px; height: 350px">
							<canvas id="pred_graph" width="150" height="150"></canvas>
						</div>





					</div>


					<!-- Help Page  -->
					<div role="tabpanel" class="tab-pane" id="help"
						style="height: 350px">
						
						<button onclick="myFunction()" class="btn btn-success">
							<b>About Business Rules</b>
						</button>
						<!-- downloading the Document   -->
						- <a href="/ttd/File/BusinessRule.docx" target="_self">Download</a>
						<button onclick="myFunction1()" class="btn btn-primary"
							style="margin-left: 5%;">
							<b>Assumptions</b>
						</button>
						- <a href="/ttd/File/assumptions.docx" target="_self">Download</a>
						<button onclick="myFunction2()" class="btn btn-danger"
							style="margin-left: 5%;">
							<b>Business Rule Based Validation</b>
						</button>
						- <a href="/ttd/File/BusinessRuleBasedValidation.docx"
							target="_self">Download</a><br></br>
						<script>
function myFunction() {
    window.open("BR.jsp");
}
</script>

						<script>
function myFunction1() {
    window.open("assum.jsp");
}
</script>

						<script>
function myFunction2() {
    window.open("BusinessRuleBasedValidation.jsp");
}
</script>



						<h3 align="left" style="margin-top: 10%;">
							<b><u>For Any Queries Contact Us:-</u></b>
						</h3>
						<h5 align="left">T.N.SREENIVAS, Director (India Operations),
							Email:- sreenivas@exafluence.com, Phone No:- 9849867604</h5>
						<h4 align="left" style="margin-top: 2%;">
							<b><u>For Technical Help (24/7):-</u></b>
						</h4>
						<h5 align="left">Mukunthan R (Project Lead), Email:-
							mukunthan@exafluence.com, Phone No:- 9133373301</h5>
						<h5 align="left">K.Vinay Kumar (Software Engineer), Email:-
							vinay@exafluence.com, Phone No:- 9059644314</h5>
					</div>

				</div>
			</div>
		</div>

		<script type="text/javascript">

 	var ddd;	
	var d2;
	 						
$(document).on('focus', '.c', function() {


$(this).clockpicker({
placement: 'bottom',
align: 'right',
autoclose: true,
'default': '20:48'
});
});							

    /*   Default Date Picker  */ 

	
	
	$(document).ready(function() {
		  var date = new Date();  
	  
	  var today = new Date(date.getFullYear(), date.getMonth(), date.getDate() );
		var tom = new Date();  /// getting tomorrows date
		tom.setDate(today.getDate()+1);
		   var end = new Date(date.getFullYear(), date.getMonth(), date.getDate());
 

	    $('#date').datepicker({
		format: "dd/mm/yyyy",
		todayHighlight: true,
		startDate: today,
		endDate: end,
		autoclose: true
		  });
		  

		  $('#date,#date2').datepicker('setDate', today);
		  
		  
		    $('#datepicker_s').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
				  });
				  

				  $('#datepicker_s').datepicker('setDate', today);
	 	  
	   $('#datevqc1').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
				  }); 
		 
		  $('#datevqc1').datepicker('setDate', today);
		  
		  $('#datevqc2').datepicker({
				format: "dd/mm/yyyy",
				todayHighlight: true,
				startDate: today,
				endDate: end,
				autoclose: true
				  });
				  

				  $('#datevqc2').datepicker('setDate', today);


	}); 
                       
                           /*  Default Day  */
                   
  var d = new Date();
  var days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
  
  
 document.getElementById("demo").innerHTML = days[d.getDay()]; 
 document.getElementById("demovqc1").innerHTML = days[d.getDay()]; 
  document.getElementById("demovqc2").innerHTML = days[d.getDay()]; 

  ddd = days[d.getDay()+1];
  
  d2 =  days[d.getDay()+2];

 
  /* validating the Username and Password in the Admin Page  */
function validateForm() {
    var un = document.loginform.usr.value;
    var pw = document.loginform.pword.value;
    var username = "username"; 
    var password = "password";
    if ((un == username) && (pw == password)) {
        return false;
    }

}
  
 // Number event for the Business Rule page (Only Numbers can enter) 
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 ||charCode > 57)) {
        return false;
    }
    return true;
}

 // Word event for the Business Rule page (only characters can enter)
function isWord(eevt) {
    eevt = (eevt) ? eevt : window.event;
    var charCode = (eevt.which) ? eevt.which : eevt.keyCode;
    if (charCode > 31 && (charCode < 38 ||charCode > 43) && (charCode < 65 ||charCode > 90) && (charCode < 97 ||charCode > 122) ) {
        return false;
    }
    return true;
}

// Time event for the Business Rule page (only Time is validated)
function isTime(eeevt) {
    eeevt = (eeevt) ? eeevt : window.event;
    var charCode = (eeevt.which) ? eeevt.which : eeevt.keyCode;
    if (charCode > 31  ) {
        return false;
    }
    return true;
}


//function for the Modal
function openModal() {
	  document.getElementById('myModal').style.display = "block";
	}

	function closeModal() {
	  document.getElementById('myModal').style.display = "none";
	}

	var slideIndex = 1;
	showSlides(slideIndex);

	function plusSlides(n) {
	  showSlides(slideIndex += n);
	}

	function currentSlide(n) {
	  showSlides(slideIndex = n);
	}

	function showSlides(n) {
	  var i;
	  var slides = document.getElementsByClassName("mySlides");
	  var dots = document.getElementsByClassName("demo");
	  var captionText = document.getElementById("caption");
	  if (n > slides.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = slides.length}
	  for (i = 0; i < slides.length; i++) {
	      slides[i].style.display = "none";
	  }
	  for (i = 0; i < dots.length; i++) {
	      dots[i].className = dots[i].className.replace(" active", "");
	  }
	  slides[slideIndex-1].style.display = "block";

	}



</script>
		<script>
  

    	  /**
    	  * Dropdown scripts
        	  */
    	  $('li.dropdown-submenu').on('click', function(event) {
    	    
    	      if ($(this).hasClass('open')){
    	          $(this).removeClass('open');
    	      }else{
    	          $('li.dropdown-submenu').removeClass('open');
    	          $(this).addClass('open');
    	     }
    	  });
</script>
		<script type="text/javascript">
		
/*
*  Sending the VQC-1 Number of Entered people to the servlet and 
*  retrieving the output from that servlet
*/
var row,value1,value2;
var Duration;

$.ajaxSetup({ cache: false });

$(document).ready(function(){
	
	var temp =0;var tog =0;
	var vq1 = moment().add(1, 'days').format("dddd");
	var vq11 = moment().add(2, 'days').format("dddd");
	
	$(".vqc1").keypress(function (e) {
		 var key = e.which;
		 var date=$('input[name="date"]').val();
		
		 
		 
		 if(key == 13)  // the enter key code
		  { 
		    
		   
		    row = $(this).attr("data-id1");
		    value1 = $(this).val();
			 Duration =$(this).val();
			 var Dayy = $("#demovqc1").text();
			 
			 if(row=="17:00"){
				 
				alert("Please Enter the Business Rules of " + vq11);
			 }
		 	var t = row.split(":");
			

			 $.ajax({
				 type: "POST",	
				 url : 'VQCIServlet',
				  
					data : {
						time : row,
						vqc1: value1,
						 date: date, 
						 
						day :Dayy
						
			 		
					 
					},
					 success : function(responseText,data) {
						  
			/* 			 	if(responseText == "slotover"){
					 	alert("Today Timing Slots are Completed!")
							
						}else */
						
						
						var t1= responseText.split("@");
					 
						  $("#Remainingpeople1"+t[0]).val(t1[0]);
						  $("#pe1"+t[0]).val(t1[1]);
						  $("#slo1"+t[0]).val(t1[2]);
						  $("#ESDIMin"+t[0]).val(t1[4],t1[5]);
						  $("#wait"+t[0]).val(t1[0]);
						 
		 				  
		 
						 var t11 = t1[3].split(":");
							if( ((t11[0] - temp) >= 0 ) && (tog==0)){
							 
								 
								$("#ESDI"+t[0]).val(t1[3]);
								temp=t11[0];
							 var e=t1[3];
							
							 			
							}else{
								 
							$("#ESDI"+t[0]).val(t1[3]+ vq1);
								var s=t1[3]+ "" + days[d.getDay()+1];
								 
							 	temp=t11[0];
								tog=1;
			
							}
									
			 }	
					 }
			 );
		  }
		 
		
		}); 
	
	});
	
	
	
$.ajaxSetup({ cache: false });
	
$(document).ready(function(){	
	
	/*
	*  Sending the VQC-2 Number of Entered people to the servlet and 
	*  retrieving the output from that servlet
	*/
	
	var vq2 = moment().add(1, 'days').format("dddd");
	var vq22 = moment().add(2, 'days').format("dddd");
	var temp =0;var tog =0;
	
	
	$(".vqc2").keypress(function (e) {
		 var key = e.which;
		 var date=$('input[name="date"]').val();
		if(key == 13)  // the enter key code
		  {
		 
		    row = $(this).attr("data-id2");
		 
			 value2 = $(this).val();
			 Duration =$(this).val();
			 var Dayy = $("#demovqc2").text();
			 var d2 = days[d.getDay()+1];
			 if(row=="17:00"){
				 
					alert("Please Enter the Business Rules of " + vq22);
				 }
				 
			 
			 
			var t = row.split(":");
			
			 $.ajax({
				 type: "POST",	
				 url : 'VQCIIServlet',
				  
					data : {
						time : row,
						vqc2: value2,
						
			 			 date:date, 
			 			day :Dayy
						
			 			
			 			 
					},
							
					success : function(responseText) {
						
					 
						
						
						var t2= responseText.split("@");
						
						  $("#Remainingpeople"+t[0]).val(t2[0]);
						  $("#peo"+t[0]).val(t2[1]);
						  $("#slo"+t[0]).val(t2[2]);
						  $("#ESDIIMin"+t[0]).val(t2[4],t2[5]);
						  $("#waiting2"+t[0]).val(t2[0]);
				  
						 var t11 = t2[3].split(":");
							if( ((t11[0] - temp) >= 0 ) && (tog==0)){
							 
								$("#ESDII"+t[0]).val(t2[3]);
								temp=t11[0];
								
							}else{
								  
								$("#ESDII"+t[0]).val(t2[3]+ vq2);
							 	temp=t11[0];
								tog=1;
							}
				
						 
								
					}
				
			 });
		  }
		 
		
		});   
	
});


//Functioning of Enter button in the 'VQC-1 & 2' calculation page 

	 	$(document).keypress(function(e) {
    if(e.which == 13) {

            var index = $('.new').index(document.activeElement) + 1;
            $('.new').eq(index).focus();
         
            $(".new").each(function(){
	        	  
  	          
        	    $(this).focusout(function(){
        	    	
        	    	if($(this).val()!==''){
        	          $(this).prop("readonly", true);
        	    	}else{
        	    		
        	    		$(this).prop("readonly", false);
        	    	
        	    	
        	    	}
        	    });
        	});
        
    }
});
	
	
	   var d = new Date();
	  d1 = d.getTime();
	  if (jQuery('#reloadValue1').val().length === 0)
	  {
	          jQuery('#reloadValue1').val(d1);
	          jQuery('body').show();
	  }
	  else
	  {
	          jQuery('#reloadValue1').val('');
	          location.reload();
	  }  

</script>

		<!-- <script>

$(document).on("click", "#br", function(){

 var dated = $("#date").val();
var dayed = $("#demo").html(); 
 


if(dayed=="Friday"  || dayed=="Saturday" || dayed=="Sunday" ){
	
	
	$("#t1").hide();
}
 
});


</script> -->

		<script>

		/* When the Business Rule button is clicked it gets the daybefore yesterday and date 
		*  then it sends to the dropcoll servlet through ajax.
		*/
		
$(document).on("click", "#br1", function(){


var DayBeforeYesterdayDate = moment().subtract(2, 'days').format("DD/MM/YYYY");
var DayBeforeYesterday = moment().subtract(2, 'days').format("dddd");
 
$.ajax({
		url: "dropcoll",
		type: "POST",
		data: {DayBeforeYesterdayDate:DayBeforeYesterdayDate, DayBeforeYesterday:DayBeforeYesterday}
	
	

	});




});

 
</script>

		<script>
   // Page Refreshing automatically after 24 hours
   
   
   function checkTime(i) {
	   if (i < 10) {
	     i = "0" + i;
	   }
	   return i;
	 }

	 function startTime() {
	   var today = new Date();
	   var h = today.getHours();
	   var m = today.getMinutes();
	   var s = today.getSeconds();
	   // add a zero in front of numbers<10
	   m = checkTime(m);
	   s = checkTime(s);
	   var sm =  h + ":" + m + ":" + s;
	 
	if(sm == "0:00:00"){
		window.location.reload(true);	
	}
	   t = setTimeout(function() {
	     startTime()
	   }, 500);
	 }
	 startTime();
	  
   
   </script>





		<!-- Time Dropdown in Analytics page  -->
		<script>
$(document).ready(function () {
	


    $(".btn-select").each(function (e) {
        var value = $(this).find("ul li.selected").html();
        if (value != undefined) {
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
    });
});

$(document).on('click', '.btn-select', function (e) {
    e.preventDefault();
    var ul = $(this).find("ul");
    if ($(this).hasClass("active")) {
        if (ul.find("li").is(e.target)) {
            var target = $(e.target);
            target.addClass("selected").siblings().removeClass("selected");
            var value = target.html();
            $(this).find(".btn-select-input").val(value);
            $(this).find(".btn-select-value").html(value);
        }
        ul.hide();
        $(this).removeClass("active");
    }
    else {
        $('.btn-select').not(this).each(function () {
            $(this).removeClass("active").find("ul").hide();
        });
        ul.slideDown(300);
        $(this).addClass("active");
    }
});

$(document).on('click', function (e) {
    var target = $(e.target).closest(".btn-select");
    if (!target.length) {
        $(".btn-select").removeClass("active").find("ul").hide();
    }
});

</script>
		<script language="javascript">

var days;
var ele_values;
var ele_graph;
var time_slot;
var queue;
var drag;
var collection;
var d11;
var today;

///// getting the default day /////
d11= moment().format("DD/MM/YYYY");



var d1 = new Date();
var days1 = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
days = days1[d1.getDay()]; 
 
 
 ///////////// getting the vqc type ////////////////////////
 
$(document).on("click", "#vqc", function(){

	 queue1 = $(":checked").val();
    $('#wed_ts option:first').prop('selected',true);
      });
 
 $(document).ready(function(){
	 queue1 = $(":checked").val();
	 
 }); 
 
function select_graph()
{
  
  	ntwkinit()
	
}

//sending the selected vqc and time through json format and getting its response

function ntwkinit()
{
 
	$("#load").show();

$("#graph_div").html('');

	var drag = document.getElementById("wed_ts").value;
	 
	if(days=="Monday"){
		 
		
		$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"mondayhis",time:drag,queue:queue1,date:d11},graph_res)
		}else if(days=="Tuesday"){
			                                                                                                                                                                                              
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"tuesdayhis",time:drag,queue:queue1,date:d11},graph_res)
	
			
		}else if(days=="Wednesday"){
			
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"wednesdayhis",time:drag,queue:queue1,date:d11},graph_res)
			
			
		}else if(days=="Thursday"){
			
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"thursdayhis",time:drag,queue:queue1,date:d11},graph_res)
			
			
		}else if(days=="Friday"){
			
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"fridayhis",time:drag,queue:queue1,date:d11},graph_res)
			
			
		}else if(days=="Saturday"){
			
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"saturdayhis",time:drag,queue:queue1,date:d11},graph_res)
			
			
		}else if(days=="Sunday"){
			
			$.get("slot_process.jsp",{dbIP:"52.38.31.24",port:"27795",db:"FORECAST",coll:"sundayhis",time:drag,queue:queue1,date:d11},graph_res)
		 }
	 
}
 
function graph_res(response)
{
	 if(response)
		 {
		 $("#load").hide();
		 response=response.trim();
		  
		 $("#p").text(response);
		 
		  $("#graph_values").empty();
	var resp = response.split("@");
	 
	      document.getElementById("graph_values").innerHTML=resp[0];//$("#graph_values").append(response);
	      document.getElementById("graph_dates").innerHTML=resp[1];//$("#graph_values").append(response);
	      
	      chartjs_graph();
		 }
}

function chartjs_graph()
{
	var values=document.getElementById('graph_values').innerHTML;
	var values1=document.getElementById('graph_dates').innerHTML;
	var values2=document.getElementById('demo').innerHTML;
 
	$("#graph_values").empty();$("#graph_dates").empty();
	
	$("#pred_graph").remove(); 
	$('#graph_div').html('');
	$('#graph_div').append('<canvas  id="pred_graph" width=950 height=500></canvas>');
	var context=$("#pred_graph");
	
	 var xval=new Array();
		values1=values1.trim();
		xval=values1.split(",");
	 	var xx = xval.slice(-3);
		var yval=new Array();
		values=values.trim();
		yval=values.split(",");
		 
		for(i=0;i<yval.length;i++)
			yval[i]=parseInt(yval[i]);
	   
		
		var yval1=yval.slice(0,13);
		var yval2=yval.slice(-3);
		 
	var pointBackgroundColors = [];
	var pointBorderColors = [];
	 var strlength = yval.length-3;
	 
	 for(x=1;x<=yval.length;x++)
		 {
		 if(x<=strlength)
			{
			 pointBackgroundColors.push("rgba(54, 162, 235, 0.2)");
			 pointBorderColors.push("rgba(54, 162, 235, 1)");
			}else{
				pointBackgroundColors.push("#FA8072");
				pointBorderColors.push("#FA8072");
			}
		 
		 }
 
		var chart_data={
		    		    labels: xval,	  	    	    
		      			datasets:[ 
		      		
		    		         {
		    		            
		    		        	 label:  "Number of Pilgrims on "+values2 ,
		    		        	 
		    		            pointBackgroundColor: pointBackgroundColors,
		    			        backgroundColor:  'rgba(54, 162, 235, 0.2)' ,
		    			           
		    		            pointBorderColor:  pointBorderColors,        
		    		           borderColor:  'rgba(54, 162, 235, 1)' ,
		    		            borderWidth: 1,
		    		            data: yval
		    		        
		    		        }
	          ,
		         
		      { data: [],
	        	
	        	  label: "Forecasted Number of Pilgrims ",
	        	  
	        	     backgroundColor: "rgba(255,160,122,0.2)",
			            borderColor: "Salmon",
		  
		          } 
		        
		    ]
		};

	var chart=new Chart(context,
			{
		      type : 'line',
				
		      data : chart_data,
		      tooltips: {
		          enabled: false
		      },
		      animation:{
		    	  animateScale:true
		      },
		      
		      options:{
		     
		    	  responsive:true,
		    	 	
		    	  hover: {
		              mode: 'label'
		          },
		          
		          title: {
		              display: true,
		              text: 'Pilgrim Influx Forecasting	'
		            },
	               
	               
	               scales:
	            	   {
	            	   yAxes:[{
		            		 display: true,
		            		 scaleLabel: {
		            			 display: true,
		            			 labelString: "Number of Pilgrims",
		            		 }
		            	         }
		            	        ],
	              
       	                  xAxes:[{
	            		    display: true,
	            			 scaleLabel: {
		            			 display: true,
		            			 labelString: "Successive "+values2+"'s",
		            		 }
	            	         }   
	            	        ]
		            	        
	            	   }
	            	   
		      }
	            	   
			}
			);  
	 
}

</script>

<!-- 	<input type="hidden" id="refresh" value="no">
		<script type="text/javascript">
		
		$(document).ready(function(e) {
		    var $input = $('#refresh');
	  	});
		if(!!window.performance && window.performance.navigation.type == 2)
		{
		    window.location.reload();
		}
	 	</script> -->
</body>
</html>