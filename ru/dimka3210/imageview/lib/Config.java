package ru.dimka3210.imageview.lib;

public class Config {
    public final static String HISTORY_FILE = "history.json";
    public final static String IMAGES_PATH = "ru/dimka3210/imageview/images/";
    public final static String TITLE = "Image viewer";

    public final static int LEFT_PANEL_WIDTH = 250;
    public final static int HISTORY_LENGTH = 100;

    public static String getDialogDefaultPath() {
        return System.getProperty("user.home");
    }
}