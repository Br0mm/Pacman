package Project;

import Model.Model;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.io.IOException;


public class Controller {
    View b = new View();
    private boolean gameStopped = false;

    public Scene control() throws IOException {
        Pane root = new Pane();
        root.getChildren().addAll(b.addBackground(), b.createParent());
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case W:
                    if (Model.direction != Model.Direction.DOWN) Model.direction = Model.Direction.UP;
                    break;
                case A:
                    if (Model.direction != Model.Direction.RIGHT) Model.direction = Model.Direction.LEFT;
                    break;
                case S:
                    if (Model.direction != Model.Direction.UP) Model.direction = Model.Direction.DOWN;
                    break;
                case D:
                    if (Model.direction != Model.Direction.LEFT) Model.direction = Model.Direction.RIGHT;
                    break;
                case ENTER:
                    startGame();
                    break;
                case ESCAPE:
                    returnToMainMenu();
                    break;
                case SPACE:
                    if (!gameStopped) {
                        gameStopped = true;
                        View.timeline.stop();
                        gameStopped = true;
                    } else {
                        gameStopped = false;
                        View.timeline.play();
                        gameStopped = false;
                    }

            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.A || keyEvent.getCode() == KeyCode.D
                    || keyEvent.getCode() == KeyCode.W || keyEvent.getCode() == KeyCode.S)
                Model.direction = Model.Direction.NEUTRAL;
        });
        return scene;
    }

    public void startNewGame() throws IOException {
        this.b = new View();
        Main.primaryStage.setScene(control());
        startGame();
    }

    public void returnToMainMenu() {
        View.timeline.stop();
        View.timeline.getKeyFrames().remove(0);
        Main.primaryStage.setScene(Main.mainMenu);
    }

    public void startGame() {
        Model.gameOver = false;
        Model.direction = Model.Direction.NEUTRAL;
        Model.score = 0;
        View.timeline.play();
    }

    public static void gameOver() {
        Model.gameOver = true;
        View.timeline.stop();
    }
}