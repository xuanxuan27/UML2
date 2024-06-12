package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shape.AssociationShape;
import UML.UML;

public class AssociationLine extends Line implements ActionListener {

	public AssociationLine() {
		super(new AssociationShape(0,0,0,0));
	}
	
	public AssociationLine(int startX, int startY, int endX, int endY){
		super(startX, startY, endX, endY, new AssociationShape(startX, startY, endX, endY));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.ASSOCIATION);
	}
	
	@Override
	public void drawLine() {
		line = new AssociationShape(startX, startY, endX, endY);
        UML.drawPanel.add(line);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
}
