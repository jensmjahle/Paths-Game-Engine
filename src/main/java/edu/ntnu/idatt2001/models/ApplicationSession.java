package edu.ntnu.idatt2001.models;

/**
 * A class to represent the application session.
 */
public class ApplicationSession {
  private boolean isHighContrastMode;
  private static ApplicationSession instance;

  /**
   * Constructor for the ApplicationSession class.
   */
  private ApplicationSession() {
    this.isHighContrastMode = false;
  }

  /**
   * Method to get the instance of the application session.
   *
   * @return The instance of the application session.
   */
  public static ApplicationSession getInstance() {
    if (instance == null) {
      instance = new ApplicationSession();
    }
    return instance;
  }

  /**
   * Method to check if the application is in high contrast mode.
   *
   * @return True if the application is in high contrast mode, false otherwise.
   */
  public boolean isHighContrastMode() {
    return isHighContrastMode;
  }

  /**
   * Method to set the application to high contrast mode.
   *
   * @param isHighContrastMode True if the application is in high contrast mode, false otherwise.
   */
  public void setHighContrastMode(boolean isHighContrastMode) {
    this.isHighContrastMode = isHighContrastMode;
  }
}
