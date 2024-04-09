import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditorDeDocumentosMultiples extends JFrame {

    private JDesktopPane desktopPane;
    private int documentCount = 1;

    public EditorDeDocumentosMultiples() {
        setTitle("Editor de documentos multiples");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        desktopPane = new JDesktopPane();
        setContentPane(desktopPane);

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

    private void createNewDocument() {
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        JInternalFrame internalFrame = new JInternalFrame("Documento " + documentCount++, true, true, true, true);
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
                new EditorDeDocumentosMultiples();
            }
        });
    }
}
