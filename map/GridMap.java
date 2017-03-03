package map;

import archive.Archivable;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
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
     * The default constructor: 10 rows ,10 columns
     */
    public GridMap(){
        this(10,10);
    }
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
     */
   public void decodeMapItems(Element itemsElement){
       ArrayList<MapItem> contents = new ArrayList<MapItem>();
       Iterator i = itemsElement.elementIterator();
       while (i.hasNext()) {
           Element element = (Element) i.next();
           MapItem temp = null;
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
        Element itemsElements = element.element(MAP_ITEMS);
        decodeMapItems(itemsElements);
    }


}
