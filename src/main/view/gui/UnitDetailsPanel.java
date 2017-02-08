package view.gui;

import java.awt.Font;
import java.awt.Graphics;

import controls.unit.UnitEnum;
import game.Game;

public class UnitDetailsPanel extends DetailsPanel{
	private Game game;
	Font detailsFont = new Font("Lucida Sans", Font.BOLD, 20);
	Font bigFont = new Font("Lucida Sans", Font.BOLD, 35);
	
	public UnitDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawBar(g, width, height);
		drawText(g, height);
	}

	private void drawText(Graphics g, int height) {
		Font old = g.getFont();
		g.setFont(detailsFont);
		g.drawString("Unit Details", 10, height - 65);
		if (game.getSelectedUnit() != -1) {
			g.drawString("Type: ", 30, height - 35);
			g.drawString("Health: ", 30, height - 10);
			g.drawString("Attack: ", 430, height - 35);
			g.drawString("Defense: ", 430, height - 10);
			g.drawString("Armor: ", 830, height - 35);
			g.drawString("Upkeep: ", 830, height - 10);
			if (game.getCurrentType() == UnitEnum.EXPLORER) {
				g.drawString("Explorer", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.COLONIST) {
				g.drawString("Colonist", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.MELEE) {
				g.drawString("Melee", 130, height - 35);
			}
			if (game.getCurrentType() == UnitEnum.RANGED) {
				g.drawString("Ranged", 130, height - 35);
			}
			g.drawString(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getHealth() + "", 130, height - 10);
			g.drawString(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getAttackDamage() + "", 530, height - 35);
			g.drawString(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getDefenseDamage() + "", 530, height - 10);
			g.drawString(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getArmor() + "", 930, height - 35);
			g.drawString(game.getCurrentPlayer().getAllUnit().get(game.getSelectedUnit()).getUpkeep() + "", 930, height - 10);
		} else {
			g.setFont(bigFont);
			if (game.getCurrentType() == UnitEnum.EXPLORER) {
				g.drawString("You Have No Explorer Units", 35, height - 25);
			}
			if (game.getCurrentType() == UnitEnum.COLONIST) {
				g.drawString("You Have No Colonist Units", 35, height - 25);
			}
			if (game.getCurrentType() == UnitEnum.MELEE) {
				g.drawString("You Have No Melee Units", 35, height - 25);
			}
			if (game.getCurrentType() == UnitEnum.RANGED) {
				g.drawString("You Have No Ranged Units", 35, height - 25);
			}
		}
		g.setFont(old);
	}

}