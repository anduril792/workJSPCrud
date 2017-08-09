package com.javatpoint.util;

import java.sql.Connection;
import java.sql.SQLException;

public class ManagerThreadLocal {
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	
	//�o��@�ӳs��
	public static Connection getConnection(){
		Connection conn = tl.get();//�q��e�u�{�����X�@�ӳs��
		if(conn==null){
			conn = C3P0Util.getConnection();//�q�������X�@��
			tl.set(conn);//��conn��H��J���e�u�{��H��
		}
		return conn;
	}
	
	//�}�l�ư�
	public static void startTransacation(){
		try {
			Connection conn = getConnection();
			conn.setAutoCommit(false);//�q��e�u�{��H�����X���s���A�}�}�l�ư�
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void commit(){
		try {
			getConnection().commit();//����ư�
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void rollback(){
		try {
			getConnection().rollback();//�^�u�ư�
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){
		try {
			getConnection().close();//��s����^����
			tl.remove();//���e�u�{��H����conn����
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}