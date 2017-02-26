import items.EquipmentManager;
import ui.ItemEditorScreen;
import ui.MainScreen;
import ui.Window;

/**
 * This class is the entry of the game
 * @ author Tann chen
 */
public class Dungeons {


    public static void main(String[] arg){
        Window dungeonsWindow = new Window();
        MainScreen mainScreen=new MainScreen();
        dungeonsWindow.pushScreen(mainScreen);
        mainScreen.setBelongWindow(dungeonsWindow);
        EquipmentManager.getEquipmentManager().loadEquipmentModule();
    }
}
