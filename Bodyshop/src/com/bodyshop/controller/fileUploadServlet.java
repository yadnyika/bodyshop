package com.bodyshop.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;

import com.bodyshop.dao.BodyShopDao;
import com.bodyshop.pojo.Product;



/**
 * Servlet implementation class fileUploadServlet
 */
@WebServlet("/fileUploadServlet")
public class fileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(fileUploadServlet.class);
	private final String UPLOAD_DIRECTORY = "/var/Bodyshop/images";  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public fileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
    		
			String productName="";
			String productPrice="";
			String productImage="";
			boolean imageFormatflag=false;
			BodyShopDao dao=new BodyShopDao();            
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                                         new DiskFileItemFactory()).parseRequest(request);
               
                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                    	 
                          String contentType=item.getContentType();
                          logger.info("content type"+contentType);
                          if(contentType.equals("image/png")||contentType.equals("image/jpg")||contentType.equals("image/jpeg"))
                          {
                        	  String name = new File(item.getName()).getName();
                              item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                              productImage=name;
                        	  
                          }
                          else
                          {
                        	  request.setAttribute("message", "please upload only jpg or png files");  
                        	  imageFormatflag=true;
                          }
                        
                        
                       
                    }
                    else
                    {
                    	String fieldname = item.getFieldName();
                        String fieldvalue = item.getString();
                        if(fieldname.equalsIgnoreCase("productName"))
                        {
                        	productName=fieldvalue;
                        }
                         if(fieldname.equalsIgnoreCase("productPrice"))
                        {
                        	productPrice=fieldvalue;
                        }	
                    }
                }
                productImage="/project/images"+ "/" +productImage;
               //File uploaded successfully
               logger.info("productName "+productName);
                Product product=new Product();
                product.setProductName(productName);
                product.setPrice(Integer.parseInt(productPrice));
                product.setImage(productImage);
                if(!imageFormatflag) {
					if(dao.insertProduct(product))
					{
            request.setAttribute("message", "File Uploaded Successfully");
					}
					else
					{
						request.setAttribute("message", "File Uploaded Successfully but not updated in database");
					}
				}
            } catch (Exception ex) {
               request.setAttribute("message", "File Upload Failed due to " + ex);
               ex.printStackTrace();
            }          
          
        }else{
            request.setAttribute("message",
                                 "Sorry this Servlet only handles file upload request");
        }
     
        request.getRequestDispatcher("/ProductAddedSuccess.jsp").forward(request, response);
      
    
	}

}
