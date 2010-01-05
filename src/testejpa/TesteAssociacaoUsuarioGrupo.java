/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testejpa;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import modelos.AUsuarioGrupo;
import modelos.Grupo;
import modelos.Usuario;

/**
 *
 * @author gustavoduarte
 */
public class TesteAssociacaoUsuarioGrupo {

    private EntityManagerFactory emf;

    public TesteAssociacaoUsuarioGrupo(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public long criaUsuarios(int n) {

        List<Grupo> grupos = findGrupos();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= n; i++) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();

                Usuario usuario = new Usuario("usuario_" + i, "senha");
                em.persist(usuario);
                List<AUsuarioGrupo> aug = new ArrayList<AUsuarioGrupo>();
                for (Grupo grupo : grupos) {
                    //aug.add(new AUsuarioGrupo(usuario.getId(), grupo.getId()));
                    em.persist(new AUsuarioGrupo(usuario.getId(), grupo.getId()));
                }

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
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {
                tx.begin();
                //TODO: o uso do merge tem impacto na performance?
                u = em.merge(u);
                //System.out.println("GGGGGGGG: " + u.getAUsuarioGrupoCollection());
                //em.refresh(u);
                em.remove(u);
               // em.flush();
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

    public List<Usuario> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Usuario> l = em.createQuery("select u from Usuario u").getResultList();
        em.close();
        return l;
    }

    public List<Grupo> findGrupos() {
        EntityManager em = emf.createEntityManager();
        List<Grupo> l = em.createQuery("select g from Grupo g").getResultList();
        em.close();
        return l;
    }
}
