package view.gui;

import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Point;

public class StructureOverviewPanel extends OverviewPanel {
    private Game game;

    public StructureOverviewPanel(Game game) {
    	this.setIsVisible(false);
        this.game = game;
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawPanelBox(gc, screenDimensions);
        Font oldFont = gc.getFont();
        gc.setFill(Color.WHITE);
        gc.setFont(Assets.getInstance().getFont(2));
        gc.fillText("Structure Overview", screenDimensions.x / 2 - 370, screenDimensions.y / 2 - 245);
        gc.setFont(Assets.getInstance().getFont(1));
        for (int i = 0; i < game.getCurrentPlayer().getBaseCount(); i++) {
            gc.fillText("Base", screenDimensions.x / 2 - 370, screenDimensions.y / 2 + (i * 30) - 200);
        }
        gc.setFill(Color.BLACK);
        gc.setFont(oldFont);
    }
}
