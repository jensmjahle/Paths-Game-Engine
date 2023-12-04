package edu.ntnu.idatt2001.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.models.actions.Action;
import edu.ntnu.idatt2001.models.actions.GoldAction;
import edu.ntnu.idatt2001.models.actions.HealthAction;
import edu.ntnu.idatt2001.models.actions.InventoryAction;
import edu.ntnu.idatt2001.models.actions.ScoreAction;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.*;

public class StoryFileHandlerTest {

  private static File TEST_FOLDER_PATH = new File(
          System.getProperty("user.home") + File.separator + ".gameSaves");

  private static Story story;
  private static Passage openingPassage;
  private static Link link1;
  private static Link link2;

  @BeforeAll
  public static void setUp() {
    openingPassage = new Passage("Opening Passage", "This is the opening passage.");
    link1 = new Link("Link 1", "Reference 1");
    link2 = new Link("Link 2", "Reference 2");

    link1.addAction(new GoldAction(10));
    link1.addAction(new HealthAction(20));

    link2.addAction(new ScoreAction(50));

    openingPassage.addLink(link1);
    openingPassage.addLink(link2);

    story = new Story("Test Story", openingPassage);
  }

  @AfterEach
  void tearDown() {
    File folder = new File("src/main/resources/testStories");

    if (folder.exists() && folder.isDirectory()) {
      File[] files = folder.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            file.delete();
          }
        }
      }
    } else {
      System.out.println("Folder does not exist or is not a directory.");
    }
  }

  @Test
  @DisplayName("Checks if the story is saved to a file and can be loaded correctly")
  void saveStoryToFileAndLoadStoryFromFile() throws IOException {
    File testFile = new File(TEST_FOLDER_PATH + "test_story.paths");

    // Save the story to a file
    StoryFileHandler.saveStoryToFile(story, testFile);

    // Load the story from the file
    Story loadedStory = StoryFileHandler.loadStoryFromFile(testFile);

    // Check if the loaded story is equal to the original story
    assertEquals(story.getTitle(), loadedStory.getTitle());
    assertEquals(story.getOpeningPassage().getTitle(), loadedStory.getOpeningPassage().getTitle());
    assertEquals(story.getOpeningPassage().getContent(),
        loadedStory.getOpeningPassage().getContent());

    // Check if the loaded story has the same links as the original story
    List<Link> originalLinks = story.getOpeningPassage().getLinks();
    List<Link> loadedLinks = loadedStory.getOpeningPassage().getLinks();

    assertEquals(originalLinks.size(), loadedLinks.size());

    for (int i = 0; i < originalLinks.size(); i++) {
      Link originalLink = originalLinks.get(i);
      Link loadedLink = loadedLinks.get(i);

      assertEquals(originalLink.getText(), loadedLink.getText());
      assertEquals(originalLink.getReference(), loadedLink.getReference());

      List<Action> originalActions = originalLink.getActions();
      List<Action> loadedActions = loadedLink.getActions();

      assertEquals(originalActions.size(), loadedActions.size());

      for (int j = 0; j < originalActions.size(); j++) {
        Action originalAction = originalActions.get(j);
        Action loadedAction = loadedActions.get(j);

        assertEquals(originalAction.getClass(), loadedAction.getClass());

        // Check specific action properties
        if (originalAction instanceof GoldAction) {
          GoldAction originalGoldAction = (GoldAction) originalAction;
          GoldAction loadedGoldAction = (GoldAction) loadedAction;
          assertEquals(originalGoldAction.getGold(), loadedGoldAction.getGold());
        } else if (originalAction instanceof HealthAction) {
          HealthAction originalHealthAction = (HealthAction) originalAction;
          HealthAction loadedHealthAction = (HealthAction) loadedAction;
          assertEquals(originalHealthAction.getHealth(), loadedHealthAction.getHealth());
        } else if (originalAction instanceof InventoryAction) {
          InventoryAction originalInventoryAction = (InventoryAction) originalAction;
          InventoryAction loadedInventoryAction = (InventoryAction) loadedAction;
          assertEquals(originalInventoryAction.getItem(), loadedInventoryAction.getItem());
        } else if (originalAction instanceof ScoreAction) {
          ScoreAction originalScoreAction = (ScoreAction) originalAction;
          ScoreAction loadedScoreAction = (ScoreAction) loadedAction;
          assertEquals(originalScoreAction.getScore(), loadedScoreAction.getScore());
        }
      }
    }
  }

  @Test
  @DisplayName("Checks if getListOfStoryFiles returns the correct list of story files")
  void getListOfStoryFiles() {
    // Create some test story files
    File testFile1 = new File(TEST_FOLDER_PATH + File.separator + "story1.paths");
    File testFile2 = new File(TEST_FOLDER_PATH + File.separator + "story2.paths");
    File testFile3 = new File(TEST_FOLDER_PATH + File.separator + "story3.txt"); // Not a story file
    File testFile4 = new File(TEST_FOLDER_PATH + File.separator + "story4.paths");

    try {
      testFile1.createNewFile();
      testFile2.createNewFile();
      testFile3.createNewFile();
      testFile4.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }

    List<File> files = StoryFileHandler.getListOfStoryFiles();

    assertTrue(files.contains(testFile1));
    assertTrue(files.contains(testFile2));
    assertTrue(files.contains(testFile4));
    assertFalse(files.contains(testFile3));

    // Delete the test files
    testFile1.delete();
    testFile2.delete();
    testFile3.delete();
    testFile4.delete();

  }
}
