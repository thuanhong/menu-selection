package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.MenuAction;
import com.project.coffee.Model.Table;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TableHandler extends UtilsHandler implements MenuAction {
    private ArrayList<Table> tableData;
    private ArrayList<Table> emptyData;

    public TableHandler() {
        this.tableData = this.convertJsonDataToArrayList(Constants.TABLE_FILE_NAME, Table.class);
        this.emptyData = this.tableData.stream()
                .filter(table -> table.getFoods().size() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void add() {
//        Scanner scanner = new Scanner(System.in);
//        Staff newStaff = new Staff();
//
//        System.out.print("Nhap ten nhan vien: ");
//        String inputString = scanner.nextLine();
//        newStaff.setName(inputString);
//
//        System.out.print("Nhap ngay sinh: ");
//        inputString = scanner.nextLine();
//        newStaff.setBirthDay(inputString);
//
//        System.out.print("Nhap gioi tinh: ");
//        inputString = scanner.nextLine();
//        newStaff.setGender(inputString);
//
//        System.out.print("Nhap que quan: ");
//        inputString = scanner.nextLine();
//        newStaff.setHomeTown(inputString);
//
//        newStaff.setStaffId(this.getUUIDv4());
//
//        this.staffData.add(newStaff);
//        String staffDataJsonString = new Gson().toJson(this.staffData);
//        this.writeFileData(Constants.STAFF_FILE_NAME, staffDataJsonString);
//        System.out.println("Them nhan vien thanh cong");
    }

    public void update() {
        this.checkTableList(this.tableData);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap STT ban can cap nhat: ");
        int inputInt = scanner.nextInt();

        Table newTable = this.tableData.get(inputInt-1);

        System.out.print("Nhap suc chua: ");
        inputInt = scanner.nextInt();
        newTable.setContain(inputInt);

        String staffDataJsonString = new Gson().toJson(this.tableData);
        this.writeFileData(Constants.TABLE_FILE_NAME, staffDataJsonString);
        System.out.println("Cap nhat ban thanh cong");
    }

    public void delete() {
        this.checkTableList(this.tableData);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap STT ban can xoa: ");
        int inputInt = scanner.nextInt();

        this.tableData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.tableData);
        this.writeFileData(Constants.TABLE_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa ban thanh cong");
    }

    public void checkTableList(@Nullable ArrayList<Table> tableData) {
        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
        columns.put("STT", 5);
        columns.put("Ma Ban", 15);
        columns.put("Suc Chua", 15);


        ArrayList<String> methods = new ArrayList<String>() {{
            add("getTableId");
            add("getContain");
        }};

        tableData = tableData != null ? tableData : this.emptyData;

        this.printTable(tableData, columns, Table.class, methods);
    }

    public void searchByContain() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap suc chua: ");
        Integer inputInt = scanner.nextInt();

        ArrayList<Table> tableDataFiltered = this.tableData.stream()
                .filter(table -> table.getContain() == inputInt)
                .collect(Collectors.toCollection(ArrayList::new));

        if (tableDataFiltered.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.checkTableList(tableDataFiltered);
    }
}
