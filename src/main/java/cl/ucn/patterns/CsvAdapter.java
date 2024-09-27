package cl.ucn.patterns;

import cl.ucn.interfaz.UserFindInterface;
import cl.ucn.modelo.Usuario;
import cl.ucn.util.Util;

import java.util.List;

public class CsvAdapter implements UserFindInterface {
    private Util util;

    public CsvAdapter() {
        this.util = new Util();
    }

    public Usuario find_user(int rut) {
        List<Usuario> usuarios = util.loadCsv();
        for (Usuario usuario : usuarios) {
            if (usuario.getRut() == rut) {
                return usuario;
            }
        }
        return null;
    }
}
