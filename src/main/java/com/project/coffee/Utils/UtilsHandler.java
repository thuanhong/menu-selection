package com.project.coffee.Utils;

import com.google.gson.Gson;
import org.json.simple.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class UtilsHandler {
    protected JSONArray getFileData(String fileName) {
        ReadData ReadDataInstance = new ReadData(fileName);
        JSONArray staffDataJsonArray = ReadDataInstance.getData();
        return staffDataJsonArray;
    }

    protected void writeFileData(String fileName, String fileContent) {
        WriteFile writeFile = new WriteFile();
        writeFile.writeDataToFile(fileName, fileContent);
    }

    protected String getUUIDv4() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    protected <T> ArrayList<T> convertJsonDataToArrayList(String fileName, Class<T> type) {
        JSONArray jsonArrayData = this.getFileData(fileName);

        ArrayList<T> result = new ArrayList<T>();

        for(int i = 0; i < jsonArrayData.size(); i++){
            Object object = jsonArrayData.get(i);
            Gson gson = new Gson();
            T classObj = gson.fromJson(object.toString(), type);
            result.add(classObj);
        }
        return result;
    }
    protected <T> void printTable(ArrayList<T> tableData, Map<String, Integer> columns, Class<T> Type, ArrayList<String> listMethod) {
        System.out.println();
        String leftAlignFormat = "";
        Set<String> set = columns.keySet();
        for (String key : set) {
            leftAlignFormat += "| %-"+ columns.get(key) +"s ";
        }
        leftAlignFormat += "|%n";
        System.out.format(leftAlignFormat, set.toArray());
        String[] slashes = new String[tableData.size()];
        Arrays.fill(slashes, "-----");
        System.out.format(leftAlignFormat, Arrays.stream(slashes).toArray());

        for (int i = 0; i < tableData.size(); i++) {
            T row = tableData.get(i);
            ArrayList rowData = new ArrayList<>();
            rowData.add(i+1);
            listMethod.stream()
                .map(method -> {
                    try {
                        Object data = Type.getMethod(method).invoke(row);
                        rowData.add(data);
                        return true;
                    }catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toCollection(ArrayList::new));

            System.out.format(leftAlignFormat, rowData.toArray());
        }
        System.out.println();
    }
}
