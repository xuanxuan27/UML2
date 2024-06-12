package Shape;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import Mode.BasicObject;
import UML.UML;

public class CompositeShape extends Shape {
	
	private int startX, startY, endX, endY;
	private int width, height;
	private int x, y;
	private boolean isVisible = true;
	
	public CompositeShape(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if(isVisible) {
			super.paintComponent(g);
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f)); // 透明度
	        g2d.setBackground(Color.white);
	        g2d.setColor(Color.white);
	        this.x = (getWidth() - width) / 2; // 計算出長方形左上角 x 坐標
	        this.y = (getHeight() - height) / 2; // 計算出長方形左上角 y 坐標
	        
	        // 繪製長方形
	        g2d.drawRect(x, y, width-2, height-2);
	        
//	        if(startX > endX && startY < endY) {
//	        	g2d.drawRect(x - width, y, width , height);
//	        }
//	        else if (startX < endX && startY > endY) {
//	        	g2d.drawRect(x, y+height, width, height);
	//
//	        }
//	        else {
//	        	g2d.drawRect(x, y, width, height);
//	        }
	        
		}
    	
    }
	
	@Override
	public boolean isHead(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTail(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void setSelect() {
    	isVisible = true;
    }
	
	@Override
    public void setUnselect() {
    	isVisible = false;
    }
	
	@Override
	public void setText(String text) {
		for(BasicObject obj : UML.drawPanel.compositeObj.includedObjects) {
			obj.shape.setText(text);
		}
	}

	@Override
	public String getText() {
		return null;
	}
}
