package edu.ntnu.idatt2001.filehandling;

import edu.ntnu.idatt2001.models.Link;
import edu.ntnu.idatt2001.models.Passage;
import edu.ntnu.idatt2001.models.Story;
import edu.ntnu.idatt2001.models.actions.Action;
import edu.ntnu.idatt2001.models.actions.GoldAction;
import edu.ntnu.idatt2001.models.actions.HealthAction;
import edu.ntnu.idatt2001.models.actions.InventoryAction;
import edu.ntnu.idatt2001.models.actions.ScoreAction;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to handle reading and writing of story files.
 * The story files are stored in the resources folder and will follow the
 * format of the requirements.
 */
public class StoryFileHandler {

  private static final String FILE_EXTENSION = ".paths";
  private static final String FILE_PATH =
      System.getProperty("user.dir") + File.separator + "src/main/resources/stories/";

  /**
   * Reads a story from a file.
   *
   * @param story The story to be read from a file.
   * @param file  The file to read from.
   * @throws IOException If the file cannot be read.
   */
  public static void saveStoryToFile(Story story, File file) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
      writer.write(story.getTitle());
      writer.newLine();

      writePassage(writer, story.getOpeningPassage());

      for (Passage passage : story.getPassages()) {
        writer.newLine();
        writePassage(writer, passage);
      }
    }
  }

  private static void writePassage(BufferedWriter writer, Passage passage) throws IOException {
    writer.newLine();
    writer.write("::" + passage.getTitle());
    writer.newLine();
    writer.write(passage.getContent());
    writer.newLine();

    for (Link link : passage.getLinks()) {
      writer.write("[" + link.getText() + "](" + link.getReference() + ")");
      writer.newLine();

      for (Action action : link.getActions()) {
        writer.write(formatAction(action));
        writer.newLine();
      }
    }
  }

  private static String formatAction(Action action) {
    if (action instanceof ScoreAction) {
      return ";Score{" + ((ScoreAction) action).getScore() + "}";
    } else if (action instanceof GoldAction) {
      return ";Gold{" + ((GoldAction) action).getGold() + "}";
    } else if (action instanceof InventoryAction) {
      return ";Inventory{" + ((InventoryAction) action).getItem() + "}";
    } else if (action instanceof HealthAction) {
      return ";Health{" + ((HealthAction) action).getHealth() + "}";
    } else {
      return "";
    }
  }

  /**
   * Loads a story from a file.
   *
   * @return The story loaded from the file.
   *
   * @throws IOException if the file could not be read.
   */
  public static Story loadStoryFromFile(File file) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String title = reader.readLine();
      reader.readLine(); // skip empty line
      List<Passage> passages = new ArrayList<>();
      Passage openingPassage = null;
      String line;

      //Read as long as there are lines to read
      while ((line = reader.readLine()) != null) {

        //If the line starts with "::" it is a passage
        if (line.startsWith("::")) {
          String passageTitle = line.substring(2).trim();
          String content = reader.readLine();
          List<Link> links = new ArrayList<>();

          line = reader.readLine();
          //Reads each link in the passage
          while (line != null && !line.isEmpty()) {
            Pattern linkPattern = Pattern.compile("\\[([^\\]]+)\\]\\(([^\\)]+)\\)");
            Matcher linkMatcher = linkPattern.matcher(line);
            if (linkMatcher.find()) {
              String text = linkMatcher.group(1);
              String reference = linkMatcher.group(2);

              List<Action> actions = new ArrayList<>();
              Link link = new Link(text, reference);

              line = reader.readLine();

              // Reads each action in the link
              while (line != null && line.startsWith(";")) {
                String[] actionParts = line.split(
                    ";"); // splits the line into the different actions

                // Adds each action to the link
                for (String actionPart : actionParts) {
                  if (!actionPart.isEmpty()) {
                    link.addAction(extractActionFromLine(actionPart));
                  }
                }
                line = reader.readLine();
              }
              links.add(link); // Adds the link to the list of links
            } else {
              line = reader.readLine();
            }
          }

          Passage passage = new Passage(passageTitle, content);
          for (Link link : links) {
            passage.addLink(link);
          }
          if (openingPassage == null) {
            openingPassage = passage;
          } else {
            passages.add(passage);
          }
        }
      }
      Story story = new Story(title, openingPassage);
      passages.forEach(story::addPassage);
      return story;
    }
  }

  /**
   * Extracts an action from a line in the file. The line should be in the format "Action{value}".
   * The action can be Gold, Health, Inventory or Score.
   *
   * @param line the line to extract the action from
   * @return the action extracted from the line
   */
  private static Action extractActionFromLine(String line) {
    String actionString = line.substring(
        line.indexOf(";") + 1); // Extract the action part of the line

    if (actionString.startsWith("Gold{")) {
      int gold = Integer.parseInt(
          actionString.substring(actionString.indexOf("{") + 1, actionString.indexOf("}")));
      return new GoldAction(gold);
    } else if (actionString.startsWith("Health{")) {
      int health = Integer.parseInt(
          actionString.substring(actionString.indexOf("{") + 1, actionString.indexOf("}")));
      return new HealthAction(health);
    } else if (actionString.startsWith("Inventory{")) {
      String item = actionString.substring(actionString.indexOf("{") + 1,
          actionString.indexOf("}"));
      return new InventoryAction(item);
    } else if (actionString.startsWith("Score{")) {
      int score = Integer.parseInt(
          actionString.substring(actionString.indexOf("{") + 1, actionString.indexOf("}")));
      return new ScoreAction(score);
    } else {
      return null;
    }
  }

  /**
   * Gets a list of all the story files in the story folder.
   *
   * @return a list of all the story files in the story folder.
   */
  public static List<File> getListOfStoryFiles() {
    List<File> storyFiles = new ArrayList<>();
    Path path = Paths.get(setFolder());
    File folder = path.toFile();

    if (folder.exists() && folder.isDirectory()) {
      File[] files = folder.listFiles();

      if (files != null) {
        for (File file : files) {
          if (file.isFile() && file.getName().endsWith(".paths")) {
            storyFiles.add(file);
          }
        }
      }
    }

    return storyFiles;
  }

  private static String setFolder() {
    File folderpath = new File(
            System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);
    if (!folderpath.exists()) {
      folderpath.mkdir();
    }
    String folderString = folderpath.toString();
    return folderString;
  }

}



