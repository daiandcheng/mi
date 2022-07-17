package com.edu.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.edu.bean.Order;
import com.edu.service.OrderService;
import com.edu.service.impl.OrderServiceImpl;

/**
 * Servlet implementation class PayServlet
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method") ;
		if("pay".equals(method)) {
			// 支付
			pay(request,response) ;
		} else if("payment".equals(method)) {
			payment(request,response);
		}
	}
	/**
	 * 这是付款的流程
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void payment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter() ;
		try {
			//获得初始化的AlipayClient
			AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			
			//设置请求参数
			AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
			alipayRequest.setReturnUrl(AlipayConfig.return_url);
			alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
			
			//商户订单号，商户网站订单系统中唯一订单号，必填
			String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			//付款金额，必填
			String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
			//订单名称，必填
			String subject = request.getParameter("WIDsubject");
			//商品描述，可空
			String body = request.getParameter("WIDbody");
			
			alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
					+ "\"total_amount\":\""+ total_amount +"\"," 
					+ "\"subject\":\""+ subject +"\"," 
					+ "\"body\":\""+ body +"\"," 
					+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			
			//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
			//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
			//		+ "\"total_amount\":\""+ total_amount +"\"," 
			//		+ "\"subject\":\""+ subject +"\"," 
			//		+ "\"body\":\""+ body +"\"," 
			//		+ "\"timeout_express\":\"10m\"," 
			//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
			//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
			
			//请求
			String result = alipayClient.pageExecute(alipayRequest).getBody();
			
			//输出
			out.println(result);
			out.flush();
			out.close();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 这是支付的方法
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void pay(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
			String oid = request.getParameter("oid") ;
			String total = request.getParameter("total") ;
			String name = request.getParameter("name") ;
			String address = request.getParameter("address") ;
			String phone = request.getParameter("phone") ;
			String unpay = request.getParameter("unpay") ;
			Order order = new Order();
			if(null != unpay && unpay.trim().length()>0) {
				order.setId(oid);
				order.setTotal(Double.parseDouble(total));
			} else {
				order.setId(oid);
				order.setName(name);
				order.setAddress(address);
				order.setPhone(phone);
				order.setTotal(Double.parseDouble(total));
				// 更新订单
				OrderService orderService = new OrderServiceImpl() ;
				orderService.update(order) ;
			}
			
		
			request.setAttribute("order", order);
			request.getRequestDispatcher("/WEB-INF/jsp/pay.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
