package ui.play;

import map.GridMap;
import map.MapIcons;
import map.MapItem;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The class is to build the panel showing the current status of gridMap
 * The panel is a sub-screen of playing screen
 * The panel will be automatically update if the content of injected girdMap change
 * During the playing game, player control the gridMap, which control the presentation of this panel
 * @author Tann chen
 */
public class GridMapPanel extends JPanel implements Observer{

    private int rowsNum;
    private int columnsNum;
    private GridMap dependentGridMap;

    /*ui*/
    private int paddingUp=40;
    private int paddingLeft=150;
    private JLabel[][] labels;

    /**
     * Constructor
     */
    public GridMapPanel(GridMap injectedGridMap){
        this.dependentGridMap=injectedGridMap;
        rowsNum=injectedGridMap.getRowsNum();
        columnsNum=injectedGridMap.getColumnsNum();

        /*ui*/
        this.setSize(600,600);
        this.setLayout(null);


        labels=new JLabel[this.rowsNum][this.columnsNum];
        for(int r=0 ; r<this.rowsNum;r++){
            for(int c=0 ; c<this.columnsNum ; c++){
                labels[r][c]=new JLabel();
                labels[r][c].setBounds(paddingLeft+c*35, paddingUp+r*35,39,39);
                labels[r][c].setName(r+","+c);
                this.add(labels[r][c]);
            }
        }

        //paint the icons for all label
        repaintLabelIcon();
    }

    /**
     * The method is used to repaint the icons of every label in the panel
     */
    private void repaintLabelIcon(){
        MapItem[][] temp=this.dependentGridMap.getMapItems();

        for(int r=0 ; r<this.rowsNum;r++){
            for(int c=0 ; c<this.columnsNum ; c++){
                if(temp[r][c]!=null){
                    int itemType=temp[r][c].getItemType();
                    labels[r][c].setIcon((MapIcons.getMapIconsManager().getIcons(itemType)));
                }else{
                    labels[r][c].setIcon(MapIcons.getMapIconsManager().getBackground());
                }
            }
        }
    }

    /**
     * The method is to update the presentation of the panel, if the content of injected gridMap changes
     */
    @Override
    public void update(Observable obs, Object o){
        repaintLabelIcon();
    }

}
