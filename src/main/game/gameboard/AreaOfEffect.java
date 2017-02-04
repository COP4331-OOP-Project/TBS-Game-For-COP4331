package game.gameboard;


/**
 * Created by David on 2/1/2017.
 */

//Save for iteration 2
public class AreaOfEffect {
    public boolean damage;
    public boolean heal;
    public int damageAmount;
    public int healAmount;

    public AreaOfEffect(boolean damage, boolean heal, int damageAmount, int healAmount){
        this.damage=damage;
        this.heal=heal;
        if(damage){
            this.damageAmount=damageAmount;
        }
        else{
            this.damageAmount=0;
        }
        if(heal){
            this.healAmount=healAmount;
        }
        else{
            this.healAmount=0;
        }
    }
}
