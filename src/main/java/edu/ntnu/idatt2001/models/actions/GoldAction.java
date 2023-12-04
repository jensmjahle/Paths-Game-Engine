package edu.ntnu.idatt2001.models.actions;

import edu.ntnu.idatt2001.models.Player;

/**
 * An action that can be executed by the player. Specifically, this action updates the gold the
 * player has.
 */
public class GoldAction implements Action {

  /**
   * The amount of gold to add to the player.
   */
  private final int gold;

  /**
   * Constructor for the GoldAction class.
   *
   * @param gold The amount of gold to add to the player.
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }

  /**
   * Method to get the amount of gold to add to the player.
   *
   * @return The amount of gold to add to the player.
   */
  public int getGold() {
    return gold;
  }


  /**
   * Executes the action on the given player. It calls the method addGold() from the class Player.
   *
   * @param player The player to execute the action.
   */
  @Override
  public void execute(Player player) {
    player.addGold(gold);
  }

  /**
   * Method to create a string that will display the amount of gold to add to the player.
   *
   * @return A string that will display the amount of gold to add to the player.
   */
  @Override
  public String toString() {
    return "GoldAction: "
        + gold;
  }
}
