package Shape;

import java.awt.*;

import javax.swing.SwingUtilities;

import UML.UML;

public class Port {
	private int x, y;
    private int size = 4;
    private Component parent; // 父组件的引用

    private boolean isConnected = false, isVisible = false;

    public Port(int x, int y, Component parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    public void paint(Graphics2D g2d) {
    	if(isVisible) {
    		g2d.setColor(Color.white);
            g2d.fillRect(x, y, size, size);
            g2d.setColor(Color.white);
            g2d.drawRect(x, y, size, size);
    	}
    	
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
    
    public Component getParent() {
        return parent;
    }
    
    // 轉換到 drawPanel
    public Point transform() { 
    	Point point = SwingUtilities.convertPoint(this.getParent(), new Point(this.x, this.y), UML.drawPanel);
        return point;
	}
    
    public void setVisible() {
    	isVisible = true;
    }
    public void setInvisible() {
    	isVisible = false;
    }
}
