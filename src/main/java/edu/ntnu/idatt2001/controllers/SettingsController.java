package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.models.ApplicationSession;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.view.SettingsView;
import javafx.scene.Parent;

/**
 * A class to represent the settings controller.
 */
public class SettingsController implements SceneController {

  private final SettingsView view;

  /**
   * The constructor for the settings controller.
   */
  public SettingsController() {
    this.view = new SettingsView(this);

    view.getBackToMainMenuButton().setOnAction(e -> navigateToMainMenu());
  }

  /**
   * Creates the layout for the settings menu.
   *
   * @return The root.
   */
  @Override
  public Parent createLayout() {
    return view.getRoot();
  }

  /**
   * Method to handle the high contrast mode button click.
   * ApplicationSession class will track if this is true or false.
   *
   * @param isHighContrastModeOn The boolean value for high contrast mode.
   */
  public void toggleHighContrastMode(boolean isHighContrastModeOn) {
    ApplicationSession.getInstance().setHighContrastMode(isHighContrastModeOn);
    PathsApplication.getSceneManager().setStyling();
  }

  /**
   * Method to handle the back to main menu button click.
   */

  public void navigateToMainMenu() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU);
  }
}
