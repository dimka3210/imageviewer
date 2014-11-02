package ru.dimka3210.imageview.lib;

import java.io.File;
import java.util.Properties;

public class Config {
    private static Config ourInstance = new Config();
    final static String title = "Image viewer";

    public static Config getInstance() {
        return ourInstance;
    }

    private Config() {
        try {
            Properties properties = new Properties();
//            properties.load(new FileInputStream(this.getFile()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getRealPath() {
        File f = new File(".");
        return f.getAbsolutePath();
    }

    public static String getTitle() {
        return title;
    }
}
