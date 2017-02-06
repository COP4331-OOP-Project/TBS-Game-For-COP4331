package view;

import java.awt.Font;
import java.awt.Graphics;

import controls.ModeEnum;
import controls.army.ArmyEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import game.Assets;
import game.Game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ControlModePanel extends Panel{
	private final static Logger log = LogManager.getLogger(ControlModePanel.class);
	private static final int PANEL_HEIGHT = 114;
	private static final int PANEL_DISTANCE_TOP = 70;
	private static final int PANEL_DISTANCE_BOTTOM = 10;
	private static final int PANEL_DISTANCE_FROM_LEFT = 5;
	private int width;
	private int height;
	private Font modeFont = new Font("Lucida Sans", Font.BOLD, 16);
	private Font subModeFont = new Font("Lucida Sans", Font.BOLD, 12);
	private String[] modeString = {"Rally Point", "Structure", "Unit", "Army"};
	private String[][] submodeString = {{"", "", "", ""}, 
				{"Base", "", "", ""}, 
				{"Explorer", "Colonist", "Melee", "Ranged"}, 
				{"Entire army", "Battle Group", "Reinforcement", ""}};
	private int mode = 0;
	private int submode = 0;
	private Game game;
	
	public ControlModePanel(Game game) {
		this.game = game;
	}

	public void draw(Graphics g, int width, int height) {
		this.width = width;
		this.height = height;
		updateModes();
		drawModePanel(g);
		drawSubmodePanel(g);

		g.setFont(modeFont);
		drawModeStrings(g);

		g.setFont(subModeFont);
		drawSubmodeStrings(g);
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
		g.drawString(modeString[0], 30, height/2 - PANEL_DISTANCE_TOP - 75);
		g.drawString(modeString[1], 30, height/2 - PANEL_DISTANCE_TOP - 39);
		g.drawString(modeString[2], 30, height/2 - PANEL_DISTANCE_TOP - 3);
		g.drawString(modeString[3], 30, height/2 - PANEL_DISTANCE_TOP + 31);
	}
	
	private void drawSubmodeStrings(Graphics g) { 
		g.drawString(submodeString[mode][0], 30, height/2 - PANEL_DISTANCE_BOTTOM + 60);
		g.drawString(submodeString[mode][1], 30, height/2 - PANEL_DISTANCE_BOTTOM + 96);
		g.drawString(submodeString[mode][2], 30, height/2 - PANEL_DISTANCE_BOTTOM + 132);
		g.drawString(submodeString[mode][3], 30, height/2 - PANEL_DISTANCE_BOTTOM + 166);
	}
	
	private void drawModePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
				, height/2 - PANEL_DISTANCE_TOP - PANEL_HEIGHT, null);
		switch(mode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_TOP - PANEL_HEIGHT, null);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_TOP - PANEL_HEIGHT, null);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_TOP - PANEL_HEIGHT, null);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 - PANEL_DISTANCE_TOP - PANEL_HEIGHT, null);
				break;
			default:
				log.warn("Invalid Mode to display");
		}
				
	}
	
	private void drawSubmodePanel(Graphics g) {
		g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT,
				height/2 + PANEL_DISTANCE_BOTTOM ,null);
		if (mode != 0) {
			switch(submode) {
			case 0:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_BOTTOM, null);
				break;
			case 1:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_BOTTOM, null);
				break;
			case 2:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_BOTTOM, null);
				break;
			case 3:
				g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
						, height/2 + PANEL_DISTANCE_BOTTOM, null);
			default:
				log.warn("Invalid Submode to display");
			}
		}
	}
}
