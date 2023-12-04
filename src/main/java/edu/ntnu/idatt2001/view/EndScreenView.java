package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controllers.EndScreenController;
import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.models.goals.Goal;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Class for the end screen view. This class is responsible for the view that is shown when the user
 * has finished the game.
 */
public class EndScreenView {

  private BorderPane root;
  private final EndScreenController controller;

  /**
   * Constructor for the end screen view.
   *
   * @param controller the controller
   */
  public EndScreenView(EndScreenController controller) {
    this.controller = controller;
    initialize();
  }

  /**
   * Method to initialize the view with all the included parts. This method is called from the
   * constructor.
   */
  public void initialize() {
    root = new BorderPane();
    SceneStyling.setBackgroundLayout(root);

    createHeader();
    createLeftSection();
    createRightSection();
  }

  /**
   * Method to create the header of the end screen. This method is called from the initialize
   * method.
   */
  private void createHeader() {
    Label headerLabel = new Label();
    headerLabel.setText(controller.getHeaderLabel());
    headerLabel.setId("headerLabel");

    SceneStyling.bindFontSize(headerLabel, 0.04, root);

    HBox mainHeaderContainer = new HBox();
    mainHeaderContainer.getChildren().add(headerLabel);
    mainHeaderContainer.setAlignment(Pos.CENTER);

    root.setTop(mainHeaderContainer);

  }

  /**
   * Method to create the left section of the end screen. This method is called from the initialize
   * method.
   */
  private void createLeftSection() {
    Button returnMenuButton = new Button("Return to Main Menu");
    Button playAgainButton = new Button("Create New Game");
    Button retryButton = new Button("Retry Game");

    SceneStyling.bindFontSize(returnMenuButton, 0.02, root);
    SceneStyling.bindFontSize(playAgainButton, 0.02, root);
    SceneStyling.bindFontSize(retryButton, 0.02, root);

    VBox mainContainerLeft = new VBox();
    mainContainerLeft.setSpacing(40);
    mainContainerLeft.setPadding(new Insets(100, 20, 20, 20));
    mainContainerLeft.setAlignment(Pos.TOP_CENTER);

    mainContainerLeft.prefWidthProperty().bind(root.widthProperty().divide(2));

    mainContainerLeft.getChildren().addAll(returnMenuButton, playAgainButton, retryButton);

    returnMenuButton.setOnAction(event -> controller.returnToMenu());
    playAgainButton.setOnAction(event -> controller.playAgain());
    retryButton.setOnAction(event -> controller.retry());

    root.setLeft(mainContainerLeft);
  }

  /**
   * Method to create the right section of the end screen. This method is called from the initialize
   * method.
   */
  private void createRightSection() {
    ListView<Goal> goalView = new ListView<>();

    Label goalsLabel = new Label();

    goalView.setItems(GameSession.getInstance().getGoals());
    goalView.setCellFactory(param -> new ListCell<>() {
      @Override
      protected void updateItem(Goal item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.toString());
        }
      }
    });

    SceneStyling.bindFontSize(goalsLabel, 0.02, root);

    VBox mainContainerRight = new VBox();
    mainContainerRight.setSpacing(20);
    mainContainerRight.setAlignment(Pos.TOP_CENTER);
    mainContainerRight.setPadding(new Insets(20, 80, 20, 20));

    mainContainerRight.prefWidthProperty().bind(root.widthProperty().divide(2));

    goalsLabel.setText("Goals you sat for this game:");

    mainContainerRight.getChildren().addAll(goalsLabel, goalView);

    Label noGoals = new Label("No goals were set for this game");
    SceneStyling.bindFontSize(noGoals, 0.015, root);

    goalView.setPlaceholder(noGoals);

    root.setRight(mainContainerRight);
  }

  /**
   * Method to get the root of the end screen view.
   *
   * @return the root
   */
  public BorderPane getRoot() {
    return root;
  }
}
