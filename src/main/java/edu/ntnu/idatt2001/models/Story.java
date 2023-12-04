package edu.ntnu.idatt2001.models;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents an interactive, non-linear narrative witch is build up by a collection of
 * passages(class Passage). The passages are connected with links(class Link).
 */
public class Story implements Serializable {

  private final Map<Link, Passage> passages;
  private final Passage openingPassage;
  private String title;

  /**
   * Creates a new story.
   *
   * @param title          The title of the story.
   * @param openingPassage The first passage in a story.
   */
  public Story(String title, Passage openingPassage) {
    this.title = title;
    this.openingPassage = openingPassage;
    this.passages = new HashMap<>();

  }

  /**
   * Retrieves the title of this story.
   *
   * @return The title of this story.
   */
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Retrieves the opening/first passage of this story.
   *
   * @return THe opening passage of this story.
   */
  public Passage getOpeningPassage() {
    return openingPassage;
  }

  /**
   * Adds a given passage to this story. It creates a new link from the passage title that connect
   * the passage to the rest of the story.
   *
   * @param passage The passage to be added to this story.
   */
  public void addPassage(Passage passage) {
    passages.put(new Link(passage.getTitle(), passage.getTitle()), passage);
  }

  /**
   * Retrieves a passage from a given link.
   *
   * @param link Acts as a key to the passage.
   * @return A passage from the given link.
   */
  public Passage getPassage(Link link) {
    return passages.get(link);
  }

  /**
   * Retrieves all passages in the story.
   *
   * @return A collection of all the passages in the story.
   */
  public Collection<Passage> getPassages() {
    return passages.values();
  }

  /**
   * Removes a given passage from passages. The method takes a link as a parameter and uses it to
   * find the passage to be removed. A passage can only be removed if it does not have any links
   * that refer to it.
   *
   * @param link The link of the passage to remove.
   * @return True if the passage was successfully removed, false otherwise.
   */
  public boolean removePassage(Link link) {

    boolean passageWithLinkExists = passages.keySet()
            .stream().anyMatch(link1 -> link1.equals(
                    link)); // Checks if there is a passage with the given link in the map.

    boolean hasLinksToPassage = passages.values().stream()
            .flatMap(p -> p.getLinks().stream())
            .anyMatch(link1 -> link1.equals(link));
    // Checks if the link exists in any of the passages.
    if (passageWithLinkExists && hasLinksToPassage) {
      return false;
    } else if (passageWithLinkExists) {
      passages.remove(link);
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the story has any broken links and returns a list of them. A broken link is a link
   * that does not refer to any passage in the story.
   *
   * @return A list of all the broken links in the story.
   */
  public List<Link> getBrokenLinks() {
    return passages.values().stream()
            .flatMap(p -> p.getLinks().stream())
            .filter(l -> !passages.containsKey(l))
            .collect(Collectors.toList());
  }


  /**
   * Method to return just the title of the story.
   *
   * @return title of the story
   */
  @Override
  public String toString() {
    return title;
  }
}
