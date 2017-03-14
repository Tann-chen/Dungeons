package characters;

/**
 * The class is to abstract the builders for creating different character objects
 * The class is the "Builder" in builder design pattern
 */
public abstract class CharacterBuilder {

    protected Character characterProduct;

    protected int[] abilityValues;


    /**
     * The method is used to generate all 6 score values, based on 4d6 and sort them using selection sort
     */
    protected void generateSortedSocreValues(){
        abilityValues= new int[6];
        for(int i=0;i<6;i++){
            abilityValues[i]=dice(4,6);
        }
        //selection sort
        for(int i=0 ; i<abilityValues.length;i++){
            int currentMax=abilityValues[i];
            int currentMaxIndex=i;
            for(int j=i+1; j<abilityValues.length;j++){
                if(currentMax<abilityValues[j]){
                    currentMax=abilityValues[j];
                    currentMaxIndex=j;
                }
            }
            if(currentMaxIndex!=i){
                abilityValues[currentMaxIndex]=abilityValues[i];
                abilityValues[i]=currentMax;
            }
        }
    }

    /**
     * The method is used to create a new character object with all 0 abilities scores
     */
    public void createNewCharacter(String characterName){
        this.characterProduct=new Character(characterName);
    }

    /**
     * The method is used to set strength of a specific character
     */
    protected abstract void setStrength();

    /**
     * The method is used to set constitution  of a specific character
     */
    protected abstract void setConstitution();

    /**
     * The method is used to set dexterity of a specific character
     */
    protected abstract void setDexterity();

    /**
     * The method is used to set intelligence, charisma, wisdom
     */
    protected void setInteChariWisd(){
        characterProduct.setDataOfCha("Intelligence",abilityValues[3]);
        characterProduct.setDataOfCha("Charisma",abilityValues[4]);
        characterProduct.setDataOfCha("Wisdom",abilityValues[5]);
    }

    /**
     * The method is used to create a random number, to obey d20 rules
     */
    private int dice(int numOfDice,int rangeOfEach){
        int result=0;
        for(int i=0;i<numOfDice;i++){
            result+= ((int)(Math.random()*rangeOfEach))+1;
        }
        return result;
    }

}
