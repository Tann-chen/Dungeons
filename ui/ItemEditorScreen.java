package ui;

import items.Equipment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.border.Border;

/**
 * The class is to define the screen in which player can create and edit the items
 * @author  Tann chen
 */
public class ItemEditorScreen extends Screen {

    private JTextField jtextMessage;
    private JList<Equipment> itemsList;
    private JLabel icon;

    private JLabel jlbItemName;
    private JTextField jtxtItemName;

    private JLabel jlbItemType;
    private JComboBox<String> jcomItemType;

    private JLabel jlbBonusType;
    private JComboBox<String> jcomBonusType;

    private JLabel jlbBonusValue;
    private JTextField jtxtBonusValue;

    private JButton jbtExit;
    private JButton jbtEdit;
    private JButton jbtCreate;
    private JButton jbtSave;

    /*Other data fields*/

    private String[] itemType={"Armor","Belt","Boots","Helmet","Ring","Shield","Weapon"};



    public ItemEditorScreen(){
        //message
        jtextMessage=new JTextField();
        jtextMessage.setEditable(false);
        jtextMessage.setText(" Message : ");
        jtextMessage.setSize(800,40);
        jtextMessage.setLocation(0,0);
        jtextMessage.setBackground(Color.white);
        this.add(jtextMessage);



        //list
        itemsList=new JList<Equipment>();
        itemsList.setSize(200,490);
        itemsList.setLocation(0,40);
        itemsList.setBackground(Color.white);
        this.add(itemsList);



        //center view
        JPanel centerView=new JPanel();
        centerView.setSize(600,490);
        centerView.setLocation(200,40);
        centerView.setBackground(Color.WHITE);
        Border centerViewBorder= BorderFactory.createEtchedBorder();
        centerView.setBorder(centerViewBorder);

        //add a JLabel to show icon
        icon =new JLabel();
        icon.setText("icon");
        icon.setSize(80,80);
        icon.setLocation(260,100);
        icon.setBackground(Color.CYAN);
        centerView.add(icon);

        //add item name label
        jlbItemName = new JLabel();
        Screen.uniLabelStyle(jlbItemName);
        jlbItemName.setText("Item name");
        jlbItemName.setSize(100,40);
        jlbItemName.setLocation(180,220);
        centerView.add(jlbItemName);

        //add text field of item name
        jtxtItemName = new JTextField();
        jtxtItemName.setEditable(false);
        jtxtItemName.setSize(150,36);
        jtxtItemName.setLocation(280,220);
        centerView.add(jtxtItemName);

        //add text type label
        jlbItemType= new JLabel();
        Screen.uniLabelStyle(jlbItemType);
        jlbItemType.setText("Item type");
        jlbItemType.setSize(100,40);
        jlbItemType.setLocation(180,270);
        centerView.add(jlbItemType);

        //add text of item type
        jcomItemType=new JComboBox<String>(this.itemType);
        jcomItemType.setEditable(false);
        jcomItemType.setEnabled(false);
        jcomItemType.setSize(150,40);
        jcomItemType.setLocation(280,270);
        centerView.add(jcomItemType);
        jcomItemType.setBounds(280,270,150,40);
        jcomItemType.doLayout();    // force show the layout

        // add label of item bonus type
        jlbBonusType = new JLabel();
        Screen.uniLabelStyle(jlbBonusType);
        jlbBonusType.setText("Bonus type");
        jlbBonusType.setSize(100,40);
        jlbBonusType.setLocation(180,320);
        centerView.add(jlbBonusType);

        //add test of bonus type
        jcomBonusType =new JComboBox<String>();
        jcomBonusType.setEditable(false);
        jcomBonusType.setEnabled(false);
        jcomBonusType.setSize(150,40);
        jcomBonusType.setLocation(280,320);
        centerView.add(jcomBonusType);
        jcomBonusType.setBounds(280,320,150,40);
        jcomBonusType.doLayout();   // force  show the layout

        //add label of bonus value
        jlbBonusValue = new JLabel();
        Screen.uniLabelStyle(jlbBonusValue);
        jlbBonusValue.setText("Bonus value");
        jlbBonusValue.setSize(100,40);
        jlbBonusValue.setLocation(180,370);
        centerView.add(jlbBonusValue);

        //add text field of bonus type
        jtxtBonusValue = new JTextField();
        jtxtBonusValue.setEditable(false);
        jtxtBonusValue.setSize(150,36);
        jtxtBonusValue.setLocation(280,370);
        centerView.add(jtxtBonusValue);

        this.add(centerView);



        //footer
        JPanel footer = new JPanel();
        footer.setSize(800,70);
        footer.setLocation(0,530);
        footer.setBackground(Color.white);
        Border footerBorder = BorderFactory.createEtchedBorder();
        footer.setBorder(footerBorder);
        //button1
        jbtCreate =new JButton();
        jbtCreate.setText("CREATE");
        Screen.uniButtionStyle(jbtCreate);
        jbtCreate.setSize(150,36);
        jbtCreate.setLocation(40,7);
        footer.add(jbtCreate);
        //button2
        jbtEdit=new JButton();
        jbtEdit.setText("EDIT");
        Screen.uniButtionStyle(jbtEdit);
        jbtEdit.setSize(150,36);
        jbtEdit.setLocation(230,7);
        footer.add(jbtEdit);
        //button3
        jbtSave=new JButton();
        jbtSave.setText("SAVE");
        Screen.uniButtionStyle(jbtSave);
        jbtSave.setSize(150,36);
        jbtSave.setLocation(420,7);
        footer.add(jbtSave);
        //button4
        jbtExit=new JButton();
        jbtExit.setText("EXIT");
        Screen.uniButtionStyle(jbtExit);
        jbtExit.setSize(150,36);
        jbtExit.setLocation(610,7);
        footer.add(jbtExit);
        this.add(footer);



        /* Action Listeners*/

        jbtCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtItemName.setEditable(true);
                jtxtBonusValue.setEditable(true);
                jcomBonusType.setEnabled(true);
                jtextMessage.setText(" Message :  The type of items is not allowed to edit");


            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemEditorScreen.this.belongWindow.popScreen();
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jcomItemType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                int index=jcomItemType.getSelectedIndex();


            }
        });

        jcomBonusType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

            }
        });

    }

}
