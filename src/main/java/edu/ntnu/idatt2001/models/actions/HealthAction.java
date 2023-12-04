package edu.ntnu.idatt2001.models.actions;

import edu.ntnu.idatt2001.models.Player;

/**
 * An action that can be executed by the player. Specifically, this action updates the health the
 * player has.
 */
public class HealthAction implements Action {

  /**
   * The amount of health to add to the player.
   */
  private final int health;

  /**
   * Constructor for the HealthAction class.
   *
   * @param health The amount of health to add to the player.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * Method to get the amount of health to add to the player.
   *
   * @return The amount of health to add to the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Executes the action on the given player. It calls the method addHealth() from the class
   * Player.
   *
   * @param player The player to execute the action.
   */
  @Override
  public void execute(Player player) {
    player.addHealth(health);
  }

  /**
   * Method to create a string that will display the amount of health to add to the player.
   *
   * @return A string that will display the amount of health to add to the player.
   */
  @Override
  public String toString() {
    return "HealthAction: "
        + health;
  }
}
