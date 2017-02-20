package view.gui;

import controls.ModeEnum;
import controls.command.CommandEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import view.Point;

public class CommandPanel extends Panel{
	
	private DropShadow ds = new DropShadow();
	private Game game;
	Point screenDimensions;
	private static final int COMMAND_Y_NORMAL = 99;
	private static final int COMMAND_Y_RP = 50;
	private int yDistance = COMMAND_Y_NORMAL;
	private static final int ICON_WIDTH = (int)Assets.getInstance().getImage("COMMAND_BUILD").getWidth();
	
	public CommandPanel(Game game) {
		
		this.game = game;
	}

	@Override
	public void draw(GraphicsContext gc, Point screenDimensions) {
		this.screenDimensions = screenDimensions;
        drawCommandPanel(gc);
        drawCurrentCommand(gc);
	}

    private void drawCommandPanel(GraphicsContext g) {
    	if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
    		yDistance = COMMAND_Y_RP;
    	} else {
    		yDistance = COMMAND_Y_NORMAL;
    	}
		g.drawImage(Assets.getInstance().getImage("GUI_COMMAND_PANEL"), 0, yDistance);
    	drawAllButtons(g);
    }
    
    private void drawAllButtons(GraphicsContext g) {
    	drawButton(g, "COMMAND_BUILD", 0, yDistance, false);
    	drawButton(g, "COMMAND_HEAL", ICON_WIDTH, yDistance, false);
    	drawButton(g, "COMMAND_ATTACK", ICON_WIDTH * 2, yDistance, false);
    	drawButton(g, "COMMAND_DEFEND", 0, yDistance + ICON_WIDTH, false);
    	drawButton(g, "COMMAND_POWER_UP", ICON_WIDTH, yDistance + ICON_WIDTH, false);
    	drawButton(g, "COMMAND_POWER_DOWN", ICON_WIDTH * 2, yDistance + ICON_WIDTH, false);
    	drawButton(g, "COMMAND_CANCEL_QUEUE", 0, yDistance + ICON_WIDTH * 2, false);
    	drawButton(g, "COMMAND_DECOMMISSION", ICON_WIDTH, yDistance + ICON_WIDTH * 2, false);
    	drawButton(g, "COMMAND_MOVE", ICON_WIDTH * 2, yDistance + ICON_WIDTH * 2, false);
}

	private void drawCurrentCommand(GraphicsContext g) {
        String commandString = "";
        if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.MAKE) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), 0, yDistance);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.HEAL) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), ICON_WIDTH, yDistance);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.ATTACK) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), ICON_WIDTH * 2, yDistance);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.DEFEND) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), 0, yDistance + ICON_WIDTH);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.POWER_UP) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), ICON_WIDTH, yDistance + + ICON_WIDTH);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.POWER_DOWN) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"), ICON_WIDTH * 2, yDistance + ICON_WIDTH);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.CANCEL_COMMAND_QUEUE) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"),  0, yDistance + ICON_WIDTH * 2);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.DECOMISSION) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"),  ICON_WIDTH, yDistance + ICON_WIDTH * 2);
        } else if (game.getCurrentCommand() != null &&
                game.getCurrentCommand() == CommandEnum.MOVE) {
        	g.drawImage(Assets.getInstance().getImage("COMMAND_HOVERED"),  ICON_WIDTH * 2, yDistance + ICON_WIDTH * 2);
        }
	}
	
	private void drawButton(GraphicsContext g, String icon, int x, int y, boolean selected) {
		if (selected) {
			g.drawImage(Assets.getInstance().getImage("COMMAND_SELECTED"), x, y);
		} else {
			g.drawImage(Assets.getInstance().getImage("COMMAND_UNSELECTED"), x, y);
		}
		g.drawImage(Assets.getInstance().getImage(icon), x, y);
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
