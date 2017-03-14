package characters;

import archive.Archivable;
import items.Equipment;
import items.EquipmentManager;
import map.MapItem;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The class is to build the character, including fighter and enemy
 */
public class Character extends MapItem implements Archivable{

    private String name;
    private int level;

    /*Ability score*/
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;

    /*Other abilities*/
    private int hitPoint;
    private int armorClass;
    private int attackBonus;
    private int damageBonus;
    private int multipleAttacks;
    private Map<String,Equipment>wornEquipments;


    public Character(String iname){
        this();
        name=iname;
    }

    public Character(){
        level=1;
        multipleAttacks=1;
        wornEquipments=new HashMap<String,Equipment>();
        modifier=new AbilityModifier();
        updateOtherAbilities();
        this.setItemType(MapItem.CHARACTER);
    }


    public String getName() { return name; }

    /**
     * The method is used to set the ability score of the fighter
     * */
    public void setDataOfCha(String dataType, int value){
        if(dataType.equals("Level"))
            level=value;
        else if(dataType.equals("Strength"))
            strength=value;
        else if(dataType.equals("Dexterity"))
            dexterity=value;
        else if(dataType.equals("Constitution"))
            constitution=value;
        else if(dataType.equals("Intelligence"))
            intelligence=value;
        else if(dataType.equals("Wisdom"))
            wisdom=value;
        else
            charisma=value;
    }
    /**
     * The method is used to update the other abilities of the fighter
     * it will be used each time the equipment change or uplevel
     */
    public void updateOtherAbilities(){
        armorClass=modifier.getter("dexterityModifier")+modifier.getter("armorClass")+10;
        attackBonus=this.level+modifier.getter("strengthModifier")+modifier.getter("attackBonus");
        damageBonus=modifier.getter("dexterityModifier")+modifier.getter("damageBonus")+10;
        hitPoint=dice(1,10)+modifier.getter("constitutionModifier");
    }


    /*AbilityModifier*/

    private AbilityModifier modifier;


    public Map<String, Equipment> getWornEquipments() {
        return wornEquipments;
    }

    public int getData(String dataType){
        if(dataType.equals("Strength"))
            return strength;
        else if(dataType.equals("Dexterity"))
            return dexterity;
        else if(dataType.equals("Constitution"))
            return constitution;
        else if(dataType.equals("Intelligence"))
            return intelligence;
        else if(dataType.equals("Wisdom"))
            return wisdom;
        else if(dataType.equals("Level"))
            return level;
        else
            return charisma;
    }

    public AbilityModifier getModifier(){return this.modifier;}



    /* Worn Equipments */

    public static final String WORN_EQUIPMENTS = "WornEquipments";

    /**
     * The method is used to wear a equipment in the character
     * if the character has already wear the type of item, the old one will be return
     */
    public Equipment wearEquipment(Equipment equip){
        String equipType=String.valueOf(equip.getEquipType());
        Equipment oldEquip=wornEquipments.put(equipType,equip);//oldEquip is the type of equips that has already worn
        this.modifier.updateValueOfModifier(this);
        updateOtherAbilities();
        return oldEquip;
    }

    public Equipment takeOffEquipment(String equipType){
        Equipment removed=wornEquipments.remove(equipType);
        this.modifier.updateValueOfModifier(this);
        updateOtherAbilities();
        return removed;
    }


    /**
     * The method is to encode the equipments worn in the character
     * The returned element will be as a element in character's xml tree
     */
    public Element encodeWornEquipments(){
        Element element = new DefaultElement(WORN_EQUIPMENTS);
        Collection<Equipment> currentWornEquips= wornEquipments.values();
        if(currentWornEquips.size()>=1) {
            for (Equipment e : currentWornEquips) {
                Element eachEquip = e.encode();
                element.add(eachEquip);
            }
        }
        return element;
    }


    public void decodeWornEquipments(Element element){
        ArrayList<Equipment> getFromXML= EquipmentManager.getEquipmentManager().decodeWornEquipments(element);
        for(Equipment e:getFromXML){
            wearEquipment(e);
        }
    }

    /*  Archiving a character */


    public static final String CHARACTER = "Character";
    public static final String NAME = "Name";
    public static final String LEVEL = "Level";
    public static final String STRENGTH  = "Strength";
    public static final String DEXTERITY = "Dexterity";
    public static final String CONSTITUTION = "Constitution";
    public static final String INTELLIGENCE = "Intelligence";
    public static final String WISDOM = "Wisdom";
    public static final String CHARISMA = "Charisma";
    public static final String HIT_POINT = "HitPoint";
    public static final String ARMOR_CLASS = "ArmorClass";
    public static final String ATTACK_BONUS = "AttackBonus";
    public static final String DAMAGE_BONUS = "DamageBonus";
    public static final String MULTIPLE_ATTACKS = "MultipleAttacks";
    public static final String ATTITUDE_IN_MAP="AttitudeInMap";


    /**
     * The method is used to encode the data of a character into a element
     * The data about worn equipments will be inside the data of the character
     */
    @Override
    public Element encode(){
        Element element = super.encode();
        //Element element = new DefaultElement(CHARACTER);
        element.setName(CHARACTER);
        element.addElement(NAME).addText(this.name);
        element.addElement(LEVEL).addText(String.valueOf(this.level));
        element.addElement(STRENGTH).addText(String.valueOf(this.strength));
        element.addElement(DEXTERITY).addText(String.valueOf(this.dexterity));
        element.addElement(CONSTITUTION).addText(String.valueOf(this.constitution));
        element.addElement(INTELLIGENCE).addText(String.valueOf(this.intelligence));
        element.addElement(WISDOM).addText(String.valueOf(this.wisdom));
        element.addElement(CHARISMA).addText(String.valueOf(this.charisma));
        element.addElement(HIT_POINT).addText(String.valueOf(hitPoint));
        element.addElement(ARMOR_CLASS).addText(String.valueOf(this.armorClass));
        element.addElement(ATTACK_BONUS).addText(String.valueOf(this.attackBonus));
        element.addElement(DAMAGE_BONUS).addText(String.valueOf(this.damageBonus));
        element.addElement(MULTIPLE_ATTACKS).addText(String.valueOf(this.multipleAttacks));
        element.addElement(ATTITUDE_IN_MAP).addText(String.valueOf(this.attitudeInMap));
        element.add(encodeWornEquipments());
        return element;
    }

    @Override
    public void decode(Element element){

        super.decode(element);
        this.name=element.element(NAME).getText();
        this.level=Integer.parseInt(element.element(LEVEL).getText());
        this.strength=Integer.parseInt(element.element(STRENGTH).getText());
        this.dexterity=Integer.parseInt(element.element(DEXTERITY).getText());
        this.constitution=Integer.parseInt(element.element(CONSTITUTION).getText());
        this.intelligence=Integer.parseInt(element.element(INTELLIGENCE).getText());
        this.wisdom=Integer.parseInt(element.element(WISDOM).getText());
        this.charisma=Integer.parseInt(element.element(CHARISMA).getText());
        this.hitPoint=Integer.parseInt(element.element(HIT_POINT).getText());
        this.armorClass=Integer.parseInt(element.element(ARMOR_CLASS).getText());
        this.attackBonus=Integer.parseInt(element.element(ATTACK_BONUS).getText());
        this.damageBonus=Integer.parseInt(element.element(DAMAGE_BONUS).getText());
        this.multipleAttacks=Integer.parseInt(element.element(MULTIPLE_ATTACKS).getText());
        this.attitudeInMap=Integer.parseInt(element.element(ATTITUDE_IN_MAP).getText());
        Element equipsElement=element.element(WORN_EQUIPMENTS);
        decodeWornEquipments(equipsElement);
    }

    /**
     * The method is used to create a random number, to obey d20 rules
     */
    private int dice(int numOfDice,int rangeOfEach){
        int result=0;
        for(int i=0;i<numOfDice;i++){
           result+= ((int)(Math.random()*rangeOfEach))+1;
        }
        return result;
    }



    /* Interaction in game map*/

    public static final int FRIENDLY=1;
    public static final int HOSTILE=2;

    //friendly or hostile
    private int attitudeInMap;

    public int getAttitude() { return attitudeInMap;}

    public void setAttitude(int attitude) { this.attitudeInMap = attitude;}
}
