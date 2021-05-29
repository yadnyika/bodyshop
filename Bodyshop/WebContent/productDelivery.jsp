
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

tr:nth-child(even){background-color: #f2f2f2}

th {
  background-color: #4CAF50;
  color: white;
   font-size:20px;
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
	margin-top: 8px; margin-right : 16px;
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
td.space{
 padding: 0px;
}
</style>
</head>
<script>
	
</script>

<body>
	<%
	String mobileNo=null;
	
	BodyShopDao dao=new BodyShopDao();

	if(null!=request.getSession())
	{
	 mobileNo=(String)session.getAttribute("mobileNo");
	 
	}

	if(null==mobileNo)
	{
	response.sendRedirect("welcome.jsp");	
	return;
	}
	else{
		if(!mobileNo.equalsIgnoreCase("9404101265")){	 response.sendRedirect("welcome.jsp");return;}
		
	}

		String searchText1 =request.getParameter("search1");
		List<Order> orderList=null;
		if(null!=searchText1){
			//System.out.println("search tesxt ="+searchText);
		 orderList=dao.fetchSearchOrderDetails(searchText1);
			
		}
		else{
			orderList = dao.fetchAllProductsDeliverery();
		}
	%>

	<div class="page-wrapper bg-gra-02 p-t-130 p-b-100 font-poppins">
		<div class="card card-4">
			<div class="card-body">
				<div class="search-container">
					<table>
						<tr>
							<td ><input  type="text"
								placeholder="Search.." name="search1"></td>
							<td class="space"><button type="button" class="btn-search1">
									<i class="fa fa-search"></i>
								</button></td>
						</tr>
					</table>



				</div>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Address</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>OrderId</th>
						<th>DateOfDelivery</th>
						<th>Order Date</th>
						<th>Order Status</th>
						<th>Edit</th>

					</tr>

					<%
						int i = 0;
						for (Order p1 : orderList) {
					%>
					<tr>
						<td id="orderId<%=i%>"><%=p1.getId()%></td>
						<td id="orderAddress<%=i%>"><%=p1.getAddress()%></td>
						<td id="orderTotal<%=i%>"><%=p1.getTotal()%></td>
						<td id="orderQuantity<%=i%>"><%=p1.getQuantity()%></td>
						<td id="orderOrderId<%=i%>" class="orderID"><%=p1.getOrderId()%></td>
						<td id="orderDeliveryTime<%=i%>"><%=p1.getOrderDeliveryTime()%></td>
						<td id="orderDate<%=i%>"><%=p1.getOrderDate()%></td>
						<td id="orderStatus<%=i%>"><%=p1.getOrderStatus()%></td>
						<td><button class="btn btn-Order-Edit"
								OnClick="orderEdit('<%=p1.getOrderId()%>','<%=p1.getAddress()%>','<%=p1.getTotal()%>','<%=p1.getQuantity()%>','<%=p1.getOrderDeliveryTime()%>','<%=p1.getOrderDate()%>','<%=p1.getOrderStatus()%>')">Edit
								Details</button></td>

					</tr>
					<%
						i++;
						}
					%>
				</table>

				<br> <br> <br> <br> <br>

				<div class="order-main-class">
					<!-- <section class="container content-section order-edit">
						<h2 class="section-header">Order Edit</h2>
						<div class="shop-items">
							<div class="shop-item">
								<div class="row row-space">

    
									<div class="input-group">
										<label class="label">Address</label> <input
											class="input--style-4" type="text" name="Address"
											placeholder="Address" required>
									</div>
								</div>
								<div class="row row-space">
									<div class="input-group">
										<label class="label">Order Delivery</label> <input
											class="input--style-4" type="text" name="Order Delivery"
											placeholder="Order Delivery" required>
									</div>

								</div>

								<div class="row row-space">

									<div class="input-group">
										<label class="label">Order Date</label> <input
											class="input--style-4" type="text" name="OrderDate"
											placeholder="OrderDate" required>
									</div>


								</div>

								<div class="input-group">
									<label class="label">Order Status</label>
									<div class="rs-select2 js-select-simple select--no-search">
										<select name="Order Status" required>
											<option disabled="disabled">Choose
												option</option>
											<option value="NotDeliver">Not Deliver</option>
											<option value="Deliver">Delivered</option>
											<option value="null">null</option>
										</select>
										<div class="select-dropdown"></div>
									</div>
								</div>
								<div class="shop-item-details">
									<button class="btn btn-primary btn-purchase" type="submit">Update
									</button>
								</div>

							</div>
						</div>
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
</body>
</html>