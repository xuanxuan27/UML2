package Mode;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import Shape.LineShape;
import Shape.Port;
import Shape.Shape;
import Shape.UsecaseShape;
import UML.DrawPanel;
import UML.UML;

public abstract class BasicObject{
	private int depth;
	public int x, y;
	public Shape shape;
	public ArrayList<Line> HeadLines = new ArrayList<Line>();
	public ArrayList<Line> TailLines = new ArrayList<Line>();;
	public boolean isSelect = false;
	private boolean isHead = false, isTail = false;
	
	public BasicObject() {
		
	}
	
	public BasicObject(int x, int y, Shape shape) {
		this.x = x;
		this.y = y;
		this.shape = shape;
	}
	
	public void drawObject() {
		shape.setPreferredSize(new Dimension(200, 200));
		UML.drawPanel.add(shape);
        UML.drawPanel.revalidate(); // 重新布局
        UML.drawPanel.repaint(); // 刷新绘图
	}
	
	public boolean isHead(int x, int y) {
		if(shape.isHead(x, y)) {
			setHead();	
			return true;
		}
		else
			return false;
	}
	
	public boolean isTail(int x, int y) {
		if(shape.isTail(x, y)) {
			setTail();
			return true;
		}
		else
			return false;
	}
	
	public void setHead() {
		isHead = true;
		DrawPanel.Head = this;
		
	}
	
	public void setTail() {
		isTail = true;
		DrawPanel.Tail = this;

	}
	
	public void setSelected() {
		isSelect = true;
		shape.setSelect();
	}
	
	public void unSelected() {
		isSelect = false;
		shape.setUnselect();
	}
	
	public void updatePosition(int x, int y) {
		
		this.x = x + shape.getX();
		this.y = y + shape.getY();
		shape.updatePosition(this.x, this.y);
	}
	
	int getDepth() {
		return depth;
	}

	public Port findNearestPort(int mouseX, int mouseY, ArrayList<Port> ports) {
	    Port nearestPort = null;
	    double minDistance = Double.MAX_VALUE;
	    
	    for (Port port : ports) {
	        int portCenterX = port.getX() + port.getSize() / 2;
	        int portCenterY = port.getY() + port.getSize() / 2;
	        
	        //  轉換座標系
	        Point point = port.transform();
	        
	        // 計算鼠標點擊位置與端口中心的距離
	        double distance = Math.sqrt(Math.pow(point.x - mouseX, 2) + Math.pow(point.y - mouseY, 2));

	        // 更新最近端口
	        if (distance < minDistance) {
	            minDistance = distance;
	            nearestPort = port;
	        }
	    }

	    return nearestPort;
	}
	
	public void updateConnections(int dx, int dy) {
		for(Line line : HeadLines) {
			if (line != null) {
		        line.moveLineHead(line.startX + dx, line.startY + dy);
		    }
		}
		for(Line line : TailLines) {
			if (line != null) {
		        line.moveLineTail(line.endX + dx, line.endY + dy);
		    }		
		}
	}
	
}
