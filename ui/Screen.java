package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author Tann chen
 */
public class Screen extends JPanel{

    protected Window belongWindow;

    public Screen(){
        this.setLayout(null);
        this.setSize(800,600);

    }

    /* Uniform JComponents style*/

    public static void uniButtionStyle(JButton jbt){

        jbt.setFont(new Font("Times",0,15));
        jbt.setForeground(Color.BLACK);
    }

    public static void uniLabelStyle(JLabel jlb){
        jlb.setFont(new Font("Times",0,16));
        jlb.setForeground(Color.DARK_GRAY);
    }

    public static void uniTextField(JTextField jtxt){
        jtxt.setBackground(Color.WHITE);
        jtxt.setForeground(Color.black);
        jtxt.setSelectionColor(Color.YELLOW);
        jtxt.setSelectedTextColor(Color.red);

    }

    public static void uniTextArea(JTextArea jtxtArea){
        jtxtArea.setBackground(Color.white);
        jtxtArea.setForeground(Color.black);
    }

    public void setBelongWindow(Window window){this.belongWindow=window;}


}
