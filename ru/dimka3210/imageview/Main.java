package ru.dimka3210.imageview;

import ru.dimka3210.imageview.frames.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame mainFrame = new MainFrame(GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration());
        mainFrame.setVisible(true);
    }
}
