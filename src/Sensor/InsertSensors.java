/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensor;

import DB.Init_DB;
import DB.Sensors_DB;

/**
 *
 * @author LENOVO
 */
public class InsertSensors {
    
    public static void main(String[] args) {

    Sensors s = new Sensors("Light sensor", "Line Hunting Sensor", 0, 1, false, 0);
        boolean x = Sensors_DB.create(s);
        System.out.println(x);

    }
}
