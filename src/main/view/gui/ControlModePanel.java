package view.gui;

import controls.ModeEnum;
import controls.army.ArmyEnum;
import controls.command.CommandEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import game.Assets;
import game.Game;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
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

	public void draw(GraphicsContext gc, int width, int height) {
		this.width = width;
		this.height = height;
		updateModes();
		
		drawModePanel(gc);
		drawSubmodePanel(gc);
		drawCommandPanel(gc);

		gc.setFont(modeFont);
		//g.setFont(currentCommandFont);
		drawCurrentCommand(gc);
		drawModeStrings(gc);
		drawSubmodeStrings(gc);
	}
	
	private void drawCommandPanel(GraphicsContext g) {
		g.drawImage(Assets.getInstance().getImage("GUI_COMMAND_PANEL"), PANEL_DISTANCE_FROM_LEFT - 20
				, PANEL_DISTANCE_COMMAND);
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

	private void drawModeStrings(GraphicsContext g) {
		g.strokeText(modeString[0], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION);
		g.strokeText(modeString[1], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + TEXT_SPACING);
		g.strokeText(modeString[2], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + 2*TEXT_SPACING);
		g.strokeText(modeString[3], 30, height/2 - PANEL_DISTANCE_MODE - TEXT1_LOCATION + 3*TEXT_SPACING);
	}
	
	private void drawSubmodeStrings(GraphicsContext g) { 
		g.strokeText(submodeString[mode][0], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION);
		g.strokeText(submodeString[mode][1], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + TEXT_SPACING);
		g.strokeText(submodeString[mode][2], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + 2*TEXT_SPACING);
		g.strokeText(submodeString[mode][3], 30, height/2 - PANEL_DISTANCE_SUBMODE - TEXT2_LOCATION + 3*TEXT_SPACING);
	}
	
	private void drawModePanel(GraphicsContext g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
				, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT);
		switch(mode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_MODE - PANEL_HEIGHT);
				break;
			default:
				log.warn("Invalid Mode to display");
		}
				
	}
	
	private void drawSubmodePanel(GraphicsContext g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT,
				height/2 + PANEL_DISTANCE_SUBMODE);
		if (mode != 0) {
			switch(submode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_SUBMODE);
			default:
				log.warn("Invalid Submode to display");
			}
		}
	}
	
	private void drawCurrentCommand(GraphicsContext g) {
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
		
		g.strokeText(commandString, PANEL_DISTANCE_FROM_LEFT + 30, PANEL_DISTANCE_COMMAND + 48);
	}

}
