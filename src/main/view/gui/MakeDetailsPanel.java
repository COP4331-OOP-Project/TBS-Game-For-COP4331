package view.gui;

import controls.unit.UnitEnum;
import game.Assets;
import game.Game;
import game.entities.units.Unit;

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
    private static final int PANEL_DISTANCE_TOP = 70;
    private static final int PANEL_DISTANCE_BOTTOM = 10;
    private static final int PANEL_DISTANCE_FROM_LEFT = 160;
    private static final int COLONIST_LIST = 0;
    private static final int BASE_LIST = 1;

    private int width;
    private int height;

    private Font modeFont = new Font("Lucida Sans", Font.BOLD, 16);
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

        g.setFont(modeFont);
        drawTypeStrings(g);

    }

    // Draw make list strings
    private void drawTypeStrings(Graphics g) {
        g.drawString(makeList[type][0], PANEL_DISTANCE_FROM_LEFT + 50, height/2 - PANEL_DISTANCE_TOP - 75);
        g.drawString(makeList[type][1], PANEL_DISTANCE_FROM_LEFT + 50, height/2 - PANEL_DISTANCE_TOP - 39);
        g.drawString(makeList[type][2], PANEL_DISTANCE_FROM_LEFT + 50, height/2 - PANEL_DISTANCE_TOP - 3);
        g.drawString(makeList[type][3], PANEL_DISTANCE_FROM_LEFT + 50, height/2 - PANEL_DISTANCE_TOP + 31);
    }

}

