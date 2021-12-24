package com.project.coffee.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class WriteFile extends OpenFile {
    public void writeDataToFile(String fileName, String fileContent) {
        URL resource = this.openFileByFileName(fileName);
        try {
            FileWriter myWriter = new FileWriter(resource.getFile());
            myWriter.write(fileContent);
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
