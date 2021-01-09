package com;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class history_data {
	Object[][] data;
	public void history_date(String ID) {
		SQL sql=new SQL();
		PreparedStatement ps = null;
		ResultSet rs;
		sql.DriveLoad();
		sql.ConnectionCreate();
		String account="select * from data where ID="+"'"+ID+"'";
		try {
			ps=sql.cnn.prepareStatement(account);
			rs=ps.executeQuery();
			rs.last();
			int line = rs.getRow();
			data = new Object[line][5];
			rs.beforeFirst();
			rs.last();
			for(int i=0;i<line;i++) {
				data[i][0] = rs.getString(1);
				data[i][1] = rs.getString(2);
				data[i][2] = rs.getString(3);
				data[i][3] = rs.getString(4);
				data[i][4] = rs.getString(5);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			data = null;
		}
		
	}
}
