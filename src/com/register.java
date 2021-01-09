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

public class register {
	JFrame zc = new JFrame("ע��");
	public register() {
		zc.setVisible(true);
		zc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		zc.setSize(300, 300);
		zc.setLayout(new BorderLayout());//������ʽ����
		zc.setLocationRelativeTo(null);//Ĭ�ϴ�λ��
		zc.setResizable(false);//���ڲ��������
		
		JLabel bg = new JLabel(new ImageIcon("img\\login\\bg.jpg"));
		JLabel ID = new JLabel("�˺ţ�");
		JLabel Password = new JLabel("���룺");
		JTextField IDtext=new JTextField(18);
		JPasswordField Passwordtext=new JPasswordField(18);
		JLabel Passwordnext = new JLabel("ȷ�����룺");
		JPasswordField Passwordtextnext = new JPasswordField(18);
		JButton login = new JButton("ע��");
		JLabel back = new JLabel("�����˺ţ����ص�¼����");
		
		back.setForeground(Color.RED);
		ID.setFont(new Font("����",Font.BOLD,12));
		Password.setFont(new Font("����",Font.BOLD,12));
		Passwordnext.setFont(new Font("����",Font.BOLD,12));
		back.setFont(new Font("����",20,12));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		bg.setBounds(0, 0, 300, 300);
		ID.setBounds(30, 85, 50, 50);
		IDtext.setBounds(100, 97, 150, 22);
		Password.setBounds(30, 115, 50, 50);
		Passwordtext.setBounds(100, 130, 150, 22);
		Passwordnext.setBounds(30, 145, 70, 50);
		Passwordtextnext.setBounds(100, 163, 150, 22);
		login.setBounds(30, 210, 220, 22);
		back.setBounds(120, 240, 150, 15);
		
		panel.add(ID);
		panel.add(IDtext);
		panel.add(Password);
		panel.add(Passwordtext);
		panel.add(Passwordnext);
		panel.add(Passwordtextnext);
		panel.add(login);
		panel.add(back);
		panel.setOpaque(false);
		zc.add(panel);
		
		((JPanel)zc.getContentPane()).setOpaque(false);
		zc.getLayeredPane().add(bg,new Integer(Integer.MIN_VALUE));
		
		
		login.addActionListener(new ActionListener() {//ע���˺ź󣬷���������

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SQL sql=new SQL();
				PreparedStatement ps = null;
				ResultSet rs;
				sql.DriveLoad();
				sql.ConnectionCreate();
				if(Passwordtext.getText().equals(Passwordtextnext.getText())&&!Passwordtext.getText().equals("")&&!IDtext.getText().equals("")) {
					try {
						String insert= "insert into user values(?,?)";//SQL���
						ps = sql.cnn.prepareStatement(insert);//Ԥ����
						ps.setString(1, IDtext.getText());
						ps.setString(2, Passwordtext.getText());
			    		ps.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
						System.out.println("��������ʧ�ܣ�");
					}
					zc.setVisible(false);
					new Login();
				}else {
					Passwordtext.setText("");
					Passwordtextnext.setText("");
					JOptionPane.showMessageDialog(new JFrame(), "���벻һ�£����������룡");
				}
				
			}
		});
		MyMouseListener listener = new MyMouseListener();
		back.addMouseListener(listener);
	}
	private class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			zc.setVisible(false);
			new Login();
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
}
