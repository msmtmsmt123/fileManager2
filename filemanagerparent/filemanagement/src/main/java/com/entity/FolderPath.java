package com.entity;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class FolderPath {
    public static String getPath() throws IOException {
        Properties properties=new Properties();
        Reader reader=new FileReader("C:\\Users\\User\\IdeaProjects\\nairis\\filemanagerparent\\filemanagement\\src\\main\\java\\config.properties");
        properties.load(reader);
        return (String)properties.get("root.dir");
    }
}
