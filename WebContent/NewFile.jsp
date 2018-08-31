<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>


<!-- autocomplete.js scripts -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!-- autocomplete.js scripts end -->
 

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<label>Hello</label><input  id="dot"/>
<script>
$(document).ready(function(){
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
   

	
   $( "#dot" ).autocomplete({
     source: availableTags
   });
   console.log(availableTags);	
 } );
});
</script>
</body>
</html>