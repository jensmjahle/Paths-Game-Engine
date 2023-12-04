package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.actions.HealthAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthActionTest {

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void getHealth() {
    Assertions.assertEquals(10, player.getHealth());
  }

  @Test
  void execute() {
    HealthAction healthAction = new HealthAction(10);
    healthAction.execute(player);
    Assertions.assertEquals(20, player.getHealth());
  }
}