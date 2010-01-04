/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testejpa;

import java.util.Date;
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
public class TesteIntegridade {
    EntityManagerFactory emf;

    public TesteIntegridade(EntityManagerFactory emf){
        this.emf = emf;
    }

   public void testaIntegridadeReferencial() {
        EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            try {

                tx.begin();

                Usuario u = new Usuario("usuario_X", "senha");
                em.persist(u);
                Grupo g = new Grupo("Grupo_X");
                em.persist(g);
                AUsuarioGrupo aug = new AUsuarioGrupo(u.getId(), g.getId());
                aug.setDataInicio(new Date());
                em.persist(aug);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
            }

           em.clear();
            try {
                tx.begin();
                Usuario u = (Usuario)((List)em.createQuery("select u from Usuario u").getResultList()).get(0);
                em.remove(u);
                tx.commit();
            } finally {
                if (tx.isActive()) {
                    tx.rollback();
                    System.out.println("PAU!!!!");
                }
                em.close();
            }
    }
}
