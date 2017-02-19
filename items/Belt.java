package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 * @author Tianen Chen
 * This class is one type of equipments, belts. it is the subclass of Equipment.
 * an belt only can enhance constitution, strength for a character who wear it
 */
public class Belt extends Equipment{

    private static final String IMAGE_PATH_NAME="belt.jpg";

    /*Constructor*/

    public Belt(String equipName){
        this.equipName=equipName;
        this.equipType=EquipType.Belt;
        this.imageName=IMAGE_PATH_NAME;
    }


    /*Enchantment bonus*/

    private int constitutionBonus;
    private int strengthBonus;


    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the boots to the character who wear it
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustConstitution(constitutionBonus);
        am.adjustStrength(strengthBonus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(bonusValue<1||bonusValue>5)
            return false;
        else if((!abilityType.trim().equalsIgnoreCase("Constitution"))&&(!abilityType.trim().equalsIgnoreCase("Strength")))
            return false;
        else if(abilityType.trim().equalsIgnoreCase("Constitution")){
            this.constitutionBonus=bonusValue;
            return true;
        }
        else{
            this.strengthBonus=bonusValue;
            return true;
        }
    }

    /**
     * This method override the getEnchantmentBonusType() in Equipment Class
     * @see Equipment
     */
    @Override
    public  String getEnchantmentBonusType(){
        if(constitutionBonus!=0)
            return "Constitution";
        else
            return "Strength";

    }


    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        if(constitutionBonus!=0)
            return this.constitutionBonus;
        else
            return this.strengthBonus;
    }




      /*Archive*/

    private class Archiving{
        private static final String CLASS = "Belt";
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
        if(element.element(Archiving.ENCHANTMENT_BONUS_TYPE).getText().equalsIgnoreCase("Constitution"))
            this.constitutionBonus=temp;
        else
            this.strengthBonus=temp;
        this.imageName=element.element(Archiving.IMAGE_NAME).getText();
    }


}
