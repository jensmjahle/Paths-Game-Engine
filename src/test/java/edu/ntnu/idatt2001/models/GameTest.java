package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.goals.Goal;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import edu.ntnu.idatt2001.models.goals.HealthGoal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Tests for the Game class")
class GameTest {
  private Player playerTest;
  private List<Goal> goalsTest;
  Game gameTest;

  Passage openingPassageTest;
  Passage passageTest2;
  Passage openingPassage;
  Story storyTest;

  @BeforeEach
  void setUp() {
    playerTest = new Player.Builder("NameTest")
            .health(10)
            .score(10)
            .gold(10)
            .addItemToInventory("ItemTest")
            .build();
    Passage passageTest = new Passage("TitleTest", "TextTest");
    goalsTest = new ArrayList<>(
            List.of(new GoldGoal(15), new HealthGoal(15)));
    gameTest = new Game(playerTest, new Story("TitleTest", passageTest), goalsTest);
    openingPassageTest = new Passage("TitleTest", "TextTest");
    passageTest2 = new Passage("TitleTest2", "TextTest2");
    openingPassage = new Passage("openingTitle", "openingContent");
    storyTest = new Story("TitleTest", openingPassage);

  }

  @Test
  @DisplayName("Check if the player is correct after adding gold and health")
  void getPlayer() {

    assertEquals("NameTest", gameTest.getPlayer().getName());
    assertEquals(10, gameTest.getPlayer().getGold());
    assertEquals(10, gameTest.getPlayer().getHealth());
  }

  @Test
  @DisplayName("Check if the story is correct and opening passage")
  void getStory() {
    assertEquals("TitleTest", gameTest.getStory().getTitle());
    assertEquals("TitleTest", gameTest.getStory().getOpeningPassage().getTitle());
  }

  @Test
  @DisplayName("Check if the goals are correct")
  void getGoals() {
    gameTest.getPlayer().addGold(5);
    gameTest.getPlayer().addHealth(5);
    assertTrue(gameTest.getGoals().get(0).isFulfilled(playerTest));
    assertTrue(gameTest.getGoals().get(1).isFulfilled(playerTest));
  }

  @Test
  @DisplayName("Check if the opening passage is correct")
  void begin() {
    assertEquals("TitleTest", gameTest.begin().getTitle());
    assertEquals("TextTest", gameTest.begin().getContent());
  }

  @Test
  @DisplayName("Check if the correct link is given")
  void go() {
    storyTest.addPassage(passageTest2);
    Game gameTest = new Game(playerTest, storyTest, goalsTest);
    Link linkToPassage2 = new Link("TitleTest2", "TitleTest2");
    openingPassage.addLink(linkToPassage2);

    gameTest.setLinkToCurrentPassage(linkToPassage2);
    assertEquals(passageTest2, gameTest.go(linkToPassage2));
  }

  @Test
  @DisplayName("Check if goals are fulfilled")
  void checkGoals() {
    // Initially, goals are not fulfilled
    assertFalse(gameTest.checkGoals());

    // Make the goals fulfilled
    gameTest.getPlayer().addGold(5);
    gameTest.getPlayer().addHealth(5);
    assertTrue(gameTest.checkGoals());
  }

  @Test
  @DisplayName("Check if the game is reset correctly")
  void resetGame() {
    gameTest.resetGame();
    assertEquals(10, gameTest.getPlayer().getGold());
    assertEquals(10, gameTest.getPlayer().getHealth());
    assertNull(gameTest.getLinkToCurrentPassage());
  }

  @AfterEach
  void tearDown() {
    playerTest = null;
    goalsTest = null;
    gameTest = null;
    openingPassageTest = null;
    passageTest2 = null;
    openingPassage = null;
    storyTest = null;
  }

}