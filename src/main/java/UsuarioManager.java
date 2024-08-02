import java.util.ArrayList;

public class UsuarioManager {
    private static UsuarioManager instance;
    private ArrayList<Usuario> usuarios;

    private UsuarioManager() {
        usuarios = new ArrayList<>();
    }

    public static synchronized UsuarioManager getInstance() {
        if (instance == null) {
            instance = new UsuarioManager();
        }
        return instance;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(String nombreUsuario) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombreUsuario().equals(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public void eliminarUsuario(String nombreUsuario) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        if (usuario != null) {
            usuarios.remove(usuario);
        }
    }

    public void actualizarUsuario(Usuario usuarioActualizado) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getNombreUsuario().equals(usuarioActualizado.getNombreUsuario())) {
                usuarios.set(i, usuarioActualizado);
                break;
            }
        }
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public boolean verificarLogin(String nombreUsuario, String contraseña) {
        Usuario usuario = buscarUsuario(nombreUsuario);
        return usuario != null && usuario.getContraseña().equals(contraseña);
    }
}
