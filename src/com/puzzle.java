package com;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class puzzle extends JFrame implements Runnable{
	MainPanel panel;
	String path;//ͼƬ��·��
	int pattern;//ͼƬ�ĵ���
	JMenuBar jmBar;
	JMenu menu,menuSelect,menuChange,menuRank,menuHelp;
	JMenuItem itemStart,itemExit,itemView,history;
	JRadioButtonMenuItem pic_change[] = new JRadioButtonMenuItem[6];
	JRadioButtonMenuItem game_rank[] = new JRadioButtonMenuItem[3];
	JLabel total_time;
	JLabel total_count;
	long startTime;
	long endTime;
	String I;
	
	public puzzle(String ID) {
		I=ID;
		
		jmBar = new JMenuBar();
		menu = new JMenu("�˵�");
		menuSelect = new JMenu("ѡ��");
		menuHelp = new JMenu("����");
		menuChange = new JMenu("ͼƬ����");
		menuRank = new JMenu("�ȼ�");
		
		itemStart = new JMenuItem("��ʼ");
		itemExit = new JMenuItem("�˳�");
		itemView = new JMenuItem("�鿴����");
		history = new JMenuItem("��ʷ��¼");
		
		total_time = new JLabel("ʱ�䣺");
		total_count = new JLabel("������");
		total_time.setForeground(Color.RED);
		total_count.setForeground(Color.RED);
		
		ButtonGroup groupChange = new ButtonGroup();
		for(int i=0;i<pic_change.length;i++) {
			pic_change[i] = new JRadioButtonMenuItem("0"+(i+1)+".jpg");
			groupChange.add(pic_change[i]);
			menuChange.add(pic_change[i]);
		}
		pic_change[0].setSelected(true);
		
		ButtonGroup groupRank = new ButtonGroup();
		String content;
		for(int i=0;i<game_rank.length;i++) {
			if(i==0)content="��";
			else if(i==1)content="��ͨ";
			else content="����";
			game_rank[i] = new JRadioButtonMenuItem(content);
			groupRank.add(game_rank[i]);
			menuRank.add(game_rank[i]);
		}
		game_rank[0].setSelected(true);
		
		menu.add(itemStart);
		menu.add(itemView);
		menu.add(itemExit);
		menuSelect.add(menuChange);
		menuSelect.add(menuRank);
		menuHelp.add(history);
		jmBar.add(menu);
		jmBar.add(menuSelect);
		jmBar.add(menuHelp);
		jmBar.add(new JLabel("                                              "));
		jmBar.add(total_time);
		jmBar.add(new JLabel("     "));
		jmBar.add(total_count);
		
		this.setJMenuBar(jmBar);
		
		itemStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				breakState(I);
				
			}
			
		});
		
		itemExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
			
		});
		
		itemView.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JButton index = new JButton(new ImageIcon(path+"\\index.jpg")); 
				JFrame model = new JFrame("ͼƬģ��");
				model.setSize(370, 370);
				model.setResizable(false);
				model.add(index);
				model.setVisible(true);
			}
			
		});
		history.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new history_panel(I);
			}
			
		});
		
		this.setTitle("ƴͼ");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);//����
		this.setSize(380, 420);
		this.setResizable(false);//���ڲ��������
		this.setPath();
		this.setPattern();
		
		panel = new MainPanel(path,pattern);
		
		this.add(panel);
		this.setVisible(true);

		//startTime = System.currentTimeMillis();
	}
	
	public void run() {//���߳�
		while(true) {
			int time=0;
			total_time.setText("ʱ�䣺"+time);
			total_count.setText("����"+panel.getCount());
			if(panel.getCount()!=0) {
				startTime = System.currentTimeMillis();
				while(!panel.check()) {
					endTime = System.currentTimeMillis();
					time = (int)((endTime-startTime)/1000);
					total_time.setText("ʱ�䣺"+time);
					total_count.setText("����"+panel.getCount());
				}
				total_count.setText("����"+panel.getCount());
				new Sava_data(I,String.valueOf(path.charAt(8)),pattern,time,panel.getCount());
				JOptionPane.showMessageDialog(this, "ƴͼ���");
				break;//�������ݺ����ѭ��
			}
		}
	}
	
	public void breakState(String I) {//��ʼ��ť
		/*puzzle puzzle = new puzzle(I);//���¿�ʼ�߳�
		Thread th = new Thread(puzzle);
		th.start();
		this.setVisible(false);//�رյ�ǰ����
*/		
		startTime = System.currentTimeMillis();
		setPattern();
		setPath();
		panel.breakRandom(path, pattern);
		}
	
	public void setPath() {//���ͼƬ·��
		for(int i=0;i<pic_change.length;i++) {
			if(pic_change[i].isSelected()) {//�ж�ѡ������ĸ���ť
				path = "img\\type"+(i+1)+"\\";//�õ�·��
			}
		}
	}
	
	public void setPattern() {//��ü���ͼ
		for(int i=0;i<game_rank.length;i++) {
			if(game_rank[i].isSelected()) {//�ж�ѡ������ĸ���ť
				if(i==0)pattern=3;
				else if(i==1)pattern=4;
				else pattern=5;
			}
		}
	}
}
