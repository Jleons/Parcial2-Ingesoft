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
public class InsertInstalls {
            
        public static void main(String[] args) {

        Init i = new Init(1, "Vacuum cleaner", Sensors_DB.search(1).getType());
        boolean b = Init_DB.create(i);
        System.out.println(b);
        
        }
}
