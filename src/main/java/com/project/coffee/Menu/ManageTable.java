package com.project.coffee.Menu;


import com.project.coffee.Handler.TableHandler;
import com.project.coffee.Utils.HandleInputSelection;

public class ManageTable {

    public static void main() {
        int swValue;

        TableHandler tableHandler = new TableHandler();
        do {
            System.out.println("=============================");
            System.out.println("|        MANAGE TABLE       |");
            System.out.println("=============================");
            System.out.println("1. Xem bàn trống            ");
            System.out.println("3. Tìm kiếm bàn theo sức chứa");
            System.out.println("4. Thêm bàn                  ");
            System.out.println("5. Xoá bàn                   ");
            System.out.println("6. Cập nhật bàn              ");
            System.out.println("7. Đặt bàn                   ");
            System.out.println("8. Thanh toán                ");
            System.out.println("0. Thoat                     ");
            System.out.println("============================ ");
            swValue = HandleInputSelection.inInt(" Select option: ");

            System.out.println();
            System.out.println();

            switch (swValue) {
                case 0:
                    break;
                case 1:
                    tableHandler.checkTableList(null);
                    break;
                case 2:
                    tableHandler.searchByContain();
                    break;
                case 3:
                    tableHandler.add();
                    break;
                case 4:
                    tableHandler.delete();
                    break;
                case 5:
                    tableHandler.update();
                    break;
                case 6:
                    System.out.println("dat ban");
                    break;
                case 7:
                    System.out.println("thanh toan");
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (swValue != 0);

    }
}
