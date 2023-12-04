package edu.ntnu.idatt2001.controllers;

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
import edu.ntnu.idatt2001.paths.PathsApplication;
import edu.ntnu.idatt2001.scenemanagement.SceneEnum;
import edu.ntnu.idatt2001.scenemanagement.SceneStyling;
import edu.ntnu.idatt2001.view.EditStoryView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Class to handle the editing of stories.
 */
public class EditStoryController implements SceneController {

  private final EditStoryView editStoryView;
  private final StoryEditSession storyEditSession;
  private final TextArea storyTitleTextArea;
  private final TextArea passageTitleTextArea;
  private final TextArea passageContentTextArea;
  private final TextArea linkTextTextArea;
  private final TextArea linkReferenceTextArea;
  private final TextField editActionTextField;
  private final TextField newHealthActionTextField;
  private final TextField newScoreActionTextField;
  private final TextField newInventoryActionTextField;
  private final TextField newGoldActionTextField;

  /**
   * Constructor for the EditStoryController.
   */
  public EditStoryController() {
    storyEditSession = new StoryEditSession();
    editStoryView = new EditStoryView(storyEditSession);

    storyTitleTextArea = editStoryView.getStoryTitleTextArea();
    passageTitleTextArea = editStoryView.getPassageTitleTextArea();
    passageContentTextArea = editStoryView.getPassageContentTextArea();
    linkTextTextArea = editStoryView.getLinkTextTextArea();
    linkReferenceTextArea = editStoryView.getLinkReferenceTextArea();
    editActionTextField = editStoryView.getEditActionTextField();
    newHealthActionTextField = editStoryView.getNewHealthActionTextField();
    newScoreActionTextField = editStoryView.getNewScoreActionTextField();
    newInventoryActionTextField = editStoryView.getNewInventoryActionTextField();
    newGoldActionTextField = editStoryView.getNewGoldActionTextField();

    initializeButtons();
    initializeListeners();
  }

  /**
   * Method to initialize the listeners of the controller.
   */
  public void initializeListeners() {

    ObjectProperty<Story> story = storyEditSession.getSelectedStoryProperty();
    story.addListener((observable, oldValue, newValue) -> {
      editStoryView.updateLayout();
    });
    ObjectProperty<Passage> passage = storyEditSession.getSelectedPassageProperty();
    passage.addListener((observable, oldValue, newValue) -> {
      editStoryView.updateLayout();
    });
    ObjectProperty<Link> link = storyEditSession.getSelectedLinkProperty();
    link.addListener((observable, oldValue, newValue) -> {
      editStoryView.updateLayout();
    });
    ObjectProperty<Action> action = storyEditSession.getSelectedActionProperty();
    action.addListener((observable, oldValue, newValue) -> {
      editStoryView.updateLayout();
    });

  }

  /**
   * Method to get the buttons from the view class and initialize them.
   */
  public void initializeButtons() {

    Button backToMainMenuButton = editStoryView.getBackToMainMenuButton();
    backToMainMenuButton.setOnAction(e -> PathsApplication
            .getSceneManager().navigateTo(SceneEnum.MAIN_MENU));
    Button addStoryToLibraryButton = editStoryView.getAddStoryToLibraryButton();
    addStoryToLibraryButton.setOnAction(e -> addStoryToLibrary());
    Button saveStoryButton = editStoryView.getSaveStoryButton();
    saveStoryButton.setOnAction(e -> saveStoryToFile());
    Button deleteStoryButton = editStoryView.getDeleteStoryButton();
    deleteStoryButton.setOnAction(e -> deleteStoryFile());
    Button createNewStoryButton = editStoryView.getCreateNewStoryButton();
    createNewStoryButton.setOnAction(e -> createNewStory());
    Button addPassageButton = editStoryView.getAddPassageButton();
    addPassageButton.setOnAction(e -> creteNewPassage());
    Button deletePassageButton = editStoryView.getDeletePassageButton();
    deletePassageButton.setOnAction(e -> deletePassage());
    Button addLinkButton = editStoryView.getAddLinkButton();
    addLinkButton.setOnAction(e -> createNewLink());
    Button deleteLinkButton = editStoryView.getDeleteLinkButton();
    deleteLinkButton.setOnAction(e -> deleteLink());
    Button addActionToLinkButton = editStoryView.getAddActionToLinkButton();
    addActionToLinkButton.setOnAction(e -> createNewAction());
    Button deleteActionToLinkButton = editStoryView.getDeleteActionToLinkButton();
    deleteActionToLinkButton.setOnAction(e -> deleteAction());
    Button cancelLinkButton = editStoryView.getCancelLinkButton();
    cancelLinkButton.setOnAction(e -> editStoryView.updateLayout());
    Button saveChangesToStoryButton = editStoryView.getSaveChangesToStoryButton();
    saveChangesToStoryButton.setOnAction(e -> saveChangesInStory());
    Button newActionButton = editStoryView.getNewActionButton();
    newActionButton.setOnAction(e -> newAction());
    Button goBackToPassageFromLinkButton = editStoryView.getGoBackToPassageFromLinkButton();
    goBackToPassageFromLinkButton.setOnAction(e -> goBackToPassageFromLink());
    Button savePassageButton = editStoryView.getSavePassageButton();
    savePassageButton.setOnAction(e -> savePassageToStory());
    Button cancelPassageButton = editStoryView.getCancelPassageButton();
    cancelPassageButton.setOnAction(e -> editStoryView.updateLayout());
    Button goBackToStoryFromPassageButton = editStoryView.getGoBackToStoryFromPassageButton();
    goBackToStoryFromPassageButton.setOnAction(e -> goBackToStoryFromPassage());
    Button editActionSaveButton = editStoryView.getEditActionSaveButton();
    editActionSaveButton.setOnAction(e -> saveChangesInAction());
    Button editActionCancelButton = editStoryView.getEditActionCancelButton();
    editActionCancelButton.setOnAction(e -> goBackToLinkFormAction());
    Button editActionDeleteButton = editStoryView.getEditActionDeleteButton();
    editActionDeleteButton.setOnAction(e -> deleteAction());
    Button newHealthActionButton = editStoryView.getNewHealthActionButton();
    newHealthActionButton.setOnAction(e -> createNewHealthAction());
    Button newGoldActionButton = editStoryView.getNewGoldActionButton();
    newGoldActionButton.setOnAction(e -> createNewGoldAction());
    Button newScoreActionButton = editStoryView.getNewScoreActionButton();
    newScoreActionButton.setOnAction(e -> createNewScoreAction());
    Button newInventoryActionButton = editStoryView.getNewInventoryActionButton();
    newInventoryActionButton.setOnAction(e -> createNewInventoryAction());
    Button editOpeningPassageButton = editStoryView.getEditOpeningPassageButton();
    editOpeningPassageButton.setOnAction(e -> storyEditSession.setSelectedPassage(
        storyEditSession.getSelectedStory().getOpeningPassage()));
    Button saveLinkButton = editStoryView.getSaveLinkButton();
    saveLinkButton.setOnAction(e -> saveLink());

  }

  /**
   * Method to save a link to the passage that is being edited.
   */
  private void saveLink() {
    Passage selectedPassage = storyEditSession.getSelectedPassage();
    Link selectedLink = storyEditSession.getSelectedLink();
    if (selectedLink != null) {
      List<Action> actions = selectedLink.getActions();
      String text = linkTextTextArea.getText();
      String reference = linkReferenceTextArea.getText();
      Link newLink = new Link(text, reference);
      actions.forEach(newLink::addAction);
      selectedPassage.removeLink(selectedLink);
      selectedPassage.addLink(newLink);
      storyEditSession.setSelectedPassage(selectedPassage);
      goBackToPassageFromLink();
      SceneStyling.showNotification("Link saved",
          "The link has been saved to the passage",
          (Pane) editStoryView.getRoot());
    } else {
      SceneStyling.showNotification("No link detected",
          "Please select a link to save",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Method to create new action for inventory.
   */
  private void createNewInventoryAction() {
    if (newInventoryActionTextField.getText().isEmpty()) {
      SceneStyling.showNotification("No Action Detected",
          "Please enter a value for the inventory action",
          (Pane) editStoryView.getRoot());
    } else {
      try {
        InventoryAction inventoryAction = new InventoryAction(
            newInventoryActionTextField.getText());
        storyEditSession.getSelectedLink().addAction(inventoryAction);
        newInventoryActionTextField.clear();
        goBackToLinkFormAction();
        SceneStyling.showNotification("Inventory Action Created",
            "The inventory action has been created",
            (Pane) editStoryView.getRoot());
      } catch (NumberFormatException e) {
        SceneStyling.showNotification("Invalid Input",
            "Please enter a valid number for the inventory action",
            (Pane) editStoryView.getRoot());
      }
    }
  }

  /**
   * Method to create new action for score.
   */
  private void createNewScoreAction() {
    if (newScoreActionTextField.getText().isEmpty()) {
      SceneStyling.showNotification("No Action Detected",
          "Please enter a value for the score action",
          (Pane) editStoryView.getRoot());
    } else {
      try {
        ScoreAction scoreAction = new ScoreAction(
            Integer.parseInt(newScoreActionTextField.getText()));
        storyEditSession.getSelectedLink().addAction(scoreAction);
        newScoreActionTextField.clear();
        goBackToLinkFormAction();
        SceneStyling.showNotification("Score Action Created",
            "The score action has been created",
            (Pane) editStoryView.getRoot());
      } catch (NumberFormatException e) {
        SceneStyling.showNotification("Invalid Input",
            "Please enter a valid number for the score action",
            (Pane) editStoryView.getRoot());
      }
    }
  }

  /**
   * Method to create new action for gold.
   */
  private void createNewGoldAction() {
    if (newGoldActionTextField.getText().isEmpty()) {
      SceneStyling.showNotification("No Action Detected",
          "Please enter a value for the gold action",
          (Pane) editStoryView.getRoot());
    } else {
      try {
        GoldAction goldAction = new GoldAction(
            Integer.parseInt(newGoldActionTextField.getText()));
        storyEditSession.getSelectedLink().addAction(goldAction);
        newGoldActionTextField.clear();
        goBackToLinkFormAction();
        SceneStyling.showNotification("Gold Action Created",
            "The gold action has been created",
            (Pane) editStoryView.getRoot());
      } catch (NumberFormatException e) {
        SceneStyling.showNotification("Invalid Input",
            "Please enter a valid number for the gold action",
            (Pane) editStoryView.getRoot());
      }
    }
  }

  /**
   * Method to create new action for health.
   */
  private void createNewHealthAction() {
    if (newHealthActionTextField.getText().isEmpty()) {
      SceneStyling.showNotification("No Action Detected",
          "Please enter a value for the health action",
          (Pane) editStoryView.getRoot());
    } else {
      try {
        HealthAction healthAction = new HealthAction(
            Integer.parseInt(newHealthActionTextField.getText()));
        newHealthActionTextField.clear();
        storyEditSession.getSelectedLink().addAction(healthAction);
        goBackToLinkFormAction();
        SceneStyling.showNotification("Health Action Created",
            "The health action has been created",
            (Pane) editStoryView.getRoot());
      } catch (NumberFormatException e) {
        SceneStyling.showNotification("Invalid Input",
            "Please enter a valid number for the health action",
            (Pane) editStoryView.getRoot());
      }
    }
  }

  /**
   * Method to save all changes in the action.
   */
  private void saveChangesInAction() {
    Link selectedLink = storyEditSession.getSelectedLink();
    Action selectedAction = storyEditSession.getSelectedAction();
    Action action;
    try {
      if (selectedAction != null) {
        if (selectedAction instanceof GoldAction) {
          action = (new GoldAction(Integer.parseInt(editActionTextField.getText())));
        } else if (selectedAction instanceof HealthAction) {
          action = (new HealthAction(Integer.parseInt(editActionTextField.getText())));
        } else if (selectedAction instanceof ScoreAction) {
          action = (new ScoreAction(Integer.parseInt(editActionTextField.getText())));
        } else if (selectedAction instanceof InventoryAction) {
          action = (new InventoryAction(editActionTextField.getText()));
        } else {
          throw new RuntimeException("Action type not recognized");
        }
        selectedLink.removeAction(selectedAction);
        selectedLink.addAction(action);
        storyEditSession.setSelectedLink(selectedLink);
        goBackToLinkFormAction();
        SceneStyling.showNotification("Action saved", "Action saved successfully",
            (Pane) editStoryView.getRoot());
      }
    } catch (NumberFormatException e) {
      editStoryView.updateLayout();
      SceneStyling.showNotification("Invalid input", "Please enter a valid number",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Method to return to story from the passage that is being edited.
   */
  private void goBackToStoryFromPassage() {
    Story selectedStory = storyEditSession.getSelectedStory();
    storyEditSession.setSelectedStory(null);
    storyEditSession.setSelectedStory(selectedStory);
  }


  /**
   * Sets the selected link to null. This will make the view go back to the passage view.
   */
  private void goBackToPassageFromLink() {
    Passage selectedPassage = storyEditSession.getSelectedPassage();
    storyEditSession.setSelectedPassage(null);
    storyEditSession.setSelectedPassage(selectedPassage);
  }

  /**
   * Creates a new action and sets it as the selected action in the story edit session.
   */
  private void newAction() {
    storyEditSession.setSelectedAction(new InventoryAction("createNewActionUsingThisString"));

  }

  /**
   * Saves the title of the story from the text area to the selected story in the story edit
   * session.
   */
  private void saveChangesInStory() {
    Story selectedStory = storyEditSession.getSelectedStory();
    if (selectedStory != null) {
      String title = storyTitleTextArea.getText();
      selectedStory.setTitle(title);
      SceneStyling.showNotification("Story saved",
          "The story has been saved",
          (Pane) editStoryView.getRoot());
    } else {
      SceneStyling.showNotification("No story selected",
          "You need to select a story before you can save it",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Saves the changes to the selected passage in the story edit session to the selected story. If
   * no passage is selected, a notification is shown.
   */
  private void savePassageToStory() {
    Story selectedStory = storyEditSession.getSelectedStory();
    Passage selectedPassage = storyEditSession.getSelectedPassage();
    if (selectedPassage != null) {
      List<Link> links = selectedPassage.getLinks();
      String title = passageTitleTextArea.getText();
      String content = passageContentTextArea.getText();
      Passage newPassage = new Passage(title, content);
      links.forEach(newPassage::addLink);
      selectedStory.removePassage(new Link(selectedPassage.getTitle(), selectedPassage.getTitle()));
      selectedStory.addPassage(newPassage);
      storyEditSession.setSelectedStory(selectedStory);
      goBackToStoryFromPassage();
      SceneStyling.showNotification("Passage saved",
          "The passage has been saved to the story",
          (Pane) editStoryView.getRoot());
    } else {
      SceneStyling.showNotification("No passage selected",
          "Please select a passage to save",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Deletes the selected action in the story edit session from the selected link.
   */
  private void deleteAction() {
    Action selectedAction = storyEditSession.getSelectedAction();
    Link selectedLink = storyEditSession.getSelectedLink();
    if (selectedAction != null && selectedLink != null) {
      selectedLink.removeAction(selectedAction);
      storyEditSession.setSelectedLink(selectedLink);
      goBackToLinkFormAction();
    } else {
      SceneStyling.showNotification("No action selected",
          "Please select an action to delete",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Return from action that is being edited to the link.
   */
  private void goBackToLinkFormAction() {
    Link selectedLink = storyEditSession.getSelectedLink();
    storyEditSession.setSelectedLink(null);
    storyEditSession.setSelectedLink(selectedLink);
  }


  /**
   * Creates a new action and sets it as the selected action in the story edit session.
   */
  private void createNewAction() {
    Action newAction = new InventoryAction("createNewActionUsingThisString");
    storyEditSession.setSelectedAction(newAction);
  }

  /**
   * Deletes the selected link in the story edit session from the selected passage. If no link is
   * selected, a notification is shown.
   */
  private void deleteLink() {
    Link selectedLink = storyEditSession.getSelectedLink();
    Passage selectedPassage = storyEditSession.getSelectedPassage();
    if (selectedLink != null && selectedPassage != null) {
      selectedPassage.removeLink(selectedLink);
      storyEditSession.setSelectedPassage(selectedPassage);
      goBackToPassageFromLink();
      SceneStyling.showNotification("Link deleted",
          "The link has been deleted",
          (Pane) editStoryView.getRoot());
    } else {
      SceneStyling.showNotification("No link selected",
          "Please select a link to delete",
          (Pane) editStoryView.getRoot());
    }
  }

  /**
   * Creates a new link and sets it as the selected link in the story edit session.
   */
  private void createNewLink() {
    Link newLink = new Link("New Link text", "New Link reference\nShould be a passage title!");
    storyEditSession.setSelectedLink(newLink);
  }

  /**
   * Creates the layout for the edit story view.
   *
   * @return the layout for the edit story view.
   */
  @Override
  public Parent createLayout() {
    return editStoryView.getRoot();
  }

  /**
   * Saves the selected story from the story edit session to a file. If the file does not exist, it
   * will be created. If the file does exist, it will be overwritten. If the file cannot be created,
   * a runtime exception will be thrown. If the file cannot be overwritten, a runtime exception will
   * be thrown.
   */
  public void saveStoryToFile() {
    Story story = storyEditSession.getSelectedStory();
    File folderPath = new File(
            System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);
    String fileName = story.getTitle() + ".paths";
    String filePath = folderPath + File.separator + fileName;


    if (!folderPath.exists()) {
      folderPath.mkdirs();
    }

    File file = new File(filePath);
    if (!file.exists()) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Could not create file");
      }
    }
    try {
      StoryFileHandler.saveStoryToFile(story, file);
      SceneStyling.showNotification(story.getTitle() + " saved", "Story saved successfully",
          (Pane) editStoryView.getRoot());
      storyEditSession.clearAll();
      editStoryView.updateLayout();
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Could not save story to file");
    }

  }

  /**
   * Saves a selected .paths file from the file explorer to the stories resource folder. If the file
   * cannot be saved, a runtime exception will be thrown. If the file cannot be copied, a runtime
   * exception will be thrown. If the file already exists, it will be overwritten. If the file
   * cannot be overwritten, a runtime exception will be thrown.
   */
  private void addStoryToLibrary() {
    File selectedFile = openFileChooser();
    if (selectedFile != null) {
      File folderPath = new File(
              System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);
      File destinationFolder = new File(folderPath.toString());

      if (!destinationFolder.exists()) {
        destinationFolder.mkdirs();
      }

      try {
        String fileName = selectedFile.getName();
        Path destinationPath = destinationFolder.toPath().resolve(fileName);
        Files.copy(selectedFile.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
        SceneStyling.showNotification("Story added to library", "Story added successfully",
            (Pane) editStoryView.getRoot());
      } catch (IOException e) {
        e.printStackTrace();
        throw new RuntimeException("Failed to save file to the stories directory");
      }
    }
  }

  /**
   * Opens a file chooser for the user to select a .paths file.
   *
   * @return the selected file
   */
  private File openFileChooser() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Open File");
    fileChooser.getExtensionFilters()
        .add(new FileChooser.ExtensionFilter("PATHS Files", "*.paths"));

    Stage stage = (Stage) editStoryView.getRoot().getScene().getWindow();
    return fileChooser.showOpenDialog(stage);
  }

  /**
   * Deletes the selected story from editStorySession and deletes the corresponding .paths file from
   * the stories resource folder. If the file cannot be deleted, a runtime exception will be thrown.
   * If the file does not exist, a runtime exception will be thrown. If the file is not a file, a
   * runtime exception will be thrown. If the file is not a directory, a runtime exception will be
   * thrown.
   */
  private void deleteStoryFile() {
    String folderPath = new String(
            System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);
    File folder = new File(folderPath);
    Story story = storyEditSession.getSelectedStory();

    if (!folder.exists() || !folder.isDirectory()) {
      throw new RuntimeException("Stories folder does not exist or is not a directory");
    }

    String storyTitle = story.getTitle();
    File[] files = folder.listFiles();

    if (files != null) {
      for (File file : files) {
        if (file.isFile() && file.getName().equals(storyTitle + ".paths")) {
          Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
          confirmationAlert.setTitle("Delete Story");
          confirmationAlert.setHeaderText("Are you sure you want to delete this story?\n"
              + "This action cannot be undone.");
          confirmationAlert.setContentText("Story: " + storyTitle);

          confirmationAlert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
              try {
                Files.delete(file.toPath());
                SceneStyling.showNotification("Story deleted", "Story file deleted successfully",
                    (Pane) editStoryView.getRoot());
                storyEditSession.clearAll();
              } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Failed to delete story file: " + file.getName());
              }
            }
          });

          return; // Exit the method after showing the confirmation alert
        }
      }
    }

    throw new RuntimeException("Story file not found: " + storyTitle);
  }

  /**
   * Creates a new story and sets it as the selected story in the story edit session.
   */
  private void createNewStory() {
    Passage passage = new Passage("Example Title", "Example Content");
    Story story = new Story("Example Story", passage);
    storyEditSession.setSelectedStory(story);
    editStoryView.updateLayout();
  }

  /**
   * Creates a new passage and sets it as the selected passage in the story edit session.
   */
  private void creteNewPassage() {
    Passage passage = new Passage("New Passage", "New Passage Content");
    storyEditSession.setSelectedPassage(passage);
    storyEditSession.getSelectedStory().addPassage(passage);
  }

  /**
   * Deletes the selected passage in story edit session from the selected story in story edit
   * session. If there is no selected passage, nothing happens.
   */
  private void deletePassage() {
    Passage passage = storyEditSession.getSelectedPassage();
    if (passage != null) {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
      alert.setTitle("Delete Passage");
      alert.setHeaderText("Are you sure you want to delete this passage?");
      alert.setContentText("This action cannot be undone.");
      Optional<ButtonType> result = alert.showAndWait();
      if (result.isPresent() && result.get() == ButtonType.OK) {
        Story story = storyEditSession.getSelectedStory();
        story.getPassages().remove(passage);
        storyEditSession.clearAll();
        storyEditSession.setSelectedStory(story);
        editStoryView.updateLayout();
        SceneStyling.showNotification("Passage deleted from the story",
            "Passage deleted successfully.",
            (Pane) editStoryView.getRoot());
      }
    }
  }

}

