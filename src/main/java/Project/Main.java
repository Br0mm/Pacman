package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {
    static Stage primaryStage;
    static Scene mainMenu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/MainMenu.fxml").toURI().toURL());
        loader.setController(new Controller());
        mainMenu = new Scene(loader.load());
        primaryStage.setTitle("Pacman");
        primaryStage.setScene(mainMenu);
        (Main.primaryStage = primaryStage).show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
