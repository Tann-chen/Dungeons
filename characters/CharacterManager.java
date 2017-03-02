package characters;

import archive.FileOperator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.File;
import java.util.*;


/**
 * This class is used to define the character manager.
 * The function 1 of character manager is to load all recorded characters in the beginning of the game
 * The function 2 of character manager is to update the character records to the file
 * The class uses Observer pattern to notify the view about the changed data
 * The class is designed in singleton pattern
 * @author Tianen Chen
 */
public class CharacterManager extends Observable{

    //solo instance
    private static CharacterManager characterManager;
    //store all characters in the game
    private ArrayList<Character> charactersList;
    //the file name of record characters
    private static final String CHARACTERS_FILE_PATH="data/characters.xml";

    /**
     * Constructor
     */
    public CharacterManager(){
        charactersList=new ArrayList<Character>();
    }

    /**
     *The method will be invoked in outside class where the characterManager is required
     */
    public static CharacterManager getCharacterManager(){
        if(characterManager==null)
            characterManager=new CharacterManager();
        return characterManager;
    }
    /**
     * The method is used to add character in the game records after creation
     */
    public void addCharacter(Character cha){
        charactersList.add(cha);
        //Observer pattern
        setChanged();
        notifyObservers(this);
    }

    /**
     * The method is used to remove character from the game records
     */
    public void removeCharacter(Character cha){
        charactersList.remove(cha);
        //Observer pattern
        setChanged();
        notifyObservers(this);
    }

    public ArrayList<Character> getCharactersList(){return this.charactersList;}

    /**
     * The method is used to load the characters lsit in the beginning of the game
     */
    public void loadCharacterModule(){
        Element rootElement = FileOperator.fileReader(CHARACTERS_FILE_PATH);
        if(rootElement!=null){
            Iterator i = rootElement.elementIterator();
            while (i.hasNext()) {
                Element element = (Element) i.next();
                Character character = new Character();
                character.decode(element);
                charactersList.add(character);
            }
        }
    }

    /**
     * The method will be used to update the characters records after player confirms the modification or creation of characters
     */
    public void updateCharacterRecords(){
        // The file to save the character records
        File file= new File(CHARACTERS_FILE_PATH);
        // The document to record the characters
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        if(charactersList==null)
            return;
        for(Character cha:charactersList){
            rootElement.add(cha.encode());
        }
        FileOperator.fileWriter(file,document);
    }

    /**
     * The method will be invoked in Ui to create an character object
     */
    public Character createCharacterInstance(String chaName){
        Character newCharacter = new Character(chaName);
        addCharacter(newCharacter);
        return newCharacter;
    }

    /**
     * The method is used to delete a character in Ui
     */
    public void deleteCharacter(String s){
        Character target=null;
        for(Character c:charactersList){
            if(c.getName().equals(s))
                c=target;
        }
        removeCharacter(target);
    }

    /**
     * The method is used to edit the information of character
     */
    public void editCharaterInfo(String name, String level,String strength,String dexterity,String constitution,
                                 String intelligence,String wisdom,String charisma){
        Character target=null;
        for(Character c :charactersList){
            if(c.getName().equals(name))
                target=c;
        }
        target.setDataOfCha("Level",Integer.valueOf(level));
        target.setDataOfCha("Strength",Integer.valueOf(strength));
        target.setDataOfCha("Dexterity",Integer.valueOf(dexterity));
        target.setDataOfCha("Constitution",Integer.valueOf(constitution));
        target.setDataOfCha("Intelligence", Integer.valueOf(intelligence));
        target.setDataOfCha("Wisdom",Integer.valueOf(wisdom));
        target.setDataOfCha("Charisma",Integer.valueOf(charisma));
    }
}
