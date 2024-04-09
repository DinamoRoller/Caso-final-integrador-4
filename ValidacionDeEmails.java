import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacionDeEmails extends JFrame {

    private JTextField emailField;
    private JLabel validationLabel;

    public ValidacionDeEmails() {
        setTitle("Validacion de Emails");
        setSize(400, 150);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        emailField = new JTextField();
        emailField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                validateEmail();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                validateEmail();
            }
        });

        validationLabel = new JLabel();
        validationLabel.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(emailField, BorderLayout.NORTH);
        panel.add(validationLabel, BorderLayout.CENTER);

        add(panel);
    }

    private void validateEmail() {
        String email = emailField.getText().trim();
        boolean isValid = isValidEmail(email);
        if (isValid) {
            validationLabel.setText("Email Valido");
            validationLabel.setForeground(Color.GREEN);
        } else {
            validationLabel.setText("Email No Valido");
            validationLabel.setForeground(Color.RED);
        }
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ValidacionDeEmails();
            }
        });
    }
}
