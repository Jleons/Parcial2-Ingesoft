/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Sensor.*;
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
public class Sensors_DB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sensor_DBU");

        
    public static boolean create(Sensors sensors) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(sensors);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return true;
        }
    }

    public static Sensors read(Sensors sensors) {
        EntityManager em = emf.createEntityManager();
        Sensors sen = null;
        Query q = em.createQuery
        ("SELECT s FROM Sensors s " + "WHERE  s.operation_id LIKE :type" + " AND s.id LIKE :name")
        .setParameter("type", sensors.getType()).setParameter("name", sensors.getName());

        try {
            sen = (Sensors) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            sen = (Sensors) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return sen;
        }
    }
           
    public static boolean update(Sensors object, Sensors newObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;

        try {
            object = read(object);
            object.setName(newObject.getName());
            object.setName(newObject.getName());
            em.merge(object);
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

    public static boolean delete(Sensors sensors) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(sensors);
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
    
    public static Sensors search(int id) {
        EntityManager em = emf.createEntityManager();
        Sensors sensor = null;
        Query q = em.createQuery("SELECT s FROM Sensors s " + "WHERE s.id = :id").setParameter("id", id);

        try {
            sensor = (Sensors) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            sensor = (Sensors) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return sensor;
        }
    }
    
    public static boolean isAverage (int in){
        Sensors s = search(in); 
        return s.getAverage();
    }
    
}
