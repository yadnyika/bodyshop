<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="com.bodyshop.dao.BodyShopDao"%>
<%@page import="com.bodyshop.pojo.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.*"%>
<%@page import="com.bodyshop.pojo.AddressDetails"%>
<%@page import="com.bodyshop.pojo.Order"%>
<%@page import="com.bodyshop.pojo.ProductDetails"%>
<%@page import="com.bodyshop.pojo.RegisterPOJO"%>
<%@page import="java.lang.Object"%>
<%@page import="java.lang.Object"%>
<%@ page import="org.apache.commons.logging.Log" %>
<%@ page import="org.apache.commons.logging.LogFactory" %>
<%@ page import="org.apache.log4j.Logger" %>
<html>
<% Log logger = LogFactory.getLog(this.getClass()); 

// Logger logger = Logger.getLogger("welcome.jsp");  %>

<% logger.debug( "This is a debug message from a bill jsp" ); %>
<% logger.info( "This is a info message from a bill jsp" ); %>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="Colorlib Templates">
<meta name="author" content="Colorlib">
<meta name="keywords" content="Colorlib Templates">

<title>BodyShop Order Bill</title>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<link rel="stylesheet" href="css/bill.css">
<script src="store.js" async></script>
</head>
<body>
<% 
String mobileNo=null;
RegisterPOJO register=null;
AddressDetails address=null;
Order order=null;
BodyShopDao dao=new BodyShopDao();
List<ProductDetails> productDetails=null;
if(null!=request.getSession())
{
 mobileNo=(String)session.getAttribute("mobileNo");
 
}
else
{
 logger.info( "inside session not found" ); 
 
}

if(null==mobileNo)
{
	 logger.info( "inside mobileNo not found redirecting to login.jsp" );
response.sendRedirect("login.jsp");	
return;
}
else
{
	if(null!=session.getAttribute("orderMap")){
	HashMap<String,Object> dataMap=(HashMap)session.getAttribute("orderMap");
	logger.info( "inside orderMap  found orderMap "+dataMap );
	if(null!=dataMap.get("orders"))
	{
		logger.info("inside orders found"+dataMap.get("orders"));
	 order=(Order)dataMap.get("orders");
	 productDetails=dao.fetchProductDetailsByOrderId(order.getOrderId());
	 register=dao.fetchUserDetails(mobileNo);
	 address=dao.fetchAddressDetailsByAddressId(order.getAddressId());
	}
	else
	{
		logger.info("inside orders not found redirecting to welcome jsp");
		response.sendRedirect("welcome.jsp");
		return;
	}

	
	}
	
}



%>
<h3>Shopping Bill </h3>
<hr>
<div class="left-div"><h3>Name: <%=register.getName()%> </h3></div>
<div class="right-div-right"><h3>Email: <%=register.getEmail() %>  </h3></div>
<div class="right-div"><h3>Mobile Number: <%=address.getOrderMobileNo() %></h3></div>  

<div class="left-div"><h3>Order Date: <%=order.getOrderDate() %> </h3></div>
<div class="right-div-right"><h3>Payment Method: <%=order.getGateway()%> </h3></div>
<div class="right-div"><h3>Expected Delivery: <%=order.getOrderDeliveryTime() %> </h3></div> 

<div class="left-div"><h3>Transaction Id: <%=order.getTxnId() %> </h3></div>
<div class="right-div-right"><h3>City: <%=order.getCity() %></h3></div> 
<div class="right-div"><h3>Address: <%=order.getCity() %> </h3></div> 

<div class="left-div"><h3>State: <%=order.getState() %> </h3></div>
<div class="right-div-right"><h3>Country: <%=order.getCountry() %> </h3></div>  

<hr>


	
	<br>
	
<table id="customers">
<h3>Product Details</h3>
  <tr>
    <th>S.No</th>
    <th>Product Name</th>
 
    <th>Price</th>
    <th>Quantity</th>
     <th>Sub Total</th>
  </tr>
  <%
  int i=1;
  for(ProductDetails product: productDetails){ 
  Product p=dao.fetchProductDetails(product.getProductName());
  %>
  
  <tr>
  	<td><%=i %></td>
    <td><%=product.getProductName() %></td>
    <td><%=p.getPrice() %></td>
    <td><%=product.getQuantity() %></td>
    <td><%=(p.getPrice()*product.getQuantity()) %></td>
     
  </tr>
  <% i++;} %>
  <tr>

</table>
<h3>Total: <%=order.getTotal() %></h3>

<a onclick="window.print();"><button class="button right-button">Print</button></a>
<a href="welcome.jsp"><button class="button right-button">Home</button></a>
<br><br><br><br>
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