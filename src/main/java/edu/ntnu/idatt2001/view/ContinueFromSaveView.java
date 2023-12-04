package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.filehandling.SaveState;
import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import java.util.HashMap;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


/**
 * This class is responsible for the view that is shown when the user wants to continue from a
 * save.
 */
public class ContinueFromSaveView {

  private final BorderPane root;
  private TableView<Game> gameTableView;
  private final Button continueButton = new Button("Continue");
  private final Button deleteButton = new Button("Delete");
  private final Button returnToMainMenu = new Button("Main Menu");


  /**
   * Method for filling the whole view. Creates all the elements and adds them to the root.
   */
  public ContinueFromSaveView() {
    root = new BorderPane();
    SceneStyling.setBackgroundLayout(root);

    createHeader();
    createLeft();
    createCenter();
    createRight();
    createBottom();
  }

  /**
   * Method to get the root of the view.
   *
   * @return the root
   */
  public Parent getRoot() {
    return root;
  }

  /**
   * Retrieves the continue button.
   *
   * @return the continue button
   */
  public Button getContinueButton() {
    return continueButton;
  }

  /**
   * Retrieves the delete button.
   *
   * @return the delete button
   */
  public Button getDeleteButton() {
    return deleteButton;
  }

  /**
   * Retrieves the return to main menu button.
   *
   * @return the return to main menu button
   */
  public Button getReturnToMainMenu() {
    return returnToMainMenu;
  }

  /**
   * Method for filling the table view with the save states.
   */
  private void createCenter() {
    HBox mainCenterContainer = new HBox();

    fillTableView();

    mainCenterContainer.getChildren().add(gameTableView);
    mainCenterContainer.setAlignment(Pos.CENTER);

    DoubleBinding sizeBinding = root.widthProperty().multiply(0.5);
    gameTableView.prefWidthProperty().bind(sizeBinding);
    gameTableView.styleProperty().bind(
        Bindings.concat("-fx-font-size: ", sizeBinding.asString(), "pt;"));
    gameTableView.setPadding(new Insets(20, 20, 20, 20));

    gameTableView.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> updateButtons());

    root.setCenter(mainCenterContainer);
  }

  /**
   * Method to create the header of the view. Adds a label to the top of the root.
   */
  private void createHeader() {
    HBox header = new HBox();

    Label headline = new Label("Choose a save to continue");
    SceneStyling.bindFontSize(headline, 0.04, root);

    header.getChildren().add(headline);
    header.setAlignment(Pos.CENTER);

    root.setTop(header);
  }

  /**
   * Method to create the left side of the view. Add buttons to the left side of the root.
   */
  private void createLeft() {
    VBox left = new VBox();

    left.setMinWidth(root.getWidth() / 100 * 20);

    SceneStyling.bindFontSize(returnToMainMenu, 0.02, root);
    SceneStyling.bindFontSize(continueButton, 0.02, root);
    SceneStyling.bindFontSize(deleteButton, 0.02, root);

    continueButton.setDisable(true);
    deleteButton.setDisable(true);

    left.setSpacing(20);
    left.setPadding(new Insets(20, 20, 20, 20));
    left.setAlignment(Pos.TOP_CENTER);

    VBox leftBottom = new VBox();
    leftBottom.setSpacing(20);
    leftBottom.setPadding(new Insets(250, 20, 20, 20));
    leftBottom.setAlignment(Pos.BOTTOM_CENTER);

    leftBottom.getChildren().addAll(continueButton, deleteButton);
    left.getChildren().addAll(returnToMainMenu, leftBottom);

    root.setLeft(left);
  }

  /**
   * Method to create the right side of the view. Adds a VBox to the right side of the root.
   */
  private void createRight() {
    VBox right = new VBox();
    root.setRight(right);
  }

  /**
   * Method to create the bottom of the view. Adds a VBox to the bottom of the root.
   */
  private void createBottom() {
    VBox bottom = new VBox();
    bottom.setMinHeight(100);
    root.setRight(bottom);
  }

  /**
   * Method to fill the table view with the save states. Loads the save states from the SaveState
   * class and adds them to the table view.
   */
  private void fillTableView() {
    HashMap<String, Game> games = SaveState.loadGame();

    gameTableView = new TableView<>();
    gameTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    TableColumn<Game, String> playerNameColumn = new TableColumn<>("Player");
    playerNameColumn.setCellValueFactory(new PropertyValueFactory<>("player"));

    TableColumn<Game, String> storyTitleColumn = new TableColumn<>("Story");
    storyTitleColumn.setCellValueFactory(new PropertyValueFactory<>("story"));

    gameTableView.getColumns().addAll(List.of(playerNameColumn, storyTitleColumn));

    try {
      // Convert the HashMap values to an ObservableList
      assert games != null;
      ObservableList<Game> gameList = FXCollections.observableArrayList(games.values());

      // Set the data in the TableView
      gameTableView.setItems(gameList);
    } catch (NullPointerException e) {
      SceneStyling.showAlert(AlertType.ERROR, "No save found",
          "No save was found. Please start a new game.");
    }
  }

  /**
   * Method to update the buttons. Disables the buttons if no save state is selected.
   */
  private void updateButtons() {
    continueButton.setDisable(gameTableView.getSelectionModel().isEmpty());
    deleteButton.setDisable(gameTableView.getSelectionModel().isEmpty());
  }

  /**
   * Method to get the selected game from the table view.
   *
   * @return the selected game
   */
  public Game getSelectedGame() {
    return gameTableView.getSelectionModel().getSelectedItem();
  }

  /**
   * Method to remove a game from the table view.
   *
   * @param game the game to remove.
   */
  public void removeGame(Game game) {
    gameTableView.getItems().remove(game);
  }

}
