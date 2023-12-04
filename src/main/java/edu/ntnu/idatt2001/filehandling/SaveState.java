package edu.ntnu.idatt2001.filehandling;

import edu.ntnu.idatt2001.models.Game;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import javafx.scene.control.Alert;

/**
 * Class for saving and loading game files.
 */
public class SaveState implements Serializable {

  /**
   * HashMap for storing games.
   */
  static HashMap<String, Game> games = new HashMap<>();

  /**
   * Creates a unique key for a game based on the player name and story title.
   *
   * @param playerName name of the player
   * @param storyTitle title of the story
   * @return a unique key
   */
  public static String createKey(String playerName, String storyTitle) {
    return playerName + storyTitle;
  }

  /**
   * Loads a game from a file. It will make a directory in the user's home directory if there is
   * none.
   *
   * @return a HashMap of games
   */
  public static HashMap<String, Game> loadGame() {

    File file = new File(
        System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator
            + "SaveState.txt");

    if (!file.exists()) {
      return new HashMap<>();
    }

    try (FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis)) {

      games = (HashMap<String, Game>) ois.readObject();

    } catch (EOFException e) {
      return new HashMap<>();
    } catch (Exception e) {
      return null;
    }
    return games;
  }

  /**
   * Saves a game to a file. It will search for a specific file in the user's home directory, and if
   * it does not exist, it will create a new one.
   *
   * @param game the game to be saved
   */
  public static void saveGame(Game game) {
    File location = new File(System.getProperty("user.home") + File.separator + ".gameSaves");

    if (!location.exists()) {
      location.mkdir();
    }

    games = loadGame();

    try (FileOutputStream fos = new FileOutputStream(new File(location, "SaveState.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {

      if (games == null) {
        games = new HashMap<>();
      }
      games.put(createKey(game.getPlayer().getName(), game.getStory().getTitle()), game);

      oos.writeObject(games);
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Error saving game");
      alert.setContentText("Could not save game");
      alert.showAndWait();
    }

  }

  /**
   * Deletes a game from the file. It will search for a specific file in the user's home directory,
   * and if it does not exist, it will create a new one. The method will load the file, remove the
   * game from the HashMap and save the file again.
   *
   * @param game the game to be deleted
   */
  public static void deleteGame(Game game) {
    File location = new File(System.getProperty("user.home") + File.separator + ".gameSaves");
    games = loadGame();
    games.remove(createKey(game.getPlayer().getName(), game.getStory().getTitle()));

    try (FileOutputStream fos = new FileOutputStream(new File(location, "SaveState.txt"));
        ObjectOutputStream oos = new ObjectOutputStream(fos)) {
      oos.writeObject(games);
    } catch (IOException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText("Error saving game");
      alert.setContentText("Could not save game");
      alert.showAndWait();
    }

  }

}