public class UsuarioFactory {
    public static Usuario crearUsuario(String nombreUsuario, String nombre, String apellido, String telefono, String email, String contraseña) {
        return new Usuario(nombreUsuario, nombre, apellido, telefono, email, contraseña);
    }
}
