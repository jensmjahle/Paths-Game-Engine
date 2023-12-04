package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controllers.GameController;
import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

/**
 * Class for the game view. This class is responsible for the view that is shown when the user is
 * playing the game.
 */
public class GameView {

  private final GameController controller;
  private final BorderPane root;
  private ListView<String> inventoryList;

  /**
   * Constructor for the game view.
   *
   * @param controller the controller
   */
  public GameView(GameController controller) {
    this.controller = controller;
    root = new BorderPane();
    updateLayout();
  }

  /**
   * Method to get the root of the game view.
   *
   * @return the inventory container
   */
  public Parent getRoot() {
    return root;
  }

  /**
   * Method responsible for creating everything in the scene. This method is called from the
   * constructor.
   */
  public void updateLayout() {
    SceneStyling.setBackgroundLayout(root);
    if (GameSession.getInstance().getGame() != null) {
      createBottom();
      createCenter();
      createHeader();
    }
    createLeftSide();
    createRightSide();
  }

  /**
   * Method to create the header of the game view. This method is called from the updateLayout
   * method.
   */
  private void createHeader() {

    Label passageTitle = new Label();
    Link linkToCurrentPassage = GameSession.getInstance().getGame().getLinkToCurrentPassage();
    String passageTitleText;
    if (linkToCurrentPassage == null) {
      passageTitleText = GameSession.getInstance().getGame().getStory().getOpeningPassage()
          .getTitle();
    } else {
      passageTitleText = GameSession.getInstance().getGame().getStory()
          .getPassage(linkToCurrentPassage).getTitle();
    }
    passageTitle.setText(passageTitleText);
    SceneStyling.bindFontSize(passageTitle, 0.02, root);

    Label playerStats = new Label();
    String playerName = GameSession.getInstance().getGame().getPlayer().getName();
    int playerHealth = GameSession.getInstance().getGame().getPlayer().getHealth();
    int playerScore = GameSession.getInstance().getGame().getPlayer().getScore();
    int playerGold = GameSession.getInstance().getGame().getPlayer().getGold();
    String playerStatsText = (playerName + " | Health: " + playerHealth + " | Score: " + playerScore
        + " | Gold: " + playerGold);
    playerStats.setText(playerStatsText);
    SceneStyling.bindFontSize(playerStats, 0.02, root);

    VBox header = new VBox();
    header.getChildren().addAll(playerStats, passageTitle);
    header.setAlignment(Pos.CENTER);

    root.setTop(header);
  }

  /**
   * Method to create the center of the game view. Center will house most of the information about
   * the game. This method is called from the updateLayout method.
   */
  private void createCenter() {

    VBox passageContentContainer = new VBox();

    passageContentContainer.setAlignment(Pos.CENTER);
    passageContentContainer.setSpacing(50);
    passageContentContainer.getStyleClass().add("passage-content-container");

    Text passageContent = new Text();
    String passageContentText;
    if (GameSession.getInstance().getGame().getLinkToCurrentPassage() == null) {
      passageContentText = GameSession.getInstance().getGame().getStory().getOpeningPassage()
          .getContent();
    } else {
      passageContentText = GameSession.getInstance().getGame().getStory()
          .getPassage(GameSession.getInstance().getGame().getLinkToCurrentPassage()).getContent();
    }
    passageContent.setText(passageContentText);
    passageContent.setTextAlignment(TextAlignment.CENTER);
    passageContent.getStyleClass().add("passage-content");

    passageContent.wrappingWidthProperty()
        .bind(passageContentContainer.widthProperty().subtract(40));
    SceneStyling.bindFontSize(passageContent, 0.015, root);
    passageContentContainer.getChildren().add(passageContent);

    VBox progressContainer = createProgressContainer();
    VBox inventoryContainer = createInventoryContainer();
    HBox mainCenterContainer = new HBox();
    mainCenterContainer.getChildren()
        .addAll(progressContainer, passageContentContainer, inventoryContainer);
    mainCenterContainer.setSpacing(20);
    mainCenterContainer.setAlignment(Pos.CENTER);
    mainCenterContainer.setPadding(new Insets(20, 20, 20, 20));

    passageContentContainer.prefWidthProperty()
        .bind(mainCenterContainer.widthProperty().divide(6).multiply(3));
    passageContentContainer.prefHeightProperty().bind(mainCenterContainer.heightProperty());

    inventoryContainer.prefWidthProperty().bind(mainCenterContainer.widthProperty().divide(6));
    inventoryContainer.prefHeightProperty().bind(mainCenterContainer.heightProperty());

    progressContainer.prefWidthProperty().bind(mainCenterContainer.widthProperty().divide(6));
    progressContainer.prefHeightProperty().bind(mainCenterContainer.heightProperty());

    mainCenterContainer.prefWidthProperty().bind(root.widthProperty().divide(7).multiply(6));
    mainCenterContainer.prefHeightProperty().bind(root.heightProperty().divide(2));

    root.setCenter(mainCenterContainer);
  }

  /**
   * Method to create the right side of the game view. This method is called from the updateLayout
   * method.
   */
  private void createRightSide() {
    VBox rightSide = new VBox();

    rightSide.prefWidthProperty().bind(root.widthProperty().divide(7));

    root.setRight(rightSide);
  }

  /**
   * Method to create the inventory container. This method is called from the createCenter method.
   *
   * @return the inventory container
   */
  private VBox createInventoryContainer() {
    Label inventoryLabel = new Label("Inventory");
    SceneStyling.bindFontSize(inventoryLabel, 0.01, root);

    ListView<String> inventoryList = new ListView<>();
    inventoryList.getItems().addAll(GameSession.getInstance().getGame().getPlayer().getInventory());
    inventoryList.setPlaceholder(new Label("Inventory is empty"));

    VBox inventoryContainer = new VBox();
    inventoryContainer.getChildren().addAll(inventoryLabel, inventoryList);
    inventoryContainer.setAlignment(Pos.CENTER);
    inventoryContainer.getStyleClass().add("passage-content-container");

    return inventoryContainer;

  }

  /**
   * Method to create the progress container. This method is called from the createCenter method.
   *
   * @return the progress container
   */
  private VBox createProgressContainer() {
    VBox progressContainer = new VBox();

    Label inventoryLabel = new Label("Progress");
    SceneStyling.bindFontSize(inventoryLabel, 0.013, root);

    progressContainer.getChildren().add(inventoryLabel);

    HashMap<String, Double> progressList = controller.listOfProgress();
    if (progressList.isEmpty()) {
      Label noProgress = new Label("No Goals Selected");
      SceneStyling.bindFontSize(noProgress, 0.006, root);
      progressContainer.getChildren().add(noProgress);
    }
    progressList.forEach((key, value) -> {
      Label label = new Label(key);
      SceneStyling.bindFontSize(label, 0.01, root);

      ProgressBar progressBar = new ProgressBar();
      progressBar.setProgress(value);
      Label toolTipLabel = new Label(Math.round(value) * 100 + "% Of " + key + " Goal Completed");
      SceneStyling.bindFontSize(toolTipLabel, 0.01, root);
      Tooltip tooltip = new Tooltip();
      tooltip.setGraphic(toolTipLabel);
      tooltip.setShowDelay(Duration.ZERO);
      tooltip.setShowDuration(Duration.INDEFINITE);
      tooltip.setHideDelay(Duration.ZERO);
      progressBar.setTooltip(tooltip);
      progressBar.prefWidthProperty().bind(progressContainer.widthProperty().multiply(0.9));

      if (value >= 1) {
        progressBar.getStyleClass().add("custom-progress-bar");
      }

      progressContainer.getChildren().addAll(label, progressBar);
    });

    progressContainer.setAlignment(Pos.TOP_CENTER);
    progressContainer.getStyleClass().add("passage-content-container");

    return progressContainer;
  }

  /**
   * Method to create the left side of the game view. This method is called from the updateLayout
   * method.
   */
  private void createLeftSide() {
    VBox leftSide = new VBox();
    leftSide.setPadding(new Insets(20, 0, 0, 20));
    leftSide.setAlignment(Pos.TOP_LEFT);

    Button pauseGameButton = new Button("Pause Game");
    SceneStyling.bindFontSize(pauseGameButton, 0.008, root);
    pauseGameButton.setOnAction(e -> controller.navigateToPauseMenu());

    leftSide.getChildren().add(pauseGameButton);
    leftSide.prefWidthProperty().bind(root.widthProperty().divide(7));
    root.setLeft(leftSide);
  }

  /**
   * Method to create the bottom of the game view. This method is called from the updateLayout
   * method.
   */
  private void createBottom() {
    GridPane bottom = new GridPane();
    bottom.setAlignment(Pos.CENTER);
    bottom.setHgap(10);
    bottom.setVgap(10);

    HBox topRow = new HBox();
    HBox bottomRow = new HBox();
    topRow.setAlignment(Pos.CENTER);
    bottomRow.setAlignment(Pos.CENTER);
    topRow.setSpacing(10);
    bottomRow.setSpacing(10);

    List<Link> choicesLinks = getChoicesLinks();

    for (int i = 0; i < choicesLinks.size(); i++) {
      Link link = choicesLinks.get(i);
      Button choiceButton = new Button(link.getText());
      SceneStyling.bindFontSize(choiceButton, 0.012, root);
      choiceButton.setOnAction(e -> controller.makeChoice(link));

      if (i % 2 == 0) {
        topRow.getChildren().add(choiceButton);
      } else {
        bottomRow.getChildren().add(choiceButton);
      }
    }

    Button endGameButton = new Button("End Game");
    SceneStyling.bindFontSize(endGameButton, 0.012, root);
    endGameButton.setOnAction(e -> controller.navigateToEndGame());

    if (choicesLinks.size() < 1) {
      topRow.getChildren().add(endGameButton);
    }

    VBox buttonLayout = new VBox(topRow, bottomRow);
    buttonLayout.setAlignment(Pos.CENTER);
    buttonLayout.setSpacing(20);
    buttonLayout.setPadding(new Insets(20, 20, 20, 20));

    bottom.add(buttonLayout, 0, 0);
    root.setBottom(bottom);
  }

  /**
   * Method to get the choices links. This method is called from the createBottom method.
   *
   * @return the links you are able to choose from
   */
  private List<Link> getChoicesLinks() {
    Game game = controller.getGame();
    if (game == null) {
      return Collections.emptyList();
    }

    if (game.getLinkToCurrentPassage() == null) {
      return game.getStory().getOpeningPassage().getLinks();
    } else {
      return game.getStory().getPassage(game.getLinkToCurrentPassage()).getLinks();
    }
  }
}

