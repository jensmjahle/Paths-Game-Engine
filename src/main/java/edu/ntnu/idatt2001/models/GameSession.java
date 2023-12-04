package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.goals.Goal;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * A class to represent a game session.
 */
public class GameSession {

  /**
   * The instance of the game session.
   */
  private static GameSession instance;

  /**
   * The game.
   */
  private Game game;

  /**
   * The property of the game.
   */
  private final ObjectProperty<Game> gameProperty;

  /**
   * The goals of the game.
   */
  private final ObservableList<Goal> goals;

  /**
   * Constructor for the GameSession class.
   */
  private GameSession() {
    this.game = null;
    this.gameProperty = new SimpleObjectProperty<>();
    this.goals = FXCollections.observableArrayList();
  }

  /**
   * Method to get the instance of the game session.
   *
   * @return The instance of the game session.
   */
  public static GameSession getInstance() {
    if (instance == null) {
      instance = new GameSession();
    }
    return instance;
  }

  /**
   * Method to get the game.
   *
   * @return The game.
   */
  public Game getGame() {
    return this.game;
  }

  /**
   * Method to set the game.
   *
   * @param game The game.
   */
  public void setGame(Game game) {
    this.game = game;
    gameProperty.set(game);
    goals.clear();
    goals.addAll(game.getGoals());
  }

  /**
   * Method to get the property of the game.
   *
   * @return The property of the game.
   */
  public ObjectProperty<Game> getGameProperty() {
    return gameProperty;
  }

  /**
   * Method to get the goals of the game.
   *
   * @return The goals of the game.
   */
  public ObservableList<Goal> getGoals() {
    return goals;
  }

  /**
   * Method to clear the game session.
   */
  public void clear() {
    this.game = null;
    this.gameProperty.set(null);
    this.goals.clear();
  }
}
