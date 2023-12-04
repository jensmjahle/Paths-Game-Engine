package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.view.DiedView;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * Controller for the died view.
 * This class is responsible for the controller of the died view.
 */
public class DiedController implements SceneController {

  private final DiedView diedView;

  public DiedController() {
    this.diedView = new DiedView();
    initializeButtons();
  }

  private void initializeButtons() {
    diedView.getReturnMenuButton().setOnAction(
        e -> PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU)
    );

    diedView.getPlayAgainButton().setOnAction(e -> {
      Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
      confirmation.setTitle("Confirmation");
      confirmation.setHeaderText("Are you sure you want to start a new game?");
      confirmation.setContentText("All progress will be lost");
      confirmation.showAndWait();

      if (confirmation.getResult() == ButtonType.OK) {
        PathsApplication.getSceneManager().navigateTo(SceneEnum.NEW_GAME);
      }
    });

    diedView.getRetryButton().setOnAction(e -> {
      GameSession.getInstance().getGame().resetGame();
      PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
    });
  }

  @Override
  public Parent createLayout() {
    return diedView.getRoot();
  }
}
