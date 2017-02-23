package items;

import archive.FileOperator;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to define the equipment manager.
 * The function 1 of equipment manager is to load all recorded equipments in the beginning of the game
 * The function 2 of equipment manager is to update the equipments records to the file
 * @author Tianen Chen
 */
public class EquipmentManager {

    private List<Equipment>equipmentList;

    public EquipmentManager(){
        equipmentList=new ArrayList<Equipment>();
    }

    public void addEquipment(Equipment equip){
        equipmentList.add(equip);
    }

    public void removeEquipment(Equipment equip){
        equipmentList.remove(equip);
    }

    public List<Equipment> getEquipmentList(){

        return this.equipmentList;
    }



    /* Loading the recorded equipments from file */

    private static final String ITEMS_FILE_PATH="／data/items.xml";
    private static HashMap<String,Class> registeredEquipments = new HashMap<String,Class>();
    /**
     * The method will be used in the beginning of the game to load the equipments information.
     */
    public void loadEquipments(){
        registerAllEquipments();
        loadRecoededEquips();
    }

    /**
     * The method is to load the equipments records and create corresponding equipment objects used in the game
     */
    private void loadRecoededEquips(){
        Element rootElement = FileOperator.fileReader(ITEMS_FILE_PATH);
        Iterator i = rootElement.elementIterator();
        while(i.hasNext()){
            Element element =(Element)i.next();
            Class classObj = registeredEquipments.get(element.getName());
            Equipment equipment=null;
            try {
                equipment = (Equipment) classObj.newInstance();
            }
            catch(Exception e){
                e.printStackTrace();
            }
            if(equipment!=null)
                equipment.decode(element);
            else
                System.out.println(element.getName()+"was wrong in loading");
            this.equipmentList.add(equipment);
        }
    }
    /**
     * This method is used in each subclass of Equipment to let the equipment manager know the kind of equipments
     */
    public static void registerEquipment(String equipmentName, Class equipmentClass){
        registeredEquipments.put(equipmentName,equipmentClass);
    }
    /**
     * The method is to register the equipments type
     */
    private void registerAllEquipments(){
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

}
