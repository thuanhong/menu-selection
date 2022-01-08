package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.IMenuAction;
import com.project.coffee.Model.Table;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TableHandler extends UtilsHandler implements IMenuAction {
    private ArrayList<Table> tableData;
    private ArrayList<Table> emptyData;
    private FoodHandler foodHandler;

    public TableHandler() {
        this.tableData = this.convertJsonDataToArrayList(Constants.TABLE_FILE_NAME, Table.class);
        this.initEmptyTable();
        this.foodHandler = new FoodHandler();
    }

    private void initEmptyTable() {
        this.emptyData = this.tableData.stream()
                .filter(table -> table.getFoods().size() == 0)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void add() {
        Table newTable = new Table();

        Integer inputInput = HandleInputSelection.inInt(" Nhập sức chứa của bàn:  ");
        newTable.setContain(inputInput);
        newTable.setFoods(new ArrayList<>());

        String lastTableId = this.tableData.get(this.tableData.size() - 1).getTableId();
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(lastTableId);
        while(matcher.find()) {
            newTable.setTableId(String.format(Constants.TABLE_ID_FORMAT, Integer.parseInt(matcher.group()) + 1));
        }

        this.tableData.add(newTable);
        this.emptyData.add(newTable);
        String tableDataJsonString = new Gson().toJson(this.tableData);
        this.writeFileData(Constants.TABLE_FILE_NAME, tableDataJsonString);
        System.out.println("Them ban thanh cong");
        System.out.println();
    }

    public void update() {
        this.checkTableList(this.tableData);

        int inputInt = HandleInputSelection.inInt(" Nhap STT ban can cap nhat: ");

        Table newTable = this.tableData.get(inputInt-1);

        inputInt = HandleInputSelection.inInt(" Nhap suc chua: ");
        newTable.setContain(inputInt);

        String staffDataJsonString = new Gson().toJson(this.tableData);
        this.writeFileData(Constants.TABLE_FILE_NAME, staffDataJsonString);
        System.out.println("Cap nhat ban thanh cong");
        System.out.println();
    }

    public void delete() {
        this.checkTableList(this.tableData);

        int inputInt = HandleInputSelection.inInt(" Nhap STT ban can xoa: ");
        this.tableData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.tableData);
        this.writeFileData(Constants.TABLE_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa ban thanh cong");
        System.out.println();
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

    public void search() {
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

    public void order() {
        this.checkTableList(null);
        Integer tableNumber = HandleInputSelection.inInt(" Nhap STT ban can dat: ");
        Table tableOrder = this.tableData.get(tableNumber - 1);

        Integer foodNumber;
        Integer quantity;
        do {

            this.foodHandler.foodList(0, null);
            foodNumber = HandleInputSelection.inInt(" Nhap STT mon an: ");
            quantity = HandleInputSelection.inInt(" Nhap so luong: ");
            if (quantity > this.foodHandler.getFoodData().get(foodNumber - 1).getQuantity()) {
                System.out.print("So luong nhap nhieu hon so luong thuc an con lai");
            }
            tableOrder.addFoods(this.foodHandler.getFoodData().get(foodNumber - 1), quantity);

            Integer continueInput = HandleInputSelection.inInt(" Tiep tuc dat mon (0-de dung lai): ");
            if (continueInput == 0) {
                this.initEmptyTable();
                String staffDataJsonString = new Gson().toJson(this.tableData);
                this.writeFileData(Constants.TABLE_FILE_NAME, staffDataJsonString);
                System.out.println("Dat ban thanh cong");
                System.out.println();
                return;
            }
        } while (true);
    }

    public void checkout() {
        ArrayList<Table> tables = this.tableData.stream()
                .filter(table -> table.getFoods().size() > 0)
                .collect(Collectors.toCollection(ArrayList::new));

        this.checkTableList(tables);
    }

}
