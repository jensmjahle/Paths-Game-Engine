package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.view.MainMenuView;
import javafx.scene.Parent;

/**
 * A class to represent the main menu controller.
 */
public class MainMenuController implements SceneController {

  private final MainMenuView mainMenuView;

  /**
   * The constructor for the main menu controller.
   */
  public MainMenuController() {
    this.mainMenuView = new MainMenuView();

    mainMenuView.getContinueFromSave().setOnAction(e -> handleContinueFromSaveButtonClick());
    mainMenuView.getNewGame().setOnAction(e -> handleNewGameButtonClick());
    mainMenuView.getEditStory().setOnAction(e -> handleEditStoryButtonClick());
    mainMenuView.getSettings().setOnAction(e -> handleSettingsButtonClick());
    if (mainMenuView.getContinueGame() != null) {
      mainMenuView.getContinueGame().setOnAction(e -> handleContinueGameButtonClick());
    }
  }

  /**
   * Method to handle the continue from save button click.
   */
  private void handleContinueFromSaveButtonClick() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.CONTINUE_GAME);
  }

  /**
   * Method to handle the new game button click.
   */
  private void handleNewGameButtonClick() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.NEW_GAME);
  }

  /**
   * Method to handle the edit story button click.
   */
  private void handleEditStoryButtonClick() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.EDIT_STORY);
  }

  /**
   * Method to handle the settings button click.
   */
  private void handleSettingsButtonClick() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.SETTINGS);
  }

  /**
   * Method to handle the continue game button click.
   */
  private void handleContinueGameButtonClick() {
    PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
  }

  /**
   * Method to create the layout.
   *
   * @return The layout.
   */
  @Override
  public Parent createLayout() {
    return mainMenuView.getRoot();
  }
}

