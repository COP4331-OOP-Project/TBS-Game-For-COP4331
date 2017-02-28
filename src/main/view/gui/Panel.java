package view.gui;

import javafx.scene.canvas.GraphicsContext;
import view.Point;

public abstract class Panel {
    private static final int TICKS_PER_IMAGE = 30;
    private static final int IMAGES_TO_LOOP = 4;
    private int animationImage = 0;
    private long animationCount = 0;
    private boolean isVisible = true;
    
    public abstract void draw(GraphicsContext gc, Point screenDimensions);

    public void drawPanel(GraphicsContext gc, Point screenDimensions) {
        if (isVisible) {
            draw(gc, screenDimensions);
        }
    }

    public abstract void hideGUIElements();
    
    public abstract void showGUIElements();
    
    public void setIsVisible(boolean isVisible) {
    	if (isVisible == false) {
    		hideGUIElements();
    	} else {
    		showGUIElements();
    	}
    	this.isVisible = isVisible;
    	
    }
    
    public boolean getIsVisible() {
    	return isVisible;
    }
    
    public void updateAnimationCount() {
        animationCount++;
    }

    public int getAnimationImage() {
        return animationImage;
    }

    public long getAnimationCount() {
        return animationCount;
    }
}
