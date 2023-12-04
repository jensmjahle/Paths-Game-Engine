package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.goals.InventoryGoal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryGoalTest {

  List items = List.of("ItemTest", "ItemTest2");

  InventoryGoal inventoryGoal = new InventoryGoal(items);

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void isFulfilled() {
    assertFalse(inventoryGoal.isFulfilled(player));
    player.addToInventory("ItemTest2");
    assertTrue(inventoryGoal.isFulfilled(player));
  }

  @Test
  void getMandatoryItems() {
    assertEquals(items, inventoryGoal.getMandatoryItems());
  }
}