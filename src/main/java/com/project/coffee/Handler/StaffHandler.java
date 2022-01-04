package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.MenuAction;
import com.project.coffee.Model.Staff;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StaffHandler extends UtilsHandler implements MenuAction {
    private ArrayList<Staff> staffData;

    public StaffHandler() {
        this.staffData = this.convertJsonDataToArrayList(Constants.STAFF_FILE_NAME, Staff.class);
    }

    public ArrayList<Staff> getStaffData() {
        return staffData;
    }

    public void setStaffData(ArrayList<Staff> staffData) {
        this.staffData = staffData;
    }

    public void add() {
        Scanner scanner = new Scanner(System.in);
        Staff newStaff = new Staff();

        System.out.print("Nhap ten nhan vien: ");
        String inputString = scanner.nextLine();
        newStaff.setName(inputString);

        System.out.print("Nhap ngay sinh: ");
        inputString = scanner.nextLine();
        newStaff.setBirthDay(inputString);

        System.out.print("Nhap gioi tinh: ");
        inputString = scanner.nextLine();
        newStaff.setGender(inputString);

        System.out.print("Nhap que quan: ");
        inputString = scanner.nextLine();
        newStaff.setHomeTown(inputString);

        newStaff.setStaffId(this.getUUIDv4());

        this.staffData.add(newStaff);
        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Them nhan vien thanh cong");
    }

    public void update() {
        this.checkStaffList(null);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap STT nhan vien can cap nhat: ");
        int inputInt = scanner.nextInt();

        String inputString = scanner.nextLine();
        Staff newStaff = this.staffData.get(inputInt-1);

        System.out.print("Nhap ten nhan vien: ");
        inputString = scanner.nextLine();
        newStaff.setName(inputString);

        System.out.print("Nhap ngay sinh: ");
        inputString = scanner.nextLine();
        newStaff.setBirthDay(inputString);

        System.out.print("Nhap gioi tinh: ");
        inputString = scanner.nextLine();
        newStaff.setGender(inputString);

        System.out.print("Nhap que quan: ");
        inputString = scanner.nextLine();
        newStaff.setHomeTown(inputString);

        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Cap nhat nhan vien thanh cong");
    }

    public void delete() {
        this.checkStaffList(null);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap STT nhan vien can xoa: ");
        int inputInt = scanner.nextInt();

        this.staffData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa nhan vien thanh cong");
    }

    public void checkStaffList(@Nullable ArrayList<Staff> staffData) {
        ArrayList<String> columns = new ArrayList<String>() {{
            add("STT");
            add("Ten Nhan Vien");
            add("Ngay Sinh");
            add("Gioi Tinh");
            add("Que Quan");
        }};

        ArrayList<String> methods = new ArrayList<String>() {{
            add("getName");
            add("getBirthDay");
            add("getGender");
            add("getHomeTown");
        }};

        this.printTable(this.staffData, columns, Staff.class, methods);
    }

    public void search() {
        int swValue;

        loop: do {
            System.out.println("1. Theo ten");
            System.out.println("2. Theo ngay sinh");
            System.out.println("3. Theo gioi tinh");
            System.out.println("4. Theo que quan");
            swValue = HandleInputSelection.inInt(" Select option: ");

            System.out.println();
            System.out.println();

            switch (swValue) {
                case 1:
                    this.searchBy("getName");
                    break loop;
                case 2:
                    this.searchBy("getBirthDay");
                    break loop;
                case 3:
                    this.searchBy("getGender");
                    break loop;
                case 4:
                    this.searchBy("getHomeTown");
                    break loop;
                default:
                    System.out.println("Invalid selection");
                    break;
            }
        }
        while (true);
    }

    private void searchBy(String methodName) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap thong tin can tim: ");
        String str = scanner.next();

        ArrayList<Staff> staffDataFiltered = this.staffData.stream()
            .filter(staff -> {
                try {
                    String data = (String) Staff.class.getMethod(methodName).invoke(staff);
                    return data.toLowerCase().contains(str.toLowerCase());
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                return false;
            })
            .collect(Collectors.toCollection(ArrayList::new));

        if (staffDataFiltered.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.checkStaffList(staffDataFiltered);
    }

    public void checkStaffBirthdayInCurrentMonth() {
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        ArrayList<Staff> staffDataFiltered = this.staffData.stream()
                .filter(staff -> {
                    String[] data = staff.getBirthDay().split("/");
                    return Integer.parseInt(data[1]) == month;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (staffDataFiltered.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.checkStaffList(staffDataFiltered);
    }
}
