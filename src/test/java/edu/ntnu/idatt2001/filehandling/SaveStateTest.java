package edu.ntnu.idatt2001.filehandling;

import edu.ntnu.idatt2001.models.Game;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.models.goals.Goal;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import edu.ntnu.idatt2001.models.goals.ScoreGoal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class SaveStateTest {

  static Map<String, Game> games;

  static GoldGoal goldGoal = new GoldGoal(100);

  static ScoreGoal scoreGoal = new ScoreGoal(100);

  static List<Goal> goals = List.of(goldGoal, scoreGoal);

  static Passage passageTest = new Passage("PassageTitleTest", "ContentTest");
  static Passage passageTest2 = new Passage("PassageTitleTest2", "ContentTest2");
  static Story storyTest = new Story("TitleTest", passageTest);

  private final static int health = 10;
  private final static int score = 10;
  private final static int gold = 10;
  private static Player playerTest;

  private static File saveLocation;


  @BeforeAll
  static void setUp() {
    playerTest = new Player.Builder("NameTest")
        .health(health)
        .score(score)
        .gold(gold)
        .addItemToInventory("ItemTest")
        .build();

    storyTest.addPassage(passageTest2);
    storyTest.addPassage(passageTest);

    saveLocation = new File(System.getProperty("user.home") + File.separator + ".gameSaves");
    games = new HashMap<>();
  }

  @Test
  void createKeyTest() {
    String playerName = "MagnusCarlsen";
    String storyTitle = "ChessChampionship";
    String expectedKey = "MagnusCarlsenChessChampionship";

    String actualKey = SaveState.createKey(playerName, storyTitle);

    Assertions.assertEquals(expectedKey, actualKey);
  }

  @Test
  void loadGame() {
    Game game = new Game(playerTest, storyTest, goals);
    String expectedKey = SaveState.createKey(game.getPlayer().getName(),
        game.getStory().getTitle());

    SaveState.saveGame(game);
    Map<String, Game> savedGames = SaveState.loadGame();

    Assertions.assertNotNull(savedGames);
    Assertions.assertTrue(savedGames.containsKey(expectedKey));
    Assertions.assertEquals(game.getPlayer().toString(),
        savedGames.get(expectedKey).getPlayer().toString());
    Assertions.assertEquals(game.getStory().toString(),
        savedGames.get(expectedKey).getStory().toString());
    Assertions.assertEquals(game.getGoals().toString(),
        savedGames.get(expectedKey).getGoals().toString());
  }

  @Test
  void saveGame() {
    Game game = new Game(playerTest, storyTest, goals);

    SaveState.saveGame(game);

    Assertions.assertTrue(saveLocation.exists());
    Assertions.assertTrue(saveLocation.isDirectory());

    File saveFile = new File(saveLocation, "SaveState.txt");
    Assertions.assertTrue(saveFile.exists());
    Assertions.assertTrue(saveFile.isFile());

    try (FileInputStream fis = new FileInputStream(saveFile);
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      Map<String, Game> savedGames = (Map<String, Game>) ois.readObject();
      Assertions.assertNotNull(savedGames);
      Assertions.assertTrue(savedGames.containsKey(
          SaveState.createKey(game.getPlayer().getName(), game.getStory().getTitle())));
      Assertions.assertEquals(game.getPlayer().toString(), savedGames.get(
              SaveState.createKey(game.getPlayer().getName(), game.getStory().getTitle())).getPlayer()
          .toString());
      Assertions.assertEquals(game.getStory().toString(), savedGames.get(
              SaveState.createKey(game.getPlayer().getName(), game.getStory().getTitle())).getStory()
          .toString());
    } catch (ClassNotFoundException e) {
      Assertions.fail("ClassNotFoundException occurred while reading saved games.");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void deleteGame() {
    Game game = new Game(playerTest, storyTest, goals);
    String gameKey = SaveState.createKey(game.getPlayer().getName(), game.getStory().getTitle());
    games.put(gameKey, game);
    SaveState.saveGame(game);

    SaveState.deleteGame(game);

    // Assert
    try (FileInputStream fis = new FileInputStream(new File(saveLocation, "SaveState.txt"));
        ObjectInputStream ois = new ObjectInputStream(fis)) {
      Map<String, Game> savedGames = (Map<String, Game>) ois.readObject();
      Assertions.assertNotNull(savedGames);
      Assertions.assertFalse(savedGames.containsKey(gameKey));
    } catch (ClassNotFoundException | FileNotFoundException e) {
      Assertions.fail("ClassNotFoundException occurred while reading saved games.");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @AfterEach
  void tearDown() {
    Game game = new Game(playerTest, storyTest, goals);
    SaveState.deleteGame(game);
  }
}