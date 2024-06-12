package Mode;

import Shape.AssociationShape;
import Shape.GeneralizationShape;
import Shape.LineShape;
import Shape.Port;
import UML.DrawPanel;
import UML.UML;

public abstract class Line {
	
	public LineShape line = null;
	protected int startX, startY, endX, endY;
	protected Port headPort, tailPort;
	
	public abstract void drawLine();
	
	public Line(LineShape lineShape) {
		this.line = lineShape;
	}
	
	public Line(int startX, int startY, int endX, int endY, LineShape lineShape){
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.line = lineShape;
	}	
	
	
	
	public void setStartPoint(int x, int y) {
		Port port = DrawPanel.Head.findNearestPort(x, y, DrawPanel.Head.shape.ports);
		this.headPort = port;
		this.startX = port.transform().x;
		this.startY = port.transform().y;
		
	}
	
	public void setDragPoint(int x, int y) {
		this.endX = x;
        this.endY = y;
        if (line != null) {
            UML.drawPanel.remove(line);
        }
        drawLine();
	}

	public void setEndPoint(int x, int y) {
		Port port = DrawPanel.Tail.findNearestPort(x, y, DrawPanel.Tail.shape.ports);
		this.tailPort = port;
		this.endX = port.transform().x;
		this.endY = port.transform().y;
		if (line != null) {
            UML.drawPanel.remove(line);
        }
        drawLine();
	}
	
	public void moveLineHead(int x, int y) {
		this.startX = x;
        this.startY = y;
        if (line != null) {
            UML.drawPanel.remove(line);
        }
        drawLine();
	}
	
	public void moveLineTail(int x, int y) {
		this.endX = x;
        this.endY = y;
        if (line != null) {
            UML.drawPanel.remove(line);
        }
        drawLine();
	}
}
