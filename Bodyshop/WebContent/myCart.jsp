

<!DOCTYPE html>
<%@page import="com.bodyshop.dao.BodyShopDao"%>
<%@page import="com.bodyshop.pojo.Order"%>
<%@page import="com.bodyshop.pojo.RegisterPOJO"%>
<%@page import="com.bodyshop.pojo.ProductDetails"%>
<%@page import="java.util.List"%>
<html>
<head>

<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<title>All Orders</title>
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

<!-- Main CSS-->
<link href="css/main.css" rel="stylesheet" media="all">
<script src="store.js" async></script>
<style>
.search-container {
	float: right;
}

table {
	border-collapse: collapse;
	width: 100%;
}

td.active {
	background-color: #2c6ed5;
}

th, td {
	text-align: center;
	padding: 8px;
}

tr:nth-child(even) {
	background-color: #f2f2f2
}

th {
	background-color: #4CAF50;
	color: white;
	font-size: 20px;
}

input {
	padding: 7px;
	margin-top: 8px;
	font-size: 17px;
	border: none;
	background: #ddd;
}

.search-container button {
	float: right;
	padding: 6px 10px;
	margin-top: 8px;
	margin-right: 16px;
	background: #ddd;
	font-size: 17px;
	border: none;
	cursor: pointer;
	margin-right: 10px;
}

.search-container button:hover {
	background: #ccc;
}

/* .search-container button {
	
    text-align: right;
    width: 150%;
    margin: 0;
    padding: 6px;
    background: #ddd;
} */
td.space {
	padding: 0px;
}
</style>
</head>
<script>
	
</script>

<body>
	<%
		String mobileNo = null;
		List<Order> orderlist = null;
		BodyShopDao dao = new BodyShopDao();

		if (null != request.getSession()) {
			mobileNo = (String) session.getAttribute("mobileNo");

		}

		if (null == mobileNo) {
			response.sendRedirect("welcome.jsp");
			return;
		}

		else {
			orderlist = dao.fetchMyUserOrderDetails(mobileNo);
		}
	%>
	<%if(null!=mobileNo){
		
	%>
	 <input type="text" name="mobileNo" value='<%=mobileNo%>' hidden/> 
	<%}else{
		//logger.info( "session is null or mobile no not found  " );
	}%>

	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="card card-4">
			<div class="card-body">

				<table border="1">
					<tr>
						<th>OrderId</th>
						<th>Order Details</th>
						<th>Order Date</th>
						<th>OrderPrice</th>

						<th>ViewOrderDetails</th>
					</tr>

					<%
						int i = 0;
						for (Order p1 : orderlist) {
					%>
					<tr>


						<td id="orderOrderId<%=i%>" class="orderID"><%=p1.getOrderId()%></td>
						<td id="orderDetails<%=i%>"><%=p1.getProductDetails()%></td>
						<td id="orderDate<%=i%>"><%=p1.getOrderDate()%></td>
						<td id="orderPrice<%=i%>"><%=p1.getTotal()%></td>
						<td><button class="btn btn-vieworder"
								OnClick="viewOrder('<%=p1.getOrderId()%>','<%=p1.getAddress()%>','<%=p1.getTotal()%>','<%=p1.getQuantity()%>','<%=p1.getOrderDeliveryTime()%>','<%=p1.getOrderDate()%>','<%=p1.getOrderStatus()%>')">View Order
								Details</button></td>

					</tr>
					<%
						i++;
						}
					%>
				</table>

				<br> <br> <br> <br> <br>


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
		
			
						<form method="GET" action="addproduct">
							<h1 class="section-header">My Order</h1>
							<div class="cart-row">
							<table border="1">
					<tr>
						<th>OrderId</th>
						<th>Order Details</th>
						<th>OrderPrice</th>
						<th>OrderExpectedDelivery</th>
						<th>OrderStatus</th>
						<th>Cancel Order
								Details</th>
					</tr>
					
					<tr>
						<td></td>
						
						<td >
						<section class="container content-section">
						<form method="GET" action="addproduct">
							<div class="cart-row">
								<span class="cart-item cart-header cart-column">ITEM</span> <span
									class="cart-price cart-header cart-column">PRICE</span> <span
									class="cart-quantity cart-header cart-column">QUANTITY</span>
							</div>
							<div class="cart-items">
							
							</div>
							
								
							</div>
							
						</form>
				</section>
						
						
						</td>
						<td ></td>
						<td ></td>
						<td ></td>
						<td><button class="btn btn-Order-Edit">Edit
								Details</button></td>

					</tr>

				</table>
								</div>
							
						</form>
				
				
		</div>
	</div>
</body>
</html>