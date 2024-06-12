package UML;
import java.awt.*;
import javax.swing.*;

import Mode.AssociationLine;
import Mode.BasicObject;
import Shape.ClassShape;
import Action.*;

public class UML {
    public static DrawPanel drawPanel;
	
	public static void main(String[] args) { 
        JFrame window = new JFrame("UML Editor");
        window.setSize(800, 600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel basePanel = new JPanel();
        basePanel.setBackground(Color.black);
        JPanel buttonPanel = new JPanel();
        drawPanel = new DrawPanel();
        drawPanel.setLayout(null);
        window.add(basePanel);
        
        basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.X_AXIS));
        basePanel.add(buttonPanel);
        basePanel.add(drawPanel);
         
        ModeButton selectBtn = new ModeButton("select", "SelectIcon.png");
        selectBtn.addActionListener(new Mode.Select());
        
        ModeButton associationLineBtn = new ModeButton("association", "AssociationLineIcon2.png");
        associationLineBtn.addActionListener(new Mode.AssociationLine());
        
        ModeButton generalizationLineBtn = new ModeButton("generalization", "GenerationLineIcon.png");
        generalizationLineBtn.addActionListener(new Mode.GeneralizationLine());
        
        ModeButton compositionLineBtn = new ModeButton("composition", "CompositionLineIcon.png");
        compositionLineBtn.addActionListener(new Mode.CompositionLine());
        
        ModeButton classBtn = new ModeButton("class", "ClassIcon.png");
        classBtn.addActionListener(new Mode.ClassObject());

        ModeButton usecaseBtn = new ModeButton("usecase", "UseCaseIcon.png");
        usecaseBtn.addActionListener(new Mode.UsecaseObject());
        
        // Add buttons to basePanel
        buttonPanel.setBackground(Color.black);

        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(selectBtn);
        buttonPanel.add(associationLineBtn);
        buttonPanel.add(generalizationLineBtn);
        buttonPanel.add(compositionLineBtn);
        buttonPanel.add(classBtn);
        buttonPanel.add(usecaseBtn);
        
        JMenuBar toolBar = new JMenuBar();
        toolBar.setFocusable(false); 

        // 创建按钮并添加到工具栏
        JMenu fileMenu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New File");
        newMenuItem.addActionListener(new NewFile());
        fileMenu.add(newMenuItem);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem renameMenuItem = new JMenuItem("Rename Object");
        // BasicObject selectedObject = drawPanel.getSelectedObject();
        renameMenuItem.addActionListener(new Rename());
        
        JMenuItem groupMenuItem = new JMenuItem("Group");
        groupMenuItem.addActionListener(new Group());
        JMenuItem upgroupMenuItem = new JMenuItem("Ungroup");
        upgroupMenuItem.addActionListener(new Ungroup());
        
        editMenu.add(renameMenuItem);
        editMenu.add(groupMenuItem);
        editMenu.add(upgroupMenuItem);

        toolBar.add(fileMenu);
        toolBar.add(editMenu);

        // 添加工具栏到主窗口
        window.getContentPane().add(toolBar, BorderLayout.PAGE_START);
         
        window.setVisible(true);
    }

}
