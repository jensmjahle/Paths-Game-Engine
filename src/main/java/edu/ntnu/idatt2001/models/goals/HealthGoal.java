package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.Player;

/**
 * A class to check if the player has reached the goal of having a certain amount of health.
 */
public class HealthGoal implements Goal {

  /**
   * The minimum amount of health the player needs to have to fulfill the goal.
   */
  private final int minimumHealth;

  /**
   * Constructor for the HealthGoal class.
   *
   * @param minimumHealth The minimum amount of health the player needs to have to fulfill the
   *                      goal.
   */
  public HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * Method to check if the goal is fulfilled by having more health than minimumHealth. It calls the
   * method getHealth() from the class Player.
   *
   * @param player The player to check if the goal is fulfilled.
   * @return true if the player has more health than the minimum amount of health needed to fulfill
   *         the goal.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= minimumHealth;
  }

  /**
   * Method to create a string that will display the minimum amount of health needed to fulfill the
   * goal.
   *
   * @return A string that will display the minimum amount of health needed to fulfill the goal.
   */
  @Override
  public String toString() {
    return "HealthGoal: "
        + "minimumHealth=" + minimumHealth;
  }

  public int getMinimumHealth() {
    return minimumHealth;
  }
}
