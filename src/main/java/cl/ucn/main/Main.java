package cl.ucn.main;

import cl.ucn.interfaz.RecursosMultimediaInterface;
import cl.ucn.interfaz.UserFindInterface;
import cl.ucn.modelo.Usuario;
import cl.ucn.patterns.CsvAdapter;
import cl.ucn.patterns.DatabaseAdapter;
import cl.ucn.patterns.RecursosMultimediaProxy;
import jakarta.persistence.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("multimediaApp");
        EntityManager em = emf.createEntityManager();

        // Parte 1
        /**
        int rut = 89830291;
        String jpql = "SELECT u from Usuario u WHERE u.rut = :rut";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("rut", rut);
        try {
            Usuario usuario = query.getSingleResult();
            System.out.println("El usuario: " + usuario.getRut() + " existe!");
        }catch (NoResultException e){
            System.out.println("El usuario no existe!");
        }
         */

        // Parte 1 con adapter
        int rut = 89830291;
        UserFindInterface csvAdapter = new CsvAdapter();
        UserFindInterface databaseAdapter = new DatabaseAdapter();

        Usuario user = databaseAdapter.find_user(rut);
        if (user == null) {
            user = csvAdapter.find_user(rut);
        }

        if (user != null) {
            System.out.println("El usuario: " + user.getRut() + " existe!");
        }
        else {
            System.out.println("El usuario no existe!");
        }

        // Parte 2
        /**
        String jpql = "SELECT u from Usuario u";
        TypedQuery<Usuario> query1 = em.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = query1.getResultList();
        for (Usuario usuario : usuarios){

            System.out.println("Rut: " + usuario.getRut() + " Permiso: " + usuario.isTienePermiso() + " Archivo: " +
                    usuario.getRecursosMultimedia().getNombre() + " Protegido: " + usuario.getRecursosMultimedia().isProtegido());

        }

        em.close();
        */

        // Parte 2 con Proxy
        String jpql = "SELECT u from Usuario u";
        TypedQuery<Usuario> query1 = em.createQuery(jpql, Usuario.class);
        List<Usuario> usuarios = query1.getResultList();
        for (Usuario usuario : usuarios){
            // Uso del proxy
            RecursosMultimediaInterface recursosMultimedia = new RecursosMultimediaProxy(usuario.getRecursosMultimedia());
            System.out.print("Rut: " + usuario.getRut() + " ");

            recursosMultimedia.mostrar(usuario);
        }
        em.close();
    }
}
