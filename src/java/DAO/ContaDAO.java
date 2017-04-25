/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Tabconta;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel
 */
public class ContaDAO implements Serializable {

    public void salvar(Tabconta conta) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        // Salvar os dados no banco
        em.getTransaction().begin();
        em.persist(conta);
        em.getTransaction().commit();

        em.close();
    }

    public void remover(Tabconta idConta) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        // Remover os dados do banco
        em.getTransaction().begin();
        Tabconta conta = em.merge(idConta);
        em.remove(conta);
        em.getTransaction().commit();

        em.close();
    }

    public Tabconta filtrarCt(Integer idCt) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        Tabconta conta = em.find(Tabconta.class, idCt);

        em.close();
        return conta;
    }

    public List<Tabconta> listar() {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        // Listar dados
        TypedQuery<Tabconta> tqc = em.createNamedQuery(Tabconta.LISTAR_T, Tabconta.class);
        List<Tabconta> cont = tqc.getResultList();

        em.close();

        return cont;
    }
    
    public void atualizar(Tabconta conta) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        
        em.getTransaction().begin();
        em.merge(conta);
        em.getTransaction().commit();

        em.close();
    }

}
