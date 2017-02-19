package view.gui;

import controls.structure.StructureEnum;
import game.Assets;
import game.Game;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.Point;

public class StructureDetailsPanel extends DetailsPanel {
    Font detailsFont = Assets.getInstance().getFont(0);
    Font bigFont = Assets.getInstance().getFont(3);
    private Game game;

    public StructureDetailsPanel(Game game) {
        this.game = game;
    }

    public void draw(GraphicsContext gc, Point screenDimensions) {
        drawBar(gc, screenDimensions);
        drawText(gc, screenDimensions.y);
    }

    private void drawText(GraphicsContext g, int height) {
        Font old = g.getFont();
        g.setFont(detailsFont);
        g.fillText("Structure Details:", 10, height - 65);
        if (game.getCurrentPlayer().getBases().size() > 0) {
            g.fillText("Type: ", 30, height - 35);
            g.fillText("Health: ", 30, height - 10);
            g.fillText("Attack: ", 430, height - 35);
            g.fillText("Defense: ", 430, height - 10);
            g.fillText("Armor: ", 830, height - 35);
            g.fillText("Upkeep: ", 830, height - 10);

            if (game.getCurrentType() == StructureEnum.BASE) {
                g.fillText("Base", 130, height - 35);
            }
            g.fillText(game.getCurrentPlayer().getBases().get(0).getHealth() + "", 130, height - 10);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getAttackDamage() + "", 530, height - 35);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getDefenseDamage() + "", 530, height - 10);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getArmor() + "", 930, height - 35);
            g.fillText(game.getCurrentPlayer().getBases().get(0).getUpkeep() + "", 930, height - 10);
        } else {
            g.setFont(bigFont);
            g.fillText("You Have No Structures", 35, height - 17);
        }
        g.setFont(old);
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
