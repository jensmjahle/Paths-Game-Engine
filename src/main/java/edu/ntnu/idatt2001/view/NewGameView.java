package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.controllers.NewGameController;
import edu.ntnu.idatt2001.filehandling.StoryFileHandler;
import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;

/**
 * Class for the new game view. This class is responsible for the view that is shown when the user
 * wants to create a new game.
 */
public class NewGameView {

  private BorderPane root;
  private final NewGameController controller;
  private ComboBox<File> storyComboBox;
  private TextField playerNameTextField;
  private TextField playerHealthTextField;
  private TextField playerScoreTextField;
  private TextField playerGoldTextField;
  private TextField healthGoalTextField;
  private TextField scoreGoalTextField;
  private TextField goldGoalTextField;
  private ListView<String> playerInventoryList;
  private ListView<String> goalsInventoryList;

  /**
   * Constructor for the new game view.
   *
   * @param controller the controller for the new game view
   */
  public NewGameView(NewGameController controller) {
    this.controller = controller;
  }

  /**
   * Method for filling the whole view. Creates all the elements and adds them to the root.
   *
   * @return the root
   */
  public Parent createLayout() {
    try {
      root = new BorderPane();
      SceneStyling.setBackgroundLayout(root);
      createHeader();
      createCenter();
      createBottom();
      createLeftSide();
    } catch (Exception e) {
      showAlert("Something went wrong\n" + e.getMessage());
    }
    return root;
  }

  /**
   * Method to create the header of the new game view. This method is called from the createLayout
   * method.
   */
  private void createHeader() {
    VBox header = new VBox();
    Label newGameLabel = new Label("Create new Game");
    SceneStyling.bindFontSize(newGameLabel, 0.03, root);
    header.getChildren().add(newGameLabel);
    header.setAlignment(Pos.CENTER);
    root.setTop(header);
  }

  /**
   * Method to create the left side of the new game view. This method is called from the
   * createLayout method.
   */
  private void createLeftSide() {
    VBox leftSide = new VBox();
    Button backToMainMenuButton = new Button("Main Menu");
    SceneStyling.bindFontSize(backToMainMenuButton, 0.01, root);
    backToMainMenuButton.setOnAction(
        e -> PathsApplication.getSceneManager().navigateTo(SceneEnum.MAIN_MENU));
    leftSide.getChildren().add(backToMainMenuButton);
    leftSide.setPadding(new Insets(20, 0, 0, 20));
    leftSide.setAlignment(Pos.TOP_LEFT);
    root.setLeft(leftSide);
  }

  /**
   * Method to create the bottom of the new game view. This method is called from the createLayout
   * method.
   */
  private void createBottom() {
    Button startGameButton = new Button("Start Game");
    Button clearAllFieldsButton = new Button("Clear All Fields");
    SceneStyling.bindFontSize(startGameButton, 0.02, root);
    SceneStyling.bindFontSize(clearAllFieldsButton, 0.02, root);
    startGameButton.setOnAction(e -> controller.startGame());
    clearAllFieldsButton.setOnAction(e -> controller.clearFields());
    HBox bottom = new HBox();
    bottom.getChildren().addAll(startGameButton, clearAllFieldsButton);
    bottom.setAlignment(Pos.CENTER);
    bottom.setSpacing(20);
    bottom.setPadding(new Insets(20, 20, 20, 20));
    root.setBottom(bottom);
  }

  /**
   * Method to create the center of the new game view. This method is called from the createLayout
   * method.
   */
  private void createCenter() {
    HBox mainCenterContainer = new HBox();
    VBox playerInputFields = createPlayerInputFields();
    VBox storyInputFields = createStoryInputFields();
    VBox goalsInputFields = createGoalsInputFields();
    mainCenterContainer.getChildren().addAll(playerInputFields, storyInputFields, goalsInputFields);
    mainCenterContainer.setSpacing(20);
    mainCenterContainer.setAlignment(Pos.CENTER);
    mainCenterContainer.setPadding(new Insets(20));
    playerInputFields.prefWidthProperty().bind(mainCenterContainer.widthProperty().divide(3));
    storyInputFields.prefWidthProperty().bind(mainCenterContainer.widthProperty().divide(3));
    goalsInputFields.prefWidthProperty().bind(mainCenterContainer.widthProperty().divide(3));
    root.setCenter(mainCenterContainer);
  }

  /**
   * Method to create the input fields for the player. This method is called from the createCenter
   * method.
   *
   * @return the VBox with the player input fields
   */
  private VBox createPlayerInputFields() {
    Label playerInputFieldHeadline = new Label("Player");
    SceneStyling.bindFontSize(playerInputFieldHeadline, 0.015, root);
    HBox playerName = createInputFieldContainer("playerName", "Player Name:  ",  "Required");
    playerNameTextField = (TextField) playerName.getChildren().get(1);
    HBox playerHealth = createInputFieldContainer("playerHealth", "Player Health:", "Optional");
    playerHealthTextField = (TextField) playerHealth.getChildren().get(1);
    HBox playerScore = createInputFieldContainer("playerScore", "Player Score:  ",  "Optional");
    playerScoreTextField = (TextField) playerScore.getChildren().get(1);
    HBox playerGold = createInputFieldContainer("playerGold", "Player Gold:    ", "Optional");
    playerGoldTextField = (TextField) playerGold.getChildren().get(1);
    VBox playerInventory = createInventoryInputFields("Player Inventory", "playerInventory");
    playerInventoryList = (ListView<String>) playerInventory.getChildren().get(3);
    VBox playerInputFields = new VBox();
    playerInputFields.getChildren().addAll(
        playerInputFieldHeadline,
        playerName,
        playerHealth,
        playerScore,
        playerGold,
        playerInventory
    );
    playerInputFields.setSpacing(5);
    playerInputFields.setAlignment(Pos.TOP_CENTER);
    playerInputFields.getStyleClass().add("passage-content-container");
    return playerInputFields;
  }

  /**
   * Method to create the input fields for the inventory the player starts with. This method is
   * called from the createPlayerInputFields method.
   *
   * @param headline the headline of the input fields
   * @param listId   the id of the list view
   * @return the VBox with the inventory input fields
   */
  private VBox createInventoryInputFields(String headline, String listId) {
    Label inventoryInputFieldHeadline = new Label(headline);
    SceneStyling.bindFontSize(inventoryInputFieldHeadline, 0.012, root)
    ;
    HBox buttonContainer = new HBox();
    Button addItemButton = new Button("Add Item");
    Button removeItemButton = new Button("Remove Item");
    SceneStyling.bindFontSize(addItemButton, 0.01, root);
    SceneStyling.bindFontSize(removeItemButton, 0.01, root);
    buttonContainer.getChildren().addAll(addItemButton, removeItemButton);
    buttonContainer.setSpacing(10);
    buttonContainer.setAlignment(Pos.CENTER);
    HBox inventoryTextFieldContainer = createInputFieldContainer("inventoryTextField",
        "Item name:  ", "Optional");
    ListView<String> inventoryList = new ListView<>();
    inventoryList.setId(listId);
    Label emptyLabel = new Label("No Items");
    inventoryList.setPlaceholder(emptyLabel);
    TextField inventoryTextField = (TextField) inventoryTextFieldContainer.getChildren().get(1);
    addItemButton.setOnAction(event -> {
      String itemName = inventoryTextField.getText();
      if (!itemName.isEmpty()) {
        inventoryList.getItems().add(itemName);
        inventoryTextField.clear();
      }
    });
    removeItemButton.setOnAction(event -> {
      String selectedItem = inventoryList.getSelectionModel().getSelectedItem();
      if (selectedItem != null) {
        inventoryList.getItems().remove(selectedItem);
        inventoryList.getSelectionModel().clearSelection();
      }
    });
    addItemButton.disableProperty().bind(inventoryTextField.textProperty().isEmpty());
    removeItemButton.disableProperty()
        .bind(Bindings.isEmpty(inventoryList.getSelectionModel().getSelectedItems()));
    VBox inventoryInputFields = new VBox();
    inventoryInputFields.getChildren().addAll(
        inventoryInputFieldHeadline,
        inventoryTextFieldContainer,
        buttonContainer,
        inventoryList
    );
    inventoryInputFields.setSpacing(5);
    inventoryInputFields.setAlignment(Pos.TOP_CENTER);
    inventoryInputFields.getStyleClass().add("passage-content-container");
    return inventoryInputFields;
  }

  /**
   * Method to create the input fields for the goals of the game. This method is called from the
   * createCenter method.
   *
   * @return the VBox with the goals input fields
   */
  private VBox createGoalsInputFields() {
    Label goalsInputFieldHeadline = new Label("Goals");
    SceneStyling.bindFontSize(goalsInputFieldHeadline, 0.015, root);
    HBox healthGoal = createInputFieldContainer("healthGoal", "Health Goal:     ", "Optional");
    healthGoalTextField = (TextField) healthGoal.getChildren().get(1);
    HBox scoreGoal = createInputFieldContainer("scoreGoal", "Score Goal:       ", "Optional");
    scoreGoalTextField = (TextField) scoreGoal.getChildren().get(1);
    HBox goldGoal = createInputFieldContainer("goldGoal", "Gold Goal:        ", "Optional");
    goldGoalTextField = (TextField) goldGoal.getChildren().get(1);
    VBox inventoryGoal = createInventoryInputFields("Inventory Goal", "inventoryGoal");
    goalsInventoryList = (ListView<String>) inventoryGoal.getChildren().get(3);

    VBox goalsInputFields = new VBox();
    goalsInputFields.getChildren().addAll(
        goalsInputFieldHeadline,
        healthGoal,
        scoreGoal,
        goldGoal,
        inventoryGoal
    );
    goalsInputFields.setSpacing(20);
    goalsInputFields.setAlignment(Pos.TOP_CENTER);
    goalsInputFields.getStyleClass().add("passage-content-container");
    return goalsInputFields;
  }

  /**
   * Method to create the input fields for the story. This method is called from the createCenter
   * method.
   *
   * @param textFieldId     the id of the text field
   * @param labelText       the label text
   * @param textFieldPrompt the prompt text of the text field
   *
   * @return the HBox with the input fields
   */
  private HBox createInputFieldContainer(String textFieldId, String labelText,
      String textFieldPrompt) {
    Label label = new Label(labelText);
    SceneStyling.bindFontSize(label, 0.01, root);
    TextField textField = new TextField();
    textField.setPromptText(textFieldPrompt);
    SceneStyling.bindFontSize(textField, 0.006, root);
    textField.setId(textFieldId);
    HBox inputFieldContainer = new HBox();
    inputFieldContainer.getChildren().addAll(label, textField);
    inputFieldContainer.setSpacing(10);
    inputFieldContainer.setAlignment(Pos.CENTER);
    return inputFieldContainer;
  }

  /**
   * Method to create the input fields for the story. This method is called from the createCenter
   * method.
   *
   * @return the VBox with the story input fields
   */
  private VBox createStoryInputFields() {
    Label storyInputFieldHeadline = new Label("Story Selection");
    SceneStyling.bindFontSize(storyInputFieldHeadline, 0.015, root);

    Button chooseFromFileButton = new Button("Choose From File Explorer");
    SceneStyling.bindFontSize(chooseFromFileButton, 0.01, root);

    storyComboBox = new ComboBox<>();
    storyComboBox.setPromptText("Choose from library");
    SceneStyling.bindFontSize(storyComboBox, 0.008, root);

    File folderpath = new File(
            System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);
    if (!folderpath.exists()) {
      folderpath.mkdir();
    }

    storyComboBox.getItems()
        .addAll(StoryFileHandler.getListOfStoryFiles());
    storyComboBox.setId("storyComboBox");

    // Set the cell factory to display only the filenames
    storyComboBox.setCellFactory(param -> new ListCell<>() {
      @Override
      protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
          setText(null);
        } else {
          setText(item.getName());
        }
      }
    });

    // Set the converter to display only the filename in the ComboBox
    storyComboBox.setConverter(new StringConverter<>() {
      @Override
      public String toString(File file) {
        return file == null ? "" : file.getName();
      }

      @Override
      public File fromString(String string) {
        return null;
      }
    });

    VBox storyInputFields = new VBox();
    storyInputFields.getChildren().addAll(
        storyInputFieldHeadline,
        storyComboBox,
        chooseFromFileButton,
        createBrokenLinksContainer()
    );

    chooseFromFileButton.setOnAction(event -> {
      File selectedFile = openFileChooser();
      if (selectedFile != null) {
        storyComboBox.getItems().add(selectedFile);
        storyComboBox.getSelectionModel().select(selectedFile);
      }
    });

    storyInputFields.setSpacing(25);
    storyInputFields.setAlignment(Pos.TOP_CENTER);
    storyInputFields.getStyleClass().add("passage-content-container");

    return storyInputFields;
  }

  /**
   * Method to create the container for the broken links list. This method is called from the
   * createStoryInputFields method. Method will look for broken links in the selected story and
   * display them in a ListView.
   *
   * @return the VBox with the broken links container
   */
  private VBox createBrokenLinksContainer() {
    VBox brokenLinksContainer;

    String tooltipBrokenLinks = ("""
        Broken links are links that are not pointing to a valid passage.
        They will still be displayed in the game, but they will not work.
        A story should not contain any broken links.
        You can fix broken links by renaming or deleting the link so that
        it links to a valid passage or create a new passage using the link.
        You can do this in the "Edit Story" view in the main menu.""");

    ListView<String> brokenLinksList = new ListView<>();

    Label emptyLabel = new Label("No Broken Links");
    brokenLinksList.setPlaceholder(emptyLabel);

    storyComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
      Story selectedStory;
      try {
        selectedStory = StoryFileHandler.loadStoryFromFile(newValue);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      List<Link> brokenLinks = new ArrayList<>(selectedStory.getBrokenLinks());
      brokenLinksList.getItems().clear();

      for (Link link : brokenLinks) {
        brokenLinksList.getItems().add(link.toString());
      }
    });

    brokenLinksContainer = SceneStyling.createContainerWithListView(
        brokenLinksList,
        "Broken Links",
        "No Broken Links",
        tooltipBrokenLinks,
        root
    );

    brokenLinksContainer.setSpacing(5);
    brokenLinksContainer.setAlignment(Pos.TOP_CENTER);
    brokenLinksContainer.getStyleClass().add("passage-content-container");
    return brokenLinksContainer;
  }

  /**
   * Method to create the functionality to open a file chooser. This method is called from the
   * createStoryInputFields method. It will first let the user browse for a file and then return the
   * file that was selected.
   *
   * @return the file that was selected in the file chooser
   */
  private File openFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open File");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("PATHS Files", "*.paths"));

    Stage stage = (Stage) root.getScene().getWindow();
    return fileChooser.showOpenDialog(stage);
  }

  /**
   * Method to return the player name from the player name text field.
   *
   * @return the player name
   */
  public String getPlayerName() {
    return playerNameTextField.getText();
  }

  /**
   * Method to return the player health from the player health text field.
   *
   * @return the player health
   */
  public int getPlayerHealth() {
    String healthText = playerHealthTextField.getText();
    return healthText.isEmpty() ? 0 : Integer.parseInt(healthText);
  }

  /**
   * Method to return the player score from the player score text field.
   *
   * @return the player score
   */
  public int getPlayerScore() {
    String scoreText = playerScoreTextField.getText();
    return scoreText.isEmpty() ? 0 : Integer.parseInt(scoreText);
  }

  /**
   * Method to return the player gold from the player gold text field.
   *
   * @return the player gold
   */
  public int getPlayerGold() {
    String goldText = playerGoldTextField.getText();
    return goldText.isEmpty() ? 0 : Integer.parseInt(goldText);
  }

  /**
   * Method to return the player inventory from the player inventory list.
   *
   * @return the player inventory
   */
  public List<String> getInventory() {
    return new ArrayList<>(playerInventoryList.getItems());
  }

  /**
   * Method to return the selected story from the story combo box.
   *
   * @return the selected story
   */
  public File getSelectedStory() {
    return storyComboBox.getSelectionModel().getSelectedItem();
  }

  /**
   * Method to return the health goal from the health goal text field.
   *
   * @return the health goal that was entered
   */
  public int getHealthGoal() {
    String healthGoalText = healthGoalTextField.getText();
    return healthGoalText.isEmpty() ? 0 : Integer.parseInt(healthGoalText);
  }

  /**
   * Method to return the score goal from the score goal text field.
   *
   * @return the score goal that was entered
   */
  public int getScoreGoal() {
    String scoreGoalText = scoreGoalTextField.getText();
    return scoreGoalText.isEmpty() ? 0 : Integer.parseInt(scoreGoalText);
  }

  /**
   * Method to return the gold goal from the gold goal text field.
   *
   * @return the gold goal that was entered
   */
  public int getGoldGoal() {
    String goldGoalText = goldGoalTextField.getText();
    return goldGoalText.isEmpty() ? 0 : Integer.parseInt(goldGoalText);
  }

  /**
   * Method to return the inventory goal from the inventory goal list.
   *
   * @return the inventory goal that was entered
   */
  public List<String> getInventoryGoal() {
    return new ArrayList<>(goalsInventoryList.getItems());
  }

  /**
   * Method to clear all the fields in the view. This method essentially sends the user back to the
   * original state of the view.
   */
  public void clearFields() {
    playerNameTextField.clear();
    playerHealthTextField.clear();
    playerScoreTextField.clear();
    playerGoldTextField.clear();
    healthGoalTextField.clear();
    scoreGoalTextField.clear();
    goldGoalTextField.clear();
    if (playerInventoryList != null) {
      playerInventoryList.getItems().clear();
    }
    if (goalsInventoryList != null) {
      goalsInventoryList.getItems().clear();
    }
  }

  /**
   * Method to show an alert to the user.
   *
   * @param message the message to be displayed in the alert
   */
  public void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText("Something went wrong");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Method to validate the user inputs. This method will check to make sure that the user has
   * entered a player name and selected a story, as well as entered valid inputs for the player
   * health, score, and gold. If the user has not entered valid inputs, an error message will be
   * displayed to the user and the input field will turn red.
   */
  public void validateUserInputs() {
    String errorMessage = "";
    boolean hasError = false;

    if (getPlayerName().isEmpty()) {
      playerNameTextField.getStyleClass().add("error");
      errorMessage += "Please enter a player name.\n";
      hasError = true;
    } else {
      playerNameTextField.getStyleClass().remove("error");
    }

    if (getSelectedStory() == null) {
      storyComboBox.getStyleClass().add("error");
      errorMessage += "Please select a story.\n";
      hasError = true;
    } else {
      storyComboBox.getStyleClass().remove("error");
    }

    try {
      playerHealthTextField.getStyleClass().remove("error");
      getPlayerHealth();
    } catch (NumberFormatException e) {
      playerHealthTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for player health.\n";
      hasError = true;
    }

    try {
      playerScoreTextField.getStyleClass().remove("error");
      getPlayerScore();
    } catch (NumberFormatException e) {
      playerScoreTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for player score.\n";
      hasError = true;
    }

    try {
      playerGoldTextField.getStyleClass().remove("error");
      getPlayerGold();
    } catch (NumberFormatException e) {
      playerGoldTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for player gold.\n";
      hasError = true;
    }

    try {
      healthGoalTextField.getStyleClass().remove("error");
      getHealthGoal();
    } catch (NumberFormatException e) {
      healthGoalTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for health goal.\n";
      hasError = true;
    }

    try {
      scoreGoalTextField.getStyleClass().remove("error");
      getScoreGoal();
    } catch (NumberFormatException e) {
      scoreGoalTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for score goal.\n";
      hasError = true;
    }

    try {
      goldGoalTextField.getStyleClass().remove("error");
      getGoldGoal();
    } catch (NumberFormatException e) {
      goldGoalTextField.getStyleClass().add("error");
      errorMessage += "Please enter a valid number for gold goal.\n";
      hasError = true;
    }


    if (hasError) {
      throw new IllegalArgumentException(errorMessage);
    }
  }
}


