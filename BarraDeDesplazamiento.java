import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BarraDeDesplazamiento extends JFrame {

    private JDesktopPane desktopPane;
    private JLabel mousePositionLabel;
    private JScrollBar scrollBar;

    public BarraDeDesplazamiento() {
        setTitle("Editor");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        desktopPane = new JDesktopPane();
        desktopPane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updateMousePosition(e.getPoint());
            }
        });
        setContentPane(desktopPane);

        mousePositionLabel = new JLabel("Posicion del raton: ");
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.add(mousePositionLabel, BorderLayout.WEST);
        add(statusPanel, BorderLayout.SOUTH);

        scrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 0, 0, 0, 100);
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                // Aquí puedes realizar alguna acción basada en el valor de la barra de desplazamiento
                int value = scrollBar.getValue();
                System.out.println("Valor de desplazamiento cambiado: " + value);
            }
        });
        add(scrollBar, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Archivo");
        JMenuItem newMenuItem = new JMenuItem("Nuevo");
        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewDocument();
            }
        });
        fileMenu.add(newMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private void updateMousePosition(Point point) {
        mousePositionLabel.setText("Posicion del raton: (" + point.x + ", " + point.y + ")");
    }

    private void createNewDocument() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JInternalFrame internalFrame = new JInternalFrame("Documento", true, true, true, true);
        internalFrame.setLayout(new BorderLayout());
        internalFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        internalFrame.setSize(300, 200);
        internalFrame.setVisible(true);

        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BarraDeDesplazamiento();
            }
        });
    }
}
