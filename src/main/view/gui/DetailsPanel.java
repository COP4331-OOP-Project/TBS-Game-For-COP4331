package view.gui;

import game.Assets;
import javafx.scene.canvas.GraphicsContext;
import view.Point;

public abstract class DetailsPanel extends Panel {
    private static int GUI_PANEL_WIDTH =
            (int) Assets.getInstance().getImage("GUI_BOTTOM").getWidth();
    private static int GUI_PANEL_HEIGHT =
            (int) Assets.getInstance().getImage("GUI_BOTTOM").getHeight();

    void drawBar(GraphicsContext gc, Point screenDimensions) {
        gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM"), 0, screenDimensions.y - GUI_PANEL_HEIGHT);
    }

}
