package com.edu.view;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.edu.bean.Brand;
import com.edu.bean.Category;
import com.edu.bean.PageBean;
import com.edu.bean.Product;
import com.edu.contant.Constant;
import com.edu.service.BrandService;
import com.edu.service.CategoryService;
import com.edu.service.ProductService;
import com.edu.service.impl.BrandServiceImpl;
import com.edu.service.impl.CategoryServiceImpl;
import com.edu.service.impl.ProductServiceImpl;
import com.edu.util.UUIDUtil;

/**
 * Servlet implementation class ProductManagerServlet
 */
public class ProductManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("list".equals(method)) {
			list(request,response);
		} else if("addUI".equals(method)) {
			addUI(request,response);
		} else if("add".equals(method)) {
			add(request,response);
		}
	}
	
	private void add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		Map<String,Object> map = new HashMap<String,Object>() ;
		try {
			if(isMultipart) {
				// 创建一个磁盘文件的工厂
				DiskFileItemFactory factory = new DiskFileItemFactory();

				// 你内存中保存的文件最大是20m 超过了20m把文件保存到临时目录下面
				factory.setSizeThreshold(1024*1024*20);
				// 设置你的临时目录
				factory.setRepository(new File(request.getContextPath()+"/"));

				// 创建一个文件上传的处理器
				ServletFileUpload upload = new ServletFileUpload(factory);

				// 设置你能够上传文件的最大值
				upload.setSizeMax(1024*1024*200);

				// 开始解析请求
				List<FileItem> items = upload.parseRequest(request);
				
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
				    FileItem item = iter.next();
				    // 普通的文件项目
				    if (item.isFormField()) {
				    	 String name = item.getFieldName();
				    	 // 因为我们使用的是commons-file-upload组件，它对我们HttpRequest进行了封装
				    	 // 封装的时候，它是按照iso-8859-1的方式，所以我们要对它进行重新编码
				    	 // 拿着之间的字节码文件，然后重新按照utf-8的方式来进行编码
				    	 String value = new String(item.getString().getBytes("ISO-8859-1"),"UTF-8");
				    	 map.put(name, value);
				    } else {
				    	// 这是上传的文件
			    	    String fileName = item.getName();// 这是上传的图片的名字
			    	    fileName = UUIDUtil.getCode()+fileName.substring(fileName.lastIndexOf(".")) ;
			    	    InputStream input = new BufferedInputStream(item.getInputStream());
			    	    // 我们可以使用日期划分文件夹
			    	    SimpleDateFormat formater = new SimpleDateFormat("/yyyy/MM/dd/");
			    	    String path = formater.format(new Date());
			    	    File dir = new File("E:/pic"+path);
			    	    map.put("pic", "/pic"+path+fileName);
			    	    if(!dir.exists()) dir.mkdirs() ;
			    	    File file = new File(dir,fileName);
			    	    OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
			    	    byte[] buffer = new byte[1024];
			    	    int len = -1 ;
			    	    while((len = input.read(buffer)) !=-1) {
			    	    	out.write(buffer, 0, len);
			    	    }
			    	    out.flush();
			    	    out.close();
			    	    input.close();
				    }
				}
				// 封装数据
				Product product = new Product();
				BeanUtils.copyProperties(product, map);
				// 补全数据
				product.setId(UUIDUtil.getId());
				product.setProduct_up_date("2022-3-25");
				// 调用service来进行数据的保存
				ProductService productService = new ProductServiceImpl() ;
				productService.save(product);
				// 重定向到显示列表页面
				response.sendRedirect(request.getContextPath()+"/manager/product?method=list");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 跳转到新增页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void addUI(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			// 1. 准备分类的数据
			CategoryService categoryService = new CategoryServiceImpl() ;
			List<Category> categoryList = categoryService.getBigCategory() ;
			// 2. 准备品牌的数据
			BrandService brandService = new BrandServiceImpl();
			List<Brand> brandList = brandService.getAll();
			request.setAttribute("categoryList", categoryList);
			request.setAttribute("brandList", brandList);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/product/addUI.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 分页显示商品的列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void list(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int pageNo = 1 ;
		try {
			pageNo = Integer.valueOf(request.getParameter("pageNo")) ;
		} catch(Exception e) {
		}
		try {
			// 准备数据
			ProductService productService = new ProductServiceImpl() ;
			PageBean pageBean = productService.getPageBean(pageNo,Constant.PAGESIZE);
			request.setAttribute("pageBean", pageBean);
			request.getRequestDispatcher("/WEB-INF/jsp/manager/product/list.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

}
