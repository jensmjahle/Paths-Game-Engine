package edu.ntnu.idatt2001.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in a game with various statistics, such as name, health, score, gold, and an
 * inventory. The Player class utilizes the Builder design pattern to allow for the creation of
 * different representations of Player objects. Usage: Player player1 = new
 * Player.Builder("playerName") .health(100) .score(50) .gold(200) .addItemToInventory("Sword")
 * .build();
 *
 * @author Jens Martin Jahle
 */
public class Player implements Serializable {

  private final int startHealth;

  private final int startScore;

  private final int startGold;


  /**
   * The name of the player.
   */
  private final String name;

  /**
   * The health of the player.
   */
  private int health;

  /**
   * The score of the player.
   */
  private int score;

  /**
   * The gold of the player.
   */
  private int gold;

  /**
   * The inventory of the player.
   */
  private final ArrayList<String> inventory = new ArrayList<>();

  /**
   * Private constructor to be used by the builder class.
   *
   * @param builder The builder to use to create the player.
   */
  private Player(Builder builder) {
    this.name = builder.name;
    this.health = builder.health;
    this.score = builder.score;
    this.gold = builder.gold;
    this.inventory.addAll(builder.inventory);

    this.startHealth = builder.health;
    this.startScore = builder.score;
    this.startGold = builder.gold;

  }

  /**
   * Method to get the name of the player.
   *
   * @return The name of the player.
   */
  public String getName() {
    return name;
  }

  /**
   * Method to get the health of the player.
   *
   * @return The health of the player.
   */
  public int getScore() {
    return score;
  }

  /**
   * Method to add score to the player.
   */
  public void addScore(int score) {
    this.score += score;
  }

  /**
   * Method to get the gold of the player.
   *
   * @return The gold of the player.
   */
  public int getGold() {
    return gold;
  }

  /**
   * Method to add gold to the player.
   *
   * @param gold The amount of gold to add to the player.
   */
  public void addGold(int gold) {
    this.gold += gold;
  }

  /**
   * Method to get the health of the player.
   *
   * @return The health of the player.
   */
  public int getHealth() {
    return health;
  }

  /**
   * Method to add health to the player.
   *
   * @param health The amount of health to add to the player.
   */
  public void addHealth(int health) {
    this.health += health;
  }

  /**
   * Method to get the inventory of the player.
   *
   * @return The inventory of the player.
   */
  public ArrayList<String> getInventory() {
    return inventory;
  }

  /**
   * Method to add an item to the inventory of the player.
   *
   * @param item The item to add to the inventory of the player.
   */
  public void addToInventory(String item) {
    inventory.add(item);
  }

  /**
   * Method to reset all values to the start values of the player.
   */
  public void resetPlayer() {
    this.health = this.startHealth;
    this.score = this.startScore;
    this.gold = this.startGold;
    this.inventory.clear();
  }

  /**
   * Method to get the start gold of the player.
   *
   * @return The start gold of the player.
   */
  public int getStartGold() {
    return startGold;
  }

  /**
   * Method to get the start health of the player.
   *
   * @return The start health of the player.
   */
  public int getStartHealth() {
    return startHealth;
  }

  /**
   * Method to get the start score of the player.
   *
   * @return The start score of the player.
   */
  public int getStartScore() {
    return startScore;
  }

  /**
   * Method to get the start inventory of the player.
   *
   * @return The start inventory of the player.
   */
  public List<String> getStartInventory() {
    return inventory;
  }

  /**
   * Builder class for the player, allowing for different representations of a Player object.
   */
  public static class Builder {

    // Required parameters
    private final String name;

    // Optional parameters - initialized to default values
    private int health = 0;
    private int score = 0;
    private int gold = 0;
    private final ArrayList<String> inventory = new ArrayList<>();

    /**
     * Constructor for the builder class.
     *
     * @param name The name of the player.
     */
    public Builder(String name) {
      this.name = name;
    }

    /**
     * Method to set the health of the player.
     *
     * @param health The health of the player.
     * @return The builder object.
     */
    public Builder health(int health) {
      this.health = health;
      return this;
    }

    /**
     * Method to set the score of the player.
     *
     * @param score The score of the player.
     * @return The builder object.
     */
    public Builder score(int score) {
      this.score = score;
      return this;
    }

    /**
     * Method to set the gold of the player.
     *
     * @param gold The gold of the player.
     * @return The builder object.
     */
    public Builder gold(int gold) {
      this.gold = gold;
      return this;
    }

    /**
     * Method to add an item to the inventory of the player.
     *
     * @param item The item to add to the inventory of the player.
     * @return The builder object.
     */
    public Builder addItemToInventory(String item) {
      this.inventory.add(item);
      return this;
    }

    /**
     * Method to build the player object based on the builder's parameters.
     *
     * @return A new player object.
     */
    public Player build() {
      return new Player(this);
    }
  }

  /**
   * Method to get a string of the player's name.
   *
   * @return A string of the player's name.
   */
  public String toString() {
    return name;
  }
}
