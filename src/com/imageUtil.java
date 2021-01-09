package com;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class imageUtil {
	
	public static boolean cutImage(File sourcePath,int cutNumber,String savaPath) {
		try {
			BufferedImage image=ImageIO.read(sourcePath);
			int allWidth =image.getWidth();
			int allHeight = image.getHeight();
			int width = allWidth/cutNumber;
			int height = allHeight/cutNumber;
			for(int i=0;i<cutNumber;i++) {
				for(int j=0;j<cutNumber;j++) {
					ImageIO.write(image.getSubimage(j*width, i*height, width, height),"jpg",
							new File(savaPath+"\\"+(i*cutNumber+j)+".jpg"));
				}
			}
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
