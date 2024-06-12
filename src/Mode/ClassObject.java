package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shape.ClassShape;

public class ClassObject extends BasicObject implements ActionListener {
	
	public ClassObject() {
		
	}
	
	public ClassObject(int x, int y){
		super(x, y, new ClassShape(x, y));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.CLASS);
	}
}
