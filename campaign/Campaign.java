package campaign;

import archive.Archivable;
import archive.FileOperator;
import map.GridMap;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

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
    public Campaign(){}

    /**
     * Constructor with parameter
     */
    public Campaign(int length){
        this.lengthOfCampaign=length;
        this.campaign=new GridMap[this.lengthOfCampaign];
    }

    public void setCampName(String campName) {
        this.campName = campName;
    }

    public int getLengthOfCampaign() {
        return lengthOfCampaign;
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
    public ArrayList<GridMap> decodeMaps(Element mapsElement){
        ArrayList<GridMap> contents = new ArrayList<GridMap>();
        Iterator i = mapsElement.elementIterator();
        while (i.hasNext()) {
            Element element = (Element) i.next();
            GridMap map = new GridMap();
            map.decode(element);
            contents.add(map);
        }
        return contents;
    }


    public Element encode(){
        Element element =new DefaultElement(CAMPAIGN_CLASS);
        element.addElement(CAMPAIGN_NAME).addText(this.campName);
        element.addElement(CAMPAIGN_LENGTH).addText(String.valueOf(this.lengthOfCampaign));
        element.add(encodeMaps());
        return element;
    }

    public void decode(Element element){
        this.campName=element.element(CAMPAIGN_NAME).getText();
        this.lengthOfCampaign=Integer.parseInt(element.element(CAMPAIGN_LENGTH).getText());
        Element mapsElement = element.element(CAMPAIGN_MAPS);
        ArrayList<GridMap> maps = decodeMaps(mapsElement);
        campaign=new GridMap[maps.size()];
        for(int i=0;i<maps.size();i++){
            campaign[i]=maps.get(i);
        }
    }

    public void save(){
        File file = new File("data/campaigns/"+this.campName+".xml");
        if(file.exists()){
         System.out.print("The campaign ["+this.campName+"] is existed, change the campaign name");
        }
        Document document = DocumentHelper.createDocument();
        Element rootElement = document.addElement("xml");
        rootElement.add(this.encode());
        FileOperator.fileWriter(file,document);
    }

    public void load(){
        Element rootElement = FileOperator.fileChooser();
        if(rootElement!=null){
            Element element = rootElement.element(this.CAMPAIGN_CLASS);
            decode(element);
        }
    }



}
