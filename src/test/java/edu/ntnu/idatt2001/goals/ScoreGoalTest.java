package edu.ntnu.idatt2001.goals;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.goals.ScoreGoal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreGoalTest {

  ScoreGoal scoreGoal = new ScoreGoal(100);

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void isFulfilled() {
    assertFalse(scoreGoal.isFulfilled(player));
    player.addScore(90);
    assertTrue(scoreGoal.isFulfilled(player));
  }
}