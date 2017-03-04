package campaign;

import archive.Archivable;
import map.GridMap;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import java.util.ArrayList;

/**
 * The class is used to create and manage the campaign in the game
 * Campaign is the linkage of the maps
 * @author Tann Chen
 */
public class Campaign implements Archivable{

    private String campName;
    private int lengthOfCampaign;
    private GridMap[] campaign;

    /**
     * Default constructor
     */
    public Campaign(){
        this(3);
    }

    /**
     * Constructor with parameter
     */
    public Campaign(int length){
        this.lengthOfCampaign=length;
        this.campaign=new GridMap[this.lengthOfCampaign];
    }
    /**
     * The method is used to set a map note in the campaign
     */
    public GridMap setMapInCampaign(GridMap map, int index){
        if(campaign[index]==null){
            campaign[index]=map;
            return null;
        }
        else{
            GridMap oldMap=campaign[index];
            campaign[index]=map;
            return map;
        }
    }
    /**
     * The method is used to delete a map note from a campaign
     */
    public void deleteMapNote(int index){
        if(this.campaign[index]!=null)
            this.campaign[index]=null;
    }



    /* Archive*/

    private static final String CAMPAIGN_CLASS = "Campaign";
    private static final String CAMPAIGN_NAME="campaignName";
    private static final String CAMPAIGN_LENGTH = "CampaignLength";
    private static final String CAMPAIGN_MAPS="Maps";

    /**
     * The method is used to encode the maps in the campaign
     */
    public Element encodeMaps(){
        Element element = new DefaultElement(CAMPAIGN_MAPS);
        if(this.campaign.length>=1) {
            for (GridMap gm : campaign) {
                Element eachMap = gm.encode();
                element.add(eachMap);
            }
        }
        return element;
    }
    /**
     * The method is used to decode the maps records in xml into a campaign object
     */
    public void decodeMaps(Element element){
        //TODO
    }


    public Element encode(){

        Element element =new DefaultElement(CAMPAIGN_CLASS);
        element.addElement(CAMPAIGN_NAME).addText(this.campName);
        element.addElement(CAMPAIGN_LENGTH).addText(String.valueOf(this.lengthOfCampaign));
        element.add(encodeMaps());
        return element;
    }

    public void decode(Element element){
        //TODO
    }







}
