package edu.ntnu.idatt2001.models;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {

    private static final
    Passage passageTest = new Passage("TitleTest","ContentTest");
    private static final
    Link link1 = new Link("LinkText", "LinkReference");
    private static final
    Link link2 = new Link("LinkText2", "LinkReference2");

    @BeforeAll
    public static void setUp() {
        passageTest.addLink(link1);
    }

    @Test
    @DisplayName("Checks if getTitle() returns correct title")
    void getTitle() {
        assertEquals("TitleTest", passageTest.getTitle());
    }

    @Test
    @DisplayName("Checks if getContent() returns correct title")
    void getContent() {
        assertEquals("ContentTest", passageTest.getContent());
    }

    @Test
    @DisplayName("Checks if addLink() adds correct link to links")
    void addLink() {
        assertTrue(passageTest.addLink(link2));
        assertTrue(passageTest.getLinks().contains(link2));
    }

    @Test
    @DisplayName("Unable to add a link if the passage already contains that link")
    void unableToAddLinkIfPassageAlreadyContainsLink() {
        assertThrows(IllegalArgumentException.class, () -> passageTest.addLink(link1));
    }

    @Test
    @DisplayName("Checks if getLinks() returns correct")
    void getLinks() {
        assertTrue(passageTest.getLinks().contains(link1));
        assertTrue(passageTest.getLinks().contains(link2));
    }

    @Test
    @DisplayName("Checks if hasLinks() returns correct boolean")
    void hasLinks() {
        assertTrue(PassageTest.passageTest.hasLinks());
    }

    @Test
    @DisplayName("Checks if toString() returns correct string")
    void toStringTest() {
        String expected = "TITLETEST, Content: 'ContentTest'";
        String actual = passageTest.toString();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("setTitle() sets correct title")
    void setTitle() {
        String expected = "TitleTest";
        String actual = passageTest.getTitle();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("removeLink() removes correct link")
    void removeLink() {
        //Arrange
        Link testlink = new Link("LinkTest", "LinkTest");
        passageTest.addLink(testlink);

        //Act
        passageTest.removeLink(testlink);

        //Assert
        assertFalse(passageTest.getLinks().contains(testlink));
    }
}