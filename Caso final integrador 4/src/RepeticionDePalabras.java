import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RepeticionDePalabras extends JFrame {

    private JTextArea textArea;
    private JTextField searchField;
    private JFileChooser fileChooser;
    private JLabel wordCountLabel;
    private JLabel searchResultLabel;
    private JButton countButton;
    private JButton searchButton;

    public RepeticionDePalabras() {
        setTitle("Contador de Palabras");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        countButton = new JButton("Contar palabras");
        countButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        wordCountLabel = new JLabel("Conteo de palabras: 0");

        searchField = new JTextField(20);

        searchButton = new JButton("Buscar");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchWord();
            }
        });

        searchResultLabel = new JLabel("");

        JButton openButton = new JButton("Abrir");
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        buttonPanel.add(countButton);
        buttonPanel.add(wordCountLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(searchResultLabel);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void countWords() {
        String text = textArea.getText();
        int wordCount = text.split("\\s+").length;
        wordCountLabel.setText("Conteo de palabras: " + wordCount);
    }

    private void searchWord() {
        String text = textArea.getText().toLowerCase();
        String wordToSearch = searchField.getText().toLowerCase();
        int count = 0;
        int index = text.indexOf(wordToSearch);
        while (index != -1) {
            count++;
            text = text.substring(index + 1);
            index = text.indexOf(wordToSearch);
        }
        searchResultLabel.setText("Numero de veces repetida '" + wordToSearch + "': " + count);
    }

    private void openFile() {
        if (fileChooser == null) {
            fileChooser = new JFileChooser();
        }

        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de texto", "txt"));
        int option = fileChooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                StringBuilder content = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    content.append(line).append("\n");
                }
                textArea.setText(content.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al abrir el archivo: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RepeticionDePalabras();
            }
        });
    }
}
