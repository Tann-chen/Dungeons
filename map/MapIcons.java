package map;

import javax.swing.*;

/**
 * The class is used to obtain the icon for map items
 * @author Tann Chen
 */
public class MapIcons {


    public ImageIcon iconCharacter;
    public ImageIcon iconEntry;
    public ImageIcon iconExit;
    public ImageIcon iconWall;
    public ImageIcon iconChest;
    public ImageIcon iconFloor;
    //solo instance
    private static MapIcons mapIconsManager;

    /**
     * The method is used to obtain the mapIcons
     * The method uses singleton pattern
     */
    public static MapIcons getMapIconsManager()
    {
        if(mapIconsManager==null)
        {
            mapIconsManager=new MapIcons();
        }
        return mapIconsManager;


    }

    public MapIcons(){

        iconCharacter = new ImageIcon("icon/character.jpg");
        iconEntry = new ImageIcon("icon/entry.jpg");
        iconExit = new ImageIcon("icon/exit.jpg");
        iconWall = new ImageIcon("icon/wall.jpg");
        iconChest = new ImageIcon("icon/chest.jpg");
    }

    public ImageIcon getIcons(String typeName)
    {
        if(typeName==null)
            return null;
        if(typeName.equals("Character"))
            return iconCharacter;
        else if(typeName.equals("Entry"))
            return iconEntry;
        else if(typeName.equals("Exit"))
            return iconExit;
        else if(typeName.equals("Wall"))
            return iconWall;
        else if(typeName.equals("Chest"))
            return iconChest;
        else
            return null;
    }


}
