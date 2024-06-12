package Shape;

import java.awt.Dimension;

import javax.swing.JPanel;

public class LineShape extends JPanel {
	protected int startX, startY, endX, endY;
	protected int width, height;
	
	public LineShape(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.width = Math.abs(endX-startX);
		this.height = Math.abs(endY-startY);
		
        setPreferredSize(new Dimension(width, height));
        setOpaque(false);
        setBounds(Math.min(startX, endX), Math.min(startY, endY), width, height);
    }
	
	public void setStartPoint(int x, int y) {
		this.startX = x;
		this.startY = y;
		revalidate();
		repaint();
	}
	
	public void setEndPoint(int x, int y) {
		this.endX = x;
		this.endY = y;
		this.width = Math.abs(endX-startX);
		this.height = Math.abs(endY-startY);
		revalidate();
		repaint();
	}
}
