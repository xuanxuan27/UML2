package UML;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Arrays;
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
    public static BasicObject selectedObj = null;
    public ArrayList<BasicObject> basicObjects;
    public ArrayList<BasicObject> includedObjs = new ArrayList<BasicObject>();
    
    DrawPanel(){
        basicObjects = new ArrayList<BasicObject>();
        setBackground(Color.black);
        addMouseHandlers();
    }

    private void addMouseHandlers() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handleMouseClicked(e);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });
        
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    private void handleMouseClicked(MouseEvent e) {
        mouseLocation = getMousePosition();
        clickX = e.getX();
        clickY = e.getY();

        if (Mode.getMode() == Mode.ModeType.CLASS) {
            basicObj = new ClassObject(clickX, clickY);
            basicObj.drawObject();
            basicObjects.add(basicObj);
        } else if (Mode.getMode() == Mode.ModeType.USECASE) {
            basicObj = new UsecaseObject(clickX, clickY);
            basicObj.drawObject();
            basicObjects.add(basicObj);
        } else if (Mode.getMode() == Mode.ModeType.SELECT) {
            if (getMousePosition() != null) {
                findSelectedObject();
            }
            if (selectedObj == null) {
                compositeObj = null;
                includedObjs.clear();
            }
        }
    }

    private void handleMousePressed(MouseEvent e) {
        mouseLocation = getMousePosition();
        startX = e.getX();
        startY = e.getY();
        if (Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {
            findHead();
            if (Head != null) {
                if (Mode.getMode() == Mode.ModeType.ASSOCIATION) {
                    line = new AssociationLine();
                } else if (Mode.getMode() == Mode.ModeType.GENERALIZATION) {
                    line = new GeneralizationLine();
                } else if (Mode.getMode() == Mode.ModeType.COMPOSITION) {
                    line = new CompositionLine();
                }
                Head.HeadLines.add(line);
                line.setStartPoint(startX, startY);
            }
        } else if (Mode.getMode() == Mode.ModeType.SELECT) {
            selectRegion = new Select();
            selectRegion.setStartPoint(startX, startY);
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        endX = e.getX();
        endY = e.getY();
        if (Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {
            findTail();
            if (line != null) {
                if (Tail != null) {
                    Tail.TailLines.add(line);
                    line.setEndPoint(endX, endY);
                    revalidate();
                    repaint();
                } else {
                    UML.drawPanel.remove(line.line);
                    revalidate();
                    repaint();
                }
                Head = null;
                Tail = null;
                line = null;
            }
        } else if (Mode.getMode() == Mode.ModeType.SELECT) {
            endX = e.getX();
            endY = e.getY();
            selectRegion.setEndPoint(endX, endY);

            if (selectRegion.shape != null) {
                selectIncludedObject();
                remove(selectRegion.shape);
            }
            revalidate();
            repaint();
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (Mode.getMode() == Mode.ModeType.ASSOCIATION || Mode.getMode() == Mode.ModeType.GENERALIZATION || Mode.getMode() == Mode.ModeType.COMPOSITION) {
            if (line != null) {
                endX = e.getX();
                endY = e.getY();
                line.setDragPoint(endX, endY);
                revalidate();
                repaint();
            }
        } else if (Mode.getMode() == Mode.ModeType.SELECT) {
            if (mouseLocation != null && selectedObj != null && compositeObj == null) {
                int dx = e.getX() - mouseLocation.x;
                int dy = e.getY() - mouseLocation.y;
                selectedObj.updatePosition(dx, dy);
                selectedObj.updateConnections(dx, dy);
                mouseLocation = e.getPoint();
                repaint();
            } else if (mouseLocation != null && selectedObj == null && includedObjs.isEmpty()) {
                endX = e.getX();
                endY = e.getY();
                selectRegion.setDragPoint(endX, endY);
                revalidate();
                repaint();
            } else if (mouseLocation != null && compositeObj != null) {
                int dx = e.getX() - mouseLocation.x;
                int dy = e.getY() - mouseLocation.y;
                selectedObj.updatePosition(dx, dy);
                selectedObj.updateConnections(dx, dy);
                mouseLocation = e.getPoint();
                repaint();
            }
        }
    }

    public void findHead() {
        for (BasicObject basicObj : basicObjects) {
            if (basicObj.isHead(startX, startY))
                break;
        }
    }

    public void findTail() {
        for (BasicObject basicObj : basicObjects) {
            if (basicObj.isTail(endX, endY) && basicObj != Head)
                break;
        }
    }
    
    public void selectIncludedObject() {
        if (selectRegion == null) return;

        for (BasicObject basicObj : basicObjects) {
            if (basicObj.shape == null) continue;

            Rectangle bounds = basicObj.shape.getBounds();
            Point[] corners = {
                    bounds.getLocation(),
                    new Point(bounds.x + bounds.width, bounds.y),
                    new Point(bounds.x, bounds.y + bounds.height),
                    new Point(bounds.x + bounds.width, bounds.y + bounds.height)
            };

            if (Arrays.stream(corners)
                    .map(corner -> SwingUtilities.convertPoint(basicObj.shape.getParent(), corner, selectRegion.shape))
                    .allMatch(selectRegion.shape::contains)) {
                if (!includedObjs.contains(basicObj)) includedObjs.add(basicObj);
                if (!basicObj.isSelect) basicObj.setSelect(true);
            }
        }
    }

    public void findSelectedObject() {
        for (BasicObject basicObj : basicObjects) {
            if (basicObj.shape != null && basicObj.shape.getBounds().contains(clickX, clickY)) {
                if (basicObj.isSelect == false) {
                    mouseLocation = getMousePosition();
                    selectedObj = basicObj;
                    basicObj.setSelect(true);
                    if (basicObj.getClass().getName() == "Mode.CompositeObject") {
                        compositeObj = (CompositeObject) basicObj;
                    }
                }
            } else {
                if (basicObj.isSelect == true) {
                	basicObj.setSelect(false);
                    selectedObj = null;
                }
            }
            revalidate();
            repaint();
        }
    }

    public BasicObject getSelectedObject() {
        return selectedObj;
    }
    
}
