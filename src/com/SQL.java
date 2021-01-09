package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL {
	String uri = "jdbc:mysql://localhost:3306/acc?useSSL=true";//数据库连接地址
	String user = "root"; //数据库登录名
	String password = "12345"; //数据库登录密码
	String sql;//数据库操作语句
	PreparedStatement ps = null;//把查询，删除，更新等操作给ps
	Connection cnn = null;//连接
	ResultSet rs;
	public void DriveLoad(){ //加载驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("加载驱动成功！");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}
	}
	public void ConnectionCreate() {//建立连接
		try {
			cnn = DriverManager.getConnection(uri, user, password);
			System.out.println("-----数据库连接成功，输出连接数据库元数据------");
			System.out.println("用户名："+cnn.getMetaData().getUserName());
			System.out.println("数据库名："+cnn.getMetaData().getDatabaseProductName());
			System.out.println("数据库版本号："+cnn.getMetaData().getDatabaseProductVersion());
			System.out.println("数据库驱动程序名："+cnn.getMetaData().getDriverName());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("连接数据库失败！");
		}
	}
	 public static void main(String[] args) {
			SQL jdb=new SQL();
			jdb.DriveLoad();
			jdb.ConnectionCreate();
		}
}
