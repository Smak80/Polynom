package ru.smak.gui.graphics;

import java.awt.*;

public class CartesianPainter extends Painter{

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(10, 10, 300, 300);
        g.setColor(Color.RED);
        g.drawOval(10, 10, 300, 300);
    }
}
