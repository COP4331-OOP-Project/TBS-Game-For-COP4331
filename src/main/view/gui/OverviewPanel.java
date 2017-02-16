package view.gui;

import game.Assets;
import javafx.scene.canvas.GraphicsContext;
import view.Panel;

public abstract class OverviewPanel extends Panel{
	public void drawPanelBox(GraphicsContext gc, int width, int height) {
		gc.drawImage(Assets.getInstance().getImage("DETAILS_PANEL"),
				width/2 - 400, height/2 - 300);
	}
}
