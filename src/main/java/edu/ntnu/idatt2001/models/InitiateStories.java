package edu.ntnu.idatt2001.models;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.scene.control.Alert;

/**
 * Class for creating story files if they don't exist.
 */
public class InitiateStories {

  static String aDucksBattleWithin = """
      A Duck's Battle Within
                
      ::The Beginning
      You are a duck, swimming gracefully in a calm pond, and you have a burning desire to overcome your inner demons. With determination in your heart, you embark on a journey of self-discovery and healing.
      [Begin your adventure](A Courageous Duck)
                
      ::A Courageous Duck
      You are a small but courageous duck, living in a peaceful pond. You've heard tales of legendary creatures who have faced their fears and achieved inner peace. Inspired by their stories, you decide to confront your own demons and find tranquility.
      [Begin your adventure](Leaving the Pond)
      ;Score{5}
      ;Inventory{Courage}
      [Stay and enjoy the safety of the pond](The End)
      ;Score{-100}
      ;Inventory{Cowardice}
                
      ::Leaving the Pond
      You take a deep breath and flap your wings, leaving the safety of the pond behind. As you glide through the air, you feel a surge of excitement and anticipation for the journey ahead.
      [Head to the mysterious swamp](The Mysterious Swamp)
      [Visit the wise owl in the forest](The Wise Owl)
      [Seek guidance from the ancient turtle](The Ancient Turtle)
      ;Inventory{Wisdom}
                
      ::The Mysterious Swamp
      You venture into the murky swamp, where you encounter a group of knowledgeable frogs. They offer to teach you their secret techniques for confronting your fears.
      [Accept their offer](Frog Wisdom)
      [Decline and continue on your journey](The Swamp Path)
                
      ::Frog Wisdom
      The wise frogs share their knowledge with you, teaching you valuable skills that will aid you in your journey. You thank them for their wisdom and continue on your path.
      [Head deeper into the swamp](The Enchanted Marsh)
      [Return to the pond](A Courageous Duck)
                
      ::The Swamp Path
      You follow the winding path through the swamp, coming across a fork in the road. The path splits into two directions.
      [Take the left path](The Enchanted Marsh)
      [Take the right path](The Shadowy Bog)
                
      ::The Wise Owl
      You arrive at the forest, where you find the wise owl perched on a branch. The owl offers to guide you on your journey, helping you confront and conquer your fears.
      [Accept the owl's guidance](The Owl's Guidance)
      [Decline and continue on your journey](Leaving the Pond)
                
      ::The Owl's Guidance
      With the wise owl by your side, you face your fears head-on, growing stronger with each challenge you overcome. You thank the owl for its guidance and continue on your path.
      [Seek guidance from the ancient turtle](The Ancient Turtle)
      [Return to the pond](A Courageous Duck)
                
      ::The Ancient Turtle
      You approach the ancient turtle, a being known for its infinite wisdom and serenity. The turtle offers to share its secrets with you, teaching you the ways of inner peace.
      [Accept the turtle's teachings](Turtle Teachings)
      [Decline and continue on your journey](Leaving the Pond)
                
      ::Turtle Teachings
      The ancient turtle imparts its wisdom upon you, helping you to cultivate inner peace and balance. You thank the turtle for its teachings and continue on your path.
      [Head to the enchanted marsh](The Enchanted Marsh)
      [Return to the pond](A Courageous Duck)
                
      ::The Enchanted Marsh
      You stumble upon an enchanted marsh, filled with magical creatures that possess extraordinary powers. The guardian of the marsh offers you a choice of three magical talismans, each with its own unique ability.
      [Choose the talisman of courage](The Talisman of Courage)
      ;Inventory{Talisman of Courage}
      [Choose the talisman of wisdom](The Talisman of Wisdom)
      ;Inventory{Talisman of Wisdom}
      [Choose the talisman of healing](The Talisman of Healing)
      ;Inventory{Talisman of Healing}
                
      ::The Talisman of Courage
      You choose the talisman of courage, feeling your resolve strengthen and your fears diminish. With your newfound courage, you are confident that you can face any challenge in your path.
      [Continue your journey](The Final Confrontation)
                
      ::The Talisman of Wisdom
      You choose the talisman of wisdom, and your mind expands with knowledge and understanding. With your newfound wisdom, you can easily navigate the complexities of your inner struggles.
      [Continue your journey](The Final Confrontation)
                
      ::The Talisman of Healing
      You choose the talisman of healing, feeling a soothing energy envelop you, mending your emotional wounds. With your newfound healing power, you can mend your heart and face your demons.
      [Continue your journey](The Final Confrontation)
                
      ::The Final Confrontation
      With your skills and abilities honed to perfection, you face your final confrontation, a formidable inner demon that will test everything you've learned on your journey. As you triumph over your adversary, you prove that you are truly a duck who has conquered its fears.
      [Return to the pond in triumph](The Triumphant Return)
      ;Score{100}
                
      ::The Triumphant Return
      You return to your home, the peaceful pond where your journey began, triumphant and with a newfound sense of inner peace. Your fellow creatures celebrate your achievements, and you bask in the glory of your emotional victory.
      [Rest and reflect on your journey](The End)
      ;Score{100}
                
      ::The Shadowy Bog
      You enter the shadowy bog, a mysterious and foreboding place. As you navigate the darkness, you feel an unsettling presence lurking just beyond your sight. Despite your fear, you press onward.
      [Face the unknown](The Final Confrontation)
      ;Score{100}
                
      ::The End
      Having achieved your dreams and fulfilled your destiny, you settle down in the calm waters of the pond, content with your accomplishments. As the sun sets on your epic journey, you know that your story will be remembered for generations to come.
      """;

  static String aMutantsOdysseyForFortuneAndFame = """
      A Mutant's Odyssey for Fortune and Fame
                
      ::The Beginning
      You are a mutant, born with extraordinary powers, and you have a burning desire to explore the world, find riches, and help those in need. With determination in your heart, you embark on a journey filled with adventure and excitement.
      [Begin your adventure](The Birth of a Hero)
                
      ::The Birth of a Hero
      You are a unique mutant, living on the outskirts of a small village. Your powers have made you an outcast, but you refuse to let that define you. Instead, you decide to venture out into the world, seeking opportunities to use your abilities for good and gain the respect of others.
      [Begin your journey](Leaving the Village)
      [Stay and enjoy the safety of your home](The End)
                
      ::Leaving the Village
      You take a deep breath and step out of your home, leaving the safety of the village behind. As you walk down the road, you feel a surge of excitement and anticipation for the journey ahead.
      [Head to the bustling city](The Bustling City)
      [Visit the wise sage in the mountains](The Wise Sage)
      [Explore the mysterious forest](The Mysterious Forest)
                
      ::The Bustling City
      You arrive at a bustling city, filled with people from all walks of life. You're drawn to the lively atmosphere and the promise of adventure it holds.
      [Search for work as a bounty hunter](The Bounty Hunter)
      ;Score{5}
      ;Inventory{Bounty License}
      [Participate in an underground fighting ring](The Fighting Ring)
      ;Score{10}
      ;Gold{50}
      ;Inventory{Fighting Gloves}
      [Leave the city and continue your journey](Leaving the Village)
                
      ::The Bounty Hunter
      As a skilled bounty hunter, you track down criminals and outlaws, bringing them to justice and earning a reputation for your unique abilities. Your newfound career leads you on exciting adventures and helps you amass a small fortune.
      [Continue your journey](The Crossroads)
                
      ::The Fighting Ring
      You enter the underground fighting ring, using your powers to become a formidable fighter. Your strength and skills attract attention, earning you a loyal following and a considerable amount of gold.
      [Continue your journey](The Crossroads)
                
      ::The Wise Sage
      You arrive at the wise sage's dwelling in the mountains. The sage offers to teach you the secrets of mastering your powers and using them for the greater good.
      [Accept the sage's teachings](Sage's Teachings)
      ;Health{20}
      ;Score{10}
      ;Inventory{Mystical Amulet}
      [Decline and continue on your journey](Leaving the Village)
                
      ::Sage's Teachings
      The wise sage imparts his knowledge upon you, helping you to refine your powers and unlock new abilities. You thank the sage for his teachings and continue on your path.
      [Continue your journey](The Crossroads)
                
      ::The Mysterious Forest
      You venture into the mysterious forest, a place rumored to be filled with powerful creatures and hidden treasures. As you explore, you encounter a fearsome dragon guarding a hoard of gold.
      [Face the dragon](The Dragon's Lair)
      [Leave the forest and continue your journey](Leaving the Village)
                
      ::The Dragon's Lair
      You bravely face the fearsome dragon, using your powers to defeat the mighty beast. As the dragon falls, you claim a portion of its hoard and earn the title of Dragon Slayer.
      [Claim your reward](The Dragon's Treasure)
      ;Health{-50}
      ;Gold{500}
      ;Score{100}
      ;Inventory{Dragon Tooth}
                
      ::The Dragon's Treasure
      You gather some of the dragon's treasure, feeling a sense of accomplishment and pride. With newfound wealth in tow, you continue on your journey.
      [Continue your journey](The Crossroads)
                
      ::The Crossroads
      You arrive at a crossroads, where paths lead in three different directions. Each path promises its own unique adventure and challenges.
      [Take the path to the enchanted kingdom](The Enchanted Kingdom)
      [Take the path to the haunted graveyard](The Haunted Graveyard)
      [Take the path to the lost city of gold](The Lost City of Gold)
                
      ::The Enchanted Kingdom
      You journey to the enchanted kingdom, a land filled with magical creatures and wondrous sights. The kingdom is under siege by a dark sorcerer, and you decide to help the inhabitants defend their home.
      [Confront the dark sorcerer](The Sorcerer's Tower)
      [Assist the kingdom's defenders](The Kingdom's Defenders)
                
      ::The Sorcerer's Tower
      You enter the dark sorcerer's tower, using your powers to overcome the magical traps and guardians. In a final battle, you defeat the sorcerer and restore peace to the enchanted kingdom.
      [Claim your reward](The Sorcerer's Treasure)
      ;Health{-20}
      ;Gold{300}
      ;Score{50}
      ;Inventory{Sorcerer's Staff}
                
      ::The Kingdom's Defenders
      You join the kingdom's defenders, using your powers to help protect the land and its inhabitants. Your heroic efforts earn you the gratitude and admiration of the kingdom.
      [Continue your journey](The Crossroads)
                
      ::The Haunted Graveyard
      You walk the path to the haunted graveyard, a place filled with restless spirits and ancient curses. As you explore, you encounter a powerful ghost in need of your help.
      [Assist the ghost](The Ghost's Request)
      [Leave the graveyard and continue your journey](The Crossroads)
                
      ::The Ghost's Request
      You agree to help the ghost, using your powers to uncover the truth behind its tragic past and put its soul to rest. In gratitude, the ghost rewards you with a valuable treasure.
      [Accept the treasure](The Ghost's Treasure)
      ;Gold{200}
      ;Score{30}
      ;Inventory{Ghostly Locket}
                
      ::The Ghost's Treasure
      You accept the ghost's treasure, feeling a sense of accomplishment and satisfaction. With the newfound reward in tow, you continue on your journey.
      [Continue your journey](The Crossroads)
                
      ::The Lost City of Gold
      You follow the path to the lost city of gold, a legendary place rumored to hold unimaginable wealth. As you explore the city, you uncover ancient secrets and face dangerous adversaries.
      [Search for the city's hidden treasures](The Hidden Treasures)
      [Leave the city and continue your journey](The Crossroads)
                
      ::The Hidden Treasures
      You delve deep into the lost city, discovering hidden treasures and powerful artifacts. As you claim your rewards, you feel a sense of pride in your accomplishments and the riches you've amassed.
      [Claim your reward](The City's Riches)
      ;Gold{1000}
      ;Score{150}
                
      ::The City's Riches
      You gather the lost city's riches, feeling a sense of accomplishment and pride. With newfound wealth in tow, you continue on your journey.
      [Continue your journey](The Crossroads)
                
      ::The End
      Having achieved your dreams and fulfilled your destiny, you settle down in a peaceful village, content with your accomplishments. As the sun sets on your epic journey, you know that your story will be remembered for generations to come.
      """;

  /**
   * Creates the stories in the folder.
   */
  public static void createStoriesInFolder() {
    File folderPath = new File(
        System.getProperty("user.home") + File.separator + ".gameSaves" + File.separator);

    if (!folderPath.exists()) {
      folderPath.mkdirs();
    }

    File folderStoryDucks = new File(folderPath + File.separator + "A ducks battle within.paths");

    if (!folderStoryDucks.exists()) {

      try (FileOutputStream fos = new FileOutputStream(
          new File(folderPath, "A ducks battle within.paths"));
          ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(aDucksBattleWithin);
      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error creating story");
        alert.setContentText("Error creating story");
        alert.showAndWait();
      }
    }

    File folderStoryMutant = new File(
        folderPath + File.separator + "A mutants odyssey for fortune and fame.paths");

    if (!folderStoryMutant.exists()) {

      try (FileOutputStream fos = new FileOutputStream(
          new File(folderPath, "A mutants odyssey for fortune and fame.paths"));
          ObjectOutputStream oos = new ObjectOutputStream(fos)) {
        oos.writeObject(aMutantsOdysseyForFortuneAndFame);
      } catch (IOException e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error creating story");
        alert.setContentText("Error creating story");
        alert.showAndWait();
      }
    }

  }

}
