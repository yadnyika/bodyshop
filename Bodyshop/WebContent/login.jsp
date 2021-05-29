
<!DOCTYPE html>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="org.apache.log4j.Logger" %>
<html lang="en">

<head>
<% Log logger = LogFactory.getLog(this.getClass()); 

// Logger logger = Logger.getLogger("welcome.jsp");  %>

<% logger.debug( "This is a debug message from a login jsp" ); %>
<% logger.info( "This is a info message from a login jsp" ); %>
<!-- Required meta tags-->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<!-- Title Page-->
<title>Login Forms</title>

<!-- Icons font CSS-->
<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<!-- Font special for pages-->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Vendor CSS-->
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet"
	media="all">

<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">
</head>
<script src="store.js" async>
/* if(null!= document.getElementsByClassName('btn-login')[0])
{
document.getElementsByClassName('btn-login')[0].addEventListener('click', loginEvent)
}

function loginEvent(event)
{
	console.log("user login event called")
	var mobileNo = document.getElementsByName('mobileNo')[0].value
	console.log("mobile No "+mobileNo)
	var password = document.getElementsByName('password')[0].value
	// Instantiate an xhr object		
	const xhr = new XMLHttpRequest();
	// Open the object
	xhr.open('GET', 'http://localhost:8090/Bodyshop/LoginServlet?mobileNo='+mobileNo+'&password='+password, true);
	// What to do when response is ready
	xhr.onload = function () {
	    if(this.status === 200){
	    	console.log("obj in response without json parse "+this.responseText);
	    	var message=this.responseText;
	    	if(message==="User Login Successfully")
	    		{
	    		console.log("user login successfully")
	    		}
	    }
	    else
	    	{
	    	console.log("somthing went wrong")
	    	}
	    }
	
} */

</script>
<body>
<%
String message=null;
String mobileNo=null;
String hiddenProperty="hidden";

	
 

if(null!=request.getSession())
{
	if(null!=session.getAttribute("loginMessage"))
	{
	message=(String)session.getAttribute("loginMessage");
	}

	if(null!=session.getAttribute("mobileNo"))
	{
 mobileNo=(String)session.getAttribute("mobileNo");
	}
	else
	{
		logger.info( "inside user is logout in session hence session is found but mobileNo not" );
	}
}else{
	logger.info( "session not found user is not login" );
}

if(null!=message)
{hiddenProperty="";
	}

if(null!=mobileNo)
{
	logger.info( "inside user is already login redirecting to welcome.jsp with mobile No "+mobileNo );
	response.sendRedirect("welcome.jsp");	
	return; 
}

%>
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="wrapper wrapper--w680">
			<div class="card card-4">
				<div class="card-body">
					<h2 class="title">Login Form</h2>
					<form method="GET" id="loginForm" action="LoginServlet">
					<input type=text  value='<%=message%>'<%=hiddenProperty%>/>
						<div class="row row-space">
							<div class="col-2">
								<div class="input-group">
									<label class="label">Mobile No</label> <input
										class="input--style-4" type="text" name="mobileNo">
						</div>
							</div>

							<div class="col-2">
								<div class="input-group">
									<label class="label">Password</label> <input
										class="input--style-4" type="password" name="password">
								</div>
							</div>
						</div>
						<!-- <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                 <label class="label">Password</label>
                                    <input class="input--style-4" type="text" name="password">
                                    <label class="label">Birthday</label>
                                    <div class="input-group-icon">
                                        <input class="input--style-4 js-datepicker" type="text" name="birthday">
                                        <i class="zmdi zmdi-calendar-note input-icon js-btn-calendar"></i>
                                    </div> 
                                </div>
                            </div> 
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Gender</label>
                                    <div class="p-t-10">
                                        <label class="radio-container m-r-45">Male
                                            <input type="radio" checked="checked" name="gender" value="male">
                                            <span class="checkmark"></span>
                                        </label>
                                        <label class="radio-container">Female
                                            <input type="radio" name="gender" value="female">
                                            <span class="checkmark"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row row-space">
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Email</label>
                                    <input class="input--style-4" type="email" name="email">
                                </div>
                            </div>
                            <div class="col-2">
                                <div class="input-group">
                                    <label class="label">Phone Number</label>
                                    <input class="input--style-4" type="text" name="phone">
                                </div>
                            </div>
                        </div>
                        <div class="input-group">
                            <label class="label">Place</label>
                            <div class="rs-select2 js-select-simple select--no-search">
                                <select name="place">
                                    <option disabled="disabled" selected="selected">Choose option</option>
                                    <option value="Chandrapur">Chandrapur</option>
                                    <option value="Nagpur">Nagpur</option>
                                    <option value="Mumbai">Mumbai</option>
                                </select>
                                <div class="select-dropdown"></div>
                            </div>
                        </div> -->
						<div class="row row-space">
							<div class="col-2">
								<div class="p-t-15">
									<input class="btn btn--radius-2 btn--blue btn-login" type="button" value="Submit" />
								</div>
							</div>
							<div class="col-2">
								<div class="p-t-15">
								<a href="register.jsp">Register</a>
								</div>
							</div>
						</div>
						<div>
							
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- Jquery JS-->
	<script src="vendor/jquery/jquery.min.js"></script>
	<!-- Vendor JS-->
	<script src="vendor/select2/select2.min.js"></script>
	<script src="vendor/datepicker/moment.min.js"></script>
	<script src="vendor/datepicker/daterangepicker.js"></script>

	<!-- Main JS-->
	<script src="js/global.js"></script>

</body>
<!-- This templates was made by Colorlib (https://colorlib.com) -->

</html>
<!-- end document-->