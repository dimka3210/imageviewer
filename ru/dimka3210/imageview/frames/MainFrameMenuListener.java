package ru.dimka3210.imageview.frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainFrameMenuListener {
    private MainFrame mainFrame;
    private String lastDir = null;

    public MainFrameMenuListener(JFrame mainFrame) {
        this.mainFrame = (MainFrame) mainFrame;
    }

    public ActionListener getOpenListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileDialog dialog = new FileDialog(mainFrame);
                dialog.setMultipleMode(false);
                dialog.setMode(FileDialog.LOAD);
                dialog.setFilenameFilter(new FilenameFilter() {
                    @Override
                    public boolean accept(File dir, String name) {
                        Pattern pattern = Pattern.compile("\\.(jpeg|jpg|png)");
                        Matcher matcher = pattern.matcher(name);
                        return matcher.find();
                    }
                });
                if (lastDir == null) {
                    dialog.setDirectory(System.getProperty("user.home"));
                } else {
                    dialog.setDirectory(lastDir);
                }
                dialog.setVisible(true);
                /**************************************/
                lastDir = dialog.getDirectory();
                if (dialog.getFile() == null) {
                    return;
                }
                mainFrame.renderBigImage(dialog.getDirectory() + File.separator + dialog.getFile());
            }
        };
    }
}
