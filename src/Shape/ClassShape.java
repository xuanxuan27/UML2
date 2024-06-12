package Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class ClassShape extends Shape {
	private int width = 80; // ����μe��
    private int height = 90; // ����ΰ���
    private int lineSpacing = 30; // �b���������Z
    
    public ClassShape(int x, int y) {
    	super(x, y, 80, 90); // x, y, width, height
    }
    
    @Override
    protected void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.gray);
        g2d.setStroke(new BasicStroke(2));
        
        x = (getWidth() - width) / 2; // �p��X����Υ��W�� x ����
        y = (getHeight() - height) / 2; // �p��X����Υ��W�� y ����
        
        // ø�s�����
        g2d.drawRect(x, y, width-2, height-2);
        
        // �p��b���Ӽ�
        int numLines = (height - lineSpacing) / lineSpacing;
        
        // �p��Ĥ@���b����m
        int startX = x;
        int startY = y + lineSpacing;
        
        // ø�s�b
        for (int i = 0; i < numLines; i++) {
            g2d.drawLine(startX, startY + i * lineSpacing, startX + width-2, startY + i * lineSpacing);
        }
        
        // ø�s�ݤf
        for (Port port : ports) {
            port.paint(g2d);
        }
        
        
    }
}
