package view;

import java.io.File;
import java.net.MalformedURLException;
import javafx.scene.image.Image;

public class Sprite {
	private int animationSpeed = 10;
	private int currentPulse = 0;
	private Image[] images;
	
	public Sprite(String filepath) {
		images = new Image[1];
		File file = new File(filepath);
		String localUrl = "";
		try {
			localUrl = file.toURI().toURL().toString();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		images[0] = new Image(localUrl);		
	}
	
	public Sprite(String[] filepaths) {
		for (int i = 0; i < filepaths.length; i++) {
			
		}
	}

	public void updateAnimation() {
		//updateCurrentPulse
	}
	
	public void setAnimationSpeed(int speed) {
		this.animationSpeed = animationSpeed;
	}
}
