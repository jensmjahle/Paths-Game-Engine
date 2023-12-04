package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * View class for the died scene.
 */
public class DiedView {

  private BorderPane root;
  private Button returnMenuButton;
  private Button playAgainButton;
  private Button retryButton;

  public DiedView() {
    createLayout();
  }

  private void createLayout() {
    root = new BorderPane();
    SceneStyling.setBackgroundLayout(root);
    createHeader();
    createCenter();
  }

  private void createHeader() {
    HBox headerContainer = new HBox();
    Label headerLabel = new Label("You died");
    headerLabel.setId("headerLabel");
    SceneStyling.bindFontSize(headerLabel, 0.04, root);
    headerContainer.getChildren().add(headerLabel);
    headerContainer.setAlignment(Pos.CENTER);
    root.setTop(headerContainer);
  }

  private void createCenter() {
    Label diedLabel = new Label();
    String diedText = (
        """
        You died during your adventures.
        You can either return to the main menu, play a new game or retry the same game.
        If you want, you can also start a story without any start-stats
        or goals just to explore the story without any pressure.""");
    diedLabel.setText(diedText);
    diedLabel.setId("diedLabel");
    SceneStyling.bindFontSize(diedLabel, 0.015, root);

    returnMenuButton = new Button("Return to Main Menu");
    playAgainButton = new Button("Create New Game");
    retryButton = new Button("Retry Game");

    SceneStyling.bindFontSize(returnMenuButton, 0.02, root);
    SceneStyling.bindFontSize(playAgainButton, 0.02, root);
    SceneStyling.bindFontSize(retryButton, 0.02, root);
    VBox mainDiedContainer = new VBox();
    mainDiedContainer.setSpacing(40);
    mainDiedContainer.setPadding(new Insets(20, 20, 20, 20));
    mainDiedContainer.setAlignment(Pos.TOP_CENTER);
    mainDiedContainer.prefWidthProperty().bind(root.widthProperty());

    VBox secondaryDiedContainer = new VBox();
    secondaryDiedContainer.setSpacing(40);
    secondaryDiedContainer.setPadding(new Insets(20, 20, 20, 20));
    secondaryDiedContainer.setAlignment(Pos.TOP_CENTER);
    secondaryDiedContainer.getChildren().addAll(returnMenuButton, playAgainButton, retryButton);

    mainDiedContainer.getChildren().addAll(diedLabel, secondaryDiedContainer);

    root.setLeft(mainDiedContainer);
  }

  public Parent getRoot() {
    return root;
  }

  public Button getReturnMenuButton() {
    return returnMenuButton;
  }

  public Button getPlayAgainButton() {
    return playAgainButton;
  }

  public Button getRetryButton() {
    return retryButton;
  }
}

