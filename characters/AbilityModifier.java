package characters;

import items.Equipment;

import java.awt.event.ActionListener;
import java.util.List;

/**
 * This class is abilityModifier, that can adjust the ability score of a character
 * One character instance corresponds on one abilityModifier
 */
public class AbilityModifier {

    private Character character; // the modifier object belongs to who

    /*Ability score*/

    private int strengthBonus=0;
    private int dexterityBonus=0;
    private int constitutionBonus=0;
    private int intelligenceBonus=0;
    private int wisdomBonus=0;
    private int charismaBonus=0;

    /*other values*/

    private int hitPoint=0;
    private int armorClass=0;
    private int attackBonus=0;
    private int damageBonus=0;

    /*Constructors*/

    public AbilityModifier(Character cha){
        character=cha;
        equipmentModifier();
    }

    //TODO:随着等级升高的modifers调整
    //TODO:属性值直接互相影响的modifiers调整



    /*the modification of abilities caused by worn equipments*/



    public void equipmentModifier(){

        List<Equipment> equipList=character.getWornEquipments();
        for (Equipment equip : equipList){
            equip.addEnchantBonusToModifier(this);
        }
    }



    /*Main function - adjust the abilities*/



    public void adjustStrength(int strengthBonus) {
        this.strengthBonus += strengthBonus;
    }

    public void adjustDexterity(int dexterityBonus) {
        this.dexterityBonus += dexterityBonus;
    }

    public void adjustConstitution(int constitutionBonus) {
        this.constitutionBonus += constitutionBonus;
    }

    public void adjustIntelligence(int intelligenceBonus) {
        this.intelligenceBonus += intelligenceBonus;
    }

    public void adjustWisdom(int wisdomBonus) {
        this.wisdomBonus += wisdomBonus;
    }

    public void adjustCharisma(int charismaBonus) {
        this.charismaBonus += charismaBonus;
    }

    public void adjustHitPoint(int hitPointBonus) {
        this.hitPoint += hitPointBonus;
    }

    public void adjustArmorClass(int armorClassBonus) {
        this.armorClass += armorClassBonus;
    }

    public void adjustAttackBonus(int attackBonus) {
        this.attackBonus += attackBonus;
    }

    public void adjustDamageBonus(int damageBonus) {
        this.damageBonus += damageBonus;
    }




    /*get the modified ability scores*/



    /*public int getStrengthBonus() {
        return character.getStrength()+strengthBonus;
    }

    public int getDexterityBonus() {
        return character.getDexterity()+dexterityBonus;
    }

    public int getConstitutionBonus() {
        return character.getConstitution()+constitutionBonus;
    }

    public int getIntelligenceBonus() {
        return character.getIntelligence()+intelligenceBonus;
    }

    public int getWisdomBonus() {
        return character.getWisdom()+wisdomBonus;
    }

    public int getCharismaBonus() {
        return character.getCharisma()+charismaBonus;
    }

    public int getHitPoint() {
        return character.getHitPoint()+hitPoint;
    }

    public int getArmorClass() {
        return character.getArmorClass()+armorClass;
    }

    public int getAttackBonus() {
        return character.getOriginalAttack()+attackBonus;
    }

    public int getDamageBonus() {
        return character.getOriginalDamage()+damageBonus;
    }*/
}
