package com.project.coffee.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Table {
    private String tableId;
    private Integer contain;
    private ArrayList<Map<String, Object>> foods;

    public Table() {
    }

    public Table(String tableId, Integer contain, ArrayList<Map<String, Object>> foods) {
        this.tableId = tableId;
        this.contain = contain;
        this.foods = foods;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public Integer getContain() {
        return contain;
    }

    public void setContain(Integer contain) {
        this.contain = contain;
    }

    public ArrayList<Map<String, Object>> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Map<String, Object>> foods) {
        this.foods = foods;
    }

    public void addFoods(Food food, Integer quantity) {
        Map<String, Object> newMap = new HashMap<>();
        newMap.put("food", food);
        newMap.put("quantity", quantity);
        this.foods.add(newMap);
    }

    @Override
    public String toString() {
        return "Table{" +
                "tableId='" + tableId + '\'' +
                ", contain=" + contain +
                ", foods=" + foods +
                '}';
    }
}
