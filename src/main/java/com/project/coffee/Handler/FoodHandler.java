package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.IMenuAction;
import com.project.coffee.Model.Food;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.HandleInputSelection;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public class FoodHandler extends UtilsHandler implements IMenuAction {
    private ArrayList<Food> foodData;
    private Integer flag = 0;

    public FoodHandler() {
        this.foodData = this.convertJsonDataToArrayList(Constants.FOOD_FILE_NAME, Food.class);
        sortFoodData();
    }

    public ArrayList<Food> getFoodData() {
        return foodData;
    }

    public void setFoodData(ArrayList<Food> foodData) {
        this.foodData = foodData;
    }

    private void sortFoodData() {
        Collections.sort(this.foodData, Comparator.comparing(Food::getPrice));
    }

    public void add() {
        Food newFood = new Food();
        String inputString;
        Integer inputInt;

        newFood.setFoodId(this.getUUIDv4());

        inputString = HandleInputSelection.inString(" Nhap ten mon an: ");
        newFood.setFoodName(inputString);

        inputInt = HandleInputSelection.inInt(" Nhap gia: ");
        newFood.setPrice(inputInt);

        inputInt = HandleInputSelection.inInt(" Nhap so luong: ");
        newFood.setQuantity(inputInt);

        this.foodData.add(newFood);
        String foodDataJsonString = new Gson().toJson(this.foodData);
        this.writeFileData(Constants.FOOD_FILE_NAME, foodDataJsonString);
        this.sortFoodData();
        System.out.println("Them thuc an thanh cong");
        System.out.println();
    }

    public void update() {
    }

    public void delete() {
        this.foodList(this.flag, null);

        int inputInt = HandleInputSelection.inInt(" Nhap STT thuc an can xoa: ");
        this.foodData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.foodData);
        this.writeFileData(Constants.FOOD_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa thuc an thanh cong");
        System.out.println();
    }

    public void foodList(Integer flag, @Nullable ArrayList<Food> foodData) {
        Map<String, Integer> columns = new LinkedHashMap<String, Integer>();
        columns.put("STT", 5);
        columns.put("Ten Mon An", 25);
        columns.put("Gia", 7);
        columns.put("So Luong", 5);
        columns.put("Danh muc", 15);

        ArrayList<String> methods = new ArrayList<String>() {{
            add("getFoodName");
            add("getPrice");
            add("getQuantity");
            add("getCategory");
        }};

        if (this.flag != flag && foodData == null) {
            this.flag = flag;
            Collections.reverse(this.foodData);
        }

        foodData = foodData != null ? foodData : this.foodData;

        this.printTable(foodData, columns, Food.class, methods);
    }

    public void search() {
        String inputString = HandleInputSelection.inPrice(" Nhap khoang gia can tim (ex: 30-90): ");

        String[] priceRange = inputString.split("-");

        ArrayList<Food> foodDataFiltered = this.foodData.stream()
                .filter(food -> food.getPrice() > Integer.parseInt(priceRange[0]) && food.getPrice() < Integer.parseInt(priceRange[1]))
                .collect(Collectors.toCollection(ArrayList::new));

        if (foodDataFiltered.size() == 0) {
            System.out.println("==================> Not Found <==================");
            return;
        }

        this.foodList(0, foodDataFiltered);
    }

}
