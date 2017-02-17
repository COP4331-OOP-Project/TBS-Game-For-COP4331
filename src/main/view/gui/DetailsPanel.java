package view.gui;

import game.Assets;
import javafx.scene.canvas.GraphicsContext;
import view.Panel;

public abstract class DetailsPanel extends Panel {
    private static int GUI_PANEL_WIDTH =
            (int) Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getWidth();
    private static int GUI_PANEL_HEIGHT =
            (int) Assets.getInstance().getImage("GUI_BOTTOM_LEFT").getHeight();

    void drawBar(GraphicsContext gc, int width, int height) {
        gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_LEFT"), 0, height - GUI_PANEL_HEIGHT);
        gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_RIGHT"),
                width - GUI_PANEL_WIDTH, height - GUI_PANEL_HEIGHT);
        int distanceFromRight = width - GUI_PANEL_WIDTH;
        for (int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
            gc.drawImage(Assets.getInstance().getImage("GUI_BOTTOM_MIDDLE"), i, height - GUI_PANEL_HEIGHT);
        }
    }

}
