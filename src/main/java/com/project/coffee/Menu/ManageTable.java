package com.project.coffee.Menu;


import com.project.coffee.Utils.HandleInputSelection;

public class ManageTable {

    public static void main() {
        int swValue;
        do {
            System.out.println("=============================");
            System.out.println("|        MANAGE TABLE       |");
            System.out.println("=============================");
            System.out.println("1. Xem bàn trống            ");
            System.out.println("3. Tìm kiếm bàn             ");
            System.out.println("4. Thêm bàn                  ");
            System.out.println("5. Xoá bàn                   ");
            System.out.println("5. Cập nhật bàn              ");
            System.out.println("5. Đặt bàn                   ");
            System.out.println("5. Thanh toán                ");
            System.out.println("0. Thoat                     ");
            System.out.println("============================ ");
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
