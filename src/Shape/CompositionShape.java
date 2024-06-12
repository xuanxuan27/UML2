package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CompositionShape extends LineShape {
	
	public CompositionShape(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        g2d.setStroke(new BasicStroke(2));
        
        int x = (getWidth() - width) / 2;
        int y = (getHeight() - height) / 2;
        
        if(startX > endX && startY < endY) {
        	g2d.drawLine(x + width, y, x , y + height);
        }
        else if (startX < endX && startY > endY) {
        	g2d.drawLine(x, y+height, x+width, y);
        }
        else {
        	g2d.drawLine(x, y, x+width, y+height);
        }
    }
}
