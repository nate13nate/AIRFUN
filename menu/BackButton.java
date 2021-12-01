package menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BackButton {

    public Button createBackButton(Stage stage, VBox newScene) {
        // back button
        Button backButton = new Button();
        backButton.setText("Start game");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(new Scene(newScene, 300, 300));
            }
        });

        return backButton;
    }
}
