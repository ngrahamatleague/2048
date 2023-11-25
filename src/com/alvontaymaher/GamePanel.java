package com.alvontaymaher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements KeyListener {

	static final int WIDTH = 400;
	static final int HEIGHT = 400;
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;

	Tile[][] tiles = new Tile[4][4];
	Random r = new Random();
	JFrame frame = new JFrame("2048");
	int[] empty = new int[2];

	public GamePanel() {

		frame.add(this);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		//	frame.setBackground(new Color(50,50,70));	
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(3);
		this.setBackground(Color.white);


		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {

				Tile tile = new Tile(i, j, 0);
				tiles[i][j] = tile;

			}
		}

		newTile();
		newTile();

		frame.pack();
		repaint();

	}

	private void newTile() {
		if (anyEmpty()) {
			empty = findEmptyTile();
			tiles[empty[0]][empty[1]].setValue(generateStartingValue());			
		}

	}

	private boolean anyEmpty() {
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				if (tiles[i][j].getValue() == 0) {
					return true;
				}
			}
		}
		return false;
	}

	private int generateStartingValue() {
		return r.nextInt(4) == 0? 4: 2;
	}

	private int[] findEmptyTile() {

		int row, col;

		do {
			row = r.nextInt(4);
			col = r.nextInt(4);
		} while (tiles[row][col].getValue() != 0);

		int[] values = {row, col};

		return values;
	}

	@Override
	public void paintComponent(Graphics g) { 



		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				try {
					tiles[j][i].draw(g);
				}catch(Exception e) {
					System.out.println(j + ", " + i);
				}
			}
		}


	}

	private void move(int direction) {



		//		if space (direction) is empty move there
		//		if space (direction) is another tile of same value then combine

		int r, c;

		//		if up start with row at 0, if down start with row at 3 ect
		//		switch(direction) {
		//			case UP: row = 1;
		//			break;
		//			case DOWN: row = 3;
		//			break;
		//			case LEFT: col = 1;
		//			break;
		//			case RIGHT: col = 3;
		//			break;
		//		}
		boolean newTileNeeded = false;
		if(direction == UP) {

			for(r = 1; r < 4; r++) {
				for(c = 0; c < 4; c++) {
					if (tiles[r][c].getValue() != 0) {
						if(moveUp(r,c)) {
							newTileNeeded = true;
						}
					}
				}

			}
		}
		if(direction == DOWN) {
			for(r = 3; r > -1; r--) {
				for(c = 0; c < 4; c++) {
					if (tiles[r][c].getValue() != 0) {
						if(moveDown(r,c)) {
							newTileNeeded = true;
						}
					}
				}

			}
		}
		if(direction == LEFT) {
			for(c = 1; c < 4; c++) {
				for(r = 0; r < 4; r++) {
					if (tiles[r][c].getValue() != 0) {
						if(moveLeft(r,c)) {
							newTileNeeded = true;
						}
					}
				}

			}
		}
		if(direction == RIGHT) {
			for(c = 3; c > -1; c--) {
				for(r = 0; r < 4; r++) {
					if (tiles[r][c].getValue() != 0) {
						if(moveRight(r,c)) {
							newTileNeeded = true;
						}
					}
				}

			}
		}
		
		if(newTileNeeded) {
			newTile();
		}
	}


	private boolean moveUp(int r, int c) {
		int row = r;

		boolean returnVal = false;
		
		while (row > 0 && tiles[row-1][c].getValue() == 0) {
			row--;
		}

		tiles[row][c].setValue(tiles[r][c].getValue());
		if(row!=r) {
			tiles[r][c].setValue(0);
			returnVal = true;
		}
		if (row > 0 && tiles[row-1][c].getValue() == tiles[row][c].getValue()) {
			tiles[row-1][c].setValue(tiles[row][c].getValue()*2);
			tiles[row][c].setValue(0);
			returnVal = true;
		}
		return returnVal;

	}

	private boolean moveDown(int r, int c) {
		int row = r;

		boolean returnVal = false;
		
		while (row <3 && tiles[row+1][c].getValue() == 0) {
			row++;
		}

		tiles[row][c].setValue(tiles[r][c].getValue());
		if(row!=r) {
			tiles[r][c].setValue(0);
			returnVal = true;
		}
		if (row <3  && tiles[row+1][c].getValue() == tiles[row][c].getValue()) {
			tiles[row+1][c].setValue(tiles[row][c].getValue()*2);
			tiles[row][c].setValue(0);
			returnVal = true;
		}
		return returnVal;
	}

	private boolean moveRight(int r, int c) {
		int col = c;
		boolean returnVal = false;
		
		while (col < 3 && tiles[r][col+1].getValue() == 0) {
			col++;
		}

		tiles[r][col].setValue(tiles[r][c].getValue());
		if(col != c) {
			tiles[r][c].setValue(0);
			returnVal = true;
		}
		if (col < 3  && tiles[r][col+1].getValue() == tiles[r][col].getValue()) {
			tiles[r][col+1].setValue(tiles[r][col].getValue()*2);
			tiles[r][col].setValue(0);
			returnVal = true;
		}
		
		return returnVal;
	}

	private boolean moveLeft(int r, int c) {
		int col = c;
		boolean returnVal = false;
		
		while (col > 0 && tiles[r][col-1].getValue() == 0) {
			col--;
		}

		tiles[r][col].setValue(tiles[r][c].getValue());
		if(col != c) {
			tiles[r][c].setValue(0);
			returnVal = true;
		}
		if (col > 0  && tiles[r][col-1].getValue() == tiles[r][col].getValue()) {
			tiles[r][col-1].setValue(tiles[r][col].getValue()*2);
			tiles[r][col].setValue(0);
			returnVal = true;
		}
		return returnVal;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_UP) {
			move(UP);
		}

		if (key == KeyEvent.VK_DOWN) {
			move(DOWN);
		}

		if (key == KeyEvent.VK_LEFT) {
			move(LEFT);
		}

		if (key == KeyEvent.VK_RIGHT) {
			move(RIGHT);
		}

	
		repaint();

	}


}
