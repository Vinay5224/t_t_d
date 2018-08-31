 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
<link type="text/css" href="webassist/datepicker/untitled1_datepicker_1/jquery-ui.css" rel="stylesheet" />
<link rel="stylesheet" href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.19/themes/pepper-grinder/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
</script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>  
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
    String name = (String) session.getAttribute("Email");  
   if(name ==null){
	   response.sendRedirect("DarshanTimeEstimation.jsp"); 
	   }
   else
   {
	   out.println(" ");
   }

   %>
   
   <input type="hidden" class="form-control" id="reloadValue">
 
<div class="container">


<!-- Top banner starts  -->
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
  <!-- Top banner ends  -->
  
  
  <!-- Logout Button -->
   <a type="button" href="DarshanTimeEstimation.jsp"> <img src="https://i.imgur.com/BHuQVEm.png" style="height:40px ;float:right" /></a>
  

        
        
         <div class="col-lg-2">

 
 

  </div>
        
        
  <h4 style="margin-top:7%;color:black"> <b><u>CHOOSE ANY ONE TIMESLOT :-</u></b></h4>
 
 
  <h5 style="margin-top:2%;margin-left:10%;color:white"> <b >One Hour Timeslot :-</b></h5>
  
  <!-- Selecting the date from the datepicker and searching and it redirects to the OneHour.jsp page -->
  
  <div class=" col-sm-12 " style="margin-left:30%" >
                    <form class="form-signin" name="Hour" onSubmit="return validateForm();" action="OneHour.jsp">
                    <div class="form-group">
                    <div class="col-xs-2"  >
<input id="datepicker_1"  name="dte" class="form-control" placeholder="Select the Date"   type="text" / required>
</div>
<div class="col-xs-2" >
 <input id="dayOfWeekSpan" name="day" class="form-control"  type="text"   />
</div> 

                    </div>
                    
                    
                    <div class="col-sm-2" style="top:0px;left:-2%" >
                           <input type="submit" id ="demo1" class="hell btn btn-md btn-primary"  value = "search" />
             
                        </div>    
                 
                    </form>
             
              
           
        </div>

  
   
   <!-- Half-an-Hour slot page --> 
  <div class="form-group" style=";margin-top:6%">
    <div class="checkbox2">
      <label class="btn btn-primary"style="margin-left:17%;padding: 0.2cm 15.5cm 0.2cm 0cm;box-shadow: 3px 3px 3px white" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
     <h style="margin-left:223%">Half an Hour Timeslot</h> 
      </label>
    </div>
  </div>
  <div id="collapseTwo" aria-expanded="true" class="collapse ">
  
    <div class="well">
      <form method="post"  action="half">
    <fieldset>
    <div class="form-group col-sm-2">
      <label for="datepicker1" style="color: white">Date</label>
      <input type="text" name="date" class="form-control" id="datepicker" placeholder="Select the Date">

    </div>
  </fieldset>
  <table class="table table-bordered">
    <thead>
      <tr>
        <th  >Time Slot</th>
        <th>Real Time People (VQC-I)</th>
        <th>Real Time People (VQC-II)</th>
        <th>Calculated Time(VQC-I)</th>
        <th>Calculated Time(VQC-II)</th>
         <th>Real Time Darshnam(VQC-I)</th>
        <th>Real Time Darshnam(VQC-II)</th>
        
        
      </tr>
    <tbody>
   <% double [] number = {0.00, 0.30, 1.00, 1.30, 2.00, 2.30, 3.00, 3.30, 4.00, 4.30, 5.00, 5.30, 6.00, 6.30,7.00,7.30,8.00, 8.30, 9.00, 9.30,10.00,11.00,11.30,12.00,12.30,13.00,13.30,14.00,14.30,15.00,15.30,16.00,16.30,17.00,17.30,18.00,18.30,19.00,19.30,20.00,21.30,22.00,22.30,23.00,23.30};  %>
   <% for ( double n: number){ %>
   
   
 
 
   
      
       <tr>
      
               <td   class="col-xs-2"><%  out.print(n+"0"); %>  </td>
        <input type="hidden" name="Time" value="<%  out.print(n+"0"); %>" />
         
       
        <td  ><input id="Real(VQC-I)" name="rvqc11" type="text" class="form-control" placeholder="No.of.Pilgrims" ></td>  
        <td><input id="Real(VQC-II)" name="rvqc21" type="text" class="form-control" placeholder="No.of.Pilgrims"></td>
        <td><input id="Calculated(VQC-I)" name="cvqc11" type="text" class="form-control" placeholder="Est.Darshan Time" ></td>  
         <td><input id="Calculated(VQC-II)" name="cvqc21" type="text" class="form-control" placeholder="Est.Darshan Time" ></td> 
        <td><input id="CalculatedTime(VQC-I)" name="ctvqc11" type="text" class="form-control" placeholder="Real Darshan Time"></td> 
          <td><input id="CalculatedTime(VQC-II)" name="ctvqc21" type="text" class="form-control" placeholder="Real Darshan Time"></td> 
      </tr>
       <% } %>  
     
      </tbody>
      </table>
   
   <button type="button" class="btn btn-primary " style="float:right;margin-top:-1.5%">Save</button>
   <!-- Half-an-Hour slot ends -->
   </form></div>
  </div>
</div>
   </body>
   

   
   <script type="text/javascript">
   
   
                               /* initial date  */
  $(document).ready(function(){
    var date_input=$('input[name="date"]'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
      format: 'dd/mm/yyyy',
      container: container,
      todayHighlight: true,
      autoclose: true,
    };
    date_input.datepicker(options);
  }); 
                               
         
  var d = new Date();
  d = d.getTime();
  if (jQuery('#reloadValue').val().length === 0)
  {
          jQuery('#reloadValue').val(d);
          jQuery('body').show();
  }
  else
  {
          jQuery('#reloadValue').val('');
          location.reload();
  }                             
                               
                               
                               /* Datepicker With Day */
                             $(function(){
	$('#datepicker_1').datepicker({
		dateFormat: 'dd/mm/yy',
		showAnim: 'show',
		onSelect: function(dateText){
        var seldate = $(this).datepicker('getDate');
        seldate = seldate.toDateString();
        seldate = seldate.split(' ');
        var weekday=new Array();
            weekday['Mon']="Monday";
            weekday['Tue']="Tuesday";
            weekday['Wed']="Wednesday";
            weekday['Thu']="Thursday";
            weekday['Fri']="Friday";
            weekday['Sat']="Saturday";
            weekday['Sun']="Sunday";
        var dayOfWeek = weekday[seldate[0]];
        $('#dayOfWeekSpan').val(""+dayOfWeek+"");
		},
		onClose: closeDatePicker_datepicker_1
		
	});
});
function closeDatePicker_datepicker_1() {
	var Date = $('#datepicker_1');
	var dd = $('#datepicker_1').val();
	if (typeof datepicker_1_Spry != null && typeof datepicker_1_Spry != "undefined" && test_Spry.validate) {
		datepicker_1_Spry.validate();
	}
	Date.blur();

}
                               
                               

	function isNumber(evt) {
   	    evt = (evt) ? evt : window.event;
   	    var charCode = (evt.which) ? evt.which : evt.keyCode;
   	    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
   	        return false;
   	    }
   	    return true;
   	}
	
	
	</script>
	   <script>
	var d = new Date();
	var days = ["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"];
	document.getElementById("demo").innerHTML = days[d.getDay()];
   
	                           /* Validate date */
	function validateForm() {
	    var un = document.loginform.dte.value;
	  
	    var date = "date"; 
	    
	    if ((dt == date)) {
	        return false;
	    }
	    else {
	        alert ("login successfully");
	        return true;
	    }
	}
   </script>
   

</html>
