/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testejpa3;

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
    public static EntityManager em = emf.createEntityManager();

    public static void main(String[] args) {

        //USUÁRIO
        int n = 1;
        for (int e = 1; e <= 4; e++) {
            n *= 10;
            long createTime = criaUsuarios(n);

            EntityManager em2 = emf.createEntityManager();
            Usuario u = (Usuario) em2.createQuery("select u from Usuario u").getResultList().get(0);
            //em.refresh(u);
            System.out.println(u.toString());
            System.out.println(u.getAUsuarioGrupoCollection());
            System.out.println("Acho que funcionou: " + u.getAUsuarioGrupoCollection().get(0).getGrupo().getNome());
            em2.close();

            long deleteTime = removeUsuarios();

            System.out.println("CriacaoJPA: " + n + " " + createTime);
            System.out.println("RemocaoJDO: " + n + " " + deleteTime);
        }
        // Fecha o gerenciador de Persistência
        em.close();
    }

    public static long criaUsuarios(int n) {
        // INSERE USUÁRIO
        EntityTransaction tx = em.getTransaction();
        long start;

        try {
            start = System.currentTimeMillis();
            tx.begin();

            Grupo grupo = new Grupo("grupo X");
            em.persist(grupo);

            for (int i = 1; i <= n; i++) {
                Usuario usuario = new Usuario("usuario_" + i, "senha");
                em.persist(usuario);

              // System.out.println("Criando aUsuarioGrupo com usuario.id = " + usuario.getId() + " e grupo.id = " + grupo.getId());
                AUsuarioGrupo aUsuarioGrupo = new AUsuarioGrupo(usuario.getId(), grupo.getId());
                em.persist(aUsuarioGrupo);
            }
            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("PAU!!!!");
            }
        }
        return System.currentTimeMillis() - start;
    }

    private static long removeUsuarios() {
        long start;
        EntityTransaction tx = em.getTransaction();
        start = System.currentTimeMillis();
        try {
            tx.begin();
            em.createQuery("delete from AUsuarioGrupo aug").executeUpdate();
            em.createQuery("delete from Usuario o").executeUpdate();
            em.createQuery("delete from Grupo g").executeUpdate();

            tx.commit();
        } finally {
            if (tx.isActive()) {
                tx.rollback();
                System.out.println("PAU!!!!");
            }
        }

        return System.currentTimeMillis() - start;
    }
}
