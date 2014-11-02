package ru.dimka3210.imageview.frames;

import ru.dimka3210.imageview.lib.Config;
import sun.util.locale.LocaleUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AboutFrame extends JDialog {
    private static AboutFrame instance = null;
    JPanel mainPanel = new JPanel();

    public AboutFrame() {
        setTitle("About " + LocaleUtils.toLowerString(Config.getTitle()));
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        renderMainPanel();
        setResizable(false);
        pack();
    }

    private void renderMainPanel() {
        Dimension defaultSize = new Dimension(256, 256);
        mainPanel.setPreferredSize(defaultSize);
        drawImage(defaultSize.width, defaultSize.height);
    }

    private void drawImage(int width, int height) {
        try {
            Image aboutImage = ImageIO.read(new File("ru/dimka3210/imageview/images/about-label.png"));
            Image resizeImage = aboutImage.getScaledInstance(width, height, 0);
            ImageIcon icon = new ImageIcon(resizeImage);
            JLabel label = new JLabel(icon);
            label.setPreferredSize(new Dimension(width, height));
            mainPanel.add(label);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void showFrame() {
        if (instance == null) {
            instance = new AboutFrame();
        }
        instance.setVisible(true);
    }
}
