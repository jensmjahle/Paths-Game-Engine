package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.goals.HealthGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {
  
  HealthGoal healthGoal = new HealthGoal(100);

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void isFulfilled() {
    assertFalse(healthGoal.isFulfilled(player));
    player.addHealth(90);
    assertTrue(healthGoal.isFulfilled(player));
  }
}