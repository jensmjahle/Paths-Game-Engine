package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StoryTest {
  static Passage passageTest = new Passage("PassageTitleTest", "ContentTest");
  static Passage passageTest2 = new Passage("PassageTitleTest2", "ContentTest2");
  static Story storyTest = new Story("TitleTest", passageTest);

  @BeforeEach
  public void Setup() {
    storyTest.addPassage(passageTest);
    storyTest.addPassage(passageTest2);
  }

  @Test
  @DisplayName("Checks if getTitle() returns correct title")
  void getTitle() {
    assertEquals("TitleTest", storyTest.getTitle());
  }

  @Test
  @DisplayName("Checks if getOpeningPassage() returns correct passage")
  void getOpeningPassage() {
    assertEquals("PassageTitleTest", storyTest.getOpeningPassage().getTitle());
  }

  @Test
  @DisplayName("Checks if addPassage() adds passage to passages")
  void addPassage() {
    assertTrue(storyTest.getPassages().contains(passageTest));
    assertTrue(storyTest.getPassages().contains(passageTest2));
  }

  @Test
  @DisplayName("Checks if getPassage() returns correct passage")
  void getPassage() {
    assertEquals(passageTest2, storyTest.getPassage(new Link(passageTest2.getTitle(), passageTest2.getTitle())));
  }

  @Test
  @DisplayName("Checks if getPassages() returns correct passages")
  void getPassages() {
    assertTrue(storyTest.getPassages().contains(passageTest));
    assertTrue(storyTest.getPassages().contains(passageTest2));
  }

  @Test
  @DisplayName("Checks if removePassage() removes passage from passages")
  void removePassage() {
    storyTest.addPassage(passageTest);
    Link linkToRemove = new Link(passageTest.getTitle(), passageTest.getTitle());
    assertTrue(storyTest.removePassage(new Link(passageTest.getTitle(), passageTest.getTitle())));
    assertFalse(storyTest.getPassages().contains(passageTest));
  }

  @Test
  @DisplayName("Checks if getBrokenLinks() returns correct broken links")
  void getBrokenLinks() {
    Link brokenLink = new Link("BrokenLink", "BrokenLink");
    Link notBrokenLink = new Link(passageTest2.getTitle(), passageTest2.getTitle());
    passageTest.addLink(brokenLink);
    passageTest.addLink(notBrokenLink);
    List<Link> brokenLinks = storyTest.getBrokenLinks();
    assertTrue(brokenLinks.contains(brokenLink));
    assertFalse(brokenLinks.contains(notBrokenLink));
  }
}