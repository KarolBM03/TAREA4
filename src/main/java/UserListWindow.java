import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserListWindow extends JFrame {
    private JList<String> userList;
    private JButton logoutButton;
    private JButton updateButton;
    private JButton deleteButton;
    private UsuarioManager usuarioManager;

    public UserListWindow() {
        usuarioManager = UsuarioManager.getInstance();
        setTitle("Lista de Usuarios");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        userList = new JList<>();
        logoutButton = new JButton("Cerrar Sesión");
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JScrollPane(userList));
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(logoutButton);

        add(panel);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginWindow().setVisible(true);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = userList.getSelectedValue();
                    String nombreUsuario = selectedValue.split(" - ")[0];  // Asumiendo que el nombre de usuario está en el primer campo
                    Usuario usuario = usuarioManager.buscarUsuario(nombreUsuario);
                    if (usuario != null) {
                        new UpdateUserWindow(usuario).setVisible(true);
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = userList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = userList.getSelectedValue();
                    String nombreUsuario = selectedValue.split(" - ")[0];
                    usuarioManager.eliminarUsuario(nombreUsuario);
                    updateUserList();
                }
            }
        });

        updateUserList();
    }

    public void updateUserList() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (Usuario usuario : usuarioManager.getUsuarios()) {
            listModel.addElement(usuario.getNombreUsuario() + " - " + usuario.getNombre() + " " + usuario.getApellido() + " - " + usuario.getTelefono() + " - " + usuario.getEmail());
        }
        userList.setModel(listModel);
    }
}
