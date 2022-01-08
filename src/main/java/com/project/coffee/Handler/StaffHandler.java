package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.IMenuAction;
import com.project.coffee.Model.Staff;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class StaffHandler extends UtilsHandler implements IMenuAction {
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
        Staff newStaff = new Staff();

        String inputString = HandleInputSelection.inString(" Nhap ten nhan vien: ");
        newStaff.setName(inputString);

        inputString = HandleInputSelection.inDate(" Nhap ngay sinh: ");
        newStaff.setBirthDay(inputString);

        inputString = HandleInputSelection.inGender(" Nhap gioi tinh: ");
        newStaff.setGender(inputString);

        inputString = HandleInputSelection.inString(" Nhap que quan: ");
        newStaff.setHomeTown(inputString);

        inputString = HandleInputSelection.inDate(" Nhap ngay vao lam viec: ");
        newStaff.setOnboarding(inputString);

        inputString = HandleInputSelection.inString(" Nhap bo phan: ");
        newStaff.setDepartment(inputString);

        Integer lastStaffId = this.staffData.get(this.staffData.size() - 1).getStaffId();
        newStaff.setStaffId(lastStaffId+1);

        this.staffData.add(newStaff);
        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Them nhan vien thanh cong");
        System.out.println();
    }

    public void update() {
        this.checkStaffList(null);

        int inputInt = HandleInputSelection.inInt(" Nhap STT nhan vien can cap nhat: ");

        String inputString;
        Staff newStaff = this.staffData.get(inputInt-1);

        inputString = HandleInputSelection.inString(" Nhap ten nhan vien: ");
        newStaff.setName(inputString);

        inputString = HandleInputSelection.inDate(" Nhap ngay sinh: ");
        newStaff.setBirthDay(inputString);

        inputString = HandleInputSelection.inGender(" Nhap gioi tinh: ");
        newStaff.setGender(inputString);

        inputString = HandleInputSelection.inString(" Nhap que quan: ");
        newStaff.setHomeTown(inputString);

        inputString = HandleInputSelection.inDate(" Nhap ngay vao lam viec: ");
        newStaff.setOnboarding(inputString);

        inputString = HandleInputSelection.inString(" Nhap bo phan: ");
        newStaff.setDepartment(inputString);

        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Cap nhat nhan vien thanh cong");
        System.out.println();
    }

    public void delete() {
        this.checkStaffList(null);

        int inputInt = HandleInputSelection.inInt(" Nhap STT nhan vien can xoa: ");
        this.staffData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.staffData);
        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa nhan vien thanh cong");
        System.out.println();
    }

    public void checkStaffList(@Nullable ArrayList<Staff> staffData) {
        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
        columns.put("STT", 5);
        columns.put("Ma NV", 5);
        columns.put("Ten Nhan Vien", 25);
        columns.put("Ngay Sinh", 10);
        columns.put("Gioi Tinh", 10);
        columns.put("Que Quan", 20);
        columns.put("Bo Phan", 15);

        ArrayList<String> methods = new ArrayList<String>() {{
            add("getStaffId");
            add("getName");
            add("getBirthDay");
            add("getGender");
            add("getHomeTown");
            add("getDepartment");
        }};

        staffData = staffData != null ? staffData : this.staffData;
        this.printTable(staffData, columns, Staff.class, methods);
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
        String str = HandleInputSelection.inString(" Nhap thong tin can tim: ");

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
