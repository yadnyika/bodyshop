if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', ready)
    
} else {
    ready()
}
var editParamNo=0;
var ordervisibility=false;

function ready() {
	
    var removeCartItemButtons = document.getElementsByClassName('btn-danger')
    for (var i = 0; i < removeCartItemButtons.length; i++) {
        var button = removeCartItemButtons[i]
        button.addEventListener('click', removeCartItem)
    }

    var quantityInputs = document.getElementsByClassName('cart-quantity-input')
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i]
        input.addEventListener('change', quantityChanged)
    }

    var addToCartButtons = document.getElementsByClassName('shop-item-button')
    for (var i = 0; i < addToCartButtons.length; i++) {
        var button = addToCartButtons[i]
        button.addEventListener('click', addToCartClicked)
    }
    var changeAddressButtons = document.getElementsByClassName('btn-change-address')
    for (var i = 0; i < changeAddressButtons.length; i++) {
        var button = changeAddressButtons[i]
        button.addEventListener('click', changeAddress)
        
    }
    if(null!= document.getElementsByClassName('btn-purchase')[0])
    	{ 
    	document.getElementsByClassName('btn-purchase')[0].addEventListener('click', purchaseClicked)
    	}
    if(null!= document.getElementsByClassName('btn-address')[0])
	{ 
    document.getElementsByClassName('btn-address')[0].addEventListener('click', getAddress)
	}
    if(null!= document.getElementsByClassName('btn-new-address')[0])
	{
    document.getElementsByClassName('btn-new-address')[0].addEventListener('click', addNewAddress)
	}
    if(null!= document.getElementsByClassName('btn-search')[0])
	{
    document.getElementsByClassName('btn-search')[0].addEventListener('click', searchProduct)
	}
    
    if(null!= document.getElementsByClassName('btn-order-update')[0])
	{
    document.getElementsByClassName('btn-order-update')[0].addEventListener('click', orderUpdate)
	}
    
    if(null!= document.getElementsByClassName('btn-search1')[0])
	{
    document.getElementsByClassName('btn-search1')[0].addEventListener('click', searchOrderId)
	}
    if(null!= document.getElementsByClassName('btn-login')[0])
	{
    document.getElementsByClassName('btn-login')[0].addEventListener('click', loginEvent)
	}
   
   
    /*
	 * var orderEditRemove = document.getElementsByClassName('order-edit')[0]
	 * while (orderEditRemove.hasChildNodes()) {
	 * orderEditRemove.removeChild(orderEditRemove.firstChild) }
	 */
    
    
  /*
	 * var orderEditButtons = document.getElementsByClassName('btn-Order-Edit')
	 * for (var i = 0; i < orderEditButtons.length; i++) { var button =
	 * orderEditButtons[i] button.addEventListener('click', function(){
	 * alert("in order edit event click"+i) orderEdit(i); }); }
	 */
    
   // document.getElementsByClassName('btn-change-address')[0].addEventListener('click',
	// changeAddress)
// document.getElementsByClassName('btn-change-address')[0].addEventListener('click',
// changeAddress)
    updateCartTotal()
    getCartData()
    updateCartQuantity()
    
    const iconShopping = document.querySelector('.iconShopping');
    const cartCloseBtn = document.querySelector('.fa-close');
    const cartBox = document.querySelector('.cartBox');
    iconShopping.addEventListener("click",function(){
    	cartBox.classList.add('active');
    	
    });
    cartCloseBtn.addEventListener("click",function(){
    	cartBox.classList.remove('active');
    });
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
	xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/LoginServlet?mobileNo='+mobileNo+'&password='+password+'&Ajax=Y', true);
	// What to do when response is ready
	xhr.onload = function () {
	    if(this.status === 200){
	    	console.log("obj in response without parse "+this.responseText);
	    	var message=this.responseText;
	    	if(message==="User Login Successfully")
	    		{
	    		console.log("user login successfully");
	    		
	    		let items = [];
	    	    if(typeof(Storage) !== 'undefined'){
	    			
	    			if(JSON.parse(localStorage.getItem('items')) === null){
	    				
	    				
	    			}else{
	    				const localItems = JSON.parse(localStorage.getItem("items"));
	    				localItems.map(data=>{
	    					
	    					// Instantiate an xhr object
	    			        const xhr = new XMLHttpRequest();
	    			        xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/AddToCartServlet?prodName='+data.name+'&image='+data.imageSrc+'&quantity='+data.quantity+'&price='+data.price+'&mobileNo='+mobileNo, true);
	    			    	// What to do when response is ready
	    			        xhr.onload = function () {
	    			            if(this.status === 200){
	    			            	// window.location.href='http://35.194.29.49:8080/Bodyshop/productDelivery.jsp';
	    			            	console.log("Cart item added in db")
	    			            	
	    			            }
	    			            else{
	    			                console.log("Some error occured")
	    			            } 
	    			        }

	    			        // send the request
	    			        xhr.send();
	    				});
	    				
	    				localStorage.clear();;
	    		    	
	    				
	    			}
	    		}else{
	    			alert('local storage is not working on your browser');
	    		}
	    		
	    		document.getElementById("loginForm").submit();
	    		}else{
	    			window.location.reload();
	    		}
	    }
	    else
	    	{
	    	console.log("somthing went wrong")
	    	}
	    }
	xhr.send();
}

function viewOrder(orderId,orderAddress,orderTotal,orderQuantity,orderDeliveryTime,orderDate,orderStatus){
	
	


 // Instantiate an xhr object
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/getOrderDetailsServlet?orderId='+orderId, true);
	// What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){
        	console.log("obj in response without json parse in viewOrder "+this.responseText);
        	let obj=JSON.parse(this.responseText)
        	var i=0;
    	var cartHtml="";
    	for (key in obj)
        {
    		var cartRow = document.createElement('div')
    	    cartRow.classList.add('cart-row')
    	    
    	    var cartItems = document.getElementsByClassName('cart-items')[0]
    	    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    	    for (var i = 0; i < cartItemNames.length; i++) {
    	        if (cartItemNames[i].innerText == obj[key].productName) {
    	            ////alert('This item is already added to the cart')
    	            return
    	        }
    	    }
    	    var cartRowContents = `
    	        <div class="cart-item cart-column">
    	            <img class="cart-item-image" src="${obj[key].image}" width="100" height="100">
    	            <span class="cart-item-title">${obj[key].productName}</span>
    	        </div>
    	        <span class="cart-price cart-column">${obj[key].price}</span>
    	        <div class="cart-quantity cart-column">
    	            <input  type="number" value="${obj[key].quantity}">
    	            
    	        </div>`
    	    cartRow.innerHTML = cartRowContents
    	    cartItems.append(cartRow)
    	    cartHtml=cartItems.innerHTML
    	    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    	    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
    	    i++;
    	    }
    	
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    xhr.send();
    console.log("We are done fetching employees!");
	
	
	
	
    const cartCloseBtn = document.querySelector('.fa-close');
    const cartBox = document.querySelector('.cartBox');

    	cartBox.classList.add('active');
    	
 
    cartCloseBtn.addEventListener("click",function(){
    	cartBox.classList.remove('active');
    });
}

function getCartData()
{
	 
	console.log('You have clicked the pop handler');
	var mobileNo = document.getElementsByName('mobileNo')[0].value
	console.log("mobile No "+mobileNo)
if(null!= document.getElementsByClassName('cart-items')[0])
	{
if(mobileNo!=null&&mobileNo!=="NotFound")
{
// Instantiate an xhr object
const xhr = new XMLHttpRequest();
// Open the object
xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/CartFetchServlet', true);
// What to do when response is ready
xhr.onload = function () {
    if(this.status === 200){
    	console.log("obj in response without json parse "+this.responseText);
    	let obj=JSON.parse(this.responseText)
    	// var json='{"products":[{"id":66,"name":"Album
		// 3","image":"Images/Album
		// 3.png","price":800,"quantity":1}],"Msg":"product ftched success
		// fully"}';
    	// let obj2=JSON.parse(json)
    	// console.log("obj in response afte json parse "+obj[0].productName);
    	var i=0;
    	var cartHtml="";
    	for (key in obj)
        {
    		var cartRow = document.createElement('div')
    	    cartRow.classList.add('cart-row')
    	    
    	    var cartItems = document.getElementsByClassName('cart-items')[0]
    	    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    	    for (var i = 0; i < cartItemNames.length; i++) {
    	        if (cartItemNames[i].innerText == obj[key].productName) {
    	            ////alert('This item is already added to the cart')
    	            return
    	        }
    	    }
    	    var cartRowContents = `
    	        <div class="cart-item cart-column">
    	            <img class="cart-item-image" src="${obj[key].image}" width="100" height="100">
    	            <span class="cart-item-title">${obj[key].productName}</span>
    	        </div>
    	        <span class="cart-price cart-column">${obj[key].price}</span>
    	        <div class="cart-quantity cart-column">
    	            <input class="cart-quantity-input" type="number" value="${obj[key].quantity}">
    	            <button class="btn btn-danger" type="button">REMOVE</button>
    	        </div>`
    	    cartRow.innerHTML = cartRowContents
    	    cartItems.append(cartRow)
    	    cartHtml=cartItems.innerHTML
    	    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    	    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
    	    i++;
    	    }
    	console.log("inner html "+cartHtml)
    	//getCardDataInCartIcon(cartHtml)
        
        
    	 
    	updateCartTotal()
        updateCartQuantityFromDb(i);
	    	
    }
    else{
        console.log("Some error occured")
    }
}
xhr.send();
}
else{
	let items = [];
    if(typeof(Storage) !== 'undefined'){
		
		if(JSON.parse(localStorage.getItem('items')) === null){
			
			
		}else{
			var cartHtml="";
			const localItems = JSON.parse(localStorage.getItem("items"));
			localItems.map(data=>{
				

	    		var cartRow = document.createElement('div')
	    	    cartRow.classList.add('cart-row')
	    	     if(null!= document.getElementsByClassName('cart-items')[0])
    	    	{
	    	    var cartItems = document.getElementsByClassName('cart-items')[0]
	    	    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
	    	    for (var i = 0; i < cartItemNames.length; i++) {
	    	        if (cartItemNames[i].innerText == data.name) {
	    	            alert('This item is already added to the cart')
	    	            return
	    	        }
	    	    }
	    	    var cartRowContents = `
	    	        <div class="cart-item cart-column">
	    	            <img class="cart-item-image" src="${data.imageSrc}" width="100" height="100">
	    	            <span class="cart-item-title">${data.name}</span>
	    	        </div>
	    	        <span class="cart-price cart-column">${data.price}</span>
	    	        <div class="cart-quantity cart-column">
	    	            <input class="cart-quantity-input" type="number" value="${data.quantity}">
	    	            <button class="btn btn-danger" type="button">REMOVE</button>
	    	        </div>`
	    	    cartRow.innerHTML = cartRowContents
	    	    cartItems.append(cartRow)
	    	    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
	    	    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
	    	    
				
					items.push(data);
	    	    updateCartTotal()
    	    	}
			});
			cartHtml=document.getElementsByClassName('cart-items')[0].innerHTML
			
			//getCardDataInCartIcon(cartHtml)
			if(null!= document.getElementsByClassName('cart-items')[0])
	    	{
			localStorage.setItem('items',JSON.stringify(items));
	    	}
			
		}
	}else{
		alert('local storage is not working on your browser');
	}
}
}



// send the request

console.log("We are done fetching employees!");	

}

function getCardDataInCartIcon(cartHtml)
{

	 
	console.log('You have getCardDataInCartIcon'+cartHtml);
	var mobileNo = document.getElementsByName('mobileNo')[0].value
	console.log("mobile No "+mobileNo)
if(null!= document.getElementsByClassName('cart-items')[1])
	{
	var cartItems=document.getElementsByClassName('cart-items')[1]
	 cartItems.innerHTML=cartHtml
	}



// send the request

console.log("We are done fetching employees!");	
	
}


function orderUpdate(event)
{
		
	var orderAddresss=document.getElementsByName('OrderAddress')[0].value
	// alert("Order Update event called "+orderAddresss )
	var orderDeliveryTime =document.getElementsByName('OrderDelivery')[0].value
	var orderDate =document.getElementsByName('OrderDate')[0].value
	var OrderStatus =document.getElementsByName('OrderStatus')[0].value
	var orderId=document.getElementsByName('OrderId')[0].value

   
 // Instantiate an xhr object
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/updateOrderServlet?orderAddresss='+orderAddresss+'&orderDeliveryTime='+orderDeliveryTime+'&orderDate='+orderDate+'&OrderStatus='+OrderStatus+'&orderId='+orderId, true);
	// What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){
        	window.location.href='http://35.194.29.49:8080/Bodyshop/productDelivery.jsp';
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    xhr.send();
    console.log("We are done fetching employees!");
	
	}

function orderEdit(orderId,orderAddress,orderTotal,orderQuantity,orderDeliveryTime,orderDate,orderStatus){
	// alert("in order edit order id "+orderId +" orderAddress "+ orderAddress +
	// " orderTotal "+ orderTotal +" orderQuantity "+ orderQuantity +"
	// orderDeliverytime "+ orderDeliveryTime +" orderDate "+ orderDate+"
	// orderStatus "+orderStatus)
	// var orderId=document.getElementsByClassName('orderID')[i].innerText;
	// alert("order edit click "+orderId)
	
	 
	if(null!=document.getElementsByClassName('order-edit')[0])
	{
		
		for (var i = 0; i < document.getElementsByClassName('order-edit').length; i++) {
			var orderEditRemove = document.getElementsByClassName('order-edit')[i]
			while (orderEditRemove.hasChildNodes()) {
				orderEditRemove.removeChild(orderEditRemove.firstChild)
				
			}
	    }
}
	
	 var orderRow = document.createElement('section')
	    orderRow.classList.add('container')
	     orderRow.classList.add('content-section')
	      orderRow.classList.add('order-edit')
	    var orderMainClass = document.getElementsByClassName('order-main-class')[0]
	 
	 var orderStatus1=""
		 var orderStatus2=""
			 
			 if("Not Delivered"==orderStatus)
				 {
				 orderStatus1="Delivered"
				orderStatus2="In Process"	 
				 }
			 else if("Delivered"==orderStatus)
				 {
				 orderStatus1="Not Delivered"
						orderStatus2="In Process"	
				 }
			 else
				 {
				 orderStatus1="Not Delivered"
						orderStatus2="Delivered"
				 }
	 
	    var orderRowContents = `
	        <h2 class="section-header">Order Edit</h2>
							<div class="shop-items">
								<div class="shop-item">
									<div class="row row-space"><div class="input-group">
											<label class="label">OrderId</label> <input
												class="input--style-4" type="text" name="OrderId"
												placeholder="orderid" value='${orderId}' readOnly >
										</div>
									</div>
									<div class="row row-space">
										<div class="input-group">
											<label class="label">Address</label> <input
												class="input--style-4" type="text" name="OrderAddress"
												placeholder="Address" value='${orderAddress}' required>
										</div>
									</div>
									<div class="row row-space">
										<div class="input-group">
											<label class="label">Order Delivery</label> <input
												class="input--style-4" type="text" name="OrderDelivery"
												placeholder="Order Delivery"  value='${orderDeliveryTime}' required>
										</div>

									</div>

									<div class="row row-space">

										<div class="input-group">
											<label class="label">Order Date</label> <input
												class="input--style-4" type="text" name="OrderDate"
												placeholder="OrderDate" value='${orderDate}' required>
										</div>


									</div>

									<div class="input-group">
										<label class="label">Order Status</label>
										<div class="rs-select2 js-select-simple select--no-search">
											<select name="OrderStatus" required>
												<option disabled="disabled" >Choose
													option</option>
												<option value="${orderStatus}" selected="selected">${orderStatus}</option>
												<option value="${orderStatus1}">${orderStatus1}</option>
												<option value="${orderStatus2}">${orderStatus2}</option>
											</select>
											<div class="select-dropdown"></div>
										</div>
									</div>
									<div class="shop-item-details">
										<button class="btn btn-primary btn-order-update" type="button">Update
										</button>
									</div>

								</div>
							</div>`
	    	orderRow.innerHTML = orderRowContents
	    	orderMainClass.append(orderRow)
	    	
	    	
 if(null!= document.getElementsByClassName('btn-order-update')[0])
	{
    document.getElementsByClassName('btn-order-update')[0].addEventListener('click', orderUpdate)
	}
		
	
	}
function searchOrderId(event){
	console.log("Search event called")	
	var searchtext=document.getElementsByName('search1')[0].value

        	window.location.href='http://35.194.29.49:8080/Bodyshop/productDelivery.jsp?search1='+searchtext;

}

function searchProduct(event){
	console.log("Search event called")	
	var searchtext1=document.getElementsByName('search')[0].value

        	window.location.href='http://35.194.29.49:8080/Bodyshop/welcome.jsp?search='+searchtext1;

}

function addNewAddress(event){

	console.log("AddAddress event called")
  
 // Instantiate an xhr object
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/AddressFetchServlet?addAddress=newAddress', true);
	// What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){
        	window.location.reload();  
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    xhr.send();
    console.log("We are done fetching employees!");

}

function changeAddress(event){
	console.log("changeAddress event called")
    var button = event.target
    var changeAddress = button.parentElement.parentElement
    var changeAddressId = changeAddress.getElementsByClassName('change-address-id')[0].innerText
 // Instantiate an xhr object
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/AddressFetchServlet?changeAddressId='+changeAddressId, true);
	// What to do when response is ready
    xhr.onload = function () {
        if(this.status === 200){
        	window.location.reload();  
        }
        else{
            console.log("Some error occured")
        }
    }

    // send the request
    xhr.send();
    console.log("We are done fetching employees!");
}

function getAddress(){ 
	console.log('You have clicked the pop handler');

// Instantiate an xhr object
const xhr = new XMLHttpRequest();
var mobileNo = document.getElementsByName('Mobile No')[0].value
console.log("mobile No "+mobileNo)
// Open the object
xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/AddressFetchServlet?mobileNo='+mobileNo, true);


// What to do when response is ready
xhr.onload = function () {
    if(this.status === 200){
    	console.log("obj in response without json parse "+this.responseText);
    	let obj=JSON.parse(this.responseText)
    	// var json='{"products":[{"id":66,"name":"Album
		// 3","image":"Images/Album
		// 3.png","price":800,"quantity":1}],"Msg":"product ftched success
		// fully"}';
    	// let obj2=JSON.parse(json)
    	console.log("obj in response afte json parse "+obj[0].name);
        // console.log("obj in response afte json parse "+obj2.products[0].id);
        document.getElementsByClassName('btn-address')[0].style.visibility = 'hidden';
        document.getElementsByClassName('user_ddress')[0].style.visibility = 'hidden';
        
        var userDetails = document.getElementsByClassName('user_ddress')[0]
        while (userDetails.hasChildNodes()) {
        	userDetails.removeChild(userDetails.firstChild)
        }
        let list = document.getElementById('list');
        str = "";
        var addressCity = document.getElementsByClassName('address-city')[0]
        var addressHeader = document.getElementsByClassName('address_header')[0]
        var addressheadTag = document.createElement('div')
        var addressheaderContents = `
            <h2 class="section-header" >ADDRESSES</h2>`
        	addressheadTag.innerHTML = addressheaderContents
        	addressHeader.append(addressheadTag)
        for (key in obj)
        { 
        	var addressRow = document.createElement('div')
            addressRow.classList.add('address-row')
            // str += `<li>${obj[key].id} </li>`;
            console.log("obj in response afte json parse "+obj[key].address);
            var addressRowContents = `
            <div class="cart-column order_address">
                <div class="cart-item cart-column">
                    <span class="address-city address-header address-column">CITY</span>
                    <span class="cart-item-title">${obj[key].city}</span>
                </div>
                <span class="address-address address-header address-column">ADDRESS</span>
                <span class="cart-price cart-column">${obj[key].address}</span>
                 <span class="address-address address-header address-column">ADDRESSID</span>
                 <span class="cart-price cart-column change-address-id" name="changeAddrId">${obj[key].addressId}</span>
                <div class="cart-quantity cart-column">
                    <button class="btn btn-primary btn-change-address" type="button">Use This Address</button>
                </div>
                </div>`
            addressRow.innerHTML = addressRowContents
            addressCity.append(addressRow)
        }
        // document.getElementsByClassName('btn-address')[0].style.visibility =
		// 'hidden';
        // list.innerHTML = str;
        var changeAddressButtons = document.getElementsByClassName('btn-change-address')
        for (var i = 0; i < changeAddressButtons.length; i++) {
            var button = changeAddressButtons[i]
            button.addEventListener('click', changeAddress)
        }
    }
    else{
        console.log("Some error occured")
    }
}

// send the request
xhr.send();
console.log("We are done fetching employees!");
	
}

function purchaseClicked() {
	var mobileNo = document.getElementsByName('mobileNo')[0].value
	console.log("mobile No "+mobileNo)
    var cartItems = document.getElementsByClassName('cart-items')[0]
    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    var cartItemsQuantity= cartItems.getElementsByClassName('cart-quantity-input')
    
     var cartRow = document.createElement('div')
     var cartlength=cartItemNames.length
     if(cartlength>0)
    	 {
    	 
    	 	if(null!=mobileNo&&mobileNo==="NotFound")
    	 		{
    		let items = [];
    	    if(typeof(Storage) !== 'undefined'){
    			
    				
    			if(JSON.parse(localStorage.getItem('items')) === null){
    				
    				
    			}else{
    				const localItems = JSON.parse(localStorage.getItem("items"));
    				localItems.map(data=>{
    					if(productName == data.name){
    						// item.no = data.no + 1;
    						// removing ites from local storage
    					}else{
    						items.push(data);
    					}
    				});
    				
    				localStorage.setItem('items',JSON.stringify(items));
    				
    			}
    	    }
    	    else
    	    	{
    	    	alert('local storage is not working on your browser');
    	    	}
    	 }
    	 	else
    	 		{
    	 		localStorage.clear();
    	 		}
    	 	
    		
     var cartRowContents = `
            
             <input name="cartlength" type="text" value="${cartlength}" hidden>
            `
    	 cartRow.innerHTML = cartRowContents
    	 cartItems.append(cartRow)
    
    
    
    for (var i = 0; i < cartItemNames.length; i++) {
        var proname=cartItemNames[i].innerText
        var cartRow = document.createElement('div')
        var prodTagName="proname"+[i]
        
 
        var cartRowContents = `
            
            <input name="${prodTagName}" type="text" value="${proname}" hidden>
            `
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    }
    
    for (var i = 0; i < cartItemsQuantity.length; i++) {
    	 var quantity=cartItemsQuantity[i].value
         var cartRow = document.createElement('div')
         var prodTagQuantity="proQuant"+[i]
    	 var cartRowContents = `
             
             <input name="${prodTagQuantity}" type="text" value="${quantity}" hidden>
             `
     cartRow.innerHTML = cartRowContents
     cartItems.append(cartRow)
    }
    alert('Thank you for your purchase')
    
    	 }
     else
    	 {
    	 
    	 alert('Please add cart items to purchase')
    	 
    	 }
    
   
    
    
}


function removeCartItem(event) {
    var buttonClicked = event.target
    buttonClicked.parentElement.parentElement.remove()
    // buttonClicked.parentElement.parentElement.childNodes[0].nodeValue
    var doc = buttonClicked.parentElement.parentElement;
var productName = null;
for (var i = 0; i < doc.childNodes.length; i++) {
	console.log("class name "+doc.childNodes[i].className)
    if (doc.childNodes[i].className == "cart-item cart-column") {
    	
      // notes = doc.childNodes[i];
    	console.log("class name "+doc.childNodes[i].getElementsByClassName('cart-item-title')[0].innerText)
    	productName=doc.childNodes[i].getElementsByClassName('cart-item-title')[0].innerText;
    	
      break;
    }
	
	
}
var mobileNo = document.getElementsByName('mobileNo')[0].value
console.log("mobile No "+mobileNo)

// Instantiate an xhr object

if(mobileNo!=null&&mobileNo!=="NotFound")
{
const xhr = new XMLHttpRequest();
xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/RemoveCartServlet?productName='+productName+'&mobileNo='+mobileNo, true);
// What to do when response is ready
xhr.onload = function () {
    if(this.status === 200){
    	window.location.reload();  
    }
    else{
        console.log("Some error occured")
    }
}

// send the request
xhr.send();
    updateCartTotal()
    const iconShoppingP = document.querySelector('.iconShopping p');

    var countcart=iconShoppingP.innerHTML
    updateCartQuantityFromDb(countcart-1)
    
}
else
	{
	let items = [];
    if(typeof(Storage) !== 'undefined'){
		
			
		if(JSON.parse(localStorage.getItem('items')) === null){
			
			
		}else{
			const localItems = JSON.parse(localStorage.getItem("items"));
			localItems.map(data=>{
				if(productName == data.name){
					// item.no = data.no + 1;
					// removing ites from local storage
					console.log("removed cart iems from cart "+productName);
				}else{
					items.push(data);
				}
			});
			
			localStorage.setItem('items',JSON.stringify(items));
			
		}
    }
    else
    	{
    	alert('local storage is not working on your browser');
    	}
	}
updateCartQuantity()
//updateCartTotalInCartIcon()

}

function quantityChanged(event) {
	console.log("inside Quantity change event ")
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1
    }
    var mobileNo = document.getElementsByName('mobileNo')[0].value
    console.log("mobile No "+mobileNo)
 
    // buttonClicked.parentElement.parentElement.childNodes[0].nodeValue
    var doc = input.parentElement.parentElement;
var productName = null;
for (var i = 0; i < doc.childNodes.length; i++) {
	console.log("class name "+doc.childNodes[i].className)
    if (doc.childNodes[i].className == "cart-item cart-column") {
    	
      // notes = doc.childNodes[i];
    	console.log("class name "+doc.childNodes[i].getElementsByClassName('cart-item-title')[0].innerText)
    	productName=doc.childNodes[i].getElementsByClassName('cart-item-title')[0].innerText;
    	
      break;
    }	
}

if(mobileNo!=null&&mobileNo==="NotFound")
{console.log("Inside Mobile no not found")
	let items = [];
    if(typeof(Storage) !== 'undefined'){
		
			
		if(JSON.parse(localStorage.getItem('items')) === null){
			
			
		}else{
			const localItems = JSON.parse(localStorage.getItem("items"));
			localItems.map(data=>{
				console.log("Item names in local storage  "+data.name+" product Name "+productName)
				if(productName === data.name){
					// item.no = data.no + 1;
					//Adding quantity 
					data.quantity=input.value;
					console.log("quantity changed "+data.quantity)
					items.push(data);
				}else{
					items.push(data);
				}
			});
			
			localStorage.setItem('items',JSON.stringify(items));
			
		}
    }
    else
    	{
    	alert('local storage is not working on your browser');
    	}
}
    updateCartTotal()
}

function addToCartClicked(event) {
    var button = event.target
    var shopItem = button.parentElement.parentElement
    var title = shopItem.getElementsByClassName('shop-item-title')[0].innerText
    var price = shopItem.getElementsByClassName('shop-item-price')[0].innerText
    var imageSrc = shopItem.getElementsByClassName('shop-item-image')[0].src
    addItemToCart(title, price, imageSrc)
    updateCartTotal()
}

function addItemToCart(title, price, imageSrc) {
    var cartRow = document.createElement('div')
    cartRow.classList.add('cart-row')
    var cartItems = document.getElementsByClassName('cart-items')[0]
    var cartItemNames = cartItems.getElementsByClassName('cart-item-title')
    var cartCount=cartItemNames.length;
    var updateCartIconFlag=true;
    for (var i = 0; i < cartItemNames.length; i++) {
        if (cartItemNames[i].innerText == title) {
            alert('This item is already added to the cart')
            updateCartIconFlag=false;
            return
        }
    }
    var mobileNo = document.getElementsByName('mobileNo')[0].value
    console.log("mobile No "+mobileNo)
    
    var cartRowContents = `
        <div class="cart-item cart-column">
            <img class="cart-item-image" src="${imageSrc}" width="100" height="100">
            <span class="cart-item-title">${title}</span>
        </div>
        <span class="cart-price cart-column">${price}</span>
        <div class="cart-quantity cart-column">
            <input class="cart-quantity-input" type="number" value="1">
            <button class="btn btn-danger" type="button">REMOVE</button>
        </div>`
    cartRow.innerHTML = cartRowContents
    cartItems.append(cartRow)
    cartRow.getElementsByClassName('btn-danger')[0].addEventListener('click', removeCartItem)
    cartRow.getElementsByClassName('cart-quantity-input')[0].addEventListener('change', quantityChanged)
    var cartHtml=cartRow.innerHTML ;
    //getCardDataInCartIcon(cartHtml)
    let items = [];
    if(mobileNo!=null&&mobileNo!=="NotFound")
    {
    	
    	// Instantiate an xhr object
        const xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://35.194.29.49:8080/Bodyshop/AddToCartServlet?prodName='+title+'&image='+imageSrc+'&quantity=1&price='+price, true);
    	// What to do when response is ready
        xhr.onload = function () {
            if(this.status === 200){
            	// window.location.href='http://35.194.29.49:8080/Bodyshop/productDelivery.jsp';
            	console.log("Cart item added in db")
            	updateCartQuantityFromDb(cartCount+1);
            }
            else{
                console.log("Some error occured")
            } 
        }

        // send the request
        xhr.send();
    	
    }
    else
    	{

        if(typeof(Storage) !== 'undefined'){
    		let item = {
    				name:title,
    				price:price,
    				quantity:1,
    				imageSrc:imageSrc
    			};
    			console.log("item oject in local storage "+item);
    		if(JSON.parse(localStorage.getItem('items')) === null){
    			items.push(item);
    			localStorage.setItem("items",JSON.stringify(items));
    			
    		}else{
    			const localItems = JSON.parse(localStorage.getItem("items"));
    			localItems.map(data=>{
    				if(item.name == data.name){
    					// item.no = data.no + 1;
    				}else{
    					items.push(data);
    				}
    			});
    			items.push(item);
    			localStorage.setItem('items',JSON.stringify(items));
    			
    		}
    	}else{
    		alert('local storage is not working on your browser');
    	}
        
        updateCartQuantity();
    	}   
}

function updateCartTotal() {
	if(null!=document.getElementsByClassName('cart-items')[0]&&null!=document.getElementsByClassName('cart-row'))
		{
    var cartItemContainer = document.getElementsByClassName('cart-items')[0]
    if(null!=cartItemContainer.getElementsByClassName('cart-row'))
    	{
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('Rs.', ''))
        var quantity = quantityElement.value
        total = total + (price * quantity)
    }
    total = Math.round(total * 100) / 100
    document.getElementsByClassName('cart-total-price')[0].innerText = 'Rs.' + total
    	}
		}
	
}

function updateCartTotalInCartIcon() {
	if(null!=document.getElementsByClassName('cart-items')[1]&&null!=document.getElementsByClassName('cart-row'))
		{
    var cartItemContainer = document.getElementsByClassName('cart-items')[1]
    if(null!=cartItemContainer.getElementsByClassName('cart-row'))
    	{
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('cart-quantity-input')[0]
        var price = parseFloat(priceElement.innerText.replace('Rs.', ''))
        var quantity = quantityElement.value
        total = total + (price * quantity)
    }
    total = Math.round(total * 100) / 100
    document.getElementsByClassName('cart-total-price')[1].innerText = 'Rs.' + total
    	}
		}
	
}

// adding data to shopping cart
function updateCartQuantity()
{
const iconShoppingP = document.querySelector('.iconShopping p');
let no = 0;
if(typeof(Storage) !== 'undefined'){
	if(localStorage.getItem('items')!=null){
JSON.parse(localStorage.getItem('items')).map(data=>{
	no= no+1;
	});
iconShoppingP.innerHTML = no;
	}
}
}

// adding data to shopping cart
function updateCartQuantityFromDb(no)
{
const iconShoppingP = document.querySelector('.iconShopping p');

iconShoppingP.innerHTML = no;
	

}
