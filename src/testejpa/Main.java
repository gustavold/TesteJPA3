/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testejpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import testejpa.TesteAssociacaoUsuarioGrupo;

/**
 *
 * @author gustavoduarte
 */
public class Main {

    public static EntityManagerFactory emf = Persistence.createEntityManagerFactory("TesteJPA3PU");

    public static void main(String[] args) {

        if (false) {
            TesteIntegridade ti = new TesteIntegridade(emf);
            ti.testaIntegridadeReferencial();
            return;
        }

        if(false){
            TesteAssociacaoUsuarioGrupo taug = new TesteAssociacaoUsuarioGrupo(emf);
            long createTime = taug.criaUsuarios(10);
            System.out.println("CriacaoJPA: " + 10 + " " + createTime);
            long deleteTime = taug.removeUsuarios();
            System.out.println("RemocaoJPA: " + 10 + " " + deleteTime);
            return;
        }

        //USUÁRIO
        TesteUsuario tu = new TesteUsuario(emf);
        int n = 1;
        for (int e = 1; e <= 5; e++) {
            n *= 10;
            long createTime = tu.criaUsuarios(n);
            System.out.println("CriacaoJPA: " + n + " " + createTime);
            
            long deleteTime = tu.removeUsuarios();
            System.out.println("RemocaoJPA: " + n + " " + deleteTime);
        }

    }
}
