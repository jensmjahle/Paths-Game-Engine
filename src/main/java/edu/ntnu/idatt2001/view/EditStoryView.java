package edu.ntnu.idatt2001.view;

import edu.ntnu.idatt2001.filehandling.StoryFileHandler;
import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.models.StoryEditSession;
import edu.ntnu.idatt2001.models.actions.Action;
import edu.ntnu.idatt2001.models.actions.GoldAction;
import edu.ntnu.idatt2001.models.actions.HealthAction;
import edu.ntnu.idatt2001.models.actions.InventoryAction;
import edu.ntnu.idatt2001.models.actions.ScoreAction;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A class to handle the view for editing stories.
 */
public class EditStoryView {

  private final BorderPane root;
  private final StoryEditSession storyEditSession;
  Button backToMainMenuButton = new Button("Main Menu");
  Button addStoryToLibraryButton = new Button("Add File to Library");
  Button deleteStoryButton = new Button("Delete Story");
  Button createNewStoryButton = new Button("Create New Story");
  Button addPassageButton = new Button("Add Passage");
  Button editOpeningPassageButton = new Button("Edit Opening Passage");
  Button goBackToLibraryButton = new Button("Go Back");
  Button saveStoryButton = new Button("Save Story");
  Button cancelStoryButton = new Button("Cancel");
  Button saveChangesToStoryButton = new Button("Save Changes");
  Button deletePassageButton = new Button("Delete Passage");
  Button addLinkButton = new Button("Add Link");
  Button deleteLinkButton = new Button("Delete Link");
  Button addActionToLinkButton = new Button("Add Action");
  Button deleteActionToLinkButton = new Button("Delete Action");
  Button saveLinkButton = new Button("Save");
  Button cancelLinkButton = new Button("Cancel");
  Button newActionButton = new Button("New Action");
  Button goBackToPassageFromLinkButton = new Button("Go Back");
  Button savePassageButton = new Button("Save");
  Button cancelPassageButton = new Button("Cancel");
  Button goBackToStoryFromPassageButton = new Button("Go Back");
  Button editActionSaveButton = new Button("Save");
  Button editActionCancelButton = new Button("Cancel");
  Button editActionDeleteButton = new Button("Delete");
  Button newHealthActionButton = new Button("Add Health Action");
  Button newGoldActionButton = new Button("Add Gold Action");
  Button newScoreActionButton = new Button("Add Score Action");
  Button newInventoryActionButton = new Button("Add Inventory Action");
  TextArea storyTitleTextArea = new TextArea();
  TextArea passageTitleTextArea = new TextArea();
  TextArea passageContentTextArea = new TextArea();
  TextArea linkTextTextArea = new TextArea();
  TextArea linkReferenceTextArea = new TextArea();
  TextField editActionTextField = new TextField();
  TextField newHealthActionTextField = new TextField();
  TextField newGoldActionTextField = new TextField();
  TextField newScoreActionTextField = new TextField();
  TextField newInventoryActionTextField = new TextField();
  boolean storySelected = false;
  boolean passageSelected = false;
  boolean linkSelected = false;
  boolean actionSelected = false;
  private boolean createNewAction = false;

  /**
   * Constructor for the EditStoryView class.
   *
   * @param storyEditSession the story edit session.
   */
  public EditStoryView(StoryEditSession storyEditSession) {
    this.storyEditSession = storyEditSession;

    root = new BorderPane();

    SceneStyling.setBackgroundLayout(root);

    initializeListeners();
    updateLayout();
  }

  public Parent getRoot() {
    return root;
  }

  /**
   * Initializes the listeners for the selected story, passage, link and action.
   */
  public void initializeListeners() {
    storyEditSession.getSelectedStoryProperty().addListener((observable, oldValue, newValue) -> {
      storySelected = newValue != null;
      passageSelected = false;
      linkSelected = false;
      actionSelected = false;
      updateLayout();
    });
    storyEditSession.getSelectedPassageProperty().addListener((observable, oldValue, newValue) -> {
      passageSelected = newValue != null;
      storySelected = false;
      linkSelected = false;
      actionSelected = false;
      updateLayout();
    });
    storyEditSession.getSelectedLinkProperty().addListener((observable, oldValue, newValue) -> {
      linkSelected = newValue != null;
      storySelected = false;
      passageSelected = false;
      actionSelected = false;
      updateLayout();
    });
    storyEditSession.getSelectedActionProperty().addListener((observable, oldValue, newValue) -> {
      actionSelected = newValue != null;
      storySelected = false;
      passageSelected = false;
      linkSelected = false;
      updateLayout();
    });
  }

  /**
   * Updates the layout of the view.
   */
  public void updateLayout() {
    createLeftSide();
    createCenter();
  }


  public Button getBackToMainMenuButton() {
    return backToMainMenuButton;
  }

  public Button getAddStoryToLibraryButton() {
    return addStoryToLibraryButton;
  }

  public Button getDeleteStoryButton() {
    return deleteStoryButton;
  }

  public Button getCreateNewStoryButton() {
    return createNewStoryButton;
  }

  public Button getAddPassageButton() {
    return addPassageButton;
  }

  public Button getDeletePassageButton() {
    return deletePassageButton;
  }

  public Button getAddLinkButton() {
    return addLinkButton;
  }

  public Button getDeleteLinkButton() {
    return deleteLinkButton;
  }

  public Button getAddActionToLinkButton() {
    return addActionToLinkButton;
  }

  public Button getDeleteActionToLinkButton() {
    return deleteActionToLinkButton;
  }

  public Button getSaveStoryButton() {
    return saveStoryButton;
  }

  public Button getSaveChangesToStoryButton() {
    return saveChangesToStoryButton;
  }

  public Button getSaveLinkButton() {
    return saveLinkButton;
  }

  public Button getCancelLinkButton() {
    return cancelLinkButton;
  }

  public Button getNewActionButton() {
    return newActionButton;
  }

  public Button getGoBackToPassageFromLinkButton() {
    return goBackToPassageFromLinkButton;
  }

  public Button getSavePassageButton() {
    return savePassageButton;
  }

  public Button getCancelPassageButton() {
    return cancelPassageButton;
  }


  public Button getGoBackToStoryFromPassageButton() {
    return goBackToStoryFromPassageButton;
  }

  public Button getEditActionSaveButton() {
    return editActionSaveButton;
  }

  public Button getEditActionCancelButton() {
    return editActionCancelButton;
  }

  public Button getEditActionDeleteButton() {
    return editActionDeleteButton;
  }

  public Button getNewHealthActionButton() {
    return newHealthActionButton;
  }

  public Button getNewGoldActionButton() {
    return newGoldActionButton;
  }

  public Button getNewScoreActionButton() {
    return newScoreActionButton;
  }

  public Button getNewInventoryActionButton() {
    return newInventoryActionButton;
  }

  public TextArea getStoryTitleTextArea() {
    return storyTitleTextArea;
  }

  public TextArea getPassageTitleTextArea() {
    return passageTitleTextArea;
  }

  public TextArea getPassageContentTextArea() {
    return passageContentTextArea;
  }

  public TextArea getLinkTextTextArea() {
    return linkTextTextArea;
  }

  public TextArea getLinkReferenceTextArea() {
    return linkReferenceTextArea;
  }

  public TextField getEditActionTextField() {
    return editActionTextField;
  }

  public TextField getNewHealthActionTextField() {
    return newHealthActionTextField;
  }

  public TextField getNewGoldActionTextField() {
    return newGoldActionTextField;
  }

  public TextField getNewScoreActionTextField() {
    return newScoreActionTextField;
  }

  public TextField getNewInventoryActionTextField() {
    return newInventoryActionTextField;
  }


  private void createLeftSide() {
    VBox leftSide = new VBox();

    //Disable buttons if no story is selected
    if (!storySelected) {
      saveStoryButton.setDisable(true);
      deleteStoryButton.setDisable(true);
    } else {
      saveStoryButton.setDisable(false);
      deleteStoryButton.setDisable(false);
    }

    VBox buttonBox = new VBox();
    buttonBox.getChildren()
        .addAll(addStoryToLibraryButton, saveStoryButton,
            deleteStoryButton, createNewStoryButton);
    buttonBox.setSpacing(20);

    //Adds the library container if a story is selected
    if (storySelected) {
      VBox libraryBox = libraryContainer(0.01);

      libraryBox.prefWidthProperty().bind(leftSide.widthProperty());
      buttonBox.getChildren().add(libraryBox);
    }

    leftSide.prefWidthProperty().bind(root.widthProperty().divide(5));

    backToMainMenuButton.prefWidthProperty().bind(leftSide.widthProperty());
    addStoryToLibraryButton.prefWidthProperty().bind(leftSide.widthProperty());
    saveStoryButton.prefWidthProperty().bind(leftSide.widthProperty());
    deleteStoryButton.prefWidthProperty().bind(leftSide.widthProperty());
    createNewStoryButton.prefWidthProperty().bind(leftSide.widthProperty());

    leftSide.getChildren().addAll(backToMainMenuButton, buttonBox);
    leftSide.setSpacing(80);
    leftSide.setPadding(
        new Insets(20, 20, 20, 20)); //set padding on the left side. top, right, bottom, left
    leftSide.setAlignment(Pos.TOP_LEFT);

    root.setLeft(leftSide);
  }

  private void createCenter() {
    VBox center = new VBox();
    center.prefWidthProperty().bind(root.widthProperty().divide(2));

    if (storySelected) {
      HBox topMenuButtons = editButtonsContainer(addPassageButton, editOpeningPassageButton,
          saveChangesToStoryButton, cancelStoryButton, goBackToLibraryButton);
      Label editStoryHeadline = new Label(
          "EDIT STORY: " + storyEditSession.getSelectedStory().getTitle());
      VBox storyEditor = storyEditor();

      editStoryHeadline.prefWidthProperty().bind(center.widthProperty());
      storyEditor.prefWidthProperty().bind(center.widthProperty());
      storyEditor.prefHeightProperty().bind(center.heightProperty());
      topMenuButtons.prefWidthProperty().bind(center.widthProperty());

      SceneStyling.bindFontSize(editStoryHeadline, 0.02, root);

      center.getChildren().addAll(topMenuButtons, editStoryHeadline, storyEditor);
    } else if (passageSelected) {
      HBox topMenuButtons = editButtonsContainer(addLinkButton, deletePassageButton,
          savePassageButton, cancelPassageButton, goBackToStoryFromPassageButton);
      Label editPassageHeadline = new Label(
          "EDIT PASSAGE: " + storyEditSession.getSelectedPassage().getTitle());
      VBox passageEditor = passageEditor();

      topMenuButtons.prefWidthProperty().bind(center.widthProperty());
      editPassageHeadline.prefWidthProperty().bind(center.widthProperty());
      passageEditor.prefWidthProperty().bind(center.widthProperty());
      passageEditor.prefHeightProperty().bind(center.heightProperty());
      SceneStyling.bindFontSize(editPassageHeadline, 0.02, root);

      center.getChildren().addAll(topMenuButtons, editPassageHeadline, passageEditor);
    } else if (linkSelected) {
      HBox topMenuButtons = editButtonsContainer(addActionToLinkButton, deleteLinkButton,
          saveLinkButton, cancelLinkButton, goBackToPassageFromLinkButton);
      Label editLinkHeadline = new Label(
          "EDIT LINK: " + storyEditSession.getSelectedLink().getText());
      VBox linkEditor = linkEditor();

      topMenuButtons.prefWidthProperty().bind(center.widthProperty());
      editLinkHeadline.prefWidthProperty().bind(center.widthProperty());
      linkEditor.prefWidthProperty().bind(center.widthProperty());
      linkEditor.prefHeightProperty().bind(center.heightProperty());
      SceneStyling.bindFontSize(editLinkHeadline, 0.02, root);

      center.getChildren().addAll(topMenuButtons, editLinkHeadline, linkEditor);
    } else if (actionSelected && createNewAction) {
      Label editActionHeadline = new Label("NEW ACTION");
      VBox actionEditor = actionEditor();

      editActionHeadline.prefWidthProperty().bind(center.widthProperty());
      actionEditor.prefWidthProperty().bind(center.widthProperty());
      actionEditor.prefHeightProperty().bind(center.heightProperty());
      SceneStyling.bindFontSize(editActionHeadline, 0.02, root);

      center.getChildren().addAll(editActionHeadline, actionEditor);
    } else if (actionSelected) {
      Label editActionHeadline = new Label("EDIT ACTION: " + storyEditSession.getSelectedAction());
      VBox actionEditor = actionEditor();

      editActionHeadline.prefWidthProperty().bind(center.widthProperty());
      actionEditor.prefWidthProperty().bind(center.widthProperty());
      actionEditor.prefHeightProperty().bind(center.heightProperty());
      SceneStyling.bindFontSize(editActionHeadline, 0.02, root);

      center.getChildren().addAll(editActionHeadline, actionEditor);
    } else {
      VBox libraryContainer = libraryContainer(0.03);

      libraryContainer.prefWidthProperty().bind(center.widthProperty().divide(2));
      libraryContainer.prefHeightProperty().bind(center.heightProperty());
      center.getChildren().addAll(libraryContainer);
      center.setAlignment(Pos.CENTER);
    }

    center.setAlignment(Pos.TOP_LEFT);
    center.setSpacing(2);
    center.setPadding(new Insets(20, 20, 20, 20));

    root.setCenter(center);
  }

  private VBox storyEditor() {

    // Sub elements to keyValues
    Label numberOfPassages = new Label(
        "Number of Passages: " + storyEditSession.getSelectedStory()
            .getPassages().size());
    Label numberOfBrokenLinks = new Label(
        "Number of Broken Links: " + storyEditSession.getSelectedStory()
            .getBrokenLinks().size());

    VBox keyValues;
    keyValues = new VBox(numberOfPassages, numberOfBrokenLinks);
    keyValues.setSpacing(2);
    keyValues.setAlignment(Pos.CENTER);
    VBox storyEditor = new VBox();
    keyValues.prefWidthProperty().bind(storyEditor.widthProperty().divide(2));
    keyValues.getStyleClass().add("passage-content-container");

    // Sub elements to titleEditor
    VBox titleInputField = inputFieldContainer("Title", storyTitleTextArea,
        storyEditSession.getSelectedStory().getTitle());
    VBox titleEditor;
    titleEditor = new VBox(titleInputField);
    titleEditor.setSpacing(10);
    titleEditor.setAlignment(Pos.CENTER);
    titleEditor.prefWidthProperty().bind(storyEditor.widthProperty().divide(2));

    HBox keyValuesAndTitleEditor;
    keyValuesAndTitleEditor = new HBox(keyValues, titleEditor);
    keyValuesAndTitleEditor.setSpacing(10);

    //Sub elements to allPassages
    ListView<Passage> passageListView = new ListView<>();
    passageListView.getItems().addAll(storyEditSession.getSelectedStory().getPassages());

    String allPassagesTooltip = """
        To edit a passage, select it from the list of passages bellow.
        To create a new passage, click the "New Passage" button.
        To delete a passage, select it and click the "Delete" button.
        """;
    VBox allPassages;
    allPassages = SceneStyling.createContainerWithListView(passageListView, "All Passages",
        "This Story has no Passages", allPassagesTooltip, root);

    passageListView.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          storyEditSession.setSelectedPassage(newValue);
          updateLayout();
        });

    // Sub elements to brokenLinks
    ListView<Link> brokenLinksListView = new ListView<>();
    brokenLinksListView.getItems().addAll(storyEditSession.getSelectedStory().getBrokenLinks());

    String allBrokenLinksTooltip = """
        Broken links are links that are not pointing to a valid passage.
        They will still be displayed in the game, but they will not work.
        A story should not contain any broken links.
        You can fix broken links by renaming or deleting the link so that
        it links to a valid passage or create a new passage using the link.
        """;
    VBox brokenLinks;
    brokenLinks = SceneStyling.createContainerWithListView(brokenLinksListView,
        "Broken Links",
        "This Story has no Broken Links",
        allBrokenLinksTooltip, root);

    storyEditor.getChildren().addAll(keyValuesAndTitleEditor, allPassages, brokenLinks);
    storyEditor.setSpacing(10);
    storyEditor.setAlignment(Pos.CENTER);
    storyEditor.getStyleClass().add("passage-content-container");

    keyValuesAndTitleEditor.prefHeightProperty().bind(storyEditor.heightProperty().divide(5));
    allPassages.prefHeightProperty().bind(storyEditor.heightProperty().divide(5).multiply(2));
    brokenLinks.prefHeightProperty().bind(storyEditor.heightProperty().divide(5).multiply(2));

    return storyEditor;
  }

  private VBox passageEditor() {

    // Sub elements to textInputFields
    VBox titleInputField = new VBox();
    VBox contentInputField = new VBox();
    if (passageSelected) {
      titleInputField = inputFieldContainer("Title", passageTitleTextArea,
          storyEditSession.getSelectedPassage().getTitle());
      contentInputField = inputFieldContainer("Content", passageContentTextArea,
          storyEditSession.getSelectedPassage().getContent());
    }

    HBox textInputFields;
    textInputFields = new HBox(titleInputField, contentInputField);
    textInputFields.setSpacing(2);
    textInputFields.setAlignment(Pos.CENTER);

    // Sub elements to allLinks
    ListView<Link> linkListView = new ListView<>();
    if (passageSelected) {
      linkListView.getItems().addAll(storyEditSession.getSelectedPassage().getLinks());
    }
    String allLinksTooltip = """
        To edit a link, select it from the list of links bellow.
        To create a new link, click the "New Link" button.
        To delete a link, select it and click the "Delete" button.
        """;
    VBox allLinks;
    allLinks = SceneStyling.createContainerWithListView(linkListView, "All Links",
        "This Passage has no Links", allLinksTooltip, root);

    VBox passageEditor = new VBox();
    passageEditor.getChildren().addAll(textInputFields, allLinks);
    passageEditor.setSpacing(10);
    passageEditor.setAlignment(Pos.CENTER);
    passageEditor.getStyleClass().add("passage-content-container");

    textInputFields.prefHeightProperty().bind(passageEditor.heightProperty().divide(5).multiply(2));
    allLinks.prefHeightProperty().bind(passageEditor.heightProperty().divide(5).multiply(3));

    linkListView.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          storyEditSession.setSelectedLink(newValue);
          updateLayout();
        });

    return passageEditor;
  }

  private VBox linkEditor() {

    // Sub elements to textInputFields
    VBox textInputField = new VBox();
    VBox referenceInputField = new VBox();
    if (linkSelected) {
      textInputField = inputFieldContainer("Text", linkTextTextArea, storyEditSession
          .getSelectedLink().getText());
      referenceInputField = inputFieldContainer("Reference", linkReferenceTextArea,
          storyEditSession.getSelectedLink().getReference());
    }

    HBox textInputFields;
    textInputFields = new HBox(textInputField, referenceInputField);
    textInputFields.setSpacing(2);
    textInputFields.setAlignment(Pos.CENTER);

    // Sub elements to allActions
    ListView<Action> actionsListView = new ListView<>();
    if (linkSelected) {
      actionsListView.getItems().addAll(storyEditSession.getSelectedLink().getActions());
    }
    String allActionsTooltip = """
        To edit an action, select it from the list of actions bellow.
        To create a new action, click the "New Action" button.
        To delete an action, select it and click the "Delete" button.
        """;
    VBox allActions;
    allActions = SceneStyling.createContainerWithListView(actionsListView, "All Actions",
        "This Link has no Actions", allActionsTooltip, root);

    VBox linkEditor = new VBox();
    linkEditor.getChildren().addAll(textInputFields, allActions);
    linkEditor.setSpacing(10);
    linkEditor.setAlignment(Pos.CENTER);
    linkEditor.getStyleClass().add("passage-content-container");

    textInputFields.prefHeightProperty().bind(linkEditor.heightProperty().divide(5).multiply(2));
    allActions.prefHeightProperty().bind(linkEditor.heightProperty().divide(5).multiply(3));

    actionsListView.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          storyEditSession.setSelectedAction(newValue);
          updateLayout();
        });

    return linkEditor;
  }

  private VBox actionEditor() {
    VBox actionEditor = new VBox();

    // Main elements
    VBox newActions = new VBox();
    VBox editAction = new VBox();

    if (storyEditSession.getSelectedAction() instanceof InventoryAction && Objects.equals(
        ((InventoryAction) storyEditSession.getSelectedAction()).getItem(),
        "createNewActionUsingThisString")) {
      actionEditor.getChildren().add(newActions);
    } else {
      editAction = newActionContainer();
      actionEditor.getChildren().add(editAction);
    }
    actionEditor.setSpacing(10);
    actionEditor.setAlignment(Pos.CENTER);
    actionEditor.getStyleClass().add("passage-content-container");

    // Sub elements to newActions
    HBox goldAction = actionInputFieldContainer("Gold:",
        "example: +10, -5, 0", newGoldActionTextField, newGoldActionButton);
    HBox healthAction = actionInputFieldContainer("Health:",
        "example: +10, -5, 0", newHealthActionTextField, newHealthActionButton);
    HBox scoreAction = actionInputFieldContainer("Score:",
        "example: +10, -5, 0", newScoreActionTextField, newScoreActionButton);
    HBox inventoryAction = actionInputFieldContainer("Inventory:",
        "example: sword, axe, bird", newInventoryActionTextField, newInventoryActionButton);

    newActions.getChildren().addAll(goldAction, healthAction, scoreAction, inventoryAction);
    newActions.setSpacing(10);
    newActions.setAlignment(Pos.CENTER);
    newActions.getStyleClass().add("passage-content-container");

    newActions.prefHeightProperty().bind(actionEditor.heightProperty());
    newActions.prefWidthProperty().bind(actionEditor.widthProperty());

    editAction.prefHeightProperty().bind(actionEditor.heightProperty());
    editAction.prefWidthProperty().bind(actionEditor.widthProperty());

    return actionEditor;
  }

  private VBox newActionContainer() {
    VBox editAction = new VBox();

    if (storyEditSession.getSelectedAction() instanceof GoldAction) {
      editActionTextField.setText(
          String.valueOf(((GoldAction) storyEditSession.getSelectedAction()).getGold()));
    } else if (storyEditSession.getSelectedAction() instanceof HealthAction) {
      editActionTextField.setText(
          String.valueOf(((HealthAction) storyEditSession.getSelectedAction()).getHealth()));
    } else if (storyEditSession.getSelectedAction() instanceof ScoreAction) {
      editActionTextField.setText(
          String.valueOf(((ScoreAction) storyEditSession.getSelectedAction()).getScore()));
    } else if (storyEditSession.getSelectedAction() instanceof InventoryAction) {
      editActionTextField.setText(
          ((InventoryAction) storyEditSession.getSelectedAction()).getItem());
    } else {
      throw new RuntimeException("Action type not recognized");
    }

    editAction.getChildren()
        .addAll(editActionTextField, editActionSaveButton, editActionCancelButton,
            editActionDeleteButton);
    editAction.setSpacing(10);
    editAction.setAlignment(Pos.CENTER);
    editAction.getStyleClass().add("passage-content-container");

    editActionSaveButton.prefWidthProperty().bind(editAction.widthProperty());
    editActionCancelButton.prefWidthProperty().bind(editAction.widthProperty());
    editActionDeleteButton.prefWidthProperty().bind(editAction.widthProperty());

    return editAction;

  }

  private VBox libraryContainer(Double size) {

    ListView<Story> libraryTable = new ListView<>();
    libraryTable.setPlaceholder(new Label("No stories in library"));

    List<File> storyFiles = StoryFileHandler.getListOfStoryFiles();
    storyFiles.forEach(file -> {
      Story story = null;
      try {
        story = StoryFileHandler.loadStoryFromFile(file);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
      libraryTable.getItems().add(story);
    });

    libraryTable.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          storyEditSession.setSelectedStory(newValue);
          updateLayout();
        });

    Label libraryHeadlineLabel = new Label("Library");
    SceneStyling.bindFontSize(libraryHeadlineLabel, size, root);

    VBox libraryContainer = new VBox();
    libraryContainer.getChildren().addAll(libraryHeadlineLabel, libraryTable);
    libraryContainer.setSpacing(20);
    libraryContainer.setAlignment(Pos.CENTER);
    libraryContainer.getStyleClass().add("passage-content-container");

    return libraryContainer;
  }

  private HBox editButtonsContainer(Button button1, Button button2, Button button3, Button button4,
      Button button5) {
    HBox editButtonsContainer = new HBox();

    editButtonsContainer.getChildren().addAll(button1, button2, button3, button4, button5);
    editButtonsContainer.setSpacing(5);
    editButtonsContainer.setAlignment(Pos.CENTER);

    button1.prefWidthProperty().bind(editButtonsContainer.widthProperty().divide(5));
    button2.prefWidthProperty().bind(editButtonsContainer.widthProperty().divide(5));
    button3.prefWidthProperty().bind(editButtonsContainer.widthProperty().divide(5));
    button4.prefWidthProperty().bind(editButtonsContainer.widthProperty().divide(5));
    button5.prefWidthProperty().bind(editButtonsContainer.widthProperty().divide(5));

    return editButtonsContainer;
  }

  private VBox inputFieldContainer(String headline, TextArea textArea, String content) {

    Label inputFieldHeadlineLabel = new Label(headline);
    SceneStyling.bindFontSize(inputFieldHeadlineLabel, 0.01, root);

    textArea.setWrapText(true);

    StringProperty passageTextProperty = new SimpleStringProperty(content);
    textArea.textProperty().bindBidirectional(passageTextProperty);

    VBox inputFieldContainer = new VBox();
    inputFieldContainer.getChildren().addAll(inputFieldHeadlineLabel, textArea);
    inputFieldContainer.setSpacing(2);
    inputFieldContainer.setAlignment(Pos.CENTER);
    inputFieldContainer.getStyleClass().add("passage-content-container");

    return inputFieldContainer;
  }

  private HBox actionInputFieldContainer(String labelText, String promptText,
      TextField textField, Button button) {

    textField.setPromptText(promptText);
    VBox textFieldAndButton = new VBox();
    textFieldAndButton.getChildren().addAll(textField, button);
    textFieldAndButton.setSpacing(5);
    textFieldAndButton.setAlignment(Pos.CENTER);

    textField.prefWidthProperty().bind(textFieldAndButton.widthProperty());
    button.prefWidthProperty().bind(textFieldAndButton.widthProperty());

    textField.prefHeightProperty().bind(textFieldAndButton.heightProperty().divide(2));
    button.prefHeightProperty().bind(textFieldAndButton.heightProperty().divide(2));

    textField.setOnKeyPressed(event -> {
      if (event.getCode() == KeyCode.ENTER) {
        button.fire();
      }
    });

    HBox actionInputFieldContainer = new HBox();
    Label inputFieldHeadlineLabel = new Label(labelText);
    actionInputFieldContainer.getChildren().addAll(inputFieldHeadlineLabel, textFieldAndButton);
    actionInputFieldContainer.setAlignment(Pos.CENTER);
    actionInputFieldContainer.getStyleClass().add("passage-content-container");

    SceneStyling.bindFontSize(inputFieldHeadlineLabel, 0.008, root);
    SceneStyling.bindFontSize(button, 0.008, root);

    inputFieldHeadlineLabel.prefWidthProperty()
        .bind(actionInputFieldContainer.widthProperty().divide(5).multiply(2));
    textFieldAndButton.prefWidthProperty()
        .bind(actionInputFieldContainer.widthProperty().divide(5).multiply(3));

    return actionInputFieldContainer;
  }


  public Button getEditOpeningPassageButton() {
    return editOpeningPassageButton;
  }
}
