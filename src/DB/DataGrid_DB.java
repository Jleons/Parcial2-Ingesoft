/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Sensor.DataGrid;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author LENOVO
 */
public class DataGrid_DB {
    
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Sensor_DBU");

    public static boolean create(DataGrid data) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(data);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
            return true;
        }
    }

    public static DataGrid read(DataGrid data) {
        EntityManager em = emf.createEntityManager();
        DataGrid dat = null;
        Query q = em.createQuery
        ("SELECT t FROM DataGrid t " + "WHERE  t.operation_id LIKE :operation_id" + " AND t.install_id LIKE :install_id")
        .setParameter("operation_id", data.getOperation_id()).setParameter("install_id", data.getInstall_id());

        try {
            dat = (DataGrid) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            dat = (DataGrid) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return dat;
        }
    }
    
    public static DataGrid singleRead(int id_s) {
        EntityManager em = emf.createEntityManager();
        DataGrid res = null;
        Query q = em.createQuery
        ("SELECT t FROM DataGrid t " + "WHERE  t.install_id =:install_id ORDER BY t.operation_id DESC")
        .setParameter("install_id", id_s);

        try {
            res = (DataGrid) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            res = (DataGrid) q.getResultList().get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            return res;
        }
    }
    
    
    public static ArrayList<DataGrid> multipleRead(int id_s) {
        
        EntityManager em = emf.createEntityManager();
        List<DataGrid> dat = null;
        Query q = em
                .createQuery("SELECT t FROM DataGrid t " + "WHERE t.install_id =:id " + "ORDER BY t.operation_id DESC")
                .setParameter("id", id_s);

        try {
            dat = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            ArrayList<DataGrid> list = new ArrayList<>(dat.size());
            if (dat.size() > 5) {
                for (int i = 0; i < 5; i++) {
                    list.add(dat.get(i));
                }
            } else {
                list.addAll(dat);
            }

            return list;
        }

    }
        
        public static ArrayList<DataGrid> averageRead(int id_s) {
        
        EntityManager em = emf.createEntityManager();
        List<DataGrid> dat = null;
        Query q = em
                .createQuery("SELECT t FROM DataGrid t " + "WHERE t.install_id =:id " + "ORDER BY t.operation_id DESC")
                .setParameter("id", id_s);

        try {
            dat = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            ArrayList<DataGrid> list = new ArrayList<>(dat.size());
            list.addAll(dat);          
            return list;
        }

    }
               
    public static boolean update(DataGrid object, DataGrid newObject) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;

        try {
            object = read(object);
            object.setOperation_id(newObject.getOperation_id());
            object.setInstall_id(newObject.getInstall_id());
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

    public static boolean delete(DataGrid data) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(data);
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
    
    /*public static int getAverage(int id , boolean has){
        
        if (has){
            return 4;
        }
        
        if(!has){
        return 1;
        }
        return 0;
    }*/
}
