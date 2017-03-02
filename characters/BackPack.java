package characters;

import archive.Archivable;
import items.Equipment;
import org.dom4j.Element;

/**
 * The method is used to build the backpack
 * @author Tann Chen
 */
public class BackPack{

    public static final int MAX_VOLUMN = 10;
    private Equipment[] backpack;
    /**
     * Constructor
     */
    public BackPack(){
        backpack=new Equipment[MAX_VOLUMN];
    }

    /**
     *The method is used to add item into the backpack
     */
    public int addItem(Equipment e){
        for(int i=0;i<MAX_VOLUMN;i++){
            if(backpack[i]==null){
                backpack[i]=e;
                return i;
            }
        }
        return -1;
    }

    /**
     * The method is used to count the number of items
     */
    public int countOfItem(){
        int num =0;
        for(Equipment e : backpack){
            if(e!=null)
                num+=1;
        }
        return num;
    }

    /**
     * The method is used to remove the item from backpack
     */
    public Equipment removeItem(int i){
        Equipment temp = backpack[i];
        backpack[i]=null;
        return temp;
    }
}
