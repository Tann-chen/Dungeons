package characters;

import items.Equipment;

import java.util.ArrayList;
import java.util.List;

/**
 * only code the aspect of ability score
 */
public class Character {

     /*Ability score*/

    private int strength=0;
    private int dexterity=0;
    private int constitution=0;
    private int intelligence=0;
    private int wisdom=0;
    private int charisma=0;

    /*other values*/

    private int hitPoint=0;
    private int armorClass=0;
    private int originalAttack=0;
    private int originalDamage=0;



    /*AbilityModifier*/
    /**
     * 一个人物对象只对应一个能力修改器
     *任何能力的修改（穿装备、升级等等），全在能力修改器上修改，以防重复叠加了（人物修炼和附加的能力）
     * 例外：在人物编辑页面中，对人物能力的修改，在此页面中修改（人物原始能力）
     */
    private AbilityModifier modifier;
    private List<Equipment> wornEquipments;

    private Character(){
        modifier = new AbilityModifier(this);
        wornEquipments=new ArrayList<Equipment>();
    }

    /*getters*/

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getOriginalAttack() {
        return originalAttack;
    }

    public int getOriginalDamage() {
        return originalDamage;
    }

    public List<Equipment> getWornEquipments() {
        return wornEquipments;
    }
}
