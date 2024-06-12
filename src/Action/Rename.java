package Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import Mode.BasicObject;
import UML.*;

public class Rename implements ActionListener{

	public void actionPerformed(ActionEvent e) {
		String newName = JOptionPane.showInputDialog(null, "Enter new name:");
		
        if (newName != null && !newName.isEmpty()) {
        	        	
            if (UML.drawPanel.selectedObj != null ) {
        		System.out.println(newName);

        		UML.drawPanel.selectedObj.shape.setText(newName);
                UML.drawPanel.revalidate();
                UML.drawPanel.repaint();
            }
//            else if (UML.drawPanel.selectedObj != null && UML.drawPanel.compositeObj != null) {
//        		System.out.println(newName);
//
//        		for(BasicObject obj : UML.drawPanel.compositeObj.includedObjects) {
//        			obj.shape.setText(newName);
//        		}
//                UML.drawPanel.revalidate();
//                UML.drawPanel.repaint();
//            }
        }
    }

}
