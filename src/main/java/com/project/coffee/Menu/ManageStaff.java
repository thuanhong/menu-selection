package com.project.coffee.Menu;


import com.project.coffee.Handler.StaffHandler;
import com.project.coffee.Utils.HandleInputSelection;

public class ManageStaff {

    public static void main() {
        int swValue;
        StaffHandler staffHandler = new StaffHandler();
        do {
            System.out.println("=============================");
            System.out.println("|        MANAGE STAFF       |");
            System.out.println("=============================");
            System.out.println("1. Xem danh sách nhân viên   ");
            System.out.println("2. Tra cứu                   ");
            System.out.println("3. Thêm nhân viên            ");
            System.out.println("4. Cập nhật nhân viên        ");
            System.out.println("5. Xoá nhân viên             ");
            System.out.println("6. Nhân viên sinh trong tháng");
            System.out.println("0. Thoat                     ");
            System.out.println("============================ ");
            swValue = HandleInputSelection.inInt(" Select option: ");

            System.out.println();
            System.out.println();

            switch (swValue) {
                case 0:
                    break;
                case 1:
                    staffHandler.checkStaffList(null);
                    break;
                case 2:
                    staffHandler.search();
                    break;
                case 3:
                    staffHandler.add();
                    break;
                case 4:
                    staffHandler.update();
                    break;
                case 5:
                    staffHandler.delete();
                    break;
                case 6:
                    staffHandler.checkStaffBirthdayInCurrentMonth();
                    break;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (swValue != 0);

    }
}
