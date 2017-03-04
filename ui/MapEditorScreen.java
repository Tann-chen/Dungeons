package ui;

import map.GridMap;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is used to build the main screen in map editor
 */
public class MapEditorScreen extends Screen{


    private JButton jbtEdit;
    private JButton jbtNew;
    private JButton jbtExit;

    public MapEditorScreen(){

        // Button1
        jbtNew=new JButton();
        jbtNew.setText("New map");
        jbtNew.setSize(200,45);
        jbtNew.setLocation(300,150);
        this.add(jbtNew);


        //button2
        jbtEdit=new JButton();
        jbtEdit.setText("Edit map");
        jbtEdit.setSize(200,45);
        jbtEdit.setLocation(300,230);
        this.add(jbtEdit);

        //button3
        jbtExit=new JButton();
        jbtExit.setText("Exit");
        jbtExit.setSize(200,45);
        jbtExit.setLocation(300,310);
        this.add(jbtExit);

         /* action listeners*/

        jbtNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMapScreen newMapScreen=new NewMapScreen();
                MapEditorScreen.this.belongWindow.pushScreen(newMapScreen);
                newMapScreen.setBelongWindow(MapEditorScreen.this.belongWindow);
            }
        });

        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridMap newGridMap= new GridMap();
                newGridMap.load();
                int rows = newGridMap.getRowsNum();
                int columns = newGridMap.getColumnsNum();
                GridMapEditorScreen gridMapEditorScreen = new GridMapEditorScreen(rows,columns,newGridMap);
                gridMapEditorScreen.setBelongWindow(MapEditorScreen.this.belongWindow);
                MapEditorScreen.this.belongWindow.pushScreen(gridMapEditorScreen);
                newGridMap.addObserver(gridMapEditorScreen);

            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               MapEditorScreen.this.belongWindow.popScreen();
            }
        });





    }


}
