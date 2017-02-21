package view.gui.game;
import game.Assets;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import view.Point;
import view.gui.Panel;

public class HoverPanel extends Panel {
    String text;
    Group root;

    public HoverPanel() {
    }

    public void drawText(GraphicsContext gc, Point location, String text) {
        Font oldFont = gc.getFont();
        gc.drawImage(Assets.getInstance().getImage("GUI_HOVER"), location.x, location.y);
        gc.setFont(Assets.getInstance().getFont(0));
        gc.fillText(text, location.x + 10, location.y + 25);
        gc.setFont(oldFont);
    }

    @Override
    public void draw(GraphicsContext gc, Point screenDimensions) {
    }

    @Override
    public void hideGUIElements() {
    }

    @Override
    public void showGUIElements() {
    }
}
