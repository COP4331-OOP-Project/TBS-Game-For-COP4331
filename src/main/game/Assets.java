package game;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class Assets {
    private final static Logger log = LogManager.getLogger(Assets.class);
    private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
    private static final String GUI_TOP = "assets/images/gui/topBar.png";
    private static final String GUI_BOTTOM = "assets/images/gui/bottomBar.png";
    private static final String GUI_COMMAND_PANEL = "assets/images/gui/mode/commandPanel.png";
    private static final String GUI_MINI_MAP_BORDER = "assets/images/gui/miniBorder.png";
    private static final String GUI_MODE_PANEL = "assets/images/gui/mode/modePanel.png";
    private static final String GUI_SUBMODE_PANEL = "assets/images/gui/mode/subModePanel.png";
    private static final String GUI_MODE_SELECTED1 = "assets/images/gui/mode/selectedMode1.png";
    private static final String GUI_MODE_SELECTED2 = "assets/images/gui/mode/selectedMode2.png";
    private static final String GUI_MODE_SELECTED3 = "assets/images/gui/mode/selectedMode3.png";
    private static final String GUI_MODE_SELECTED4 = "assets/images/gui/mode/selectedMode4.png";
    private static final String TERRAIN_SAND = "assets/images/terrain/sand/sand.png";
    private static final String TERRAIN_GRASS1 = "assets/images/terrain/grass/grass1.png";
    private static final String TERRAIN_GRASS2 = "assets/images/terrain/grass/grass2.png";
    private static final String TERRAIN_GRASS3 = "assets/images/terrain/grass/grass3.png";
    private static final String TERRAIN_MOUNTAIN1 = "assets/images/terrain/mountain/mountain1.png";
    private static final String TERRAIN_MOUNTAIN2 = "assets/images/terrain/mountain/mountain2.png";
    private static final String TERRAIN_MOUNTAIN3 = "assets/images/terrain/mountain/mountain3.png";
    private static final String TERRAIN_WATER1 = "assets/images/terrain/water/water1.png";
    private static final String TERRAIN_WATER2 = "assets/images/terrain/water/water2.png";
    private static final String TERRAIN_WATER3 = "assets/images/terrain/water/water3.png";
    private static final String GRASS_MINI = "assets/images/small/grassmini.png";
    private static final String SAND_MINI = "assets/images/small/sandmini.png";
    private static final String WATER_MINI = "assets/images/small/watermini.png";
    private static final String MOUNTAIN_MINI = "assets/images/small/mountainmini.png";
    private static final String UNIT_SELECTED = "assets/images/units/selectedUnit.png";
    private static final String UNIT_G = "assets/images/units/green.png";
    private static final String UNIT_B = "assets/images/units/blue.png";
    private static final String UNIT_Y = "assets/images/units/yellow.png";
    private static final String UNIT_O = "assets/images/units/orange.png";
    private static final String UNIT_O_SMALL = "assets/images/small/orange.png";
    private static final String UNIT_B_SMALL = "assets/images/small/blue.png";
    private static final String UNIT_MELEE = "assets/images/units/decal/Melee.png";
    private static final String UNIT_RANGED = "assets/images/units/decal/Ranged.png";
    private static final String UNIT_EXPLORER = "assets/images/units/decal/Explorer.png";
    private static final String UNIT_COLONIST = "assets/images/units/decal/Colonist.png";
    private static final String BASE_SELECTED = "assets/images/structure/baseSelected.png";
    private static final String BASE_ARROW = "assets/images/structure/baseArrow.png";
    private static final String BASE_G = "assets/images/structure/baseGreen.png";
    private static final String BASE_B = "assets/images/structure/baseBlue.png";
    private static final String BASE_Y = "assets/images/structure/baseYellow.png";
    private static final String BASE_O = "assets/images/structure/baseOrange.png";
    private static final String BASE_B_SMALL = "assets/images/small/baseblue.png";
    private static final String BASE_O_SMALL = "assets/images/small/baseorange.png";
    private static final String ARMY_SELECTED = "assets/images/army/selectedArmy.png";
    private static final String ARMY_G = "assets/images/army/GreenArmy.png";
    private static final String ARMY_B = "assets/images/army/BlueArmy.png";
    private static final String ARMY_Y = "assets/images/army/YellowArmy.png";
    private static final String ARMY_O = "assets/images/army/OrangeArmy.png";
    private static final String ICON_O = "assets/images/icon/orange.png";
    private static final String ICON_B = "assets/images/icon/blue.png";
    private static final String RALLY_POINT_SELECTED = "assets/images/rallyPoint/selectedRallyPoint.png";
    private static final String DETAILS_PANEL = "assets/images/detailsPanel/detailsPanel.png";
    private static final String MOVE_SELECTED = "assets/images/tileCovering/moveSelected.png";
    private static final String AOE_DIE = "assets/images/areaEffect/loseHealth.png";
    private static final String AOE_LOSE = "assets/images/areaEffect/redCross.png";
    private static final String AOE_HEAL = "assets/images/areaEffect/skullDecal.png";
    private static final String FONT = "assets/fonts/nuku.ttf";
    private static final String TEXT_PATTERN = "assets/images/textTexture.jpg";
    private HashMap<String, Integer> assets = new HashMap<String, Integer>();
    private Font defaultFont;
    private Font smallFont;
    private Font mediumFont;
    private Font largeFont;
    private Font hugeFont;
    private ImagePattern text;

    private ArrayList<Image> gameImages;
    private int lastItemLoaded = 0;

    private Assets() {
    } //Constructor is Private, only one instance of Resources can be created

    public static Assets getInstance() {
        return INSTANCE;
    }

    public void loadResources() {
        loadImages();
        loadFonts();
    }

    private void loadFonts() {
        try {
            defaultFont = Font.loadFont(new FileInputStream(new File(FONT)), 22);
            smallFont = Font.loadFont(new FileInputStream(new File(FONT)), 20);
            mediumFont = Font.loadFont(new FileInputStream(new File(FONT)), 24);
            largeFont = Font.loadFont(new FileInputStream(new File(FONT)), 37);
            hugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 57);
        } catch (IOException e) {
            defaultFont = new Font("Lucida Sans", 20);
            smallFont = new Font("Lucida Sans", 18);
            mediumFont = new Font("Lucida Sans", 21);
            largeFont = new Font("Lucida Sans", 35);
            hugeFont = new Font("Lucida Sans", 55);
            e.printStackTrace();
        }
    }

    private void loadImages() {
        gameImages = new ArrayList<Image>();
        loadItem("GUI_TOP", GUI_TOP);
        loadItem("GUI_BOTTOM", GUI_BOTTOM);

        loadItem("GUI_COMMAND_PANEL", GUI_COMMAND_PANEL);
        loadItem("GUI_MINI_MAP_BORDER", GUI_MINI_MAP_BORDER);

        loadItem("GUI_MODE_SELECTED1", GUI_MODE_SELECTED1);
        loadItem("GUI_MODE_SELECTED2", GUI_MODE_SELECTED2);
        loadItem("GUI_MODE_SELECTED3", GUI_MODE_SELECTED3);
        loadItem("GUI_MODE_SELECTED4", GUI_MODE_SELECTED4);

        loadItem("GUI_SUBMODE_PANEL", GUI_SUBMODE_PANEL);
        loadItem("GUI_MODE_PANEL", GUI_MODE_PANEL);

        loadItem("TERRAIN_SAND", TERRAIN_SAND);
        loadItem("TERRAIN_GRASS1", TERRAIN_GRASS1);
        loadItem("TERRAIN_GRASS2", TERRAIN_GRASS2);
        loadItem("TERRAIN_GRASS3", TERRAIN_GRASS3);
        loadItem("TERRAIN_MOUNTAIN1", TERRAIN_MOUNTAIN1);
        loadItem("TERRAIN_MOUNTAIN2", TERRAIN_MOUNTAIN2);
        loadItem("TERRAIN_MOUNTAIN3", TERRAIN_MOUNTAIN3);
        loadItem("TERRAIN_WATER1", TERRAIN_WATER1);
        loadItem("TERRAIN_WATER2", TERRAIN_WATER2);
        loadItem("TERRAIN_WATER3", TERRAIN_WATER3);

        loadItem("GRASS_MINI", GRASS_MINI);
        loadItem("SAND_MINI", SAND_MINI);
        loadItem("WATER_MINI", WATER_MINI);
        loadItem("MOUNTAIN_MINI", MOUNTAIN_MINI);
        
        loadItem("BASE_O_SMALL", BASE_O_SMALL);
        loadItem("BASE_B_SMALL", BASE_B_SMALL);
        loadItem("UNIT_O_SMALL", UNIT_O_SMALL);
        loadItem("UNIT_B_SMALL", UNIT_B_SMALL);

        loadItem("UNIT_SELECTED", UNIT_SELECTED);
        loadItem("UNIT_MELEE", UNIT_MELEE);
        loadItem("UNIT_RANGED", UNIT_RANGED);
        loadItem("UNIT_EXPLORER", UNIT_EXPLORER);
        loadItem("UNIT_COLONIST", UNIT_COLONIST);

        loadItem("UNIT_G", UNIT_G);
        loadItem("UNIT_B", UNIT_B);
        loadItem("UNIT_Y", UNIT_Y);
        loadItem("UNIT_O", UNIT_O);

        loadItem("BASE_SELECTED", BASE_SELECTED);
        loadItem("BASE_ARROW", BASE_ARROW);
        loadItem("BASE_G", BASE_G);
        loadItem("BASE_B", BASE_B);
        loadItem("BASE_Y", BASE_Y);
        loadItem("BASE_O", BASE_O);

        loadItem("ARMY_SELECTED", ARMY_SELECTED);
        loadItem("ARMY_G", ARMY_G);
        loadItem("ARMY_B", ARMY_B);
        loadItem("ARMY_Y", ARMY_Y);
        loadItem("ARMY_O", ARMY_O);

        loadItem("ICON_O", ICON_O);
        loadItem("ICON_B", ICON_B);

        loadItem("RALLY_POINT_SELECTED", RALLY_POINT_SELECTED);
        loadItem("DETAILS_PANEL", DETAILS_PANEL);
        loadItem("MOVE_SELECTED", MOVE_SELECTED);

        loadItem("AOE_DIE", AOE_DIE);
        loadItem("AOE_LOSE", AOE_LOSE);
        loadItem("AOE_HEAL", AOE_HEAL);
        loadItem("TEXT_PATTERN", TEXT_PATTERN);
    }

    private void loadItem(String name, String path) {
        File file = new File(path);
        String localUrl = "";
        try {
            localUrl = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        gameImages.add(new Image(localUrl));
        assets.put(name, lastItemLoaded);
        log.info("Loaded Item: " + name);
        lastItemLoaded++;
    }

    public Image getImage(String image) {
        return gameImages.get(assets.get(image));
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
            default:
                return defaultFont;
        }
    }
}
