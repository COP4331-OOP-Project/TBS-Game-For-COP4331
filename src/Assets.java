import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class Assets {
	
	private static final Assets INSTANCE = new Assets(); //This is the one instance of resources (singleton)
	private Hashtable<String, Integer> assets = new Hashtable<String, Integer>();
	//private static final String IMAGE = "assets/image.png"; - Sample image
	private ArrayList<BufferedImage> gameImages;
	
	private Assets() {} //Constructor is Private, only one instance of Resources can be created

	public void loadResources() {
		gameImages = new ArrayList<BufferedImage>();
		/* Rewrite this part
		try {
			Rewrite this part
			gameImages.add(ImageIO.read(new File(IMAGE)));
			assets.put("IMAGE", 0);
		} catch (IOException e) {
			System.out.println("File Not Found");
			e.printStackTrace();
		}
		*/	
	}
	
	public BufferedImage getImage(String image) {
		return gameImages.get(assets.get(image));
	}
	
	public static Assets getInstance() {
		return INSTANCE;
	}
}
