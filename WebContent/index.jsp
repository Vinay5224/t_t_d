<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css"href="css/bootstrap.min.css">
  <script type="text/javascript"src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script type="text/javascript" src="js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<style>

 
body{ background:url("images/loginbg.JPG")center center;
/* http://www.bluitgroup.com/wp-content/uploads/2015/11/plain-light-color-for-guest-background.jpg */
background-repeat: no-repeat;
background-attachment: fixed;
background-position: center;
background-size: 1350px 650px;

}


* {
    box-sizing: border-box;
}

/* Header styling */
h2 {
    text-align: center;
    margin-bottom: 50px;
    color: #fff;
    font-family:Timesnewroman;
}



/* form starting stylings ------------------------------- */
.group {
    position: relative;
    margin-bottom: 35px;
}

/* input styling   */
input {
    font-size: 18px;
    
    padding: 5px 10px 10px 5px;
    display: block;
    width: 100%;
    border: none;
    border-bottom: 1px solid #fff;
    background: transparent;
}

/* input styling for focus */
    input:focus {
        outline: none;
    }

/* LABEL ======================================= */
label {
    color: #fff;
    font-size: 23px;
    font-family:Timesnewroman;
    font-weight: normal;
    position: absolute;
    pointer-events: none;
    left: 5px;
    top: 10px;
    transition: 0.2s ease all;
    -moz-transition: 0.2s ease all;
    -webkit-transition: 0.2s ease all;
}

/* active state */
input:focus ~ label, input:valid ~ label {
    top: -20px;
    font-size: 14px;
    color: #fff;
}

/* BOTTOM BARS ================================= */
.bar {
    position: relative;
    display: block;
    width: 100%;
}

    .bar:before, .bar:after {
        content: '';
        height: 2px;
        width: 0;
        bottom: 1px;
        position: absolute;
        background: #fff;
        transition: 0.2s ease all;
        -moz-transition: 0.2s ease all;
        -webkit-transition: 0.2s ease all;
    }

    .bar:before {
        left: 50%;
    }

    .bar:after {
        right: 50%;
    }



/* active state */
input:focus ~ .bar:before, input:focus ~ .bar:after {
    width: 50%;
}

/* HIGHLIGHTER ================================== */
.highlight {
    position: absolute;
    height: 60%;
    width: 100px;
    top: 25%;
    left: 0;
    pointer-events: none;
    opacity: 0.5;
}

/* active state */
input:focus ~ .highlight {
    -webkit-animation: inputHighlighter 0.3s ease;
    -moz-animation: inputHighlighter 0.3s ease;
    animation: inputHighlighter 0.3s ease;
}

/* ANIMATIONS ================ */
@-webkit-keyframes inputHighlighter {
    from {
        background: #fff;
    }

    to {
        width: 0;
        background: transparent;
    }
}

@-moz-keyframes inputHighlighter {
    from {
        background: #fff;
    }

    to {
        width: 0;
        background: transparent;
    }
}

@keyframes inputHighlighter {
    from {
        background: #fff;
    }

    to {
        width: 0;
        background: transparent;
    }
}

 /* Panel styling */	
#panel {
    border: 1px solid rgb(200, 200, 200);
    box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 5px 2px;
   /* background: -webkit-linear-gradient(90deg, #2caab3 0%, #2c8cb3 100%);
    background: red; /* For browsers that do not support gradients */ 
 background: -webkit-linear-gradient(90deg, rgba(44, 170, 179, 0.63) 0%, rgba(76, 142, 169, 0.88) 100%);
  /* background: -o-linear-gradient(90deg, #2caab3 0%, #2c8cb3 100%); /* For Opera 11.1 to 12.0 */ 
 /*  background: -moz-linear-gradient(90deg, #2caab3 0%, #2c8cb3 100%); /* For Firefox 3.6 to 15 */   
  /*  background: -webkit-linear-gradient(90deg, rgba(186, 155, 145, 0.77) 0%, rgba(185, 155, 146, 0.77) 100%); */
    
    
    border-radius: 4px;
    top: 50px;
}

/* Button Styling  */
.button {
    position: relative;
    
    border: none;
    font-size: 23px;
    font-family: Bodoni;
    color: #FFFFFF;
    padding: 8px;
    width: 100px;
    text-align: center;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    text-decoration: none;
    overflow: hidden;
    cursor: pointer;
}

.button:after { 
    content: "";
    background: #fcfcfc;
    display: block;
    position: absolute;
    padding-top: 300%;
    padding-left: 350%;
    margin-left: -20px!important;
    margin-top: -120%;
    opacity: 0;
    transition: all 0.8s
}

.button:active:after {
    padding: 0;
    margin: 0;
    opacity: 1;
    transition: 0s
}
</style>
<body>

<div class="container">

<div class="img-figure"style="margin-top:3%">
  <div class="panel panel-default" style="box-shadow: 3px 3px 1px gray;">
  <div class="panel-heading custom_class" style="height:160px;background-color:white; " >
  <h1 align="center" style="margin-top:0%;margin-left:-4%;color:#3876c2;text-shadow: 2px 2px 1px black;font-size:45px;font-family:Timesnewroman">Forecasting of Piligrim Influx <br>&<br>Estimated Darshan Time</h1>
  </div>
<div id="image" style="float:right; margin-RIGHT:2%;margin-top:-11%">
<a href="http://www.exafluence.com" target="new">
<img src="images/company-logo.png" alt="exafluence" class="media-object" style="width:210px;height:90px"align="right"/></a>
<p style="margin-left:30%"><b>Beta Ver.1.0</b></p>
</div>
<div id="image" style="float:left; margin-right:4%;margin-left:1%; margin-top:-13%;">
<img src="images/ttdlogo.png" class="media-object" style="width:150px ; height:130px"align="left"/>
</div>


  </div>


</div>

    <div class="col-lg-offset-4 col-lg-4" id="panel">
        <h2> Login </h2>
<!-- Sending the Username and Password to the HomeLoginServlet.java using form with post method  -->
        <form method="post"  action="HomeLoginServlet">

            <div class="group">
                <input type="email" name="email_id" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Username</label>
            </div>

    
            <div class="group">
                <input type="password" name="password" required>
                <span class="highlight"></span>
                <span class="bar"></span>
                <label>Password</label>
            </div>
          
            <div class="group">
                <center><button type="submit" class="button btn btn-primary">Login</button></center>
            </div>
              <h5 align="center" style="color:white"><b>Beta Version for Testing</b> </h5>
              
              
                      <% String Name=(String)  request.getAttribute("name");
						 if (Name=="n") { %>
					     <div class="alert alert-danger">Invalid username or password</div>
					<% } else { %>
					      <p></p>
					<% } %>
            
        </form>

    </div>
</div>

</body>
</html>



