import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GestorDeContactos extends JFrame {

    private List<Contacto> contacts;
    private JList<Contacto> contactsList;
    private DefaultListModel<Contacto> contactsListModel;

    public GestorDeContactos() {
        setTitle("Contacts Manager");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        contacts = new ArrayList<>();
        contactsListModel = new DefaultListModel<>();
        contactsList = new JList<>(contactsListModel);
        JScrollPane scrollPane = new JScrollPane(contactsList);

        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addContact();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addContact() {
        String name = JOptionPane.showInputDialog(this, "Enter name:");
        String email = JOptionPane.showInputDialog(this, "Enter email:");
        String phoneNumber = JOptionPane.showInputDialog(this, "Enter phone number:");

        Contacto contact = new Contacto(name, email, phoneNumber);
        contacts.add(contact);
        contactsListModel.addElement(contact);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GestorDeContactos();
            }
        });
    }
}
