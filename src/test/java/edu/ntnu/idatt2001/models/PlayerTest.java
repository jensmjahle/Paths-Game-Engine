package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.Player;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
  private final static int health = 10;
  private final static int score = 10;
  private final static int gold = 10;
  private static Player playerTest;

  @BeforeEach
  void setUp() {
    playerTest = new Player.Builder("NameTest")
            .health(health)
            .score(score)
            .gold(gold)
            .addItemToInventory("ItemTest")
            .build();
  }

  @Test
  @DisplayName("Basic builder test")
  void testBuilderBasic() {
    Player player = new Player.Builder("NameTest2").build();
    assertEquals("NameTest2", player.getName());
    assertEquals(0, player.getHealth());
    assertEquals(0, player.getScore());
    assertEquals(0, player.getGold());
    assertEquals(0, player.getInventory().size());
  }
  @Test
  @DisplayName("Builder with health")
  void testBuilderWithHealth() {
    Player player = new Player.Builder("John Doe").health(100).build();

    assertEquals("John Doe", player.getName());
    assertEquals(100, player.getHealth());
    assertEquals(0, player.getScore());
    assertEquals(0, player.getGold());
    assertTrue(player.getInventory().isEmpty());
  }

  @Test
  @DisplayName("Builder with score")
  void testBuilderWithScore() {
    Player player = new Player.Builder("John Doe").score(50).build();

    assertEquals("John Doe", player.getName());
    assertEquals(0, player.getHealth());
    assertEquals(50, player.getScore());
    assertEquals(0, player.getGold());
    assertTrue(player.getInventory().isEmpty());
  }

  @Test
  @DisplayName("Builder with gold")
  void testBuilderWithGold() {
    Player player = new Player.Builder("John Doe").gold(1000).build();

    assertEquals("John Doe", player.getName());
    assertEquals(0, player.getHealth());
    assertEquals(0, player.getScore());
    assertEquals(1000, player.getGold());
    assertTrue(player.getInventory().isEmpty());
  }

  @Test
  @DisplayName("Builder with inventory")
  void testBuilderWithInventory() {
    Player player = new Player.Builder("John Doe").addItemToInventory("Sword").build();

    assertEquals("John Doe", player.getName());
    assertEquals(0, player.getHealth());
    assertEquals(0, player.getScore());
    assertEquals(0, player.getGold());
    assertFalse(player.getInventory().isEmpty());
    assertEquals(1, player.getInventory().size());
    assertEquals("Sword", player.getInventory().get(0));
  }

  @Test
  @DisplayName("Builder with all parameters")
  void testBuilderWithAllParameters() {
    Player player = new Player.Builder("John Doe")
        .health(100)
        .score(50)
        .gold(1000)
        .addItemToInventory("Sword")
        .build();

    assertEquals("John Doe", player.getName());
    assertEquals(100, player.getHealth());
    assertEquals(50, player.getScore());
    assertEquals(1000, player.getGold());
    assertFalse(player.getInventory().isEmpty());
    assertEquals(1, player.getInventory().size());
    assertEquals("Sword", player.getInventory().get(0));
  }

  @Test
  @DisplayName("Method getName() returns correct name")
  void getName() {
    assertEquals("NameTest", playerTest.getName());
  }

  @Test
  @DisplayName("Method getScore() returns correct score")
  void getScore() {
    assertEquals(10, playerTest.getScore());
  }

  @Test
  @DisplayName("Method addScore() adds correct score")
  void addScore() {
    playerTest.addScore(10);
    assertEquals(20, playerTest.getScore());
  }

  @Test
  @DisplayName("Method getGold() returns correct gold")
  void getGold() {
    assertEquals(10,playerTest.getGold());
  }

  @Test
  @DisplayName("Method addGold() adds correct gold")
  void addGold() {
    playerTest.addGold(10);
    assertEquals(20, playerTest.getGold());
  }

  @Test
  @DisplayName("Method getHealth() returns correct health")
  void getHealth() {
    assertEquals(10, playerTest.getHealth());
  }

  @Test
  @DisplayName("Method addHealth() adds correct health")
  void addHealth() {
    playerTest.addHealth(10);
    assertEquals(20, playerTest.getHealth());
  }

  @Test
  @DisplayName("Method getInventory() returns correct inventory")
  void getInventory() {
    assertEquals(1, playerTest.getInventory().size());
    assertTrue(playerTest.getInventory().contains("ItemTest"));
  }

  @Test
  @DisplayName("Method addToInventory() adds correct item to inventory")
  void addItemInventory() {
    playerTest.addToInventory("ItemTest2");
    assertEquals(2,playerTest.getInventory().size());
  }
}