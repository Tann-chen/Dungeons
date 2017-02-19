package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 * This class is one type of equipments, helmet. it is the subclass of Equipment.
 * an helmet only can enhance intelligence, wisdom, armor class
 * @author Tianen Chen
 */
public class Helmet extends Equipment {

    private static final String IMAGE_PATH_NAME="belt.jpg";


    /*Constructor*/

    public Helmet(String equipName){
        this.equipName=equipName;
        this.equipType=EquipType.Helmet;
        this.imageName=IMAGE_PATH_NAME;
}




    /*Enchantment bonus*/

    private int intelligenceBonus;
    private int wisdomBonus;
    private int armorClassBonus;


    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the helmet to the character who wear it
     * @see Equipment
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustIntelligence(intelligenceBonus);
        am.adjustWisdom(wisdomBonus);
        am.adjustArmorClass(armorClassBonus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(bonusValue<1||bonusValue>5)
            return false;
        if(abilityType.trim().equalsIgnoreCase("Intelligence")){
            this.intelligenceBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("Wisdom")){
            this.wisdomBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("ArmorClass")){
            this.armorClassBonus=bonusValue;
            return true;
        }
        return false;
    }

    /**
     * This method override the getEnchantmentBonusType() in Equipment Class
     * @see Equipment
     */
    @Override
    public  String getEnchantmentBonusType(){
        if(intelligenceBonus!=0)
            return "Intelligence";
        else if(wisdomBonus!=0)
            return "Wisdom";
        else
            return "ArmorClass";
    }


    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        if(intelligenceBonus!=0)
            return intelligenceBonus;
        else if(wisdomBonus!=0)
            return wisdomBonus;
        else
            return armorClassBonus;
    }



    /*Archive*/

    private class Archiving{
        private static final String CLASS = "Helmet";
        private static final String ENCHANTMENT_BONUS_TYPE="BonusType";
        private static final String ENCHANTMENT_BONUS_VALUE="BonusValue";
        private static final String IMAGE_NAME="ImageName";
    }

    /**
     *The method will encode the data of an belt to an element in xml tree
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
     *The method will decode an element recoding a belt to the data
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public void decode(Element element){
        super.decode(element);
        int temp = Integer.parseInt(element.element(Archiving.ENCHANTMENT_BONUS_VALUE).getText());
        if(element.element(Archiving.ENCHANTMENT_BONUS_TYPE).getText().equalsIgnoreCase("Intelligence"))
            this.intelligenceBonus=temp;
        else if(element.element(Archiving.ENCHANTMENT_BONUS_TYPE).getText().equalsIgnoreCase("Wisdom"))
            this.wisdomBonus=temp;
        else
            this.armorClassBonus=temp;
        this.imageName=element.element(Archiving.IMAGE_NAME).getText();
       }


}
