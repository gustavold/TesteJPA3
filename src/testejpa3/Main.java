/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testejpa3;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import testejpa3.AUsuarioGrupo;

/**
 *
 * @author gustavoduarte
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TesteJPA3PU");
  //  public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        if (true) {
            testaIntegridadeReferencial();
            return;
        }

        //USUÁRIO
        int n = 1;
        for (int e = 1; e <= 5; e++) {
            n *= 10;
            long createTime = criaUsuarios(n);
            System.out.println("CriacaoJPA: " + n + " " + createTime);
            
            long deleteTime = removeUsuarios();
            System.out.println("RemocaoJPA: " + n + " " + deleteTime);
        }
        // Fecha o gerenciador de Persistência
      //  em.close();
    }

    public static long criaUsuarios(int n) {
        // INSERE USUÁRIO

        long start = System.currentTimeMillis();

        for (int i = 1; i <= n; i++) {
            EntityManager em2 = emf.createEntityManager();
            EntityTransaction tx = em2.getTransaction();
            try {

                tx.begin();

                Usuario usuario = new Usuario("usuario_" + i, "senha");
                em2.persist(usuario);

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

    private static long removeUsuarios() {
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

    public static List<Usuario> findAll() {
        //TODO: aqui eu tive que usar outro entityManager, mas no JDO estou usando o mesmo.
        //      Isso influencia no resultado?
        EntityManager em2 = emf.createEntityManager();
        List<Usuario> l = em2.createQuery("select u from Usuario u").getResultList();
        //em.refresh(u);
        // System.out.println(u.toString());
        // System.out.println(u.getAUsuarioGrupoCollection());
        // System.out.println("Acho que funcionou: " + u.getAUsuarioGrupoCollection().get(0).getGrupo().getNome());
        em2.close();
        return l;
    }

    private static void testaIntegridadeReferencial() {
        EntityManager em2 = emf.createEntityManager();
            EntityTransaction tx = em2.getTransaction();
            try {

                tx.begin();

                Usuario u = new Usuario("usuario_X", "senha");
                em2.persist(u);
                Grupo g = new Grupo("Grupo_X");
                em2.persist(g);
                AUsuarioGrupo aug = new AUsuarioGrupo(u.getId(), g.getId());
                aug.setDataInicio(new Date());
                em2.persist(aug);
                em2.flush();
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
            }
            
            /////////////////////////////////////////////////////
            if (false) return;
            em2.clear();
            try {
                tx.begin();
                Usuario u = (Usuario)((List)em2.createQuery("select u from Usuario u").getResultList()).get(0);
                System.out.println("GGGGG: "+u.getAUsuarioGrupoCollection().toString());
                //u.setAUsuarioGrupoCollection(null);
                //em2.merge(u);
                em2.refresh(u);
                em2.remove(u);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
                em2.close();
            }

                            

    }
}
