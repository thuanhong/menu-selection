package com.project.coffee.Utils;

import com.google.gson.Gson;
import com.project.coffee.Model.Staff;

import java.util.ArrayList;

public class PrintTable {
    public static void output(ArrayList<Object> tableData) {
        String leftAlignFormat = "| %-15s | %-4d |%n";

        System.out.format("+-----------------+------+%n");
        System.out.format("| Column name     | ID   |%n");
        System.out.format("+-----------------+------+%n");
        for (int i = 0; i < 5; i++) {
            System.out.format(leftAlignFormat, "some data" + i, i * i);
        }
        System.out.format("+-----------------+------+%n");
        for(int i = 0; i < tableData.size(); i++){
            Object object = tableData.get(i);
            Gson gson= new Gson();
            Staff obj = gson.fromJson(object.toString(), Staff.class);
            System.out.println(obj.getName());
            // now do something with the Object
        }
    }
}
