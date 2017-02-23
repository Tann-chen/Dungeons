package characters;

import archive.Archivable;
import items.Equipment;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * character architectural structure
 */
public class Character implements Archivable{

     /*Ability score*/
    private int strength=0;
    private int dexterity=0;
    private int constitution=0;
    private int intelligence=0;
    private int wisdom=0;
    private int charisma=0;

    /*other values*/
    private int level=0;
    private int hitPoint=0;
    private int armorClass=0;
    private int originalAttack=0;
    private int originalDamage=0;

    private Character(){
        modifier = new AbilityModifier(this);
        wornEquipments=new ArrayList<Equipment>();
        //TODO:这里面需要用到initAbilityScore()
    }
    /*这个方法用于设定人物的属性值，但是需要做安全性判断，当输入的属性值类型不存在时需要System.out.println报错*/
    public void setAbilityScore(String abilityType, int value){
        //TODO
    }

    /*这个函数是用在初始化人物的时候用的，按照要求在人物初始化的时候，人物的6项ability score 是随机决定的，但总值是固定的18*/
    public void initAbilityScore(){
        //TODO：这里面需要用到setAbilityScore()
    }

    /*AbilityModifier*/
    /**
     * 一个人物对象只对应一个能力修改器
     *任何能力的修改（穿装备、升级等等），全在能力修改器上修改，以防重复叠加了（人物修炼和附加的能力）
     * 例外：在人物编辑页面中，对人物能力的修改，在此页面中修改（人物原始能力）
     */
    private AbilityModifier modifier;

    //TODO:这一块需要需求分析，如果兴趣去边看D20边写就写着，不然就先放着把


    /* Worn Equipments */

    private List<Equipment> wornEquipments;

    public List<Equipment> getWornEquipments() {
        return wornEquipments;
    }

    public void wearingEquipment(Equipment equip){
        //TODO:这是一个穿装备的动作
        //TODO:一个人物身上只能穿一样同种类的装备，例如只能拿一把武器。当拿另一把武器时，要放弃本来拿的武器
        //TODO:List<Equipement>去保存身上的武器可能不是最适宜，因为不好去确定身上是否有什么装备－> map()或者set()我仍在考虑中
    }



    /*因为在.xml存档中，人物身上穿的装备要随人物的信息一起存进去所以，穿在身上的装备
    * 存档为.xml数的一个element*/
    public Element encodeWornEquipments(){
        //TODO
        return null;
         }

    public void decodeWornEquipments(Element element){

        //TODO :解析穿上身上装备的element后，将装备一件件读出来，放到wornEquipments（ArrayList）中。
    }



    /*  Archiving a character */

    @Override
    public Element encode(){
       //TODO
        return null;
    }

    @Override
    public void decode(Element element){
        //TODO
    }


}
