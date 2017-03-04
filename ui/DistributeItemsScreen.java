package ui;


import characters.Character;
import items.Equipment;
import items.EquipmentManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * The class is to build a screen to distribute the equipments from repository to a character
 * @author Tann Chen
 */
public class DistributeItemsScreen extends Screen implements Observer{

    private JTextField jtxtMessage;
    private JList<String> repositoryList;
    private JButton jbtArmor;
    private JButton jbtBelt;
    private JButton jbtBoots;
    private JButton jbtHelmet;
    private JButton jbtRing;
    private JButton jbtShield;
    private JButton jbtWeapon;
    private JButton jbtExit;
    private JButton jbtSave;
    private JButton jbtEquip;


    private ArrayList<Equipment> itemsList;
    private String selectedItemName;
    private Character targetCharacter;
    
    public DistributeItemsScreen(Character target){
        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setForeground(Color.green);
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);

        //JList
        repositoryList=new JList<String>();
        repositoryList.setBackground(Color.WHITE);
        repositoryList.setSelectionForeground(Color.RED);
        repositoryList.setSelectionBackground(Color.CYAN);
        repositoryList.setSize(195,489);
        repositoryList.setLocation(3,40);
        this.add(repositoryList);

        //right side
        JPanel right = new JPanel();
        right.setLayout(null);
        right.setBounds(200,40,600,489);
        //armor
        jbtArmor =new JButton();
        jbtArmor.setText("Armor");
        Screen.uniButtionStyle(jbtArmor);
        jbtArmor.setSize(220,220);
        jbtArmor.setLocation(190,135);
        right.add(jbtArmor);
        //shield
        jbtShield=new JButton();
        jbtShield.setText("Shield");
        Screen.uniButtionStyle(jbtShield);
        jbtShield.setSize(90,90);
        jbtShield.setLocation(90,135);
        right.add(jbtShield);
        //belt
        jbtBelt=new JButton();
        jbtBelt.setText("Belt");
        Screen.uniButtionStyle(jbtBelt);
        jbtBelt.setSize(90,90);
        jbtBelt.setLocation(90,265);
        right.add(jbtBelt);

        //helmet
        jbtHelmet=new JButton();
        jbtHelmet.setText("Helmet");
        Screen.uniButtionStyle(jbtHelmet);
        jbtHelmet.setSize(90,90);
        jbtHelmet.setLocation(255,15);
        right.add(jbtHelmet);

        //boots
        jbtBoots=new JButton();
        jbtBoots.setText("Boots");
        Screen.uniButtionStyle(jbtBoots);
        jbtBoots.setSize(120,70);
        jbtBoots.setLocation(240,380);
        right.add(jbtBoots);

        //ring
        jbtRing=new JButton();
        jbtRing.setText("Ring");
        Screen.uniButtionStyle(jbtRing);
        jbtRing.setSize(90,90);
        jbtRing.setLocation(430,135);
        right.add(jbtRing);
        //weapon
        jbtWeapon=new JButton();
        jbtWeapon.setText("Weapon");
        Screen.uniButtionStyle(jbtWeapon);
        jbtWeapon.setSize(90,90);
        jbtWeapon.setLocation(430,265);
        right.add(jbtWeapon);
        this.add(right);



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
        jbtEquip= new JButton();
        jbtEquip.setText("Equip");
        Screen.uniButtionStyle(jbtEquip);
        jbtEquip.setSize(130,36);
        jbtEquip.setLocation(310,7);
        jbtEquip.setVisible(false);
        footer.add(jbtEquip);
        this.add(footer);

        //button3
        jbtSave=new JButton();
        jbtSave.setText("Save");
        jbtSave.setSize(130,36);
        jbtSave.setLocation(660,7);
        footer.add(jbtSave);


        this.targetCharacter=target;
        showTheList();
        repaintUI();


        /* action listeners */

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DistributeItemsScreen.this.belongWindow.popScreen();
            }
        });

        jbtEquip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment selected =null;
                for(Equipment equip: itemsList){
                    if(equip.getEquipName().equals(selectedItemName))
                        selected=equip;
                }
                Equipment temp= targetCharacter.wearEquipment(selected);
                if(temp!=null)
                    EquipmentManager.getEquipmentManager().addEquipment(temp);
                EquipmentManager.getEquipmentManager().deleteItem(selected.getEquipName());
                repaintUI();

            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                DistributeItemsScreen.this.belongWindow.popScreen();

            }
        });


        repositoryList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedItemName=repositoryList.getSelectedValue();
                jbtEquip.setVisible(true);
            }
        });

        jbtArmor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp=targetCharacter.takeOffEquipment("Armor");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });

        jbtBelt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Equipment temp= targetCharacter.takeOffEquipment("Belt");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });
        jbtBoots.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp=targetCharacter.takeOffEquipment("Boots");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });
        jbtHelmet.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp= targetCharacter.takeOffEquipment("Helmet");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });
        jbtRing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp=targetCharacter.takeOffEquipment("Ring");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });
        jbtShield.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp= targetCharacter.takeOffEquipment("Shield");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });
        jbtWeapon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipment temp= targetCharacter.takeOffEquipment("Weapon");
                repaintUI();
                EquipmentManager.getEquipmentManager().addEquipment(temp);
            }
        });

    }
    /**
     * The method is used to show the list of items in repository
     */
    private void showTheList(){
        itemsList = EquipmentManager.getEquipmentManager().getEquipmentList();
        int num=this.itemsList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=itemsList.get(i).getEquipName();
        }
       repositoryList.setListData(showInJList);
    }

    /**
     * The method is used to update the right-side UI
     */
    private void repaintUI(){
        Set wornItemsTypes =targetCharacter.getWornEquipments().keySet();
        if(!wornItemsTypes.contains("Armor"))
            jbtArmor.setEnabled(false);
        else
            jbtArmor.setEnabled(true);

        if(!wornItemsTypes.contains("Belt"))
            jbtBelt.setEnabled(false);
        else
            jbtBelt.setEnabled(true);

        if(!wornItemsTypes.contains("Boots"))
            jbtBoots.setEnabled(false);
        else
            jbtBoots.setEnabled(true);

        if(!wornItemsTypes.contains("Helmet"))
            jbtHelmet.setEnabled(false);
        else
            jbtHelmet.setEnabled(true);

        if(!wornItemsTypes.contains("Ring"))
            jbtRing.setEnabled(false);
        else
            jbtRing.setEnabled(true);

        if(!wornItemsTypes.contains("Shield"))
            jbtShield.setEnabled(false);
        else
            jbtShield.setEnabled(true);

        if(!wornItemsTypes.contains("Weapon"))
            jbtWeapon.setEnabled(false);
        else
            jbtWeapon.setEnabled(true);
    }

    /**
     * The method override update method in Observer to update the content
     */
    @Override public void update(Observable obs, Object o){
        this.itemsList=((EquipmentManager)obs).getEquipmentList();
        int num=this.itemsList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=itemsList.get(i).getEquipName();
        }
        repositoryList.setListData(showInJList);
    }
}
