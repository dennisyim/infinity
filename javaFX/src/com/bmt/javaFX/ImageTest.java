package com.bmt.javaFX;

import java.applet.*;
import java.awt.*;

public class ImageTest extends Applet {
	Image myImage1;
	Image myImage2;
	Image myImage3;
	
	public void init() {
		myImage1 = Toolkit.getDefaultToolkit().getImage("D:\\day\\abc\\image2.jpg");
		myImage2 = Toolkit.getDefaultToolkit().getImage("D:\\day\\abc\\image8.jpg");
		myImage3 = Toolkit.getDefaultToolkit().getImage("D:\\day\\abc\\mountain_2.jpg");
	}
	
	public void paint(Graphics g) {
		g.drawImage(myImage1, 0, 0, this);
		g.drawImage(myImage2, 220, 0, this);
		g.drawImage(myImage3, 0, 160, this);
	}
}
