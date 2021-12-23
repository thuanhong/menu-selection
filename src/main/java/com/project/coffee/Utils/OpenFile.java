package com.project.coffee.Utils;

import java.net.URL;

public class OpenFile {
    protected URL openFileByFileName(String fileName) {
        URL resource = ReadData.class.getClassLoader().getResource(fileName);

        if (resource == null)
            throw new IllegalArgumentException("file is not found!");

        return resource;
    }
}
