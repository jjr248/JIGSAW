package com;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class history_panel extends JFrame {
	public history_panel(String id) {
		String bt[]= {"�˺�","��ͼ","�Ѷ�","ʱ��","����"};
		history_data da = new history_data();
		da.history_date(id);
		DefaultTableModel model = new DefaultTableModel(da.data,bt);
		JTable tabel = new JTable(model);
		
		tabel.getTableHeader().setReorderingAllowed(false);
		tabel.setEnabled(false);//tabel ���ܱ༭
		JScrollPane pane =new JScrollPane();
		pane.setViewportView(tabel);
		pane.setBounds(0, 0, 380, 400);
		
		this.add(pane);
		
		this.setTitle("��ʷ����");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);//����
		this.setSize(380, 420);
		this.setResizable(false);//���ڲ��������
		this.setVisible(true);
	}
}
