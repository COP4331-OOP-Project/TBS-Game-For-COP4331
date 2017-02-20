package view.gui;

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
        g.drawImage(Assets.getInstance().getImage("GUI_COMMAND_PANEL"), 0, screenDimensions.y - 250);
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
