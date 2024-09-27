package cl.ucn.patterns;

import cl.ucn.interfaz.UserFindInterface;
import cl.ucn.modelo.Usuario;
import jakarta.persistence.*;

public class DatabaseAdapter implements UserFindInterface {
    private EntityManager em;

    public DatabaseAdapter() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("multimediaApp");
        this.em = emf.createEntityManager();
    }

    public Usuario find_user(int rut) {
        String jpql = "SELECT u from Usuario u WHERE u.rut = :rut";
        TypedQuery<Usuario> query = em.createQuery(jpql, Usuario.class);
        query.setParameter("rut", rut);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
