/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sensor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import DB.*;

/**
 *
 * @author LENOVO
 */
public class DataManagement {
    public static void sendData(int id ,boolean b) {

        Init i = Init_DB.searchInit(id);

        Sensors s = Sensors_DB.search(i.getId());
        int data = 0;  
        
        if(b == true){data = 1;}
        else{data = 0;}
        
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fecha = LocalDateTime.now().format(formatter);

        DataGrid t = new DataGrid(id, data, fecha);

        DataGrid_DB.create(t);
    }

    public static ArrayList<Object[]> showDataGrid(int id) {
        ArrayList<DataGrid> list = DataGrid_DB.multipleRead(id);
        ArrayList<Object[]> rows = new ArrayList<>();
        Init ins = Init_DB.searchInit(id);
        Sensors s = Sensors_DB.search(ins.getId());

        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row = new Object[6];
            row[0] = list.get(i).getOperation_id();
            row[1] = list.get(i).getInstall_id();
            row[2] = Init_DB.searchInit(list.get(i).getInstall_id()).getSensor_Location();
            row[3] = list.get(i).getData();
            row[4] = list.get(i).getDate();

            if (list.get(i).getData()< s.getMax_value()
                    && list.get(i).getData()> s.getMin_value()) {
                row[5] = "NORMAL";
            } else {
                row[5] = "ALERTA";
            }

            rows.add(row);
        }

        return rows;
    }

    
}
