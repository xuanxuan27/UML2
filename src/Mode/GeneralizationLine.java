package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shape.AssociationShape;
import Shape.GeneralizationShape;
import UML.UML;

public class GeneralizationLine extends Line implements ActionListener {

	
	public GeneralizationLine() {
		super(new GeneralizationShape(0,0,0,0));
	}
	
	public GeneralizationLine(int startX, int startY, int endX, int endY){
		super(startX, startY, endX, endY, new GeneralizationShape(0,0,0,0));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.GENERALIZATION);
	}
	
	@Override
	public void drawLine() {
		line = new GeneralizationShape(startX, startY, endX, endY);
        UML.drawPanel.add(line);
        UML.drawPanel.revalidate();
        UML.drawPanel.repaint();
	}
}
