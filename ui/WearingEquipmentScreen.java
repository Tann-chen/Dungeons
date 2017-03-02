package ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * @author Tann Chen
 */
public class WearingEquipmentScreen extends Screen{

    private JTextField jtxtMessage;
    public JList<String> repostory;
    public JList<String> backpack;
    public JList<String> wornEquipments;

    JButton jbtLeft1;
    JButton jbtRight1;
    JButton jbtLeft2;
    JButton jbtRight2;

    public WearingEquipmentScreen(){

        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setForeground(Color.green);
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);

        //repostory JList
        repostory=new JList<String>();
        repostory.setBackground(Color.WHITE);
        repostory.setSelectionForeground(Color.RED);
        repostory.setSelectionBackground(Color.CYAN);
        JScrollPane scrollList =new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.getViewport().setView(repostory);
        scrollList.setSize(190,489);
        scrollList.setLocation(3,40);
        this.add(scrollList);

        //backpack JList
        backpack=new JList<String>();
        backpack.setBackground(Color.WHITE);
        backpack.setSelectionForeground(Color.RED);
        backpack.setSelectionBackground(Color.CYAN);
        backpack.setLocation(206,40);
        backpack.setSize(190,489);
        this.add(backpack);

        //worn equips JList
        wornEquipments=new JList<String>();
        wornEquipments.setBackground(Color.WHITE);
        wornEquipments.setSelectionForeground(Color.RED);
        wornEquipments.setSelectionBackground(Color.CYAN);
        wornEquipments.setLocation(409,40);
        wornEquipments.setSize(190,489);
        this.add(wornEquipments);


        //footer
        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setSize(800,70);
        footer.setLocation(0,530);
        footer.setBackground(Color.white);
        Border footerBorder = BorderFactory.createEtchedBorder();
        footer.setBorder(footerBorder);
        //button1
        jbtLeft1 =new JButton();
        jbtLeft1.setText("<<<");
        Screen.uniButtionStyle(jbtLeft1);
        jbtLeft1.setSize(40,40);
        jbtLeft1.setLocation(100,7);
        footer.add(jbtLeft1);
        //button2
        jbtRight1 =new JButton();
        jbtRight1.setText(">>>");
        Screen.uniButtionStyle(jbtRight1);
        jbtRight1.setSize(40,40);
        jbtRight1.setLocation(155,7);
        footer.add(jbtRight1);
        // button 3
        jbtLeft2 =new JButton();
        jbtLeft2.setText("<<<");
        Screen.uniButtionStyle(jbtLeft2);
        jbtLeft2.setSize(40,40);
        jbtLeft2.setLocation(405,7);
        footer.add(jbtLeft2);
        //button4
        jbtRight2 =new JButton();
        jbtRight2.setText(">>>");
        Screen.uniButtionStyle(jbtRight2);
        jbtRight2.setSize(40,40);
        jbtRight2.setLocation(455,7);
        footer.add(jbtRight2);

        this.add(footer);



        repostory.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        backpack.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

        wornEquipments.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

            }
        });

    }


}
