package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.Player;

/**
 * A class to check if the player has reached the goal of having a certain amount of gold.
 */
public class GoldGoal implements Goal {

  /**
   * The minimum amount of gold the player needs to have to fulfill the goal.
   */
  private final int minimumGold;

  /**
   * Constructor for the GoldGoal class.
   *
   * @param minimumGold The minimum amount of gold the player needs to have to fulfill the goal.
   */
  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * Method to check if the goal is fulfilled by having more gold than minimumGold. It calls the
   * method getGold() from the class Player.
   *
   * @param player The player to check if the goal is fulfilled.
   * @return true if the player has more gold than the minimum
   *         amount of gold needed to fulfill the goal.
   *
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getGold() >= minimumGold;
  }

  /**
   * Method to create a string that will display the minimum amount of gold needed to fulfill the
   * goal.
   *
   * @return A string that will display the minimum amount of gold needed to fulfill the goal.
   */
  @Override
  public String toString() {
    return "GoldGoal: "
            + "minimumGold=" + minimumGold;
  }

  public int getGold() {
    return minimumGold;
  }
}
