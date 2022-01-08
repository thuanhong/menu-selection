package com.project.coffee.Menu;

import com.project.coffee.Handler.FoodHandler;
import com.project.coffee.Utils.HandleInputSelection;

public class ManageFood {

    public static void main() {
        int swValue;
        FoodHandler foodHandler = new FoodHandler();
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
                    foodHandler.foodList(0, null);
                    break;
                case 2:
                    foodHandler.foodList(1, null);
                    break;
                case 3:
                    foodHandler.search();
                    break;
                case 4:
                    foodHandler.add();
                    break;
                case 5:
                    foodHandler.delete();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (swValue != 0);

    }
}
