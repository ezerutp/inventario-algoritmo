package appinventario.views;

import java.awt.Point;
import javax.swing.JFrame;

public class BaseMover extends JFrame {
    private int initialX;
    private int initialY;

    public BaseMover() {
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            @Override
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                formMouseDragged(evt);
            }
        });
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                formMousePressed(evt);
            }
        });
        this.setResizable(false);
        this.setUndecorated(true);
    }

    private void formMousePressed(java.awt.event.MouseEvent evt) {
        initialX = evt.getX();
        initialY = evt.getY();
    }

    private void formMouseDragged(java.awt.event.MouseEvent evt) {
        int deltaX = evt.getX() - initialX;
        int deltaY = evt.getY() - initialY;
        Point location = this.getLocation();
        this.setLocation((int) (location.getX() + deltaX), (int) (location.getY() + deltaY));
    }
}