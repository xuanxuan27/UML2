package Shape;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Shape extends JPanel {
	
	public ArrayList<Port> ports;
	protected int x, y, width, height;
    private JLabel label; 
    private String text = "Basic Object";
    
	public Shape(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		setPreferredSize(new Dimension(width, height)); // 设置组件大小
        setOpaque(false);
        setBounds(x, y, width, height);
        
        label = new JLabel(text); // 创建一个显示文本的标签
        label.setBounds(10, 10, 100, 20); // 设置标签的位置和大小
        label.setForeground(Color.white); // 设置字体颜色，默认为黑色
        add(label); // 将标签添加到面板中
        
        ports = new ArrayList<Port>(4);
        addPorts(); // 添加端口
	}
	
	private void addPorts() {
        int centerX = (width-2) / 2;
        int centerY = (height-2) / 2;

        ports.add(new Port(centerX, 0, this)); // top
        ports.add(new Port(centerX, height-2, this)); // bottom
        ports.add(new Port(0, centerY, this)); // left
        ports.add(new Port(width-2, centerY, this)); // right
    }
    
    public boolean isHead(int x, int y) {
        return getBounds().contains(x, y);
    }
    
    public boolean isTail(int x, int y) {
        return getBounds().contains(x, y);
    }
    
    public void setSelect() {
    	portVisible(ports);
    }
    
    public void setUnselect() {
    	portInvisible(ports);
    }
    
	public void updatePosition(int x, int y) {
        setLocation(x, y); // 更新物件位置
    }
	
	public void portVisible(ArrayList<Port> ports) {
		for(Port port: ports) {
			port.setVisible();
		}
	}
	
	public void portInvisible(ArrayList<Port> ports) {
		for(Port port: ports) {
			port.setInvisible();
		}
	}
	
	public void setText(String text) {
        this.text = text;
        label.setText(text); // 更新标签的文本内容
    }
    
    public String getText() {
        return text;
    }
}
