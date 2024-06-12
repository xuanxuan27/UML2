package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import UML.UML;;
public class NewFile implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		UML.drawPanel.removeAll();
		UML.drawPanel.revalidate();
		UML.drawPanel.repaint();
	}
}
