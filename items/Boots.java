package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 * @author Tianen Chen
 * This class is one type of equipments, boots. it is the subclass of Equipment.
 * an boots only can enhance armorClass, dexterity for a character who wear it
 */
public class Boots extends Equipment{

    private static final String IMAGE_PATH_NAME="boots.jpg";


    /*Constructor*/

    public Boots(String equipName) {
        this.equipName=equipName;
        this.equipType=EquipType.Boots;
        this.imageName=IMAGE_PATH_NAME;
    }


    /*Enchantment bonus*/

    private int armorClassBonus;
    private int dexterityBonus;


    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the boots to the character who wear it
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustArmorClass(armorClassBonus);
        am.adjustDexterity(dexterityBonus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(bonusValue<1||bonusValue>5)
            return false;
        else if((!abilityType.trim().equalsIgnoreCase("ArmorClass"))&&(!abilityType.trim().equalsIgnoreCase("DexterityBonus")))
            return false;
        else if(abilityType.trim().equalsIgnoreCase("ArmorClass")){
            this.armorClassBonus=bonusValue;
            return true;
        }
        else{
            this.dexterityBonus=bonusValue;
            return true;
        }
    }

    /**
     * This method override the getEnchantmentBonusType() in Equipment Class
     * @see Equipment
     */
    @Override
    public  String getEnchantmentBonusType(){
        if(armorClassBonus!=0)
            return "ArmorClass";
        else
            return "Dexterity";
    }


    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        if (armorClassBonus!=0)
            return armorClassBonus;
        else
            return dexterityBonus;
    }


    /*Archive*/

    private class Archiving{
        private static final String CLASS = "Boots";
        private static final String ENCHANTMENT_BONUS_TYPE="BonusType";
        private static final String ENCHANTMENT_BONUS_VALUE="BonusValue";
        private static final String IMAGE_NAME="ImageName";
    }

    /**
     *The method will encode the data of an boots to an element in xml tree
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
     *The method will decode an element recoding a boots to the data
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public void decode(Element element){
        super.decode(element);
        int temp = Integer.parseInt(element.element(Archiving.ENCHANTMENT_BONUS_VALUE).getText());
        if(element.element(Archiving.ENCHANTMENT_BONUS_TYPE).getText().equalsIgnoreCase("ArmorClass"))
            this.armorClassBonus=temp;
        else
            this.dexterityBonus=temp;
        this.imageName=element.element(Archiving.IMAGE_NAME).getText();
        }

}
