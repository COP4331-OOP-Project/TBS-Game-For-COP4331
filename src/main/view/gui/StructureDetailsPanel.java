package view.gui;

import java.awt.Font;
import java.awt.Graphics;

import controls.structure.StructureEnum;
import game.Assets;
import game.Game;

public class StructureDetailsPanel extends DetailsPanel{
	private Game game;
	Font detailsFont = Assets.getInstance().getFont(0);
	Font bigFont = Assets.getInstance().getFont(3);

	public StructureDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawBar(g, width, height);
		drawText(g, height);
	}

	private void drawText(Graphics g, int height) {
		Font old = g.getFont();
		g.setFont(detailsFont);
		g.drawString("Structure Details:", 10, height - 65);
		if (game.getCurrentPlayer().getBases().size() > 0) {
			g.drawString("Type: ", 30, height - 35);
			g.drawString("Health: ", 30, height - 10);
			g.drawString("Attack: ", 430, height - 35);
			g.drawString("Defense: ", 430, height - 10);
			g.drawString("Armor: ", 830, height - 35);
			g.drawString("Upkeep: ", 830, height - 10);
		
			if (game.getCurrentType() == StructureEnum.BASE) {
				g.drawString("Base", 130, height - 35);
			}
			g.drawString(game.getCurrentPlayer().getBases().get(0).getHealth() + "", 130, height - 10);
			g.drawString(game.getCurrentPlayer().getBases().get(0).getAttackDamage() + "", 530, height - 35);
			g.drawString(game.getCurrentPlayer().getBases().get(0).getDefenseDamage() + "", 530, height - 10);
			g.drawString(game.getCurrentPlayer().getBases().get(0).getArmor() + "", 930, height - 35);
			g.drawString(game.getCurrentPlayer().getBases().get(0).getUpkeep() + "", 930, height - 10);
		} else {
			g.setFont(bigFont);
			g.drawString("You Have No Structures", 35, height - 17);
		}
		g.setFont(old);
	}
		
}
