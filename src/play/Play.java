package play;

import archive.Archivable;
import campaign.Campaign;
import characters.Character;
import map.GridMap;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

/**
 * The class is to control the play of the selected campaign with a selected character
 * The class controls the change of gridMap, changing the injection to play object
 * @author Tann chen
 */
public class Play implements Archivable{

    private String playName;
    private String playTime;

    private Campaign selectedCampaign;
    private Character selectedCharacter;
    private PlayControl playControl;

    // to record the index of playing map in campaign
    private int currentMapIndex;
    private int maxMapInCampaign;
    /**
     * constructor
     */
    public Play(Campaign campaign, Character character){
        this.selectedCampaign=campaign;
        this.selectedCharacter=character;
        maxMapInCampaign=selectedCampaign.getLengthOfCampaign()-1;
    }
    /**
     * The method is used to start the play, the first map in the campaign will be load into playControl
     */
    public void startPlay(){
        currentMapIndex=0;
        GridMap playingGridMap=selectedCampaign.getTheGridMap(currentMapIndex);
        playControl = new PlayControl(playingGridMap,selectedCharacter);
    }

    /**
     * The method is used to change to next map in the campaign
     * @return true change successfully, false if no map can be changed
     */
    public boolean nextMap(){
        currentMapIndex+=1;
        if(currentMapIndex>=maxMapInCampaign)
            return false;
        GridMap playingGridMap=selectedCampaign.getTheGridMap(currentMapIndex);
        playControl.changeGridMap(playingGridMap);
        System.out.println("you are paying the"+currentMapIndex+"map in the campaign");
        return true;
    }

    /* Archive */

    public static final String PLAY_CLASS = "Play";
    public static final String PLAY_NAME="PlayName";
    public static final String PLAY_TIME="PlayTime";
    public static final String PLAY_CHARACTER="PlayCharacter";
    public static final String PLAY_CAMPAIGN="PlayCampaign";



    /**
     * the method is used to encode the data related to a play
     * @return an element in xml tree
     */
    @Override
    public Element encode(){
        Element element = new DefaultElement(PLAY_CLASS);
        element.addElement(PLAY_NAME).addText(this.playName);
        element.addElement(PLAY_TIME).addText(this.playTime);
        element.addElement(PLAY_CHARACTER).add(this.selectedCharacter.encode());
        element.addElement(PLAY_CAMPAIGN).add(this.selectedCampaign.encode());
        return element;
    }

    /**
     * the method is used to decode an element to the data of an play
     * @param element an element in xml tree
     */
    @Override
    public void decode(Element element){
        //TODO:build3
    }


}
