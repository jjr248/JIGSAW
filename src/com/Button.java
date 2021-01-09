package com;

import javax.swing.JButton;

public class Button extends JButton {//按钮下标，用于移动记录
	int row;
	int col;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	
}
