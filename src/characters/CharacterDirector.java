package characters;

/**
 * The class is to direct the build of character type
 * The class is the director of the builder pattern
 * @author  Tann chen
 */
public class CharacterDirector {

    private CharacterBuilder characterBuilder;
    /**
     * constructor
     */
    public CharacterDirector(CharacterBuilder builder){
        this.characterBuilder=builder;
    }

    /**
     * The method is to build the ability score for all type of characters
     */
    public void createNewCharacter(String characterName){
        characterBuilder.createNewCharacter(characterName);
        characterBuilder.generateSortedSocreValues();
        characterBuilder.setConstitution();
        characterBuilder.setStrength();
        characterBuilder.setDexterity();
        characterBuilder.setInteChariWisd();
    }

    public Character getCharacterProduct(){
        return characterBuilder.characterProduct;
    }

}
