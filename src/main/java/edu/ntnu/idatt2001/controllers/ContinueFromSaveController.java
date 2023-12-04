package edu.ntnu.idatt2001.controllers;

import edu.ntnu.idatt2001.filehandling.SaveState;
import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import edu.ntnu.idatt2001.view.ContinueFromSaveView;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Controller for the continueFromSave scene.
 */
public class ContinueFromSaveController implements SceneController {

  private ContinueFromSaveView view;

  @Override
  public Parent createLayout() {
    view = new ContinueFromSaveView();
    Button continueButton = view.getContinueButton();
    Button deleteButton = view.getDeleteButton();
    Button returnToMainMenu = view.getReturnToMainMenu();

    continueButton.setOnAction(e -> onContinueButtonClicked());
    deleteButton.setOnAction(e -> onDeleteButtonClicked());
    returnToMainMenu.setOnAction(e ->
        PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU));

    return view.getRoot();
  }

  /**
   * Called when the user clicks the "Back" button. Selects the chosen game and navigates to the
   * game scene.
   */
  public void onContinueButtonClicked() {
    Game game = view.getSelectedGame();

    if (game == null) {
      SceneStyling.showNotification("No save selected",
          "Please select a save to continue", (Pane) view.getRoot());
      return;
    }
    GameSession.getInstance().setGame(game);
    PathsApplication.getSceneManager().navigateTo(SceneEnum.GAME);
  }

  /**
   * Called when the user clicks the "Delete" button. Deletes the chosen game and updates the view.
   */
  public void onDeleteButtonClicked() {
    Game game = view.getSelectedGame();

    if (game == null) {
      SceneStyling.showAlert(AlertType.ERROR, "No save selected", "Please select a save to delete");
      return;
    }

    SaveState.deleteGame(game);
    view.removeGame(game);
    SceneStyling.showNotification("Save deleted", "Save deleted successfully",
        (Pane) view.getRoot());
  }
}

