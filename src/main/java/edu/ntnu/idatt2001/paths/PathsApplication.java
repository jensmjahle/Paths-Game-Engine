package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.models.InitiateStories;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneManager;
import java.util.Objects;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A class to represent the actual application.
 */
public class PathsApplication extends Application {

  /**
   * The scene manager.
   */
  private static SceneManager sceneManager;

  /**
   * The start method to start showing the GUI.
   *
   * @param primaryStage The primary stage.
   */
  @Override
  public void start(Stage primaryStage) {

    sceneManager = new SceneManager(primaryStage);

    primaryStage.setTitle("Paths");
    primaryStage.getIcons().add(new Image(
        Objects.requireNonNull(getClass().getResourceAsStream("/images/backpackIcon.png"))));

    primaryStage.show();



    //Navigate to the main menu
    sceneManager.navigateTo(SceneEnum.MAIN_MENU);
    sceneManager.setWindowSize(0.8);
    sceneManager.setStyling();

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Welcome to Paths");
    alert.setHeaderText("Recommended!");
    alert.setContentText("""
            Do you want to create example story files now?
            If you choose no, you can find them later in the edit story menu
            through the file explorer or choose your own files.""");
    alert.showAndWait();

    if (alert.getResult().getText().equals("OK")) {
      InitiateStories.createStoriesInFolder();
    }

  }

  /**
   * The main method.
   *
   * @param args The arguments.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Method to get the scene manager.
   *
   * @return The scene manager.
   */
  public static SceneManager getSceneManager() {
    return sceneManager;
  }

}
