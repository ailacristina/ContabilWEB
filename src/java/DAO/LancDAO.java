/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelo.Tablanc;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel
 */
public class LancDAO implements Serializable {

    public void lancar(Tablanc lancamento) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();

        // efetuar lançamento
        em.getTransaction().begin();
        em.persist(lancamento);
        em.getTransaction().commit();

        em.close();
    }

    public void estornar(Tablanc lanc) {

        EntityManager em = JPAUtil.getEMF().createEntityManager();

        // Remover os dados do banco
        em.getTransaction().begin();
        Tablanc lancamento = em.merge(lanc);
        em.remove(lancamento);
        em.getTransaction().commit();

        em.close();
    }

    public List<Tablanc> diario() {

        EntityManager em = JPAUtil.getEMF().createEntityManager();
        //EntityManager em = emf.createEntityManager();

        // lista todos os lançamentos
        TypedQuery<Tablanc> tqc = em.createNamedQuery(Tablanc.LISTAR_L, Tablanc.class);
        List<Tablanc> lanc = tqc.getResultList();

        //TypedQuery<Tabconta> tqc = em.createNamedQuery(Tabconta.LISTAR_T, Tabconta.class);
        //List<Tabconta> cont = tqc.getResultList();
        em.close();

        return lanc;

    }

    public void atualizar(Tablanc lancamento) {
        EntityManager em = JPAUtil.getEMF().createEntityManager();
        
        em.getTransaction().begin();
        em.merge(lancamento);
        em.getTransaction().commit();

        em.close();
    }
}
