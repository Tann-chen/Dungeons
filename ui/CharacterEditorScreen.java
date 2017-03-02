package ui;

import characters.Character;
import characters.CharacterManager;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;

/**
 *The class is to define the screen in which player can create and edit the character
 * The class implements Observer in Observer pattern to monitor the characterManager
 */
public class CharacterEditorScreen extends Screen implements Observer{

     /*JComponents*/

    private JTextField jtxtMessage;
    private JList<String> characterList;

    private JLabel jlbCharacterName;
    private JTextField jtxtChaName;

    private JLabel jlbLevel;
    private JTextField jtxtLevel;

    private JLabel jlbStrength;
    private JTextField jtxtStrength;
    private JTextField jtxtStrengthModi;

    private JLabel jlbDexterity;
    private JTextField jtxtDexterity;
    private JTextField jtxtDexterityModi;

    private JLabel jlbConsititution;
    private JTextField jtxtConstitution;
    private JTextField jtxtConsitutionModi;

    private JLabel jlbIntelligence;
    private JTextField jtxtIntelligence;
    private JTextField jtxtIntelligenceModi;

    private JLabel jlbWisdom;
    private JTextField jtxtWisdom;
    private JTextField jtxtWisdomModi;

    private JLabel jlbCharisma;
    private JTextField jtxtCharisma;
    private JTextField jtxtCharismaModi;


    private JButton jbtPartialSave;

    private JButton jbtExit;
    private JButton jbtEdit;
    private JButton jbtCreate;
    private JButton jbtSave;
    private JButton jbtDelete;



    // Partial save button switch
    private int partialSaveSwitch;
    //the elements of character in JList
    private ArrayList<Character> charactersList;
    //the selected item in JList
    private String selectedChaName;


    public CharacterEditorScreen(){

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
        characterList=new JList<String>();//TODO:滑动后才能显示内容
        characterList.setBackground(Color.WHITE);
        characterList.setSelectionForeground(Color.RED);
        characterList.setSelectionBackground(Color.CYAN);
        JScrollPane scrollList =new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollList.getViewport().setView(characterList);
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

        //label of character name
        jlbCharacterName= new JLabel();
        Screen.uniLabelStyle(jlbCharacterName);
        jlbCharacterName.setText("Character name");
        jlbCharacterName.setSize(100,40);
        jlbCharacterName.setLocation(45,60);
        centerView.add(jlbCharacterName);
        //add text of character name
        jtxtChaName = new JTextField();
        jtxtChaName.setEditable(false);
        jtxtChaName.setSize(100,36);
        jtxtChaName.setLocation(155,60);
        centerView.add(jtxtChaName);

        //label of strength
        jlbStrength= new JLabel();
        Screen.uniLabelStyle(jlbStrength);
        jlbStrength.setText("Strength");
        jlbStrength.setSize(100,40);
        jlbStrength.setLocation(45,160);
        centerView.add(jlbStrength);
        //add text of character name
        jtxtStrength = new JTextField();
        jtxtStrength.setEditable(false);
        jtxtStrength.setSize(50,36);
        jtxtStrength.setLocation(150,160);
        centerView.add(jtxtStrength);
        //modi
        jtxtStrengthModi = new JTextField();
        jtxtStrengthModi.setEditable(false);
        jtxtStrengthModi.setSize(50,36);
        jtxtStrengthModi.setLocation(205,160);
        centerView.add(jtxtStrengthModi);

        //dexterity
        //label
        jlbDexterity= new JLabel();
        Screen.uniLabelStyle(jlbDexterity);
        jlbDexterity.setText("Dexterity");
        jlbDexterity.setSize(100,40);
        jlbDexterity.setLocation(45,260);
        centerView.add(jlbDexterity);
        //textField
        jtxtDexterity = new JTextField();
        jtxtDexterity.setEditable(false);
        jtxtDexterity.setSize(50,36);
        jtxtDexterity.setLocation(150,260);
        centerView.add(jtxtDexterity);
        //modi
        jtxtDexterityModi = new JTextField();
        jtxtDexterityModi.setEditable(false);
        jtxtDexterityModi.setSize(50,36);
        jtxtDexterityModi.setLocation(205,260);
        centerView.add(jtxtDexterityModi);
        //constitution
        //label
        jlbConsititution= new JLabel();
        Screen.uniLabelStyle(jlbConsititution);
        jlbConsititution.setText("Constitution");
        jlbConsititution.setSize(100,40);
        jlbConsititution.setLocation(45,360);
        centerView.add(jlbConsititution);
        //textField
        jtxtConstitution= new JTextField();
        jtxtConstitution.setEditable(false);
        jtxtConstitution.setSize(50,36);
        jtxtConstitution.setLocation(150,360);
        centerView.add(jtxtConstitution);
        //modi
        jtxtConsitutionModi= new JTextField();
        jtxtConsitutionModi.setEditable(false);
        jtxtConsitutionModi.setSize(50,36);
        jtxtConsitutionModi.setLocation(205,360);
        centerView.add(jtxtConsitutionModi);


        //right
        //label of character level
        jlbLevel= new JLabel();
        Screen.uniLabelStyle(jlbLevel);
        jlbLevel.setText("Level");
        jlbLevel.setSize(100,40);
        jlbLevel.setLocation(345,60);
        centerView.add(jlbLevel);
        //add text of character level
        jtxtLevel = new JTextField();
        jtxtLevel.setEditable(false);
        jtxtLevel.setSize(100,36);
        jtxtLevel.setLocation(455,60);
        centerView.add(jtxtLevel);

        //label of intelligence
        jlbIntelligence= new JLabel();
        Screen.uniLabelStyle(jlbIntelligence);
        jlbIntelligence.setText("Intelligence");
        jlbIntelligence.setSize(100,40);
        jlbIntelligence.setLocation(345,160);
        centerView.add(jlbIntelligence);
        //text
        jtxtIntelligence = new JTextField();
        jtxtIntelligence.setEditable(false);
        jtxtIntelligence.setSize(50,36);
        jtxtIntelligence.setLocation(450,160);
        centerView.add(jtxtIntelligence);
        //modi
        jtxtIntelligenceModi = new JTextField();
        jtxtIntelligenceModi.setEditable(false);
        jtxtIntelligenceModi.setSize(50,36);
        jtxtIntelligenceModi.setLocation(505,160);
        centerView.add(jtxtIntelligenceModi);
        //wisdom
        //label
        jlbWisdom= new JLabel();
        Screen.uniLabelStyle(jlbWisdom);
        jlbWisdom.setText("Wisdom");
        jlbWisdom.setSize(100,40);
        jlbWisdom.setLocation(345,260);
        centerView.add(jlbWisdom);
        //add text of character name
        jtxtWisdom = new JTextField();
        jtxtWisdom.setEditable(false);
        jtxtWisdom.setSize(50,36);
        jtxtWisdom.setLocation(450,260);
        centerView.add(jtxtWisdom);
        //modi
        jtxtWisdomModi = new JTextField();
        jtxtWisdomModi.setEditable(false);
        jtxtWisdomModi.setSize(50,36);
        jtxtWisdomModi.setLocation(505,260);
        centerView.add(jtxtWisdomModi);
        //charisma
        //label
        jlbCharisma= new JLabel();
        Screen.uniLabelStyle(jlbCharisma);
        jlbCharisma.setText("Intelligence");
        jlbCharisma.setSize(100,40);
        jlbCharisma.setLocation(345,360);
        centerView.add(jlbCharisma);
        //text
        jtxtCharisma = new JTextField();
        jtxtCharisma.setEditable(false);
        jtxtCharisma.setSize(50,36);
        jtxtCharisma.setLocation(450,360);
        centerView.add(jtxtCharisma);
        //modi
        jtxtCharismaModi = new JTextField();
        jtxtCharismaModi.setEditable(false);
        jtxtCharismaModi.setSize(50,36);
        jtxtCharismaModi.setLocation(505,360);
        centerView.add(jtxtCharismaModi);

        // add a button
        jbtPartialSave = new JButton();
        jbtPartialSave.setVisible(false);
        jbtPartialSave.setSize(110,36);
        jbtPartialSave.setLocation(240,445);
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


        /* initialized works */

        showTheList();
        this.partialSaveSwitch=0;




        /* Action listener */

        jbtCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtChaName.setEditable(true);
                jbtPartialSave.setVisible(true);
                jbtPartialSave.setText("Create");
                CharacterEditorScreen.this.partialSaveSwitch=1;
            }
        });

        jbtEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtxtLevel.setEditable(true);
                jtxtStrength.setEditable(true);
                jtxtDexterity.setEditable(true);
                jtxtConstitution.setEditable(true);
                jtxtIntelligence.setEditable(true);
                jtxtWisdom.setEditable(true);
                jtxtCharisma.setEditable(true);
                jbtPartialSave.setVisible(true);
                jbtPartialSave.setText("Edit");
                CharacterEditorScreen.this.partialSaveSwitch=2;
            }
        });

        jbtExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterEditorScreen.this.belongWindow.popScreen();
            }
        });

        jbtDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterManager.getCharacterManager().deleteCharacter(selectedChaName);
            }
        });

        jbtSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterManager.getCharacterManager().updateCharacterRecords();
            }
        });

        jbtPartialSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jtxtChaName.getText().trim().equals("")){
                    warningMessage("The character name can not be empty!");
                    return;
                }

                if(partialSaveSwitch==1){
                    String charactName=jtxtChaName.getText().trim();
                    Character newCha=CharacterManager.getCharacterManager().createCharacterInstance(charactName);
                    promptMessage("The item [" +charactName+"] is created successfully! " +
                            "Remember to save after all your editing ! " );
                    showSomeOneInfo(newCha);
                }
                else if(partialSaveSwitch==2){
                    String newLevel = jtxtLevel.getText().trim();
                    String newStrength=jtxtStrength.getText().trim();
                    String newDexterity=jtxtDexterity.getText().trim();
                    String newConstitution=jtxtConstitution.getText().trim();
                    String newIntelligence=jtxtIntelligence.getText().trim();
                    String newWisdom=jtxtWisdom.getText().trim();
                    String newCharisma=jtxtCharisma.getText().trim();

                    CharacterManager.getCharacterManager().editCharaterInfo(selectedChaName,newLevel,newStrength,
                            newDexterity,newConstitution,newIntelligence,newWisdom,newCharisma);
                }

            }
        });

        characterList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(characterList.isSelectionEmpty())
                    return;

                selectedChaName=characterList.getSelectedValue();
                Character selected =null;
                for(Character cha : CharacterEditorScreen.this.charactersList){
                    if(selectedChaName.equals(cha.getName()))
                        selected=cha;
                }
                showSomeOneInfo(selected);
                jbtEdit.setEnabled(true);
                jtxtChaName.setEditable(false);
                jtxtLevel.setEditable(false);
                jtxtStrength.setEditable(false);
                jtxtDexterity.setEditable(false);
                jtxtConstitution.setEditable(false);
                jtxtIntelligence.setEditable(false);
                jtxtWisdom.setEditable(false);
                jtxtCharisma.setEditable(false);
                jbtDelete.setEnabled(true);
            }
        });


    }

    /**
     * The method is used to prompt the information to inform the player
     */
    private void promptMessage(String message){
        jtxtMessage.setText(" Message : "+message);
        jtxtMessage.setForeground(Color.GREEN);
    }
    /**
     * The method is used to prompt the warning information to user
     */
    private void warningMessage(String message){
        jtxtMessage.setText(" Message : "+message);
        jtxtMessage.setForeground(Color.RED);
    }

    private void showTheList(){
        this.charactersList=CharacterManager.getCharacterManager().getCharactersList();
        int num=this.charactersList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=charactersList.get(i).getName();
        }
        characterList.setListData(showInJList);
    }


    /**
     * The method override update method in Observer to update the content
     */
    @Override public void update(Observable obs, Object o){
        this.charactersList=((CharacterManager)obs).getCharactersList();
        int num=this.charactersList.size();
        String[] showInJList=new String[num];
        for(int i=0; i<num;i++){
            showInJList[i]=charactersList.get(i).getName();
        }
        characterList.setListData(showInJList);
    }

    public void showSomeOneInfo(Character cha){
        jtxtChaName.setText(cha.getName());
        jtxtLevel.setText(String.valueOf(cha.getData("Level")));
        jtxtStrength.setText(String.valueOf(cha.getData("Strength")));
        jtxtDexterity.setText(String.valueOf(cha.getData("Dexterity")));
        jtxtConstitution.setText(String.valueOf(cha.getData("Constitution")));
        jtxtIntelligence.setText(String.valueOf(cha.getData("Intelligence")));
        jtxtWisdom.setText(String.valueOf(cha.getData("Wisdom")));
        jtxtCharisma.setText(String.valueOf(cha.getData("Charisma")));
    }

}
