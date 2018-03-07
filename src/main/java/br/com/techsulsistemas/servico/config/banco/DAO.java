/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.techsulsistemas.servico.config.banco;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 *
 * @author kelvin
 */
public class DAO {

    private static EntityManagerFactory emf = configure("ForgePDVPU");
    private static final ThreadLocal<EntityManager> session = new ThreadLocal<>();

    protected DAO() {
    }

    public static EntityManagerFactory configure(String persistenceUnit) {
        Properties props = new Properties();
        props.put(PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://localhost:5432/forge_pdv");
        props.put(PersistenceUnitProperties.JDBC_USER, "kopp");
        props.put(PersistenceUnitProperties.JDBC_PASSWORD, "kopp");
        props.put(PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
        props.put(PersistenceUnitProperties.ALLOW_ZERO_ID, "true");

        props.put(PersistenceUnitProperties.LOGGING_LEVEL, "SEVERE");
        props.put(PersistenceUnitProperties.LOGGING_PARAMETERS, "true");
        props.put(PersistenceUnitProperties.CACHE_SIZE_DEFAULT, "0");
        props.put(PersistenceUnitProperties.CACHE_SHARED_DEFAULT, "false");
        props.put(PersistenceUnitProperties.CACHE_TYPE_DEFAULT, "NONE");
        
        return Persistence.createEntityManagerFactory(persistenceUnit, props);
    }

    public static EntityManager getEM() {
        EntityManager em = (EntityManager) DAO.session.get();
        if (em == null) {
            em = emf.createEntityManager();
            DAO.session.set(em);
        }
        return em;
    }

    public static EntityManager getNewEM() {
        return emf.createEntityManager();
    }

    public static void close() {
        if (DAO.session.get() == null) {
            return;
        }

        getEM().close();
        DAO.session.set(null);
    }

    public static void begin() {
        getEM().getTransaction().begin();
    }

    public static void commit() {
        getEM().getTransaction().commit();
        close();
    }

    public static void rollback() {
        try {
            if (getEM().getTransaction().isActive()) {
                getEM().getTransaction().rollback();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean isConnectionOk() {
        try {
            DAO.getNewEM().close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ex.printStackTrace();
            }
            return false;
        }
    }
}