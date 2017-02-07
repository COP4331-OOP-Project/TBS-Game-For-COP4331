package view.gui;

import game.Assets;

import game.Assets;
import game.Game;
import game.Player;
import view.Panel;

import java.awt.Graphics;

import controls.command.CommandEnum;

import java.awt.Font;

public class CivilizationPanel extends Panel{
	Font currentCommandFont = new Font("Lucida Sans", Font.BOLD, 20);
	Font civInfoFont = new Font("Lucida Sans", Font.BOLD, 20);
	private Game game;

	public CivilizationPanel(Game game) {
		this.game = game;
	}

	public static int GUI_PANEL_WIDTH =
			Assets.getInstance().getImage("GUI_TOP_LEFT").getWidth();
	public static int GUI_PANEL_HEIGHT =
			Assets.getInstance().getImage("GUI_TOP_LEFT").getHeight();

	public void draw(Graphics g, int screenWidth, int screenHeight) {
		drawBar(g, screenWidth, screenHeight);
		drawText(g);
		drawPlayerIcon(g);
		drawCurrentCommand(g, screenWidth, screenHeight);
	}

	private void drawCurrentCommand(Graphics g, int width, int height) {
		Font oldFont = g.getFont();
		g.setFont(currentCommandFont);
		String commandString = "";
		if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.MAKE) {
			commandString = "Make";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.HEAL) {
			commandString = "Heal";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.ATTACK) {
			commandString = "Attack";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.DEFEND) {
			commandString = "Defend";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.POWER_UP) {
			commandString = "Power Up";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.POWER_DOWN) {
			commandString = "Power Down";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.CANCEL_COMMAND_QUEUE) {
			commandString = "Cancel Command Queue";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.DECOMISSION) {
			commandString = "Decomission";
		} else if (game.getCurrentCommand() != null && 
				game.getCurrentCommand() == CommandEnum.MOVE) {
			commandString = "Move";
		} else if (game.getCurrentCommand() == null) {
			commandString = "None";
		}
		
		g.drawString("Current Command: " + commandString, width - 470, 35);
		g.setFont(oldFont);
		
	}

	private void drawPlayerIcon(Graphics g) {
		if (game.getCurrentPlayer().getPlayerID() == 0) {
			g.drawImage(Assets.getInstance().getImage("ICON_O"), 80, 17, null);
		} else {
			g.drawImage(Assets.getInstance().getImage("ICON_B"), 80, 17, null);
		}
		
	}

	private void drawText(Graphics g) {
		Font old = g.getFont();
		g.setFont(civInfoFont);
		g.drawString("Player: ", 5, 35);
		g.drawString("Turn: "+ game.getTurnNum(), 5, 65);
		g.setFont(old);
	}

	//Draw the blue panel itself
	private void drawBar(Graphics g, int screenWidth, int screenHeight) {
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_LEFT"), 0, 0, null);
		g.drawImage(Assets.getInstance().getImage("GUI_TOP_RIGHT"),
				screenWidth - GUI_PANEL_WIDTH, 0, null);
		int distanceFromRight = screenWidth - GUI_PANEL_WIDTH;
		for(int i = GUI_PANEL_WIDTH; i < distanceFromRight; i++) {
			g.drawImage(Assets.getInstance().getImage("GUI_TOP_MIDDLE"), i, 0, null);
		}
	}
}
