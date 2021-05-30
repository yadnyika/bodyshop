
<!DOCTYPE html>
<%@page import="com.bodyshop.dao.BodyShopDao"%>
<%@page import="com.bodyshop.pojo.Product"%>
<%@page import="java.util.List"%>
<%@page import="com.bodyshop.pojo.RegisterPOJO"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="org.apache.log4j.Logger" %>
<%-- Get a reference to the logger for this class --%>
<% Log logger = LogFactory.getLog(this.getClass()); 

// Logger logger = Logger.getLogger("welcome.jsp");  %>

<% logger.debug( "This is a debug message from a welcome jsp" ); %>
<% logger.info( "This is a info message from a welcome jsp" ); %>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<title>BodyShop Welcome</title>
<!-- Icons font CSS-->
<link href="vendor/mdi-font/css/material-design-iconic-font.min.css"
	rel="stylesheet" media="all">
<link href="vendor/font-awesome-4.7/css/font-awesome.min.css"
	rel="stylesheet" media="all">
<link rel="stylesheet" href="styles.css" />
<!-- Font special for pages-->
<link
	href="https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Vendor CSS-->
<link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
<link href="vendor/datepicker/daterangepicker.css" rel="stylesheet"
	media="all">
	<link href="fontawesome/css/all.css" rel="stylesheet"> 
	
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
}

.topnav {
	overflow: hidden;
	background: -webkit-gradient(linear, left bottom, right top, from(#fc2c77),
		to(#6c4079));
	background: -webkit-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
	background: -moz-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
	background: -o-linear-gradient(bottom left, #fc2c77 0%, #6c4079 100%);
	background: linear-gradient(to top right, #fc2c77 0%, #6c4079 100%);
}

.topnav a {
	float: left;
	display: block;
	color: black;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
	font-size: 17px;
}

.topnav a:hover {
	background-color: #ddd;
	color: black;
}

.topnav a.active {
	background-color: #2196F3;
	color: white;
}

.topnav .search-container {
	float: right;
}

.topnav input[type=text] {
	padding: 6px;
	margin-top: 8px;
	font-size: 17px;
	border: none;
}

.topnav .search-container button {
	float: right;
	padding: 6px 10px;
	margin-top: 8px;
	margin-right: 16px;
	background: #ddd;
	font-size: 17px;
	border: none;
	cursor: pointer;
}

.topnav .search-container button:hover {
	background: #ccc;
}

@media screen and (max-width: 600px) {
	.topnav .search-container {
		float: none;
	}
	.topnav a, .topnav input[type=text], .topnav .search-container button {
		float: none;
		display: block;
		text-align: left;
		width: 100%;
		margin: 0;
		padding: 14px;
	}
	.topnav input[type=text] {
		border: 1px solid #ccc;
	}
}
</style>
<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">
<script src="store.js" async></script>
</head>
<script>
	
</script>

<body>


	<%
	//session.setAttribute("mobileNo", "08329719275");
	String mobileNo=null;
	String userName="User";
	if(session!=null)
	{
		if(null!=session.getAttribute("mobileNo"))
		{
		mobileNo=(String)session.getAttribute("mobileNo");
		}
		
	}
		BodyShopDao dao = new BodyShopDao();
		String searchText = request.getParameter("search");
		List<Product> products = null;
		if (null != searchText) {
			logger.info( "inside search text search text is not null "+searchText );
			//System.out.println("search tesxt ="+searchText);
			products = dao.fetchSearchProductDetails(searchText);
			logger.info( "fetcching products after searchs "+products );

		} else {
			products = dao.fetchAllProductsDetails();
			logger.info( "fetch all product details "+products );
		}
	%>	
	<%if(null!=mobileNo){
		
		RegisterPOJO userPojo=dao.fetchUserDetails(mobileNo);
		userName=userPojo.getName();
		
	%>
	 <input type="text" name="mobileNo" value='<%=mobileNo%>' hidden> 
	<%}else{
		logger.info( "session is null or mobile no not found  " );
	%>
	<input type="text" name="mobileNo" value='NotFound' hidden> 
	<%}%>
<div class="topnav">
		<a href="#home">Home</a> <a href="#about">About</a> <a href="#contact">Contact</a>
		
		<div >
		<div class="iconShopping">
				<p>0</p>
				<i class="fa fa-shopping-cart"></i>
			</div>
		</div>
		<%if(mobileNo!=null){%><a href="LogOutServlet">LogOut</a> <%}else{%><a href="login.jsp">LogIn</a> <%}%>
		<div class="search-container">
			<table>
				<tr>
					<td><input type="text" placeholder="Search.." name="search"></td>
					<td class="space"><button type="button" class="btn-search">
							<i class="fa fa-search"></i>
						</button></td>
				</tr>
			</table>

		</div>

		<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
			<div class="card card-4">
				<div class="card-body">

					<h2 class="title">Welcome  <%=userName %> </h2>
					
					<section class="container content-section">
						<h2 class="section-header">Cosmatics</h2>
						<div class="shop-items">
							<%
								for (Product p1 : products) { 
							%>
							<div class="shop-item">
								<span class="shop-item-title"><%=p1.getProductName()%></span> <img
									class="shop-item-image" src=<%=p1.getImage()%>>
								<div class="shop-item-details">
									<span class="shop-item-price">Rs.<%=p1.getPrice()%></span>
									<button class="btn1 btn-primary shop-item-button" type="button">ADD TO CART</button>
								</div>
							</div>
							<%
								}
							%>

						</div>
					</section>
					<!-- <section class="container content-section">
						<form method="GET" action="addproduct">
							<h2 class="section-header">CART</h2>
							<div class="cart-row">
								<span class="cart-item cart-header cart-column">ITEM</span> <span
									class="cart-price cart-header cart-column">PRICE</span> <span
									class="cart-quantity cart-header cart-column">QUANTITY</span>
							</div>
							<div class="cart-items"></div>
							<div class="cart-total">
								<strong class="cart-total-title">Total</strong> <span
									class="cart-total-price">Rs0</span>
							</div>
							<button class="btn btn-primary btn-purchase" type="submit">PURCHASE</button>
						</form>
					</section> -->
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
<div class="cartBox">
		<div class="cart">
			<i class="fa fa-close"></i>
			<br/><br/><br/>
		<div id="content">
			<section class="container content-section">
						<form method="GET" action="addproduct">
							<h1 class="section-header">CART</h1>
							<div class="cart-row">
								<span class="cart-item cart-header cart-column">ITEM</span> <span
									class="cart-price cart-header cart-column">PRICE</span> <span
									class="cart-quantity cart-header cart-column">QUANTITY</span>
							</div>
							<div class="cart-items"></div>
							<div class="cart-total">
								<strong class="cart-total-title">Total</strong> <span
									class="cart-total-price">Rs0</span>
							</div>
							<button class="btn btn-primary btn-purchase" type="submit">PURCHASE</button>
						</form>
				</section>
				</div>
		</div>
	</div>

</body>
</html>