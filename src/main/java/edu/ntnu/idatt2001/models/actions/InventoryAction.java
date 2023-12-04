package edu.ntnu.idatt2001.models.actions;

import edu.ntnu.idatt2001.models.Player;

/**
 * An action that can be executed by the player. Specifically, this action updates the inventory the
 * player has.
 */
public class InventoryAction implements Action {

  /**
   * The item to add to the player's inventory.
   */
  private final String item;

  /**
   * Constructor for the InventoryAction class.
   *
   * @param item The item to add to the player's inventory.
   */
  public InventoryAction(String item) {
    this.item = item;
  }

  /**
   * Executes the action on the given player. It calls the method addToInventory() from the class
   * Player.
   *
   * @param player The player to execute the action.
   */
  @Override
  public void execute(Player player) {
    player.addToInventory(item);
  }

  /**
   * Method to get the item to add to the player's inventory.
   *
   * @return The item to add to the player's inventory.
   */
  public String getItem() {
    return item;
  }

  /**
   * Method to create a string that will display the item to add to the player's inventory.
   *
   * @return A string that will display the item to add to the player's inventory.
   */
  @Override
  public String toString() {
    return "InventoryAction: "
        + '\'' + item + '\'';
  }
}
