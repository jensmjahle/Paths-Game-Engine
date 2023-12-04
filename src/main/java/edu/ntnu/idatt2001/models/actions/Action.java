package edu.ntnu.idatt2001.models.actions;

import edu.ntnu.idatt2001.models.Player;
import java.io.Serializable;

/**
 * An action that can be executed by a player.
 */
public interface Action extends Serializable {

  void execute(Player player);
}
