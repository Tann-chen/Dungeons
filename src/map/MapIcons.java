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
    public ImageIcon iconBgc;
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
        iconBgc =new ImageIcon("icon/bgc.jpg");
    }

    public ImageIcon getIcons(int typeName)
    {
        if(typeName==0)
            return null;
        if(typeName==MapItem.CHARACTER)
            return iconCharacter;
        else if(typeName==MapItem.ENTRY)
            return iconEntry;
        else if(typeName==MapItem.EXIT)
            return iconExit;
        else if(typeName==MapItem.WALL)
            return iconWall;
        else if(typeName==MapItem.CHEST)
            return iconChest;
        else
            return null;
    }

    public ImageIcon getBackground(){
        return iconBgc;
    }

}
