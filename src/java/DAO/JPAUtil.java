/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.Serializable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Daniel
 */
public class JPAUtil implements Serializable {
    private static EntityManagerFactory emf;
        
    public static EntityManagerFactory getEMF() {
    
        if (emf == null){
            emf = Persistence.createEntityManagerFactory("ContabilWebDanielPU");
        }
        return emf;
    }
}
