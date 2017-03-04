package map;

import archive.Archivable;
import archive.FileOperator;
import characters.Character;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Observable;

/**
 * The class is used to build the gridmap model
 * @author Tann Chen
 */
public class GridMap extends Observable implements Archivable{


    private String mapName;
    private int rowsNum;
    private int columnsNum;
    private MapItem[][] mapItems;

    /**
     * The default constructor
     * Only used in load data from xml file, the mapItem[][] will be initialize in decode()method
     */
    public GridMap(){}
    /**
     * Constructor
     */
    public GridMap(int rows, int columns){
        rowsNum=rows;
        columnsNum=columns;
        mapItems=new MapItem[rowsNum][columnsNum];
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {return mapName;}

    public int getRowsNum() {return rowsNum;}

    public int getColumnsNum() {return columnsNum;}

    public MapItem[][] getMapItems() {return mapItems;}

    public void setItem(MapItem item){
        int row =item.getYLocate();
        int column=item.getXLocate();
        this.mapItems[row][column]=item;
        setChanged();
        notifyObservers(this);
    }

    public void setItem(MapItem item, int x, int y){
        this.mapItems[y][x]=item;
    }


    public void initTheGridMap(ArrayList<MapItem> contents){
        if(contents.size()==0)
            return;
        for(MapItem tempitem : contents){
            if(this.mapItems[tempitem.getYLocate()][tempitem.getXLocate()]!=null)
                System.out.println("the map is wrong, items overlap");
            setItem(tempitem);
        }

    }


               /* Archiving */

   public static final String MAP_ITEMS="MapItems";

   /**
    * The method is used to encode data of a map in to a file
    */
   public Element encodeMapItems(){
       Element element =new DefaultElement(MAP_ITEMS);
       for(int r=0 ; r<this.rowsNum;r++){
           for(int c=0; c<this.columnsNum;c++){
               if(mapItems[r][c]!=null){
                   element.add(mapItems[r][c].encode());
               }
           }
       }
       return element;
   }
    /**
     * The method is used to read a map from a xml file
     */
   public void decodeMapItems(Element itemsElement){
       ArrayList<MapItem> contents = new ArrayList<MapItem>();
       Iterator i = itemsElement.elementIterator();
       while (i.hasNext()) {
           Element element = (Element) i.next();
           MapItem temp = new MapItem();
           temp.decode(element);
           contents.add(temp);
       }
       initTheGridMap(contents);
   }


    public static final String GRID_MAP="GridMap";
    public static final String MAP_NAME="MapName";
    public static final String ROWS_NUM="RowsNum";
    public static final String COLUMNS_NUM="ColumnsNum";


    public Element encode(){

        Element element = new DefaultElement(GRID_MAP);
        element.addElement(MAP_NAME).addText(this.mapName);
        element.addElement(ROWS_NUM).addText(String.valueOf(this.rowsNum));
        element.addElement(COLUMNS_NUM).addText(String.valueOf(this.columnsNum));
        element.add(encodeMapItems());
        return element;
    }

    public void decode(Element element){
        this.mapName=element.element(MAP_NAME).getText();
        this.rowsNum=Integer.parseInt(element.element(ROWS_NUM).getText());
        this.columnsNum=Integer.parseInt(element.element(COLUMNS_NUM).getText());
        //initialize the mapItem[][];
        mapItems = new MapItem[rowsNum][columnsNum];
        Element itemsElements = element.element(MAP_ITEMS);
        decodeMapItems(itemsElements);
    }


    public void save(){
       File file = new File("data/maps/"+this.mapName+".xml");
       if(file.exists())
           return;
       Document document = DocumentHelper.createDocument();
       Element rootElement = document.addElement("xml");
       rootElement.add(this.encode());
       FileOperator.fileWriter(file,document);
    }

    public void load(){
        Element rootElement = FileOperator.fileChooser();
        if(rootElement!=null){
            Element element = rootElement.element(this.GRID_MAP);
            decode(element);
        }
    }


}
