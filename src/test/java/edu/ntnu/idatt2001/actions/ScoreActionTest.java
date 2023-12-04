package edu.ntnu.idatt2001.actions;

import edu.ntnu.idatt2001.models.Player;
import edu.ntnu.idatt2001.models.actions.ScoreAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreActionTest {

  Player player = new Player.Builder("NameTest")
          .health(10)
          .score(10)
          .gold(10)
          .addItemToInventory("ItemTest")
          .build();

  @Test
  void execute() {
    ScoreAction scoreAction = new ScoreAction(10);
    scoreAction.execute(player);
    assertEquals(20, player.getScore());
  }

  @Test
  void getScore() {
    assertEquals(10, player.getScore());
  }
}