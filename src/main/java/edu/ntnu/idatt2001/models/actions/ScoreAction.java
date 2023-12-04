package edu.ntnu.idatt2001.models.actions;

import edu.ntnu.idatt2001.models.Player;

/**
 * An action that can be executed by the player. Specifically, this action updates the score the
 * player has.
 */
public class ScoreAction implements Action {

  /**
   * The amount of score to add to the player.
   */
  private final int points;

  /**
   * Constructor for the ScoreAction class.
   *
   * @param points The amount of score to add to the player.
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * Executes the action on the given player. It calls the method addScore() from the class Player.
   *
   * @param player The player to execute the action.
   */
  @Override
  public void execute(Player player) {
    player.addScore(points);
  }

  /**
   * Method to get the amount of score to add to the player.
   *
   * @return The amount of score to add to the player.
   */
  public int getScore() {
    return points;
  }

  /**
   * Method to create a string that will display the amount of score to add to the player.
   *
   * @return A string that will display the amount of score to add to the player.
   */
  @Override
  public String toString() {
    return "ScoreAction: "
        + points;
  }
}
