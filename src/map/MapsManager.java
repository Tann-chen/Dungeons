package map;

import archive.FileOperator;
import characters.Character;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

/**
 * The class is used to manager the maps
 * Created by t.e.chen on 2017-03-03.
 * The class uses singleton design pattern
 */
public class MapsManager extends Observable{

    //solo instance
    private static MapsManager mapsManager;
    //list to store all maps in the game
    private ArrayList<GridMap>mapsList;
    //the file name of record characters
    private static final String MAPS_FILE_PATH="data/maps.xml";

    /**
     * Constructor
     */
    public MapsManager(){
        mapsList=new ArrayList<GridMap>();
    }
    /**
     *The method will be invoked in outside class where the MapsManager is required
     */
    public static MapsManager getMapsManager() {
        if (mapsManager == null)
            mapsManager = new MapsManager();
        return mapsManager;
    }

    /**
     * The method is used to add map in the game records after creation
     */
    public void addMap(GridMap map){
        mapsList.add(map);
        //Observer pattern
        setChanged();
        notifyObservers(this);
    }
    /**
     * The method is used to remove map from the game records
     */
    public void removeMaps(GridMap map){
        mapsList.remove(map);
        //Observer pattern
        setChanged();
        notifyObservers(this);
    }

    /**
     * The method is used to get the all maps in the game
     */
    public ArrayList<GridMap> getMapsList() {
        return mapsList;
    }

    /**
     * The method is used to load the maps list in the beginning of the game
     */
    public void loadMapsModule(){
        Element rootElement = FileOperator.fileReader(MAPS_FILE_PATH);
        if(rootElement!=null){
            Iterator i = rootElement.elementIterator();
            while (i.hasNext()) {
                Element element = (Element) i.next();
                GridMap map =new GridMap();
                map.decode(element);
                mapsList.add(map);
            }
        }
    }

    /**
     * The method will be used to update the maps records after player confirms the modification or creation of maps
     */
    public void updateMapsRecords(){

        // The file to save the maps records
        File file= new File(MAPS_FILE_PATH);
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        if(mapsList==null)
            return;
        for(GridMap themap:mapsList){
            rootElement.add(themap.encode());
        }
        FileOperator.fileWriter(file,document);
    }

    /**
     * The method is used to delete a map in UI
     */
    public void deleteMap(String s){
        GridMap target=null;
        for(GridMap gm:mapsList){
            if(gm.getMapName().equals(s))
                target=gm;
        }
        removeMaps(target);
    }



}
