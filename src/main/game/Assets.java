package game;

import javafx.scene.image.Image;
import javafx.scene.text.Font;
import view.Sprite;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Assets {
    private final static Logger log = LogManager.getLogger(Assets.class);
    private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
    private static final String FONT = new String("assets/fonts/nuku.ttf");
    private HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
    private Font defaultFont;
    private Font smallFont;
    private Font mediumFont;
    private Font largeFont;
    private Font hugeFont;
    private Font veryHugeFont;
    private int lastItemLoaded = 0;

    private Assets() {
    } //Constructor is Private, only one instance of Resources can be created

    private void loadSprites() {
       sprites.put("MENU_BACKGROUND", new Sprite("assets/images/menu/menuBackground.jpg"));
       sprites.put("GAME_BACKGROUND", new Sprite("assets/images/menu/gameBackground.jpg"));
       sprites.put("GUI_TOP", new Sprite("assets/images/gui/topBar.png"));
       sprites.put("GUI_HOVER", new Sprite("assets/images/gui/hover.png"));
       sprites.put("GUI_MAP_BAR", new Sprite("assets/images/gui/mapMakerBar.png"));
       sprites.put("GUI_BOTTOM", new Sprite("assets/images/gui/bottomBar.png"));
       sprites.put("GUI_COMMAND_PANEL", new Sprite("assets/images/gui/mode/commandPanel.png"));
       sprites.put("GUI_MINI_MAP_BORDER", new Sprite("assets/images/gui/miniBorder.png"));
       sprites.put("GUI_MINI_MAP_BACKGROUND", new Sprite("assets/images/gui/miniMapBackground.png"));
       sprites.put("GUI_MODE_PANEL", new Sprite("assets/images/gui/mode/modePanel.png"));
       sprites.put("GUI_SUBMODE_PANEL", new Sprite("assets/images/gui/mode/subModePanel.png"));
       sprites.put("GUI_MODE_SELECTED1", new Sprite("assets/images/gui/mode/selectedMode1.png"));
       sprites.put("GUI_MODE_SELECTED2", new Sprite("assets/images/gui/mode/selectedMode2.png"));
       sprites.put("GUI_MODE_SELECTED3", new Sprite("assets/images/gui/mode/selectedMode3.png"));
       sprites.put("GUI_MODE_SELECTED4", new Sprite("assets/images/gui/mode/selectedMode4.png"));
       sprites.put("TERRAIN_SAND", new Sprite("assets/images/terrain/sand/sand.png"));
       sprites.put("TERRAIN_GRASS", new Sprite(new String[] {"assets/images/terrain/grass/grass1.png",
        													"assets/images/terrain/grass/grass2.png",
        													"assets/images/terrain/grass/grass3.png"}));
       sprites.put("TERRAIN_MOUNTAIN", new Sprite(new String[] {"assets/images/terrain/mountain/mountain1.png",
    			 												"assets/images/terrain/mountain/mountain2.png",
    			 												"assets/images/terrain/mountain/mountain3.png"}));
       sprites.put("TERRAIN_WATER", new Sprite(new String[] {"assets/images/terrain/water/water1.png",
    			   											"assets/images/terrain/water/water2.png",
    			   											"assets/images/terrain/water/water3.png"}));
       sprites.put("GRASS_MINI", new Sprite("assets/images/small/grassmini.png"));
       sprites.put("SAND_MINI", new Sprite("assets/images/small/sandmini.png"));
       sprites.put("WATER_MINI", new Sprite("assets/images/small/watermini.png"));
       sprites.put("MOUNTAIN_MINI", new Sprite("assets/images/small/mountainmini.png"));
       sprites.put("UNIT_SELECTED", new Sprite("assets/images/units/selectedUnit.png"));
       sprites.put("UNIT_G", new Sprite("assets/images/units/green.png"));
       sprites.put("UNIT_B", new Sprite("assets/images/units/blue.png"));
       sprites.put("UNIT_Y", new Sprite("assets/images/units/yellow.png"));
       sprites.put("UNIT_O", new Sprite("assets/images/units/orange.png"));
       sprites.put("UNIT_O_SMALL", new Sprite("assets/images/small/orange.png"));
       sprites.put("UNIT_B_SMALL", new Sprite("assets/images/small/blue.png"));
       sprites.put("UNIT_MELEE", new Sprite("assets/images/units/decal/Melee.png"));
       sprites.put("UNIT_RANGED", new Sprite("assets/images/units/decal/Ranged.png"));
       sprites.put("UNIT_EXPLORER", new Sprite("assets/images/units/decal/Explorer.png"));
       sprites.put("UNIT_COLONIST", new Sprite("assets/images/units/decal/Colonist.png"));
       sprites.put("BASE_SELECTED", new Sprite("assets/images/structure/baseSelected.png"));
       sprites.put("BASE_ARROW", new Sprite("assets/images/structure/baseArrow.png"));
       sprites.put("BASE_G", new Sprite("assets/images/structure/baseGreen.png"));
       sprites.put("BASE_B", new Sprite("assets/images/structure/baseBlue.png"));
       sprites.put("BASE_Y", new Sprite("assets/images/structure/baseYellow.png"));
       sprites.put("BASE_O", new Sprite("assets/images/structure/baseOrange.png"));
       sprites.put("BASE_B_SMALL", new Sprite("assets/images/small/baseblue.png"));
       sprites.put("BASE_O_SMALL", new Sprite("assets/images/small/baseorange.png"));
       sprites.put("ARMY_SELECTED", new Sprite("assets/images/army/selectedArmy.png"));
       sprites.put("ARMY_G", new Sprite("assets/images/army/GreenArmy.png"));
       sprites.put("ARMY_B", new Sprite("assets/images/army/BlueArmy.png"));
       sprites.put("ARMY_Y", new Sprite("assets/images/army/YellowArmy.png"));
       sprites.put("ARMY_O", new Sprite("assets/images/army/OrangeArmy.png"));
       sprites.put("ICON_O", new Sprite("assets/images/icon/orange.png"));
       sprites.put("ICON_B", new Sprite("assets/images/icon/blue.png"));
       sprites.put("RALLY_POINT_SELECTED", new Sprite("assets/images/rallyPoint/selectedRallyPoint.png"));
       sprites.put("DETAILS_PANEL", new Sprite("assets/images/detailsPanel/detailsPanel.png"));
       sprites.put("MOVE_SELECTED1", new Sprite("assets/images/tileCovering/moveSelected1.png"));
       sprites.put("MOVE_SELECTED2", new Sprite("assets/images/tileCovering/moveSelected2.png"));
       sprites.put("MOVE_SELECTED3", new Sprite("assets/images/tileCovering/moveSelected3.png"));
       sprites.put("AOE_DIE", new Sprite("assets/images/areaEffect/loseHealth.png"));
       sprites.put("AOE_LOSE", new Sprite("assets/images/areaEffect/redCross.png"));
       sprites.put("AOE_HEAL", new Sprite("assets/images/areaEffect/skullDecal.png"));
       sprites.put("COMMAND_ASSIGN_WORKER", new Sprite("assets/images/gui/commandIcons/icon/assignWorker.png"));
       sprites.put("COMMAND_ATTACK", new Sprite("assets/images/gui/commandIcons/icons/attack.png"));
       sprites.put("COMMAND_BUILD", new Sprite("assets/images/gui/commandIcons/icons/build.png"));
       sprites.put("COMMAND_CANCEL_QUEUE", new Sprite("assets/images/gui/commandIcons/icons/cancelQueue.png"));
       sprites.put ("COMMAND_DECOMMISSION", new Sprite("assets/images/gui/commandIcons/icons/decommission.png"));
       sprites.put("COMMAND_DEFEND", new Sprite("assets/images/gui/commandIcons/icons/defend.png"));
       sprites.put("COMMAND_DROP_OFF_WORKER", new Sprite("assets/images/gui/commandIcons/icons/dropOffWorker.png"));
       sprites.put("COMMAND_FOUND_CAPITOL", new Sprite("assets/images/gui/commandIcons/icons/foundCapitol.png"));
       sprites.put("COMMAND_GOTO_RALLY_POINT", new Sprite("assets/images/gui/commandIcons/icons/gotoRallyPoint.png"));
       sprites.put("COMMAND_HEAL", new Sprite("assets/images/gui/commandIcons/icons/heal.png"));
       sprites.put("COMMAND_MOVE", new Sprite("assets/images/gui/commandIcons/icons/move.png"));
       sprites.put("COMMAND_PICKUP_WORKER", new Sprite("assets/images/gui/commandIcons/icons/pickupWorker.png"));
       sprites.put("COMMAND_POWER_DOWN", new Sprite("assets/images/gui/commandIcons/icons/powerDown.png"));
       sprites.put("COMMAND_POWER_UP", new Sprite("assets/images/gui/commandIcons/icons/powerUp.png"));
       sprites.put("COMMAND_UNASSIGN_ALL_WORKERS", new Sprite("assets/images/gui/commandIcons/icons/unassignAllWorkers.png"));
       sprites.put("TEXT_PATTERN", new Sprite("assets/images/textTexture.jpg"));
    }
    
    public static Assets getInstance() {
        return INSTANCE;
    }

    public void loadResources() {
        loadSprites();
        loadFonts();
    }

    private void loadFonts() {
        try {
            defaultFont = Font.loadFont(new FileInputStream(new File(FONT)), 22);
            smallFont = Font.loadFont(new FileInputStream(new File(FONT)), 20);
            mediumFont = Font.loadFont(new FileInputStream(new File(FONT)), 25);
            largeFont = Font.loadFont(new FileInputStream(new File(FONT)), 37);
            hugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 53);
            veryHugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 100);
        } catch (IOException e) {
            defaultFont = new Font("Lucida Sans", 20);
            smallFont = new Font("Lucida Sans", 18);
            mediumFont = new Font("Lucida Sans", 21);
            largeFont = new Font("Lucida Sans", 35);
            hugeFont = new Font("Lucida Sans", 55);
            veryHugeFont = new Font("Lucida Sans", 100);
            e.printStackTrace();
        }
    }


    public Sprite getSprite(String sprite) {
        return sprites.get(sprite);
    }
    
    public Font getFont(int size) {
        switch (size) {
            case 0:
                return smallFont;
            case 1:
                return mediumFont;
            case 2:
                return largeFont;
            case 3:
                return hugeFont;
            case 4:
            	return veryHugeFont;
            default:
                return defaultFont;
        }
    }
}
