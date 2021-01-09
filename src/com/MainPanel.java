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
	Button[] button = new Button[25];//按钮数组
	ImageIcon[] icon = new ImageIcon[25];//图片数组
	int state[] = new int[25];//图片存放顺序
	int nullButton;//空白按钮位置
	int pattern;//图片底数
	int total;//图片总数
	int count=0;//总步数
	
	public MainPanel(String path,int pattern) {
		this.pattern = pattern;
		total = pattern*pattern;
		breakRandom(path,pattern);
	}
	
	public void breakRandom(String path,int pattern) {//生成按钮，把图片放到按钮
		count=0;
		this.pattern=pattern;
		total=pattern*pattern;
		imageUtil.cutImage(new File(path+"\\index.jpg"), pattern, path+pattern);
		this.removeAll();
		this.updateUI();//刷新界面
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
	
	public void random(int a[]) {//随机数组
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
		
		int cover=0;//调整拼图位置，使拼图有解
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
		
		System.out.println("图片的初始顺序为");
		for(i=0;i<total;i++) {
			System.out.print(a[i]+" ");
		}
	}
	
	public boolean remove(Button clicked) {//移动拼图块
		int rowN = button[nullButton].getRow();//空白按钮位置
		int colN = button[nullButton].getCol();
		int rowC = clicked.getRow();//移动按钮位置
		int colC = clicked.getCol();
		if(((rowN-rowC)==1 && (colN-colC)==0) || ((rowN-rowC)==-1 && (colN-colC)==0) || ((rowN-rowC)==0 && (colN-colC)==1)//判断是否可移动
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
	
	public boolean check() {//判断拼图是否完成
		for (int i = 0; i < total; i++) {//判断图片名和按钮位置是否匹配
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
