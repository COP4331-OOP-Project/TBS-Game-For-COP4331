package view.gui;

import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.Font;
import view.Point;


public class CivilizationPanel extends Panel {
	DropShadow ds = new DropShadow();
    public static int GUI_PANEL_WIDTH =
            (int) Assets.getInstance().getImage("GUI_TOP").getWidth();
    public static int GUI_PANEL_HEIGHT =
            (int) Assets.getInstance().getImage("GUI_TOP").getHeight();
    Font civInfoFont = Assets.getInstance().getFont(2);
    private Game game;
    ImagePattern textFill = new ImagePattern(Assets.getInstance().getImage("TEXT_PATTERN"), 0, 0, 1, 1, true);
    
    public CivilizationPanel(Game game) {
        this.game = game;
    	ds.setOffsetY(2.0f);
    	ds.setColor(Color.color(0, 0, 0));
    }

    
    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawBar(gc, screenDimensions);
        drawText(gc);
        drawPlayerIcon(gc);
    }


    private void drawPlayerIcon(GraphicsContext g) {
        if (game.getCurrentPlayer().getPlayerID() == 0) {
            g.drawImage(Assets.getInstance().getImage("ICON_O"), 130, 3);
        } else {
            g.drawImage(Assets.getInstance().getImage("ICON_B"), 130, 3);
        }
    }

    private void drawText(GraphicsContext g) {
        Font old = g.getFont();
        g.setFont(civInfoFont);
        g.setFill(textFill);
        g.setEffect(ds);
        g.fillText("Player: ", 10, 34);
        g.fillText("Turn: " + game.getTurnNum() + "   O: 1-1   E: 2-2   F: 3-5", 180, 34);
        g.setEffect(null);
        g.setFont(old);
    }

    //Draw the blue panel itself
    private void drawBar(GraphicsContext g, Point screenDimensions) {
        g.drawImage(Assets.getInstance().getImage("GUI_TOP"), 0, 0);
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
