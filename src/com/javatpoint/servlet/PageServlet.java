package com.javatpoint.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.javatpoint.bean.Article;
import com.javatpoint.dao.ArticleDao;

public class PageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//�ɯ���s���d�߱���
		String category = request.getParameter("category");
		if(category==null){
			category="";
		}
		//��l�ƨC����ܪ��O����
		int pagesize = 4;
		
		int page = 1;//��e��
		String currPage = request.getParameter("page");//�q�W�@���ΤU�@���o�쪺�ƾ�
		if(currPage!=null&&!"".equals(currPage)){//�Ĥ@���X�ݸ귽�ɡAcurrPage�i��Onull
			page = Integer.parseInt(currPage);
		}
		
		ArticleDao ad = new ArticleDao();
		List<Article> list = ad.getAllArticleByPage(page, pagesize);
		
		/*if(list!=null){
		request.setAttribute("products", list);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(request, response); 
		}*/
		request.setAttribute("list", list);
		request.getRequestDispatcher("/product_list.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}