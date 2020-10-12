package ru.smak.gui;

import ru.smak.gui.graphics.CartesianPainter;
import ru.smak.gui.graphics.components.ControlPanel;
import ru.smak.gui.graphics.components.GraphicsPanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public class MainWindow extends JFrame {
    GraphicsPanel mainPanel;
    ControlPanel controlPanel;

    static final Dimension MIN_SIZE = new Dimension(450, 350);
    static final Dimension MIN_FRAME_SIZE = new Dimension(600, 500);

    public MainWindow(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(MIN_FRAME_SIZE);
        setTitle("Полиномы");

        mainPanel = new GraphicsPanel();
        controlPanel = new ControlPanel();

        mainPanel.setBackground(Color.WHITE);
        mainPanel.addPainter(new CartesianPainter());
        controlPanel.setBorder(new EtchedBorder());

        GroupLayout gl = new GroupLayout(getContentPane());
        setLayout(gl);
        gl.setVerticalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addComponent(mainPanel, (int)(MIN_SIZE.height*0.8), MIN_SIZE.height, GroupLayout.DEFAULT_SIZE)
                .addGap(4)
                .addComponent(controlPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(4)
        );
        gl.setHorizontalGroup(gl.createSequentialGroup()
                .addGap(4)
                .addGroup(
                        gl.createParallelGroup()
                            .addComponent(mainPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                            .addComponent(controlPanel, MIN_SIZE.width, MIN_SIZE.width, GroupLayout.DEFAULT_SIZE)
                )
                .addGap(4)
        );
        pack();
    }
}
