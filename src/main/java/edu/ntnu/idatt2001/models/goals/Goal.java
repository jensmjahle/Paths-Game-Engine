package edu.ntnu.idatt2001.models.goals;

import edu.ntnu.idatt2001.models.Player;
import java.io.Serializable;

/**
 * The interface Goal.
 */
public interface Goal extends Serializable {

  /**
   * Method to check if the goal is fulfilled.
   *
   * @return false as default, but will be overridden in the subclasses.
   */
  default boolean isFulfilled(Player player) {
    return false;
  }

}
