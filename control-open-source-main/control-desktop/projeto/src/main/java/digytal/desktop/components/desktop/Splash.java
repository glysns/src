package digytal.desktop.components.desktop;

import java.awt.*;

import javax.swing.*;

import digytal.desktop.components.Imagens;

//https://stackoverflow.com/questions/16343098/resize-a-picture-to-fit-a-jlabel
public class Splash extends JFrame {
    public Splash() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setUndecorated(true);
    }
    public void show(String slogan) {
        show(slogan,"app/app-splash",400,300);
    }
    public void show(String slogan, String png,int width, int height){
        JLabel image = new JLabel("");
        ImageIcon img= Imagens.png( png);
        image.setIcon(new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));

        image.setBorder(BorderFactory.createEtchedBorder());
        getContentPane().add(image, BorderLayout.CENTER);

        JLabel label = new JLabel(slogan);
        label.setBorder(BorderFactory.createEtchedBorder());
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setForeground(Color.BLUE);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        getContentPane().add(label, BorderLayout.SOUTH);

        setSize(width,height);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
