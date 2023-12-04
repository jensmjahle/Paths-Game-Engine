package edu.ntnu.idatt2001.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a chapter of a story. Chapters/passages are connected to each other with a link (class
 * Link).
 */
public class Passage implements Serializable {

  private String title;
  private String content;
  private final List<Link> links;

  /**
   * Creates an instance of a passage.
   *
   * @param title   A title used to identify a passage. As a String.
   * @param content A text that's a dialog or the content of the passage. As a String.
   */
  public Passage(String title, String content) {
    this.title = title;
    this.content = content;
    this.links = new ArrayList<>();
  }

  /**
   * Retrieves the title of this passage.
   *
   * @return The title of a passage. As a String.
   */
  public String getTitle() {
    return title;
  }

  /**
   * Retrieves the content of this passage.
   *
   * @return The content of a passage. As a String.
   */
  public String getContent() {
    return content;
  }

  /**
   * Adds a link to this passage if the link is not already in the passage.
   *
   * @param link The link to be added to the passage.
   */
  public boolean addLink(Link link) {
    if (links.contains(link)) {
      throw new IllegalArgumentException(
          "Cannot add link to passage. This passage already contains this link.");
    } else {
      return links.add(link);
    }
  }

  /**
   * Retrieves a list of all the links this passage contains.
   *
   * @return All the links the chosen passage contains.
   */
  public List<Link> getLinks() {
    return links;
  }

  /**
   * Checks if this passage contains 1 or more links.
   *
   * @return True if the passage contains 1 or more links. False otherwise.
   */
  public boolean hasLinks() {
    return !links.isEmpty();
  }

  /**
   * Retrieves a visual representation of this passage with all its content.
   *
   * @return All the information stored in a passage. As a String.
   */
  @Override
  public String toString() {
    return title.toUpperCase()
        + ", Content: '" + content + '\'';

  }

  /**
   * Compares this Passage object to another object to determine if they are equal. Two objects are
   * considered equal if they are of the same class and have the same title, content, and links.
   *
   * @param o The object to compare to this Passage.
   * @return True if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Passage passage = (Passage) o;
    return Objects.equals(title, passage.title) && Objects.equals(content, passage.content)
        && Objects.equals(links, passage.links);
  }

  /**
   * Generates a hash code for this Passage object. The hash code is generated based on the title,
   * content and links of this Passage object.
   *
   * @return A hash code for this Passage object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(title, content, links);
  }

  public void setTitle(String text) {
    this.title = text;
  }

  /**
   * Sets the content of this passage.
   *
   * @param text The new content of this passage.
   */
  public void setContent(String text) {
    this.content = text;
  }

  /**
   * Removes a link from this passage.
   *
   * @param link The link to be removed.
   */
  public void removeLink(Link link) {
    links.remove(link);
  }
}
