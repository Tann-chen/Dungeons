package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 * This class is one type of equipments,shield. it is the subclass of Equipment.
 * a shield only can enhance ArmorClass.
 * @author Tann chen
 */
public class Shield extends Equipment{

    private static final String IMAGE_PATH_NAME="shield.jpg";
    private static final String[] allowedBonusTypes={"ArmorClass"};


    /*Constructors*/
    public Shield(){
        this.equipType=EquipType.Shield;
        this.imageName=IMAGE_PATH_NAME;
    }

     /*Enchantment bonus*/

    private int armorClassBonus;


    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the shield to the character who wear it
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustArmorClass(this.armorClassBonus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(bonusValue<1||bonusValue>5)
            return false;
        else if(abilityType.trim().equalsIgnoreCase("ArmorClass")){
            this.armorClassBonus=bonusValue;
            return true;
        }
        else
            return false;
    }

    /**
     * This method override the getEnchantmentBonusType() in Equipment Class
     * @see Equipment
     */
    @Override
    public  String getEnchantmentBonusType(){
        return "ArmorClass";
    }

    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        return armorClassBonus;
    }



     /*archive*/


    private static final String ARCHIVE_CLASS = "Shield";
    private static final String ENCHANTMENT_BONUS_TYPE="BonusType";
    private static final String ENCHANTMENT_BONUS_VALUE="BonusValue";
    private static final String IMAGE_NAME="ImageName";



    /**
     *The method will encode the data of an armor to an element in xml tree
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public Element encode(){
        Element element=super.encode();
        element.setName(ARCHIVE_CLASS);
        element.addElement(ENCHANTMENT_BONUS_TYPE).addText(this.getEnchantmentBonusType());
        element.addElement(ENCHANTMENT_BONUS_VALUE).addText(String.valueOf(this.getBonusValue()));
        element.addElement(IMAGE_NAME).addText(this.imageName);
        return element;

    }
    /**
     *The method will decode an element recoding an armor to the data
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public void decode(Element element){
        super.decode(element);
        int value=Integer.parseInt(element.element(ENCHANTMENT_BONUS_VALUE).getText());
        String type= element.element(ENCHANTMENT_BONUS_TYPE).getText();
        setEnchantmentBonus(type,value);
        this.imageName=element.element(IMAGE_NAME).getText();
    }
    /**
     *The method is used to let the equipment manager know Shield is a kind of equipments
     * The purpose is to match the shield element in xml tree to the Shield class
     */
    public static void registerEquipments(){
        EquipmentManager.getEquipmentManager().registerEquipment(ARCHIVE_CLASS,Shield.class);
        EquipmentManager.getEquipmentManager().registeAllowedBonusType("Shield",Shield.allowedBonusTypes);
    }


}
