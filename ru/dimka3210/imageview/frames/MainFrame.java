package ru.dimka3210.imageview.frames;

import ru.dimka3210.imageview.lib.Config;
import ru.dimka3210.imageview.lib.Screen;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel bigImage;
    MainFrameMenuListener menuListener;

    public MainFrame(GraphicsConfiguration gc) {
        super(gc);
        mainPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        bigImage = new JLabel();

        setTitle(Config.getTitle());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(Screen.getFrameSize());
        setLocationRelativeTo(null);
        renderMainPanel();
        renderMenu();
        setContentPane(mainPanel);
    }

    private void renderMainPanel() {
        final int leftWidth = 250;
        Dimension frameSize = Screen.getFrameSize();
        Border b = new LineBorder(new Color(0, 255, 0), 1);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));

        leftPanel.setPreferredSize(new Dimension(leftWidth, frameSize.height));
        rightPanel.setPreferredSize(new Dimension(frameSize.width - leftWidth, frameSize.height));
        bigImage.setPreferredSize(rightPanel.getPreferredSize());
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(bigImage, BorderLayout.CENTER);
//        leftPanel.setBorder(b);
//        rightPanel.setBorder(b);

        Box mainBox = Box.createHorizontalBox();
        mainBox.add(leftPanel);
        mainBox.add(rightPanel);

        mainPanel.add(mainBox);
        menuListener = new MainFrameMenuListener(this);
    }

    private void renderMenu() {
        JMenuBar bar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem menuAbout = new JMenuItem("About");

        menuAbout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AboutFrame.showFrame();
            }
        });


        JMenuItem itemOpen = new JMenuItem("Open directory", getIcon("menu-open.png", 16, 16));
        itemOpen.addActionListener(menuListener.getOpenListener());
        JMenuItem itemClose = new JMenuItem("Close", getIcon("menu-close.png", 16, 16));
        JMenuItem itemExit = new JMenuItem("Exit", getIcon("menu-exit.png", 16, 16));

        menuFile.add(itemOpen);
        menuFile.add(itemClose);
        menuFile.add(itemExit);

        bar.add(menuFile);
        bar.add(menuAbout);

        setJMenuBar(bar);
    }

    private Icon getIcon(String fileName, int width, int height) {
        Icon icon = null;
        try {
            Image image = ImageIO.read(new File("ru/dimka3210/imageview/images/" + fileName));
            Image resizeImage = image.getScaledInstance(width, height, 0);
            icon = new ImageIcon(resizeImage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return icon;
    }

    public void renderBigImage(String filename) {
        try {
            Dimension frameSize = rightPanel.getSize();
            Image image = ImageIO.read(new File(filename));
            ImageIcon imageIcon = new ImageIcon(filename);
            Dimension imageSize = new Dimension(imageIcon.getIconWidth(), imageIcon.getIconHeight());
            Dimension finalSize = processImageSize(frameSize, imageSize);
            System.out.println(finalSize);
            ImageIcon resultBigImageIcon = new ImageIcon(image.getScaledInstance(finalSize.width, finalSize.height, 0));
            bigImage.removeAll();
            bigImage.setIcon(resultBigImageIcon);
            bigImage.validate();
            bigImage.repaint();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private Dimension processImageSize(Dimension frameSize, Dimension imageSize) {
        int f_w = frameSize.width;
        int f_h = frameSize.height;
        int i_w = imageSize.width;
        int i_h = imageSize.height;
        int result_width = imageSize.width;
        int result_height = imageSize.height;


        double k = 1;

        if (i_w > f_w) {
            k = (double) f_w / (double) i_w;
            result_height = (int) (i_h * k);
            result_width = f_w;
        } else if (i_h > f_h) {
            k = (double) f_h / (double) i_h;
            result_width = (int) (i_w * k);
            result_height = f_h;
        }

        return new Dimension(result_width, result_height);
    }
}
