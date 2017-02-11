package view.gui;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import controls.ModeEnum;
import controls.army.ArmyEnum;
import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import game.Assets;
import game.Game;
import view.Panel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControlModePanel extends Panel{
	private final static Logger log = LogManager.getLogger(ControlModePanel.class);
	private static final int PANEL_HEIGHT = 114;
	private static final int PANEL_DISTANCE_MODE = 78;
	private static final int PANEL_DISTANCE_SUBMODE = 35;
	private static final int PANEL_DISTANCE_COMMAND = 95;
	private static final int PANEL_DISTANCE_FROM_LEFT = 5;
	private static final int TEXT_SPACING = 48;
	private static final int TEXT1_LOCATION = 58;
	private static final int TEXT2_LOCATION = -125;
	private int width;
	private int height;
	private Font modeFont = Assets.getInstance().getFont(1);
	private String[] modeString = {"Rally Point", "Structure", "Unit", "Army"};
	private String[][] submodeString = {{"", "", "", ""}, 
				{"Base", "", "", ""}, 
				{"Explorer", "Colonist", "Melee", "Ranged"}, 
				{"Entire Army", "Battle Group", "Reinforcement", ""}};
	private int mode = 0;
	private int submode = 0;
	private Game game;
	
	public ControlModePanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics2D g, int width, int height) {
		this.width = width;
		this.height = height;
		updateModes();
		
		drawModePanel(g);
		drawSubmodePanel(g);
		drawCommandPanel(g);

		g.setFont(modeFont);
		//g.setFont(currentCommandFont);
		drawCurrentCommand(g);
		drawModeStrings(g);
		drawSubmodeStrings(g);
	}
	
	private void drawCommandPanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_COMMAND_PANEL"), PANEL_DISTANCE_FROM_LEFT - 20
				, PANEL_DISTANCE_COMMAND, null);
	}

	private void updateModes() {
		if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
			mode = 0;
			submode = 0;
		} else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
			mode = 1;
			if (game.getCurrentType() == StructureEnum.BASE) {
				submode = 0;
			}
		} else if (game.getCurrentMode() == ModeEnum.UNIT) {
			mode = 2;
				if (game.getCurrentType() == UnitEnum.EXPLORER)
					submode = 0;
				if (game.getCurrentType() == UnitEnum.COLONIST)
					submode = 1;
				if (game.getCurrentType() == UnitEnum.MELEE)
					submode = 2;
				if (game.getCurrentType() == UnitEnum.RANGED)
					submode = 3;
		} else if (game.getCurrentMode() == ModeEnum.ARMY) {
			mode = 3;
				if (game.getCurrentType() == ArmyEnum.ENTIRE_ARMY)
					submode = 0;
				if (game.getCurrentType() == ArmyEnum.BATTLE_GROUP)
					submode = 1;
				if (game.getCurrentType() == ArmyEnum.REINFORCEMENTS)
					submode = 2;
		}
		
			
	}

	private void drawModeStrings(Graphics g) {
		g.drawString(modeString[0], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION);
		g.drawString(modeString[1], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + TEXT_SPACING);
		g.drawString(modeString[2], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + 2*TEXT_SPACING);
		g.drawString(modeString[3], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + 3*TEXT_SPACING);
	}
	
	private void drawSubmodeStrings(Graphics g) { 
		g.drawString(submodeString[mode][0], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION);
		g.drawString(submodeString[mode][1], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + TEXT_SPACING);
		g.drawString(submodeString[mode][2], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + 2*TEXT_SPACING);
		g.drawString(submodeString[mode][3], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + 3*TEXT_SPACING);
	}
	
	private void drawModePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
				, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT, null);
		switch(mode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT, null);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT, null);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT, null);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT, null);
				break;
			default:
				log.warn("Invalid Mode to display");
		}
				
	}
	
	private void drawSubmodePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT,
				height/2 + PANEL_DISTANCE_SUBMODE ,null);
		if (mode != 0) {
			switch(submode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE, null);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE, null);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE, null);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE, null);
			default:
				log.warn("Invalid Submode to display");
			}
		}
	}
	
	private void drawCurrentCommand(Graphics g) {
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
		
		g.drawString(commandString, PANEL_DISTANCE_FROM_LEFT + 30, PANEL_DISTANCE_COMMAND + 48);
	}

}
