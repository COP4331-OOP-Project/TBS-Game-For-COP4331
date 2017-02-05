package game;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

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
	
	private static final String GUI_MODE_PANEL = "assets/gui/mode/modePanel.png";
	private static final String GUI_MODE_SELECTED1 = "assets/gui/mode/selected1.png";
	private static final String GUI_MODE_SELECTED2 = "assets/gui/mode/selected2.png";
	private static final String GUI_MODE_SELECTED3 = "assets/gui/mode/selected3.png";
	private static final String GUI_MODE_SELECTED4 = "assets/gui/mode/selected4.png";
	
	private static final String TERRAIN_SAND = "assets/terrain/sand.jpg";
	private static final String TERRAIN_GRASS = "assets/terrain/grass.jpg";
	private static final String TERRAIN_WATER1 = "assets/terrain/water1.jpg";
	private static final String TERRAIN_WATER2 = "assets/terrain/water2.jpg";
	private static final String TERRAIN_WATER3 = "assets/terrain/water3.jpg";
	
	private static final String OUTLINE_MINI = "assets/small/outline.jpg";
	private static final String GRASS_MINI = "assets/small/grassmini.jpg";
	private static final String SAND_MINI = "assets/small/sandmini.jpg";
	private static final String WATER_MINI = "assets/small/watermini.jpg";
	
	private static final String UNIT_SELECTED = "assets/units/selectedUnit.png";
	private static final String UNIT_G = "assets/units/green.png";
	private static final String UNIT_B = "assets/units/blue.png";
	private static final String UNIT_Y = "assets/units/yellow.png";
	private static final String UNIT_O = "assets/units/orange.png";
	
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
	
	private static final String ARMY_SELECTED = "assets/army/selectedArmy.png";
	private static final String ARMY_G = "assets/army/GreenArmy.png";
	private static final String ARMY_B = "assets/army/BlueArmy.png";
	private static final String ARMY_Y = "assets/army/YellowArmy.png";
	private static final String ARMY_O = "assets/army/OrangeArmy.png";
	
	private static final String RALLY_POINT_SELECTED = "assets/rallyPoint/selectedRallyPoint.png";
	
	private ArrayList<BufferedImage> gameImages;
	private int lastItemLoaded = 0;
	
	private Assets() {} //Constructor is Private, only one instance of Resources can be created

	public void loadResources() {
		gameImages = new ArrayList<BufferedImage>();
		loadItem("GUI_TOP_LEFT", GUI_TOP_LEFT);
		loadItem("GUI_TOP_MIDDLE", GUI_TOP_MIDDLE);
		loadItem("GUI_TOP_RIGHT", GUI_TOP_RIGHT);
		
		loadItem("GUI_BOTTOM_LEFT", GUI_BOTTOM_LEFT);
		loadItem("GUI_BOTTOM_MIDDLE", GUI_BOTTOM_MIDDLE);
		loadItem("GUI_BOTTOM_RIGHT", GUI_BOTTOM_RIGHT);
		
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
		

		loadItem("OUTLINE_MINI", OUTLINE_MINI);
		loadItem("GRASS_MINI", GRASS_MINI);
		loadItem("SAND_MINI", SAND_MINI);
		loadItem("WATER_MINI", WATER_MINI);
		
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
		
		loadItem("RALLY_POINT_SELECTED", RALLY_POINT_SELECTED);
		
	}
	
	private void loadItem(String name, String path) {
		try {
			gameImages.add(ImageIO.read(new File(path)));
			assets.put(name, lastItemLoaded);
			log.debug("File Loaded: " + path);
			
			lastItemLoaded++;
		} catch (IOException e) {
			log.warn("File Not Found: " + path);
			e.printStackTrace();
		}
	}
	
	public BufferedImage getImage(String image) {
		return gameImages.get(assets.get(image));
	}
	
	public static Assets getInstance() {
		return INSTANCE;
	}
}
