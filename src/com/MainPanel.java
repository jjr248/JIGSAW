package com;

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
	Button[] button = new Button[25];//��ť����
	ImageIcon[] icon = new ImageIcon[25];//ͼƬ����
	int state[] = new int[25];//ͼƬ���˳��
	int nullButton;//�հװ�ťλ��
	int pattern;//ͼƬ����
	int total;//ͼƬ����
	int count=0;//�ܲ���
	
	public MainPanel(String path,int pattern) {
		this.pattern = pattern;
		total = pattern*pattern;
		breakRandom(path,pattern);
	}
	
	public void breakRandom(String path,int pattern) {//���ɰ�ť����ͼƬ�ŵ���ť
		count=0;
		this.pattern=pattern;
		total=pattern*pattern;
		imageUtil.cutImage(new File(path+"\\index.jpg"), pattern, path+pattern);
		this.removeAll();
		this.updateUI();//ˢ�½���
		this.setLayout(new GridLayout(pattern,pattern));
		nullButton=total-1;
		random(state);
		for(int i=0;i<total;i++) {
			button[i]=new Button();
			button[i].setRow(i/pattern);
			button[i].setCol(i%pattern);
			this.add(button[i]);
		}
		
		for(int i=0;i<total-1;i++) {
			icon[i] = new ImageIcon(path+pattern+"\\"+state[i]+".jpg");
			button[i].setIcon(icon[i]);
		}
		
		for (int i = 0; i < total; i++) {
			button[i].addMouseListener(new MouseAdapter() {
				public void mousePressed(MouseEvent e) {
					Button button = (Button)e.getSource();
					if(remove(button))count++;
				}
			});
			
		}
	}
	
	public void random(int a[]) {//�������
		Random cd = new Random();
		int i=0;
		a[0]=cd.nextInt(total-1);
		for(i=0;i<total-1;i++) {
			int temp=cd.nextInt(total-1);
			for(int j=0;j<i;j++) {
				if(a[j]!=temp) {
					a[i]=temp;
				}else {
					i--;
					break;
				}
			}
		}
		a[i]=total-1;
		
		int cover=0;//����ƴͼλ�ã�ʹƴͼ�н�
		for(i=0;i<total-1;i++) {
			for(int j=i+1;j<total-1;j++) {
				if(a[i]>a[j]) {
					cover++;
				}
			}
		}
		if((cover&1)==1) {
			int temp=a[total-2];
			a[total-2]=a[total-3];
			a[total-3]=temp;
		}
		
		System.out.println("ͼƬ�ĳ�ʼ˳��Ϊ");
		for(i=0;i<total;i++) {
			System.out.print(a[i]+" ");
		}
	}
	
	public boolean remove(Button clicked) {//�ƶ�ƴͼ��
		int rowN = button[nullButton].getRow();//�հװ�ťλ��
		int colN = button[nullButton].getCol();
		int rowC = clicked.getRow();//�ƶ���ťλ��
		int colC = clicked.getCol();
		if(((rowN-rowC)==1 && (colN-colC)==0) || ((rowN-rowC)==-1 && (colN-colC)==0) || ((rowN-rowC)==0 && (colN-colC)==1)//�ж��Ƿ���ƶ�
				|| ((rowN-rowC)==0 && (colN-colC)==-1)) {
			ImageIcon icon = (ImageIcon)clicked.getIcon();
			button[nullButton].setIcon(icon);
			clicked.setIcon(null);
			int clickState = rowC*pattern+colC;
			nullButton = rowN*pattern+colN;
			state[nullButton] = state[clickState];
			state[clickState] = total-1;
			nullButton = clickState;
			check();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean check() {//�ж�ƴͼ�Ƿ����
		for (int i = 0; i < total; i++) {//�ж�ͼƬ���Ͱ�ťλ���Ƿ�ƥ��
			if(state[i]!=i) {
				return false;
			}
		}
		
		return true;
	}
	
	public int getCount() {
		return count;
	}
}
