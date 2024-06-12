package Mode;

import java.awt.Point;
import java.util.ArrayList;

import Shape.CompositeShape;
import UML.UML;

public class CompositeObject extends BasicObject {
	private int startX, startY, endX, endY;
	public ArrayList<BasicObject> includedObjects;

	public CompositeObject() {
		includedObjects = new ArrayList<BasicObject>();
	}
	
	@Override
	public void drawObject() {
		shape = new CompositeShape(startX, startY, endX, endY);
		UML.drawPanel.add(shape);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
	
	public void groupObject() {
		for(BasicObject obj : UML.drawPanel.includedObjs) {
			includedObjects.add(obj);
			UML.drawPanel.basicObjects.remove(obj);
		}
		UML.drawPanel.includedObjs.clear();
		UML.drawPanel.basicObjects.add(this);
		
		for(BasicObject obj : UML.drawPanel.basicObjects) {
			String name = obj.getClass().getName();
		}
		setSelected();
		
	}
	
	public void setStartPoint(int x, int y) {
		this.startX = x;
		this.startY = y;
	}
	
	public void setDragPoint(int x, int y) {
		this.endX = x;
		this.endY = y;
		if(shape != null)
			UML.drawPanel.remove(shape);
		drawObject();
	}
	
	public void setEndPoint(int x, int y) {
		this.endX = x;
		this.endY = y;
		if(shape != null)
			UML.drawPanel.remove(shape);
		drawObject();
	}

	@Override
	public boolean isHead(int x, int y) {
		return false;
	}

	@Override
	public boolean isTail(int x, int y) {
		return false;
	}

	@Override
	public void updatePosition(int dx, int dy) {
		
		// 改來改去突然就可以正常動了，為啥
		Point location = shape.getLocation();
	    int newX = location.x + dx;
	    int newY = location.y + dy;
	    shape.setLocation(newX, newY);
	    for (BasicObject obj : includedObjects) {
	        obj.updatePosition(dx, dy); // 更新成员对象的位置
	    }

	}
	
	public void updateConnections(int x, int y) {
		for(BasicObject obj : includedObjects) {
			obj.updateConnections(x, y);
		}
	}

	@Override
	public void setSelected() {
		isSelect = true;
		shape.setSelect();
		for (BasicObject obj : includedObjects) {
			obj.isSelect = true;
			obj.shape.setSelect();
		}
	}
	
	@Override
	public void unSelected() {
		isSelect = false;
		shape.setUnselect();
		for (BasicObject obj : includedObjects) {
			obj.isSelect = false;
			obj.shape.setUnselect();
		}
	}

}
