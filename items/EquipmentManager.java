package items;

import Archive.Archivable;
import Archive.FileReader;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * This class is used to define the equipment manager
 * The function 1 of equipment manager is to load all recorded equipments in the beginning of the game.
 * The function 2 of equipment manager is to save the created equipments into the file.
 * The class uses the singleton pattern
 * @author Tianen Chen
 */
public class EquipmentManager {

    private static EquipmentManager equipmentManager;




    /*Constructor*/
    /**
     * In singleton pattern, the constructor can only be used by self
     */
    private EquipmentManager(){
        registerAllEquipments();
        loadRecoededEquips();


    }

    //这里可能需要直接返回list<equipments>,目前还不清楚，先放着
    public static EquipmentManager equipmentManager(){

        if(equipmentManager==null)
            equipmentManager=new EquipmentManager();
        return equipmentManager;
    }





    /* Loading the recorded equipments */

    private static final String ITEMS_FILE_PATH="／data/items.xml";
    private List<Equipment>recordedEquipments;


    private void loadRecoededEquips(){
        Element rootElement = FileReader.fileReader(ITEMS_FILE_PATH);
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
            recordedEquipments.add(equipment);
        }
    }


    public List<Equipment> getRecordedEquipments(){
        return recordedEquipments;
    }





    /* Registering Equipments Classes*/


    private static HashMap<String,Class> registeredEquipments = new HashMap<String,Class>();

    /**
     * This method is used in each subclass of Equipment to let the equipment manager know the kind of equipments
     */
    public static void registerEquipment(String equipmentName, Class equipmentClass){
        registeredEquipments.put(equipmentName,equipmentClass);
    }

    private void registerAllEquipments(){
        Armor.registerEquipments();
        Belt.registerEquipments();
        Boots.registerEquipments();
        Helmet.registerEquipments();
        Ring.registerEquipments();
        Shield.registerEquipments();
        Weapon.registerEquipments();
    }



}
