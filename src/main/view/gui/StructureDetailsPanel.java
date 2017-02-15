package view.gui;

import controls.structure.StructureEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class StructureDetailsPanel extends DetailsPanel{
	private Game game;
	Font detailsFont = Assets.getInstance().getFont(0);
	Font bigFont = Assets.getInstance().getFont(3);

	public StructureDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(GraphicsContext gc, int width, int height) {
		drawBar(gc, width, height);
		drawText(gc, height);
	}

	private void drawText(GraphicsContext g, int height) {
		Font old = g.getFont();
		g.setFont(detailsFont);
		g.strokeText("Structure Details:", 10, height - 65);
		if (game.getCurrentPlayer().getBases().size() > 0) {
			g.strokeText("Type: ", 30, height - 35);
			g.strokeText("Health: ", 30, height - 10);
			g.strokeText("Attack: ", 430, height - 35);
			g.strokeText("Defense: ", 430, height - 10);
			g.strokeText("Armor: ", 830, height - 35);
			g.strokeText("Upkeep: ", 830, height - 10);
		
			if (game.getCurrentType() == StructureEnum.BASE) {
				g.strokeText("Base", 130, height - 35);
			}
			g.strokeText(game.getCurrentPlayer().getBases().get(0).getHealth() + "", 130, height - 10);
			g.strokeText(game.getCurrentPlayer().getBases().get(0).getAttackDamage() + "", 530, height - 35);
			g.strokeText(game.getCurrentPlayer().getBases().get(0).getDefenseDamage() + "", 530, height - 10);
			g.strokeText(game.getCurrentPlayer().getBases().get(0).getArmor() + "", 930, height - 35);
			g.strokeText(game.getCurrentPlayer().getBases().get(0).getUpkeep() + "", 930, height - 10);
		} else {
			g.setFont(bigFont);
			g.strokeText("You Have No Structures", 35, height - 17);
		}
		g.setFont(old);
	}
		
}
