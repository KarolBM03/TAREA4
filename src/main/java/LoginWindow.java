import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWindow extends JFrame {
    private JTextField userField;
    private JPasswordField passField;
    private JButton loginButton;
    private JButton registerButton;
    private UsuarioManager usuarioManager;

    public LoginWindow() {
        usuarioManager = UsuarioManager.getInstance();
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        userField = new JTextField();
        passField = new JPasswordField();
        loginButton = new JButton("Iniciar Sesión");
        registerButton = new JButton("Registrar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("Usuario:"));
        panel.add(userField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passField);
        panel.add(loginButton);
        panel.add(registerButton);

        add(panel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = userField.getText();
                String contraseña = new String(passField.getPassword());
                if (usuario.isEmpty() || contraseña.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar su usuario y contraseña.");
                } else {
                    if (usuarioManager.verificarLogin(usuario, contraseña)) {
                        dispose();
                        new UserListWindow().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterWindow().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new LoginWindow().setVisible(true);
    }
}
