import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ComparadorDeArchivos extends JFrame {
    private JButton selectFile1Btn, selectFile2Btn, compareBtn;
    private JTextArea resultArea;
    private File file1, file2;

    public ComparadorDeArchivos() {
        setTitle("Comparador de Archivos");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        selectFile1Btn = new JButton("Seleccione el archivo 1");
        selectFile2Btn = new JButton("Seleccione el archivo 2");
        compareBtn = new JButton("Comparar");
        resultArea = new JTextArea(10, 40);

        JPanel panel = new JPanel();
        panel.add(selectFile1Btn);
        panel.add(selectFile2Btn);
        panel.add(compareBtn);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        selectFile1Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFile(1);
            }
        });

        selectFile2Btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectFile(2);
            }
        });

        compareBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                compareFiles();
            }
        });
    }

    private void selectFile(int fileNumber) {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            if (fileNumber == 1) {
                file1 = selectedFile;
            } else if (fileNumber == 2) {
                file2 = selectedFile;
            }
        }
    }

    private void compareFiles() {
        if (file1 == null || file2 == null) {
            resultArea.setText("seleccione 2 archivos para comparar.");
            return;
        }

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            int lineNumber = 1;
            boolean areEqual = true;

            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    resultArea.append("Los archivos son distintos en la linea " + lineNumber + "\n");
                    areEqual = false;
                }

                line1 = reader1.readLine();
                line2 = reader2.readLine();
                lineNumber++;
            }

            if (areEqual) {
                resultArea.append("Los archivos son iguales\n");
            }

        } catch (IOException e) {
            resultArea.setText("Error al leer el archivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComparadorDeArchivos().setVisible(true);
            }
        });
    }
}
