package com.project.coffee.Menu;


import com.project.coffee.Utils.HandleInputSelection;

public class ManageFood {

    public static void main() {
        int swValue;
        do {
            System.out.println("=============================");
            System.out.println("|        MANAGE FOOD        |");
            System.out.println("=============================");
            System.out.println("1. Xem danh sách tăng dần   ");
            System.out.println("2. Xem danh sách giảm dần  ");
            System.out.println("3. Tìm kiếm thức ăn         ");
            System.out.println("4. Thêm thức ăn              ");
            System.out.println("5. Xoá thức ăn               ");
            System.out.println("0. Thoat                     ");
            System.out.println("=============================");
            swValue = HandleInputSelection.inInt(" Select option: ");

            System.out.println();
            System.out.println();

            switch (swValue) {
                case 0:
                    break;
                case 1:
//                    ReadData.main();
                    break;
                case 2:
                    System.out.println("Option 2 selected");
                    break;
                case 3:
                    System.out.println("Option 2 selected");
                    break;
                case 4:
                    System.out.println("Option 2 selected");
                    break;
                case 5:
                    System.out.println("Option 2 selected");
                    break;
                case 6:
                    System.out.println("Option 2 selected");
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (swValue != 0);

    }
}
