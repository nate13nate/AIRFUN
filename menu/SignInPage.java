package menu;

import database.Database;
import database.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import mainfiles.Main;
import java.util.ArrayList;

public class SignInPage {
    public static Scene makeSignInPage(Button button, ArrayList<User> users) {
        VBox signIn = new VBox(10);
        HBox password = new HBox();
        HBox userName = new HBox();
        Label name = new Label("Name");
        Label pass = new Label("Password");
        Label message = new Label("");
        TextField enterName = new TextField();
        TextField enterPass = new TextField();
        Button submit = new Button();

        submit.setText("Submit");
        signIn.setStyle("-fx-background-image: url('./images/background.png'); -fx-background-size: cover; -fx-font-size: 18pt; -fx-font-family: SansSerif Bold;");
        Image image = new Image(
                "./images/button.png",
                300,
                94,
                false,
                false
        );
        BackgroundImage bgim = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background bg = new Background(bgim);

        submit.setBackground(bg);
        submit.setMinWidth(300);
        submit.setMinHeight(94);

        signIn.setAlignment(Pos.CENTER);

        userName.getChildren().addAll(name, enterName);
        password.getChildren().addAll(pass, enterPass);

        submit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(users.get(users.size()-1).getUserName());
                if (enterName.getText().compareTo("") != 0) {
                    for (int i = 0; i < users.size(); i++) {
                        if (enterName.getText().compareTo(users.get(i).getUserName()) == 0) {
                            if (enterPass.getText().compareTo(users.get(i).getPass()) == 0) {
                                message.setText("Successfully signed in! You can press the back button to return to the menu");
                                Main.isSignedIn = true;
                                Main.userName = users.get(i).getUserName();
                                Main.userScore = users.get(i).getScore();
                                break;
                            }
                        }
                    }
                    if (!Main.isSignedIn) {
                        message.setText("Incorrect username or password");
                    }
                }
            }
        });

        signIn.getChildren().addAll(userName, password, message, submit, button);
        return new Scene(signIn, Main.GAME_WIDTH, Main.GAME_HEIGHT);
    }
}
