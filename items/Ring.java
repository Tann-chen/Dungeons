package items;

import characters.AbilityModifier;
import org.dom4j.Element;

/**
 *This class is one type of equipments,ring. it is the subclass of Equipment.
 * a ring only can enhance armorClass, strength,constitution,wisdom,charisma.
 * @author Tianen Chen
 */
public class Ring extends Equipment{
    //所有指环的图片是相同的，icon,所以定死
    private static final String IMAGE_PATH_NAME="ring.jpg";

    //这三个基本属性是从父类继承下来的，构造函数，这也是考虑到在创建一件装备时，用户在界面填写装备名称，选择装备类别。至于用户定义装备的属性加成值，
    //用于定义加成值时复杂多，所以先创建这个装备对象，在调用这个装备对象的方法就行。看下面就知道了
    /*Constructors*/
    public Ring(String equiName){
        this.equipName=equiName;
        this.equipType=EquipType.Ring;
        this.imageName=IMAGE_PATH_NAME;
    }


    //  戒指可能有5种属性加值，只能加了一种，其他就为0.
     /*Enchantment bonus*/

    private int armorClassBonus=0;
    private int strengthBonus=0;
    private int constitutionBonus=0;
    private int wisdomBonus=0;
    private int charismaBonus=0;


    //  虽然只能只有一种属性加成，但是在将属性加成加到modifier时还是都加一边，反正＋0不变。
    /**
     * The method override the method in superclass Equipment
     * The method will be used to add the enchantment bonus of the ring to the character who wear it
     * @see Equipment
     */
    @Override
    public void addEnchantBonusToModifier(AbilityModifier am){
        am.adjustArmorClass(this.armorClassBonus);
        am.adjustStrength(this.strengthBonus);
        am.adjustConstitution(constitutionBonus);
        am.adjustWisdom(this.wisdomBonus);
        am.adjustCharisma(this.charismaBonus);
    }

    //由于装备在设置属性值只能加一样值，所以当用户选择加什么类型之后，UI用getText()获取类型的string,和值，通过这个方法来定义装备属性加值
    //因为有5中不同情况，所以分别判断。返回true,设定成功，返回false，失败。－－在UI搞个状态栏
    /**
     * This method override the setEnchantmentBonus() in Equipment class
     * @see Equipment
     */
    @Override
    public boolean setEnchantmentBonus(String abilityType,int bonusValue){
        if(bonusValue<1||bonusValue>5)
            return false;
        if(abilityType.trim().equalsIgnoreCase("ArmorClass")){
            this.armorClassBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("Strength")){
            this.strengthBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("Constitution")){
            this.constitutionBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("Wisdom")){
            this.wisdomBonus=bonusValue;
            return true;
        }
        if(abilityType.trim().equalsIgnoreCase("Charisma")){
            this.charismaBonus=bonusValue;
            return true;
        }
        return false;
    }


    //获取属性附加值的类型，值为0的肯定不是，肯定是那个值不为0的
    /**
     * This method override the getEnchantmentBonusType() in Equipment Class
     * @see Equipment
     */
    @Override
    public  String getEnchantmentBonusType(){
        if(armorClassBonus!=0)
            return "ArmorClass";
        else if(strengthBonus!=0)
            return "Strength";
        else if(constitutionBonus!=0)
            return "Constitution";
        else if(wisdomBonus!=0)
            return "Wisdom";
        else
            return "Charisma";
    }

    //获取属性附加值，道理同上
    /**
     * This method override the getBonusValue() in Equipment Class
     * @see Equipment
     */
    @Override
    public int getBonusValue(){
        if(armorClassBonus!=0)
            return armorClassBonus;
        else if(strengthBonus!=0)
            return strengthBonus;
        else if(constitutionBonus!=0)
            return constitutionBonus;
        else if(wisdomBonus!=0)
            return wisdomBonus;
        else
            return charismaBonus;
    }



     /*Archive*/
    //XML为各件装备保存的属性：名称（父类那里），种类,加值的类型，加了多少，图片名字。
    private class Archiving{
        private static final String CLASS = "Ring";
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
        String tempType=element.element(Archiving.ENCHANTMENT_BONUS_TYPE).getText();
        if(tempType.equalsIgnoreCase("ArmorClass"))
            this.armorClassBonus=temp;
        else if(tempType.equalsIgnoreCase("Strength"))
                this.strengthBonus=temp;
        else if(tempType.equalsIgnoreCase("Constitution"))
                this.constitutionBonus=temp;
        else if(tempType.equalsIgnoreCase("Wisdom"))
            this.wisdomBonus=temp;
        else
            this.charismaBonus=temp;
        this.imageName=element.element(Archiving.IMAGE_NAME).getText();
    }
}
