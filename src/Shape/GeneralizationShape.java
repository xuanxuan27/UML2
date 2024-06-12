package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GeneralizationShape extends LineShape {

	public GeneralizationShape(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}
	
	
	public void paintArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		// Calculate the angle of the line
        double theta = Math.atan2(y2 - y1, x2 - x1);

        // Length of the sides of the triangle
        int arrowLength = 15;
        int arrowWidth = 10;

        // Calculate the points for the arrow
        int[] xPoints = new int[3];
        int[] yPoints = new int[3];

        xPoints[0] = x2;
        yPoints[0] = y2;

        xPoints[1] = (int) (x2 - arrowLength * Math.cos(theta - Math.toRadians(45)));
        yPoints[1] = (int) (y2 - arrowLength * Math.sin(theta - Math.toRadians(45)));

        xPoints[2] = (int) (x2 - arrowLength * Math.cos(theta + Math.toRadians(45)));
        yPoints[2] = (int) (y2 - arrowLength * Math.sin(theta + Math.toRadians(45)));

        // Draw the arrow
        g2d.drawPolygon(xPoints, yPoints, 3);
	}
}
