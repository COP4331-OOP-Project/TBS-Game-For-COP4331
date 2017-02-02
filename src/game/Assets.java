package game;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Assets {
	
	private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
	private Hashtable<String, Integer> assets = new Hashtable<String, Integer>();
	private static final String GUI_TOP_LEFT = "assets/gui/leftTop.png";
	private static final String GUI_TOP_MIDDLE = "assets/gui/barMiddle.png";
	private static final String GUI_TOP_RIGHT = "assets/gui/rightTop.png";
	
	private static final String TERRAIN_SAND = "assets/terrain/sand.jpg";
	private static final String TERRAIN_GRASS = "assets/terrain/grass.jpg";
	private static final String TERRAIN_WATER1 = "assets/terrain/water1.jpg";
	private static final String TERRAIN_WATER2 = "assets/terrain/water2.jpg";
	private static final String TERRAIN_WATER3 = "assets/terrain/water3.jpg";
	
	private static final String UNIT_MELEE_G = "assets/units/green/GreenMelee.png";
	private static final String UNIT_RANGED_G = "assets/units/green/GreenRanged.png";
	private static final String UNIT_EXPLORER_G = "assets/units/green/GreenExplorer.png";
	private static final String UNIT_COLONIST_G = "assets/units/green/GreenColonist.png";
	
	private static final String UNIT_MELEE_B = "assets/units/blue/BlueMelee.png";
	private static final String UNIT_RANGED_B = "assets/units/blue/BlueRanged.png";
	private static final String UNIT_EXPLORER_B = "assets/units/blue/BlueExplorer.png";
	private static final String UNIT_COLONIST_B = "assets/units/blue/BlueColonist.png";
	
	private static final String UNIT_MELEE_Y = "assets/units/yellow/YellowMelee.png";
	private static final String UNIT_RANGED_Y = "assets/units/yellow/YellowRanged.png";
	private static final String UNIT_EXPLORER_Y = "assets/units/yellow/YellowExplorer.png";
	private static final String UNIT_COLONIST_Y = "assets/units/yellow/YellowColonist.png";
	
	private static final String UNIT_MELEE_O = "assets/units/orange/OrangeMelee.png";
	private static final String UNIT_RANGED_O = "assets/units/orange/OrangeRanged.png";
	private static final String UNIT_EXPLORER_O = "assets/units/orange/OrangeExplorer.png";
	private static final String UNIT_COLONIST_O = "assets/units/orange/OrangeColonist.png";
	
	private ArrayList<BufferedImage> gameImages;
	private int lastItemLoaded = 0;
	
	private Assets() {} //Constructor is Private, only one instance of Resources can be created

	public void loadResources() {
		gameImages = new ArrayList<BufferedImage>();
		loadItem("GUI_TOP_LEFT", GUI_TOP_LEFT);
		loadItem("GUI_TOP_MIDDLE", GUI_TOP_MIDDLE);
		loadItem("GUI_TOP_RIGHT", GUI_TOP_RIGHT);
		
		loadItem("TERRAIN_SAND", TERRAIN_SAND);
		loadItem("TERRAIN_GRASS", TERRAIN_GRASS);
		loadItem("TERRAIN_WATER1", TERRAIN_WATER1);
		loadItem("TERRAIN_WATER2", TERRAIN_WATER2);
		loadItem("TERRAIN_WATER3", TERRAIN_WATER3);
		
		loadItem("UNIT_MELEE_G", UNIT_MELEE_G);
		loadItem("UNIT_RANGED_G", UNIT_RANGED_G);
		loadItem("UNIT_EXPLORER_G", UNIT_EXPLORER_G);
		loadItem("UNIT_COLONIST_G", UNIT_COLONIST_G);
			
		loadItem("UNIT_MELEE_B", UNIT_MELEE_B);
		loadItem("UNIT_RANGED_B", UNIT_RANGED_B);
		loadItem("UNIT_EXPLORER_B", UNIT_EXPLORER_B);
		loadItem("UNIT_COLONIST_B", UNIT_COLONIST_B);
			
		loadItem("UNIT_MELEE_Y", UNIT_MELEE_Y);
		loadItem("UNIT_RANGED_Y", UNIT_RANGED_Y);
		loadItem("UNIT_EXPLORER_Y", UNIT_EXPLORER_Y);
		loadItem("UNIT_COLONIST_Y", UNIT_COLONIST_Y);
			
		loadItem("UNIT_MELEE_O", UNIT_MELEE_O);
		loadItem("UNIT_RANGED_O", UNIT_RANGED_O);
		loadItem("UNIT_EXPLORER_O", UNIT_EXPLORER_O);
		loadItem("UNIT_COLONIST_O", UNIT_COLONIST_O);
	}
	
	private void loadItem(String name, String path) {
		try {
			gameImages.add(ImageIO.read(new File(path)));
			assets.put(name, lastItemLoaded);
			System.out.println("File Loaded: " + path); 
			lastItemLoaded++;
		} catch (IOException e) {
			System.out.println("File Not Found: " + path);
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
