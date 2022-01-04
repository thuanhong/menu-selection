package com.project.coffee.Handler;

import com.google.gson.Gson;
import com.project.coffee.Interface.MenuAction;
import com.project.coffee.Model.Food;
import com.project.coffee.Utils.Constants;
import com.project.coffee.Utils.UtilsHandler;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FoodHandler extends UtilsHandler implements MenuAction {
    private ArrayList<Food> foodData;
    private Integer flag = 0;

    public FoodHandler() {
        this.foodData = this.convertJsonDataToArrayList(Constants.FOOD_FILE_NAME, Food.class);
        Collections.sort(this.foodData, Comparator.comparing(Food::getPrice));
    }

    public ArrayList<Food> getFoodData() {
        return foodData;
    }

    public void setFoodData(ArrayList<Food> foodData) {
        this.foodData = foodData;
    }

    public void add() {
        Scanner scanner = new Scanner(System.in);
        Food newFood = new Food();
        String inputString;
        Integer inputInt;

        newFood.setFoodId(this.getUUIDv4());

        System.out.print("Nhap ten mon an: ");
        inputString = scanner.nextLine();
        newFood.setFoodName(inputString);

        System.out.print("Nhap gia: ");
        inputInt = scanner.nextInt();
        newFood.setPrice(inputInt);
        scanner.nextLine();

        System.out.print("Nhap so luong: ");
        inputInt = scanner.nextInt();
        newFood.setQuantity(inputInt);

        this.foodData.add(newFood);
        String foodDataJsonString = new Gson().toJson(this.foodData);
        this.writeFileData(Constants.FOOD_FILE_NAME, foodDataJsonString);
        System.out.println("Them thuc an thanh cong");
    }

    public void update() {
    }

    public void delete() {
        this.foodList(this.flag, null);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap STT thuc an can xoa: ");
        int inputInt = scanner.nextInt();

        this.foodData.remove(inputInt-1);

        String staffDataJsonString = new Gson().toJson(this.foodData);
        this.writeFileData(Constants.FOOD_FILE_NAME, staffDataJsonString);
        System.out.println("Xoa thuc an thanh cong");
    }

    public void foodList(Integer flag, @Nullable ArrayList<Food> foodData) {

        ArrayList<String> columns = new ArrayList<String>() {{
            add("STT");
            add("Ten Mon An");
            add("Gia");
            add("So Luong");
        }};

        ArrayList<String> methods = new ArrayList<String>() {{
            add("getFoodName");
            add("getPrice");
            add("getQuantity");
        }};

        if (this.flag != flag && foodData == null) {
            this.flag = flag;
            Collections.reverse(this.foodData);
        }

        foodData = foodData != null ? foodData : this.foodData;

        this.printTable(foodData, columns, Food.class, methods);
    }

    public void searchByPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap khoang gia can tim (ex: 30-90): ");
        String inputString = scanner.nextLine();

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
