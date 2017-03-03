package ui;

import characters.CharacterManager;
import items.EquipmentManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * The class is to define the main screen, which is shown in the beginning of the game
 * There are several buttons with different navigation in the main screen
 * @author Tann Chen
 */
public class MainScreen extends Screen{

    private JButton jbtStartGame;
    private JButton jbtMapEditor;
    private JButton jbtCharacterEditor;
    private JButton jbtItemEditor;
    private JLabel jlbTitle;

    public MainScreen(){

        // add a label
        jlbTitle = new JLabel();
        jlbTitle.setText("Dungeons");
        Screen.uniLabelStyle(jlbTitle);
        jlbTitle.setFont(new Font("Times", 0, 90));
        jlbTitle.setHorizontalAlignment(SwingConstants.CENTER);
        jlbTitle.setLocation(160, 120);
        jlbTitle.setSize(500, 100);
        this.add(jlbTitle);

        // add a start game button
        jbtStartGame = new JButton();
        jbtStartGame.setText("Start Game");
        jbtStartGame.setLocation(300, 300);
        jbtStartGame.setSize(200, 40);
        this.add(jbtStartGame);


        // add a map editor button
        jbtMapEditor = new JButton();
        jbtMapEditor.setText("Edit Maps");
        jbtMapEditor.setLocation(300, 350);
        jbtMapEditor.setSize(200, 40);
        this.add(jbtMapEditor);

        // add a character editor button
        jbtCharacterEditor = new JButton();
        jbtCharacterEditor.setText("Edit Characters");
        jbtCharacterEditor.setLocation(300, 400);
        jbtCharacterEditor.setSize(200, 40);
        this.add(jbtCharacterEditor);

        // add an item editor button
        jbtItemEditor = new JButton();
        jbtItemEditor.setText("Edit Items");
        jbtItemEditor.setLocation(300, 450);
        jbtItemEditor.setSize(200, 40);
        this.add(jbtItemEditor);




        /*Action Listeners*/


        jbtStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        jbtMapEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MapEditorScreen mapEditorScreen =new MapEditorScreen();
                MainScreen.this.belongWindow.pushScreen(mapEditorScreen);
                mapEditorScreen.setBelongWindow(MainScreen.this.belongWindow);
            }
        });

        jbtCharacterEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CharacterEditorScreen characterEditorScreen =new CharacterEditorScreen();
                MainScreen.this.belongWindow.pushScreen(characterEditorScreen);
                characterEditorScreen.setBelongWindow(MainScreen.this.belongWindow);
                CharacterManager.getCharacterManager().addObserver(characterEditorScreen);
            }
        });

        jbtItemEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemEditorScreen itemEditorScreen = new ItemEditorScreen();
                MainScreen.this.belongWindow.pushScreen(itemEditorScreen);
                itemEditorScreen.setBelongWindow(MainScreen.this.belongWindow);
                EquipmentManager.getEquipmentManager().addObserver(itemEditorScreen);
            }
        });


    }




}
