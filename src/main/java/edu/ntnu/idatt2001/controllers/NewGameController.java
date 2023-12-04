package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.filehandling.StoryFileHandler;
import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.models.goals.Goal;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import edu.ntnu.idatt2001.models.goals.HealthGoal;
import edu.ntnu.idatt2001.models.goals.InventoryGoal;
import edu.ntnu.idatt2001.models.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.view.NewGameView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Parent;

/**
 * A class to represent a new game controller.
 */
public class NewGameController implements SceneController {

  private NewGameView view;

  /**
   * Creates the layout for the new game menu.
   *
   * @return The root.
   */
  @Override
  public Parent createLayout() {
    view = new NewGameView(this);
    return view.createLayout();
  }

  /**
   * Starting a new game with the given parameters.
   * It includes optional parameters and goals.
   * However, the players needs a name and a selected story to start the game.
   */
  public void startGame() {
    try {
      view.validateUserInputs();
      // Get player details from the view
      String playerName = view.getPlayerName();

      // Get story details from the view
      File selectedStory = view.getSelectedStory();

      // Get other player details from the view
      int playerHealth = view.getPlayerHealth();
      int playerScore = view.getPlayerScore();
      int playerGold = view.getPlayerGold();
      List<String> playerInventory = view.getInventory();

      // Get goals details from the view
      int healthGoal = view.getHealthGoal();

      // Create player
      Player.Builder playerBuilder = new Player.Builder(playerName)
          .health(playerHealth)
          .score(playerScore)
          .gold(playerGold);
      for (String item : playerInventory) {
        playerBuilder.addItemToInventory(item);
      }

      // Load story from file
      Story story;
      try {
        story = StoryFileHandler.loadStoryFromFile(selectedStory);
      } catch (IOException ex) {
        throw new RuntimeException("Could not load story from file");
      }

      List<Goal> goals = new ArrayList<>();
      if (healthGoal != 0) {
        goals.add(new HealthGoal(healthGoal));
      }
      int scoreGoal = view.getScoreGoal();
      if (scoreGoal != 0) {
        goals.add(new ScoreGoal(scoreGoal));
      }
      int goldGoal = view.getGoldGoal();
      if (goldGoal != 0) {
        goals.add(new GoldGoal(goldGoal));
      }
      List<String> inventoryGoal = view.getInventoryGoal();
      if (!inventoryGoal.isEmpty()) {
        goals.add(new InventoryGoal(inventoryGoal));
      }

      // Create game and set it in GameSession
      Player player = playerBuilder.build();
      Game game = new Game(player, story, goals);
      GameSession.getInstance().setGame(game);

      // Navigate to the game scene
      PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
    } catch (Exception e) {
      view.showAlert(e.getMessage());
    }
  }

  public void clearFields() {
    view.clearFields();
  }
}
