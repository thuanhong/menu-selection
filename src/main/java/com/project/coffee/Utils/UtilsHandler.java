package com.project.coffee.Utils;

import org.json.simple.JSONArray;

import java.util.UUID;

public class UtilsHandler {
    public JSONArray getFileData(String fileName) {
        ReadData ReadDataInstance = new ReadData(fileName);
        JSONArray staffDataJsonArray = ReadDataInstance.getData();
        return staffDataJsonArray;
    }

    public void writeFileData(String fileName, String fileContent) {
        WriteFile writeFile = new WriteFile();
        writeFile.writeDataToFile(fileName, fileContent);
    }

    public String getUUIDv4() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
