package Shape;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
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
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.width = Math.abs(endX-startX);
		this.height = Math.abs(endY-startY);
		
		// 計算左上角坐標
        this.x = Math.min(startX, endX);
        this.y = Math.min(startY, endY);
        
        setPreferredSize(new Dimension(width, height));
        setBounds(this.x, this.y, width, height);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		if (isVisible) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); // 透明度
            g2d.setBackground(Color.white);
            g2d.setColor(Color.white);
            
            // 再次計算寬度和高度，防止在界面調整大小後數值出錯
            this.width = Math.abs(endX - startX);
            this.height = Math.abs(endY - startY);
            
            // 計算左上角坐標
            this.x = Math.min(startX, endX);
            this.y = Math.min(startY, endY);
            
            // 繪製長方形
            g2d.drawRect(0, 0, width, height);
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
