package com.javatpoint.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.javatpoint.bean.Order;
import com.javatpoint.bean.OrderItem;
import com.javatpoint.bean.Product;
import com.javatpoint.util.C3P0Util;
import com.javatpoint.util.ManagerThreadLocal;

public class OrderDao {
	// �K�[�w��
	public void addOrder(Order order) throws SQLException {
		QueryRunner qr = new QueryRunner();
		qr.update(ManagerThreadLocal.getConnection(),
				"INSERT INTO orders VALUES(?,?,?,?,?,?,?,?)", order.getId(),
				order.getMoney(), order.getReceiverAddress(), order
						.getReceiverName(), order.getReceiverPhone(), order
						.getPaystate(), order.getOrdertime(), order.getUser()
						.getId());
	}

	// �ھڥΤ�id�d�ߩҦ��w��
	public List<Order> findOrders(int id) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from orders where user_id=?",
				new BeanListHandler<Order>(Order.class), id);
	}

	//�d�߫��w�Τ᪺�w��H��
	public Order findOrdersByOrderId(String orderid) throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		//�o��@�өw��
		Order order = qr.query("select * from orders where id=?", new BeanHandler<Order>(Order.class),orderid);
		//�o���e�w�椤���Ҧ��w�涵
		//List<OrderItem> orderItems = qr.query("select * from orderItem where order_id=? ", new  BeanListHandler<OrderItem>(OrderItem.class),orderid);
		//�o��Ҧ��w�涵�����ӫ~�H��
		//List<Product> products = qr.query("select * from products where id in(select product_id from orderitem where order_id=?)", new BeanListHandler<Product>(Product.class),orderid);
		
		List<OrderItem>  orderItems = qr.query("SELECT * FROM orderitem o,products p WHERE p.id=o.product_id AND order_id=?", new ResultSetHandler<List<OrderItem>>(){

			public List<OrderItem> handle(ResultSet rs) throws SQLException {
				List<OrderItem> orderItems = new ArrayList<OrderItem>();
				while(rs.next()){
					
					//�ʸ�OrderItem��H
					OrderItem oi = new OrderItem();
					oi.setBuynum(rs.getInt("buynum"));
					//�ʸ�Product��H
					Product p = new Product();
					p.setName(rs.getString("name"));
					p.setPrice(rs.getDouble("price"));
					//��C��p��H�ʸ˨�OrderItem��H��
					oi.setP(p);
					//��C��OrderItem��H�ʸ˨춰�X��
					orderItems.add(oi);
				}
				return orderItems;
			}
			
		},orderid);
		
		//��Ҧ����w�涵orderItems�K�[��D���HOrder��
		order.setOrderItems(orderItems);
		
		return order;
	}

	public List<Order> findAllOrders() throws SQLException {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from orders", new BeanListHandler<Order>(Order.class));
		
	}
}