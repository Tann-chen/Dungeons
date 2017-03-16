package play;

import characters.Character;
import items.Equipment;
import map.Chest;
import map.GridMap;
import map.MapItem;

/**
 * The class is to control the play in a specific map, one gridMap in the campaign
 * In order to make the play suitable for every gridMap, the class uses DI - all control bases on injected gridMap
 * @auth Tann chen
 */
public class PlayControl{

    private GridMap controlledMap;
    private Character controlledCharacter;
    private int rowsNumOfMap;
    private int columnsNumOfMap;

    public PlayControl(GridMap gridMap, Character character){
        this.controlledMap=gridMap;   //dependency injection
        this.rowsNumOfMap=controlledMap.getRowsNum();
        this.columnsNumOfMap=controlledMap.getColumnsNum();
        this.controlledCharacter=character;
    }
    /**
     * The method is used to change controlled gridMap, to change map in campaign
     */
    public void changeGridMap(GridMap theGridMap){
        this.controlledMap=theGridMap;
    }

    /**
     * The method is used in beginning of the playing in a map, it set the player in the entry of the map
     */
    public void onEntry(){
        MapItem theEntry=controlledMap.findSpecificMapItem(MapItem.ENTRY);
        int rowOfEntry=theEntry.getYLocate();
        int columnOfEntry=theEntry.getXLocate();
        controlledMap.moveItemTo(controlledCharacter,rowOfEntry,columnOfEntry);
    }
    /**
     * The method is to move the player up in the map
     */
    public void moveUp(){
        int currentRow=controlledCharacter.getYLocate();
        int currentColumn=controlledCharacter.getXLocate();
        if(currentRow>=1)
            controlledMap.moveItemTo(controlledCharacter, currentRow-1, currentColumn);
    }
    /**
     * The method is used to move the player down in the map
     */
    public void moveDown(){
        int currentRow=controlledCharacter.getYLocate();
        int currentColumn=controlledCharacter.getXLocate();
        if(currentRow<rowsNumOfMap-1)
            controlledMap.moveItemTo(controlledCharacter,currentRow+1,currentColumn);

    }
    /**
     * The method is used to move the player left in the map
     */
    public void moveLeft(){
        int currentRow=controlledCharacter.getYLocate();
        int currentColumn=controlledCharacter.getXLocate();
        if(currentColumn>=1)
            controlledMap.moveItemTo(controlledCharacter,currentRow,currentColumn-1);
    }

    /**
     * The method is used to move the player right in the map
     */
    public void moveRight(){
        int currentRow=controlledCharacter.getYLocate();
        int currentColumn=controlledCharacter.getXLocate();
        if(currentColumn<columnsNumOfMap-1)
            controlledMap.moveItemTo(controlledCharacter,currentRow,currentColumn+1);
    }

    /* Interaction with special map items*/
    /**
     * The interactive mapItems includes characters and chests
     * Considering the common feature of them is un-across
     * So only un-across mapItem can be used to invoke this method
     */
    public void interaction(MapItem target){   //TODO:和上下左右联系到一块
        if(target.getItemType()==MapItem.WALL)
            return;
        if(target.getItemType()==MapItem.CHEST){
            Chest targetChest=(Chest)target;
            interactWithChest(targetChest);
        }
        if(target.getItemType()==MapItem.CHARACTER){
            Character targetCha=(Character)target;
            if(targetCha.getAttitude()==Character.FRIENDLY){
                interactWithFriendlyCha(targetCha);
            }
            else if(targetCha.getAttitude()==Character.HOSTILE){
                interactWithHostileCha(targetCha);
            }

        }

    }

    /**
     * The method is to interact with a chest in map
     */
    public void interactWithChest(Chest target){
        Equipment loot=target.openTheChest();
        //TODO:放到背包
    }

    public void interactWithFriendlyCha(Character target){}
    public void interactWithHostileCha(Character target){}












}
