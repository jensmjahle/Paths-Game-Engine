package edu.ntnu.idatt2001.models;

import edu.ntnu.idatt2001.models.actions.Action;
import edu.ntnu.idatt2001.models.actions.GoldAction;
import edu.ntnu.idatt2001.models.actions.HealthAction;
import edu.ntnu.idatt2001.models.actions.InventoryAction;
import edu.ntnu.idatt2001.models.actions.ScoreAction;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests for the Link class")
class LinkTest {

  private static Link linkTest;
  private static Link linkTest2;
  private static GoldAction gold;
  private static InventoryAction inventory;

  @BeforeAll
  public static void setUp() {
    linkTest = new Link("text1", "text1");
    linkTest2 = new Link("text2", "text2");
    gold = new GoldAction(5);
    inventory = new InventoryAction("testItem");
    linkTest.addAction(gold);
    linkTest.addAction(inventory);
  }

  @Test
  @DisplayName("When getText is called, it should return the expected text")
  void getText() {
    String expected = "text1";
    String actual = linkTest.getText();
    assertEquals(expected, actual);
  }

  @Test
  @DisplayName("When getReference is called, it should return the expected reference")
  void getReference() {
    String expected = "text1";
    String actual = linkTest.getReference();
    assertEquals(expected, actual);
  }


  @Test
  @DisplayName("When addActions is called, it should add the action to the list of actions")
  void addAction() {
    HealthAction testAction = new HealthAction((98));
    linkTest.addAction(testAction);
    assertTrue(linkTest.getActions().contains(testAction));
  }


  @Test
  @DisplayName("When getActions is called, it should return the list of actions")
  void getActions() {
    assertTrue(linkTest.getActions().contains(gold));
    assertTrue(linkTest.getActions().contains(inventory));
  }

  @Test
  @DisplayName("Checks that equal objects has equal hashcode")
  void testHashCodeEquals() {
    linkTest2 = linkTest;
    assertEquals(linkTest.hashCode(), linkTest2.hashCode());
  }

  @Test
  @DisplayName("Checks that unequal objects has unequal hashcode")
  void testHashCodeNotEquals() {
    assertNotEquals(linkTest.hashCode(),linkTest2.hashCode());
  }

  @Test
  @DisplayName("Check if toString() returns the correct string representation")
  void testToString() {
    Link link = new Link("LinkText", "LinkReference");
    link.addAction(new GoldAction(50));
    link.addAction(new ScoreAction(10));

    String expected = "'LinkText', Reference: 'LinkReference', Actions:[GoldAction: 50, ScoreAction: 10]";
    assertEquals(expected, link.toString());
  }

  @Test
  @DisplayName("Check if an action is removed successfully")
  void testRemoveAction() {
    Link link = new Link("LinkText", "LinkReference");
    Action action1 = new GoldAction(50);
    Action action2 = new ScoreAction(10);
    link.addAction(action1);
    link.addAction(action2);

    assertEquals(2, link.getActions().size());

    link.removeAction(action1);

    assertEquals(1, link.getActions().size());
    assertFalse(link.getActions().contains(action1));
    assertTrue(link.getActions().contains(action2));
  }

}