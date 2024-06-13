package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class CompositionShape extends LineShape {
	
	public CompositionShape(int startX, int startY, int endX, int endY) {
		super(startX, startY, endX, endY);
	}
	

	@Override
	public void paintArrow(Graphics2D g2d, int x1, int y1, int x2, int y2) {
		// 计算直线的角度
        double theta = Math.atan2(y2 - y1, x2 - x1);

        // 菱形的半径（即从中心到顶点的距离）
        int diamondRadius = 10;

        // 计算菱形的四个顶点坐标
        int[] xPoints = new int[4];
        int[] yPoints = new int[4];

        xPoints[0] = x2;
        yPoints[0] = y2;

        xPoints[1] = (int) (x2 - diamondRadius * Math.cos(theta - Math.toRadians(45)));
        yPoints[1] = (int) (y2 - diamondRadius * Math.sin(theta - Math.toRadians(45)));

        xPoints[2] = (int) (x2 - 2 * diamondRadius * Math.cos(theta));
        yPoints[2] = (int) (y2 - 2 * diamondRadius * Math.sin(theta));

        xPoints[3] = (int) (x2 - diamondRadius * Math.cos(theta + Math.toRadians(45)));
        yPoints[3] = (int) (y2 - diamondRadius * Math.sin(theta + Math.toRadians(45)));

        // 画空心菱形
        g2d.drawPolygon(xPoints, yPoints, 4);
		
	}
}
