package com.ding.sm.component;
import javax.swing.*;
import java.awt.*;

/**
 * @ClassName ImgPanel
 * @Description TODO
 * @Author 15696
 * @Date 2020/12/6
 **/
public class ImgPanel extends JPanel {
    private String fileName;

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    protected void paintComponent(Graphics g) {
        ImageIcon icon = new ImageIcon("src/main/resources/img/" + fileName);
        Image image = icon.getImage();
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), icon.getImageObserver());
    }
}
