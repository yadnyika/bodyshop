package com.bodyshop.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.bodyshop.pojo.AddressDetails;
import com.bodyshop.pojo.Cart;
import com.bodyshop.pojo.Order;
import com.bodyshop.pojo.Product;
import com.bodyshop.pojo.ProductDetails;
import com.bodyshop.pojo.RegisterPOJO;
import com.bodyshop.service.Utility;
import com.google.gson.Gson;

public class BodyShopDao {
	static Logger logger = Logger.getLogger(BodyShopDao.class);  
	public boolean register(RegisterPOJO register) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.persist(register);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in insert..........." , e);
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public RegisterPOJO getLoginDetails(RegisterPOJO pojo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<RegisterPOJO> rgisterPojoList = null;
		RegisterPOJO rPojo = null;

		try {
			Query q = em.createQuery("select r from RegisterPOJO r where mobileNo=:mobileNo and password=:password"); // JPQL
			q.setParameter("mobileNo", pojo.getMobileNo());
			q.setParameter("password", pojo.getPassword());
			rgisterPojoList = q.getResultList();
			if (null != rgisterPojoList && rgisterPojoList.size() > 0) {
				rPojo = rgisterPojoList.get(0);
			}
		} catch (Exception e) {
			logger.error("exception in login" , e);
		} finally {
			em.close();
			emf.close();
		}

		return rPojo;
	}
	
	public RegisterPOJO getUserDetailsByMobileNo(String mobileNo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<RegisterPOJO> rgisterPojoList = null;
		RegisterPOJO rPojo = null;

		try {
			Query q = em.createQuery("select r from RegisterPOJO r where mobileNo=:mobileNo"); // JPQL
			q.setParameter("mobileNo",mobileNo);
			//q.setParameter("password", pojo.getPassword());
			rgisterPojoList = q.getResultList();
			if (null != rgisterPojoList && rgisterPojoList.size() > 0) {
				rPojo = rgisterPojoList.get(0);
			}
		} catch (Exception e) {
			logger.error("exception in login",e);
		} finally {
			em.close();
			emf.close();
		}

		return rPojo;
	}

	public RegisterPOJO fetchUserDetails(String mobileNo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<RegisterPOJO> rgisterPojoList = null;
		RegisterPOJO rPojo = null;

		try {
			Query q = em.createQuery("select r from RegisterPOJO r where mobileNo=:mobileNo"); // JPQL
			q.setParameter("mobileNo", mobileNo);
			// q.setParameter("password", pojo.getPassword());
			rgisterPojoList = q.getResultList();
			if (null != rgisterPojoList && rgisterPojoList.size() > 0) {
				rPojo = rgisterPojoList.get(0);
			}
		} catch (Exception e) {
			logger.error("exception in login" , e);
			}

		return rPojo;
	}

	public void update(RegisterPOJO pojo) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(pojo);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in update" , e);
			e.printStackTrace();

		} finally {
			em.close();
			emf.close();
		}

	}

	public boolean updateCart(Cart cart) {
		boolean updateFalg=false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(cart);
			tx.commit();
			updateFalg=true;
		} catch (Exception e) {
			logger.error("exception in updatecart" , e);
			e.printStackTrace();

		} finally {
			em.close();
			emf.close();
		}
		return updateFalg;
	}

	public boolean updateOrder(Order pojo) {
		boolean updateFlag = false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			em.merge(pojo);
			tx.commit();
			updateFlag = true;
		} catch (Exception e) {
			logger.error("exception in updateorder" , e);
			e.printStackTrace();
			updateFlag = false;

		} finally {
			em.close();
			emf.close();
		}

		return updateFlag;

	}

	public RegisterPOJO forgotPassword(RegisterPOJO pojo) {
		System.out.println("inside forgot password " + pojo.getAnswer() + " question " + pojo.getSecurityQuestion()
				+ " mobile No " + pojo.getMobileNo());
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<RegisterPOJO> rgisterPojoList = null;
		RegisterPOJO rPojo = null;

		try {
			Query q = em.createQuery(
					"select r from RegisterPOJO r where mobileNo=:mobileNo and securityQuestion=:securityQuestion and answer=:answer"); // JPQL
			q.setParameter("mobileNo", pojo.getMobileNo());
			q.setParameter("securityQuestion", pojo.getSecurityQuestion());
			q.setParameter("answer", pojo.getAnswer());
			rgisterPojoList = q.getResultList();
			if (null != rgisterPojoList && rgisterPojoList.size() > 0) {
				rPojo = rgisterPojoList.get(0);
			}
		} catch (Exception e) {
			logger.error("exception in registerPojo" , e);
		} finally {
			em.close();
			emf.close();
		}

		return rPojo;
	}

	public boolean insertProduct(Product pro) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.persist(pro);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in insertProduct" , e);
			e.printStackTrace();
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public boolean insertCart(Cart cart) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.persist(cart);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in insertCart" , e);
			e.printStackTrace();
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public boolean insertOrder(Order o) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.persist(o);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in insertOrder" , e);
			e.printStackTrace();
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public boolean insertProductDetails(ProductDetails productDetails) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.persist(productDetails);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in inserproductdetails" , e);
			e.printStackTrace();
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public boolean insertAddressDetails(AddressDetails addressDetail) {
		boolean registerStaus = true;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {

			EntityTransaction tx = em.getTransaction();
			tx.begin();

			em.merge(addressDetail);
			tx.commit();
		} catch (Exception e) {
			logger.error("exception in insertAddressDetails " , e);
			e.printStackTrace();
			registerStaus = false;
		} finally {
			em.close();
			emf.close();
		}

		return registerStaus;
		// TODO Auto-generated method stub

	}

	public List<Product> fetchAllProductsDetails() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Product> productsPojoList = null;
		Product rPojo = null;

		try {
			Query q = em.createQuery("select r from Product r"); // JPQL
			// q.setParameter("mobileNo", mobileNo);
			// q.setParameter("password", pojo.getPassword());
			productsPojoList = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchallproducts" , e);
		} finally {
			em.close();
			emf.close();
		}

		return productsPojoList;
	}

	public List<Order> fetchAllProductsDeliverery() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Order> orderPojoList = null;
		Product rPojo = null;

		try {
			Query q = em.createQuery("select r from Order r"); // JPQL
			// q.setParameter("mobileNo", mobileNo);
			// q.setParameter("password", pojo.getPassword());
			orderPojoList = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchallproductdelivery" , e);
		} finally {
			em.close();
			emf.close();
		}

		return orderPojoList;
	}

	public Product fetchProductById(int productid) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Product p = em.find(Product.class, productid);

		return p;

	}

	public List<Order> fetchSearchOrderDetails(String orderid) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Order> orderlist = null;

		try {
			Query q = em.createQuery("select r from Order r where orderId=:orderid"); // JPQL

			q.setParameter("orderid", orderid);
			orderlist = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchserachorderdetails" , e);
		} finally {
			em.close();
			emf.close();
		}

		return orderlist;

	}

	public boolean removeCart(Cart cart) {
		boolean removeFlag=false;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");

		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();

			Cart cart1 = em.find(Cart.class, cart.getId());

			em.remove(cart1);
			tx.commit();
			removeFlag=true;

		} catch (Exception e) {
			// TODO: handle exception
			logger.error("exception in removecart" , e);
			e.printStackTrace();
		}

		finally {
			em.close();
			emf.close();
		}
		
		return removeFlag;
	}

	public List<Cart> fetchCartByMobileNo(String mobileno, String cartstatus) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Cart> cartlist = null;

		try {
			Query q = em.createQuery("select r from Cart r where mobileNo=:mobileno and  cartStatus=:cartstatus"); // JPQL

			q.setParameter("mobileno", mobileno);
			q.setParameter("cartstatus", cartstatus);
			cartlist = q.getResultList();

		} catch (Exception e) {
			System.out.println("exception in fetchcartbymono" + e);
		} finally {
			em.close();
			emf.close();
		}

		return cartlist;

	}
	
	public List<Cart> fetchCartByCookieId(String cookieId, String cartstatus) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Cart> cartlist = null;

		try {
			Query q = em.createQuery("select r from Cart r where cookieId=:cookieId and  cartStatus=:cartstatus"); // JPQL

			q.setParameter("cookieId", cookieId);
			q.setParameter("cartstatus", cartstatus);
			cartlist = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchbycookieid" , e);
		} finally {
			em.close();
			emf.close();
		}

		return cartlist;

	}
	
	public Cart fetchCartByProductName(String mobileno, String productName) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Cart> cartlist = null;
		Cart cart=null;
		try {
			Query q = em.createQuery("select r from Cart r where mobileNo=:mobileno and  productName=:productName"); // JPQL

			q.setParameter("mobileno", mobileno);
			q.setParameter("productName", productName);
			cartlist = q.getResultList();
			if (null != cartlist && cartlist.size() > 0) {
				cart = cartlist.get(0);
			}
		} catch (Exception e) {
			logger.error("exception in fetchcartbyproname" , e);
		} finally {
			em.close();
			emf.close();
		}

		return cart;

	}
	public List<Order> fetchMyUserOrderDetails(String mobileno) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Order> orderlist = null;
		
		try {
			Query q = em.createQuery("select r from Order r where mobileNo=:mobileno"); // JPQL

			q.setParameter("mobileno", mobileno);
			
			orderlist = q.getResultList();
			
		} catch (Exception e) {
			logger.error("exception in fetchcartbyproname" , e);
		} finally {
			em.close();
			emf.close();
		}

		return orderlist;

	}

	
	

	public Product fetchProductDetails(String name) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Product> productlist = null;
		Product p = null;

		try {
			Query q = em.createQuery("select r from Product r where ProductName=:name"); // JPQL

			q.setParameter("name", name);
			productlist = q.getResultList();
			if (null != productlist && productlist.size() > 0) {
				p = productlist.get(0);
			}

		} catch (Exception e) {
			logger.error("exception in fetchprodetails" , e);} finally {
			em.close();
			emf.close();
		}

		return p;
	}

	public List<AddressDetails> fetchAddressDetails(String mobileNo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<AddressDetails> AddressDetails = null;
		Product p = null;

		try {
			Query q = em.createQuery("select r from AddressDetails r where mobileNo=:mobileNo"); // JPQL

			q.setParameter("mobileNo", mobileNo);
			AddressDetails = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchaddrdetails" , e);} finally {
			em.close();
			emf.close();
		}

		return AddressDetails;
	}

	public List<Product> fetchSearchProductDetails(String searchtext) {
		System.out.println("fetchSearchProductDetails " + searchtext);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Product> SearchDetails = null;
		Product p = null;

		try {
			Query q = em.createQuery("select r from Product r where ProductName like :searchtext"); // JPQL

			q.setParameter("searchtext", "%" + searchtext + "%");
			SearchDetails = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchserachprodetails" , e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return SearchDetails;
	}

	public AddressDetails fetchAddressDetailsByAddressId(String addressId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<AddressDetails> AddressDetails = null;
		AddressDetails addr = null;

		try {
			Query q = em.createQuery("select r from AddressDetails r where addressId=:addressId"); // JPQL

			q.setParameter("addressId", addressId);
			AddressDetails = q.getResultList();
			if (null != AddressDetails && AddressDetails.size() > 0) {
				addr = AddressDetails.get(0);
			}

		} catch (Exception e) {
			logger.error("exception in fetchadddetaibyadddrid" , e);
		} finally {
			em.close();
			emf.close();
		}

		return addr;
	}

	public List<ProductDetails> fetchProductDetailsByOrderId(String orderId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<ProductDetails> productDetails = null;

		try {
			Query q = em.createQuery("select r from ProductDetails r where orderId=:orderId"); // JPQL

			q.setParameter("orderId", orderId);
			productDetails = q.getResultList();

		} catch (Exception e) {
			logger.error("exception in fetchProductDetailsByOrderId" , e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return productDetails;
	}

	public Order fetchOrdersDetailsByOrderId(String orderId) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-demo");
		EntityManager em = emf.createEntityManager();
		List<Order> OrderDetails = null;
		Order odr = null;

		try {
			Query q = em.createQuery("select r from Order r where orderId=:orderId"); // JPQL

			q.setParameter("orderId", orderId);
			OrderDetails = q.getResultList();
			if (null != OrderDetails && OrderDetails.size() > 0) {
				odr = OrderDetails.get(0);
			}

		} catch (Exception e) {
			System.out.println("exception in login" + e);
		} finally {
			em.close();
			emf.close();
		}

		return odr;
	}

	public static void main(String[] args) {
		BodyShopDao dao = new BodyShopDao();
	 
//System.out.println("insert into product "+dao.insertProduct(p));
		List<AddressDetails> addressDetails=dao.fetchAddressDetails("08329719275");
		String jsonResponseString = new Gson().toJson(addressDetails);
		System.out.println("json String "+jsonResponseString);
		logger.info("json string"+jsonResponseString);
		//PropertiesConfigurator is used to configure logger from properties file
       PropertyConfigurator.configure("src/log4j.xml");
 
        //Log in console in and log file
        logger.debug("Log4j appender configuration is successful !!");
		/*List<AddressDetails> addressDetails2=new Gson().fromJson(jsonResponseString, ArrayList.class);
		System.out.println("Address Details 2 after parse "+addressDetails2);*/
		// System.out.println("gsd
		// :"+Utlity.encodeImage("C:/Users/lenovo/Downloads/Bodyshop/Bodyshop/WebContent/images/download
		// (1).jpg"));
	}
}
