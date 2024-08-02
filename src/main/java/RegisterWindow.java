import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterWindow extends JFrame {
    private JTextField userField;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passField;
    private JPasswordField confirmPassField;
    private JButton registerButton;
    private UsuarioManager usuarioManager;

    public RegisterWindow() {
        usuarioManager = UsuarioManager.getInstance();
        setTitle("Registro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        userField = new JTextField();
        nameField = new JTextField();
        lastNameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();
        passField = new JPasswordField();
        confirmPassField = new JPasswordField();
        registerButton = new JButton("Registrar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Nombre de Usuario:"));
        panel.add(userField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nameField);
        panel.add(new JLabel("Apellido:"));
        panel.add(lastNameField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(phoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passField);
        panel.add(new JLabel("Confirmar Contraseña:"));
        panel.add(confirmPassField);
        panel.add(registerButton);

        add(panel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = userField.getText();
                String nombre = nameField.getText();
                String apellido = lastNameField.getText();
                String telefono = phoneField.getText();
                String email = emailField.getText();
                String contraseña = new String(passField.getPassword());
                String confirmarContraseña = new String(confirmPassField.getPassword());

                if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                } else if (!contraseña.equals(confirmarContraseña)) {
                    JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden.");
                } else {
                    Usuario usuario = UsuarioFactory.crearUsuario(nombreUsuario, nombre, apellido, telefono, email, contraseña);
                    usuarioManager.agregarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente.");
                    dispose();
                }
            }
        });
    }
}
