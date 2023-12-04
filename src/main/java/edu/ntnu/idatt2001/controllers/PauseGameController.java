package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.filehandling.SaveState;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import edu.ntnu.idatt2001.view.PauseGameView;
import javafx.scene.Parent;

/**
 * A class to represent the pause game controller.
 */
public class PauseGameController implements SceneController {

  private PauseGameView view;

  /**
   * Creates the layout for the pause menu when you are in a game.
   *
   * @return The root.
   */
  @Override
  public Parent createLayout() {
    view = new PauseGameView();

    view.getResumeButton().setOnAction(e -> {
      PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
    });

    view.getSaveButton().setOnAction(e -> {
      try {
        SaveState.saveGame(GameSession.getInstance().getGame());
        SceneStyling.showNotification("Game saved", "Game saved successfully", view.getRoot());
      } catch (Exception exception) {
        exception.printStackTrace();
      }

    });

    view.getMainMenuButton().setOnAction(e -> {
      PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU);
    });

    return view.getRoot();
  }
}
