package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controllers.GameController;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Class that creates the pause game view.
 */
public class PauseGameView {

  private StackPane root;
  private Button resumeButton;
  private Button saveButton;
  private Button mainMenuButton;

  /**
   * Constructor for the pause game view. Creates the layout.
   */
  public PauseGameView() {
    createLayout();
  }

  /**
   * Method to create the layout of the pause game view. This method is called from the
   * constructor.
   */
  private void createLayout() {
    root = new StackPane();
    SceneStyling.setBackgroundLayout(root);

    createBackground();
    createCenter();
  }

  /**
   * Method to create the background of the pause game view. This method is called from the
   * createLayout method.
   */
  private void createBackground() {
    Parent background = new GameController().createLayout();
    background.setEffect(new BoxBlur(10, 10, 3));
    root.getChildren().add(0, background);
  }

  /**
   * Method to create the center of the pause game view. This method is called from the createLayout
   * method.
   */
  private void createCenter() {
    VBox center = new VBox();
    center.getStyleClass().add("passage-content-container");
    center.setPadding(new Insets(20));

    resumeButton = new Button("Resume");
    saveButton = new Button("Save");
    mainMenuButton = new Button("Exit to Main Menu");

    Label pauseLabel = new Label("Game Paused");
    SceneStyling.bindFontSize(pauseLabel, 0.04, root);
    SceneStyling.bindFontSize(resumeButton, 0.02, root);
    SceneStyling.bindFontSize(saveButton, 0.02, root);
    SceneStyling.bindFontSize(mainMenuButton, 0.02, root);

    center.getChildren().addAll(pauseLabel, resumeButton, saveButton, mainMenuButton);
    center.setSpacing(20);
    center.setAlignment(Pos.CENTER);

    DoubleBinding width = root.widthProperty().multiply(0.5);
    DoubleBinding height = root.heightProperty().multiply(0.7);

    center.setMinWidth(width.get());
    center.setMinHeight(height.get());

    center.maxWidthProperty().bind(width);
    center.maxHeightProperty().bind(height);

    center.prefWidthProperty().bind(width);
    center.prefHeightProperty().bind(height);

    root.getChildren().add(center);
  }

  /**
   * Method to get the root of the pause game view.
   *
   * @return the root
   */
  public StackPane getRoot() {
    return root;
  }

  /**
   * Method that returns the resume button from the pause game view.
   *
   * @return the resume button
   */
  public Button getResumeButton() {
    return resumeButton;
  }

  /**
   * Method that returns the save button from the pause game view.
   *
   * @return the save button
   */
  public Button getSaveButton() {
    return saveButton;
  }

  /**
   * Method that returns the main menu button from the pause game view.
   *
   * @return the main menu button
   */
  public Button getMainMenuButton() {
    return mainMenuButton;
  }
}

