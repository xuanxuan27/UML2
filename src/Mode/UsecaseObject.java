package Mode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Shape.UsecaseShape;

public class UsecaseObject extends BasicObject implements ActionListener {
	
	public UsecaseObject() {
		
	}
	
	public UsecaseObject(int x, int y){
		super(x, y, new UsecaseShape(x, y));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Mode.setMode(Mode.ModeType.USECASE);
	}
	
	
}
