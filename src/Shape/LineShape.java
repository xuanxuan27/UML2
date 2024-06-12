package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public abstract class LineShape extends JPanel {
	protected int startX, startY, endX, endY;
	protected int width, height;
	
	public abstract void paintArrow(Graphics2D g2d, int x1, int y1, int x2, int y2);
	
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
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(2));

        int x1 = startX < endX ? 0 : width;
        int y1 = startY < endY ? 0 : height;
        int x2 = startX < endX ? width : 0;
        int y2 = startY < endY ? height : 0;

        g2d.drawLine(x1, y1, x2, y2);

        paintArrow(g2d, x1, y1, x2, y2);
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
