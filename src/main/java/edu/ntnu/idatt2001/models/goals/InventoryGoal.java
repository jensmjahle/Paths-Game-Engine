package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.Player;
import java.util.List;

/**
 * A class to check if the player has reached the goal of having a certain amount of mandatory items
 * in the inventory.
 */
public class InventoryGoal implements Goal {

  /**
   * The list of mandatory items the player needs to have to fulfill the goal.
   */
  private final List<String> mandatoryItems;

  /**
   * Constructor for the InventoryGoal class.
   *
   * @param mandatoryItems The list of mandatory items the player needs to have to fulfill the
   *                       goal.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * Method to check if the goal is fulfilled. It calls the method containsAll() from the List
   * library and checks if the player has the items through getInventory().
   *
   * @param player The player to check if the goal is fulfilled.
   * @return true if the player has all the mandatory items in the inventory.
   */
  @Override
  public boolean isFulfilled(Player player) {
    return player.getInventory().containsAll(mandatoryItems);
  }

  /**
   * Method to get the list of mandatory items needed to fulfill the goal.
   *
   * @return The list of mandatory items needed to fulfill the goal.
   */
  public List<String> getMandatoryItems() {
    return mandatoryItems;
  }

  /**
   * Method to create a string that will display the list of mandatory items needed to fulfill the
   * goal.
   *
   * @return A string that will display the list of mandatory items needed to fulfill the goal.
   */
  @Override
  public String toString() {
    return "InventoryGoal: "
        + "mandatoryItems=" + mandatoryItems;
  }

  public List<String> getInventory() {
    return mandatoryItems;
  }
}
