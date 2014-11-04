package ru.dimka3210.imageview.frames;

import ru.dimka3210.imageview.lib.Config;
import sun.util.locale.LocaleUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class AboutFrame extends JDialog {
    private static AboutFrame instance = null;
    JPanel mainPanel = new JPanel();

    public AboutFrame() {
        setTitle("About " + LocaleUtils.toLowerString(Config.TITLE));
        setContentPane(mainPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        renderMainPanel();
        setResizable(false);
        pack();
    }

    private void renderMainPanel() {
        Dimension defaultSize = new Dimension(256, 256);
        mainPanel.setPreferredSize(defaultSize);
        mainPanel.setLayout(new BorderLayout());
        drawImage(defaultSize.width, defaultSize.height);
    }

    private void drawImage(int width, int height) {
        try {
            Image aboutImage = ImageIO.read(new File("ru/dimka3210/imageview/images/about-label.png"));
            Image resizeImage = aboutImage.getScaledInstance(width, height, 0);
            ImageIcon icon = new ImageIcon(resizeImage);
            JLabel label = new JLabel(icon);
            JLabel link = getLinkLabel();
            label.setPreferredSize(new Dimension(width, height));
            mainPanel.add(label, BorderLayout.CENTER);
            mainPanel.add(link, BorderLayout.NORTH);
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

    public JLabel getLinkLabel() {
        JLabel label = new JLabel("http://dimka3210.ru");
        Color color = new Color(0, 32, 128);
        Border border = BorderFactory.createMatteBorder(0, 0, 2, 0, color);

        label.setForeground(color);
        label.setBorder(border);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    URI url = URI.create("http://dimka3210.ru");
                    Desktop.getDesktop().browse(url);
                } catch (Exception exception) {
                    System.out.println(exception.getMessage());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        return label;
    }
}
