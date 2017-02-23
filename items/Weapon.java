package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 * This class is one type of equipments, weapon. it is the subclass of Equipment.
 * a weapon only can enhance attackBonus, damageBonus.
 * @author Tann chen
 */
public class Weapon extends Equipment{

    private static final String IMAGE_PATH_NAME="weapon.jpg";

    /*Constructors*/

    public Weapon(String equipName){
        this.equipName=equipName;
        this.equipType=EquipType.Weapon;
        this.imageName=IMAGE_PATH_NAME;
    }



     /*Enchantment bonus*/

     private int attackBonusPlus;
     private int damageBonusPlus;



    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the weapon to the character who wear it
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustAttackBonus(this.attackBonusPlus);
        am.adjustDamageBonus(this.damageBonusPlus);
    }

    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue) {
        if(bonusValue<1||bonusValue>5)
            return false;
        if(abilityType.trim().equalsIgnoreCase("AttackBonus")){
            this.attackBonusPlus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("DamageBonus")){
            this.damageBonusPlus=bonusValue;
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
        if(attackBonusPlus!=0)
            return "AttackBonus";
        else
            return "DamageBonus";
    }


    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        if(attackBonusPlus!=0)
            return attackBonusPlus;
        else
            return damageBonusPlus;
    }


     /*archive*/


    private static final String ARCHIVE_CLASS = "Weapon";
    private static final String ENCHANTMENT_BONUS_TYPE="BonusType";
    private static final String ENCHANTMENT_BONUS_VALUE="BonusValue";
    private static final String IMAGE_NAME="ImageName";


    /**
     *The method will encode the data of an belt to an element in xml tree
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
     *The method will decode an element recoding a belt to the data
     *The method override the encode encode()method in Equipment
     * @see Equipment
     */
    @Override
    public void decode(Element element){
        super.decode(element);
        int value = Integer.parseInt(element.element(ENCHANTMENT_BONUS_VALUE).getText());
        String type = element.element(ENCHANTMENT_BONUS_TYPE).getText();
        setEnchantmentBonus(type,value);
        this.imageName=element.element(IMAGE_NAME).getText();
    }

    /**
     *The method is used to let the equipment manager know Weapon is a kind of equipments
     * The purpose is to match the weapon element in xml tree to the Weapon class
     */
    public static void registerEquipments(){
        EquipmentManager.registerEquipment(ARCHIVE_CLASS,Weapon.class);
    }


}
