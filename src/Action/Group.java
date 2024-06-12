package Action;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Mode.BasicObject;
import Mode.CompositeObject;
import Shape.CompositeShape;
import UML.UML;

public class Group implements ActionListener{
	
	public int topLeftX, topLeftY, bottomRightX, bottomRightY;
	public CompositeShape shape;
	// public CompositeObject compositeObj;
	ArrayList<BasicObject> includedObjs = UML.drawPanel.includedObjs;
	
	public void actionPerformed(ActionEvent e) {
		this.topLeftX = findTopLeftPoint().x;
		this.topLeftY = findTopLeftPoint().y;
		this.bottomRightX = Math.max(findTopLeftPoint().x, findBottomRightPoint().x);
		this.bottomRightY = Math.max(findTopLeftPoint().y, findBottomRightPoint().y);
		
		UML.drawPanel.compositeObj = new CompositeObject();
		
		UML.drawPanel.compositeObj.setStartPoint(topLeftX, topLeftY);
		UML.drawPanel.compositeObj.setEndPoint(bottomRightX, bottomRightY);
		UML.drawPanel.compositeObj.drawObject();
		
		UML.drawPanel.compositeObj.groupObject();
    }
	
	public void drawObject() {
		shape = new CompositeShape(topLeftX, topLeftY, bottomRightX, bottomRightY);
		UML.drawPanel.add(shape);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
	
	public Point findTopLeftPoint() {
        int topLeftX = Integer.MAX_VALUE, topLeftY = Integer.MAX_VALUE;
        for (BasicObject obj : includedObjs) {
            Rectangle bounds = obj.shape.getBounds();
            Point objTopLeft = new Point(bounds.x, bounds.y);
            Point convertedObjTopLeft = transform(obj, objTopLeft);
            if (convertedObjTopLeft.x < topLeftX) {
                topLeftX = objTopLeft.x;
            }
            if(convertedObjTopLeft.y < topLeftY) {
            	topLeftY = objTopLeft.y;
            }
        }
        return new Point(topLeftX, topLeftY);
    }

    public Point findBottomRightPoint() {
    	int bottomRightX = Integer.MIN_VALUE, bottomRightY = Integer.MIN_VALUE;
        for (BasicObject obj : includedObjs) {
            Rectangle bounds = obj.shape.getBounds();
            Point objBottomRight = new Point(bounds.x + bounds.width, bounds.y + bounds.height);
            Point convertedObjBottomRight = transform(obj, objBottomRight);
            if (convertedObjBottomRight.x > bottomRightX) {
                bottomRightX = objBottomRight.x;
            }
            if(objBottomRight.y > bottomRightY) {
            	bottomRightY = objBottomRight.y;
            }
        }
        return new Point(bottomRightX, bottomRightY);
    }
    
    public Point transform(BasicObject obj, Point point) {
    	Point convertedPoint = SwingUtilities.convertPoint(obj.shape.getParent(), point, UML.drawPanel);
    	return convertedPoint;
    }

}
