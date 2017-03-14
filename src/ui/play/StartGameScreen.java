package ui.play;

import ui.Screen;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The method is used to build the screen of starting game with the functions of selecting character and campaign to play
 * @author Tann chen
 */
public class StartGameScreen extends Screen {

    private JTextField jtxtMessage;
    private JLabel jlbSelectCha;
    private JLabel jlbSelectCamp;
    private JComboBox<String>jcomSelectCha;
    private JButton jbtLoadCamp;
    private JButton jbtExit;
    private JButton jbtStart;


    public StartGameScreen(){

        //message
        jtxtMessage=new JTextField();
        jtxtMessage.setEditable(false);
        jtxtMessage.setText(" Message : ");
        jtxtMessage.setForeground(Color.green);
        jtxtMessage.setSize(800,40);
        jtxtMessage.setLocation(0,0);
        jtxtMessage.setBackground(Color.white);
        this.add(jtxtMessage);


        //center view
        JPanel centerView=new JPanel();
        centerView.setLayout(null);
        centerView.setSize(800,490);
        centerView.setLocation(0,40);
        centerView.setBackground(Color.WHITE);
        Border centerViewBorder= BorderFactory.createEtchedBorder();
        centerView.setBorder(centerViewBorder);
        //label select character
        jlbSelectCha=new JLabel();
        jlbSelectCha.setText("Select  a  Hero");
        jlbSelectCha.setSize(100,40);
        jlbSelectCha.setLocation(260,150);
        centerView.add(jlbSelectCha);
        //jCom to select character
        jcomSelectCha=new JComboBox<String>();
        jcomSelectCha.setSize(100,40);
        jcomSelectCha.setLocation(370,150);
        centerView.add(jcomSelectCha);
        jcomSelectCha.doLayout();
        //label campaign
        jlbSelectCamp=new JLabel();
        jlbSelectCamp.setText("Load campaign");
        jlbSelectCamp.setSize(100,40);
        jlbSelectCamp.setLocation(260,230);
        centerView.add(jlbSelectCamp);
        //button to open loading
        jbtLoadCamp=new JButton();
        jbtLoadCamp.setText("...");
        Screen.uniButtionStyle(jbtLoadCamp);
        jbtLoadCamp.setSize(35,35);
        jbtLoadCamp.setLocation(370,230);
        centerView.add(jbtLoadCamp);
        this.add(centerView);


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
        jbtStart= new JButton();
        jbtStart.setText("Save");
        Screen.uniButtionStyle(jbtStart);
        jbtStart.setSize(130,36);
        jbtStart.setLocation(660,7);
        footer.add(jbtStart);
        this.add(footer);



        jbtLoadCamp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               //TODO
            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

        jbtStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });

    }
}
