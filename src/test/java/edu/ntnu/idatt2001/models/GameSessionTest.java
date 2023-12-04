package edu.ntnu.idatt2001.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.models.goals.Goal;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import edu.ntnu.idatt2001.models.goals.HealthGoal;
import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.Story;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GameSessionTest {

  private GameSession gameSession;

  @BeforeEach
  public void setup() {
    gameSession = GameSession.getInstance();
  }
  @AfterEach
  public void tearDown() {
    gameSession.clear();
    System.out.println(gameSession.getGoals());
  }

  @Test
  public void testGetGameInitiallyReturnsNull() {
    // Arrange

    // Act
    Game game = gameSession.getGame();

    // Assert
    assertNull(game);
  }

  @Test
  public void testSetGameUpdatesGameAndGoals() {
    // Arrange
    Player player = new Player.Builder("Player 1").build();
    Story story = new Story("Story 1", new Passage("Passage 1", "Text 1"));
    HealthGoal goal1 = new HealthGoal(50);
    GoldGoal goal2 = new GoldGoal(100);
    List<Goal> goals = new ArrayList<>();
    goals.add(goal1);
    goals.add(goal2);
    Game game = new Game(player, story, goals);

    // Act
    gameSession.setGame(game);
    Game actualGame = gameSession.getGame();
    ObservableList<Goal> actualGoals = gameSession.getGoals();

    // Assert
    assertEquals(goals, new ArrayList<>(actualGoals));
  }

  @Test
  public void testGetGamePropertyReturnsCorrectValue() {
    // Arrange
    Player player = new Player.Builder("Player 1").build();
    Story story = new Story("Story 1", new Passage("Passage 1", "Text 1"));
    HealthGoal goal1 = new HealthGoal(50);
    GoldGoal goal2 = new GoldGoal(100);
    List<Goal> goals = new ArrayList<>();
    goals.add(goal1);
    goals.add(goal2);
    Game game = new Game(player, story, goals);
    gameSession.setGame(game);

    // Act
    ObjectProperty<Game> gameProperty = gameSession.getGameProperty();
    Game actualGame = gameProperty.get();

    // Assert
    assertEquals(game, actualGame);
  }

  @Test
  public void testGetGoalsReturnsCorrectList() {
    // Arrange
    Player player = new Player.Builder("Player 1").build();
    Story story = new Story("Story 1", new Passage("Passage 1", "Text 1"));
    HealthGoal goal1 = new HealthGoal(50);
    GoldGoal goal2 = new GoldGoal(100);
    List<Goal> goals = new ArrayList<>();
    goals.add(goal1);
    goals.add(goal2);
    Game game = new Game(player, story, goals);
    gameSession.setGame(game);

    // Act
    ObservableList<Goal> actualGoals = gameSession.getGoals();

    // Assert
    assertEquals(goals, new ArrayList<>(actualGoals));
  }

  @Test
  public void testGetGoalsReturnsEmptyListWhenNoGameSet() {
    // Arrange

    // Act
    ObservableList<Goal> goals = gameSession.getGoals();
    System.out.println(goals);
    // Assert
    assertTrue(goals.isEmpty());
  }
}
