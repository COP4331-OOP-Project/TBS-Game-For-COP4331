package game.gameboard;

/**
 * Created by David on 2/1/2017.
 */

//TODO Add resources in iteration 2
public class Resource {
    public int[] ResourceAmount;


    public Resource(int amount1, int amount2, int amount3) {
        ResourceAmount = new int[3];
        ResourceAmount[0] = amount1;
        ResourceAmount[1] = amount2;
        ResourceAmount[2] = amount3;
    }
}
