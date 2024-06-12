package Action;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Mode.BasicObject;
import UML.UML;

public class Ungroup implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		for(BasicObject obj : UML.drawPanel.compositeObj.includedObjects) {
			UML.drawPanel.basicObjects.add(obj);
		}
		UML.drawPanel.compositeObj.includedObjects.clear();
		UML.drawPanel.remove(UML.drawPanel.compositeObj.shape);
		//UML.drawPanel.compositeObj = null;
		UML.drawPanel.revalidate();
		UML.drawPanel.repaint();
    }

}
