package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.actions.Action;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 * A class to represent the story edit session.
 */
public class StoryEditSession {
  private Story selectedStory;
  private final ObjectProperty<Story> selectedStoryProperty;
  private Passage selectedPassage;
  private final ObjectProperty<Passage> selectedPassageProperty;
  private Link selectedLink;
  private final ObjectProperty<Link> selectedLinkProperty;
  private Action selectedAction;
  private final ObjectProperty<Action> selectedActionProperty;

  /**
   * Constructor for the StoryEditSession class.
   */
  public StoryEditSession() {
    selectedStory = null;
    selectedPassage = null;
    selectedLink = null;
    selectedAction = null;

    selectedStoryProperty = new SimpleObjectProperty<>();
    selectedPassageProperty = new SimpleObjectProperty<>();
    selectedLinkProperty = new SimpleObjectProperty<>();
    selectedActionProperty = new SimpleObjectProperty<>();
  }

  /**
   * Method to get the selected story.
   *
   * @return The selected story.
   */
  public Story getSelectedStory() {
    return selectedStory;
  }

  /**
   * Method to set the selected story.
   */
  public void setSelectedStory(Story selectedStory) {
    this.selectedStory = selectedStory;
    selectedStoryProperty.set(selectedStory);
  }

  /**
   * Method to get the selected passage property.
   *
   * @return The selected passage property.
   */
  public Passage getSelectedPassage() {
    return selectedPassage;
  }

  /**
   * Method to set the selected passage property.
   */
  public void setSelectedPassage(Passage selectedPassage) {
    this.selectedPassage = selectedPassage;
    selectedPassageProperty.set(selectedPassage);
  }

  /**
   * Method to get the selected link property.
   *
   * @return The selected link property.
   */
  public Link getSelectedLink() {
    return selectedLink;
  }

  /**
   * Method to set the selected link property.
   */
  public void setSelectedLink(Link selectedLink) {
    this.selectedLink = selectedLink;
    selectedLinkProperty.set(selectedLink);
  }

  /**
   * Method to get the selected action property.
   *
   * @return The selected action property.
   */
  public Action getSelectedAction() {
    return selectedAction;
  }

  /**
   * Method to set the selected action property.
   */
  public void setSelectedAction(Action selectedAction) {
    this.selectedAction = selectedAction;
    selectedActionProperty.set(selectedAction);
  }

  /**
   * Method to check if the selected story is null.
   *
   * @return True if the selected story is null, false otherwise.
   */
  public ObjectProperty<Story> getSelectedStoryProperty() {
    return selectedStoryProperty;
  }

  /**
   * Method to check if the selected passage is null.
   *
   * @return True if the selected passage is null, false otherwise.
   */
  public ObjectProperty<Passage> getSelectedPassageProperty() {
    return selectedPassageProperty;
  }

  /**
   * Method to check if the selected link is null.
   *
   * @return True if the selected link is null, false otherwise.
   */
  public ObjectProperty<Link> getSelectedLinkProperty() {
    return selectedLinkProperty;
  }

  /**
   * Method to check if the selected action is null.
   *
   * @return True if the selected action is null, false otherwise.
   */
  public ObjectProperty<Action> getSelectedActionProperty() {
    return selectedActionProperty;
  }

  /**
   * Method to clear all the selected objects.
   */
  public void clearAll() {
    selectedStory = null;
    selectedPassage = null;
    selectedLink = null;
    selectedAction = null;
    selectedStoryProperty.set(null);
    selectedPassageProperty.set(null);
    selectedLinkProperty.set(null);
    selectedActionProperty.set(null);
  }
}
