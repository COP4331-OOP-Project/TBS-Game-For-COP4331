package view.gui;

import controls.unit.UnitEnum;
import game.Assets;
import game.Game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;

/**
 * Created by Alex on 2/5/17.
 */
public class MakeDetailsPanel {

    Game game;
    private final static Logger log = LogManager.getLogger(ControlModePanel.class);
    private static final int PANEL_HEIGHT = 114;
    private static final int PANEL_DISTANCE = 78;
    private static final int PANEL_DISTANCE_FROM_LEFT = 210;
	private static final int TEXT_SPACING = 48;
	private static final int TEXT_LOCATION = 58;
    private static final int COLONIST_LIST = 0;
    private static final int BASE_LIST = 1;
	private static final int TEXT_OFFSET = 28;

    private int width;
    private int height;

    private Font modeFont = Assets.getInstance().getFont(1);
    private String[][] makeList = {{"Base", "", "", ""},
            {"Melee", "Ranged", "Colonist", "Explorer"}};

    private int type = 0;
    private int mode = 0;

    // Constructor
    public MakeDetailsPanel(Game game) {
        this.game = game;
    }

    private void updateType() {
        if (game.getCurrentType() == UnitEnum.COLONIST) type = COLONIST_LIST;
        else type = BASE_LIST;

        this.mode = game.getCurrentMakeOption();
    }

    // Draw the panel
    public void draw(Graphics g, int width, int height) {

        this.width = width;
        this.height = height;

        updateType();

        g.drawImage(Assets.getInstance().getImage("GUI_MODE_PANEL"), PANEL_DISTANCE_FROM_LEFT
                , height/2 - PANEL_DISTANCE - PANEL_HEIGHT, null);

        switch(mode) {
            case 0:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED1"), PANEL_DISTANCE_FROM_LEFT
                        , height/2 - PANEL_DISTANCE - PANEL_HEIGHT, null);
                break;
            case 1:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED2"), PANEL_DISTANCE_FROM_LEFT
                        , height/2 - PANEL_DISTANCE - PANEL_HEIGHT, null);
                break;
            case 2:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED3"), PANEL_DISTANCE_FROM_LEFT
                        , height/2 - PANEL_DISTANCE - PANEL_HEIGHT, null);
                break;
            case 3:
                g.drawImage(Assets.getInstance().getImage("GUI_MODE_SELECTED4"), PANEL_DISTANCE_FROM_LEFT
                        , height/2 - PANEL_DISTANCE - PANEL_HEIGHT, null);
                break;
            default:
                log.warn("Invalid Mode to display");
        }

        g.setFont(modeFont);
        drawTypeStrings(g);

    }

    // Draw make list strings
    private void drawTypeStrings(Graphics g) {
		g.drawString(makeList[type][0], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, height/2 - PANEL_DISTANCE - TEXT_LOCATION);
		g.drawString(makeList[type][1], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, height/2 - PANEL_DISTANCE - TEXT_LOCATION + TEXT_SPACING);
		g.drawString(makeList[type][2], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, height/2 - PANEL_DISTANCE - TEXT_LOCATION + 2*TEXT_SPACING);
		g.drawString(makeList[type][3], PANEL_DISTANCE_FROM_LEFT + TEXT_OFFSET, height/2 - PANEL_DISTANCE - TEXT_LOCATION + 3*TEXT_SPACING);
    }

}

