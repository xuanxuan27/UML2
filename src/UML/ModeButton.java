package UML;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ModeButton extends JButton{
    private static ModeButton selectedButton = null;
	private String mode;
	private int size = 40;
	//ModeButton(String mode, String iconPath){
	ModeButton(String mode, String iconPath){
		super(); 
		this.mode = mode;
        initButton(iconPath, size);
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if (selectedButton != null && selectedButton != ModeButton.this) {
                    selectedButton.setSelected(false);
                }
                
            	setSelected(true);
            }
        });
	}
	
	private void initButton(String iconPath, int size) {
        iconPath = "Resources/" + iconPath;
        ImageIcon icon = new ImageIcon(iconPath);

        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaledImg));
        setPreferredSize(new Dimension(size, size));
        setBackground(Color.black);
        
        if(this.mode == "select") {
        	setSelected(true);
        }
    }
	
	public void setSelected(boolean selected) {
        if (selected) {
            setBackground(Color.gray);
            selectedButton = this;
        } else {
            setBackground(Color.black);
            selectedButton = null;
        }
    }
    
    public boolean isSelected() {
        return getBackground().equals(Color.gray);
    }
    
}
