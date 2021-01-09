package com;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	//成员变量
		private JFrame frame=new JFrame("拼图");
		private JLabel rb = new JLabel(new ImageIcon("img\\login\\jm.gif"));
		private JLabel ID = new JLabel("账号：");
		private JLabel Password = new JLabel("密码：");
		private JTextField IDtext=new JTextField(18);
		private JPasswordField Passwordtext=new JPasswordField(18);
		private JButton login = new JButton("登录");
		private JLabel lbl = new JLabel("没有账户？点击注册");
		JPanel panel=new JPanel();
		public Login() {//构造方法
			//1.设计界面
			frame.setSize(310, 310);
			frame.setLayout(new BorderLayout());//采用流式布局
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationRelativeTo(null);//居中
			frame.setResizable(false);//窗口不允许最大化
			
			ID.setFont(new Font("宋体",20,15));
			Password.setFont(new Font("宋体",20,15));
			lbl.setFont(new Font("楷体",20,12));
			
			panel.setLayout(null);
			panel.setBackground(Color.WHITE);
			login.setBackground(Color.BLUE);
			rb.setBounds(0, 0, 300, 160);
			ID.setBounds(50, 160, 50, 50);
			IDtext.setBounds(100, 172, 150, 22);
			Password.setBounds(50, 185, 50, 50);
			Passwordtext.setBounds(100, 200, 150, 22);
			login.setBounds(50, 225, 200, 22);
			lbl.setBounds(140, 230, 150, 50);
			panel.add(rb);
			panel.add(ID);
			panel.add(IDtext);
			panel.add(Password);
			panel.add(Passwordtext);
			panel.add(login);
			panel.add(lbl);
			
			frame.add(panel);
			
			login.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					login();
				}
				
			});
			MyMouseListener listener = new MyMouseListener();
			lbl.addMouseListener(listener);
		}
		
		public void login()  {//登录事件
			
			if(!IDtext.getText().equals("")&&!Passwordtext.getText().equals("")) {
				try {
					SQL sql=new SQL();
					PreparedStatement ps = null;
					ResultSet rs;
					sql.DriveLoad();
					sql.ConnectionCreate();
					String account="select ID,Password from user where ID="+"'"+IDtext.getText()+"'";
					ps=sql.cnn.prepareStatement(account);
					rs=ps.executeQuery();
		     		rs.next();
		     		String IDD= rs.getString(1);
		     		if(IDD.equals(IDtext.getText())) {
		     			if(rs.getString(2).equals(Passwordtext.getText())) {
		     				frame.setVisible(false);
		     				puzzle puzzle = new puzzle(IDtext.getText());
		     				Thread th = new Thread(puzzle);
		     				th.start();
		     			}else {
		     				Passwordtext.setText("");
		     				JOptionPane.showMessageDialog(new JFrame(), "密码错误！！");
		     			}
		     		}else {
		     			Passwordtext.setText("");
		     			JOptionPane.showMessageDialog(new JFrame(), "账号密码错误！！");
		     		}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					Passwordtext.setText("");
	     			JOptionPane.showMessageDialog(new JFrame(), "账号密码错误！！");
				}
			}else {
				JOptionPane.showMessageDialog(new JFrame(), "请输入账号密码！！！");
			}
		}
		
		private class MyMouseListener implements MouseListener{//鼠标事件

			@Override
			public void mouseClicked(MouseEvent e) {//打开注册对话框
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new register();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}

}
