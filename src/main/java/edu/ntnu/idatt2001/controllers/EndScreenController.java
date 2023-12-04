package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.view.EndScreenView;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Controller for the end screen view. This class is responsible for the controller of the end
 * screen view.
 */
public class EndScreenController implements SceneController {

  private final EndScreenView endScreenView;

  public EndScreenController() {
    endScreenView = new EndScreenView(this);
  }

  @Override
  public Parent createLayout() {
    return endScreenView.getRoot();
  }

  /**
   * Returns to the main menu scene.
   */
  public void returnToMenu() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU);
  }

  /**
   * Returns the victory text for the header label.
   *
   * @return the header label text
   */
  public String getHeaderLabel() {

    String ifVictory;
    if (GameSession.getInstance().getGameProperty().get().checkGoals()) {
      ifVictory = ("You won!");
    } else if (GameSession.getInstance().getGameProperty().get().getGoals().isEmpty()) {
      ifVictory = ("""
          You completed the game
                without any goals
          """);
    } else {
      ifVictory = "Goals not reached:";
    }
    return ifVictory;
  }

  /**
   * Navigates to the new game scene.
   * Shows a confirmation alert before navigating.
   */
  public void playAgain() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText("Are you sure you want to start a new game?");
    alert.setContentText("All progress will be lost");
    alert.showAndWait();

    if (alert.getResult() == ButtonType.OK) {
      PathsApplication.getSceneManager().navigateTo(SceneEnum.NEW_GAME);
    }
  }

  public void retry() {
    GameSession.getInstance().getGame().resetGame();
    PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
  }
}
