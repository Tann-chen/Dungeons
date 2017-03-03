package ui;

import map.MapIcons;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is to build a dialog where player can select map items when they are editing a map
 * @author Tann Chen
 */
public class SelectMapItemDialog extends JDialog {


    private JPanel centerView = new JPanel();
    private String[] itemTypes={"Entry","Exit","Wall","Chest","Character"};

    public final int paddingUp=120;
    public final int paddingLeft=120;
    private JButton[] buttons;
    private String selectedType;



    public SelectMapItemDialog() {

        buttons=new JButton[itemTypes.length];

        this.setSize(500,300);
        this.setLocation(100,100);
        this.getContentPane().setLayout(new BorderLayout());

        centerView.setLayout(new FlowLayout());
        centerView.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.getContentPane().add(centerView, BorderLayout.CENTER);

        buttons =new JButton[itemTypes.length];

        for(int i=0;i<itemTypes.length;i++)
        {
            buttons[i]=new JButton();
            buttons[i].setIcon(MapIcons.getMapIconsManager().getIcons(itemTypes[i]));
            buttons[i].setBounds(paddingLeft+i*35, paddingUp, 35,35);
            buttons[i].setName(String.valueOf(i));
            centerView.add(buttons[i]);

            /* action listener*/
            buttons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub

                    int index=Integer.parseInt(((JButton) e.getSource()).getName());
                    selectedType=itemTypes[index];
                    setVisible(false);
                }
            });

        }

        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public String getSelectedType(){return this.selectedType;}



}
