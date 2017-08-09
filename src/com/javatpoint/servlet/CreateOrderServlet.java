package com.javatpoint.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.javatpoint.bean.Order;
import com.javatpoint.bean.OrderItem;
import com.javatpoint.bean.Product;
import com.javatpoint.bean.User;
import com.javatpoint.service.OrderService;

public class CreateOrderServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Order order = new Order();
		try {
			BeanUtils.populate(order, request.getParameterMap());
			order.setId(UUID.randomUUID().toString());
			order.setUserId(Integer.valueOf(request.getParameter("id")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		//2�B���session��H�����ʪ����ƾ�
		Map<Product, String> cart = (Map<Product, String>) request.getSession().getAttribute("cart");
		
		//3�B�M���ʪ��������ƾڡA�K�[��orderItem��H���A�P�ɧ�h��orderItem�K�[��list���X��
		List<OrderItem> list = new ArrayList<OrderItem>();
		for (Product p : cart.keySet()) {
			OrderItem oi = new OrderItem();
			oi.setOrder(order);//��Order��H�K�[��OrderItem��
			oi.setP(p);   //���ʪ��������ӫ~��H�K�[��OrderItem��
			oi.setBuynum(Integer.parseInt(cart.get(p)));//�ʪ��������ӫ~�ƶq
			
			list.add(oi);//��C�өw�涵�K�[�춰�X��
		}
		
		order.setOrderItems(list);
		
		OrderService os = new OrderService();
		os.addOrder(order);
		
		//
		request.getSession().invalidate();
		request.getRequestDispatcher("/paysuccess.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}