package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controllers.SettingsController;
import edu.ntnu.idatt2001.models.ApplicationSession;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for the settings view. This class is responsible for the view that is shown when the user
 * wants to change the settings.
 */
public class SettingsView {

  private BorderPane root;
  private final SettingsController controller;
  private final Button backToMainMenuButton = new Button("Main Menu");

  /**
   * Constructor for the settings view.
   */
  public SettingsView(SettingsController controller) {
    this.controller = controller;
    createLayout();
  }

  /**
   * Method to create the layout of the settings view.
   */
  public void createLayout() {
    root = new BorderPane();

    SceneStyling.setBackgroundLayout(root);

    SceneStyling.bindFontSize(backToMainMenuButton, 0.01, root);

    createCenter();
    createLeftSide();
    createRightSide();
    createHeader();
  }

  /**
   * Method to create the header of the settings view.
   * This is where the headline is located.
   */
  private void createHeader() {
    HBox header = new HBox();

    Label headline = new Label("Settings");
    SceneStyling.bindFontSize(headline, 0.06, root);

    header.getChildren().add(headline);
    header.setAlignment(Pos.CENTER);

    root.setTop(header);
  }

  /**
   * Method to create the center of the settings view.
   * This is where the high contrast mode toggle is located.
   */
  private void createCenter() {

    CheckBox highContrastModeToggle = new CheckBox("High Contrast Mode");
    highContrastModeToggle.setSelected(ApplicationSession.getInstance().isHighContrastMode());
    SceneStyling.bindFontSize(highContrastModeToggle, 0.03, root);

    highContrastModeToggle.selectedProperty().addListener((observable, oldValue, newValue) ->
        controller.toggleHighContrastMode(newValue));

    HBox center = new HBox();
    center.setAlignment(Pos.CENTER);
    center.getChildren()
        .addAll(highContrastModeToggle);

    root.setCenter(center);
  }

  /**
   * Method to create the left side of the settings view.
   * This is where the back to main menu button is located.
   */
  private void createLeftSide() {
    VBox leftSide = new VBox();

    leftSide.getChildren().add(backToMainMenuButton);
    leftSide.setPadding(
        new Insets(20, 0, 0, 20)); //set padding on the left side. top, right, bottom, left
    leftSide.setAlignment(Pos.TOP_LEFT);

    leftSide.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    root.setLeft(leftSide);
  }

  /**
   * Method to create the right side of the settings view.
   * This is where the trademark is located.
   */
  private void createRightSide() {
    VBox rightSide = SceneStyling.createTradeMarkContainer(root);
    rightSide.prefWidthProperty().bind(root.widthProperty().multiply(0.2));
    root.setRight(rightSide);
  }

  /**
   * Method to get the back to main menu button.
   *
   * @return The back to main menu button.
   */
  public Button getBackToMainMenuButton() {
    return backToMainMenuButton;
  }

  /**
   * Method to get the root of the settings view.
   *
   * @return The root of the settings view.
   */
  public Parent getRoot() {
    return root;
  }
}
