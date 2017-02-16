package view.gui;

import controls.unit.UnitEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;

public class UnitDetailsPanel extends DetailsPanel{
	private Game game;
	Font detailsFont = Assets.getInstance().getFont(0);
	Font bigFont = Assets.getInstance().getFont(3);
	
	public UnitDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(GraphicsContext gc, int width, int height) {
		drawBar(gc, width, height);
		drawText(gc, height);
	}

	private void drawText(GraphicsContext g, int height) {
		Font old = g.getFont();
		g.setFont(detailsFont);
		g.fillText("Unit Details", 10, height - 65);
		if (game.getSelectedUnit() != -1) {
			g.fillText("Type: ", 30, height - 35);
			g.fillText("Health: ", 30, height - 10);
			g.fillText("Attack: ", 430, height - 35);
			g.fillText("Defense: ", 430, height - 10);
			g.fillText("Armor: ", 830, height - 35);
			g.fillText("Upkeep: ", 830, height - 10);
			if (game.getCurrentType() == UnitEnum.EXPLORER) {
				g.fillText("Explorer", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.COLONIST) {
				g.fillText("Colonist", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.MELEE) {
				g.fillText("Melee", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.RANGED) {
				g.fillText("Ranged", 130, height - 35);
			}
			g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getHealth() + "", 130, height - 10);
			g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getAttackDamage() + "", 530, height - 35);
			g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getDefenseDamage() + "", 530, height - 10);
			g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getArmor() + "", 930, height - 35);
			g.fillText(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getUpkeep() + "", 930, height - 10);
		} else {
			g.setFont(bigFont);
			if (game.getCurrentType() == UnitEnum.EXPLORER) {
				g.fillText("You Have No Explorer Units", 35, height - 17);
			}
			if (game.getCurrentType() == UnitEnum.COLONIST) {
				g.fillText("You Have No Colonist Units", 35, height - 17);
			}
			if (game.getCurrentType() == UnitEnum.MELEE) {
				g.fillText("You Have No Melee Units", 35, height - 17);
			}
			if (game.getCurrentType() == UnitEnum.RANGED) {
				g.fillText("You Have No Ranged Units", 35, height - 17);
			}
		}
		g.setFont(old);
	}

}