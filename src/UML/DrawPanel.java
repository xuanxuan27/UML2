package UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import Mode.*;
import Shape.Port;

public class DrawPanel extends JPanel {
	public BasicObject basicObj;
	public Line line;
	public CompositeObject compositeObj;
	public Select selectRegion;
	public int clickX, clickY;
	public Point mouseLocation;
    public static int startX, startY, endX, endY;
    public static BasicObject Head = null, Tail = null;
    // public static List<BasicObject> selectedObjs = null;
    public static BasicObject selectedObj = null;
    public ArrayList<BasicObject> basicObjects;
    public ArrayList<BasicObject> includedObjs = new ArrayList<BasicObject>();
    
	DrawPanel(){
		basicObjects = new ArrayList<BasicObject>();
		// selectedObjs = new ArrayList<BasicObject>();
		setBackground(Color.black);
		
		addMouseListener(new MouseAdapter() {
		   		    
            @Override
            public void mouseClicked(MouseEvent e) {
            	mouseLocation = getMousePosition();
            	clickX = e.getX();
            	clickY = e.getY();
        
            	if (Mode.getMode() == Mode.ModeType.CLASS) {
            		//if(basicObj == null)
            		basicObj = new ClassObject(clickX, clickY);
            		basicObj.drawObject();
            		basicObjects.add(basicObj);
            	}
            	else if (Mode.getMode() == Mode.ModeType.USECASE) {
            		basicObj = new UsecaseObject(clickX, clickY);
            		basicObj.drawObject();
            		basicObjects.add(basicObj);
            	}
            	else if (Mode.getMode() == Mode.ModeType.SELECT) {
            		if (getMousePosition() != null) {
                        findSelectedObject();
                    }
            		if(selectedObj == null) {
            			compositeObj = null;
            			includedObjs.clear();
            		}
            	}
            }
            @Override
            public void mousePressed(MouseEvent e) {
            	mouseLocation = getMousePosition();
                startX = e.getX();
                startY = e.getY();
                if(Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {
                	findHead();
                    if(Head != null) {
                    	if (Mode.getMode() == Mode.ModeType.ASSOCIATION) {
                    		line = new AssociationLine();
                    	}
                        else if (Mode.getMode() == Mode.ModeType.GENERALIZATION) {
                    		line = new GeneralizationLine();
                    	}
                        else if (Mode.getMode() == Mode.ModeType.COMPOSITION) {
                    		line = new CompositionLine();
                    	}
                    	Head.HeadLines.add(line);
                    	line.setStartPoint(startX, startY);
                    }
                }    
                else if(Mode.getMode() == Mode.ModeType.SELECT) {
                	selectRegion = new Select();
                	selectRegion.setStartPoint(startX, startY);
                }
            }


            @Override
            public void mouseReleased(MouseEvent e) {
                endX = e.getX();
                endY = e.getY();
                if(Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {
                	findTail();
                    if(line != null) {
                    	if(Tail != null) {
                    		Tail.TailLines.add(line);
                        	line.setEndPoint(endX, endY);
                        	revalidate();
                        	repaint();
                        }
                        else {
                        	UML.drawPanel.remove(line.line);
                        	revalidate();
                        	repaint();
                        }
                        Head = null;
                    	Tail = null;
                    	line = null;
                    }
                }
                else if(Mode.getMode() == Mode.ModeType.SELECT) {
                	endX = e.getX();
                    endY = e.getY();
                    selectRegion.setEndPoint(endX, endY);

                    if(selectRegion.shape != null) {
                    	selectIncludedObject();
                    	remove(selectRegion.shape);
                    }
                    revalidate();
                	repaint();
                }
            }
            
        });
		
		addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
            	if(Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {

            		if (line != null) {
                        endX = e.getX();
                        endY = e.getY();
                        line.setDragPoint(endX, endY);
                        revalidate();
                        repaint();
                    }
            	}
            	else if(Mode.getMode() == Mode.ModeType.SELECT) {
            		// 移動單一物件
            		if (mouseLocation != null && selectedObj != null && compositeObj == null) {
        				int dx = e.getX() - mouseLocation.x;
                        int dy = e.getY() - mouseLocation.y;
                        selectedObj.updatePosition(dx, dy); // 更新物件位置
                        selectedObj.updateConnections(dx, dy); // 更新連線的位置
                        mouseLocation = e.getPoint(); // 更新鼠标位置
                        repaint(); // 重新绘制面板
            			
                    }
            		// 畫選取框
            		else if (mouseLocation != null && selectedObj == null && includedObjs.isEmpty()) {
            			endX = e.getX();
                        endY = e.getY();
                        selectRegion.setDragPoint(endX, endY);
                        revalidate();
                        repaint();
            		}
            		// 移動 group 物件
            		else if(mouseLocation != null && compositeObj != null) {
            			int dx = e.getX() - mouseLocation.x;
                        int dy = e.getY() - mouseLocation.y;
                        // selectedObj.updatePosition(selectedObj.shape.getX() + dx, selectedObj.shape.getY() + dy); // 更新物件位置
                        selectedObj.updatePosition(dx, dy);
                        selectedObj.updateConnections(dx, dy); // 更新連線的位置
                        mouseLocation = e.getPoint(); // 更新鼠标位置
                        repaint(); // 重新绘制面板
            		}
            	}
            }
        });
    	
	}
	
	public void findHead() {
		for(BasicObject basicObj : basicObjects) {
			if(basicObj.isHead(startX, startY))
				break;
		}
	}
	
	public void findTail() {
		for(BasicObject basicObj : basicObjects) {
			if(basicObj.isTail(endX, endY) && basicObj != Head)
				break;
		}
	}
	public void findSelectedObject() {
		for(BasicObject basicObj : basicObjects) {
			if(basicObj.shape != null && basicObj.shape.getBounds().contains(clickX, clickY)) {
				if(basicObj.isSelect == false) {
					mouseLocation = getMousePosition();
					// if(basicObj.getClass().getName() == "Mode.CompositeObject")
					selectedObj = basicObj;
					basicObj.setSelected();
					if(basicObj.getClass().getName() == "Mode.CompositeObject") {
						compositeObj = (CompositeObject) basicObj;
					}
				}
			}
			else {
				if(basicObj.isSelect == true) {
					basicObj.unSelected();
					selectedObj = null;
					// compositeObj = null;
				}
			}
			revalidate();
			repaint();
		}
	}
	
	public BasicObject getSelectedObject() {
		return selectedObj;
	}
	
	public void selectIncludedObject() {

		if (selectRegion != null) {
	        for (BasicObject basicObj : basicObjects) {
	        	if(basicObj.shape != null) {
	        		Rectangle bounds = basicObj.shape.getBounds();
		            // 獲取矩形的四個角點
		            Point upperLeft = bounds.getLocation();
		            Point upperRight = new Point(bounds.x + bounds.width, bounds.y);
		            Point lowerLeft = new Point(bounds.x, bounds.y + bounds.height);
		            Point lowerRight = new Point(bounds.x + bounds.width, bounds.y + bounds.height);

		            // 將矩形的四個角點坐標轉換
		            Point convertedUpperLeft = SwingUtilities.convertPoint(basicObj.shape.getParent(), upperLeft, selectRegion.shape);
		            Point convertedUpperRight = SwingUtilities.convertPoint(basicObj.shape.getParent(), upperRight, selectRegion.shape);
		            Point convertedLowerLeft = SwingUtilities.convertPoint(basicObj.shape.getParent(), lowerLeft, selectRegion.shape);
		            Point convertedLowerRight = SwingUtilities.convertPoint(basicObj.shape.getParent(), lowerRight, selectRegion.shape);
		          
		            // 檢查轉換後的四個角點是否都在 compositeObj 的形狀範圍內
		            if (selectRegion.shape.contains(convertedUpperLeft) &&
		            	selectRegion.shape.contains(convertedUpperRight) &&
		            	selectRegion.shape.contains(convertedLowerLeft) &&
		            	selectRegion.shape.contains(convertedLowerRight)) {
		                // 檢查 basicObj 是否已經在 includedObjs 中
		                if (!includedObjs.contains(basicObj)) {
		                    includedObjs.add(basicObj);
		                }
		                // 檢查 basicObj 是否已經被選擇
		                if (!basicObj.isSelect) {
		                    basicObj.setSelected();
		                }
		            }
	        	}
	            
	        }
	    }
	}
}
