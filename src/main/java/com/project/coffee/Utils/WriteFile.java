package com.project.coffee.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class WriteFile extends OpenFile {
    public void WriteFile() {
        URL resource = this.openFileByFileName("json/TableData.json");
        try {
            FileWriter myWriter = new FileWriter(resource.getFile());
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
