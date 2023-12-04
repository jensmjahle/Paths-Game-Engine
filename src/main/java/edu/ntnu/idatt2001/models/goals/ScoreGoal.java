package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.Player;

/**
 * A class to check if the player has reached the goal of having a certain amount of points.
 */
public class ScoreGoal implements Goal {

  /**
   * The minimum amount of points the player needs to have to fulfill the goal.
   */
  private final int minimumPoints;

  /**
   * Constructor for the ScoreGoal class.
   *
   * @param minimumPoints The minimum amount of points the player needs to have to fulfill the
   *                      goal.
   */
  public ScoreGoal(int minimumPoints) {
    this.minimumPoints = minimumPoints;
  }

  /**
   * Method to check if the goal is fulfilled by having more points than minimumPoints. It calls the
   * method getScore() from the class Player.
   *
   * @param player The player to check if the goal is fulfilled.
   * @return true if the player has more points than the minimum amount of points needed to fulfill
   *         the goal.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getScore() >= minimumPoints;
  }

  /**
   * Method to create a string that will display the minimum amount of points needed to fulfill the
   * goal.
   *
   * @return A string that will display the minimum amount of points needed to fulfill the goal.
   */
  @Override
  public String toString() {
    return "ScoreGoal: "
        + "minimumPoints=" + minimumPoints;
  }

  public int getScore() {
    return minimumPoints;
  }
}
