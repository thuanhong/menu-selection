package com.project.coffee.Model;

import java.util.ArrayList;

public class Table {
    private String tableId;
    private Integer contain;
    private ArrayList<Food> foods;

    public Table() {
    }

    public Table(String tableId, Integer contain, ArrayList<Food> foods) {
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

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }
}
