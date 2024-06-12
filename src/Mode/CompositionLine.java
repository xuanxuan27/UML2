package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shape.AssociationShape;
import Shape.CompositionShape;
import UML.UML;

public class CompositionLine extends Line implements ActionListener {

	
	public CompositionLine() {
		super(new CompositionShape(0,0,0,0));
	}
	
	public CompositionLine(int startX, int startY, int endX, int endY){
		super(startX, startY, endX, endY, new CompositionShape(startX,startY,endX,endY));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.COMPOSITION);
	}
	
	@Override
	public void drawLine() {
		line = new CompositionShape(startX, startY, endX, endY);
        UML.drawPanel.add(line);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
}
