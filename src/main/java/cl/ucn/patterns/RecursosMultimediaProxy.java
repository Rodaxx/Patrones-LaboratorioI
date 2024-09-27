package cl.ucn.patterns;

import cl.ucn.interfaz.RecursosMultimediaInterface;
import cl.ucn.modelo.RecursosMultimedia;
import cl.ucn.modelo.Usuario;

public class RecursosMultimediaProxy implements RecursosMultimediaInterface {

    private RecursosMultimedia recursoReal;

    public RecursosMultimediaProxy(RecursosMultimedia recursoReal) {
        this.recursoReal = recursoReal;
    }

    // El proxy controla el acceso y luego llama al recurso real
    @Override
    public void mostrar(Usuario usuario) {
        if (recursoReal.isProtegido()) {
            if (usuario.tienePermiso()) {
                recursoReal.mostrar(usuario);
            } else {
                System.out.println("Acceso denegado, no tiene permiso para acceder al recurso protegido.");
            }
        } else {
            recursoReal.mostrar(usuario);
        }
    }
}
