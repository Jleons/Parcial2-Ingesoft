/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Sensor.Init;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author LENOVO
 */
public class Init_DB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sensor_DBU");

    public Init_DB() {
    }

    
    public static boolean create(Init init) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(init);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return true;
        }
    }
    
    public static Init read(Init init) {
        EntityManager em = emf.createEntityManager();
        Init re = null;
        Query q = em.createQuery
        ("SELECT t FROM Init t " + "WHERE  t.operation_id LIKE :install_id" + " AND t.install_id LIKE :id")
        .setParameter("install_id", init.getInstall_id()).setParameter("id", init.getId());

        try {
            re = (Init) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            re = (Init) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return re;
        }
    }

    
    public static boolean update(Init s, Init new_s) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean done = false;

        try {
            s = read(s);
            s = (Init) new_s.clone();
            em.merge(s);
            em.getTransaction().commit();
            done = true;

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return done;
        }
    }
    
    public static boolean delete(Init init) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(init);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return ret;
        }
    }
    
    public static Init searchInit(int id_i) {
        EntityManager em = emf.createEntityManager();
        Init done = null;
        Query q = em.createQuery("SELECT i FROM Init i " + "WHERE i.install_id = :install_id")
                .setParameter("install_id", id_i);

        try {
            done = (Init) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            done = (Init) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return done;
        }
    }
}
