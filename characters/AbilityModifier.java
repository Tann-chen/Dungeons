package characters;


import items.Equipment;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This class is abilityModifier, that can adjust the ability score of a character
 * One character instance corresponds on one abilityModifier
 */
public class AbilityModifier {

    /*Ability modifier*/

    private int strengthModifier;
    private int dexterityModifier;
    private int constitutionModifier;
    private int intelligenceModifier;
    private int wisdomModifier;
    private int charismaModifier;

    /*other values*/
    private int armorClass;
    private int attackBonus;
    private int damageBonus;


    /**
     * initialize the values of ability modifier
     */
    public void updateValueOfModifier(Character character){
        Collection<Equipment> wornequips=character.getWornEquipments().values();
        for(Equipment e:wornequips)
            e.addEnchantBonusToModifier(this);
    }


    /*Main function - adjust the value in modifier*/


    public void adjustStrength(int strengthBonus) {
        this.strengthModifier += strengthBonus;
    }

    public void adjustDexterity(int dexterityBonus) {
        this.dexterityModifier += dexterityBonus;
    }

    public void adjustConstitution(int constitutionBonus) {
        this.constitutionModifier += constitutionBonus;
    }

    public void adjustIntelligence(int intelligenceBonus) {
        this.intelligenceModifier += intelligenceBonus;
    }

    public void adjustWisdom(int wisdomBonus) {
        this.wisdomModifier += wisdomBonus;
    }

    public void adjustCharisma(int charismaBonus) {
        this.charismaModifier += charismaBonus;
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

    /**
     * Getter
     */
    public int getter(String type){
        if(type.equals("StrengthModifier"))
            return strengthModifier;
        else if(type.equals("DexterityModifier"))
            return dexterityModifier;
        else if(type.equals("ConstitutionModifier"))
            return constitutionModifier;
        else if(type.equals("IntelligenceModifier"))
            return intelligenceModifier;
        else if(type.equals("WisdomModifier"))
            return wisdomModifier;
        else if(type.equals("CharismaModifier"))
            return charismaModifier;
        else if(type.equals("ArmorClass"))
            return armorClass;
        else if(type.equals("AttackBonus"))
            return attackBonus;
        else
            return damageBonus;
    }

}
