package items;

import Archive.Archivable;
import characters.AbilityModifier;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

/**
 * This class is superclass for every type of equipments that can be worn in characters.
 * @author Tann chen
 */
public abstract class Equipment implements Archivable {

    //To limit the Equipment type
    protected enum EquipType{Helmet, Armor, Shield, Ring, Belt, Boots, Weapon};


    /*Constructors*/

    protected Equipment(){}

    /*basic properties*/

    protected String equipName;
    protected EquipType equipType;
    protected String imageName;



    public String getEquipName() {
        return equipName;
    }

    public void setEquipName(String equipName) {
        this.equipName = equipName;
    }

    public EquipType getEquipType() {
        return equipType;
    }

    public void setEquipType(EquipType equipType) {
        this.equipType = equipType;
    }




    //装备具有的属性加成效果
    /*Enchantment bonus of the equipment*/


    //这个抽象方法 是 用在将装备的属性加成效果加到人物的abilitymodifier上，参数（modifier对象）
    //各类型装备会具体实现这个方法－－利用JVM去判断哪时候调用哪个
    /**
     *This abstract method is used to add the enchantment bonus of this equipment to the modifier of the character
     *the subclass of Equipment will override the method to implement the specific function
     */
    public abstract void  addEnchantBonusToModifier(AbilityModifier am);


    //这个方法是用于设置装备的属性加成效果
    //因为每件装备只能加一样属性值，所以参数（加的属性值类型，加的值）
    //这个方法会在UI中使用到，当用户选择加什么值和定义加多少时。
    /**
     * This abstract method is used to set the enchantment bonus of an equipment
     * The subclass of Equipment will override the method to implement the specific function
     * The method will be used in EditItemsView and Archiving.
     */
    public abstract boolean setEnchantmentBonus(String abilityType,int bonusValue);

    /**
     *These two methods below are used to get the enchantment bonus type and its value
     * the subclass of Equipment will override them
     * the methods will be used in EditItemView and Archiving.
     */
    //这两个方法用于获取一个装备对象所加成属性的属性类型和属性值。 用于UI，当用户编辑装备时，用于显示装备目前加了什么值。
    public abstract String getEnchantmentBonusType();
    public abstract int getBonusValue();




    /* Archive the equipments*/

    public static final String ARCHIVE_CLASS = "Equipment";
    private static final String EQUIP_NAME="Name";


    /**
     * the method is used to encode the data related to an equipment
     * @return an element in xml tree
     */
    // 将装备的属性值转化成element对象。
    @Override
    public Element encode(){

        Element element = new DefaultElement(ARCHIVE_CLASS);
        element.addElement(EQUIP_NAME).addText(this.equipName);
        return element;
    }

    /**
     * the method is used to decode an element to the data of an equipment
     * @param element an element in xml tree
     */
    //将XML对象转化成属性值
    @Override
    public void decode(Element element){this.equipName=element.element(EQUIP_NAME).getText();}

}
