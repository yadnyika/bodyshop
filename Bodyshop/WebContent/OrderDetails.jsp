<%@page import="com.bodyshop.pojo.AddressDetails"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.bodyshop.dao.BodyShopDao"%>
<%@page import="com.bodyshop.pojo.Product"%>
<%@page import="com.bodyshop.pojo.AddressDetails"%>
<%@page import="com.bodyshop.pojo.Cart"%>
<%@page import="com.bodyshop.pojo.RegisterPOJO"%>
<%@page import="java.util.List"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="org.apache.log4j.Logger" %>
<html>
<head>
<% Log logger = LogFactory.getLog(this.getClass()); 

// Logger logger = Logger.getLogger("welcome.jsp");  %>

<% logger.debug( "This is a debug message from a OrderDetails jsp" ); %>
<% logger.info( "This is a info message from a OrderDetails jsp" ); %>
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
<style>
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
</style>
<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">
<title>Cart</title>

</head>

<body>
	<%
		String mobileNo = null;
		String userName = "User";
		List<Product> productlist = null;
		List<Cart> cartList = null;
		AddressDetails address = null;
		if (null != request.getSession()) {
			if (null != session.getAttribute("mobileNo")) {
				mobileNo = (String) session.getAttribute("mobileNo");
			}

		}
		else
		{
			logger.info( "inside session is not found " );
			
		}

		if (null == mobileNo) {
			logger.info( "inside mobileNo is not found redirecting to login.jsp" );
			response.sendRedirect("login.jsp");
			return;
		} else {
			productlist = (List<Product>) session.getAttribute("ProductList");
			cartList = (List<Cart>) session.getAttribute("CartList");

			address = (AddressDetails) session.getAttribute("Address");
			logger.info( "inside cart list and adress list "+cartList+ " address "+address );
			if (null != cartList && cartList.size() > 0) {

			} else {
				response.sendRedirect("welcome.jsp");
				return;
			}
		}
	%>
	<%
		if (null != mobileNo) {

			if (null != session.getAttribute("userPojo")) {
				RegisterPOJO userPojo = (RegisterPOJO) session.getAttribute("userPojo");
				userName = userPojo.getName();
				logger.info( "User login userName"+userName );
			}
	%>
	<input type="text" name="mobileNo" value='<%=mobileNo%>' hidden>
	<%
		} else {
	%>
	<input type="text" name="mobileNo" value='NotFound' hidden>
	<%
		}
	%>
	<div class="topnav">
		<a href="welcome.jsp">Home</a><a href="LogOutServlet">LogOut</a>
		<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
			<div class="card card-4">
				<div class="card-body">
					<form method="GET" action="orderproduct">
						<section class="container content-section">

							<h2 class="section-header">CART</h2>
							<div class="cart-row">
								<span class="cart-item cart-header cart-column">ITEM</span> <span
									class="cart-price cart-header cart-column">PRICE</span> <span
									class="cart-quantity cart-header cart-column">QUANTITY</span>
							</div>
							<div class="cart-items">
								<%
									for (Cart c1 : cartList) {
								%>
								<div class="cart-row">
									<div class="cart-item cart-column">
										<img class="cart-item-image" src="<%=c1.getImage()%>"
											width="100" height="100"> <span class="cart-item-title"
											name="<%=c1.getProductName()%>"><%=c1.getProductName()%></span>
									</div>
									<span class="cart-price cart-column"><%=c1.getPrice()%></span>
									<div class="cart-quantity cart-column">
										<input class="cart-quantity-input" type="number"
											value="<%=c1.getQuantity()%>">
										<button class="btn btn-danger" type="button">REMOVE</button>
									</div>
								</div>
								<%
									}
								%>
							</div>
							<div class="cart-total">
								<strong class="cart-total-title">Total</strong> <span
									class="cart-total-price">Rs:0</span>
							</div>
						</section>
						<section class="container content-section">
							</br> </br> </br> </br> </br> </br>
						</section>
						<section class="container content-section">
							<div class="user_ddress">
								<%
									if (null != address) {
								%>
								<h2 class="section-header">User Details</h2>
								<input type="text" name="addrId"
									value="<%=address.getAddressId()%>" hidden />
								<div class="shop-items">
									<div class="shop-item">
										<div class="row row-space">
											<div class="col-2">
												<div class="input-group">
													<label class="label">Country</label> <input
														class="input--style-4" type="text" name="Country"
														placeholder="Country" value="<%=address.getCountry()%>"
														required>
												</div>
											</div>
											<div class="col-2">
												<div class="input-group">
													<label class="label">State</label> <input
														class="input--style-4" type="text" name="State"
														placeholder="State" value="<%=address.getState()%>"
														required>
												</div>
											</div>
										</div>
										<div class="row row-space">
											<div class="col-2">
												<div class="input-group">
													<label class="label">City</label> <input
														class="input--style-4" type="text" name="City"
														value="<%=address.getCity()%>" placeholder="City" required>
												</div>
											</div>
											<div class="col-2">
												<div class="input-group">
													<label class="label">Mobile No</label> <input
														class="input--style-4" type="text" name="Mobile No"
														value="<%=address.getMobileNo()%>" placeholder="Mobile No"
														required>
												</div>
											</div>
										</div>
										<div class="row row-space">
											<div class="input-group">
												<label class="label">Address</label> <input
													class="input--style-4" type="text" name="Address"
													value="<%=address.getAddress()%>" placeholder="Address"
													required>
											</div>
										</div>
										<div class="input-group">
											<label class="label">Select way of Payment Gateway</label>
											<div class="rs-select2 js-select-simple select--no-search">
												<select name="Gateway" required>
													<option disabled="disabled" selected="selected">Choose
														option</option>
													<option value="COD" selected>COD</option>
													<option value="UPI">UPI</option>
													<option value="Net Banking">Net Banking</option>
												</select>
												<div class="select-dropdown"></div>
											</div>
										</div>

										<div class="row"><div class="col-2">
											<button class="btn btn-primary btn-address1" type="submit">Proceed
												to Generate Bill</button>
										
</div><div class="col-2">
										
											<button class="btn btn-primary btn-address" type="button">Change
												Address</button>
										</div><div class="col-2">
											<button
												onclick="document.forms[0].action='welcome.jsp';return true"
												type="submit" class="btn btn-primary btn-address">Continue
												Shopping</button>
										</div><div class="col-2">

										
											<button class="btn btn-primary btn-new-address" type="button">Pay
												with new Address</button>
										</div>
									</div>
								</div>
								<%
									} else {
								%>
								<h2 class="section-header">Add User Details</h2>
								<div class="shop-items">
									<div class="shop-item">
										<div class="row row-space">
											<div class="col-2">
												<div class="input-group">
													<label class="label">Country</label> <input
														class="input--style-4" type="text" name="Country"
														placeholder="Country" required>
												</div>
											</div>
											<div class="col-2">
												<div class="input-group">
													<label class="label">State</label> <input
														class="input--style-4" type="text" name="State"
														placeholder="State" required>
												</div>
											</div>
										</div>
										<div class="row row-space">
											<div class="col-2">
												<div class="input-group">
													<label class="label">City</label> <input
														class="input--style-4" type="text" name="City"
														placeholder="City" required>
												</div>
											</div>
											<div class="col-2">
												<div class="input-group">
													<label class="label">Mobile No</label> <input
														class="input--style-4" type="text" name="Mobile No"
														placeholder="Mobile No" required>
												</div>
											</div>
										</div>
										<div class="row row-space">
											<div class="input-group">
												<label class="label">Address</label> <input
													class="input--style-4" type="text" name="Address"
													placeholder="Address" required>
											</div>
										</div>
										<div class="input-group">
											<label class="label">Select way of Payment Gateway</label>
											<div class="rs-select2 js-select-simple select--no-search">
												<select name="Gateway" required>
													<option disabled="disabled" selected="selected">Choose
														option</option>
													<option value="COD" selected>COD</option>
													<option value="UPI">UPI</option>
													<option value="Net Banking">Net Banking</option>
												</select>
												<div class="select-dropdown"></div>
											</div>
										</div>
										<div class="shop-item-details">
											<button class="btn btn-primary btn-order" type="submit">Proceed
												to Generate Bill</button>
										</div>

									</div>
								</div>

								<%
									}
								%>
							</div>

						</section>
						<section class="container content-section">
							<div class="address_header"></div>

							<div class="address-row">


								<span class="address-quantity address-header address-column"></span>
							</div>
							<div class="address-city"></div>


						</section>

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
</html>