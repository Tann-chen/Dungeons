package ui;

import map.GridMap;
import java.io.File;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is to build the map editor screen
 * @author Tann Chen
 */
public class NewMapScreen extends Screen{

    /*JComponents*/

    private JLabel jlbName;
    private JTextField jtxtName;

    private JLabel jlbRowsNum;
    private JTextField jtxtRowsNum;

    private JLabel jlbColumnsNum;
    private JTextField jtxtColumnsNum;

    private JButton jbtExit;
    private JButton jbtNext;


    public NewMapScreen(){
        JPanel center = new JPanel();
        center.setLayout(null);
        center.setBounds(0,0,800,530);

        // name
        jlbName=new JLabel();
        jlbName.setText("Map name");
        jlbName.setSize(100,40);
        jlbName.setLocation(225,100);
        center.add(jlbName);

        jtxtName=new JTextField();
        jtxtName.setSize(150,36);
        jtxtName.setLocation(350,100);
        center.add(jtxtName);

        //rows
        jlbRowsNum=new JLabel();
        jlbRowsNum.setText("Row number");
        jlbRowsNum.setSize(100,40);
        jlbRowsNum.setLocation(225,200);
        center.add(jlbRowsNum);

        jtxtRowsNum=new JTextField();
        jtxtRowsNum.setSize(150,36);
        jtxtRowsNum.setLocation(350,200);
        center.add(jtxtRowsNum);

        //columns
        jlbColumnsNum=new JLabel();
        jlbColumnsNum.setText("Column number");
        jlbColumnsNum.setSize(120,40);
        jlbColumnsNum.setLocation(225,300);
        center.add(jlbColumnsNum);

        jtxtColumnsNum=new JTextField();
        jtxtColumnsNum.setSize(150,36);
        jtxtColumnsNum.setLocation(350,300);
        center.add(jtxtColumnsNum);
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
        jbtNext= new JButton();
        jbtNext.setText("Next");
        Screen.uniButtionStyle(jbtNext);
        jbtNext.setSize(130,36);
        jbtNext.setLocation(660,7);
        footer.add(jbtNext);
        this.add(footer);


        /* action listeners*/

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewMapScreen.this.belongWindow.popScreen();
            }
        });

        jbtNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtxtName.getText();
                String rowsNum = jtxtRowsNum.getText();
                String columnsNum = jtxtColumnsNum.getText();
                if(name.equals("")){
                    System.out.println(" map name can not be empty");
                    return;
                }
                if(rowsNum.equals("")){
                    System.out.println("Rows number can not be empty");
                    return;
                }
                if(columnsNum.equals("")){
                    System.out.println("Rows number can not be empty");
                    return;
                }

                File file = new File("data/maps/"+jtxtName.getText().trim()+".xml");
                if(file.exists()){
                    System.out.println("The map name has exited,change file name");
                    return;
                }

                int rows=Integer.parseInt(rowsNum);
                int columns=Integer.parseInt(columnsNum);

                GridMap newGridMap = new GridMap(rows,columns);
                newGridMap.setMapName(jtxtName.getText().trim());
                GridMapEditorScreen gridMapEditorScreen = new GridMapEditorScreen(rows,columns,newGridMap);
                gridMapEditorScreen.setBelongWindow(NewMapScreen.this.belongWindow);
                NewMapScreen.this.belongWindow.pushScreen(gridMapEditorScreen);
                newGridMap.addObserver(gridMapEditorScreen);
            }
        });
    }


}
