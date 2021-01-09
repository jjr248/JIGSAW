package com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Sava_data {
	
	public Sava_data(String ID,String map,int difficulty,int time,int count) {
		SQL sql=new SQL();
		PreparedStatement ps = null;
		ResultSet rs;
		sql.DriveLoad();
		sql.ConnectionCreate();
		try {
			String insert= "insert into data values(?,?,?,?,?)";//SQL语句
			ps = sql.cnn.prepareStatement(insert);//预处理
			ps.setString(1, ID);
			ps.setString(2, map);
			ps.setInt(3, difficulty);
			ps.setInt(4, time);
			ps.setInt(5, count);
    		ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
			System.out.println("保存数据失败！");
		}
	}
}
