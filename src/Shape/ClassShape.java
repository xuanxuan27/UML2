package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ClassShape extends Shape {
	private int width = 80; // 長方形寬度
    private int height = 90; // 長方形高度
    private int lineSpacing = 30; // 槓之間的間距
    
    public ClassShape(int x, int y) {
    	super(x, y, 80, 90); // x, y, width, height
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(2));
        
        x = (getWidth() - width) / 2; // 計算出長方形左上角 x 坐標
        y = (getHeight() - height) / 2; // 計算出長方形左上角 y 坐標
        
        // 繪製長方形
        g2d.drawRect(x, y, width-2, height-2);
        
        // 計算槓的個數
        int numLines = (height - lineSpacing) / lineSpacing;
        
        // 計算第一條槓的位置
        int startX = x;
        int startY = y + lineSpacing;
        
        // 繪製槓
        for (int i = 0; i < numLines; i++) {
            g2d.drawLine(startX, startY + i * lineSpacing, startX + width-2, startY + i * lineSpacing);
        }
        
        // 繪製端口
        for (Port port : ports) {
            port.paint(g2d);
        }
        
        
    }
}
