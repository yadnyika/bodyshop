<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="description" content="This is the description">
<link rel="stylesheet" href="styles.css" />
<script src="store.js" async></script>
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
<title>Add Product</title>

</head>
<body>

<% 
String mobileNo=null;
if(null!=request.getSession())
{
	
 mobileNo=(String)session.getAttribute("mobileNo");
 
}

if(null==mobileNo)
{
response.sendRedirect("login.jsp");	
return;
}
else
{
if(!mobileNo.equalsIgnoreCase("9404101265"))
{
	response.sendRedirect("login.jsp");	
	return;
	}
}
%>
	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="card card-4">
			<div class="card-body">


				<form action="fileUploadServlet" method="post"
					enctype="multipart/form-data">
					<section class="container content-section">
						<h2 class="section-header">Add Product</h2>
						<div class="shop-items">
							<div class="shop-item">
								<div class="input-group">
									<label class="label">Product Name</label> <input
										class="input--style-4" type="text" name="productName">
								</div>
								<div class="input-group">
									<label class="label">Product Price</label> <input
										class="input--style-4" type="text" name="productPrice">
								</div>
								<div class="input-group">
									<label class="label">Product Image</label> <input
										class="input--style-4" type="file" name="file" />
								</div>
								<div class="shop-item-details">

									<button class="btn btn-primary shop-item-button" type="submit">ADD
										PRODUCT</button>
								</div>
							</div>
						</div>
					</section>
				</form>
			</div>
		</div>
	</div>
	

</body>
</html>