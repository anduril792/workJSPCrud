package com.javatpoint.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javatpoint.bean.Article;
import com.javatpoint.dao.ArticleDao;

public class AddCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int idArticle = Integer.valueOf(request.getParameter("idArticle"));
		
		ArticleDao ad = new ArticleDao();
		
		Article a = ad.getArticleById(idArticle);
		
		//�qsession�����ʪ������X ��
		HttpSession session = request.getSession();
		Map<Article, String> cart = (Map<Article, String>) session.getAttribute("cart");
		int num = 1;
		//�p��O�Ĥ@���X�ݡA�S���ʪ�����H�A�ڭ̴N�Ы� �@���ʪ�����H
		if(cart==null){
			cart = new HashMap<Article, String>();		
		}
		//�d�ݷ�e���X���O�_�s�b�o����,�p�G���N��ƾڨ��X�ӥ[1;
		if(cart.containsKey(a)){
			num=Integer.parseInt(cart.get(a))+1;
		}
		//��Ϯѩ�J�ʪ���
		cart.put(a, num+"");
		
		//��cart��H��^��session�@�ΰ줤
		session.setAttribute("cart", cart);
		
		out.print("<a href='"+request.getContextPath()+"/pageServlet'>�~���ʪ�</a>�A<a href='"+request.getContextPath()+"/cart.jsp'>�d���ʪ���</a>");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}