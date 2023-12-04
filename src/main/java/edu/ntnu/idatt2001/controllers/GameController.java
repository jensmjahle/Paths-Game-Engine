package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import edu.ntnu.idatt2001.models.goals.HealthGoal;
import edu.ntnu.idatt2001.models.goals.InventoryGoal;
import edu.ntnu.idatt2001.models.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import edu.ntnu.idatt2001.view.GameView;
import java.util.HashMap;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;

/**
 * Controller for the game scene.
 */
public class GameController implements SceneController {

  private Game game;
  private GameView gameView;

  /**
   * Creates a new instance of the controller.
   */
  public GameController() {
    game = GameSession.getInstance().getGame();

    ObjectProperty<Game> gameProperty = GameSession.getInstance().getGameProperty();
    gameProperty.addListener((observable, oldValue, newValue) -> {
      game = newValue;
      gameView.updateLayout();
    });
  }


  @Override
  public Parent createLayout() {
    gameView = new GameView(this);
    return gameView.getRoot();
  }

  public Game getGame() {
    return game;
  }

  public void navigateToPauseMenu() {
    GameSession.getInstance().setGame(game);
    PathsApplication.getSceneManager().navigateTo(SceneEnum.PAUSE_MENU);
  }

  /**
   * Navigates to the end game scene. Updates the game session with the current game.
   */
  public void navigateToEndGame() {
    GameSession.getInstance().setGame(game);
    PathsApplication.getSceneManager().navigateTo(SceneEnum.END_GAME);
  }

  /**
   * Creates a progress bar based on the progress the player
   * has made on the goals they sat at the start.
   *
   * @return true if the game is over, false otherwise
   */
  public HashMap<String, Double> listOfProgress() {
    HashMap<String, Double> progress = new HashMap<>();
    game.getGoals().forEach(goal -> {
      if (goal instanceof HealthGoal) {
        int startHealth = game.getPlayer().getStartHealth();
        int currentHealth = game.getPlayer().getHealth();
        int healthGoal = ((HealthGoal) goal).getMinimumHealth();
        progress.put("Health", (double) (currentHealth - startHealth) / (healthGoal - startHealth));
      } else if (goal instanceof GoldGoal) {
        int startGold = game.getPlayer().getStartGold();
        int currentGold = game.getPlayer().getGold();
        int goldGoal = ((GoldGoal) goal).getGold();
        progress.put("Gold", (double) (currentGold - startGold) / (goldGoal - startGold));
      } else if (goal instanceof ScoreGoal) {
        int startScore = game.getPlayer().getStartScore();
        int currentScore = game.getPlayer().getScore();
        int scoreGoal = ((ScoreGoal) goal).getScore();
        progress.put("Score", (double) (currentScore - startScore) / (scoreGoal - startScore));
      } else if (goal instanceof InventoryGoal) {
        int startInventory = game.getPlayer().getStartInventory().size();
        int currentInventory = game.getPlayer().getInventory().size();
        int inventoryGoal = ((InventoryGoal) goal).getMandatoryItems().size();
        progress.put("Inventory",
            (double) (currentInventory - startInventory) / (inventoryGoal - startInventory));
      }
    });
    return progress;
  }

  /**
   * Handles the choice of the player. If the link is broken, an error message is shown. If the
   * player is dead, the player is navigated to the died scene. If the player has reached the goal,
   * the player is navigated to the end game scene. Otherwise, the player is navigated to the next
   * passage. Updates the game session with the current game.
   *
   * @param link the link the player has chosen.
   */
  public void makeChoice(Link link) {
    if (game.getStory().getBrokenLinks().contains(link)) {
      SceneStyling.showAlert(AlertType.ERROR, "This link is broken",
          """
              OPS! YOU CAN NOT GO THIS WAY!
              This button is not linked to a passage and is therefore broken.
              You should remove this link from the story or try to go another way.""");
    } else {
      link.getActions().forEach(action -> action.execute(game.getPlayer()));
      if (game.checkGoals()) {
        GameSession.getInstance().setGame(game);
        PathsApplication.getSceneManager().navigateTo(SceneEnum.END_GAME);
      } else if (game.checkIfDead()) {
        GameSession.getInstance().setGame(game);
        PathsApplication.getSceneManager().navigateTo(SceneEnum.DIED);
      } else {
        game.go(link);
        GameSession.getInstance().setGame(game);
        gameView.updateLayout();
      }
    }
  }
}
