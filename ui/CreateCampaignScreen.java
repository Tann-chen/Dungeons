package ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is to build the creating campagin screen
 * @author Tann Chen
 */
public class CreateCampaignScreen extends Screen {

    private JTextField jtxtMessage;
    private JButton map1;
    private JButton map2;
    private JButton map3;
    private JButton map4;

    private JLabel line1;
    private JLabel line2;
    private JLabel line3;

    private JButton jbtExit;
    private JButton jbtSave;


    public CreateCampaignScreen(){

        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setForeground(Color.green);
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);

        // center
        JPanel center= new JPanel();
        center.setLayout(null);
        center.setSize(800,489);
        center.setLocation(0,41);
        //map1
        map1=new JButton();
        map1.setText("map1");
        map1.setSize(50,50);
        map1.setLocation(150,220);
        center.add(map1);
        //line1
        line1=new JLabel();
        line1.setText("------");
        line1.setSize(50,50);
        line1.setLocation(200,220);
        center.add(line1);
        //map2
        map2=new JButton();
        map2.setText("map2");
        map2.setSize(50,50);
        map2.setLocation(250,220);
        center.add(map2);
        //line2
        line2=new JLabel();
        line2.setText("------");
        line2.setSize(50,50);
        line2.setLocation(300,220);
        center.add(line2);
        //map3
        map3=new JButton();
        map3.setText("map3");
        map3.setSize(50,50);
        map3.setLocation(350,220);
        center.add(map3);
        //line3
        line3=new JLabel();
        line3.setText("------");
        line3.setSize(50,50);
        line3.setLocation(400,220);
        center.add(line3);
        //button4
        map4=new JButton();
        map4.setText("map4");
        map4.setSize(50,50);
        map4.setLocation(450,220);
        center.add(map4);

        this.add(center);


        //footer
        JPanel footer =new JPanel();
        footer.setLayout(null);
        footer.setSize(800,70);
        footer.setLocation(0,530);
        footer.setBackground(Color.white);
        Border footerBorder = BorderFactory.createEtchedBorder();
        footer.setBorder(footerBorder);
        //button1
        jbtExit=new JButton();
        jbtExit.setText("Exit");
        Screen.uniButtionStyle(jbtExit);
        jbtExit.setSize(130,36);
        jbtExit.setLocation(20,7);
        footer.add(jbtExit);
        //button2
        jbtSave= new JButton();
        jbtSave.setText("Save");
        Screen.uniButtionStyle(jbtSave);
        jbtSave.setSize(130,36);
        jbtSave.setLocation(660,7);
        footer.add(jbtSave);
        this.add(footer);


        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCampaignScreen.this.belongWindow.popScreen();
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }


}