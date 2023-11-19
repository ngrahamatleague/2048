package com.alvontaymaher;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tile {
	
	private int row, col, value;
	public static BufferedImage[] images = new BufferedImage[5];;
	public static boolean needImage = true;
	public static boolean gotImage = false;	
	static {
		loadImages();
		
	}
	public Tile(int row, int col, int value) {
		this.row = row;
		this.col = col;
		this.value = value;
	}
	
	public void draw(Graphics g) {
	
			switch(value) {
				case 0: g.setColor(Color.white);
						g.fillRect(col*100, row*100, 100, 100);
				break;
				case 2:
					g.drawImage(images[0], col*100, row*100, 100, 100, null);
				break;
				case 4: g.drawImage(images[1], col*100, row*100, 100, 100, null);
				break;
				case 8: g.drawImage(images[2], col*100, row*100, 100, 100, null);
				break;
				case 16: g.drawImage(images[3], col*100, row*100, 100, 100, null);
				break;
				case 32: g.drawImage(images[4], col*100, row*100, 100, 100, null);
				break;
	//			case 64: g.setColor(new Color(40,50,60));
	//			break;
	//			case 128: g.setColor(new Color(40,50,60));
	//			break;
	//			case 256: g.setColor(new Color(40,50,60));
	//			break;
	//			case 512: g.setColor(new Color(40,50,60));
	//			break;
	//			case 1024: g.setColor(new Color(40,50,60));
	//			break;
	//			case 2048: g.setColor(new Color(40,50,60));
	//			break;
				
			}
		
//		g.setColor(Color.BLACK);
//        Font font = new Font("Arial", Font.BOLD, 24);
//        g.setFont(font);
//		g.drawString(String.valueOf(value), col*100+40, row*100+40);
		
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	static void loadImages() {
	    if (needImage) {
	        try {
	        	for (int i = 0, num = 2; i < images.length; i++) {
	        		System.out.println("2048-" + num + ".png");
	        		images[i] = ImageIO.read(Tile.class.getResourceAsStream("2048-" + num + ".png"));
	            	num *= 2;
	        	}

	            gotImage = true;
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        needImage = false;
	    }
	}
	
}
