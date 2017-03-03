package ui;

import map.GridMap;
import map.MapIcons;
import map.MapItem;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is to build the core part of the map editor
 * after player input the size of the map, This screen will shown to player, depended on the input size.
 * @author Tann Chen
 */
public class GridMapEditorScreen extends Screen{

    private int rowsNum;
    private int columnsNum;
    private GridMap belongGridMap;


    /*JComponents*/
    private JTextField jtxtMessage;
    private JButton[][] buttons;
    private JButton jbtExit;
    private JButton jbtSave;

    private int paddingUp=40;
    private int paddingLeft=150;

    public GridMapEditorScreen(int rows, int columns){

        this.rowsNum=rows;
        this.columnsNum=columns;

        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setForeground(Color.green);
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);

        //center
        JPanel center= new JPanel();
        center.setLayout(null);
        center.setSize(800,489);
        center.setLocation(0,41);

        buttons = new JButton[this.rowsNum][this.columnsNum];
        for(int r=0 ; r<this.rowsNum;r++){
            for(int c=0 ; c<this.columnsNum ; c++){
                buttons[r][c]=new JButton();
                buttons[r][c].setBounds(paddingLeft+c*35, paddingUp+r*35,39,39);
                buttons[r][c].setName(r+","+c);
                center.add(buttons[r][c]);
            }
        }
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




        /*action listeners*/

        for(int r=0 ; r<this.rowsNum;r++){
            for(int c=0 ; c<this.columnsNum ; c++){
                buttons[r][c].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        JButton button=(JButton)e.getSource();
                        String str=button.getName();
                        String split[]=str.split(",");
                        int r=Integer.parseInt(split[0]);
                        int c=Integer.parseInt(split[1]);
                        SelectMapItemDialog selectMapItemDialog =new SelectMapItemDialog();
                        String selected = selectMapItemDialog.getSelectedType();
                        selectMapItemDialog.dispose();
                        button.setIcon(MapIcons.getMapIconsManager().getIcons(selected));
                        MapItem newMapItem=createMapItem(selected,c,r);
                        belongGridMap.setItem(newMapItem);
                    }
                });
            }
        }

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               GridMapEditorScreen.this.belongWindow.popScreen();
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
                GridMapEditorScreen.this.belongWindow.popScreen();
                GridMapEditorScreen.this.belongWindow.popScreen();
            }
        });

    }


    /**
     * The method is used to combine a grid map editor screen with a gridMap(model)
     */
    public void setBelongGridMap(GridMap gm){
        this.belongGridMap=gm;
    }

    /**
     * The method is used to create a MapItem instance according player's choice
     */
    public MapItem createMapItem(String type, int x, int y){
        MapItem newMapItem=new MapItem();
        newMapItem.setLocation(x,y);
        if(type.equals("Entry"))
            newMapItem.setItemType(MapItem.ENTRY);
        else if(type.equals("Exit"))
            newMapItem.setItemType(MapItem.EXIT);
        else if(type.equals("Wall"))
            newMapItem.setItemType(MapItem.WALL);
        else if(type.equals("Chest"))
            newMapItem.setItemType(MapItem.CHEST);
        else if(type.equals("Character"))
            newMapItem.setItemType(MapItem.CHARACTER);
        return newMapItem;
    }

}
