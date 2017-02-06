package view;

import java.awt.Font;
import java.awt.Graphics;

import controls.unit.UnitEnum;
import game.Game;

public class UnitDetailsPanel extends DetailsPanel{
	private Game game;
	Font detailsFont = new Font("Lucida Sans", Font.BOLD, 20);
	
	public UnitDetailsPanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		drawBar(g, width, height);
		drawText(g, height);
	}

	private void drawText(Graphics g, int height) {
		int health = 0;
		int attack = 0;
		int defense = 0;
		int armor = 0;
		int upkeep = 0;
		Font old = g.getFont();
		//System.out.println(game.getCurrentType());
		g.setFont(detailsFont);
		g.drawString("Unit Details", 10, height - 65);
		g.drawString("Type: ", 30, height - 35);
		g.drawString("Health: ", 30, height - 10);
		g.drawString("Attack: ", 430, height - 35);
		g.drawString("Defense: ", 430, height - 10);
		g.drawString("Armor: ", 830, height - 35);
		g.drawString("Upkeep: ", 830, height - 10);
		System.out.println(game.getSelectedUnit());
		if (game.getSelectedUnit() != -1) {
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
		}
		g.setFont(old);
		
	}

}