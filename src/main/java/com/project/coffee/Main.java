package com.project.coffee;


import com.project.coffee.Menu.ManageFood;
import com.project.coffee.Menu.ManageStaff;
import com.project.coffee.Menu.ManageTable;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.PrintTable;
import com.project.coffee.Utils.ReadData;
import org.json.simple.JSONArray;

public class Main {

    public static void main(String[] args) {
        int swValue;
        do {
            System.out.println("==============================");
            System.out.println("| COFFEE SHOP MENU SELECTION |");
            System.out.println("==============================");
            System.out.println("1. Quản lý nhân viên         ");
            System.out.println("2. Quản lý thông tin bàn     ");
            System.out.println("3. Quản lý thức ăn           ");
            System.out.println("4. Thống kê                  ");
            System.out.println("0. Thoat                     ");
            System.out.println("==============================");
            swValue = HandleInputSelection.inInt(" Select option: ");

            System.out.println();
            System.out.println();

            switch (swValue) {
                case 0:
                    System.out.println("Goodbye");
                    break;
                case 1:
                    ManageStaff.main();
                    break;
                case 2:
                    ManageTable.main();
                    break;
                case 3:
                    ManageFood.main();
                    break;
                case 4:

                    ReadData ReadDataInstance = new ReadData(Constants.STAFF_FILE_NAME);
                    JSONArray staffData = ReadDataInstance.getData();
                    PrintTable.output(staffData);
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (swValue != 0);

    }
}
