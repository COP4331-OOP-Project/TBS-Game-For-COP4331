package game;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Hashtable;

import javafx.scene.image.Image;
import javafx.scene.text.Font;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Assets {
	private final static Logger log = LogManager.getLogger(Assets.class);
	private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
	private Hashtable<String, Integer> assets = new Hashtable<String, Integer>();
	
	private static final String GUI_TOP_LEFT = "assets/gui/leftTop.png";
	private static final String GUI_TOP_MIDDLE = "assets/gui/barMiddle.png";
	private static final String GUI_TOP_RIGHT = "assets/gui/rightTop.png";
	
	private static final String GUI_BOTTOM_LEFT = "assets/gui/leftBottom.png";
	private static final String GUI_BOTTOM_MIDDLE = "assets/gui/barMiddleBottom.png";
	private static final String GUI_BOTTOM_RIGHT = "assets/gui/rightBottom.png";
	
	private static final String GUI_COMMAND_PANEL = "assets/gui/mode/commandPanel.png";
	private static final String GUI_MINI_MAP_BORDER = "assets/gui/miniBorder.png";
	private static final String GUI_MODE_PANEL = "assets/gui/mode/modePanel.png";
	private static final String GUI_MODE_SELECTED1 = "assets/gui/mode/selected1.png";
	private static final String GUI_MODE_SELECTED2 = "assets/gui/mode/selected2.png";
	private static final String GUI_MODE_SELECTED3 = "assets/gui/mode/selected3.png";
	private static final String GUI_MODE_SELECTED4 = "assets/gui/mode/selected4.png";
	

	private static final String GUI_MAIN_MODE_PANEL = "assets/gui/mode/mainModePanel.png";
	private static final String GUI_MAIN_MODE_SELECTED1 = "assets/gui/mode/selectedMode1.png";
	private static final String GUI_MAIN_MODE_SELECTED2 = "assets/gui/mode/selectedMode2.png";
	private static final String GUI_MAIN_MODE_SELECTED3 = "assets/gui/mode/selectedMode3.png";
	private static final String GUI_MAIN_MODE_SELECTED4 = "assets/gui/mode/selectedMode4.png";
	
	private static final String TERRAIN_SAND = "assets/terrain/sand.png";
	private static final String TERRAIN_GRASS = "assets/terrain/grass.png";
	private static final String TERRAIN_WATER1 = "assets/terrain/water1.png";
	private static final String TERRAIN_WATER2 = "assets/terrain/water2.png";
	private static final String TERRAIN_WATER3 = "assets/terrain/water3.png";
	
	private static final String GRASS_MINI = "assets/small/grassmini.png";
	private static final String SAND_MINI = "assets/small/sandmini.png";
	private static final String WATER_MINI = "assets/small/watermini.png";
	
	private static final String UNIT_SELECTED = "assets/units/selectedUnit.png";
	private static final String UNIT_G = "assets/units/green.png";
	private static final String UNIT_B = "assets/units/blue.png";
	private static final String UNIT_Y = "assets/units/yellow.png";
	private static final String UNIT_O = "assets/units/orange.png";
	
	private static final String UNIT_O_SMALL = "assets/small/orange.png";
	private static final String UNIT_B_SMALL = "assets/small/blue.png";
	
	private static final String UNIT_MELEE = "assets/units/decal/Melee.png";
	private static final String UNIT_RANGED = "assets/units/decal/Ranged.png";
	private static final String UNIT_EXPLORER = "assets/units/decal/Explorer.png";
	private static final String UNIT_COLONIST = "assets/units/decal/Colonist.png";
	
	private static final String BASE_SELECTED = "assets/structure/baseSelected.png";
	private static final String BASE_ARROW = "assets/structure/baseArrow.png";
	private static final String BASE_G = "assets/structure/baseGreen.png";
	private static final String BASE_B = "assets/structure/baseBlue.png";
	private static final String BASE_Y = "assets/structure/baseYellow.png";
	private static final String BASE_O = "assets/structure/baseOrange.png";

	private static final String BASE_B_SMALL = "assets/small/baseblue.png";
	private static final String BASE_O_SMALL = "assets/small/baseorange.png";
	
	private static final String ARMY_SELECTED = "assets/army/selectedArmy.png";
	private static final String ARMY_G = "assets/army/GreenArmy.png";
	private static final String ARMY_B = "assets/army/BlueArmy.png";
	private static final String ARMY_Y = "assets/army/YellowArmy.png";
	private static final String ARMY_O = "assets/army/OrangeArmy.png";
	
	private static final String ICON_O = "assets/icon/orange.png";
	private static final String ICON_B = "assets/icon/blue.png";
	
	private static final String RALLY_POINT_SELECTED = "assets/rallyPoint/selectedRallyPoint.png";
	private static final String DETAILS_PANEL = "assets/detailsPanel/detailsPanel.png";
	
	private static final String MOVE_SELECTED = "assets/tileCovering/moveSelected.png";
	
	private static final String AOE_DIE = "assets/areaEffect/loseHealth.png";
	private static final String AOE_LOSE = "assets/areaEffect/redCross.png";
	private static final String AOE_HEAL = "assets/areaEffect/skullDecal.png";
	
	private static final String FONT = "assets/fonts/Lato-Black.ttf";
	
	private Font defaultFont;
	private Font smallFont;
	private Font mediumFont;
	private Font largeFont;
	private Font hugeFont;
	
	private ArrayList<Image> gameImages;
	private int lastItemLoaded = 0;
	
	private Assets() {} //Constructor is Private, only one instance of Resources can be created

	public void loadResources() {
		loadImages();
		loadFonts();
	}
	
	private void loadFonts() {
		try {
			defaultFont = Font.loadFont(new FileInputStream(new File(FONT)), 20);
			smallFont = Font.loadFont(new FileInputStream(new File(FONT)), 18);
			mediumFont = Font.loadFont(new FileInputStream(new File(FONT)), 21);
			largeFont = Font.loadFont(new FileInputStream(new File(FONT)), 35);
			hugeFont = Font.loadFont(new FileInputStream(new File(FONT)), 55);
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
		loadItem("GUI_TOP_LEFT", GUI_TOP_LEFT);
		loadItem("GUI_TOP_MIDDLE", GUI_TOP_MIDDLE);
		loadItem("GUI_TOP_RIGHT", GUI_TOP_RIGHT);
		
		loadItem("GUI_BOTTOM_LEFT", GUI_BOTTOM_LEFT);
		loadItem("GUI_BOTTOM_MIDDLE", GUI_BOTTOM_MIDDLE);
		loadItem("GUI_BOTTOM_RIGHT", GUI_BOTTOM_RIGHT);
		
		loadItem("GUI_COMMAND_PANEL", GUI_COMMAND_PANEL);
		loadItem("GUI_MINI_MAP_BORDER", GUI_MINI_MAP_BORDER);
		
		

		loadItem("GUI_MAIN_MODE_PANEL", GUI_MAIN_MODE_PANEL);
		loadItem("GUI_MAIN_MODE_SELECTED1", GUI_MAIN_MODE_SELECTED1);
		loadItem("GUI_MAIN_MODE_SELECTED2", GUI_MAIN_MODE_SELECTED2);
		loadItem("GUI_MAIN_MODE_SELECTED3", GUI_MAIN_MODE_SELECTED3);
		loadItem("GUI_MAIN_MODE_SELECTED4", GUI_MAIN_MODE_SELECTED4);
		
		
		loadItem("GUI_MODE_PANEL", GUI_MODE_PANEL);
		loadItem("GUI_MODE_SELECTED1", GUI_MODE_SELECTED1);
		loadItem("GUI_MODE_SELECTED2", GUI_MODE_SELECTED2);
		loadItem("GUI_MODE_SELECTED3", GUI_MODE_SELECTED3);
		loadItem("GUI_MODE_SELECTED4", GUI_MODE_SELECTED4);
		
		loadItem("TERRAIN_SAND", TERRAIN_SAND);
		loadItem("TERRAIN_GRASS", TERRAIN_GRASS);
		loadItem("TERRAIN_WATER1", TERRAIN_WATER1);
		loadItem("TERRAIN_WATER2", TERRAIN_WATER2);
		loadItem("TERRAIN_WATER3", TERRAIN_WATER3);

		loadItem("GRASS_MINI", GRASS_MINI);
		loadItem("SAND_MINI", SAND_MINI);
		loadItem("WATER_MINI", WATER_MINI);

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
	}
	
	private void loadItem(String name, String path) {
		File file = new File(path);
		String localUrl = "";
		try {
			localUrl = file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
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
	
	public static Assets getInstance() {
		return INSTANCE;
	}
}
