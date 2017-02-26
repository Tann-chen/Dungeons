package ui;

import items.Equipment;
import items.EquipmentManager;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.Observer;

/**
 * The class is to define the screen in which player can create and edit the items
 * The class implements Observer in Observer pattern to monitor the EquipmentManager
 * @author  Tann chen
 */
public class ItemEditorScreen extends Screen implements Observer {

    /*JComponents*/

    private JTextField jtxtMessage;
    private JList<String> itemsList;
    private JLabel icon;

    private JLabel jlbItemName;
    private JTextField jtxtItemName;

    private JLabel jlbItemType;
    private JComboBox<String> jcomItemType;

    private JLabel jlbBonusType;
    private JComboBox<String> jcomBonusType;

    private JLabel jlbBonusValue;
    private JTextField jtxtBonusValue;
    private JButton jbtPartialSave;

    private JButton jbtExit;
    private JButton jbtEdit;
    private JButton jbtCreate;
    private JButton jbtSave;
    private JButton jbtDelete;


    /*Other data fields*/

    //Elements of jcomItemType
    private String[] itemType={"Armor","Belt","Boots","Helmet","Ring","Shield","Weapon"};
    //stores the allowed bonus types for each kind of items
    private Map<String,String[]> allowedEquipBonusTypesMap;
    // Partial save button switch
    private int partialSaveSwitch;
    //The elements of equipments in JList
    private ArrayList<Equipment> equipmentsList;
    //the selected item in JList
    private String selectedItemName;



    public ItemEditorScreen(){

        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);


        //JList
        itemsList=new JList<String>();//TODO:滑动后才能显示内容
        itemsList.setBackground(Color.white);
        itemsList.setForeground(Color.BLACK);
        itemsList.setSelectionForeground(Color.blue);
        itemsList.setSelectionBackground(Color.gray);
        JScrollPane scrollList =new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.getViewport().setView(itemsList);
        scrollList.setSize(195,489);
        scrollList.setLocation(3,40);
        this.add(scrollList);


        //center view
        JPanel centerView=new JPanel();
        centerView.setLayout(null);
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

        // add a button
        jbtPartialSave = new JButton();
        jbtPartialSave.setVisible(false);
        jbtPartialSave.setSize(110,36);
        jbtPartialSave.setLocation(240,440);
        centerView.add(jbtPartialSave);

        this.add(centerView);



        //footer
        JPanel footer = new JPanel();
        footer.setLayout(null);
        footer.setSize(800,70);
        footer.setLocation(0,530);
        footer.setBackground(Color.white);
        Border footerBorder = BorderFactory.createEtchedBorder();
        footer.setBorder(footerBorder);
        //button1
        jbtCreate =new JButton();
        jbtCreate.setText("Create");
        Screen.uniButtionStyle(jbtCreate);
        jbtCreate.setSize(130,36);
        jbtCreate.setLocation(25,7);
        footer.add(jbtCreate);
        //button2
        jbtEdit=new JButton();
        jbtEdit.setText("Edit");
        Screen.uniButtionStyle(jbtEdit);
        jbtEdit.setSize(130,36);
        jbtEdit.setLocation(180,7);
        jbtEdit.setEnabled(false);
        footer.add(jbtEdit);
        //button3
        jbtDelete=new JButton();
        jbtDelete.setText("Delete");
        Screen.uniButtionStyle(jbtDelete);
        jbtDelete.setSize(130,36);
        jbtDelete.setLocation(335,7);
        footer.add(jbtDelete);

        //button4
        jbtSave=new JButton();
        jbtSave.setText("Save");
        Screen.uniButtionStyle(jbtSave);
        jbtSave.setSize(130,36);
        jbtSave.setLocation(490,7);
        jbtDelete.setEnabled(false);
        footer.add(jbtSave);
        //button5
        jbtExit=new JButton();
        jbtExit.setText("Exit");
        Screen.uniButtionStyle(jbtExit);
        jbtExit.setSize(130,36);
        jbtExit.setLocation(645,7);
        footer.add(jbtExit);
        this.add(footer);

        /* initialized works*/
        showTheList();
        this.allowedEquipBonusTypesMap=EquipmentManager.getEquipmentManager().getEquipAllowedBonusTypeMap();
        this.partialSaveSwitch=0;




        /* Action Listeners*/

        jbtCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  itemsList.clearSelection();
                initContentOfJComponent(jtxtItemName);
                initContentOfJComponent(jcomItemType);
                initContentOfJComponent(jcomBonusType);
                initContentOfJComponent(jtxtBonusValue);
                jbtPartialSave.setVisible(true);
                jbtPartialSave.setText("Create");
                partialSaveSwitch=1;
             //   jcomBonusType.setSelectedIndex(-1);
               // jcomItemType.setSelectedIndex(-1);
            }
        });

        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtBonusValue.setEditable(true);
                jcomBonusType.setEnabled(true);
                partialSaveSwitch=2;
                jbtPartialSave.setVisible(true);
                jbtPartialSave.setText("Modify");

            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemEditorScreen.this.belongWindow.popScreen();
            }
        });

        jbtDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedItemName!=null){
                    EquipmentManager.getEquipmentManager().deleteItem(selectedItemName);
                }
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EquipmentManager.getEquipmentManager().updateEquipmentRecords();
            }
        });

        jcomItemType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                jcomBonusType.removeAllItems();

               String selectedType=(String)jcomItemType.getSelectedItem();
                if(!ItemEditorScreen.this.allowedEquipBonusTypesMap.containsKey(selectedType)){
                    System.out.println("select Item Type");
                }
                else{
                    String[] allowedTypes = allowedEquipBonusTypesMap.get(selectedType);
                    for(String s :allowedTypes){
                        jcomBonusType.addItem(s);
                    }
                }
                jcomBonusType.setSelectedIndex(-1);
            }
        });

        itemsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(itemsList.isSelectionEmpty())
                    return;

                selectedItemName=itemsList.getSelectedValue();
                Equipment selected =null;
                for(Equipment equip : ItemEditorScreen.this.equipmentsList){
                    if(selectedItemName.equals(equip.getEquipName()))
                        selected=equip;
                }
                jtxtItemName.setText(selected.getEquipName());
                jcomItemType.setSelectedItem(String.valueOf(selected.getEquipType()));
                jcomBonusType.setSelectedItem(selected.getEnchantmentBonusType());
                jtxtBonusValue.setText(String.valueOf(selected.getBonusValue()));
                jbtEdit.setEnabled(true);
                jtxtItemName.setEditable(false);
                jcomItemType.setEnabled(false);
                jcomBonusType.setEnabled(false);
                jtxtBonusValue.setEditable(false);
                jbtPartialSave.setVisible(false);
                jbtDelete.setEnabled(true);
            }
        });

        jbtPartialSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtxtItemName.getText().trim().equals("")){
                    warningMessage("The item name can not be empty!");
                    return;
                }
                if(jcomItemType.getSelectedIndex()==-1){
                    warningMessage("Please select the type of the item !");
                    return;
                }
                if(jcomBonusType.getSelectedIndex()==-1){
                    warningMessage("Please select the bonus type of the item");
                    return;
                }
                if(jtxtBonusValue.getText().trim().equals("")){
                    warningMessage("Please assign the bonus value of the item!");
                    return;
                }
                if(Integer.parseInt(jtxtBonusValue.getText().trim())<0||Integer.parseInt(jtxtBonusValue.getText().trim())>5){
                    warningMessage("The bonus value of item mush range from 0 to 5 !");
                    jtxtBonusValue.setText("");
                    return;
                }

                if(partialSaveSwitch==1){
                    String itemName=jtxtItemName.getText().trim();
                    String itemType=(String)jcomItemType.getSelectedItem();
                    String bonusType=(String)jcomBonusType.getSelectedItem();
                    int bonusValue=Integer.parseInt(jtxtBonusValue.getText().trim());
                    EquipmentManager.getEquipmentManager().createItemInstance(itemName,itemType,bonusType,bonusValue);
                    promptMessage("The item [" +jtxtItemName.getText().trim()+"] is created successfully! " +
                                    "Remember to save after all your editing ! " );
                }
                else if(partialSaveSwitch==2){
                    String bonusType=(String)jcomBonusType.getSelectedItem();
                    int bonusValue=Integer.parseInt(jtxtBonusValue.getText().trim());
                    EquipmentManager.getEquipmentManager().editItemData(selectedItemName,bonusType,bonusValue);
                }

            }
        });

    }


    private void promptMessage(String message){
        jtxtMessage.setText(" Message : "+message);
        jtxtMessage.setForeground(Color.GREEN);
    }

    private void warningMessage(String message){
        jtxtMessage.setText(" Message : "+message);
        jtxtMessage.setForeground(Color.RED);
    }

    private void showTheList(){
        this.equipmentsList=EquipmentManager.getEquipmentManager().getEquipmentList();
        int num=this.equipmentsList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=equipmentsList.get(i).getEquipName();
        }
        itemsList.setListData(showInJList);
    }

    /**
     * The method override update method in Observer to update the content
     */
    @Override public void update(Observable obs,Object o){
        this.equipmentsList=((EquipmentManager)obs).getEquipmentList();
        int num=this.equipmentsList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=equipmentsList.get(i).getEquipName();
        }
        itemsList.setListData(showInJList);
    }

    /**
     * The method is to enable the JComponent and clear the content of it
     */
    private void initContentOfJComponent(JComponent component){
        if(component instanceof JTextField){
            ((JTextField) component).setEditable(true);
            ((JTextField) component).setText("");
        }
        else if(component instanceof JComboBox){
            ((JComboBox) component).setSelectedIndex(-1);
            component.setEnabled(true);
        }
    }


}
