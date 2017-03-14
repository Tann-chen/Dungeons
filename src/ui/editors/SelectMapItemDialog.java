package ui.editors;

import characters.Character;
import characters.CharacterManager;
import map.MapIcons;
import map.MapItem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * The class is to build a dialog where player can select map items when they are editing a map
 * @author Tann Chen
 */
public class SelectMapItemDialog extends JDialog {


    private JPanel centerView;
    private JPanel characterSetView;
    private JButton[] buttons;
    private JComboBox<String> jcomCharacters;
    private JButton jbtFriendly;
    private JButton jbtHostile;


    private int[] itemTypes={MapItem.ENTRY, MapItem.EXIT,MapItem.WALL,MapItem.CHEST,MapItem.CHARACTER};

    public final int paddingUp=120;
    public final int paddingLeft=120;

    //records the selected info
    private int selectedType;
    private String selectedCharacter;
    private int selectedCharacterAttitude;



    public SelectMapItemDialog() {

        this.setSize(500,300);
        this.setLocation(100,100);
        this.getContentPane().setLayout(new BorderLayout());


        //center view with many buttons
        centerView =new JPanel();
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
        }

        characterSetView=new JPanel();
        characterSetView.setBorder(new EmptyBorder(5, 5, 30, 5));
        this.getContentPane().add(characterSetView,BorderLayout.SOUTH);

        //jcomCharacters
        jcomCharacters=new JComboBox<String>(obtainCharacterNames());
        jcomCharacters.setVisible(false);
        jcomCharacters.setSelectedIndex(-1);
        characterSetView.add(jcomCharacters);

        //jbtFriendly
        jbtFriendly=new JButton();
        jbtFriendly.setText("Friendly");
        jbtFriendly.setVisible(false);
        characterSetView.add(jbtFriendly);


        //jbtHostile
        jbtHostile =new JButton();
        jbtHostile.setText("Hostile");
        jbtHostile.setVisible(false);
        characterSetView.add(jbtHostile);

        /* Action listeners*/

        for(int i=0;i<itemTypes.length-1;i++)
        {
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

        buttons[itemTypes.length-1].addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                jcomCharacters.setVisible(true);
                jbtFriendly.setVisible(true);
                jbtHostile.setVisible(true);
            }
        });

        jbtFriendly.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(jcomCharacters.getSelectedIndex()==-1)
                    return;
                selectedCharacter=(String)jcomCharacters.getSelectedItem();
                selectedCharacterAttitude=Character.FRIENDLY;
                selectedType=MapItem.CHARACTER;
                setVisible(false);
            }
        });

        jbtHostile.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                if(jcomCharacters.getSelectedIndex()==-1)
                    return;
                selectedCharacter=(String)jcomCharacters.getSelectedItem();
                selectedCharacterAttitude=Character.HOSTILE;
                selectedType=MapItem.CHARACTER;
                setVisible(false);
            }
        });


        /* other works*/

        this.setModal(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);

    }

    public String[] obtainCharacterNames(){
        ArrayList<Character> charactersList = CharacterManager.getCharacterManager().getCharactersList();
        String[] characterNames=new String[charactersList.size()];
        for(int i=0; i<charactersList.size();i++){
            characterNames[i]=charactersList.get(i).getName();
        }
        return characterNames;
    }


    public int getSelectedType(){return this.selectedType;}

    public String getSelectedCharacterName() {return selectedCharacter;}

    public int getSelectedCharacterAttitude() {return selectedCharacterAttitude;}
}

