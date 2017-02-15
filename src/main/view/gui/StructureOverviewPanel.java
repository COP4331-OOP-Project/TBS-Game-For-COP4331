package view.gui;

import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class StructureOverviewPanel extends OverviewPanel{
	private Game game;
	public StructureOverviewPanel(Game game) {
		this.game = game;
	}

	public void draw(GraphicsContext gc, int width, int height) {
		drawPanelBox(gc, width, height);
		Font oldFont = gc.getFont();
		gc.setFill(Color.WHITE);
		gc.setFont(Assets.getInstance().getFont(2));
		gc.strokeText("Structure Overview", width/2 - 370, height/2 - 245);
		gc.setFont(Assets.getInstance().getFont(1));
		for (int i = 0; i < game.getCurrentPlayer().getBaseCount(); i++) {
			gc.strokeText("Base", width/2 - 370, height/2 + (i * 30) - 200);
		}
		gc.setFill(Color.BLACK);
		gc.setFont(oldFont);
	}
}
