package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.MenuAction;
import com.project.coffee.Model.Staff;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.ReadData;
import com.project.coffee.Utils.WriteFile;
import org.jetbrains.annotations.Nullable;
import org.json.simple.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class StaffHandler implements MenuAction {
    private ArrayList<Staff> staffData = new ArrayList<Staff>();

    public StaffHandler() {
        ReadData ReadDataInstance = new ReadData(Constants.STAFF_FILE_NAME);
        JSONArray staffDataJsonArray = ReadDataInstance.getData();
        this.convertJsonDataToStaffData(staffDataJsonArray);
    }

    public ArrayList<Staff> getStaffData() {
        return staffData;
    }

    public void setStaffData(ArrayList<Staff> staffData) {
        this.staffData = staffData;
    }

    public void add() {
       String newStr = new Gson().toJson(this.staffData);
       System.out.println(newStr);
    }

    public void update() {
        WriteFile writeFile = new WriteFile();
        writeFile.WriteFile();
    }

    public void delete() {

    }

    public void checkStaffList(@Nullable ArrayList<Staff> staffData) {
        staffData = staffData == null ? this.staffData : staffData;
        System.out.println();
        String leftAlignFormat = "| %-25s | %-15s | %-15s | %-25s |%n";
        System.out.format(leftAlignFormat, "Ten Nhan Vien", "Ngay Sinh", "Gioi Tinh", "Que Quan");
        System.out.format(leftAlignFormat, "-------------", "-------------", "-------------", "-------------");

        for (Staff staff : staffData) {
            System.out.format(leftAlignFormat, staff.getName(), staff.getBirthDay(), staff.getGender(), staff.getHomeTown());
        }
        System.out.println();
    }

    private void convertJsonDataToStaffData(JSONArray staffDataJsonArray) {
        for(int i = 0; i < staffDataJsonArray.size(); i++){
            Object object = staffDataJsonArray.get(i);
            Gson gson = new Gson();
            Staff staff = gson.fromJson(object.toString(), Staff.class);
            this.staffData.add(staff);
        }
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

        ArrayList<Staff> staffDataFilterd = this.staffData.stream()
                .filter(staff -> {
                    try {
                        String data = (String) Staff.class.getMethod(methodName).invoke(staff);
                        return data.toLowerCase().contains(str.toLowerCase());
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    return false;
                })
                .collect(Collectors.toCollection(ArrayList::new));

        if (staffDataFilterd.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.checkStaffList(staffDataFilterd);
    }
}
