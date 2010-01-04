/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testejpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import modelos.Usuario;

/**
 *
 * @author gustavoduarte
 */
public class TesteUsuario {
    EntityManagerFactory emf;

    public TesteUsuario(EntityManagerFactory emf){
        this.emf = emf;
    }


    public long criaUsuarios(int n) {
        // INSERE USUÁRIO

        long start = System.currentTimeMillis();

        for (int i = 1; i <= n; i++) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {

                tx.begin();

                Usuario usuario = new Usuario("usuario_" + i, "senha");
                em.persist(usuario);

                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
                em.close();
            }
        }
        return System.currentTimeMillis() - start;
    }

    public long removeUsuarios() {
        long start;

        List<Usuario> users = findAll();

        start = System.currentTimeMillis();

        for (Usuario u : users) {
            EntityManager em2 = emf.createEntityManager();
            EntityTransaction tx = em2.getTransaction();
            try {
                tx.begin();
                em2.remove(em2.merge(u));
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
                em2.close();
            }
        }
        return System.currentTimeMillis() - start;
    }

    public List<Usuario> findAll() {
        EntityManager em2 = emf.createEntityManager();
        List<Usuario> l = em2.createQuery("select u from Usuario u").getResultList();
        em2.close();
        return l;
    }
}
