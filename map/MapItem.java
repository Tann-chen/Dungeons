package map;

import archive.Archivable;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

/**
 * The class is used to abstract the map item
 * @authore Tann Chen
 */
public class MapItem implements Archivable{

    /* the num to present the item types*/
    public static final int ENTRY=8;
    public static final int EXIT=9;
    public static final int FLOOR=0;
    public static final int WALL=1;
    public static final int CHARACTER=2;
    public static final int CHEST=3;



    /* data fields*/
    private int XLocate;
    private int YLocate;
    private int itemType;
    private boolean across=false;    // can be acrossed or not
    private String itemImage;

    public void setLocation(int x ,int y){
       XLocate=x;
       YLocate=y;
    }

    public void setItemType(int type){
        itemType=type;
        if(type==FLOOR)
            across=true;
        if(type==ENTRY)
            across=true;
        if(type==EXIT)
            across=true;
    }
    public int getItemType(){return itemType;}

    public int getXLocate() {
        return XLocate;
    }

    public int getYLocate() {
        return YLocate;
    }

    public static final String MAP_ITEM="MapItem";
    public static final String X_LOCATION="XLocation";
    public static final String Y_LOCATION="YLocation";
    public static final String ITEM_TYPE="ItemType";
    public static final String ACROSS="Across";
    public static final String ITEM_IMAGE="ItemImage";


    /**
     * The method is used to encode the mapItem
     */
    public void decode(Element element){
        this.XLocate=Integer.parseInt(element.element(X_LOCATION).getText());
        this.YLocate=Integer.parseInt(element.element(Y_LOCATION).getText());
        this.itemType= Integer.parseInt(element.element(ITEM_TYPE).getText());
        this.across=Boolean.parseBoolean(element.element(ACROSS).getText());
        //this.itemImage=element.element(ITEM_IMAGE).getText();

    }

    public Element encode(){
        Element element = new DefaultElement(MAP_ITEM);
        element.addElement(X_LOCATION).addText(String.valueOf(this.XLocate));
        element.addElement(Y_LOCATION).addText(String.valueOf(this.YLocate));
        element.addElement(ITEM_TYPE).addText(String.valueOf(this.itemType));
        element.addElement(ACROSS).addText(String.valueOf(this.across));
       // element.addElement(ITEM_IMAGE).addText(this.itemImage);
        return element;
    }
}
