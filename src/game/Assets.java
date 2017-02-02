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
	private static final String TERRAIN_WATER = "assets/terrain/water.jpg";
	private ArrayList<BufferedImage> gameImages;
	
	private Assets() {} //Constructor is Private, only one instance of Resources can be created

	public void loadResources() {
		gameImages = new ArrayList<BufferedImage>();
		try {
			gameImages.add(ImageIO.read(new File(GUI_TOP_LEFT)));
			assets.put("GUI_TOP_LEFT", 0);
			gameImages.add(ImageIO.read(new File(GUI_TOP_MIDDLE)));
			assets.put("GUI_TOP_MIDDLE", 1);
			gameImages.add(ImageIO.read(new File(GUI_TOP_RIGHT)));
			assets.put("GUI_TOP_RIGHT", 2);
			gameImages.add(ImageIO.read(new File(TERRAIN_SAND)));
			assets.put("TERRAIN_SAND", 3);
			gameImages.add(ImageIO.read(new File(TERRAIN_GRASS)));
			assets.put("TERRAIN_GRASS", 4);
			gameImages.add(ImageIO.read(new File(TERRAIN_WATER)));
			assets.put("TERRAIN_WATER", 5);
		} catch (IOException e) {
			System.out.println("File Not Found");
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
