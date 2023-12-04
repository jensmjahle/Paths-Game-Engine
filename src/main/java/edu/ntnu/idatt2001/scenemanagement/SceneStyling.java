package edu.ntnu.idatt2001.scenemanagement;

import java.util.Objects;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * Class to style the scenes.
 */
public class SceneStyling {

  /**
   * Binds font size to root width.
   *
   * @param node the node to bind
   * @param size the size of the font
   * @param root the root pane
   */
  public static void bindFontSize(Node node, double size, Pane root) {
    DoubleBinding sizeBinding = root.widthProperty().multiply(size);
    node.styleProperty().bind(Bindings.concat(
        "-fx-font-size: ", sizeBinding.asString(), "pt;"
    ));
  }


  /**
   * Creates root layout.
   *
   * @param root the root
   */
  public static void setBackgroundLayout(Pane root) {
    String backgroundImage = Objects.requireNonNull(
        SceneStyling.class.getResource("/images/pathsBackground.png")).toExternalForm();
    root.setStyle("-fx-background-image: url('" + backgroundImage + "');"
        + "-fx-background-position: center center;"
        + "-fx-background-repeat: stretch;"
        + "-fx-background-size: cover;");
  }

  /**
   * Creates a container with a list view.
   *
   * @param listView        the list view
   * @param headline        the headline
   * @param placeholderText the placeholder text
   * @param tooltipText     the tooltip text
   * @param root            the root
   * @param <T>             the type parameter
   * @return the v box
   */
  public static <T> VBox createContainerWithListView(ListView<T> listView, String headline,
                                 String placeholderText, String tooltipText, Pane root) {
    ImageView imageView = new ImageView(new Image(
        Objects.requireNonNull(
            SceneStyling.class.getResourceAsStream("/images/informationIcon.png"))));
    SceneStyling.bindFontSize(imageView, 0.012, root);
    imageView.fitWidthProperty().bind(root.widthProperty().multiply(0.015));
    imageView.fitHeightProperty().bind(root.widthProperty().multiply(0.015));
    imageView.setPreserveRatio(true);

    Tooltip tooltip = new Tooltip(tooltipText);
    tooltip.setShowDelay(Duration.ZERO);
    tooltip.setShowDuration(Duration.INDEFINITE);
    tooltip.setHideDelay(Duration.ZERO);

    Tooltip.install(imageView, tooltip);

    HBox headlineContainer = new HBox();
    Label headlineLabel = new Label(headline);
    SceneStyling.bindFontSize(headlineLabel, 0.01, root);
    headlineContainer.getChildren().addAll(headlineLabel, imageView);
    headlineContainer.setSpacing(5);
    headlineContainer.setAlignment(Pos.CENTER);

    listView.setPlaceholder(new Label(placeholderText));

    VBox container = new VBox();
    container.getChildren().addAll(
        headlineContainer,
        listView
    );

    container.setSpacing(1);
    container.setAlignment(Pos.TOP_CENTER);
    container.getStyleClass().add("passage-content-container");

    return container;
  }

  /**
   * Creates a container with a list view.
   *
   * @param root the root
   * @return the v box
   */
  public static VBox createTradeMarkContainer(Pane root) {
    Label trademark = new Label("© 2023 Paths");
    SceneStyling.bindFontSize(trademark, 0.009, root);

    VBox tradeMarkContainer = new VBox();
    tradeMarkContainer.getChildren().addAll(trademark);
    tradeMarkContainer.setPadding(
        new Insets(20, 0, 0, 20));
    tradeMarkContainer.setAlignment(Pos.BOTTOM_RIGHT);

    return tradeMarkContainer;
  }

  /**
   * Creates a notification.
   *
   * @param message the message
   */
  public static void showNotification(String title, String message, Pane root) {
    Notifications.create()
        .title(title)
        .text(message)
        .position(Pos.BOTTOM_CENTER)
        .owner(root)
        .hideAfter(Duration.seconds(3))
        .show();
  }

  /**
   * Shows an alert with the provided message and title.
   *
   * @param alertType the type of alert
   * @param title     title of the alert
   * @param message   the content text of the alert
   */
  public static void showAlert(AlertType alertType, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle("Error");
    alert.setHeaderText(title);
    alert.setContentText(message);
    alert.showAndWait();
  }

}
