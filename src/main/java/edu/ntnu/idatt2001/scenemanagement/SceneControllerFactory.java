package edu.ntnu.idatt2001.scenemanagement;

import edu.ntnu.idatt2001.controllers.ContinueFromSaveController;
import edu.ntnu.idatt2001.controllers.DiedController;
import edu.ntnu.idatt2001.controllers.EditStoryController;
import edu.ntnu.idatt2001.controllers.EndScreenController;
import edu.ntnu.idatt2001.controllers.GameController;
import edu.ntnu.idatt2001.controllers.MainMenuController;
import edu.ntnu.idatt2001.controllers.NewGameController;
import edu.ntnu.idatt2001.controllers.PauseGameController;
import edu.ntnu.idatt2001.controllers.SceneController;
import edu.ntnu.idatt2001.controllers.SettingsController;

/**
 * A class to represent a scene controller factory. This class is used to create scene controllers.
 */
public class SceneControllerFactory {

  /**
   * Method to create all scene controllers.
   *
   * @param sceneEnum The scene enum.
   * @return The scene controller.
   */
  public static SceneController createSceneController(SceneEnum sceneEnum) {
    return switch (sceneEnum) {
      case MAIN_MENU -> new MainMenuController();
      case GAME -> new GameController();
      case NEW_GAME -> new NewGameController();
      case PAUSE_MENU -> new PauseGameController();
      case SETTINGS -> new SettingsController();
      case CONTINUE_GAME -> new ContinueFromSaveController();
      case EDIT_STORY -> new EditStoryController();
      case END_GAME -> new EndScreenController();
      case DIED -> new DiedController();
      default -> throw new IllegalArgumentException("Unsupported scene: " + sceneEnum);
    };
  }
}
