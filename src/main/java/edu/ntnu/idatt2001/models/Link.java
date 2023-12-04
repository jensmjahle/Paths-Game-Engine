package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.actions.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Acts as a connection between passages (class Passage) that make it possible to move from one
 * passage to another.
 */
public class Link implements Serializable {

  private final String text;
  private final String reference;
  private final List<Action> actions;

  /**
   * Creates a connection/link between passages (class Passage).
   *
   * @param text      Describes a choice or action a player can choose from in a story. As a
   *                  String.
   * @param reference A unique identification that refers to a specific passage (class Passage). As
   *                  a String.
   */
  public Link(String text, String reference) {
    this.text = text;
    this.reference = reference;
    this.actions = new ArrayList<>();
  }

  /**
   * Retrieves the text of a link.
   *
   * @return The parameter text. As a String.
   */
  public String getText() {
    return this.text;
  }

  /**
   * Retrieves the reference to a specific passage.
   *
   * @return The parameter reference. As a String.
   */
  public String getReference() {
    return this.reference;
  }

  /**
   * Adds an action from the class Action to the list actions.
   *
   * @param action An action from the class Action.
   */

  public void addAction(Action action) {
    actions.add(action);
  }

  /**
   * Retrieves all the actions a link contains.
   *
   * @return A list of actions.
   */
  public List<Action> getActions() {
    return this.actions;
  }

  /**
   * Retrieves a visual representation of a link with all its information.
   *
   * @return A visual representation of a link. As a String.
   */
  @Override
  public String toString() {
    return "'" + text + '\''
        + ", Reference: '" + reference + '\''
        + ", Actions:" + actions;
  }

  /**
   * Compares this Link object with the specified object for equality.
   *
   * @param o the object to be compared with this Link object for equality.
   * @return true if the specified object is equal to this Link object, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return Objects.equals(reference, link.reference);
  }

  /**
   * Retrieves a hash code value for this Link object.
   *
   * @return A hash code value for this Link object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(reference);
  }

  /**
   * Removes an action from the list actions.
   *
   * @param action An action from the class Action.
   */
  public void removeAction(Action action) {
    actions.remove(action);
  }
}
