package items;

import java.util.ArrayList;
import archive.FileOperator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.File;
import java.util.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Observable;

/**
 * This class is used to define the equipment manager.
 * The function 1 of equipment manager is to load all recorded equipments in the beginning of the game
 * The function 2 of equipment manager is to update the equipments records to the file
 * The class uses Observer pattern to notify the view about the changed data
 * The class is designed in singleton pattern
 * @author Tianen Chen
 */
public class EquipmentManager extends Observable{

    //solo instance
    private static EquipmentManager equipmentManager;

    //stores all equipments in the game
    private  ArrayList<Equipment> equipmentList;

    //store allowed bonus types of all equipments
    private  Map<String,String[]> equipAllowedBonusTypeMap;

    //the file name of record equipments
    private static final String ITEMS_FILE_PATH="/items.xml";

    //store the equipments type ans its responding class
    private  HashMap<String,Class> registeredEquipments;


    /* Singleton */
    /**
     * Constructor of equipmentManager
     */
    private EquipmentManager(){
        equipmentList=new ArrayList<Equipment>();
        equipAllowedBonusTypeMap=new HashMap<String,String[]>();
        registeredEquipments = new HashMap<String,Class>();
    }

    /**
     * The method will be invoked in outside class where the equipmentManger is required
     */
    public static EquipmentManager getEquipmentManager(){
        if(equipmentManager==null)
            equipmentManager=new EquipmentManager();
        return equipmentManager;
    }



    /* General methods*/

    public void addEquipment(Equipment equip){

        equipmentList.add(equip);
        //observer pattern
        setChanged();
        notifyObservers(this);
    }

    public void removeEquipment(Equipment equip){

        equipmentList.remove(equip);
        //observer pattern
        setChanged();
        notifyObservers(this);
    }

    /*  Getters */

    public  ArrayList<Equipment> getEquipmentList(){return equipmentList;}

    public  HashMap<String, Class> getRegisteredEquipments() {return registeredEquipments;}

    /**
     * The method will be invoked in every subclass of the equipments to register the allowed types of bonus
     * registered information stores in the Map
     */
    public  void registeAllowedBonusType(String equipName, String[] allowedBonusType){
        equipAllowedBonusTypeMap.put(equipName,allowedBonusType);
    }

    public  Map<String, String[]> getEquipAllowedBonusTypeMap() {
        return equipAllowedBonusTypeMap;
    }


    /* Loading the recorded equipments from file */


    /**
     * The method will be used in the beginning of the game to load the equipments information.
     */
    public void loadEquipmentModule(){
        registerAllEquipments();
        loadRecoededEquips();
    }

    /**
     * The method is to load the equipments records and create corresponding equipment objects used in the game
     */
    private void loadRecoededEquips(){
        Element rootElement = FileOperator.fileReader(ITEMS_FILE_PATH);
        if(rootElement!=null) {
            Iterator i = rootElement.elementIterator();
            while (i.hasNext()) {
                Element element = (Element) i.next();
                Class classObj = registeredEquipments.get(element.getName());
                Equipment equipment = null;
                try {
                    equipment = (Equipment) classObj.newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (equipment != null)
                    equipment.decode(element);
                else
                    System.out.println(element.getName() + "was wrong in loading");
                    equipmentList.add(equipment);
            }
        }
    }
    /**
     * This method is used in each subclass of Equipment to let the equipment manager know the kind of equipments
     */
    public  void registerEquipment(String equipmentName, Class equipmentClass){
        registeredEquipments.put(equipmentName,equipmentClass);
    }
    /**
     * The method is to register the equipments type
     */
    private  void registerAllEquipments(){
        Armor.registerEquipments();
        Belt.registerEquipments();
        Boots.registerEquipments();
        Helmet.registerEquipments();
        Ring.registerEquipments();
        Shield.registerEquipments();
        Weapon.registerEquipments();
    }



    /* Update the equipments records */

    /**
     * The method will be used to update the equipments records after player confirms the modification or creation of equipments
     */
    public void updateEquipmentRecords(){

        // The file to save the equipments records
        File file= new File(ITEMS_FILE_PATH);
        // The document to record the equipments
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        for(Equipment e:equipmentList){
            rootElement.add(e.encode());
        }
        FileOperator.fileWriter(file,document);
    }


    /* Create a Equipments from UI*/
    /**
     * The method will be invoked in Ui to create an equipment object
     */
    public void createItemInstance(String name,String itemType,String bonusType, int bonusValue){

        Class classObj = registeredEquipments.get(itemType);
        try {
            Equipment newEquipment = (Equipment) classObj.newInstance();
            newEquipment.setEquipName(name);
            newEquipment.setEnchantmentBonus(bonusType,bonusValue);
            addEquipment(newEquipment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The method is invoked in Ui to edit the data of a specific equipment
     */
    public void editItemData(String itemName, String newBonusType, int newBonusValue){
        Equipment target=null;
        for(Equipment equip :equipmentList){
            if(equip.getEquipName().equals(itemName))
                target=equip;
        }
        target.setEnchantmentBonus(newBonusType,newBonusValue);
    }

}
