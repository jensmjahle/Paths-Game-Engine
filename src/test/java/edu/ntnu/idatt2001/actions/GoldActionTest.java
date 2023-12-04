package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.actions.GoldAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldActionTest {

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void getGold() {
    Assertions.assertEquals(10, player.getGold());
  }

  @Test
  void execute() {
    GoldAction goldAction = new GoldAction(10);
    goldAction.execute(player);
    Assertions.assertEquals(20, player.getGold());
  }
}