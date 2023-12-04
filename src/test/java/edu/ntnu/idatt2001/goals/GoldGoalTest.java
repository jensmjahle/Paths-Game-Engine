package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.goals.GoldGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldGoalTest {

  GoldGoal goldGoal = new GoldGoal(100);

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void isFulfilled() {
    assertFalse(goldGoal.isFulfilled(player));
    player.addGold(90);
    assertTrue(goldGoal.isFulfilled(player));
  }
}