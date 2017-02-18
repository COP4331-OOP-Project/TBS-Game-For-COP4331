package game.entities;

/**
 * Created by Alex on 2/3/17.
 */
public interface ICombatant {

    void performAttack();          // Return entity atk value for damaging a tile

    void performDefend();          // Return entity armor value for defending a direction

}
