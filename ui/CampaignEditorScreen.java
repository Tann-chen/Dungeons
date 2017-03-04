package ui;

import campaign.Campaign;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The class is used to build the campaign editor screen
 *
 */
public class CampaignEditorScreen extends Screen{

    private JTextField jtxtMessage;
    private JButton jbtload;
    private JButton jbtEdit;
    private JButton jbtNew;
    private JButton jbtExit;
    private Campaign holdCampaign;

    public CampaignEditorScreen(){


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
        center.setSize(600,560);
        center.setLocation(0,40);
        // Button1
        jbtNew=new JButton();
        jbtNew.setText("New campaign");
        jbtNew.setSize(200,45);
        jbtNew.setLocation(300,120);
        center.add(jbtNew);

        //Button2
        jbtload=new JButton();
        jbtload.setText("Load Campaign");
        jbtload.setSize(200,45);
        jbtload.setLocation(300,200);
        center.add(jbtload);

        //button3
        jbtEdit=new JButton();
        jbtEdit.setText("Edit Campaign");
        jbtEdit.setSize(200,45);
        jbtEdit.setLocation(300,280);
        center.add(jbtEdit);
        this.add(center);
        //button4
        jbtExit=new JButton();
        jbtExit.setText("Exit");
        jbtExit.setSize(200,45);
        jbtExit.setLocation(300,360);
        center.add(jbtExit);
        this.add(center);

        setJbtLoadStatus();





        /* action listeners*/

        jbtNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateCampaignScreen createCampaignScreen=new CreateCampaignScreen();
                CampaignEditorScreen.this.belongWindow.pushScreen(createCampaignScreen);
                createCampaignScreen.setBelongWindow(CampaignEditorScreen.this.belongWindow);

            }
        });

        jbtload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setJbtLoadStatus();
            }
        });

        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CampaignEditorScreen.this.belongWindow.popScreen();
            }
        });



    }


    private void setJbtLoadStatus(){
        if(this.holdCampaign==null)
            jbtEdit.setEnabled(false);
        else
            jbtEdit.setEnabled(true);
    }

}
