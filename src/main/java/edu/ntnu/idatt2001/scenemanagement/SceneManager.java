package edu.ntnu.idatt2001.scenemanagement;

import edu.ntnu.idatt2001.controllers.SceneController;
import edu.ntnu.idatt2001.models.ApplicationSession;
import java.util.Objects;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * A class to represent the scene manager. This class is used to manage the scenes and navigate
 * between them.
 */
public class SceneManager {

  public final Stage stage;
  private Scene scene;

  /**
   * Constructor for the scene manager.
   *
   * @param stage The stage.
   */
  public SceneManager(Stage stage) {
    this.stage = stage;
  }

  /**
   * Method to navigate to a scene. This method creates a new scene if there is no scene, and sets
   * the root of the scene to the layout of the scene controller.
   *
   * @param sceneEnum The scene enum.
   */
  public void navigateTo(SceneEnum sceneEnum) {

    SceneController controller = SceneControllerFactory.createSceneController(sceneEnum);
    Parent layout = controller.createLayout();

    if (scene == null) {
      scene = new Scene(layout);
      stage.setScene(scene);
    } else {
      scene.setRoot(layout);
    }
    setStyling();
  }

  /**
   * Method to set the window size.
   *
   * @param scaleFactor The scale factor.
   */
  public void setWindowSize(double scaleFactor) {

    Screen screen = Screen.getPrimary();
    Rectangle2D bounds = screen.getVisualBounds();

    double screenWidth = bounds.getWidth();
    double screenHeight = bounds.getHeight();
    double targetWidth = screenWidth * scaleFactor;
    double targetHeight = screenHeight * scaleFactor;

    stage.setMinWidth(targetWidth * 0.8);
    stage.setMinHeight(targetHeight * 0.8);

    stage.setWidth(targetWidth);
    stage.setHeight(targetHeight);
  }

  /**
   * Method to set the styling. This method adds the css file to the scene.
   */
  public void setStyling() {
    String cssFile;
    boolean isHighContrast = ApplicationSession.getInstance().isHighContrastMode();
    if (isHighContrast) {
      cssFile = Objects.requireNonNull(getClass().getResource("/css/highContrastStyle.css"))
          .toExternalForm();
    } else {
      cssFile = Objects.requireNonNull(getClass().getResource("/css/styles.css"))
          .toExternalForm();
    }
    stage.getScene().getStylesheets().clear();
    stage.getScene().getStylesheets().add(cssFile);
  }

  /**
   * Method to get the scene.
   *
   * @return The scene.
   */
  public Scene getScene() {
    return scene;
  }

}
