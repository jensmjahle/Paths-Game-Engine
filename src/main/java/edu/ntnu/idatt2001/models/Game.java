package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.goals.Goal;
import java.io.Serializable;
import java.util.List;

/**
 * A class to represent a game.
 */
public class Game implements Serializable {

  /**
   * The player in the game.
   */
  private final Player player;

  /**
   * Boolean to check if goals are fulfilled.
   */
  private boolean isFulfilled;

  /**
   * The story of the game.
   */
  private final Story story;

  /**
   * The goals of the game.
   */
  private final List<Goal> goals;

  private Link linkToCurrentPassage;

  /**
   * Constructor for the Game class.
   *
   * @param player The player in the game.
   * @param story  The story of the game.
   * @param goals  The goals of the game.
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.player = player;
    this.story = story;
    this.goals = goals;
  }

  /**
   * Method to get current player.
   *
   * @return The player in the game.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * Method to get current story.
   *
   * @return The story of the game.
   */
  public Story getStory() {
    return story;
  }

  /**
   * Method to get current goals.
   *
   * @return The goals of the game.
   */
  public List<Goal> getGoals() {
    return goals;
  }

  /**
   * Method to start a new game and get the opening passage.
   *
   * @return The opening passage of the story.
   */
  public Passage begin() {
    if (linkToCurrentPassage == null) {
      return story.getOpeningPassage();
    } else {
      return story.getPassage(linkToCurrentPassage);
    }
  }

  /**
   * Method to get the next passage.
   *
   * @param link The link to the next passage.
   * @return The next passage.
   */
  public Passage go(Link link) {
    if (link == null) {
      return null;
    }

    setLinkToCurrentPassage(link);
    return story.getPassage(link);

  }

  /**
   * Method to get the link to the next passage.
   *
   * @return The link to the next passage.
   */
  public Link getLinkToCurrentPassage() {
    return linkToCurrentPassage;
  }

  /**
   * Method to set the link to the next passage.
   *
   * @param linkToCurrentPassage The link to the next passage.
   */
  public void setLinkToCurrentPassage(Link linkToCurrentPassage) {
    this.linkToCurrentPassage = linkToCurrentPassage;
  }

  /**
   * Method to check if the goals are fulfilled.
   * If the goals are fulfilled, the game is over.
   *
   * @return True if the goals are fulfilled, false if not.
   */
  public boolean checkGoals() {
    isFulfilled = true;
    for (Goal goal : goals) {
      if (!goal.isFulfilled(player)) {
        isFulfilled = false;
      }
    }
    if (goals.isEmpty()) {
      isFulfilled = false;
    }
    return isFulfilled;
  }

  /**
   * Method to reset the game back to the start of the story. All the stats and goals will not be
   * reset.
   */
  public void resetGame() {
    player.resetPlayer();
    linkToCurrentPassage = null;
  }

  /**
   * Method to check if the player is dead.
   * If the player is dead, the game is over.
   *
   * @return True if the player is dead, false if not.
   */
  public boolean checkIfDead() {
    boolean  isDead = false;
    if (player.getHealth() <= 0 && player.getStartHealth() > 1) {
      isDead = true;
    }
    return isDead;
  }

}
