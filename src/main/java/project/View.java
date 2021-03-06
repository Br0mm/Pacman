package project;

import model.FieldPoint;
import model.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;


public class View {
    static public Timeline timeline = new Timeline();
    private static final int bodySize = 20;

    public Parent createParent() throws IOException {
        final int width = 28 * bodySize;
        final int height = 31 * bodySize;
        Pane root = new AnchorPane();
        root.setPrefSize(width, height);

        Node heroBody = createCharacterFront(0);
        Node blinkyBody = createCharacterFront(1);
        Node pinkyBody = createCharacterFront(2);
        Node inkyBody = createCharacterFront(3);
        Group gameField = createGameField();

        KeyFrame frame = new KeyFrame(Duration.seconds(0.01), event -> {
            Model.classicGame();
            Model.blinkyMove();
            Model.inkyMove();
            Model.pinkyMove();
            Model.gameOverCheck();
            heroBody.setTranslateX(Model.pacman.currentX * bodySize);
            heroBody.setTranslateY(Model.pacman.currentY * bodySize);
            blinkyBody.setTranslateX(Model.blinky.currentX * bodySize);
            blinkyBody.setTranslateY(Model.blinky.currentY * bodySize);
            inkyBody.setTranslateX(Model.inky.currentX * bodySize);
            inkyBody.setTranslateY(Model.inky.currentY * bodySize);
            pinkyBody.setTranslateX(Model.pinky.currentX * bodySize);
            pinkyBody.setTranslateY(Model.pinky.currentY * bodySize);
            gameField.getChildren().get(Model.pacman.currentY * 28 + Model.pacman.currentX).setVisible(false);
        });
        timeline.getKeyFrames().add(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        root.getChildren().addAll(gameField, heroBody, inkyBody, pinkyBody, blinkyBody);
        return root;
    }

    public Node createCharacterFront(int colorIndex) {
        Circle circle = new Circle();
        circle.setRadius(bodySize / 2);
        circle.setCenterY(bodySize / 2);
        circle.setCenterX(bodySize / 2);
        switch (colorIndex) {
            case 0:
                circle.setFill(Color.YELLOW);
                break;
            case 1:
                circle.setFill(Color.RED);
                break;
            case 2:
                circle.setFill(Color.PINK);
                break;
            case 3:
                circle.setFill(Color.BLUE);
                break;
        }
        return circle;
    }

    public static Group createGameField() {
        Group field = new Group();
        for (int i = 0; i < 31; i++) {
            for (int j = 0; j < 28; j++) {
                if (Model.field.gameField[i][j].isObstacle()) {
                    Rectangle rect = new Rectangle(bodySize, bodySize);
                    rect.setY(i * bodySize);
                    rect.setX(j * bodySize);
                    rect.setFill(Color.DARKBLUE);
                    field.getChildren().add(rect);
                }
                else if (Model.field.gameField[i][j].getType() == FieldPoint.PointType.COMMON) {
                    Circle circle = new Circle();
                    circle.setRadius(bodySize / 5);
                    circle.setCenterY(i * bodySize + bodySize / 2);
                    circle.setCenterX(j * bodySize + bodySize / 2);
                    circle.setFill(Color.WHITE);
                    field.getChildren().add(circle);
                }
            }
        }
        return field;
    }


    public ImageView addBackground() throws MalformedURLException {
        return new ImageView(new Image(new File("background/field.jpg").toURI().toURL().toString(),
                28 * bodySize, 31 * bodySize, false, true));
    }
}
