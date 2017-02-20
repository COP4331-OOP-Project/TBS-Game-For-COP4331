package view.gui;

import controls.ModeEnum;
import controls.army.ArmyEnum;
import controls.structure.StructureEnum;
import controls.unit.UnitEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.Point;

public class ControlModePanel extends Panel {
	
    private final static Logger log = LogManager.getLogger(ControlModePanel.class);
    private static final int MODE_Y = 0;
    private static final int SUBMODE_X = -18;
    private static final int SUBMODE_Y = 115;
    private static final int TEXT_SPACING = 172;
    private static final int TEXT1_LOCATION = 13;
    private static final int TEXT2_LOCATION = 10;
	private static final int MODE_TEXT_HEIGHT = 87;
	private DropShadow ds = new DropShadow();
	private Point screenDimensions;
    private Font modeFont = Assets.getInstance().getFont(1);
    private String[] modeString = {"Rally Point", "Structure", "Unit", "Army"};
    private String submodeString = "";
    private int mode = 0;
    private Game game;

    public ControlModePanel(Game game) {
        this.game = game;
        ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
    	this.screenDimensions = screenDimensions;
        updateModes();
        drawModePanel(gc);
        drawSubmodePanel(gc);

        gc.setFont(modeFont);
        //g.setFont(currentCommandFont);
        
        gc.setEffect(ds);
        drawModeStrings(gc);
        drawSubmodeStrings(gc);
        gc.setEffect(null);
    }




    private void updateModes() {
        if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
            mode = 0;
            submodeString = "";
        } else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
            mode = 1;
            if (game.getCurrentType() == StructureEnum.BASE) {
            	submodeString = "Base";
            }
        } else if (game.getCurrentMode() == ModeEnum.UNIT) {
            mode = 2;
            if (game.getCurrentType() == UnitEnum.EXPLORER)
            	submodeString = "Explorer";
            if (game.getCurrentType() == UnitEnum.COLONIST)
            	submodeString = "Colonist";
            if (game.getCurrentType() == UnitEnum.MELEE)
            	submodeString = "Melee";
            if (game.getCurrentType() == UnitEnum.RANGED)
            	submodeString = "Ranged";
        } else if (game.getCurrentMode() == ModeEnum.ARMY) {
            mode = 3;
            if (game.getCurrentType() == ArmyEnum.ENTIRE_ARMY)
            	submodeString = "Entire Army";
            if (game.getCurrentType() == ArmyEnum.BATTLE_GROUP)
            	submodeString = "Battle Group";
            if (game.getCurrentType() == ArmyEnum.REINFORCEMENTS)
            	submodeString = "Reinforcements";
        }


    }

    private void drawModeStrings(GraphicsContext g) {
        g.fillText(modeString[0], TEXT1_LOCATION, MODE_TEXT_HEIGHT);
        g.fillText(modeString[1], TEXT1_LOCATION + TEXT_SPACING, MODE_TEXT_HEIGHT);
        g.fillText(modeString[2], TEXT1_LOCATION + 2 * TEXT_SPACING, MODE_TEXT_HEIGHT);
        g.fillText(modeString[3], TEXT1_LOCATION + 3 * TEXT_SPACING, MODE_TEXT_HEIGHT);
    }

    private void drawSubmodeStrings(GraphicsContext g) {
        g.fillText(submodeString, 10, TEXT2_LOCATION);
    }

    private void drawModePanel(GraphicsContext g) {
    	Image img = Assets.getInstance().getImage("GUI_MODE_PANEL");
        g.drawImage(img, screenDimensions.x - img.getWidth() , MODE_Y);
        switch (mode) {
            case 0:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 1:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 2:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            case 3:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), screenDimensions.x - img.getWidth() , MODE_Y);
                break;
            default:
                log.warn("Invalid Mode to display");
        }

    }

    private void drawSubmodePanel(GraphicsContext g) {
        g.drawImage(Assets.getInstance().getImage("GUI_SUBMODE_PANEL"), SUBMODE_X, SUBMODE_Y);
    }



	@Override
	public void hideGUIElements() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showGUIElements() {
		// TODO Auto-generated method stub
		
	}

}
