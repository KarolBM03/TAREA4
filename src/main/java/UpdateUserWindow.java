import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateUserWindow extends JFrame {
    private JTextField userField;
    private JTextField nameField;
    private JTextField lastNameField;
    private JTextField phoneField;
    private JTextField emailField;
    private JPasswordField passField;
    private JButton updateButton;
    private Usuario usuario;
    private UsuarioManager usuarioManager;

    public UpdateUserWindow(Usuario usuario) {
        this.usuario = usuario;
        usuarioManager = UsuarioManager.getInstance();
        setTitle("Actualizar Usuario");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        userField = new JTextField(usuario.getNombreUsuario());
        nameField = new JTextField(usuario.getNombre());
        lastNameField = new JTextField(usuario.getApellido());
        phoneField = new JTextField(usuario.getTelefono());
        emailField = new JTextField(usuario.getEmail());
        passField = new JPasswordField(usuario.getContraseña());
        updateButton = new JButton("Actualizar");

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
        panel.add(updateButton);

        add(panel);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombreUsuario = userField.getText();
                String nombre = nameField.getText();
                String apellido = lastNameField.getText();
                String telefono = phoneField.getText();
                String email = emailField.getText();
                String contraseña = new String(passField.getPassword());

                if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios.");
                } else {
                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setNombre(nombre);
                    usuario.setApellido(apellido);
                    usuario.setTelefono(telefono);
                    usuario.setEmail(email);
                    usuario.setContraseña(contraseña);
                    usuarioManager.actualizarUsuario(usuario);
                    JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                    dispose();
                }
            }
        });
    }
}
