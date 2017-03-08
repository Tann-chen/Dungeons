import characters.CharacterManager;
import items.EquipmentManager;
import ui.MainScreen;
import ui.Window;

/**
 * This class is the entry of the game
 * In the beginning of game, load the module of equipments
 * @ author Tann chen
 */
public class Dungeons {

    public static void main(String[] arg){
        Window dungeonsWindow = new Window();
        MainScreen mainScreen=new MainScreen();
        dungeonsWindow.pushScreen(mainScreen);
        mainScreen.setBelongWindow(dungeonsWindow);
        EquipmentManager.getEquipmentManager().loadEquipmentModule();
        CharacterManager.getCharacterManager().loadCharacterModule();
        // do not need to load the map module
    }
}
