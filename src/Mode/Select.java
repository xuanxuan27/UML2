package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import Shape.SelectShape;
import java.util.ArrayList;

import Shape.CompositeShape;
import UML.UML;

public class Select implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.SELECT);
	}
	
	private int startX, startY, endX, endY;
	public CompositeShape shape = null;
	public ArrayList<BasicObject> includedObjects;

	public void drawObject() {
		shape = new CompositeShape(startX, startY, endX, endY);
		UML.drawPanel.add(shape);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
	
	public void groupObject() {
		for(BasicObject obj : UML.drawPanel.includedObjs) {
			includedObjects.add(obj);
		}
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
	}

//	public void updatePosition(int x, int y) {
//		
//	}
//
//	public void setSelected() {
//		
//	}
//
//	public void unSelected() {
//		
//	}
}
