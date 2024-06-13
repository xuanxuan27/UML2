package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;

public class AssociationShape extends LineShape{

	public AssociationShape(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}
	
	@Override
	public void paintArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		// Calculate the angle of the line
        double theta = Math.atan2(y2 - y1, x2 - x1);

        // Arrow size
        int arrowLength = 10;
        int arrowWidth = 5;

        // Calculate the end points of the two lines forming the arrow head
        int arrowX1 = (int) (x2 - arrowLength * Math.cos(theta + Math.toRadians(30)));
        int arrowY1 = (int) (y2 - arrowLength * Math.sin(theta + Math.toRadians(30)));
        int arrowX2 = (int) (x2 - arrowLength * Math.cos(theta - Math.toRadians(30)));
        int arrowY2 = (int) (y2 - arrowLength * Math.sin(theta - Math.toRadians(30)));

        // Draw the arrow head
        g2d.drawLine(x2, y2, arrowX1, arrowY1);
        g2d.drawLine(x2, y2, arrowX2, arrowY2);
	}
}
