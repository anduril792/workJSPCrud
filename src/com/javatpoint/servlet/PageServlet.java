package com.javatpoint.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javatpoint.bean.PageBean;
import com.javatpoint.bean.Product;
import com.javatpoint.service.ProductService;

public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�ɯ���s���d�߱���
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		//��l�ƨC����ܪ��O����
		int pageSize = 6;
		
		int currentPage = 1;//��e��
		String currPage = request.getParameter("currentPage");//�q�W�@���ΤU�@���o�쪺�ƾ�
		if(currPage!=null&&!"".equals(currPage)){//�Ĥ@���X�ݸ귽�ɡAcurrPage�i��Onull
			currentPage = Integer.parseInt(currPage);
		}
		
		ProductService bs = new ProductService();
		//�����d�ߡA�}��^PageBean��H
		PageBean pb = bs.findBooksPage(currentPage,pageSize,category);
		List<Product> list =bs.findAllBooks();
		
		/*if(list!=null){
		request.setAttribute("products", list);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response); 
		}*/
		request.setAttribute("pb", pb);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}