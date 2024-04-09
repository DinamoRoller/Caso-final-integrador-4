import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AppParaDibujar extends JFrame {

    private DrawingPanel drawingPanel;

    public AppParaDibujar() {
        setTitle("Paint 2.0");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        drawingPanel = new DrawingPanel();
        add(drawingPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppParaDibujar();
            }
        });
    }
}

class DrawingPanel extends JPanel {

    private int lastX, lastY;

    public DrawingPanel() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                Graphics g = getGraphics();
                g.setColor(Color.BLACK);
                g.drawLine(lastX, lastY, x, y);
                lastX = x;
                lastY = y;
            }
        });
    }
}
