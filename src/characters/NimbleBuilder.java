package characters;

/**
 * The class is used to create a nimble concrete builder to generate nimble type characters
 * @author Tann chen
 */
public class NimbleBuilder extends CharacterBuilder{

    /**
     * The method override the setStrength() in its superclass
     * @see CharacterBuilder
     */
    @Override
    public void setStrength(){
        characterProduct.setDataOfCha("Strength",abilityValues[2]);
    }

    /**
     * The method override the setConstitution() in its superclass
     * @see CharacterBuilder
     */
    @Override
    public void setConstitution(){
        characterProduct.setDataOfCha("Constitution",abilityValues[1]);
    }

    /**
     * The method override the setDexterity() in its superclass
     * @see CharacterBuilder
     */
    @Override
    public void setDexterity(){
        characterProduct.setDataOfCha("Dexterity",abilityValues[0]);
    }
}
