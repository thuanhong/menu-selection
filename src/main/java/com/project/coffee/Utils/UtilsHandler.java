package com.project.coffee.Utils;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
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

    protected  <T> ArrayList<T> convertJsonDataToArrayList(String fileName, Class<T> type) {
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
    protected  <T> void printTable(ArrayList<T> tableData, ArrayList<String> columns, Class<T> Type, ArrayList<String> listMethod) {
        System.out.println();
        String leftAlignFormat = "| %-5s " + StringUtils.repeat("| %-20s ", columns.size() - 1) + "|%n";
        System.out.format(leftAlignFormat, columns.toArray());
        String[] slashes = new String[tableData.size()];
        Arrays.fill(slashes, "-------------");
        slashes[0] = "---";
        System.out.format(leftAlignFormat, Arrays.stream(slashes).toArray());

        for (int i = 0; i < tableData.size(); i++) {
            T row = tableData.get(i);
            ArrayList rowData = new ArrayList<>();
            rowData.add(i+1);
            listMethod.stream()
                .map(method -> {
                    try {
                        String data = (String) Type.getMethod(method).invoke(row);
                        rowData.add(data);
                        return true;
                    } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    return false;
                }).collect(Collectors.toCollection(ArrayList::new));

            System.out.format(leftAlignFormat, rowData.toArray());
        }
        System.out.println();
    }
}
