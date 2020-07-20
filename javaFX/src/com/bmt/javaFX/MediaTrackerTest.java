package com.bmt.javaFX;

import java.applet.Applet;
import java.awt.*;

public class MediaTrackerTest extends Applet implements Runnable {

	int imageTotal;
	int imageLoaded;
	
	Image javacup[];
	Thread clock;
	MediaTracker myTracker;
	
	public void init() {
		imageTotal = 16;
		imageLoaded = 0;
		myTracker = new MediaTracker(this);
		javacup = new Image[imageTotal];
		for(int i=0;i<imageTotal;i++) {
			javacup[i]=Toolkit.getDefaultToolkit().getImage("D:\\day\\image_javacup\\javacup"+i+".gif");
			myTracker.addImage(javacup[i], i);
		}
	}
	
	public void start() {
		if(clock==null) {
			clock = new Thread(this);
			clock.start();
		}
	}
	
	public void paint(Graphics g) {
		if(imageLoaded<imageTotal) {
			g.setColor(Color.blue);
			g.drawRect(72, 115, 150, 40);
			g.fillRect(72,  115,  imageLoaded*10,  40);			
		}
		else {
			for(int i=0;i<3;i++) {
				for(int j=0;j<5;j++) {
					g.drawImage(javacup[i*5+j],  j*59,  i*90,  59,  90,  this);
				}
			}
		}
	}
	public void update(Graphics g) {
		paint(g);
	}
	public void run() {
		for(int i=0;i<imageTotal;i++) {
			try {
				myTracker.waitForID(i);
			} 
			catch(InterruptedException ie) {}
			while((myTracker.statusID(i, true) & MediaTracker.COMPLETE)==0){}
			imageLoaded++;
			repaint();
			try{
				clock.sleep(50);
			}
			catch(InterruptedException ie) {}
		}
	}
	
	public void stop() {
		if((clock!=null) && (clock.isAlive())) {
			clock = null;
		}
	}


}

