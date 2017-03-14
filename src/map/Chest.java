package map;

import archive.Archivable;
import items.Equipment;
import items.EquipmentManager;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import java.util.Iterator;

/**
 * The chest contains treasure in the map
 * From requirementsm the chest should contain the items in item repository in the game.
 * @authore Tann Chen
 */
public class Chest extends MapItem implements Archivable{

    private Equipment[] contain;
    private String itemImage="chest.img";
    private boolean openStatus;  //open or not

    public Chest(){
        this.setItemType(MapItem.CHEST);
        contain =new Equipment[1];
        initChest();
    }

    /**
     * The method is used to initilize a chest
     */
    public void initChest(){
        Equipment oneEquip=EquipmentManager.getEquipmentManager().takeOneEquipmentOut();
        contain[0]=oneEquip;
        openStatus=false;
    }
    /**
     * The method is used to open a chest, take the equipments contained in chest out
     */
    public Equipment openTheChest(){
        Equipment temp=contain[0];
        contain=null;
        openStatus=true;
        return temp;
    }


    /* Archive */

    public static final String CHEST="Chest";
    public static final String CONTAINING="Containing";


    /**
     *The method is used to encode the data of a chest into a element
     * The data about equipments inside the chest will be inside the data of the chest
     */
    @Override
    public Element encode(){
        Element element = super.encode();
        element.setName(CHEST);
        //add contained equipment element
        Element containElement= new DefaultElement(CONTAINING);
        containElement.add(contain[0].encode());
        element.add(containElement);
        return element;
    }

    @Override
    public void decode(Element element){
        super.decode(element);

        Element equipInChestElement=element.element(CONTAINING);
        Iterator i = equipInChestElement.elementIterator();
        while (i.hasNext()) {
            Element equipelement = (Element) i.next();
            contain[0]=EquipmentManager.getEquipmentManager().decodeEquipment(equipelement);
            openStatus=false;
        }
    }


}
