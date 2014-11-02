package ru.dimka3210.imageview.lib;

import java.awt.*;

public class Screen {
    public static Dimension getFrameSize(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.getWidth() * 0.7);
        int height = (int) (screenSize.getHeight() * 0.5);
        return new Dimension(width, height);
    }
}
