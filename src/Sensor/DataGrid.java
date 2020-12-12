/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensor;

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
@Table(name = "DataGrid")
public class DataGrid implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private int operation_id;
    private int install_id;
    private int data;
    private String date;

    DataGrid(){
    }
    
    public DataGrid(int install_id, int data, String date) {
        
        this.install_id = install_id;
        this.data = data;
        this.date = date;
    }

    public int getOperation_id() {
        return operation_id;
    }

    public void setOperation_id(int operation_id) {
        this.operation_id = operation_id;
    }

    public int getInstall_id() {
        return install_id;
    }

    public void setInstall_id(int install_id) {
        this.install_id = install_id;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}
