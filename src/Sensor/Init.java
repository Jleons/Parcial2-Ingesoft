/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensor;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author LENOVO
 */
@Entity
@Table(name = "Init")
public class Init implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int install_id;
    private int id;
    private String sensor_Location;
    private String sensor_type;
    
    public Init(){
    }

    public Init(int id, String sensor_Location, String sensor_type) {
        this.id = id;
        this.sensor_Location = sensor_Location;
        this.sensor_type = sensor_type;
    }

    public int getInstall_id() {
        return install_id;
    }

    public void setInstall_id(int install_id) {
        this.install_id = install_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensor_Location() {
        return sensor_Location;
    }

    public void setSensor_Location(String sensor_Location) {
        this.sensor_Location = sensor_Location;
    }

    public String getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }
    
    public Init clone() throws CloneNotSupportedException {
        Init cloned = (Init) super.clone();
        return cloned;
    }
    
}
