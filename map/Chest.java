package map;

import items.Equipment;
import items.EquipmentManager;

/**
 * The chest contains treasure in the map
 * From requirementsm the chest should contain the items in item repository in the game.
 * @authore Tann Chen
 */
public class Chest extends MapItem{

    private Equipment[] contain;
    private String itemImage="chest.img";
    private boolean openStatus;      //open or not

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

}
