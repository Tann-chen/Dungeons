package items;

import characters.AbilityModifier;
import org.dom4j.Element;


/**
 * @author Tianen Chen
 * This class is one type of equipments, armor. it is the subclass of Equipment.
 * an armor only can enhance the armor class for a character
 */
public class Armor extends Equipment{

    private static final String IMAGE_PATH_NAME="armor.jpg";

    /*Constructor*/

    public Armor(String equiName){
        this.equipName=equiName;
        this.equipType=EquipType.Armor;
        this.imageName=IMAGE_PATH_NAME;
    }


    /*Enchantment bonus*/

    private int armorClassBonus;


    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the armor to the character who wear it
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustArmorClass(armorClassBonus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(!abilityType.trim().equalsIgnoreCase("ArmorClass"))
            return false;
        else if(bonusValue<1 || bonusValue>5)
            return false;
        else{
            this.armorClassBonus=bonusValue;
            return true;
        }
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
        return this.armorClassBonus;
    }


    /*Archive*/

    private class Archiving{
        private static final String CLASS = "Armor";
        private static final String ENCHANTMENT_BONUS_TYPE="BonusType";
        private static final String ENCHANTMENT_BONUS_VALUE="BonusValue";
        private static final String IMAGE_NAME="ImageName";

    }

    /**
     *The method will encode the data of an armor to an element in xml tree
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public Element encode(){
        Element element=super.encode();
        element.setName(Archiving.CLASS);
        element.addElement(Archiving.ENCHANTMENT_BONUS_TYPE).addText(this.getEnchantmentBonusType());
        element.addElement(Archiving.ENCHANTMENT_BONUS_VALUE).addText(String.valueOf(this.getBonusValue()));
        element.addElement(Archiving.IMAGE_NAME).addText(this.imageName);
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
        this.armorClassBonus=Integer.parseInt(element.element(Archiving.ENCHANTMENT_BONUS_VALUE).getText());
        this.imageName=element.element(Archiving.IMAGE_NAME).getText();
    }

}
