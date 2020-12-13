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
    
    public static float processData(int id_s){
        
        ArrayList<DataGrid> arrayData = new ArrayList<>();
        Init i = Init_DB.searchInit(id_s);
        Sensors s = Sensors_DB.search(i.getId());
        int average = 0;
        int count = 0;
        
        if(Sensors_DB.isAverage(id_s)){
            arrayData = DataGrid_DB.averageRead(id_s);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime date = null;
            
            for (DataGrid t : arrayData) {
                date = LocalDateTime.parse(t.getDate(), formatter);
                if(date.isAfter(LocalDateTime.now().minusHours(s.getHours()))){
                    System.out.println(t.getOperation_id() + ".  " + t.getDate());
                    average += t.getData();
                    count++;
                                        
                }
            }
            
            float div = (float)average/count;
            System.out.println(average + "/" + count + " = " + div);
            System.out.println("------------------");
            return (div);
            
        }
        if(!Sensors_DB.isAverage(id_s)){
            DataGrid t = DataGrid_DB.singleRead(id_s);
            return t.getData();
        }
        
        return 0;
        
    }
    
    /*public static int dataParcial (int in){
                
        return DataGrid_DB.getAverage(in, Sensors_DB.isAverage(in));        
    }*/
}
