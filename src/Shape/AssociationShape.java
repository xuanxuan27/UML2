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
	    int dx = x2 - x1;
	    int dy = y2 - y1;
	    double angle = Math.atan2(dy, dx);
	    int len = (int) Math.sqrt(dx * dx + dy * dy);

	    AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
	    at.concatenate(AffineTransform.getRotateInstance(angle));
	    g2d.transform(at);

	    // 绘制箭头线段
	    g2d.drawLine(0, 0, len, 0);
	    // 绘制箭头头部
	    int arrowSize = 10;
	    Polygon arrow = new Polygon();
	    arrow.addPoint(len, 0);
	    arrow.addPoint(len - arrowSize, arrowSize / 2);
	    arrow.addPoint(len - arrowSize, -arrowSize / 2);
	    g2d.fill(arrow);
	}
}
