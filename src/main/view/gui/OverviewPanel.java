package view.gui;

import game.Assets;
import javafx.scene.canvas.GraphicsContext;
import view.Point;

public abstract class OverviewPanel extends Panel {
    public void drawPanelBox(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(Assets.getInstance().getImage("DETAILS_PANEL"),
                screenDimensions.x / 2 - 400, screenDimensions.y / 2 - 300);
    }
}
