package view.gui;

import game.Assets;
import javafx.scene.canvas.GraphicsContext;
import view.Point;

public abstract class DetailsPanel extends Panel {
    private static int GUI_PANEL_WIDTH =
            (int) Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getWidth();
    private static int GUI_PANEL_HEIGHT =
            (int) Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getHeight();

    void drawBar(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_LEFT"), 0, screenDimensions.y - GUI_PANEL_HEIGHT);
        gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_RIGHT"),
                screenDimensions.x - GUI_PANEL_WIDTH, screenDimensions.y - GUI_PANEL_HEIGHT);
        int distanceFromRight = screenDimensions.x - GUI_PANEL_WIDTH;
        for (int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
            gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_MIDDLE"), i, screenDimensions.y - GUI_PANEL_HEIGHT);
        }
    }

}
