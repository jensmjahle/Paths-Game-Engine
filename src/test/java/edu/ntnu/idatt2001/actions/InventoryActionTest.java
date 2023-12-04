package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.actions.InventoryAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryActionTest {

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void getInventory() {
    Assertions.assertEquals("[ItemTest]", player.getInventory().toString());
  }

  @Test
  void execute() {
    InventoryAction inventoryAction = new InventoryAction("ItemTest2");
    inventoryAction.execute(player);
    Assertions.assertEquals(2, player.getInventory().size());
  }
}