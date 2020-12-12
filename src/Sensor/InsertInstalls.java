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
        Init_DB.create(i);
        Init t = new Init(2, "Lab Robot", Sensors_DB.search(2).getType());
        Init_DB.create(t);
        }
}
