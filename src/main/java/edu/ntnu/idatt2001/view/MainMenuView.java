package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.models.GameSession;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Class for the main menu view. This class is responsible for the view that is shown when the user
 * starts the game.
 */
public class MainMenuView {

  private final BorderPane root;
  private Button continueGame = null;
  private Button continueFromSave;
  private Button newGame;
  private Button editStory;
  private Button settings;

  /**
   * Constructor for the main menu view.
   */
  public MainMenuView() {
    root = new BorderPane();
    SceneStyling.setBackgroundLayout(root);

    root.setTop(createHeader(root));
    root.setBottom(createBottom(root));
    root.setCenter(createCenter(root));
  }

  /**
   * Method to create the header of the main menu. This method is called from the constructor.
   *
   * @param root the root
   * @return the header
   */
  private HBox createHeader(BorderPane root) {
    HBox header = new HBox();
    Label headline = new Label("Welcome to Paths");
    SceneStyling.bindFontSize(headline, 0.06, root);

    header.getChildren().add(headline);
    header.setAlignment(Pos.CENTER);

    return header;
  }

  /**
   * Method to create the center of the main menu. This method is called from the constructor.
   *
   * @param root the root
   * @return the center as a VBox
   */
  private VBox createCenter(Pane root) {
    VBox centerMainMenu = new VBox();

    //Adds continue game button if there is a game to continue

    if (GameSession.getInstance().getGame() != null) {
      continueGame = new Button("Continue Game");
      SceneStyling.bindFontSize(continueGame, 0.03, root);
      centerMainMenu.getChildren().add(continueGame);
    }

    continueFromSave = new Button("Load Game from Save");
    newGame = new Button("New Game");
    editStory = new Button("Edit Story");
    settings = new Button("Settings");

    SceneStyling.bindFontSize(continueFromSave, 0.03, root);
    SceneStyling.bindFontSize(newGame, 0.03, root);
    SceneStyling.bindFontSize(editStory, 0.03, root);
    SceneStyling.bindFontSize(settings, 0.03, root);

    centerMainMenu.getChildren().addAll(continueFromSave, newGame, editStory, settings);
    centerMainMenu.setSpacing(20);
    centerMainMenu.setAlignment(Pos.CENTER);

    return centerMainMenu;
  }

  /**
   * Method to create the bottom of the main menu. This method is called from the constructor.
   *
   * @param root the root
   * @return the bottom as a VBox
   */
  private VBox createBottom(BorderPane root) {
    VBox bottom = new VBox();

    Label trademark = new Label("© 2023 Paths");
    SceneStyling.bindFontSize(trademark, 0.009, root);

    bottom.getChildren().addAll(trademark);
    bottom.setPadding(
        new Insets(20, 0, 0, 20)); //set padding on the left side. top, right, bottom, left
    bottom.setAlignment(Pos.BOTTOM_RIGHT);

    return bottom;
  }

  /**
   * Method to get the root of the main menu view.
   *
   * @return the root of the main menu view
   */
  public Parent getRoot() {
    return root;
  }

  /**
   * Method to get the continue game button.
   *
   * @return the continue game button
   */
  public Button getContinueGame() {
    return continueGame;
  }

  /**
   * Method to get the continue from save button.
   *
   * @return the continue from save button
   */
  public Button getContinueFromSave() {
    return continueFromSave;
  }

  /**
   * Method to get the new game button.
   *
   * @return the new game button
   */
  public Button getNewGame() {
    return newGame;
  }

  /**
   * Method to get the edit story button.
   *
   * @return the edit story button
   */
  public Button getEditStory() {
    return editStory;
  }

  /**
   * Method to get the settings button.
   *
   * @return the settings button
   */
  public Button getSettings() {
    return settings;
  }
}
