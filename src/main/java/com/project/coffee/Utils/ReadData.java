package com.project.coffee.Utils;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.net.URL;


public class ReadData extends OpenFile {
    private JSONArray data;

    public ReadData(String filename) {
        this.data = this.readDataFromResource((filename));
    }

    public JSONArray getData() {
        return data;
    }

    public JSONArray readDataFromResource(String fileName) {
        URL resource = this.openFileByFileName(fileName);
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(resource.getFile()));
            JSONArray jsonArray = (JSONArray) obj;
            return jsonArray;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
