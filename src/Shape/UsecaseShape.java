package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class UsecaseShape extends Shape {
	private int width = 80; // 设置类图形的默认大小
    private int height = 60;
    
    public UsecaseShape(int x, int y) {
    	super(x, y, 80, 60);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(2));
        
        int x = (getWidth() - width) / 2; // 根据组件大小计算出 x 坐标
        int y = (getHeight() - height) / 2; // 根据组件大小计算出 y 坐标
        g2d.drawOval(x, y, width, height); // 使用 fillOval 方法填充圆形
        
        // 繪製端口
        for (Port port : ports) {
            port.paint(g2d);
        }
    }
}
